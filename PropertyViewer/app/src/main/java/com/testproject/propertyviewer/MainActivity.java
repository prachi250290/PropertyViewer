package com.testproject.propertyviewer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseViews();
    }


    private void initialiseViews() {
        Button googleAccount = (Button) findViewById(R.id.login_btn_googleAccount);
        googleAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_btn_googleAccount:
                loginUsingGoogleAccount();
                break;
        }
    }

    private void loginUsingGoogleAccount() {
        Intent propertiesIntent = new Intent(this, PropertyListActivity.class);
        startActivity(propertiesIntent);
    }
}
