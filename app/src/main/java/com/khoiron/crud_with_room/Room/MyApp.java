package com.khoiron.crud_with_room.Room;

import android.app.Application;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.migration.Migration;

/**
 * Created by khoiron on 27/01/18.
 */

public class MyApp extends Application {

    public static AppDatabase db;
    
    @Override
    public void onCreate() {
        super.onCreate();
        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"mahasiswa").allowMainThreadQueries().build();
    }

}
