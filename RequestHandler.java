package com.example.example; // change this to the new of the package that you put this class into

import com.loopj.android.http.*;

public class RequestHandler {

    // enter your base URL here
    private static final String URL = "";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public static void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    // url is just a partial url that will be wrapped with your base url, creating the absolute path to the API
    private static String getAbsoluteUrl(String relativeUrl) {
        return URL + relativeUrl;
    }
}

