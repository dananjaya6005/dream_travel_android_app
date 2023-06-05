package com.example.dreamtravelv1.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dreamtravelv1.R;
import com.example.dreamtravelv1.local_data_handle.TravelAdapter;
import com.example.dreamtravelv1.local_data_handle.TravelItem;

import java.util.ArrayList;
import java.util.List;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class Travel_Agency extends Fragment {

    private List<TravelItem> travelItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_travel_agency, container, false);

        travelItems = parseJsonData();

        ListView listView = rootView.findViewById(R.id.list_view);
        TravelAdapter adapter = new TravelAdapter(requireContext(), travelItems);
        listView.setAdapter(adapter);

        return rootView;
    }

    @NonNull
    private List<TravelItem> parseJsonData() {
        String json = "[{\"AGADivision\":\"Dehiwala-Mount Lavinia\",\"Address\":\"NO. 572/10, 2ND FLOOR, MADIWELA ROAD, THALAWATHUGODA\",\"District\":\"Colombo\",\"Name\":\"EXOTIC TOURS (PVT) LTD\",\"PS/MC/UC\":\"Moratuwa Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.34, DR WIJEWARDHANA MAWATHA, COLOMBO 10\",\"District\":\"Colombo\",\"Name\":\"HAMOOS TRAVELS (PVT) LTD\",\"PS/MC/UC\":\"Maradana\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.20 DHARMARAMA ROAD, COLOMBO 06\",\"District\":\"Colombo\",\"Name\":\"WORLDLINK TRAVELS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO. 182, AIRLINK TOWERS, MAIN STREET, KATTANKUDY 02\",\"District\":\"Colombo\",\"Name\":\"THE TRAVEL MARKET (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.236, GALLE ROAD, COLOMBO 03\",\"District\":\"Colombo\",\"Name\":\"TANGERINE TOURS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.28, R.A.D. MEL MAWATHA, COLOMBO 04\",\"District\":\"Colombo\",\"Name\":\"SEIKOU LANKA TOURS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.345.1/1, KOTTE ROAD, RAJAGIRIYA\",\"District\":\"Colombo\",\"Name\":\"HANA TRAVELS & TOURS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO. 61, ALEXANDRA PLACE, COLOMBO 07\",\"District\":\"Colombo\",\"Name\":\"FINTRAVEL (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO 26.B, NUGEGODA ROAD, PEPLIYANA.\",\"District\":\"Colombo\",\"Name\":\"MAGICAL ISLE HOLIDAYS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Galle\",\"Address\":\"NO.4/B,2ND CROSS STREET,TALBOT TOWN,GALLE.\",\"District\":\"Galle\",\"Name\":\"TAPROBANE TRAVELS ( PVT ) LTD\",\"PS/MC/UC\":\"Galle Divisional Secretariat\"},{\"AGADivision\":\"Bentota Pradeshiya Sabha\",\"Address\":\"MIHIRIPENNA, WARAPITIYA, DHARGA TOWN\",\"District\":\"Galle\",\"Name\":\"CEYLON'S BEST TOURS (PVT) LTD\",\"PS/MC/UC\":\"Benthota Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"350, AMBAGAHA JUNCTION ROAD, GOTHATUWA\",\"District\":\"Colombo\",\"Name\":\"LANKA TRAVEL SERVICES\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.9, CAL.T.G. JAYAWARDENA MAWATHA, COLOMBO 03\",\"District\":\"Colombo\",\"Name\":\"GLOBAL CONNECTION TRAVELS & TOURS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.130/1/B,WATADDARA,VEYANGODA\",\"District\":\"Colombo\",\"Name\":\"REGIONAL DEVELOPMENT HOLDINGS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"},{\"AGADivision\":\"Gampaha\",\"Address\":\"NO.136/B, KANDY ROAD, KALALPITIYA, PASYALA\",\"District\":\"Gampaha\",\"Name\":\"ADITHYA LANKA HOLIDAYS (PVT) LTD\",\"PS/MC/UC\":\"Gampaha Divisional Secretariat\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.41, MARINE GRAND BANQUEST HALL, 2ND FLOOR, MARINE DRIVE, COLOMBO 06\",\"District\":\"Colombo\",\"Name\":\"GALLE TRAVLS & TOURS (PVT) LTD\",\"PS/MC/UC\":\"Colombo Divisional Secretariat\"}]";

        List<TravelItem> travelItems = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String agaDivision = jsonObject.optString("AGADivision");
                String address = jsonObject.optString("Address");
                String district = jsonObject.optString("District");
                String name = jsonObject.optString("Name");
                String psMcUc = jsonObject.optString("PS/MC/UC");

                TravelItem travelItem = new TravelItem(agaDivision, address, district, name, psMcUc);
                travelItems.add(travelItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return travelItems;
    }

}
