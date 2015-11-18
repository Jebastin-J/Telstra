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

import java.util.HashMap;

/**
 * Model Class for Request.
 *
 * @param <T> - Generic Response Type
 * @param <O> - Generic Request Type
 */
public class RequestInfo<T, O> {


    private static final String LOG_TAG = "RequestInfo";

    private Context mContext;
    //Headers for Authenticated service calls
    private HashMap<String, String> headers;
    //Request Data Model
    private O requestModel;
    //Response Data Model
    private T responseModel;
    //Specifies the HttpMethod Type of this Request (GET / POST ..)
    private HttpMethod httpMethod;
    //Specifies the URL of this Request
    private String URL;

    public RequestInfo(Context context) {
        //Get WCToken and Check the Validity of the Token. If Invalid, request it back again.
        mContext = context;


    }

    /**
     * Getter Method for Request Headers
     *
     * @return RequestDataModel
     */
    public HashMap<String, String> getHeaders() {
        return headers;
    }

    /**
     * Setter Method for Request Headers
     *
     * @param headers
     */
    public void setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
    }

    /**
     * Getter Method for Request Data Model
     *
     * @return RequestDataModel
     */
    public O getRequestModel() {
        return requestModel;
    }

    /**
     * Setter Method for Request Data Model
     *
     * @param requestModel RequestDataModel Object
     */
    public void setRequestModel(O requestModel) {
        this.requestModel = requestModel;
    }

    /**
     * Getter for Response Data Model
     *
     * @param <T> - Response Data Model Class Type
     * @return Response Data Model Object
     */
    public <T> T getResponseModel() {
        return (T) responseModel;
    }

    /**
     * Setter for Response Data Model
     *
     * @param responseModel Response Data Model Object
     */
    public void setResponseModel(T responseModel) {
        this.responseModel = responseModel;
    }

    /**
     * Getter for URL of this request
     *
     * @return URL
     */
    public String getURL() {
        return URL;
    }

    /**
     * Setter for URL of this Request
     *
     * @param URL
     */
    public void setURL(String URL) {
        this.URL = URL;
    }

    /**
     * @return int value of HttpMethod
     */
    public int getHttpMethod() {
        return httpMethod.ordinal();
    }

    /**
     * Setter for HttpMethod of this Request
     *
     * @param httpMethod
     */
    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    @Override
    public String toString() {
        return "RequestInfo{" +
                ", headers=" + headers +
                ", requestModel=" + requestModel +
                ", responseModel=" + responseModel +
                ", httpMethod=" + httpMethod +
                ", URL='" + URL + '\'' +
                '}';
    }

}
