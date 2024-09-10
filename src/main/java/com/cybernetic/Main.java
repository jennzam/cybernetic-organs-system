package com.cybernetic;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        OrganInventory inventory = new OrganInventory();


        System.out.println("Adding organs to inventory...");
        CyberneticOrgan organ1 = new CyberneticOrgan("001", "CyberHeartX1", "Pumps blood", "TypeO");
        CyberneticOrgan organ2 = new CyberneticOrgan("002", "CyberEyeV2", "Enhanced vision", "TypeA");

        System.out.println(inventory.addOrgan(organ1));
        System.out.println(inventory.addOrgan(organ2));
        System.out.println();


        Patient patient = new Patient("John Doe", "45", "Heart condition");
        System.out.println("Adding organs to patient " + patient.name + "...");
        System.out.println(patient.addOrgan(organ1));
        System.out.println();

        System.out.println("Listing installed organs for " + patient.name + ":");
        ArrayList<CyberneticOrgan> organs = patient.getOrganList();
        if (organs.isEmpty()) {
            System.out.println("No organs installed.");
        } else {
            for (CyberneticOrgan organ : organs) {
                System.out.println("- " + organ.model + ": " + organ.functionality);
            }
        }
        System.out.println();


        System.out.println("Listing all organs in inventory:");
        System.out.println(inventory.toString());
        System.out.println();


        System.out.println("Searching for organs with functionality 'Enhanced vision'...");
        System.out.println(inventory.searchOrgansByFunctionality("Enhanced vision"));



        System.out.println("Sorting organs by model name in inventory...");
        inventory.sortOrgansByModel();
        System.out.println("Sorted organs:");
        System.out.println(inventory.getOrganListString());
    }
}