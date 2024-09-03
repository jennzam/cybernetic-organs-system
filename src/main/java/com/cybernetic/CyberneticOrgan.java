package com.cybernetic;

public class CyberneticOrgan {
    String id;
    String model;
    String functionality;
    String compatibility;


    public String getDetails(String id, String model, String functionality, String compatibility) {
        this.id = id;
        this.model = model;
        this.functionality = functionality;
        this.compatibility = compatibility;
        return "Details";
    }

    public String isCompatible(String patientCompatibility){
        return patientCompatibility;
    }
}

