package com.example.dreamtravelv1.local_data_handle;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dreamtravelv1.R;

import java.util.List;

public class TravelAdapter extends ArrayAdapter<TravelItem> {
    public TravelAdapter(Context context, List<TravelItem> travelItems) {
        super(context, 0, travelItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_layout, parent, false);
        }

        TravelItem currentItem = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.tv_name);
        TextView addressTextView = convertView.findViewById(R.id.tv_address);
        TextView districtTextView = convertView.findViewById(R.id.tv_district);
        TextView psMcUcTextView = convertView.findViewById(R.id.tv_ps_mc_uc);

        nameTextView.setText(currentItem.getName());
        addressTextView.setText(currentItem.getAddress());
        districtTextView.setText(currentItem.getDistrict());
        psMcUcTextView.setText(currentItem.getPS_MC_UC());

        return convertView;
    }
}
