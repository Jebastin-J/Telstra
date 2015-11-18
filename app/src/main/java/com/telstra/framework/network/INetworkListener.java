/**
 * @author    Cognizant
 *
 * @brief     Defines the global constants used across the application
 *
 * @copyright Copyright (c) 2015 Cognizant. All rights reserved.
 *
 * @version   1.0
 */

package com.telstra.framework.network;

/**
 * The application that needs to get the Network specific callbacks needs
 * to implement and register this Listener with NetworkFactory.
 * <p/>
 * This takes two Generic types.
 *
 * @param <T> - Represents the ResponseModel Type
 * @param <O> - Represents the RequestModel Type
 */
public interface INetworkListener<T, O> {
    //Call back method in case of successful response
    void onSuccess(ResponseInfo<O> responseObject);

    //Call back method in case of Exceptions / Errors that happened while processing
    //the request. (like exception in server, connection timeout etc...).
    void onError(ResponseInfo responseObject);

}
