package com.example.har02.eiexercise;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.androidplot.xy.XYPlot;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements Runnable {

    private TextView textView;
    private SensorClass sensorClass;
    private Thread thread;
    private XYPlot plot;
    private PlotEngine plotEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textView);

        plot = (XYPlot) findViewById(R.id.plot);


        sensorClass = new SensorClass(this, textView);
        sensorClass.startSensor();

        plotEngine = new PlotEngine(plot);
        thread = new Thread(this);
        thread.start();






    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(100);
                double[] newSensorVals = sensorClass.getaSensorVals();
                plotEngine.replot(newSensorVals);
                Log.d("Sensor", "sleep for 100ms" + Arrays.toString(newSensorVals));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
