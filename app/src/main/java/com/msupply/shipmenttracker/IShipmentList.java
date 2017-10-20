package com.msupply.shipmenttracker;

import org.json.JSONArray;

/**
 * Created by abhishek on 17/10/17.
 */

public interface IShipmentList {

    public void prepareListData(JSONArray params);

    public void onSessionTimedOut();

    public void errorListener();

    public void buttonClickListener(String shipmentID);
}
