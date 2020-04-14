package com.iconmobile.dcpsample;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.iconmobile.dcpsdkandroid.Address;
import com.iconmobile.dcpsdkandroid.Budget;
import com.iconmobile.dcpsdkandroid.Coord;
import com.iconmobile.dcpsdkandroid.DCP;
import com.iconmobile.dcpsdkandroid.Error;
import com.iconmobile.dcpsdkandroid.Fitness;
import com.iconmobile.dcpsdkandroid.Offer;
import com.iconmobile.dcpsdkandroid.Person;
import com.iconmobile.dcpsdkandroid.Profile;
import com.iconmobile.dcpsdkandroid.Rating;
import com.iconmobile.dcpsdkandroid.Region;
import com.iconmobile.dcpsdkandroid.util.Either;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LibraryCheck extends BaseActivity {

    private static final ArrayList <String > EXTRA_LIST = new ArrayList<>();
    TextView textViewHeader;
    List<String> profileIds;
    ArrayList<Person> allShownPersons = new ArrayList<Person>();
    ArrayList<String> myNames;
    ArrayList<String> myImageUrls;
    Button profile_button;
    Button person_button;
    Button address_button;
    Button budget_button;
    Button coordinates_button;
    Profile aProfile;
    Person aPerson;
    Address homeAddress;


    private static final String TAG = "libraryCheckActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_check);


        profile_button = (Button) findViewById(R.id.profile_button);
        person_button = (Button) findViewById(R.id.person_button);
        address_button = (Button) findViewById(R.id.address_button);
        budget_button = (Button) findViewById(R.id.budget_button);
        coordinates_button= (Button) findViewById(R.id.coordinates_button);
        profileIds = DCP.INSTANCE.getProfileIds();

        aProfile = DCP.INSTANCE.getProfile(profileIds.get(3));
        aPerson = aProfile.getMe();


//profile checks
        profile_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onProfileButtonTap();
            }
        });
//person checks
        person_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onPersonButtonTap();
            }
        });
//address checks
        address_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onAddressButtonPressed();
            }
        });

//budgetCheck
        budget_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                onBudgetButtonPressed();
            }
        });

//Corrdinates Check
        coordinates_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                OnCoordinatesButtonPressed();
            }
        });

//DCP Check
        //initialise,destroy
        DCP.INSTANCE.destroy();
        DCP.INSTANCE.initialize(this, new DCP.InitializeCallback() {
            @Override
            public void onInitializeCompleted(@NotNull Either<Error, Boolean> either) {
                Log.i("h","initialized method worked again");
            }}
        );

        DCP.INSTANCE.getOffers(aProfile,null, new DCP.OfferCallback() {
            @Override
            public void onOfferCallCompleted(@NotNull Either<Error, ? extends List<Offer>> either) {
                  Log.i(TAG, " in offer CAllback");
                }

            } )
        ;

        //interests
        HashMap<String, Rating> someInterests = aPerson.getInterests();
        for(Map.Entry e : someInterests.entrySet()){
            Rating aRating = (Rating) e.getValue();
            Log.i("interest + rating",(DCP.INSTANCE.getInterestLabel((String) e.getKey()) + " = " + String.valueOf( aRating.getValue())));
        }
        Log.i("max if rating range", String.valueOf(Rating.Companion.getMAX())
                + " \n " + String.valueOf(Rating.Companion.getMIN()));

        for ( String key : someInterests.keySet() ) {
            Log.i("interstLabel", DCP.INSTANCE.getInterestLabel(key));
            Log.i("children of interest: ", String.valueOf(DCP.INSTANCE.getInterests(key)));
        }
        //refresh
        DCP.INSTANCE.refresh(false, new DCP.RefreshCallback() {
                    @Override
                    public void onRefreshCompleted(@NotNull Either<Error, Boolean> either) {
                        Log.i("refresh asynchron","worked ");
                    }
                }
                );

//RegionCheck
        homeAddress = aPerson.getHome();
        Region aRegionAsAddress;

