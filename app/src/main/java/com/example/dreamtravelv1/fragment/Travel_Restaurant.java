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
import com.example.dreamtravelv1.local_data_handle.RestaurantAdapter;
import com.example.dreamtravelv1.local_data_handle.RestaurantItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class Travel_Restaurant extends Fragment {

    private List<RestaurantItem> restaurantItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_travel_restaurant, container, false);

        restaurantItems = parseJsonData();

        ListView listView = rootView.findViewById(R.id.list_view);
        RestaurantAdapter adapter = new RestaurantAdapter(requireContext(), restaurantItems);
        listView.setAdapter(adapter);

        return rootView;
    }

    @NonNull
    private List<RestaurantItem> parseJsonData() {
        String json = "[{\"AGADivision\":\"Colombo\",\"Address\":\"NO. 11, GALLE FACE COURT 02 COLOMBO\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"BAVARIAN BARN RESTAURANT\"},{\"AGADivision\":\"Negombo\",\"Address\":\"80B PORUTHOTA ROAD ETTHUKALA NEGOMBO\",\"District\":\"Gampaha\",\"Grade\":\"A\",\"Name\":\"LORDS RESTAURANT\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO 11, GALLE FACE TERRACE, COLOMBO 03\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"NIHONBASHI RESTAURANT\"},{\"AGADivision\":\"Uppuveli Trinco Malee\",\"Address\":\"NO 1285, KANDY ROAD, PALAIYOOTHU, TRINCO MALEE\",\"District\":\"Trincomalee\",\"Grade\":\"A\",\"Name\":\"FREE WIND RESTAURANT & BAR\"},{\"AGADivision\":\"Negombo\",\"Address\":\"204, LEWIS PLACE, NEGOMBO\",\"District\":\"Gampaha\",\"Grade\":\"B\",\"Name\":\"EDWIN RESTAURANT\"},{\"AGADivision\":\"Peliyagoda\",\"Address\":\"154/A, KANDY RD, DALUGAMA,KELANIYA\",\"District\":\"Gampaha\",\"Grade\":\"B\",\"Name\":\"DAKSHINA RESTAURANT\"},{\"AGADivision\":\"Maharagama\",\"Address\":\"278/2, HIGHLEVEL RD, MAHALWARAWA, PANNIPITIYA\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"KUMU KUMA CHINESE RESTAURANT\"},{\"AGADivision\":\"Colombo\",\"Address\":\"192 1/1, BODHIRAJA MAWATHA, COLOMBO 11\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"HANSAGIRI RESTAURANT\"},{\"AGADivision\":\"Ja-Ela\",\"Address\":\"269, WELIGAMPITIYA JA-ELA\",\"District\":\"Gampaha\",\"Grade\":\"A\",\"Name\":\"JAYALATH INN RESTAURANT\"},{\"AGADivision\":\"Colombo\",\"Address\":\"N0.03, EDWARD LANE COLOMBO 03\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"GREAT WALL RESTAURANTS\"},{\"AGADivision\":\"Colombo\",\"Address\":\"338, T.B. JAYAH MAWATHA, COLOMBO 10\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"EXCEL RESTAURANTS\"},{\"AGADivision\":\"Kandy\",\"Address\":\"N0.30, GEORGE E.DE SILVA MAWATHA, KANDY\",\"District\":\"Kandy\",\"Grade\":\"A\",\"Name\":\"PUBS AND PLACES- KANDY\"},{\"AGADivision\":\"Beruwala Pradeshiya Sabha\",\"Address\":\"428, GALLE ROAD, HETTIMULLA BERUWALA\",\"District\":\"Kalutara\",\"Grade\":\"A\",\"Name\":\"KANDOORI RESTAURANT (BERUWALA)\"},{\"AGADivision\":\"Kandy\",\"Address\":\"NO 30,RAJAPIHILLA MAWATHA, KANDY\",\"District\":\"Kandy\",\"Grade\":\"A\",\"Name\":\"SENANI RESTAURANT\"},{\"AGADivision\":\"Biyagama Pradeshiya Sabha\",\"Address\":\"107/1 MAKOLA SOUTH, MAKOLA\",\"District\":\"Gampaha\",\"Grade\":\"A\",\"Name\":\"JANAHITHA RESTAURANT\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.256,SETHMIL BUILDING,5TH FLOOR,SRIMATH RAMANATHAN MAWATHA,COLOMBO 15.\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"FLAG & WHISTLE RESTAURANT & PUB\"},{\"AGADivision\":\"Maharagama\",\"Address\":\"NO. 5/2 5/3 SRI DEWANANDA ROAD,NAWINNA,MAHARAGAMA.\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"CHANVAL RESTAURANT (MAHARAGAMA)\"},{\"AGADivision\":\"Kandy\",\"Address\":\"60/1,YATINUWARA WEEDIYA,KANDY\",\"District\":\"Kandy\",\"Grade\":\"B\",\"Name\":\"PUBS AND PLACES - YATINUWARA\"},{\"AGADivision\":\"Maharagama\",\"Address\":\"NO. 278/4, RATHMALDENIYA ROAD PELANWATTA, PANNIPITIYA\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"ISHARA RESTAURANT\"},{\"AGADivision\":\"Damana Pradeshiya Sabha\",\"Address\":\"BEHIND THE ECONOMICAL CENTER ALUTHPARA, DAMBULLA\",\"District\":\"Matale\",\"Grade\":\"A\",\"Name\":\"SAYURA RESTAURANT\"},{\"AGADivision\":\"Matale\",\"Address\":\"NO.99, KAUDPELELLA, MATALE\",\"District\":\"Matale\",\"Grade\":\"A\",\"Name\":\"RANWELI RESTAURANT\"},{\"AGADivision\":\"Pelmadulla Pradeshiya Sabha\",\"Address\":\"NO 18,DIPPITIGALA, LELLOPITIYA\",\"District\":\"Ratnapura\",\"Grade\":\"A\",\"Name\":\"SILVER RAY RESTAURANT\"},{\"AGADivision\":\"Colombo\",\"Address\":\"LIBERTY ARCADE,NO.282,R.A. DE MEL MW,COLOMBO 03.\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"RAJA BOJUN\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO154, KINYSEY ROAD, COLOMBO07\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"MINTAGE RESTAURANT & BAR\"},{\"AGADivision\":\"Rambukkana Pradeshiya Sabha\",\"Address\":\"PINNAWALA, RAMBUKKANA\",\"District\":\"Kegalle\",\"Grade\":\"A\",\"Name\":\"ELEPHANT PARK RESTAURANT\"},{\"AGADivision\":\"Bentota Pradeshiya Sabha\",\"Address\":\"NO.357,GALLE ROAD,WARAHENA BENTOTA\",\"District\":\"Galle\",\"Grade\":\"A\",\"Name\":\"PUBS AND PLACES (BENTOTA)\"},{\"AGADivision\":\"Hikkaduwa\",\"Address\":\"NO.384, GALLE ROAD HIKKADUWA\",\"District\":\"Galle\",\"Grade\":\"A\",\"Name\":\"REFRESH RESTAURANT\"},{\"AGADivision\":\"Bentota Pradeshiya Sabha\",\"Address\":\"NO.45, NATIONAL HOLIDAY RESORT, BENTOTA\",\"District\":\"Galle\",\"Grade\":\"A\",\"Name\":\"GOLDEN GRILL\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.95,MALAY STREET, COLOMBO 02\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"SAMAL RESTAURANT\"},{\"AGADivision\":\"Colombo\",\"Address\":\"NO.17, MELBOURNE AVENUE COLOMBO 04\",\"District\":\"Colombo\",\"Grade\":\"A\",\"Name\":\"SIAM HOUSE THAI RESTAURANT\"},{\"AGADivision\":\"Gampaha\",\"Address\":\"156/3/F , NEW KANDY ROAD, BANDARAWATTA , BIYAGAMA.\",\"District\":\"Gampaha\",\"Grade\":\"A\",\"Name\":\"RAMANKA RESTAURANT\"}]";

        List<RestaurantItem> travelItems = new ArrayList<>();

        try {
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                String agaDivision = jsonObject.optString("AGADivision");
                String address = jsonObject.optString("Address");
                String district = jsonObject.optString("District");
                String Grade = jsonObject.optString("Grade");
                String name = jsonObject.optString("Name");


                RestaurantItem restaurantItem = new RestaurantItem(agaDivision, address, district, name, Grade);
                travelItems.add(restaurantItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return travelItems;
    }
}