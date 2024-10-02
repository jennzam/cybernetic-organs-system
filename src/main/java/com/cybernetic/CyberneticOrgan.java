package com.cybernetic;

public class CyberneticOrgan {
    String id;
    String model;
    String functionality;
    String compatibility;


    public CyberneticOrgan(String id, String model, String functionality, String compatibility) {
        this.id = id;
        this.model = model;
        this.functionality = functionality;
        this.compatibility = compatibility;
    }

    public String getDetails() {
        return "ID: " + id + " | Model: " + model + " | Functionality: " + functionality + " | Compatibility: " + compatibility;
    }


    public String isCompatible(String patientCompatibility){

        return patientCompatibility;
    }

    @Override
    public String toString() {
        return "ID: " + id + " | Model: " + model + " | Compatibility: " + compatibility;
    }

}

