package com.msupply.shipmenttracker;

import android.content.Intent;
import android.graphics.Color;
import android.os.TestLooperManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OTPValidation extends AppCompatActivity implements IOTPValidation {

    TextView info, response, resendOTP;
    String mobile;
    String otpString;
    Intent intent;
    EditText otp;
    Button continueButton;
    private OTPValidationImplementer mOtpValidationImplementer;
    Intent shipmentListIntent;
    SessionManager sessionManager ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpvalidation);
        intent = getIntent();
        mobile = intent.getStringExtra(constants.MOBILE_PASS);
        info = (TextView) findViewById(R.id.info);
        response = (TextView) findViewById(R.id.response);
        response.setText("");
        otp = (EditText) findViewById(R.id.otp);
        resendOTP = (TextView) findViewById(R.id.resendOTP);
        continueButton = (Button) findViewById(R.id.continueButton);
        continueButton.setEnabled(false);
        shipmentListIntent = new Intent(this,ShipmentList.class);
        sessionManager = new SessionManager(getApplicationContext());

        info.setText("Please enter OTP sent to the mobile number " + mobile);
        otp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (otp.getText().toString().length() == 6) {
                    continueButton.setEnabled(true);
                } else {
                    continueButton.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    public void onClick(View v) {
        //Toast.makeText(this,"OTP Button Clicked",Toast.LENGTH_LONG).show();
        otpString = otp.getText().toString();
        mOtpValidationImplementer = new OTPValidationImplementer(this, this, mobile, otpString);
    }

    @Override
    public void OTPResponse(int status) {
        switch (status) {
            case 200:
                sessionManager.setLogin(mobile);
                shipmentListIntent.putExtra(constants.MOBILE_PASS,mobile);
                startActivity(shipmentListIntent);
                response.setText("OTP Verified");
                resendOTP.setVisibility(View.GONE);
                continueButton.setVisibility(View.GONE);
                otp.setVisibility(View.GONE);
                break;
            case 400:
                response.setText("Mobile Number or OTP is missing");
                break;
            case 404:
                response.setText("Incorrect OTP");
                break;
            default:
                response.setText("Unhandled Error");
        }

    }


    public void resendOTPResponse(int status) {
        switch (status) {
            case 200:
                response.setText("OTP has been resent to your mobile");
                break;
            case 400:
                response.setText("Please enter proper mobile number.");
                break;
            case 404:
                response.setText("There are no shipments waiting for you.");
                break;
            default:
                response.setText("Unhandled Error");
        }

    }

    @Override
    public void displayError(String error) {
        response.setText(String.valueOf("Error occured: " + error));
    }

    public void resendOTP(View v) {
        otp.setText("");
        //Toast.makeText(this, "Resend OTP Clicked", Toast.LENGTH_LONG).show();
        mOtpValidationImplementer = new OTPValidationImplementer(this, this, mobile);
    }


}
