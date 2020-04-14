package com.iconmobile.dcpsample.Licht;

import android.os.Bundle;

import com.github.mikephil.charting.charts.LineChart;
import com.iconmobile.dcpsample.BaseActivity;
import com.iconmobile.dcpsample.R;

import java.io.IOException;

public class LightningConditions extends BaseActivity {

        LineChart chart;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.show_graph);
            chart = findViewById(R.id.chart);

            ShowGraph aGraph = new ShowGraph();

            try {
                aGraph.doLineGraph(this, chart);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
}
