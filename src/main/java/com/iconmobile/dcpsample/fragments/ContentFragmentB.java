package com.iconmobile.dcpsample.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.iconmobile.dcpsample.R;

public class ContentFragmentB extends Fragment implements View.OnClickListener
{
    public static String FRAG_MESSAGE_DEF_B =
            "com.example.myfragment.CALL_FRAGMENT_B";


    private OnContentBButtonClickedListener mCallback;
    public interface OnContentBButtonClickedListener {
        public void onContentBButtonClicked(int buttonID, String optionalData);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.content_view_b, container, false);
        Button btnShowFragB = (Button) myView.findViewById(R.id.sendContentBText);
        btnShowFragB.setOnClickListener(this);
        if (savedInstanceState != null) {
            // restore internal values - here not needed
        }
        return myView;
    }


    @Override
    public void onStart() {
        super.onStart();
        String textValue = "";
// check if in two pane view mode
        if (getFragmentManager().findFragmentById(R.id.control_fragment)    !=    null) {
            textValue = getResources().getString(R.string.prefix_two_pane);
        } else {
            textValue = getResources().getString(R.string.prefix_one_pane);
        }
        Bundle args = getArguments();
        if (args != null) {
            textValue += " " + args.getString(FRAG_MESSAGE_DEF_B);
        }
        setTextValue(textValue);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnContentBButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnContentAButtonClickedListener");

        }
    }


    public void setTextValue(String newValue)
    {
        Activity myActivity = getActivity();
        if (myActivity != null) {
            TextView myTextView =
                    (TextView) myActivity.findViewById(R.id.fragBSentText);
            if (myTextView != null)
                myTextView.setText(newValue);
        }
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendContentBText) {
            Activity myActivity = getActivity();
            if (myActivity != null) {
                String messageContent = ((EditText)
                        myActivity.findViewById(
                                R.id.editContentBText)).getText().toString();
                mCallback.onContentBButtonClicked(R.id.sendContentBText,
                        messageContent);
            }
        }
    }







}
