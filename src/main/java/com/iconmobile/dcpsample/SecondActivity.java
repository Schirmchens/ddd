package com.iconmobile.dcpsample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class SecondActivity extends BaseActivity {

    private int countPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();

        countPressed = intent.getIntExtra(MainActivity.EXTRA_NUMBER,0);
        TextView textViewHeader = (TextView) findViewById(R.id.random);
        TextView textViewRandom = (TextView) findViewById(R.id.theRandomNumber);

        textViewHeader.setText("Here is a random number between 0 and " + countPressed);
        textViewRandom.setText(String.valueOf(makeRandomOf(countPressed)));

    }



    private int makeRandomOf(int i){
        Random random = new java.util.Random();
        int randomNumber = 0;
        if (countPressed > 0){
            randomNumber = random.nextInt(countPressed + 1 );
        }
        return randomNumber;
    }
}
