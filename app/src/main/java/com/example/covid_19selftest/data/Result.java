package com.example.covid_19selftest.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.PrimaryKey;

@Fts4
@Entity
public class Result {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo(name = "rowid")
    public int uid;

    @ColumnInfo(name = "patient_name")
    public String name;

    @ColumnInfo(name = "date_of_birth")
    public String dateOfBirth;

    @ColumnInfo(name = "state_of_origin")
    public String state;
    public String healthCondition;
    public String breathing;
    public String chestPain;
    public String speechLoss;
    public String fever;
    public String dryCough;
    public String tiredness;

    public Result() {

    }

    public Result(String name, String dateOfBirth, String state, String healthCondition, String breathing, String chestPain, String speechLoss, String fever, String dryCough, String tiredness) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.state = state;
        this.healthCondition = healthCondition;
        this.breathing = breathing;
        this.chestPain = chestPain;
        this.speechLoss = speechLoss;
        this.fever = fever;
        this.dryCough = dryCough;
        this.tiredness = tiredness;
    }
}
