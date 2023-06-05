package com.example.dreamtravelv1;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class compass extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor magnetometer;
    private Sensor accelerometer;
    private float[] lastAccelerometerData = new float[3];
    private float[] lastMagnetometerData = new float[3];
    private float[] rotationMatrix = new float[9];
    private float[] orientation = new float[3];

    private ImageView compassImage;
    private TextView directionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        compassImage = findViewById(R.id.compassImage);
        directionTextView = findViewById(R.id.directionTextView);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        // Set up the bottom navigation view
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);
        bottomNavigationView.setSelectedItemId(R.id.compass);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.compass:
                        return true;

                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.search:
                        startActivity(new Intent(getApplicationContext(),search.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.location:
                        startActivity(new Intent(getApplicationContext(),MapsActivity.class));
                        overridePendingTransition(0,0);
                        return  true;
                    default:
                        return false;
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor == null) return;

        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(event.values, 0, lastAccelerometerData, 0, event.values.length);
        } else if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(event.values, 0, lastMagnetometerData, 0, event.values.length);
        }

        SensorManager.getRotationMatrix(rotationMatrix, null, lastAccelerometerData, lastMagnetometerData);
        SensorManager.getOrientation(rotationMatrix, orientation);

        float azimuth = (float) Math.toDegrees(orientation[0]);
        compassImage.setRotation(-azimuth);

        String direction;
        if (azimuth >= -22.5 && azimuth < 22.5) {
            direction = "N";
        } else if (azimuth >= 22.5 && azimuth < 67.5) {
            direction = "NE";
        } else if (azimuth >= 67.5 && azimuth < 112.5) {
            direction = "E";
        } else if (azimuth >= 112.5 && azimuth < 157.5) {
            direction = "SE";
        } else if (azimuth >= 157.5 || azimuth < -157.5) {
            direction = "S";
        } else if (azimuth >= -157.5 && azimuth < -112.5) {
            direction = "SW";
        } else if (azimuth >= -112.5 && azimuth < -67.5) {
            direction = "W";
        } else {
            direction = "NW";
        }
        directionTextView.setText(direction);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Do nothing
    }
}
