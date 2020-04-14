package com.iconmobile.dcpsample.Licht;

import android.content.Context;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.iconmobile.dcpsample.R;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ShowGraph {

    final String TAG = "ShowGraphClass: ";

    public void doLineGraph(Context context, LineChart chart) throws IOException {

        ArrayList<Entry> entries = new ArrayList<>();
        entries.add(new Entry(0, 4));
        entries.add(new Entry(1, 1));
        entries.add(new Entry(2, 2));
        entries.add(new Entry(3, 4));

        File filedir = context.getFilesDir();
        File file = new File("com/iconmobile/dcpsample/Licht/LightningDataRessource");



// Jetzt die Befragung:

        Log.i("APP", file.getAbsolutePath());
        Log.i("APP", file.getParent());
        Log.i("APP", String.valueOf(File.listRoots()));
        Log.i("APP", String.valueOf(file.getAbsoluteFile()));
        Log.i("APP fildir", filedir.getAbsolutePath());
        Log.i("APP fildir", filedir.getName());
        Log.i("APP fildir", filedir.getParent());


        if (filedir.exists()) {
            Log.i("App", "Ich bin!");
        }

        if (filedir.isFile()) {
            Log.i("App", "Ich bin eine Datei!");
        }

        if (file.isDirectory()) {
            Log.i("App", "Ich bin ein Verzeichnis!");
        }

        if (file.getParentFile().exists()) {
            Log.i("App", "Ich bin!");
        }

        if (file.isFile()) {
            Log.i("App file", "Ich bin eine Datei!");
        }

        if (file.isDirectory()) {
            Log.i("App", "Ich bin ein Verzeichnis!");
        }



    /*    Context cxt = context;
        FileOutputStream fos = null;

        String dateiname = "test.txt";
        String text = "Dies ist unser erster Text";

        try {
            //Öffne privaten FileOutputStream
            fos = cxt.openFileOutput(dateiname, Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            //Fehler beim Speichern
            Log.e(TAG, "File not found");
        }
        try {
            //Schreibe Text in die Datei
            fos.write(text.getBytes());
        } catch (IOException e) {
            //Fehler beim Speichern
            Log.e(TAG, "Fehler beim speichern");

        }
        try {

            //Schließe den FileOutputStream
            fos.close();

        } catch (IOException e) {

            //Fehler beim Speichern
            Log.e(TAG, "error");

        }





        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(("app/sampledata/LightningDataResource.csv")));
            String line = "";
            while((line = br.readLine()) != null) {
                // Ganze Zeile:
                // System.out.println(line);
                String[] parts = line.split(";");
                Log.i(TAG,"Vorname: " + parts[0]);
                Log.i(TAG,"Nachname: " + parts[1]);
                int time = Integer.parseInt(parts[0]);
                int value = Integer.parseInt(parts[1]);
                entries.add(new Entry(time, value));
                // ...
            }
        } catch(FileNotFoundException e) {
            e.printStackTrace();
            Log.e(TAG,"File not found");
        } catch(IOException e) {
            e.printStackTrace();
            Log.e(TAG,"Fehlerhafte Eingabe");
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch(IOException e) {
                    e.printStackTrace();
                    Log.e(TAG,"Fehlerhafte Eingabe");
                }
            }
        }




    */

        LineDataSet dataSet = new LineDataSet(entries, "Customized values");
                dataSet.setColor(ContextCompat.getColor(context, R.color.colorPrimary));
                dataSet.setValueTextColor(ContextCompat.getColor(context, R.color.colorPrimaryDark));
        //****
        // Controlling X axis
        XAxis xAxis = chart.getXAxis();
        // Set the xAxis position to bottom. Default is top
                xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        // Set the xAxis textSize
                xAxis.setTextSize(R.attr.autoSizeTextType);

        //Customizing x axis value
        final String[] months = new String[]{"Jan", "Feb", "Mar", "Apr"};

        IAxisValueFormatter formatter = new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return months[(int) value];
            }
        };
                xAxis.setGranularity(1f); // minimum axis-step (interval) is 1
                xAxis.setValueFormatter(formatter);

        //***
        // Controlling right side of y axis
        YAxis yAxisRight = chart.getAxisRight();
                yAxisRight.setEnabled(false);

        //***
        // Controlling left side of y axis
        YAxis yAxisLeft = chart.getAxisLeft();
                yAxisLeft.setGranularity(1f);
                yAxisLeft.setTextSize(R.attr.autoSizeTextType);

        // Setting Data
        LineData data = new LineData(dataSet);
                chart.setData(data);
                chart.animateX(2500);
        //refresh
                chart.invalidate();
    }

}
