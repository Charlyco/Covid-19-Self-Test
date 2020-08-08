package com.example.covid_19selftest.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ResultDao {

    @Query("SELECT * FROM Result")
    LiveData<Result[]> loadAllResults();

    @Query("SELECT * FROM Result WHERE patient_name LIKE :name")
    LiveData<Result[]> findByName(String name);

    @Query("SELECT * FROM Result WHERE rowid IN (:patientIds)")
    LiveData<Result[]> loadAllByIds(int[] patientIds);

    @Query("SELECT patient_name, state_of_origin FROM result")
    LiveData<List<Result>> loadNameAndState();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(Result... results);

    @Delete
    void delete(Result result);

    @Delete
    void deleteAll(Result...results);
}
