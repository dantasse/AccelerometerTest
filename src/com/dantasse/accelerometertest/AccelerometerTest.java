package com.dantasse.accelerometertest;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class AccelerometerTest extends Activity implements OnClickListener {

    TextView textView;
    Button button1;
    SensorManager sensorManager;
    Sensor accelerometer;
    Sensor uiAccelerometer;

    Listener normalListener;
    Listener uiListener;
    Listener gameListener;
    Listener fastestListener;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager
                .getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        
//        normalListener = new Listener(this);
//        sensorManager.registerListener(normalListener, accelerometer,
//                SensorManager.SENSOR_DELAY_NORMAL);
//        uiListener = new Listener(this);
//        sensorManager.registerListener(uiListener, accelerometer,
//                SensorManager.SENSOR_DELAY_UI);
//        gameListener = new Listener(this);
//        sensorManager.registerListener(gameListener, accelerometer,
//                SensorManager.SENSOR_DELAY_GAME);
        fastestListener = new Listener(this);
        sensorManager.registerListener(fastestListener, accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST);

        textView = (TextView) findViewById(R.id.text_view);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
    }

    protected void onResume() {
        super.onResume();
//        sensorManager.registerListener(normalListener, accelerometer,
//                SensorManager.SENSOR_DELAY_NORMAL);
//        sensorManager.registerListener(uiListener, accelerometer,
//                SensorManager.SENSOR_DELAY_UI);
//        sensorManager.registerListener(gameListener, accelerometer,
//                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(fastestListener, accelerometer,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    protected void onPause() {
        super.onPause();
//        sensorManager.unregisterListener(normalListener);
//        sensorManager.unregisterListener(uiListener);
//        sensorManager.unregisterListener(gameListener);
        sensorManager.unregisterListener(fastestListener);
    }

    public void displayRates() {
        button1.setEnabled(true);

        textView.setText(String.format(
                "Sampling rate: %.2f", fastestListener.getSamplingRate()));
//                String.format("Normal rate: %.2f\nUI rate: %s\nGame rate: %s\nFastest rate: %s\n",
//                        normalListener.getSamplingRate(),
//                        uiListener.getSamplingRate(),
//                        gameListener.getSamplingRate(),
//                        fastestListener.getSamplingRate()));
    }

    @Override
    public void onClick(View v) {
        // only clickable thing is the button
        button1.setEnabled(false);
        textView.setText("Working...");
//        normalListener.startRecording();
//        uiListener.startRecording();
//        gameListener.startRecording();
        fastestListener.startRecording();
    }
}
