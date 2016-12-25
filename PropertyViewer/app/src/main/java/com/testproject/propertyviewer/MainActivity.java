package com.testproject.propertyviewer;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.testproject.propertyviewer.Model.LoginResult;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private GoogleApiClient mGoogleApiClient;

    private final int RC_SIGN_IN = 0;

    private static final String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseViews();

        configureGoogleClient();
    }


    private void initialiseViews() {
        SignInButton googleAccount = (SignInButton) findViewById(R.id.login_btn_googleAccount);
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Utility.showProgressDialog(this, "Logging in...");
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            loginUsingGoogleId(acct.getEmail(), acct.getId());
        } else {
            // Signed out, show unauthenticated UI.
        }
    }

    private void loginUsingGoogleAccount() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    private void loginUsingGoogleId(String emailId, String googleId) {
        ApiInterface apiService =
                ApiClient.getClientGSON(new HashMap<String, String>()).create(ApiInterface.class);


        Map<String, String> params = new HashMap<String, String>();
        params.put(Constants.PARAM_EMAIL_ADDRESS, emailId);
        params.put(Constants.PARAM_GOOGLE_ID, googleId);
        Call<LoginResult> call = apiService.login(params);
        call.enqueue(new Callback<LoginResult>() {
            @Override
            public void onResponse(Call<LoginResult> call, Response<LoginResult> response) {
                Utility.hideProgressDialog();
                if (response != null) {
                    if (response.isSuccessful()) {

                        LoginResult loginResult = response.body();

                        //Save token
                        SharedPreferenceManager.getInstance(MainActivity.this).saveKeyValue(Constants.SHARED_PREFERENCE_TOKEN, loginResult.getSession().getTokenId());
                        openSearchOptionsScreen();
                        finish();


                    } else {
                        //Handle failure
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResult> call, Throwable t) {
                Utility.hideProgressDialog();
                // Log error here since request failed
                Log.e(TAG, t.toString());
            }
        });
    }

    private void configureGoogleClient() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this, new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

                    }
                })
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
    }

    private void openSearchOptionsScreen () {
        Intent searchOptionsIntent = new Intent(this, SearchOptionsActivity.class);
        startActivity(searchOptionsIntent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if(!SharedPreferenceManager.getInstance(this).getValueForKey(Constants.SHARED_PREFERENCE_TOKEN).equals("")) {
            openSearchOptionsScreen();
            finish();
        }
    }
}
