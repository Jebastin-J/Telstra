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
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;


/**
 * Singleton class for the Request Queue and Image Caching.
 */
public class RequestQSingleton {

    private static RequestQSingleton gInstance;
    private static Context gCtx;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;

    private final static int NUMBER_OF_IMAGES_TO_CACHE = 5;

    private RequestQSingleton(Context context) {
        gCtx = context;
        mRequestQueue = getRequestQueue();

        /**
         * Uses LRU cache to Cache the Number of Images that needs to be cached in the
         * application.
         */
        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(NUMBER_OF_IMAGES_TO_CACHE);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized RequestQSingleton getInstance(Context context) {
        if (gInstance == null) {
            gInstance = new RequestQSingleton(context);
        }
        return gInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(gCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}