//FitnessCheck
        Fitness aFitness = aPerson.getFitness();
        Log.i("aFitnessCategory", String.valueOf(aPerson.getFitness().getValue())
                +  " \n " +"MAX value" +  String.valueOf(Fitness.Companion.getMAX())
                +  " \n " +"MIN value" +  String.valueOf(Fitness.Companion.getMIN())
                +  " \n " +"is between MIN and MAX: " +  String.valueOf((aFitness.getValue() < Fitness.Companion.getMAX() && (aFitness.getValue() > Fitness.Companion.getMIN())))
        );






    }






    public void onProfileButtonTap() {
        Log.i("createProfile", String.valueOf(profileIds.size()));
        Log.i("createProfile", String.valueOf(profileIds.contains(profileIds.get(2))));
        Log.i("createProfile", String.valueOf(profileIds.contains("qa:1")));

        for (String profile: profileIds) {
            Log.i("getId0", ((String) profile));


        }

        aProfile = DCP.INSTANCE.getProfile(profileIds.get(2));
        Log.i("getIdOfPerson", String.valueOf(aProfile.getId())
                + " \n " + String.valueOf(aProfile.getBudget())
                + " \n " + String.valueOf(aProfile.getKeywords())
                + " \n " + String.valueOf(aProfile.getMe())
                + " \n " + String.valueOf(aProfile.getTcAccepted())
                + " \n " + String.valueOf(aProfile.getBudget())
                + " \n " + String.valueOf(aProfile.getSpouse())
                + " \n " + String.valueOf(aProfile.getVersion())

        );

        //Profile createdProfile = DCP.INSTANCE.createProfile();
        aProfile.setVersion(2);
        Log.i("version of created Profile", " \n " + String.valueOf(aProfile.getVersion()));
        aProfile.setVersion(1);
        TextView textViewHeader = (TextView) findViewById(R.id.header_libraryCheck);
        textViewHeader.setText("profile runned");


    }

    public void onPersonButtonTap(){

        aPerson = aProfile.getMe();
        for(int i=1; i < profileIds.size(); ++i) {
            allShownPersons.add(DCP.INSTANCE.getProfile(
                    profileIds.get(i))
                    .getMe());
        }
        myNames = new ArrayList<String>();
        for(Person person: allShownPersons){
            myNames.add(person.getName());
            }





        Log.i(TAG, String.valueOf(allShownPersons));
        Log.i("a Profile:",  "from ID:" + String.valueOf(aProfile.getId())
                + " \n " + "Birthday:" + String.valueOf(aPerson.getBirthday())
                + " \n " + "Fitness:" + String.valueOf(aPerson.getFitness())
                + " \n " + "Gender:" + String.valueOf(aPerson.getGender())
                + " \n " +  "home Address:" + String.valueOf(aPerson.getHome())
                + " \n " + "interests:" +String.valueOf(aPerson.getInterests())
                + " \n " + "memberships:" + String.valueOf(aPerson.getMemberships())
                + " \n " + "name:" +String.valueOf(aPerson.getName())
                + " \n " + "work:" +String.valueOf(aPerson.getWork())
        );

       Intent intent = new Intent(this, PersonRecyclerView.class);
       startActivity(intent);





    }


    public void onAddressButtonPressed(){
        homeAddress = aPerson.getHome();
        Address workAddress = aPerson.getWork();


        Log.i("home Address:",  "from: " + String.valueOf(aPerson.getName())
                + " \n " + "city :" + String.valueOf(homeAddress.getCity())
                + " \n " + "coordinates:" + String.valueOf(homeAddress.getCoord())
                + " \n " + "Country:" + String.valueOf(homeAddress.getCountry())
                + " \n " +  "email: " + String.valueOf(homeAddress.getEmail())
                + " \n " + "phone:" +String.valueOf(homeAddress.getPhone())
                + " \n " + "street:" + String.valueOf(homeAddress.getStreet())
                + " \n " + "valid:" +String.valueOf(homeAddress.getValid())
                + " \n " + "zip format:" +String.valueOf(homeAddress.getZip())
        );

        Log.i("work Address:",  "from: " + String.valueOf(aPerson.getName())
                + " \n " + "city :" + String.valueOf(workAddress.getCity())
                + " \n " + "coordinates:" + String.valueOf(workAddress.getCoord())
                + " \n " + "Country:" + String.valueOf(workAddress.getCountry())
                + " \n " +  "email: " + String.valueOf(workAddress.getEmail())
                + " \n " + "phone:" +String.valueOf(workAddress.getPhone())
                + " \n " + "street:" + String.valueOf(workAddress.getStreet())
                + " \n " + "valid:" +String.valueOf(workAddress.getValid())
                + " \n " + "zip:" +String.valueOf(workAddress.getZip())
        );
        TextView textViewHeader = (TextView) findViewById(R.id.header_libraryCheck);
        textViewHeader.setText("address runned");

    }


    public void onBudgetButtonPressed(){
        Budget aBudget = aProfile.getBudget();
        Log.i("Budget: ", String.valueOf(aBudget.getValue())
                +  " \n " +"MAX value" +  String.valueOf(Budget.Companion.getMAX())
                +  " \n " +"MIN value" +  String.valueOf(Budget.Companion.getMIN())
                +  " \n " +"is between MIN and MAX: " +  String.valueOf((aBudget.getValue() < Budget.Companion.getMAX() && (aBudget.getValue() > Budget.Companion.getMIN())))
        );

   /*   for (String currentProfileID: profileIds) {
          if(currentProfileID!= "0"){
              Profile aCurrentProfile = DCP.INSTANCE.getProfile(currentProfileID);
              Budget aCurrentBudget = aCurrentProfile.getBudget();
              Log.i("Budget is between MIN and MAX: ", String.valueOf(
                      (aCurrentBudget.getValue() < Budget.Companion.getMAX() && (aBudget.getValue() > Budget.Companion.getMIN())))
              );
          }


        }
*/
        TextView textViewHeader = (TextView) findViewById(R.id.header_libraryCheck);
        textViewHeader.setText("Budget runned");
    }

    public void OnCoordinatesButtonPressed(){
        homeAddress = aPerson.getHome();
        Coord aCoordinate = homeAddress.getCoord();
        Log.i("coordinates",
                "the latitude" + String.valueOf(aCoordinate.getLat())
                        + " \n " + "the longitude"+ String.valueOf(aCoordinate.getLon())
        );
        TextView textViewHeader = (TextView) findViewById(R.id.header_libraryCheck);
        textViewHeader.setText("Coordinates runned");
    }

}