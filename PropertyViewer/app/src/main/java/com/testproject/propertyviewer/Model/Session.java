package com.testproject.propertyviewer.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by prachi on 25/12/16.
 */

public class Session {

    @SerializedName("token_id")
    private String tokenId;

    @SerializedName("email_address")
    private String emailAddress;

    private String name;

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
