package com.msupply.shipmenttracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class ShipmentList extends AppCompatActivity {

    private List<shipmentListRow> shipmentRowList = new ArrayList<>();
    private RecyclerView recyclerView;
    private shipmentListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipment_list);

       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/

        recyclerView = (RecyclerView) findViewById(R.id.rv_shipment);
        mAdapter = new shipmentListAdapter(shipmentRowList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareListData();


    }

    private void prepareListData()
    {
        shipmentListRow mShipmentListRow = new shipmentListRow("id 1", "Date 11", "Date 12");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 2", "Date 21", "Date 22");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 2", "Date 21", "Date 22");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 2", "Date 21", "Date 22");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);

        mShipmentListRow = new shipmentListRow("id 3", "Date 31", "Date 32");
        shipmentRowList.add(mShipmentListRow);



        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void  onBackPressed(){
        finish();
    }
}
