package com.testproject.propertyviewer.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by prachi on 25/12/16.
 */

public class PropertyListing {

    private String msg;

    private int status;

    @SerializedName("data")
    private ArrayList<Property> properties;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }

    public void setProperties(ArrayList<Property> properties) {
        this.properties = properties;
    }



}
