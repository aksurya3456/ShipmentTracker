package com.msupply.shipmenttracker;

/**
 * Created by abhishek on 4/10/17.
 */

public interface IMainActivityView {

    void displayReturnedValue(int statusCode);
    void displayError(String error);
    boolean validateMobileNumber(String mobile);
}
