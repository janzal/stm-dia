package com.cvut.janzaloudek.stm_dia;

import android.app.Application;

import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by janzaloudek on 10/05/16.
 */
public class DiaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Setup Firebase persistence (data are stored after application is killed)
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
