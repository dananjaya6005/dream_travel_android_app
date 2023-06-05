package com.example.dreamtravelv1.local_data_handle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.dreamtravelv1.R;

import java.util.List;

public class RestaurantAdapter extends ArrayAdapter<RestaurantItem> {

    public RestaurantAdapter(Context context, List<RestaurantItem> restaurantItems) {
        super(context, 0, restaurantItems);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_restaurant, parent, false);
        }

        RestaurantItem currentItem = getItem(position);

        TextView nameTextView = convertView.findViewById(R.id.tv_name);
        TextView addressTextView = convertView.findViewById(R.id.tv_address);
        TextView districtTextView = convertView.findViewById(R.id.tv_district);
        TextView agadivision = convertView.findViewById(R.id.tv_agadivision);
        TextView gradeTextView = convertView.findViewById(R.id.tv_grade);

        nameTextView.setText(currentItem.getName());
        addressTextView.setText(currentItem.getAddress());
        districtTextView.setText(currentItem.getDistrict());
        gradeTextView.setText(currentItem.getGrade());
        agadivision.setText(currentItem.getAGADivision());

        return convertView;
    }
}
