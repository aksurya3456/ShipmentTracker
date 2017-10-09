package com.msupply.shipmenttracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by abhishek on 9/10/17.
 */

public class shipmentListAdapter extends RecyclerView.Adapter<shipmentListAdapter.myViewHolder> {
    private List<shipmentListRow> shipmentListRowList;

    public shipmentListAdapter(List<shipmentListRow> listEntity)
    {
        shipmentListRowList = listEntity;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shipment_list_row,parent,false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        shipmentListRow mShipmentListRow = shipmentListRowList.get(position);
        holder.shipmentId.setText(mShipmentListRow.getShipmentId());
        holder.pickUpDate.setText(mShipmentListRow.getPickUpDate());
        holder.deliveryDate.setText(mShipmentListRow.getDeliveryDate());

    }

    @Override
    public int getItemCount() {
        return shipmentListRowList.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder
    {
        public TextView shipmentId, pickUpDate, deliveryDate;

        public myViewHolder(View view)
        {
            super(view);
            shipmentId = (TextView) view.findViewById(R.id.shipment_id);
            pickUpDate = (TextView) view.findViewById(R.id.pickUpDate);
            deliveryDate = (TextView) view.findViewById(R.id.deliveryDate);
        }

    }


}
