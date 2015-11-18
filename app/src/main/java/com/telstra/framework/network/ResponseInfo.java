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


public class ResponseInfo<O> {

    //Unique Token for the given session of communication (for HTTP request)
    private String wcAuthToken;

    //Unique Token for the given session of communication (for HTTPS request)
    private String wcTrustedAuthToken;

    //Response Data Model
    private O responseData;

    //Holds the response code
    private int responseCode;

    //Holds error message if any.
    private String errorMessage;

    /**
     * Getter for WCAuthToken
     *
     * @return wcAuthToken
     */
    public String getWcAuthToken() {
        return wcAuthToken;
    }

    /**
     * Setter for WCAuthToken
     *
     * @param wcAuthToken
     */
    public void setWcAuthToken(String wcAuthToken) {
        this.wcAuthToken = wcAuthToken;
    }

    /**
     * Getter for WCTrustedAuthToken
     *
     * @return WCTrustedAuthToken
     */
    public String getWcTrustedAuthToken() {
        return wcTrustedAuthToken;
    }

    /**
     * Setter for WCTrustedAuthToken
     *
     * @param wcTrustedAuthToken
     */
    public void setWcTrustedAuthToken(String wcTrustedAuthToken) {
        this.wcTrustedAuthToken = wcTrustedAuthToken;
    }

    /**
     * Getter for Response Data Model
     *
     * @return Response Data Model Object
     */
    public O getResponseData() {
        return responseData;
    }

    /**
     * Setter for Response Data Model
     *
     * @param responseData Response Data Model Object
     */
    public void setResponseData(O responseData) {
        this.responseData = responseData;
    }

    /**
     * Getter for Response Code
     *
     * @return response code
     */
    public int getResponseCode() {
        return responseCode;
    }

    /**
     * Setter for response code
     *
     * @param responseCode
     */
    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * Getter method for response error message
     *
     * @return
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Setter for response error message
     *
     * @param errorMessage
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
