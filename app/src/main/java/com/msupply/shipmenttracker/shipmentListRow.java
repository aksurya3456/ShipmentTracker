package com.msupply.shipmenttracker;

/**
 * Created by abhishek on 9/10/17.
 */

public class shipmentListRow {

    private String shipmentId, pickUpDate, deliveryDate;

    public shipmentListRow(){}

    public shipmentListRow(String sId, String pDate, String dDate)
    {
        shipmentId = sId;
        pickUpDate = pDate;
        deliveryDate = dDate;
    }

    public void setShipmentId(String sId)
    {
        shipmentId = sId;
    }

    public String getShipmentId()
    {
        return shipmentId;
    }

    public void setPickUpDate(String pDate)
    {
        pickUpDate = pDate;
    }

    public String getPickUpDate()
    {
        return pickUpDate;
    }

    public void setDeliveryDate(String dDate)
    {
        deliveryDate = dDate;
    }

    public String getDeliveryDate()
    {
        return deliveryDate;
    }
}
