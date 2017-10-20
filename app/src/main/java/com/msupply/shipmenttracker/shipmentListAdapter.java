package com.msupply.shipmenttracker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

/**
 * Created by abhishek on 9/10/17.
 */

public class shipmentListAdapter extends RecyclerView.Adapter<shipmentListAdapter.myViewHolder> {
    public IShipmentList mIShipmentList;
    private List<shipmentListRow> shipmentListRowList;

    public shipmentListAdapter(List<shipmentListRow> listEntity, IShipmentList shipmentList) {
        shipmentListRowList = listEntity;
        mIShipmentList = shipmentList;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.shipment_list_row, parent, false);
        return new myViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        final shipmentListRow mShipmentListRow = shipmentListRowList.get(position);
        holder.shipmentId.setText(mShipmentListRow.getShipmentId());
        holder.pickUpDate.setText(mShipmentListRow.getPickUpDate());
        holder.deliveryDate.setText(mShipmentListRow.getDeliveryDate());

        holder.shipment_row_linear_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mIShipmentList.buttonClickListener(mShipmentListRow.getShipmentId());
            }
        });


    }

    @Override
    public int getItemCount() {
        return shipmentListRowList.size();
    }

    public void appendList(Map<String, String> params) {

    }


    public class myViewHolder extends RecyclerView.ViewHolder {
        public TextView shipmentId, pickUpDate, deliveryDate;

        public LinearLayout shipment_row_linear_layout;

        public myViewHolder(View view) {
            super(view);
            shipmentId = (TextView) view.findViewById(R.id.shipment_id);
            pickUpDate = (TextView) view.findViewById(R.id.pickUpDate);
            deliveryDate = (TextView) view.findViewById(R.id.deliveryDate);

            shipment_row_linear_layout = (LinearLayout) view.findViewById(R.id.shipment_row_linear_layout);
        }

    }


}
