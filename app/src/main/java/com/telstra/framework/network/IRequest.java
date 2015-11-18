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
 * The Base Interface for all RequestTypes (JSON, SOAP, Text...) implementation.
 * Any Specific Implementation has to implement IRequest and override the methods.
 * <p/>
 * The NetworkFactory returns the instance of specific request(getInstance()) of type IRequest.
 * <p/>
 * This Interface specifics, two Generic Types
 *
 * @param <T> - T Specifies the ResponseModelType (Class)
 * @param <O> - O Specifies the RequestModelType (Class)
 */
public interface IRequest<T, O> {
    /**
     * Call back interface method for processing the Server Request
     *
     * @param requestInfo - RequestInfo Object which holds all the required Request Details
     */
    void processRequest(RequestInfo<T, O> requestInfo, Class<T> TClass, Class<O> OClass, boolean invalidateCache);

    void registerListener(INetworkListener networkListener);

    boolean cancelRequest();
}
