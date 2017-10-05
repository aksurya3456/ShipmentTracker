package com.msupply.shipmenttracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements IMainActivityView {


    private TextView response;
    private final String TAG = this.getClass().getSimpleName();
    private MainActivityImplementer mMainActivityImplementer;
    EditText editText;
    Button button;
    String mobile;
    Intent otpValidationIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response = (TextView) findViewById(R.id.response);
        response.setText("");
        button = (Button) findViewById(R.id.continueButton);
        button.setEnabled(false);
        otpValidationIntent = new Intent(this, OTPValidation.class);

        editText = (EditText) findViewById(R.id.phone);
        editText.addTextChangedListener(
                new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        editText.setTextColor(Color.BLACK);
                        mobile = editText.getText().toString();
                        if (validateMobileNumber(mobile)) {
                            button.setEnabled(true);
                        } else {
                            button.setEnabled(false);
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }
                }
        );

    }


    public void onClick(View v) {

        mobile = editText.getText().toString();
        if (validateMobileNumber(mobile)) {
            mMainActivityImplementer = new MainActivityImplementer(this, this, mobile);
        }
    }

    @Override
    public void displayReturnedValue(int statusCode) {
        //response.setText(String.valueOf(statusCode));
        switch (statusCode) {
            case 200:


                otpValidationIntent.putExtra(constants.MOBILE_PASS, mobile);
                startActivity(otpValidationIntent);
                response.setText("OTP Generated Successfully.");
                break;
            case 400:
                response.setText("Please enter proper mobile number.");
                break;
            case 404:
                response.setText("There are no shipments waiting for you.");

        }
    }

    @Override
    public void displayError(String error) {
        response.setText(String.valueOf("Error occured: " + error));
    }

    @Override
    public boolean validateMobileNumber(String mobile) {
        String mobilePattern = "[6-9]{1}[0-9]{9}";
        if (mobile.matches(mobilePattern)) {
            //Toast.makeText(this, "Mobile Number pattern valid", Toast.LENGTH_SHORT).show();
            return true;
        } else if (mobile.matches("[0-5]{1}[0-9]{9}")) {
            Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
            editText.setTextColor(Color.RED);
            return false;
        } else {
            //Toast.makeText(this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
