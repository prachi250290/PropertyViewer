package com.testproject.propertyviewer;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by prachi on 25/12/16.
 */
public class ApiClient {

    public static final String BASE_URL = "http://api.hdfcred.net";
    private static Retrofit retrofit = null;


    public static Retrofit getClient(final HashMap<String, String> headers) {


        /// Validate Before Using
        if (headers != null && headers != null && headers.keySet().size() > 0) {
            headers.putAll(headers);
        }

        // Interceptor to pass Header Values
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request.Builder builder = chain.request().newBuilder();

                // Add Headers
                for (String key : headers.keySet()) {

                    String headerParam = headers.get(key);
                    builder.addHeader(key, headerParam);

                }

                Request request = builder.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

    }


    public static Retrofit getClientGSON(final HashMap<String, String> headers) {


        /// Validate Before Using
        if (headers != null && headers != null && headers.keySet().size() > 0) {
            headers.putAll(headers);
        }

        // Interceptor to pass Header Values
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {

                Request.Builder builder = chain.request().newBuilder();

                // Add Headers
                for (String key : headers.keySet()) {

                    String headerParam = headers.get(key);
                    builder.addHeader(key, headerParam);

                }

                Request request = builder.build();
                return chain.proceed(request);
            }
        };

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(headerInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

}
