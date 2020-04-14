package com.iconmobile.dcpsample.Licht;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.iconmobile.dcpsample.BaseActivity;
import com.iconmobile.dcpsample.R;

import java.io.IOException;

public class VirtualMathFunction extends BaseActivity {

    private static final int DELTA_VALUE = 5 ;
    private static final String TAG = "math Function Class: ";
    private EditText inputTextField;
    private SeekBar erstesSchieberegler;
    private SeekBar zweiterSchiebeRegler;
    private Button whole_diagramm_button;
    TextView textView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.math_function);
        init_toolbar(R.id.toolbar_main);

        ShowGraph aGraph = new ShowGraph();
        LineChart chart= findViewById(R.id.linechart_small);
        try {
            aGraph.doLineGraph(this, chart);
        } catch (IOException e) {
            e.printStackTrace();
        }

        inputTextField = (EditText) findViewById(R.id.get_a_number);
        erstesSchieberegler = (SeekBar) findViewById(R.id.seekBar_for_number);
        zweiterSchiebeRegler = (SeekBar) findViewById(R.id.seekBar2);
        textView = (TextView) findViewById(R.id.number2vonSchieberregler);
        whole_diagramm_button = (Button) findViewById(R.id.whole_diagramm);
        textView.setText(String.valueOf( erstesSchieberegler.getProgress())) ;

        inputTextField.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    sendMessage();
                    handled = true;
                }
                return handled;
            }
        });

        whole_diagramm_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), LightningConditions.class);
                startActivity(intent);
            }
        });

        this.textView.setText("Helligkeit: " + erstesSchieberegler.getProgress() + "/" + erstesSchieberegler.getMax());
        //
        this.erstesSchieberegler.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progress = 0;

            // When Progress value changed.
            @Override
            public void onProgressChanged(SeekBar seekBar, int progressValue, boolean fromUser) {
                progress = progressValue;
                textView.setText("Helligkeit: " + progressValue + "/" + seekBar.getMax());
                Log.i(TAG, "Changing seekbar's progress");
                Toast.makeText(getApplicationContext(), "Changing seekbar's progress", Toast.LENGTH_SHORT).show();
            }

            // Notification that the user has started a touch gesture.
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.i(TAG, "Started tracking seekbar");
                Toast.makeText(getApplicationContext(), "Started tracking seekbar", Toast.LENGTH_SHORT).show();
            }

            // Notification that the user has finished a touch gesture
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                textView.setText("Progress: " + progress + "/" + seekBar.getMax());
                Log.i(TAG, "Stopped tracking seekbar");
                Toast.makeText(getApplicationContext(), "Stopped tracking seekbar", Toast.LENGTH_SHORT).show();

            }
        });



    }


    private void sendMessage() {
        String progress = String.valueOf(inputTextField.getText());
        Log.i(TAG, "lalal"+ progress + "is that progress");
        int progress2 = Integer.parseInt(progress);
        if(progress2 > this.erstesSchieberegler.getMax())  {
            this.erstesSchieberegler.setProgress(0);
        }else {
            this.erstesSchieberegler.setProgress(progress2 );
        }
        textView.setText("Progress: " + erstesSchieberegler.getProgress()+ "/" + erstesSchieberegler.getMax());


    }









}