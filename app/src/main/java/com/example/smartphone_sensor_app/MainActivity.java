package com.example.smartphone_sensor_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    // Individual light and proximity sensors.
    private Sensor mSensorLight;

    // TextViews to display current sensor values
    private TextView mTextSensorLight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mSensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mTextSensorLight = (TextView) findViewById(R.id.label_light);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        String sensor_error = getResources().getString(R.string.error_no_sensor);
        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

        float currentValue = sensorEvent.values[0];

        mTextSensorLight.setText(getResources().getString(
                R.string.label_light, currentValue));

        }


    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }


    public void onStartClicked(View view){
        mSensorManager =
                (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mTextSensorLight = (TextView) findViewById(R.id.label_light);
        mSensorLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        if (mSensorLight != null) {
            mSensorManager.registerListener(this, mSensorLight,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        String sensor_error = getResources().getString(R.string.error_no_sensor);
        if (mSensorLight == null) {
            mTextSensorLight.setText(sensor_error);
        }
    }

    public void onStopClicked(View view){
        mSensorManager.unregisterListener(this);
        String sensor_stopped = getResources().getString(R.string.no_data);
        mTextSensorLight.setText(sensor_stopped);
    }

}
