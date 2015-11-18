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
 * Enumeration Representing various server request/response types.
 */
public enum RequestType {
    //0 - Indicates that the request/response Type type is of JSON format
    REQUEST_TYPE_JSON,
    //1 - Indicates that the request type is of XML (SOAP) format
    REQUEST_TYPE_XML
}
