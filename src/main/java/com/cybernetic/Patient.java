package com.cybernetic;

import java.util.ArrayList;

public class Patient {
    String name;
    String age;
    String medicalHistory;
    CyberneticOrgan[] installedOrgans = new CyberneticOrgan[5];
    int organCount = 0;

    public Patient(String name, String age, String medicalHistory) {
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    public String addOrgan(CyberneticOrgan organ) {
        if (organCount < installedOrgans.length) {
            installedOrgans[organCount] = organ;
            organCount++;

            return "Added " + organ.model + " to " + name + "'s installed organs.";
        } else {
            return "Organ limit reached, cannot install more organs.";
        }
    }


    public ArrayList<CyberneticOrgan> getOrganList() {
        ArrayList<CyberneticOrgan> installedOrgansList = new ArrayList<>();
        for (int i = 0; i < organCount; i++) {
            installedOrgansList.add(installedOrgans[i]);
        }
        return installedOrgansList;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Name: ").append(name).append("\n");
        str.append("Age: ").append(age).append("\n");
        str.append("Medical History: ").append(medicalHistory).append("\n");

        str.append("Installed Organs: ");
        if (organCount == 0) {
            str.append("No organs installed.");
        } else {
            str.append("\n");
            for (int i = 0; i < organCount; i++) {
                str.append("- ").append(installedOrgans[i].model).append(": ").append(installedOrgans[i].functionality).append("\n");
            }
        }

        return str.toString();
    }


}
