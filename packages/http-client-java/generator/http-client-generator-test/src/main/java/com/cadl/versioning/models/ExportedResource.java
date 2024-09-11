// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.versioning.models;

import com.azure.core.annotation.Generated;
import com.azure.core.annotation.Immutable;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The ExportedResource model.
 */
@Immutable
public final class ExportedResource implements JsonSerializable<ExportedResource> {
    /*
     * The id property.
     */
    @Generated
    private final String id;

    /*
     * The resourceUri property.
     */
    @Generated
    private final String resourceUri;

    /**
     * Creates an instance of ExportedResource class.
     * 
     * @param id the id value to set.
     * @param resourceUri the resourceUri value to set.
     */
    @Generated
    private ExportedResource(String id, String resourceUri) {
        this.id = id;
        this.resourceUri = resourceUri;
    }

    /**
     * Get the id property: The id property.
     * 
     * @return the id value.
     */
    @Generated
    public String getId() {
        return this.id;
    }

    /**
     * Get the resourceUri property: The resourceUri property.
     * 
     * @return the resourceUri value.
     */
    @Generated
    public String getResourceUri() {
        return this.resourceUri;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        jsonWriter.writeStringField("resourceUri", this.resourceUri);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ExportedResource from the JsonReader.
     * 
     * @param jsonReader The JsonReader being read.
     * @return An instance of ExportedResource if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ExportedResource.
     */
    @Generated
    public static ExportedResource fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String id = null;
            String resourceUri = null;
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    id = reader.getString();
                } else if ("resourceUri".equals(fieldName)) {
                    resourceUri = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }
            return new ExportedResource(id, resourceUri);
        });
    }
}
