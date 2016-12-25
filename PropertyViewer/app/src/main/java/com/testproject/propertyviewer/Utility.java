package com.testproject.propertyviewer;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

    public static void loadMap(final Context context, String latitude, String longitude) {

       String urlToLoad = "http://maps.google.com/maps?q="+latitude +"," + longitude;
        Intent browserIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse(urlToLoad));
        context.startActivity(browserIntent);
    }

}
