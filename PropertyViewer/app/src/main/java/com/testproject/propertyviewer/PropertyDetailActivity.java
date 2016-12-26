package com.testproject.propertyviewer;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PropertyDetailActivity extends Activity {

    private ImageView propertyImage, developerImage;
    private TextView name, description, developerName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        initializeViews();

        setFonts();

        setValues();
    }

    private void initializeViews() {
        propertyImage = (ImageView) findViewById(R.id.property_detail_image);
        developerImage = (ImageView) findViewById(R.id.property_detail_developer_imageview);
        name = (TextView) findViewById(R.id.property_detail_name_textview);
        description = (TextView) findViewById(R.id.property_detail_description_textview);
        developerName = (TextView) findViewById(R.id.property_detail_devname_textview);
    }

    private void setFonts() {
        //Apply Fonts
        Fonts.getSharedFontsManager().setFont(this, name, Fonts.BANDY_REGULAR);
        Fonts.getSharedFontsManager().setFont(this, description, Fonts.BANDY_REGULAR);
        Fonts.getSharedFontsManager().setFont(this, developerName, Fonts.BANDY_REGULAR);

    }

    private void setValues() {
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            name.setText(extras.getString(Constants.INTENT_KEY_PROPERTY_NAME));
            description.setText(extras.getString(Constants.INTENT_KEY_PROPERTY_DESCRIPTION));
            developerName.setText(extras.getString(Constants.INTENT_KEY_PROPERTY_DEVELOPER_NAME));

            Picasso.with(this).load(extras.getString(Constants.INTENT_KEY_PROPERTY_IMAGE)).into(propertyImage);
            Picasso.with(this).load(extras.getString(Constants.INTENT_KEY_PROPERTY_DEVELOPER_IMAGE)).into(developerImage);

        }
    }


}
