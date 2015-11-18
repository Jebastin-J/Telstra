/**
 * @author Cognizant
 * @brief Defines the global constants used across the application
 * @copyright Copyright (c) 2015 Cognizant. All rights reserved.
 * @version 1.0
 */
package com.telstra.framework.network.json;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Request Object Class for the GSON Request and Response.
 *
 * @param <T> - Response Object Type
 * @param <O> - Request Object Type
 */
public class GSONObjectRequest<T, O> extends Request<T> {

    private static final String PROTOCOL_CHARSET = "utf-8";
    private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", PROTOCOL_CHARSET);
    private static final String LOG_TAG = "GSONObjectRequest";

    private final Gson mGson;

    private final Class<T> clazz;

    //Map Representing the Headers
    private Map<String, String> mHeaders = new HashMap<String, String>();

    //Listener for the response from RequestQueue
    private final Response.Listener<T> mListener;

    private final Class<O> inputType;

    String authToken = null;

    Priority defaultPriority = Priority.HIGH;
    Priority setPriority = null;

    private O postObject;

    public GSONObjectRequest(int method, String url, Class<T> clazz, Map<String, String> headers, Map<String, String> params,
                             O postObject, Class<O> inputType, Response.Listener<T> listener, Response.ErrorListener errorListener, Context context, boolean skipHeader) {

        super(method, url, errorListener);
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.disableHtmlEscaping();
        gsonBuilder.registerTypeAdapter(Collection.class, new CollectionDeserializer());

        if (headers != null)
            this.mHeaders = headers;

        this.mGson = gsonBuilder.create();
        this.clazz = clazz;
        this.mListener = listener;
        this.inputType = inputType;
        this.postObject = postObject;
    }

    @Override
    public Priority getPriority() {

        if (setPriority == null) {

            return defaultPriority;
        }

        return setPriority;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Log.d(LOG_TAG, "getHeaders");

        return mHeaders;
    }

    @Override
    public byte[] getBody() {

        String jsonString = mGson.toJson(postObject, inputType);

        try {

            Log.d("LOG_TAG", "GSONObject Request getBody: " + jsonString);

            return jsonString.getBytes(PROTOCOL_CHARSET);
        } catch (UnsupportedEncodingException e) {

            VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", jsonString, PROTOCOL_CHARSET);

            return null;
        }
    }

    @Override
    public String getBodyContentType() {
        return PROTOCOL_CONTENT_TYPE;
    }

    @Override
    protected void deliverResponse(T response) {

        if (response != null) {

            Log.d("response", response.toString());
        }

        mListener.onResponse(response);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {

        try {

            String strJson = new String(response.data, HttpHeaderParser.parseCharset(response.headers));

            Log.d("Response: ", strJson);

            return Response.success(mGson.fromJson(strJson, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return Response.error(new ParseError(e));
        }
    }
}
