package com.example.smartphone_sensor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorLight;
    private TextView textSensorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        textSensorLight = (TextView) findViewById(R.id.label_light);
        String sensor_stopped = getResources().getString(R.string.no_data);
        textSensorLight.setText(sensor_stopped);
        textSensorLight.setTextColor(Color.GRAY);

    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float currentValue = sensorEvent.values[0];

        textSensorLight.setText(getResources().getString(
                R.string.label_light, currentValue));

        }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void onStartClicked(View view){

        sensorLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (sensorLight != null) {
            sensorManager.registerListener(this, sensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
            textSensorLight.setTextColor(Color.BLACK);
        }
        String sensor_error = getResources().getString(R.string.error_no_sensor);
        if (sensorLight == null) {
            textSensorLight.setText(sensor_error);
        }

    }

    public void onStopClicked(View view){
        sensorManager.unregisterListener(this);
        String sensor_stopped = getResources().getString(R.string.no_data);
        textSensorLight.setText(sensor_stopped);
        textSensorLight.setTextColor(Color.GRAY);
    }

}
