package com.example.rafi715.rimeapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rafi715.rimeapplication.Calling.BaseActivity;
import com.example.rafi715.rimeapplication.Calling.PlaceCallActivity;
import com.example.rafi715.rimeapplication.Calling.SinchService;
import com.sinch.android.rtc.SinchError;

public class Login extends BaseActivity implements SinchService.StartFailedListener {

    private Button mLoginButton;
    private EditText mLoginName;
    private EditText mLoginPhone;
    private ProgressDialog mSpinner;
    CheckBox saveLoginCheckBox;

    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;

    private boolean saveLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mLoginName = (EditText) findViewById(R.id.userName);
        mLoginPhone = (EditText) findViewById(R.id.contactNumber);

        mLoginButton = (Button) findViewById(R.id.loginSubmit);
        mLoginButton.setEnabled(false);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginClicked();
            }
        });

        saveLoginCheckBox = (CheckBox) findViewById(R.id.checkBoxLogin);

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();


        saveLogin = loginPreferences.getBoolean("saveLogin", false);

        if (saveLogin == true) {
            mLoginName.setText(loginPreferences.getString("contactName", ""));
            mLoginPhone.setText(loginPreferences.getString("contactPhone", ""));

            saveLoginCheckBox.setChecked(true);
        }
    }

    @Override
    protected void onServiceConnected() {
        mLoginButton.setEnabled(true);
        getSinchServiceInterface().setStartListener(this);
    }

    @Override
    protected void onPause() {
        if (mSpinner != null) {
            mSpinner.dismiss();
        }
        super.onPause();
    }

    //Function to display error message if Sinch fails to connect
    @Override
    public void onStartFailed(SinchError error) {
        String errMsgType = error.toString().substring(21, 28);

        switch (errMsgType) {
            case "NETWORK":
                Toast.makeText(this, "You are not connected to Internet.", Toast.LENGTH_SHORT).show();
                break;
             default: Toast.makeText(this, errMsgType, Toast.LENGTH_SHORT).show();
                 break;

        }


        if (mSpinner != null) {
            mSpinner.dismiss();
        }
    }

    @Override
    public void onStarted() {
        openMainActivity();
    }

    private void loginClicked() {
        String userName = mLoginName.getText().toString();
        String userPhone = mLoginPhone.getText().toString();


        if (userName.isEmpty() && userPhone.isEmpty() ) {
            Toast.makeText(this, "Please enter your Name and Phone number", Toast.LENGTH_SHORT).show();
            return;
        }   //if sinch is already not running, then start sinch with userName
        else if (!getSinchServiceInterface().isStarted()) {
            getSinchServiceInterface().startClient(userName);
            showSpinner();
        } else {
            //saving login detail, so that user doesn't have to enter his detail every time
            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("contactName", userName);
                loginPrefsEditor.putString("contactPhone", userPhone);

                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }

            mLoginName.setText("");
            mLoginPhone.setText("");

            //login
            openMainActivity();
        }
    }

    private void openMainActivity() {
        Intent mainActivity = new Intent(this, MainPage.class);
        startActivity(mainActivity);
    }

    private void showSpinner() {
        mSpinner = new ProgressDialog(this);
        mSpinner.setTitle("Logging in");
        mSpinner.setMessage("Please wait...");
        mSpinner.show();
    }

}
