package com.iconmobile.dcpsample;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GestureDetectorCompat;

import com.iconmobile.dcpsdkandroid.DCP;
import com.iconmobile.dcpsdkandroid.Error;
import com.iconmobile.dcpsdkandroid.Offer;
import com.iconmobile.dcpsdkandroid.Person;
import com.iconmobile.dcpsdkandroid.Profile;
import com.iconmobile.dcpsdkandroid.Rating;
import com.iconmobile.dcpsdkandroid.util.Either;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class APersonData  extends BaseActivity {

    private static final String TAG = "aPersonDataClass: ";
    private int position;
    private Person aPerson;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.person_data);
        mDetector = new GestureDetectorCompat(this, new MyGestureListener());

        Intent intent = getIntent();
        position = (intent.getIntExtra(PersonAdapter.EXTRA_NUMBER,0))
                %( DCP.INSTANCE.getProfileIds().size());

          aPerson = DCP.INSTANCE.getProfile
                (DCP.INSTANCE.getProfileIds().get(position))
                .getMe();

        showText();




    }






    private void showText() {
        ((TextView) findViewById(R.id.theName)).setText(aPerson.getName());
        ((TextView) findViewById(R.id.theBirthday)).setText("Birthday: " + aPerson.getBirthday());
        ((TextView) findViewById(R.id.theFitness)).setText("Fitness Level: " + aPerson.getFitness().getValue());
        ((TextView) findViewById(R.id.theGender)).setText("Gender: " + aPerson.getGender());
        ((TextView) findViewById(R.id.theHome)).setText("The Home is in: " + aPerson.getHome().getCity()
                + " in " + aPerson.getHome().getCountry());

    HashMap<String, Rating> someInterests = aPerson.getInterests();
    StringBuilder outputInterests= new StringBuilder();
        for(Map.Entry e : someInterests.entrySet()){
        Rating aRating = (Rating) e.getValue();
        Log.i("interest + rating",(DCP.INSTANCE.getInterestLabel((String) e.getKey()) + " = " + String.valueOf( aRating.getValue())));
        outputInterests.append(DCP.INSTANCE.getInterestLabel((String) e.getKey()) + " with a rating of: " + aRating.getValue() + " \n ");

    }
        ((TextView) findViewById(R.id.theInterests)).setText(" Interests: " + outputInterests);

    StringBuilder outputMembershpis = new StringBuilder();
    List<String> membershpips = aPerson.getMemberships();
        for(String s : membershpips){
        outputMembershpis.append(s+" \n ");
    }

        ((TextView) findViewById(R.id.theMembershpis)).setText("Memberships: " + outputMembershpis);

        ((TextView) findViewById(R.id.theWork)).setText("The Work-Address is in: " + aPerson.getWork().getCity()
                + " in " + aPerson.getWork().getCountry());

    }


    //Gesture control

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }







    class MyGestureListener extends GestureDetector.SimpleOnGestureListener{




            private static final String DEBUG_TAG = "Gestures";

        @Override
        public boolean onDown(MotionEvent event) {
            Log.d(DEBUG_TAG,"onDown: " + event.toString());
            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            Log.d(DEBUG_TAG, "onFling: " + event1.toString() + event2.toString());


            Profile aProfile = DCP.INSTANCE.getProfile(DCP.INSTANCE.getProfileIds().get(2));
            List<Offer> offerss;
            Context context =getApplicationContext();

            DCP.INSTANCE.getOffers(aProfile, null, new DCP.OfferCallback(){
                        @Override
                        public void onOfferCallCompleted(@NotNull Either<Error, ? extends List<Offer>> either) {
                            if (either instanceof Either.Left) {
                                Error error = ((Either.Left<Error>) either).getValue();
                                Log.e("Debug", "Offers error: " + error.getMessage());
                            } else if (either instanceof Either.Right) {
                                List<Offer> offers = ((Either.Right<List<Offer>>) either).getValue();
                                Log.e("Debug", "Get offers: " + offers.size());

                            } }
                    });


           Toast myToast = Toast.makeText(getApplicationContext(), "offers", Toast.LENGTH_SHORT);

            myToast.show();

            return true;
        }
    }




}

