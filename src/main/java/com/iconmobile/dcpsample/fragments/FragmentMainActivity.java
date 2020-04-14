package com.iconmobile.dcpsample.fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.iconmobile.dcpsample.R;

public class FragmentMainActivity extends FragmentActivity implements
        com.iconmobile.dcpsample.fragments.ControlFragment.OnControlButtonClickedListener,
        com.iconmobile.dcpsample.fragments.ContentFragmentA.OnContentAButtonClickedListener,
        com.iconmobile.dcpsample.fragments.ContentFragmentB.OnContentBButtonClickedListener {

@Override
    public void onContentAButtonClicked(int buttonID, String optionalData) {
        sendDataToControl(optionalData);
    }
    @Override
    public void onContentBButtonClicked(int buttonID, String optionalData) {
        sendDataToControl(optionalData);
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragmente_main);
        if (findViewById(R.id.my_fragment_container) != null) {
            if (savedInstanceState != null) {
                if (getSupportFragmentManager().findFragmentById(
                        R.id.my_fragment_container) != null)
                    return;
            }
            com.iconmobile.dcpsample.fragments.ControlFragment firstFragment = new com.iconmobile.dcpsample.fragments.ControlFragment();
            firstFragment.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.my_fragment_container, firstFragment).commit();
        } else {
            if (savedInstanceState == null) {
                com.iconmobile.dcpsample.fragments.ContentFragmentA secondFragment =
                        new com.iconmobile.dcpsample.fragments.ContentFragmentA();
                secondFragment.setArguments(getIntent().getExtras());
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.my_content_fragment_container,
                                secondFragment).commit();
            }
        }
    }


    @Override
    public void onControlButtonClicked(int buttonID, String optionalData) {
        String messageValue = ((EditText)
                findViewById(R.id.editControlText)).getText().toString();
        switch(buttonID){
            case R.id.showFragA:
                sendDataToContentA(messageValue);
                break;
            case R.id.showFragB:
                sendDataToContentB(messageValue);
                break;
            case R.id.sendControlText:
                sendControlText(messageValue);
                break;
        }
    }



    private void sendDataToControl(String optionalData) {
        if (!inTwoPaneMode()) {
            com.iconmobile.dcpsample.fragments.ControlFragment newFragment = new com.iconmobile.dcpsample.fragments.ControlFragment();
            Bundle args = new Bundle();
            args.putString(com.iconmobile.dcpsample.fragments.ControlFragment.FRAG_MESSAGE_DEF_C,
                    optionalData);
            newFragment.setArguments(args);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.my_fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            ((EditText) findViewById(R.id.editControlText)).setText(optionalData);
        }
    }


    private void sendDataToContentA(String optionalData) {
        if (inTwoPaneMode()) {
            if (findViewById(R.id.linearLayoutA) != null) {
                com.iconmobile.dcpsample.fragments.ContentFragmentA contentFragment = (com.iconmobile.dcpsample.fragments.ContentFragmentA) getSupportFragmentManager()
                        .findFragmentById(R.id.my_content_fragment_container);
                contentFragment.setTextValue(optionalData);
            } else {
                com.iconmobile.dcpsample.fragments.ContentFragmentA newFragment = new com.iconmobile.dcpsample.fragments.ContentFragmentA();
                Bundle args = new Bundle();
                args.putString(com.iconmobile.dcpsample.fragments.ContentFragmentA.FRAG_MESSAGE_DEF_A,
                        optionalData);
                newFragment.setArguments(args);
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.my_content_fragment_container,
                        newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }else{
            com.iconmobile.dcpsample.fragments.ContentFragmentA newFragment = new com.iconmobile.dcpsample.fragments.ContentFragmentA();
            Bundle args = new Bundle();
            args.putString(com.iconmobile.dcpsample.fragments.ContentFragmentA.FRAG_MESSAGE_DEF_A,
                    optionalData);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.my_fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    private void sendDataToContentB(String optionalData) {
        if (inTwoPaneMode()) {
            if (findViewById(R.id.linearLayoutA) != null) {
                com.iconmobile.dcpsample.fragments.ContentFragmentB contentFragment = (com.iconmobile.dcpsample.fragments.ContentFragmentB) getSupportFragmentManager()
                        .findFragmentById(R.id.my_content_fragment_container);
                contentFragment.setTextValue(optionalData);
            } else {
                com.iconmobile.dcpsample.fragments.ContentFragmentB newFragment = new com.iconmobile.dcpsample.fragments.ContentFragmentB();
                Bundle args = new Bundle();
                args.putString(com.iconmobile.dcpsample.fragments.ContentFragmentB.FRAG_MESSAGE_DEF_B,
                        optionalData);
                newFragment.setArguments(args);
                FragmentTransaction transaction =
                        getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.my_content_fragment_container,
                        newFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        }else{
            com.iconmobile.dcpsample.fragments.ContentFragmentB newFragment = new com.iconmobile.dcpsample.fragments.ContentFragmentB();
            Bundle args = new Bundle();
            args.putString(com.iconmobile.dcpsample.fragments.ContentFragmentB.FRAG_MESSAGE_DEF_B,
                    optionalData);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.my_fragment_container, newFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }


    private void sendControlText(String optionalData) {
        Fragment contentFragment =
                getSupportFragmentManager().findFragmentById(
                        R.id.my_content_fragment_container);
        if ((contentFragment != null) &&
                (findViewById(R.id.linearLayoutA) != null)) {
            ((com.iconmobile.dcpsample.fragments.ContentFragmentA)contentFragment).setTextValue(optionalData);
        } else if ((contentFragment != null) &&
                (findViewById(R.id.linearLayoutB) != null)) {
            ((com.iconmobile.dcpsample.fragments.ContentFragmentB)contentFragment).setTextValue(optionalData);
        }
    }

    private boolean inTwoPaneMode() {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            return false;
        }
        return (getSupportFragmentManager().findFragmentById(
                R.id.my_content_fragment_container) != null);
    }

}
