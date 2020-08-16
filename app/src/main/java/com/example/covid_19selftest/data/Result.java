package com.example.covid_19selftest.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Fts4;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;


@Entity
public class Result {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @NonNull
    @ColumnInfo(name = "patient_name")
    public String name ;

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
    public String status;

    @Ignore
    public Result() {
    }

    public Result(String name, String dateOfBirth, String state, String healthCondition,
                  String breathing, String chestPain, String speechLoss, String fever,
                  String dryCough, String tiredness, String status) {
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
        this.status = status;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getState() {
        return state;
    }

    public String getHealthCondition() {
        return healthCondition;
    }

    public String getBreathing() {
        return breathing;
    }

    public String getChestPain() {
        return chestPain;
    }

    public String getSpeechLoss() {
        return speechLoss;
    }

    public String getFever() {
        return fever;
    }

    public String getDryCough() {
        return dryCough;
    }

    public String getTiredness() {
        return tiredness;
    }

    public String getStatus() {
        return status;
    }
}