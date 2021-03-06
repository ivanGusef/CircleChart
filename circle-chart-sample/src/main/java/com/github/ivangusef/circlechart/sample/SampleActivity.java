package com.github.ivangusef.circlechart.sample;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.github.ivangusef.circlechart.circlechart.CircleChartView;


public class SampleActivity extends ActionBarActivity {

    public static final double[] DATA = new double[]{
            15.7,
            13.8,
            100,
            11,
            53
    };

    public static final int[] COLORS = new int[]{
            Color.GREEN,
            Color.DKGRAY,
            Color.CYAN,
            Color.BLUE,
            Color.MAGENTA
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample);

        final CircleChartView chartView = (CircleChartView) findViewById(R.id.chart);
        chartView.setData(DATA, COLORS);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sample, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
