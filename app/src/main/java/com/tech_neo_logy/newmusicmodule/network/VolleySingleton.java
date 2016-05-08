package com.tech_neo_logy.newmusicmodule.network;

import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.tech_neo_logy.newmusicmodule.MyApplication;
import com.tech_neo_logy.newmusicmodule.utils.LruBitmapCache;

/**
 * Created by Prafull on 31-Mar-16.
 */
public class VolleySingleton {
    private static VolleySingleton volleySingletonInstance = null;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;


    public static final String TAG = VolleySingleton.class
            .getSimpleName();

    private VolleySingleton(){
        requestQueue = Volley.newRequestQueue(MyApplication.getAppcontext());
    }

    public static VolleySingleton getVolleyInstance(){
        if(volleySingletonInstance == null)
            volleySingletonInstance = new VolleySingleton();
        return volleySingletonInstance;
    }
    public RequestQueue getRequestQueue(){
        return requestQueue;
    }

    public ImageLoader getImageLoader() {
        getRequestQueue();
        if (imageLoader == null) {
            imageLoader = new ImageLoader(this.requestQueue,
                    new LruBitmapCache());
        }
        return this.imageLoader;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }


}
