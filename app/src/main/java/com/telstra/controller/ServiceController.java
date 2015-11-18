package com.telstra.controller;

import android.content.Context;

import com.telstra.R;
import com.telstra.framework.network.HttpMethod;
import com.telstra.framework.network.INetworkListener;
import com.telstra.framework.network.NetworkFactory;
import com.telstra.framework.network.RequestInfo;
import com.telstra.framework.network.RequestType;
import com.telstra.framework.network.json.JSONRequest;
import com.telstra.model.ServiceResponse;

/**
 * Created by 461495 on 11/15/2015.
 */
public class ServiceController {
    private Context mContext = null;
    private JSONRequest<Object, Object> mJsonRequest = null;

    public ServiceController(Context context, INetworkListener listener) {

        mContext = context;
        mJsonRequest = (JSONRequest) NetworkFactory.getInstance(RequestType.REQUEST_TYPE_JSON, context);
        mJsonRequest.registerListener(listener);
    }

    /* Fetch the response from the url */
    public void getServiceResponse() {

        //Http Request Info
        RequestInfo<ServiceResponse, Object> requestInfo = new RequestInfo<ServiceResponse, Object>(mContext);
        requestInfo.setRequestModel(new Object());
        requestInfo.setURL(mContext.getString(R.string.service_url));
        requestInfo.setHttpMethod(HttpMethod.GET);
        mJsonRequest.setSkipHeader(true);
        mJsonRequest.processRequest(requestInfo, ServiceResponse.class, Object.class, true);
    }
}