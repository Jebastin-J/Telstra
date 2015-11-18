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

import android.content.Context;

import com.telstra.framework.network.json.JSONRequest;


/**
 * This Class provides a factory of different webservice request / response.
 * (Like JSON, XML, Text...)
 * <p/>
 * The Application can fetch the required instance by passing the RequestType to
 * getInstance() method.
 */
public class NetworkFactory {

    /**
     * Returns the specific instance from the Factory
     *
     * @param requestType - {RequestType.REQUEST_TYPE_JSON, REQUEST_TYPE_XML}
     * @param context     - Application Context
     * @return Instance of specified Request
     */
    public static IRequest getInstance(RequestType requestType, Context context) {
        if (requestType == RequestType.REQUEST_TYPE_JSON) {
            return new JSONRequest(context);
        } else if (requestType == RequestType.REQUEST_TYPE_XML) {
            // Code for XML Type Goes Here
        }
        return null;
    }
}
