package com.example.covid_19selftest.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Result.class}, version = 4, exportSchema = false)
public abstract class ResultDataBase extends RoomDatabase {
    public abstract ResultDao cResultDao();
    public static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService cDatabaseExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    private static ResultDataBase INSTANCE;

    public static ResultDataBase getDatabase (Context context){
    if (INSTANCE == null){
        synchronized (ResultDataBase.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        ResultDataBase.class, "result_database")
                        .fallbackToDestructiveMigration()
                        .build();
            }
        }
    }
    return INSTANCE;
    }
}
