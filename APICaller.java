package com.example.example; // change this to the new of the package that you put this class into

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class APICaller {

    /*
    Handle post and get requests by generating a different url for each of them, using a code and the token of the user.
    Remember to edit the parameters for post requests and the name of the fields in the response.
    getTest() and postTest() are just examples, you can create as many functions as you like following this same exact structure,
    just remember to generate the correct url.
    This class uses RequestHandler, which you will find in this directory, and com.loopj.android.http.*, which you will need to import.
     */

    public void getTest(OnResponseCallback callback) {
        String code = "1";
        String url = generateURL(code); // generates relative url
        handleResponse(url, callback);
    }

    public void postTest(OnResponseCallback callback){
        String code = "2";
        String url = generateURL(code);
        RequestParams params = new RequestParams();
        params.put("test_parameter_1", "test_parameter_2"); // parameters of the POST request
        handlePost(url, params, callback);
    }

    private String generateURL(String code) {
        // The token is not mandatory. If you don't use Firebase just avoid this.
        String url = code + "/" + FirebaseAuth.getInstance().getAccessToken(true);
        // RequestHandler will then generate the absolute url using getAbsoluteUrl()
        return url;
    }


    private void handleResponse(String url, final OnResponseCallback callback) {
        /*
        Logs the response type and its content. In the case  of a get request the parameters will be null.
         */
        RequestHandler.get(url,
                null,
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {
                        // if it's a JSONArray make sure you take the data at the correct position
                        JSONObject json = null;
                        String status = "";
                        String data = "";
                        try {
                            json = (JSONObject) response.get(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            status = json.getString("status");
                            data = json.getString("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("ResponseType", "JSONArray");
                        Log.d("ResponseStatus", status);
                        Log.d("ResponseData", data);

                        // this template could be used to generate some interactions based on the response
                        if (status == "OK") {
                            callback.onResponse(true, data);
                        } else {
                            callback.onResponse(false, data);
                        }

                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {
                        // in the case of a JSONObject we can directly take the elements
                        String status = "";
                        String data = "";
                        try {
                            status = response.getString("status");
                            data = response.getString("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("ResponseType", "JSONObject");
                        Log.d("ResponseStatus", status);
                        Log.d("ResponseData", data);

                        if (status == "OK") {
                            callback.onResponse(true, data);
                        } else {
                            callback.onResponse(false, data);
                        }

                    }

                }
        );
    }

    public void handlePost(String url, RequestParams params, final OnResponseCallback callback){
        /*
        Logs the response type and its content. Parameters must be included in this case.
         */
        RequestHandler.post(url,
                params,
                new JsonHttpResponseHandler() {

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONArray response) {

                        JSONObject json = null;
                        String status = "";
                        String data = "";
                        try {
                            json = (JSONObject) response.get(0);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        try {
                            status = json.getString("status");
                            data = json.getString("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("ResponseType", "JSONArray");
                        Log.d("ResponseStatus", status);
                        Log.d("ResponseData", data);


                        if (status == "OK") {
                            callback.onResponse(true, data);
                        } else {
                            callback.onResponse(false, data);
                        }

                    }

                    @Override
                    public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, JSONObject response) {

                        String status = "";
                        String data = "";
                        try {
                            status = response.getString("status");
                            data = response.getString("data");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Log.d("ResponseType", "JSONObject");
                        Log.d("ResponseStatus", status);
                        Log.d("ResponseData", data);

                        if (status == "OK") {
                            callback.onResponse(true, data);
                        } else {
                            callback.onResponse(false, data);
                        }

                    }

                }
        );
    }
}