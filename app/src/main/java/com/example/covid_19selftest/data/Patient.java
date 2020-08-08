package com.example.covid_19selftest.data;

public class Patient {
    String name;
    String dateOfBirth;
    String state;
    String healthCondition;
    String breathing;
    String chestPain;
    String speechLoss;
    String fever;
    String dryCough;
    String tiredness;

    public Patient() {

    }

    public Patient(String name, String dateOfBirth, String state, String healthCondition, String breathing, String chestPain, String speechLoss, String fever, String dryCough, String tiredness) {
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
