package com.example.denise.empower;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.AccessToken;
import com.facebook.login.widget.LoginButton;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    TextView txtStatus;
    LoginButton loginButton;
    CallbackManager callbackManager;
    Button btnAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_main);
        initializeControls();
        loginWithFacebook();
    }

    private void initializeControls() {
        callbackManager = CallbackManager.Factory.create();
        txtStatus = (TextView) findViewById(R.id.txtStatus);
        loginButton = (LoginButton) findViewById(R.id.login_button);
        btnAction = (Button) findViewById(R.id.btnAction);

        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {

                //My Contact Information
                //Mobile +91 9537299596
                //Email : satish98nada@hotmail.com (you can search on skype by this email)
                //You can contact me any time for android development help
                //LinkedIn : https://www.linkedin.com/in/satishnada/

               Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(myIntent);

          /*      if (AccessToken.getCurrentAccessToken() != null){
                    LoginManager.getInstance().logOut();
                }

                else{
                    LoginManager.getInstance().logInWithReadPermissions(
                            MainActivity.this,
                            Arrays.asList("public_profile","email","user_friends")
                    );
                }*/
            }
        });
    }

    private void loginWithFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                loginResult.getAccessToken();
                Intent myIntent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(myIntent);
            }

            @Override
            public void onCancel() {
                txtStatus.setText("Login Cancelled\n");
            }

            @Override
            public void onError(FacebookException error) {
                txtStatus.setText("Login Error: " + error.getMessage());
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
}
