package com.msupply.shipmenttracker;

/**
 * Created by abhishek on 5/10/17.
 */

public interface IOTPValidation {
     void OTPResponse(int status);
     void displayError(String error);
}
