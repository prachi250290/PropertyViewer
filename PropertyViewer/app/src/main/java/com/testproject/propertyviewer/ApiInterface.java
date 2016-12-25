package com.testproject.propertyviewer;

import com.testproject.propertyviewer.Model.LoginResult;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by prachi on 25/12/16.
 */
public interface ApiInterface {

    @GET("/mobile_v3/project_listing/")
    Call<String> getProperties(@QueryMap Map<String, String> options);

    @POST("/mobile_v3/login/")
    Call<LoginResult> login(@Body  Map<String, String> body);
}
