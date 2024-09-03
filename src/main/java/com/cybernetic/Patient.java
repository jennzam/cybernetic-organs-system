package com.cybernetic;

public class Patient {
    String name;
    String age;
    String medicalHistory;
    String[] installedOrgans = new String[5]; //array list


    public String addOrgan(CyberneticOrgan organ){
        //add organ to patient's list of installed organs
        return "List of installed organs.";
    }

    public String getPatientInfo(String name, String age, String medicalHistory){
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
        return "Patient information.";
    }
}
