/*
File Name:    LocationValidationResult.java
HomeWork:     HW06
Team Members: Venkata Naga Akash Ungarala
              Revati Dhananjayan Lalitha
              Sloane Houston
*/

package com.example.akashungarala.hw06;


public class LocationValidationResult {
    String status,likelihood, normalizedLocation;

    public LocationValidationResult() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLikelihood() {
        return likelihood;
    }

    public void setLikelihood(String likelihood) {
        this.likelihood = likelihood;
    }

    public String getNormalizedLocation() {
        return normalizedLocation;
    }

    public void setNormalizedLocation(String normalizedLocation) {
        this.normalizedLocation = normalizedLocation;
    }
}

