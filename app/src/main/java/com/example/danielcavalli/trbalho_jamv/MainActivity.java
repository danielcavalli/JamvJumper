package com.example.danielcavalli.trbalho_jamv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    //private Sensor senAccelerometer;
    long lastUpdate = 0;
    public static float y ;
    public static float x ;
    public static float z;

    public void setBackgroundColor(int color) {
        View view = getWindow().getDecorView();
        view.setBackgroundColor(color);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new GameView(this));
        sensorManager=(SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        setBackgroundColor(Color.argb(1,61,61,61));
    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;
        if (mySensor.getType()==Sensor.TYPE_ACCELEROMETER) {
            MainActivity.x = sensorEvent.values[0];
            MainActivity.y = sensorEvent.values[1];
            MainActivity.z = sensorEvent.values[2];
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
