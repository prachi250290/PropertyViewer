package com.testproject.propertyviewer;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by prachi on 25/12/16.
 */
public class Utility {

    private static ProgressDialog progressDialog;

    public static void showProgressDialog(Context context, String Message) {

        showProgressDialog(context, Message, false);
    }

    public static void showProgressDialog(Context context, String Message,
                                          boolean cancelable) {
        if (progressDialog == null) {
            progressDialog = new ProgressDialog(context);
        }
        progressDialog.setMessage(Message);
        progressDialog.setCancelable(cancelable);
        progressDialog.show();
    }

    public static void hideProgressDialog() {
        if (progressDialog.isShowing()) {
            progressDialog.cancel();
        }
    }

}
