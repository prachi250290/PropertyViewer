package com.testproject.propertyviewer;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

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

       // Utility.showProgressDialog(this, getString(R.string.fetching_properties_msg));

        Bundle bundle = getIntent().getExtras();
        fetchProperties(bundle.getString(Constants.INTENT_KEY_PLACE), bundle.getString(Constants.INTENT_KEY_BUDGET));
    }


    private void initializeViews() {

        recyclerView = (RecyclerView) findViewById(R.id.property_list_view);

        propertyListAdapter = new PropertyListAdapter(propertyList, this);
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(propertyListAdapter);
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                openPropertyDetailScreen(propertyList.get(position));
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
    }

    private void openPropertyDetailScreen(Property property) {
        Intent propertyDetailIntent = new Intent(PropertyListActivity.this, PropertyDetailActivity.class);
        propertyDetailIntent.putExtra(Constants.INTENT_KEY_PROPERTY_NAME, property.getName());
        propertyDetailIntent.putExtra(Constants.INTENT_KEY_PROPERTY_DESCRIPTION, property.getDescription());
        propertyDetailIntent.putExtra(Constants.INTENT_KEY_PROPERTY_DEVELOPER_NAME, property.getDeveloperName());
        propertyDetailIntent.putExtra(Constants.INTENT_KEY_PROPERTY_IMAGE, property.getImage());
        propertyDetailIntent.putExtra(Constants.INTENT_KEY_PROPERTY_DEVELOPER_IMAGE, property.getDeveloperImage());
        startActivity(propertyDetailIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }


    private void fetchProperties(String place, String budget) {


        ApiInterface apiService =
                ApiClient.getClient(getToken()).create(ApiInterface.class);


        Map<String, String> queryMap = new HashMap<String, String>();
        queryMap.put(Constants.QUERY_PARAM_AREA_NAME, place);
        queryMap.put(Constants.QUERY_PARAM_POSITION, Constants.QUERY_PARAM_POSITION_VALUE);
        queryMap.put(Constants.QUERY_PARAM_BUDGET, budget);

        Call<String> call = apiService.getProperties(queryMap);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                //Utility.hideProgressDialog();
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
                        Toast.makeText(PropertyListActivity.this, "No Properties found", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                //Utility.hideProgressDialog();
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void openPropertyDescriptionScreen() {

    }

    private HashMap<String, String> getToken() {
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Constants.HEADER_TOKEN_ID, SharedPreferenceManager.getInstance(this).getValueForKey(Constants.SHARED_PREFERENCE_TOKEN));
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


    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private PropertyListActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final PropertyListActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }

    public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

}
