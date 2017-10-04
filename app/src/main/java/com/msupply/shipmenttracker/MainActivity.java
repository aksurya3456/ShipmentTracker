package com.msupply.shipmenttracker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IMainActivityView {

    private TextView response;
    private final String TAG = this.getClass().getSimpleName();
    private MainActivityImplementer mMainActivityImplementer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response = (TextView) findViewById(R.id.response);
        response.setText("");
    }


    public void onClick(View v) {
        EditText editText = (EditText) findViewById(R.id.phone);
        String mobile = editText.getText().toString();
        if(validateMobileNumber(mobile)) {
            mMainActivityImplementer = new MainActivityImplementer(this, this, mobile);
        }
    }

    @Override
    public void displayReturnedValue(int statusCode) {
        //response.setText(String.valueOf(statusCode));
        switch (statusCode){
            case 200:
                response.setText("OTP Generated Successfully");
                break;
            case 400:
                response.setText("Please enter proper mobile number");
                break;
            case 404:
                response.setText("There are no shipments pending for you");

        }
    }

    @Override
    public void displayError(String error) {
        response.setText(String.valueOf("Error occured: " + error));
    }

    @Override
    public boolean validateMobileNumber(String mobile) {
        String mobilePattern = "[6-9]{1}[0-9]{9}";
        if(mobile.matches(mobilePattern))
        {
            //Toast.makeText(this, "Mobile Number pattern valid", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
