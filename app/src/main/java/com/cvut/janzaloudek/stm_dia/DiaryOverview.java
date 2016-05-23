package com.cvut.janzaloudek.stm_dia;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cvut.janzaloudek.stm_dia.Survey.SurveyResponseOverviewAdapter;
import com.cvut.janzaloudek.stm_dia.model.entity.SurveyResponse;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.LinkedHashMap;
import java.util.Map;

public class DiaryOverview extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{

//    private PendingIntent pendingIntent;
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

        FirebaseAuth ref = FirebaseAuth.getInstance();
        FirebaseUser authData = ref.getCurrentUser();

        if (authData == null) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            return;
        }

        setContentView(R.layout.activity_diary_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        loadData();
//        setNotificationIfAllowed();
    }

//    private void setNotificationIfAllowed() {
//
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//
//        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
//        notificationIntent.addCategory("android.intent.category.DEFAULT");
//
//        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.SECOND, 15);
//        alarmManager.setExact(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), broadcast);
//    }

    private void loadData() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReferenceFromUrl(Config.FIREBASE_RESPONSES_URL);
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        ref = ref.child(user.getUid());
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
                    Intent intent = new Intent(DiaryOverview.this, SurveyDetailActivity.class);
                    // Send id of current record for detail information
                    Bundle b = new Bundle();
                    int count = 0;
                    for (String key : surveyResponses.keySet()) {
                        if (count == i) {
                            b.putSerializable("rec",surveyResponses.get(key));
                            break;
                        }
                        count++;
                    }

                    intent.putExtras(b); //Put your response to display
                    startActivity(intent);
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;

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
        FirebaseAuth.getInstance().signOut();
    }
}
