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

public class ContentFragmentA extends Fragment implements View.OnClickListener
{
    public static String FRAG_MESSAGE_DEF_A =
            "com.example.myfragment.CALL_FRAGMENT_A";


    private OnContentAButtonClickedListener mCallback;
    public interface OnContentAButtonClickedListener {
        public void onContentAButtonClicked(int buttonID, String optionalData);
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.content_view_a, container, false);
        Button btnShowFragA = (Button) myView.findViewById(R.id.sendContentAText);
        btnShowFragA.setOnClickListener(this);
        if (savedInstanceState != null) {
            // restore internal values - here not needed
        }
        return myView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnContentAButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnContentAButtonClickedListener");
        }
    }



    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.sendContentAText) {
            Activity myActivity = getActivity();
            if (myActivity != null) {
                String messageContent = ((EditText)
                        myActivity.findViewById(
                                R.id.editContentAText)).getText().toString();
                mCallback.onContentAButtonClicked(R.id.sendContentAText,
                        messageContent);
            }
        }
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
            textValue += " " + args.getString(FRAG_MESSAGE_DEF_A);
        }
        setTextValue(textValue);
    }

    public void setTextValue(String newValue)
    {
        Activity myActivity = getActivity();
        if (myActivity != null) {
            TextView myTextView =
                    (TextView) myActivity.findViewById(R.id.fragASentText);
            if (myTextView != null)
                myTextView.setText(newValue);
        }
    }


}
