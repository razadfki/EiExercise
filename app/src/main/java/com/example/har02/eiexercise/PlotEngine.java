package com.example.har02.eiexercise;

import android.graphics.Color;
import android.graphics.Paint;

import com.androidplot.xy.LineAndPointFormatter;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYPlot;
import com.androidplot.xy.XYSeries;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by har02 on 6/2/2016.
 */
public class PlotEngine {
    XYPlot plot;
    int buuferSize = 100;

    List<Number> displayBuffer1;
    List<Number> displayBuffer2;
    List<Number> displayBuffer3;

    LineAndPointFormatter series1;
    LineAndPointFormatter series2;
    LineAndPointFormatter series3;
    public PlotEngine(XYPlot xyPlot)
    {
        plot = xyPlot;
        displayBuffer1 = new ArrayList<>(buuferSize);
        displayBuffer2 = new ArrayList<>(buuferSize);
        displayBuffer3 = new ArrayList<>(buuferSize);

        series1 = new LineAndPointFormatter(Color.rgb(255,0,0),null ,null,null);
        series1.getLinePaint().setStrokeJoin(Paint.Join.ROUND);
        series1.getLinePaint().setStrokeWidth(10);

        series2 = new LineAndPointFormatter(Color.rgb(0,255,0),null ,null,null);
        series2.getLinePaint().setStrokeJoin(Paint.Join.ROUND);
        series2.getLinePaint().setStrokeWidth(10);

        series3 = new LineAndPointFormatter(Color.rgb(0,0,255),null ,null,null);
        series3.getLinePaint().setStrokeJoin(Paint.Join.ROUND);
        series3.getLinePaint().setStrokeWidth(10);

        for(int i =0;i<buuferSize; i++)
        {
            displayBuffer1.add(i,0);
            displayBuffer2.add(i,0);
            displayBuffer3.add(i,0);

        }
    }

    public void replot(double[] newVals)
    {
        displayBuffer1.remove(0);
        displayBuffer1.add(newVals[0]);
        displayBuffer2.remove(0);
        displayBuffer2.add(newVals[0]);
        displayBuffer3.remove(0);
        displayBuffer3.add(newVals[0]);

        XYSeries series1 = new SimpleXYSeries(displayBuffer1,SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Series1");
        XYSeries series2 = new SimpleXYSeries(displayBuffer2,SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Series1");
        XYSeries series3 = new SimpleXYSeries(displayBuffer3,SimpleXYSeries.ArrayFormat.Y_VALS_ONLY,"Series1");

        plot.clear();
        plot.addSeries(series1,this.series1);
        plot.addSeries(series2,this.series2);
        plot.addSeries(series3,this.series3);

        plot.redraw();
    }
}

