package com.iconmobile.dcpsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.iconmobile.dcpsdkandroid.DCP;
import com.iconmobile.dcpsdkandroid.Error;
import com.iconmobile.dcpsdkandroid.util.Either;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends BaseActivity {

    private int countPressed = 0;
    private TextView toastcounter;
    private Button nextButton;
    private Button goDeeperButton;
    private Button countButton;
    private Button mathFunctionButton;
    public static final String EXTRA_NUMBER = "dcp_sample_app_android_java_EXTRA_NUMBER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init_toolbar(R.id.toolbar_main);


        DCP.INSTANCE.initialize(this, new DCP.InitializeCallback() {
            @Override
            public void onInitializeCompleted(@NotNull Either<Error, Boolean> either) {
                Log.i("h","initialized method worked");
            }
        });

        Log.i("debug", "hallo");
        toastcounter = (TextView) findViewById(R.id.text_toast_counter);
        nextButton = (Button) findViewById(R.id.next_button);
        countButton = (Button) findViewById(R.id.count_button);
        mathFunctionButton= (Button )findViewById(R.id.math_function_activity);



        nextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                openActivity2();
            }
        });

        goDeeperButton = (Button) findViewById(R.id.deeper_button);
        goDeeperButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLibraryCheck();
            }
        });

       countButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                onCountButtonTap();
            }
        });
       mathFunctionButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
                openMathActivity();
           }
         });
        }

    private void openMathActivity(){
        Intent intent = new Intent(this, VirtualMathFunction.class);
        startActivity(intent);
    }



    public void openActivity2(){

        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra(EXTRA_NUMBER, countPressed);
        startActivity(intent);
    }


    public void openLibraryCheck(){
        Intent intent = new Intent(this, LibraryCheck.class);
        startActivity(intent);
    }


    public void onButtonTap(View view) {
    Log.i("pressed", "pressed");
    Toast myToast = Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();
    }

    public void onCountButtonTap() {
        Log.i("countButton", "count Button is Pressed was called");
        countPressed++;
        toastcounter.setText(String.valueOf(countPressed));

    }


    @Override
    public void setContentView(View view) {
        super.setContentView(view);

        view.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
     /*           MainActivityDirection.ActionMainActivityToSecondActivity action =
                        MainActivityDirections.
                                actionMainActivityToSecondActivity("From FirstFragment");
            NavHostActivity.findNavController(MainActivity.this)
                        .navigate(action);
      */
            }
        });

        view.findViewById(R.id.toast_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("pressed", "pressed");
                Toast myToast = Toast.makeText(getApplicationContext(), "Hello toast!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });
    }

    // onViewCreated()



    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        DCP.INSTANCE.destroy();
    }
}
