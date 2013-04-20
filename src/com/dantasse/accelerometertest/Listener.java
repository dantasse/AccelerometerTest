package com.dantasse.accelerometertest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class Listener implements SensorEventListener {

    private long startTime;
    private int numSamples;
    private boolean isActive = false;
    private double samplingRate = 0.0;
    private AccelerometerTest accelerometerTest;
    
    public Listener(AccelerometerTest accelerometerTest) {
        this.accelerometerTest = accelerometerTest;
    }
    public double getSamplingRate() {
        return samplingRate;
    }
    public void startRecording() {
        startTime = System.currentTimeMillis();
        numSamples = 0;
        isActive = true;
    }
    public boolean isActive() {
        return isActive;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (isActive) {
            numSamples++;
            long now = System.currentTimeMillis();
            if (now >= startTime + 5000) {
                samplingRate = numSamples / ((now - startTime) / 1000.0);                
                isActive = false;
                accelerometerTest.displayRates();
            }
        }
    }
}
