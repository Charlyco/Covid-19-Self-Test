package com.example.covid_19selftest.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Result.class}, version = 1)
public abstract class ResultDataBase extends RoomDatabase {
    public abstract ResultDao cResultDao();
}
