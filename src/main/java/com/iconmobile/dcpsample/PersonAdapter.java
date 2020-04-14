package com.iconmobile.dcpsample;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.iconmobile.dcpsdkandroid.Person;

import java.util.List;

// Create the basic adapter extending from RecyclerView.Adapter
// Note that we specify the custom ViewHolder which gives us access to our views
public class PersonAdapter extends
        RecyclerView.Adapter<PersonAdapter.ViewHolder> {


    public static final String EXTRA_NUMBER = "dcp_sample_app_android_java_EXTRA_NUMBER";
    private List <Person> mPerson;
    private Context context;


    // Pass in the person List into the constructor
    public PersonAdapter(List<Person> persons) {
        mPerson = persons;
    }


    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView nameTextView;
        public Button getAllDataOfPersonButton;

        // We also create a constructor that accepts the entire item row and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            nameTextView = (TextView) itemView.findViewById(R.id.person_name);
            getAllDataOfPersonButton = (Button) itemView.findViewById(R.id.all_data_of_person_button);

            itemView.setOnClickListener(this);
            getAllDataOfPersonButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, APersonData.class);
                    intent.putExtra(EXTRA_NUMBER, getAdapterPosition() +1); //minus 1, da der erste Wert bei init in LibraryCheck rausgenommen wurde
                    context.startActivity(intent);
                }
            });

        }



        @Override
        public void onClick(View view) {
            int position = getAdapterPosition(); // gets item position
            if (position != RecyclerView.NO_POSITION) { // Check if an item was deleted, but the user clicked it before the UI removed it
                Person personInAdapter = mPerson.get(position);

                Log.i("on clicked: ", String.valueOf(personInAdapter));
                // We can access the data within the views
               Toast.makeText(context, personInAdapter.getBirthday(), Toast.LENGTH_SHORT).show();
            }
        }
    }






    @NonNull
    @Override
    //to layout and create the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate(aufblasen, aufpumpen) the custom layout
        View personView = inflater.inflate(R.layout.layout_listitem, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(personView);
        return viewHolder;
    }

    @Override
    //set the view attributes based on data
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get the data model based on position
        Person person = mPerson.get(position);

        // Set item views based on your views and data model
        TextView textView = holder.nameTextView;
        textView.setText(person.getName());
        Button button = holder.getAllDataOfPersonButton;
       button.setText((person.getGender().equals("male")) ? "View the data of a male person" : "female person");
       // button.setEnabled(person.isOnline());
    }
    @Override
    public int getItemCount() {
        return mPerson.size();
    }
}