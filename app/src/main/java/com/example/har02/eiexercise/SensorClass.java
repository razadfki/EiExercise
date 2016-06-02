package com.example.har02.eiexercise;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.widget.TextView;

import com.androidplot.xy.XYPlot;

import org.w3c.dom.Text;

import java.io.File;
import java.util.Arrays;

/**
 * Created by har02 on 6/2/2016.
 */
public class SensorClass implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mSensor;
    private TextView textView;
    private Context mcontext;
    private XYPlot plot;
    double[] aSensorVals;
    private Thread fileThread;
    private FileManagement fileManagement;

    public SensorClass(Context context, TextView textView) {
        this.mcontext = context;
        aSensorVals = new double[3];

        this.textView = textView;
        mSensorManager = (SensorManager) mcontext.getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        fileManagement = new FileManagement();
        fileThread = new Thread(fileManagement);
        fileThread.start();

        //textView.setText(mSensor.toString());

        //mSensorManager.registerListener(this,mSensor,)
        //Log.d("sensor",mSensor.toString());
    }

    @Override
    public void onSensorChanged(SensorEvent event) {


        aSensorVals[0] = event.values[0];
        aSensorVals[1] = event.values[1];
        aSensorVals[2] = event.values[2];
        //textView.setText(String.valueOf(aSensorVals[0]) + ", " + String.valueOf(aSensorVals[1]) + ", " + String.valueOf(aSensorVals[2]));
        fileManagement.writeDataBlocking(System.currentTimeMillis()+ Arrays.toString(aSensorVals));
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void startSensor() {
        mSensorManager.registerListener(this, mSensor, mSensorManager.SENSOR_DELAY_FASTEST);
    }

    public void pauseSensor() {
        mSensorManager.unregisterListener(this);
    }

    public double[] getaSensorVals() {
        return this.aSensorVals;
    }
}
