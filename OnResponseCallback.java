package com.example.example;

public interface OnResponseCallback {
    public void onResponse(boolean status, String data);
}

/*

To callback and view the status and content of the response:

    APICaller.getTest(new OnResponseCallback(){
        @Override
        public void onResponse(boolean status, String data){
           // generate pop-up and show data, depending on the status
        }
    });

This should be contained in the same Activity that will display the pop-up.
 */
