// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.
// Code generated by Microsoft (R) TypeSpec Code Generator.

package com.cadl.armresourceprovider.fluent;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.PagedIterable;
import com.azure.core.http.rest.Response;
import com.azure.core.management.polling.PollResult;
import com.azure.core.util.Context;
import com.azure.core.util.polling.SyncPoller;
import com.cadl.armresourceprovider.fluent.models.ResultInner;
import com.cadl.armresourceprovider.fluent.models.TopLevelArmResourceInner;
import com.cadl.armresourceprovider.models.TopLevelArmResourceUpdate;

/**
 * An instance of this class provides access to all the operations defined in TopLevelArmResourceInterfacesClient.
 */
public interface TopLevelArmResourceInterfacesClient {
    /**
     * Get a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a TopLevelArmResource along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<TopLevelArmResourceInner> getByResourceGroupWithResponse(String resourceGroupName,
        String topLevelArmResourceName, Context context);

    /**
     * Get a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a TopLevelArmResource.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    TopLevelArmResourceInner getByResourceGroup(String resourceGroupName, String topLevelArmResourceName);

    /**
     * Create a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param resource Resource create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of concrete tracked resource types can be created by aliasing this
     * type using a specific property type.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<TopLevelArmResourceInner>, TopLevelArmResourceInner> beginCreateOrUpdate(
        String resourceGroupName, String topLevelArmResourceName, TopLevelArmResourceInner resource);

    /**
     * Create a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param resource Resource create parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of concrete tracked resource types can be created by aliasing this
     * type using a specific property type.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<TopLevelArmResourceInner>, TopLevelArmResourceInner> beginCreateOrUpdate(
        String resourceGroupName, String topLevelArmResourceName, TopLevelArmResourceInner resource, Context context);

    /**
     * Create a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param resource Resource create parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return concrete tracked resource types can be created by aliasing this type using a specific property type.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    TopLevelArmResourceInner createOrUpdate(String resourceGroupName, String topLevelArmResourceName,
        TopLevelArmResourceInner resource);

    /**
     * Create a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param resource Resource create parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return concrete tracked resource types can be created by aliasing this type using a specific property type.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    TopLevelArmResourceInner createOrUpdate(String resourceGroupName, String topLevelArmResourceName,
        TopLevelArmResourceInner resource, Context context);

    /**
     * Update a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param properties The resource properties to be updated.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return concrete tracked resource types can be created by aliasing this type using a specific property type along
     * with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    Response<TopLevelArmResourceInner> updateWithResponse(String resourceGroupName, String topLevelArmResourceName,
        TopLevelArmResourceUpdate properties, Context context);

    /**
     * Update a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param properties The resource properties to be updated.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return concrete tracked resource types can be created by aliasing this type using a specific property type.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    TopLevelArmResourceInner update(String resourceGroupName, String topLevelArmResourceName,
        TopLevelArmResourceUpdate properties);

    /**
     * Delete a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<Void>, Void> beginDelete(String resourceGroupName, String topLevelArmResourceName);

    /**
     * Delete a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<Void>, Void> beginDelete(String resourceGroupName, String topLevelArmResourceName,
        Context context);

    /**
     * Delete a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void delete(String resourceGroupName, String topLevelArmResourceName);

    /**
     * Delete a TopLevelArmResource.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    void delete(String resourceGroupName, String topLevelArmResourceName, Context context);

    /**
     * List TopLevelArmResource resources by resource group.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a TopLevelArmResource list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<TopLevelArmResourceInner> listByResourceGroup(String resourceGroupName);

    /**
     * List TopLevelArmResource resources by resource group.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a TopLevelArmResource list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<TopLevelArmResourceInner> listByResourceGroup(String resourceGroupName, Context context);

    /**
     * List TopLevelArmResource resources by subscription ID.
     * 
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a TopLevelArmResource list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<TopLevelArmResourceInner> list();

    /**
     * List TopLevelArmResource resources by subscription ID.
     * 
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response of a TopLevelArmResource list operation as paginated response with {@link PagedIterable}.
     */
    @ServiceMethod(returns = ReturnType.COLLECTION)
    PagedIterable<TopLevelArmResourceInner> list(Context context);

    /**
     * A long-running resource action.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<ResultInner>, ResultInner> beginAction(String resourceGroupName,
        String topLevelArmResourceName);

    /**
     * A long-running resource action.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link SyncPoller} for polling of long-running operation.
     */
    @ServiceMethod(returns = ReturnType.LONG_RUNNING_OPERATION)
    SyncPoller<PollResult<ResultInner>, ResultInner> beginAction(String resourceGroupName,
        String topLevelArmResourceName, Context context);

    /**
     * A long-running resource action.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ResultInner action(String resourceGroupName, String topLevelArmResourceName);

    /**
     * A long-running resource action.
     * 
     * @param resourceGroupName The name of the resource group. The name is case insensitive.
     * @param topLevelArmResourceName arm resource name for path.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws com.azure.core.management.exception.ManagementException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    ResultInner action(String resourceGroupName, String topLevelArmResourceName, Context context);
}
