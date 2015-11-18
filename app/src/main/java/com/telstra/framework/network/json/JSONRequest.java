/**
 * @author    Cognizant
 *
 * @brief     Defines the global constants used across the application
 *
 * @copyright Copyright (c) 2015 Cognizant. All rights reserved.
 *
 * @version   1.0
 */
package com.telstra.framework.network.json;

import android.content.Context;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.telstra.R;
import com.telstra.framework.network.INetworkListener;
import com.telstra.framework.network.IRequest;
import com.telstra.framework.network.RequestInfo;
import com.telstra.framework.network.ResponseInfo;

public class JSONRequest<T, O> implements IRequest {

    boolean bIsSkipHeader = false;

    private Context mContext;

    RequestQueue requestQueue = null;
    INetworkListener networkListener = null;
    Class<T> TClass = null;
    Class<O> OClass = null;
    GSONObjectRequest<T, O> jsObjRequest;

    public JSONRequest() {
    }

    public JSONRequest(Context context) {

        requestQueue = RequestQSingleton.getInstance(context).getRequestQueue();
        mContext = context;
    }

    public boolean isSkipHeader() {
        return bIsSkipHeader;
    }

    public void setSkipHeader(boolean bIsSkipHeader) {
        this.bIsSkipHeader = bIsSkipHeader;
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    @Override
    public void processRequest(RequestInfo requestInfo, Class TClass, Class OClass, boolean invalidateCache) {

        this.TClass = TClass;
        this.OClass = OClass;

        jsObjRequest = new GSONObjectRequest<T, O>(
                requestInfo.getHttpMethod(),
                requestInfo.getURL(),
                this.TClass,
                requestInfo.getHeaders(),
                null,
                (O) requestInfo.getRequestModel(),
                this.OClass,
                createSuccessListener(),
                createErrorListener(),
                mContext,
                bIsSkipHeader);

        jsObjRequest.setTag(mContext.getString(R.string.app_name));

        //Increasing the timeout to 20secs and setting retry limit to 0
        jsObjRequest.setRetryPolicy(new DefaultRetryPolicy(20000, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        if (RequestQSingleton.getInstance(mContext).getRequestQueue().getCache() != null && invalidateCache) {
            RequestQSingleton.getInstance(mContext).getRequestQueue().getCache().invalidate(requestInfo.getURL(), true);
        }

        RequestQSingleton.getInstance(mContext).getRequestQueue().add(jsObjRequest);
    }

    @Override
    public void registerListener(INetworkListener networkListener) {

        this.networkListener = networkListener;
    }

    @Override
    public boolean cancelRequest() {

        if (!jsObjRequest.hasHadResponseDelivered()) {

            jsObjRequest.cancel();

            if (jsObjRequest.isCanceled()) {
                return true;
            }
        }

        return false;
    }

    private Response.Listener<T> createSuccessListener() {

        return new Response.Listener<T>() {

            @Override
            public void onResponse(T response) {

                ResponseInfo<T> responseInfo = new ResponseInfo();
                responseInfo.setResponseData(response);
                networkListener.onSuccess(responseInfo);
            }
        };
    }

    private Response.ErrorListener createErrorListener() {

        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(final VolleyError volleyError) {

                volleyError.printStackTrace();

                try {
                    ResponseInfo<VolleyError> responseInfo = new ResponseInfo();
                    responseInfo.setResponseData(volleyError);
                    responseInfo.setErrorMessage(mContext.getString(R.string.network_error));

                    networkListener.onError(responseInfo);

                    return;
                }

                catch (Exception ex) {

                    ex.printStackTrace();
                }
            }
        };
    }
}