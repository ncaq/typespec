import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.HttpHeaders;
import com.azure.core.util.BinaryData;
import com.azure.core.util.CoreUtils;
import com.azure.core.util.DateTimeRfc1123;
import com.azure.core.util.logging.ClientLogger;
import com.azure.core.util.serializer.ObjectSerializer;
import com.azure.core.util.serializer.TypeReference;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.DateTimeException;
import java.time.Duration;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;

// DO NOT modify this helper class

final class PollingUtils {

    public static final TypeReference<Map<String, Object>> POST_POLL_RESULT_TYPE_REFERENCE
        = new TypeReference<Map<String, Object>>() { };

    public static final HttpHeaderName OPERATION_LOCATION_HEADER
        = HttpHeaderName.fromString("Operation-Location");

    public static final String HTTP_METHOD = "httpMethod";
    public static final String REQUEST_URL = "requestURL";
    public static final String POLL_RESPONSE_BODY = "pollResponseBody";

    private static final String FORWARD_SLASH = "/";
    public static String getAbsolutePath(String path, String endpoint, ClientLogger logger) {
        try {
            URI uri = new URI(path);
            if (!uri.isAbsolute()) {
                if (CoreUtils.isNullOrEmpty(endpoint)) {
                    throw logger.logExceptionAsError(new IllegalArgumentException(
                        "Relative path requires endpoint to be non-null and non-empty to create an absolute path."));
                }

                if (endpoint.endsWith(FORWARD_SLASH) && path.startsWith(FORWARD_SLASH)) {
                    return endpoint + path.substring(1);
                } else if (!endpoint.endsWith(FORWARD_SLASH) && !path.startsWith(FORWARD_SLASH)) {
                    return endpoint + FORWARD_SLASH + path;
                } else {
                    return endpoint + path;
                }
            }
        } catch (URISyntaxException ex) {
            throw logger.logExceptionAsWarning(new IllegalArgumentException("'path' must be a valid URI.", ex));
        }
        return path;
    }

    public static <T> T deserializeResponseSync(BinaryData binaryData, ObjectSerializer serializer, TypeReference<T> typeReference) {
        T value;
        if (binaryData == null) {
            value = null;
        } else if (typeReference.getJavaClass().isAssignableFrom(BinaryData.class)) {
            // T is BinaryData
            value = typeReference.getJavaClass().cast(binaryData.toReplayableBinaryData());
        } else {
            value = binaryData.toObject(typeReference, serializer);
        }
        return value;
    }

    @SuppressWarnings("unchecked")
    public static <T> Mono<T> deserializeResponse(BinaryData binaryData, ObjectSerializer serializer, TypeReference<T> typeReference) {
        Mono<T> value;
        if (binaryData == null) {
            value = Mono.empty();
        } else if (typeReference.getJavaClass().isAssignableFrom(BinaryData.class)) {
            // T is BinaryData
            value = (Mono<T>) binaryData.toReplayableBinaryDataAsync();
        } else {
            value = binaryData.toObjectAsync(typeReference, serializer);
        }
        return value;
    }

    private static final HttpHeaderName RETRY_AFTER_MS_HEADER = HttpHeaderName.fromString("retry-after-ms");
    private static final HttpHeaderName X_MS_RETRY_AFTER_MS_HEADER = HttpHeaderName.fromString("x-ms-retry-after-ms");
    public static Duration getRetryAfterFromHeaders(HttpHeaders headers, Supplier<OffsetDateTime> nowSupplier) {
        // Found 'x-ms-retry-after-ms' header, use a Duration of milliseconds based on the value.
        Duration retryDelay = tryGetRetryDelay(headers, X_MS_RETRY_AFTER_MS_HEADER, s -> tryGetDelayMillis(s));
        if (retryDelay != null) {
            return retryDelay;
        }

        // Found 'retry-after-ms' header, use a Duration of milliseconds based on the value.
        retryDelay = tryGetRetryDelay(headers, RETRY_AFTER_MS_HEADER, s -> tryGetDelayMillis(s));
        if (retryDelay != null) {
            return retryDelay;
        }

        // Found 'Retry-After' header. First, attempt to resolve it as a Duration of seconds. If that fails, then
        // attempt to resolve it as an HTTP date (RFC1123).
        retryDelay = tryGetRetryDelay(headers, HttpHeaderName.RETRY_AFTER,
            headerValue -> tryParseLongOrDateTime(headerValue, nowSupplier));

        // Either the retry delay will have been found or it'll be null, null indicates no retry after.
        return retryDelay;
    }
    private static Duration tryGetRetryDelay(HttpHeaders headers, HttpHeaderName headerName,
                                             Function<String, Duration> delayParser) {
        String headerValue = headers.getValue(headerName);

        return CoreUtils.isNullOrEmpty(headerValue) ? null : delayParser.apply(headerValue);
    }
    private static Duration tryParseLongOrDateTime(String value, Supplier<OffsetDateTime> nowSupplier) {
        long delaySeconds;
        try {
            OffsetDateTime retryAfter = new DateTimeRfc1123(value).getDateTime();

            delaySeconds = nowSupplier.get().until(retryAfter, ChronoUnit.SECONDS);
        } catch (DateTimeException ex) {
            delaySeconds = tryParseLong(value);
        }

        return (delaySeconds >= 0) ? Duration.ofSeconds(delaySeconds) : null;
    }
    private static long tryParseLong(String value) {
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException ex) {
            return -1;
        }
    }
    private static Duration tryGetDelayMillis(String value) {
        long delayMillis = tryParseLong(value);
        return (delayMillis >= 0) ? Duration.ofMillis(delayMillis) : null;
    }
}
