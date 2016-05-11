package com.cvut.janzaloudek.stm_dia;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by janzaloudek on 10/05/16.
 */
public class DiaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(true);
    }
}
