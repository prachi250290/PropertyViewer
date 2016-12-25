package com.testproject.propertyviewer;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;

import com.google.gson.Gson;
import com.testproject.propertyviewer.Model.Property;
import com.testproject.propertyviewer.Model.PropertyListing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PropertyListActivity extends Activity {

    private RecyclerView recyclerView;
    private PropertyListAdapter propertyListAdapter;
    private List<Property> propertyList = new ArrayList<>();

    LinearLayoutManager mLayoutManager;

    private static final String TAG = PropertyListActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_list);

        initializeViews();

        Utility.showProgressDialog(this, getString(R.string.fetching_properties_msg));

        fetchProperties();
    }


    private void initializeViews() {

        recyclerView = (RecyclerView) findViewById(R.id.property_list_view);

        propertyListAdapter = new PropertyListAdapter(propertyList, this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(propertyListAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                openPropertyDescriptionScreen();
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });
    }


    private void fetchProperties() {


        ApiInterface apiService =
                ApiClient.getClient(getToken()).create(ApiInterface.class);


        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(Constants.QUERY_PARAM_AREA_NAME, "Chennai");
        queryMap.put(Constants.QUERY_PARAM_POSITION, Constants.QUERY_PARAM_POSITION_VALUE);
        queryMap.put(Constants.QUERY_PARAM_BUDGET, "10000000");

        Call<String> call = apiService.getProperties(queryMap);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Utility.hideProgressDialog();
                if (response != null) {
                    if (response.isSuccessful()) {
                        PropertyListing propertyListing = parseResponseString(response.body());

                        if(propertyListing!= null && propertyListing.getProperties()!= null && propertyListing.getProperties().size() >0) {
                            List<Property> properties = propertyListing.getProperties();

                            propertyList.addAll(properties);
                            propertyListAdapter.setPropertyList(propertyList);
                            propertyListAdapter.notifyDataSetChanged();

                        }


                    } else {
                        //Handle failure
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Utility.hideProgressDialog();
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void openPropertyDescriptionScreen() {

    }

    private HashMap<String, String> getToken() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Constants.HEADER_TOKEN_ID, "1734044778585e86b27901e");
        return headers;
    }

    private PropertyListing parseResponseString(String response) {

        Gson gson = new Gson();

        String responseString = response;
        int lastOpeningCharacter = responseString.lastIndexOf("{");
        responseString = responseString.substring(0, lastOpeningCharacter);

        try {
            return gson.fromJson(responseString, PropertyListing.class);

        } catch (Throwable t) {
            Log.e(TAG, "Could not parse malformed JSON: \"" + responseString + "\"");
        }

        return null;

    }

}
