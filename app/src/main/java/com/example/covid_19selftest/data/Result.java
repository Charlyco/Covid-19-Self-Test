package com.example.covid_19selftest.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity
public class Result implements Serializable {
    @PrimaryKey (autoGenerate = true)
    public int uid;

    @NonNull
    @ColumnInfo(name = "patient_name")
    public String name;

    @ColumnInfo(name = "date_of_assessment")
    public String dateOfAssessment;

    @ColumnInfo(name = "state_of_origin")
    public String state;
    public String healthCondition;
    public String breathing;
    public String chestPain;
    public String speechLoss;
    public String fever;
    public String dryCough;
    public String tiredness;
    public String soreThroat;
    public String tasteLoss;
    public String recommendation;

    @Ignore
    public Result() {
    }

    public Result(@NonNull String name, String dateOfAssessment, String state, String healthCondition,
                  String breathing, String chestPain, String speechLoss, String tasteLoss, String fever,
                  String dryCough, String tiredness, String soreThroat, String recommendation) {
        this.name = name;
        this.dateOfAssessment = dateOfAssessment;
        this.state = state;
        this.healthCondition = healthCondition;
        this.breathing = breathing;
        this.chestPain = chestPain;
        this.speechLoss = speechLoss;
        this.tasteLoss = tasteLoss;
        this.fever = fever;
        this.dryCough = dryCough;
        this.tiredness = tiredness;
        this.soreThroat = soreThroat;
        this.recommendation = recommendation;
    }

    @NotNull
    public String getName() {
        return name;
    }

    public String getDateOfAssessment() {
        return dateOfAssessment;
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

    public String getTasteLoss() { return tasteLoss; }

    public String getFever() {
        return fever;
    }

    public String getDryCough() {
        return dryCough;
    }

    public String getTiredness() {
        return tiredness;
    }

    public String getSoreThroat() { return soreThroat; }

    public String getRecommendation() {
        return recommendation;
    }

    public void setDateOfAssessment(String dateOfAssessment) {
        this.dateOfAssessment = dateOfAssessment;
    }
}
