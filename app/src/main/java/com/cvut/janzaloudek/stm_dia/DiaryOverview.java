package com.cvut.janzaloudek.stm_dia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cvut.janzaloudek.stm_dia.FieldSelection.FieldAdapter;
import com.cvut.janzaloudek.stm_dia.Survey.SurveyResponseOverviewAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyItem;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyResponse;
import com.firebase.client.AuthData;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DiaryOverview extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

    Map<String, SurveyResponse> surveyResponses = new LinkedHashMap<String, SurveyResponse>();

    public void showLoginActivity(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void showFieldSelectionActivity(View view) {
        Intent intent = new Intent(this, FieldSelectionActivity.class);
        startActivity(intent);
    }

    public void showSurveyDetailActivity(View view) {
        Intent intent = new Intent(this, SurveyDetailActivity.class);
        // Send id of current record for detail information
        // TODO: change record_id by real id of chosen record
        int record_id = 1;
        Bundle b = new Bundle();
        b.putInt("rec_id", record_id); //Your id
        intent.putExtras(b); //Put your id to your next Intent
        startActivity(intent);
    }

    public void showSurveyFormActivity(View view) {
        Intent intent = new Intent(this, SurveyFormActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Firebase ref = new Firebase(Config.FIREBASE_URL);
        AuthData authData = ref.getAuth();

        if (authData == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return;
        }

        setContentView(R.layout.activity_diary_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadData();
    }

    private void loadData() {
        Firebase ref = new Firebase(Config.FIREBASE_RESPONSES_URL).child("janzal");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot s : snapshot.getChildren()) {
                    surveyResponses.put(s.getKey(), s.getValue(SurveyResponse.class));
                }

                SurveyResponseOverviewAdapter adapter = new SurveyResponseOverviewAdapter(surveyResponses);

                ListView listView = (ListView) findViewById(R.id.overview_listview);
                listView.setAdapter(adapter);

                listView.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
                    //                Intent intent = new Intent(DiaryOverview.this, SurveyDetailActivity.class);
//                startActivity(intent);
                    Intent intent = new Intent(DiaryOverview.this, SurveyDetailActivity.class);
                    // Send id of current record for detail information
                    // TODO: change record_id by real id of chosen record
                    int record_id = 1;
                    Bundle b = new Bundle();
                    b.putInt("rec_id", record_id); //Your id
                    intent.putExtras(b); //Put your id to your next Intent
                    startActivity(intent);
                });
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.diary_overview, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);

        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

//            case R.id.action_favorite:
//                // User chose the "Favorite" action, mark the current item
//                // as a favorite...
//                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_manage) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.nav_logout) {
            logout();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void logout() {
        Firebase ref = new Firebase(Config.FIREBASE_URL);
        ref.unauth();
    }
}
