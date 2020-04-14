package com.iconmobile.dcpsample;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iconmobile.dcpsdkandroid.DCP;
import com.iconmobile.dcpsdkandroid.Person;

import java.util.ArrayList;
import java.util.List;

public class PersonRecyclerView extends BaseActivity {

    private List<String> profileIds;
    public ArrayList<Person> allPersons = new ArrayList<Person>();



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.recycle_view_tester);

            profileIds = DCP.INSTANCE.getProfileIds();

            //fill Lists, initialize
            for(int i=1; i < profileIds.size(); ++i) {
                Person aCurrentPerson = DCP.INSTANCE.getProfile(
                        profileIds.get(i))
                        .getMe();
                allPersons.add(aCurrentPerson);
            }
            for(int i=1; i < profileIds.size(); ++i) {
                Person aCurrentPerson = DCP.INSTANCE.getProfile(
                        profileIds.get(i))
                        .getMe();
                allPersons.add(aCurrentPerson);
            }
            // Lookup the recycler view
            RecyclerView recyclervPersons = (RecyclerView) findViewById(R.id.recyclerv_view);

            PersonAdapter adapter = new PersonAdapter(allPersons);
            // Attach the adapter to the recyclerview to populate items
            recyclervPersons.setAdapter(adapter);
            // Set layout manager to position the items
            recyclervPersons.setLayoutManager(new LinearLayoutManager(this));
            recyclervPersons.setHasFixedSize(true);
            /*RecyclerView.ItemDecoration itemDecoration = new
                    DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            recyclervPersons.addItemDecoration(itemDecoration); */




     /*       Intent intent = getIntent();

            countPressed = intent.getIntExtra(MainActivity.EXTRA_NUMBER,0);
            TextView textViewHeader = (TextView) findViewById(R.id.random);
            TextView textViewRandom = (TextView) findViewById(R.id.theRandomNumber);

            textViewHeader.setText("Here is a random number between 0 and " + countPressed);
            textViewRandom.setText(String.valueOf(makeRandomOf(countPressed)));
*/
        }
    }



