package com.msupply.shipmenttracker;

/**
 * Created by abhishek on 4/10/17.
 */

public final class constants {
    public static final String MOBILE_PASS = "MOBILE_PASS";
    public static String baseUrl = "https://aksurya3456.000webhostapp.com/API/";
    public static String requestOTPurl = baseUrl + "requestOTP.php";
    static String validateOTPurl = baseUrl + "validateOTP.php";
    static String resendOTPurl = baseUrl + "requestOTP.php";
    static String getShipmentListUrl = baseUrl + "shipmentList.php";
}
