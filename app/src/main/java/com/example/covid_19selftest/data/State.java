package com.example.covid_19selftest.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class State {

    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("confirmedCases")
    @Expose
    private Integer confirmedCases;
    @SerializedName("casesOnAdmission")
    @Expose
    private Integer casesOnAdmission;
    @SerializedName("discharged")
    @Expose
    private Integer discharged;
    @SerializedName("death")
    @Expose
    private Integer death;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getConfirmedCases() {
        return confirmedCases;
    }

    public void setConfirmedCases(Integer confirmedCases) {
        this.confirmedCases = confirmedCases;
    }

    public Integer getCasesOnAdmission() {
        return casesOnAdmission;
    }

    public void setCasesOnAdmission(Integer casesOnAdmission) {
        this.casesOnAdmission = casesOnAdmission;
    }

    public Integer getDischarged() {
        return discharged;
    }

    public void setDischarged(Integer discharged) {
        this.discharged = discharged;
    }

    public Integer getDeath() {
        return death;
    }

    public void setDeath(Integer death) {
        this.death = death;
    }

}