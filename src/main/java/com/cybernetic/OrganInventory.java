package com.cybernetic;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.UUID;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class OrganInventory {
    ArrayList<CyberneticOrgan> inventory;

    public OrganInventory() {
        inventory = new ArrayList<>();
    }

    public String addOrgan(CyberneticOrgan organ) {
        inventory.add(organ);
        return "Added " + organ.model + " to inventory.";
    }

    public ArrayList<CyberneticOrgan> getOrganList() {
        return inventory;
    }

    public ArrayList<CyberneticOrgan> sortOrgansByModel() {
        Collections.sort(inventory, new Comparator<CyberneticOrgan>() {
            @Override
            public int compare(CyberneticOrgan o1, CyberneticOrgan o2) {
                return o1.model.compareTo(o2.model);
            }
        });
        return inventory;
    }

    public String removeOrgan(String model) {
        for (CyberneticOrgan organ : inventory) {
            if (organ.model.equals(model)) {
                inventory.remove(organ);
                return "Organ " + model + " removed.";
            }
        }

        return "Organ " + model + " not found.";
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Organ Inventory:\n");
        if (inventory.isEmpty()) {
            str.append("No organs in inventory.\n");
        } else {
            for (CyberneticOrgan organ : inventory) {
                str.append("- ").append(organ.model).append(": ").append(organ.functionality).append("\n");
            }
        }
        return str.toString();
    }

    public String getOrganListString() {
        StringBuilder str = new StringBuilder();
        for (CyberneticOrgan organ : inventory) {
            str.append("- ").append(organ.model).append(": ").append(organ.functionality).append("\n");
        }
        return str.toString();
    }

    public String searchOrgansByFunctionality(String functionality) {
        ArrayList<CyberneticOrgan> result = new ArrayList<>();
        for (CyberneticOrgan organ : inventory) {
            if (organ.functionality.equalsIgnoreCase(functionality)) {
                result.add(organ);
            }
        }

        StringBuilder str = new StringBuilder();
        str.append("Found ").append(result.size()).append(" organ(s):\n");
        for (CyberneticOrgan organ : result) {
            str.append("- ").append(organ.model).append(": ").append(organ.functionality).append("\n");
        }
        return str.toString();
    }

    public String sortOrgansByModelString() {
        sortOrgansByModel();
        StringBuilder str = new StringBuilder();
        for (CyberneticOrgan organ : inventory) {
            str.append("- ").append(organ.model).append("\n");
        }
        return str.toString();
    }

    public void loadOrgansCSV(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName);
            CSVReader csvReader = new CSVReader(new InputStreamReader(inputStream))) {

                if(csvReader ==null)

                {
                    throw new IOException("Resource not found: " + fileName);
                }

                String[] nextLine;
                csvReader.readNext();

                while((nextLine =csvReader.readNext())!=null)

                {
                    if (nextLine.length == 4) {
                        CyberneticOrgan organ = new CyberneticOrgan(
                                UUID.randomUUID().toString(),
                                nextLine[0].trim(),
                                nextLine[1].trim(),
                                nextLine[2].trim()
                        );
                        addOrgan(organ);
                    }
                }

            } catch (IOException e){
                e.printStackTrace();
            } catch (CsvValidationException e) {
                e.printStackTrace();
        }


    }

    public void removeDuplicateOrgans(){
        ArrayList<CyberneticOrgan> uniqueOrgans = new ArrayList<>();
        removeDuplicatesHelper(0, uniqueOrgans);
        inventory = uniqueOrgans;
    }

    private void removeDuplicatesHelper(int startIndex, ArrayList<CyberneticOrgan> uniqueOrgans) {
        if (startIndex >= inventory.size()){
            return;
        }

        CyberneticOrgan currentOrgan = inventory.get(startIndex);
        boolean isDuplicate = false;

        for (CyberneticOrgan organ : uniqueOrgans) {
            if (organ.model.equals(organ.model)) {
                isDuplicate = true;
                break;
            }
        }
        if (!isDuplicate) {
            uniqueOrgans.add(currentOrgan);
        }
        removeDuplicatesHelper(startIndex + 1, uniqueOrgans);
    }

    public CyberneticOrgan findOrganByModel(String model) {
        return findOrganByModelHelper(0, model);
    }

    public CyberneticOrgan findOrganByModelHelper(int startIndex, String model) {
        if (startIndex >= inventory.size()) {
            return null;
        }

        CyberneticOrgan currentOrgan = inventory.get(startIndex);
        if (currentOrgan.model.equals(model)) {
            return currentOrgan;
        }
        return findOrganByModelHelper(startIndex + 1, model);
    }

}




