package com.msupply.shipmenttracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        response = (TextView) findViewById(R.id.response);
        response.setText("");
    }


    public void onClick(View v)
    {
        EditText editText =(EditText) findViewById(R.id.phone);
        String mobile = editText.getText().toString();
        String otpResponse = MainActivityImplementer.generateOTP(this,mobile);
        response.setText(otpResponse);

        /*if(otpGenerated) {
            response.setText("OTP Generated");
        }
        else
        {
            response.setText("Please recheck the mobile number and press continue");
        }*/
    }
}
