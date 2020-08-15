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
//      Will be used to implement search for a particular patient by name
    @Query("SELECT * FROM Result WHERE patient_name LIKE :name")
    LiveData<List<Result>> findByName(String name);

    @Query("SELECT * FROM Result")
    LiveData<List<Result>> loadAllResults();

//    @Query("SELECT patient_name, state_of_origin FROM result")
//    LiveData<List<Result>> loadNameAndState();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertResult(Result results);

    @Delete
    void delete(Result result);

    @Delete
    void deleteAll(Result...results);
}
