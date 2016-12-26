package com.testproject.propertyviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SearchOptionsActivity extends Activity {

    private EditText placeEditText, budgetEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);

        initializeViews();
    }

    private void initializeViews() {
        placeEditText = (EditText) findViewById(R.id.search_place_edittext);
        budgetEditText = (EditText) findViewById(R.id.search_budget_edittext);
        searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchPlaces();
            }
        });
    }

    private void searchPlaces() {
        Intent propertyListIntent = new Intent(this, PropertyListActivity.class);
        propertyListIntent.putExtra(Constants.INTENT_KEY_PLACE,placeEditText.getText().toString());
        propertyListIntent.putExtra(Constants.INTENT_KEY_BUDGET,budgetEditText.getText().toString());
        startActivity(propertyListIntent);
    }
}
