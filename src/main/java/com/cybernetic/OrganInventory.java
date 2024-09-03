package com.cybernetic;

public class OrganInventory {
    String[] inventory = new String[5];

    public String addOrgan(CyberneticOrgan organ){
        return "Organ added.";
    }

    public String getOrgan(String model){
        return "Organ.";
    }
}
