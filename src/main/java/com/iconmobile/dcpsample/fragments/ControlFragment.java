package com.iconmobile.dcpsample.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.iconmobile.dcpsample.R;

public class ControlFragment extends Fragment implements View.OnClickListener {

    public static String FRAG_MESSAGE_DEF_C = "sensor-sample-app-android.CALL_FRAGMENT_C";
    View myView;

    private OnControlButtonClickedListener mCallback;
    public interface OnControlButtonClickedListener {
        public void onControlButtonClicked(int buttonNo, String optionalData);
    }








    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            // restore internal values
            }
        myView = inflater.inflate(R.layout.control_view, container, false);
        Button btnShowFragA = (Button) myView.findViewById(R.id.showFragA);
        btnShowFragA.setOnClickListener(this);
        Button btnShowFragB = (Button) myView.findViewById(R.id.showFragB);
        btnShowFragB.setOnClickListener(this);
        Button btnSendText = (Button) myView.findViewById(R.id.sendControlText);
        btnSendText.setOnClickListener(this);
        return myView;
    }


    @Override
    public void onClick(View v) {
        Activity myActivity = getActivity();
        if (myActivity != null) {
            String myMessage = ((EditText) myActivity.findViewById(
                    R.id.editControlText)).getText().toString();
            if ((v.getId() == R.id.showFragA) ||
                    (v.getId() == R.id.showFragB) ||
                    (v.getId() == R.id. sendControlText))
                mCallback.onControlButtonClicked(v.getId(), myMessage);
        }
    }



    @Override
    public void onStart() {
        super.onStart();
        Bundle args = getArguments();
        if (args != null) {
            Activity myActivity = getActivity();
            if (myActivity != null)
                ((EditText) myActivity.findViewById(
                        R.id.editControlText)).setText(
                        args.getString(FRAG_MESSAGE_DEF_C));
        }
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mCallback = (OnControlButtonClickedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnControlButtonClickedListener");
        }
    }


}
