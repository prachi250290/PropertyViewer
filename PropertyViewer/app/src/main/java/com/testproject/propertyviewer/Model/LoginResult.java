package com.testproject.propertyviewer.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prachi on 25/12/16.
 */

public class LoginResult {

    private String msg;

    private int status;

    @SerializedName("data")
    private Session session;

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

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

}
