package com.iconmobile.dcpsample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.iconmobile.dcpsample.Licht.LightningConditions;
import com.iconmobile.dcpsample.Licht.VirtualMathFunction;

public class BaseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    public void init_toolbar(int toolbar_id) {
        Toolbar toolbar = (Toolbar) findViewById(toolbar_id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.menu_activity_main, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();
            switch(id){
                case R.id.menu_in_header:
                    Toast myToast= Toast.makeText(this, "Menu selected", Toast.LENGTH_LONG);
                    myToast.show();
                    return true;

                case R.id.action_get_to_library_check:
                    Intent intent = new Intent(this, LibraryCheck.class);
                    startActivity(intent);
                    return true;

                case R.id.action_get_to_all_persons:
                    Intent intent2 = new Intent(this, PersonRecyclerView.class);
                    startActivity(intent2);
                    return true;

                case R.id.action_get_virtual_math_function:
                    Intent intent3 = new Intent(this, VirtualMathFunction.class);
                    startActivity(intent3);
                    return true;

                case R.id.action_show_graph:
                    Intent intent4 = new Intent(this, LightningConditions.class);
                    startActivity(intent4);
                    return true;

                case R.id.action_settings:
                    return true;

                default: return super.onOptionsItemSelected(item);
            }

        }

    @Override
    public void setContentView(int layoutResID) {
        DrawerLayout fullView = (DrawerLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout activityContainer = (FrameLayout) fullView.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, activityContainer, true);
        super.setContentView(fullView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        init_toolbar(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        setTitle("Activity Title");
    }

}
