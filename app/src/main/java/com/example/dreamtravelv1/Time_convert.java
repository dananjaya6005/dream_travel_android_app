package com.example.dreamtravelv1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;


public class Time_convert extends AppCompatActivity {

    private EditText editTextTime;
    private TextView textViewConvertedTime;
    private Spinner spinnerCountries;
    private ArrayAdapter<String> countryAdapter;
    private List<String> countryList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_convert);

        editTextTime = findViewById(R.id.editTextTime);
        textViewConvertedTime = findViewById(R.id.textViewConvertedTime);
        spinnerCountries = findViewById(R.id.spinnerCountries);
        Button buttonConvert = findViewById(R.id.buttonConvert);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                convertTime();
            }
        });

        // Populate the country spinner with available time zones
        populateCountrySpinner();

        // Set a listener for country selection changes
        spinnerCountries.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                convertTime(); // Convert time when a new country is selected
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void populateCountrySpinner() {
        countryList = new ArrayList<>();
        String[] availableIDs = TimeZone.getAvailableIDs();

        for (String id : availableIDs) {
            String displayName = TimeZone.getTimeZone(id).getDisplayName();
            countryList.add(displayName);
        }

        countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, countryList);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCountries.setAdapter(countryAdapter);
    }

    private void convertTime() {
        String inputTime = editTextTime.getText().toString();
        String selectedCountry = spinnerCountries.getSelectedItem().toString();

        DateTimeZone sourceTimeZone = DateTimeZone.getDefault();
        DateTimeZone targetTimeZone = DateTimeZone.forID(getTimeZoneID(selectedCountry));

        DateTime sourceDateTime = DateTime.parse("1970-01-01T" + inputTime);
        DateTime targetDateTime = sourceDateTime.withZone(targetTimeZone);

        String convertedTime = targetDateTime.toString("HH:mm");
        textViewConvertedTime.setText("Converted Time: " + convertedTime);
    }

    private String getTimeZoneID(String displayName) {
        for (String id : TimeZone.getAvailableIDs()) {
            if (displayName.equals(TimeZone.getTimeZone(id).getDisplayName())) {
                return id;
            }
        }
        return null;
    }
}
