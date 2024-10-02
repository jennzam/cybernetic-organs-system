package com.cybernetic;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        OrganInventory organInventory = new OrganInventory();

        try {
            
            organInventory.loadOrgansCSV("sample-organ-list.csv");

            System.out.println("Before Sorting: ");
            System.out.println(organInventory.getOrganListString());

            List<CyberneticOrgan> sortedOrgans = organInventory.quickSortOrganByNameModelAndCompatibility(organInventory.getOrganList());

            System.out.println("\nAfter QuickSort Sorting: ");
            for (CyberneticOrgan organ : sortedOrgans) {
                System.out.println(organ);
            }

            List<CyberneticOrgan> builtInSortedOrgans = organInventory.sortOrganByNameModelAndCompatibilityUsingBuiltInSort();

            System.out.println("\nAfter Built-In Sorting: ");
            for (CyberneticOrgan organ : builtInSortedOrgans) {
                System.out.println(organ);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
