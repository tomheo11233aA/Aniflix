package com.polytechnic.aniflix;

import android.app.Application;
import android.content.Context;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MyApplication extends Application {

    private DatabaseReference mDatabaseReference;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        initFirebase();
    }

    public void initFirebase() {
        String mReference = "movie";
        FirebaseDatabase mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mFirebaseDatabase.getReference(mReference);
    }

    public DatabaseReference getDatabaseReference() {
        return mDatabaseReference;
    }
}
