package com.example.covid_19selftest.data;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

    public class Data {

        @SerializedName("totalSamplesTested")
        @Expose
        private String totalSamplesTested;
        @SerializedName("totalConfirmedCases")
        @Expose
        private Integer totalConfirmedCases;
        @SerializedName("totalActiveCases")
        @Expose
        private Integer totalActiveCases;
        @SerializedName("discharged")
        @Expose
        private Integer discharged;
        @SerializedName("death")
        @Expose
        private Integer death;
        @SerializedName("states")
        @Expose
        private List<State> states = null;

        public String getTotalSamplesTested() {
            return totalSamplesTested;
        }

        public void setTotalSamplesTested(String totalSamplesTested) {
            this.totalSamplesTested = totalSamplesTested;
        }

        public Integer getTotalConfirmedCases() {
            return totalConfirmedCases;
        }

        public void setTotalConfirmedCases(Integer totalConfirmedCases) {
            this.totalConfirmedCases = totalConfirmedCases;
        }

        public Integer getTotalActiveCases() {
            return totalActiveCases;
        }

        public void setTotalActiveCases(Integer totalActiveCases) {
            this.totalActiveCases = totalActiveCases;
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

        public List<State> getStates() {
            return states;
        }

        public void setStates(List<State> states) {
            this.states = states;
        }

    }