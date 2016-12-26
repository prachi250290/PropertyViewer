package com.testproject.propertyviewer;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by prachi on 25/12/16.
 */

public class SharedPreferenceManager {

    private static SharedPreferenceManager instance;
    private Context managerContext;

    private SharedPreferenceManager(){

    }

    public static SharedPreferenceManager getInstance(Context context){

        if(instance == null){

            instance = new SharedPreferenceManager();
            instance.managerContext = context.getApplicationContext();

        }

        return instance;

    }

    public static void resetManager() {
        instance = null;
    }

    public void saveKeyValue(String key, String value){

        SharedPreferences.Editor editor = managerContext.getSharedPreferences(Constants.SHARED_PREFERENCE_USER_DETAILS, managerContext.MODE_PRIVATE).edit();
        editor.putString(key, value);
        editor.commit();

    }

    public String getValueForKey(String key){

        SharedPreferences prefs = managerContext.getSharedPreferences(Constants.SHARED_PREFERENCE_USER_DETAILS, managerContext.MODE_PRIVATE);
        String value = prefs.getString(key, "");
        return value;

    }

}
