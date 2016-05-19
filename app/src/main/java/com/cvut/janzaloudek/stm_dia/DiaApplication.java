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
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
