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
import java.util.List;

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
                str.append("Name: ").append(organ.id).append("\n");
                str.append("Model: ").append(organ.model).append("\n");
                str.append("Compatibility: ").append(organ.compatibility).append("\n");
                str.append("\n");
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

            if (csvReader == null) {
                throw new IOException("Resource not found: " + fileName);
            }

            String[] nextLine;
            csvReader.readNext();

            while ((nextLine = csvReader.readNext()) != null) {
                if (nextLine.length == 4) {
                    CyberneticOrgan organ = new CyberneticOrgan(

                            nextLine[0].trim(),
                            nextLine[1].trim(),
                            nextLine[2].trim(),
                            nextLine[3].trim()
                    );
                    addOrgan(organ);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }


    }

    public void removeDuplicateOrgans() {
        ArrayList<CyberneticOrgan> uniqueOrgans = new ArrayList<>();
        removeDuplicatesHelper(0, uniqueOrgans);
        inventory = uniqueOrgans;
    }

    private void removeDuplicatesHelper(int startIndex, ArrayList<CyberneticOrgan> uniqueOrgans) {
        if (startIndex >= inventory.size()) {
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

    public List<CyberneticOrgan> sortOrganByNameModelAndCompatibilityUsingBuiltInSort() {
        Collections.sort(inventory, new Comparator<CyberneticOrgan>() {
            @Override
            public int compare(CyberneticOrgan o1, CyberneticOrgan o2) {
                int nameCompare = o1.id.compareTo(o2.id);
                if (nameCompare != 0) {
                    return nameCompare;
                }

                int modelCompare = o1.model.compareTo(o2.model);
                if (modelCompare != 0) {
                    return modelCompare;
                }
                return o1.compatibility.compareTo(o2.compatibility);
            }
        });
        return inventory;
        
    }


    public List<CyberneticOrgan> quickSortOrganByNameModelAndCompatibility(List<CyberneticOrgan> unmodifiableOrganList){
        List<CyberneticOrgan> organList = new ArrayList<>(unmodifiableOrganList);
        quickSort(organList, 0, organList.size() - 1);
        return organList;
        
    }

    public void quickSort(List<CyberneticOrgan> organList, int l, int h) {
        if(l < h){
            int pivotIndex = partition(organList, l, h);
            quickSort(organList, l, pivotIndex - 1);
            quickSort(organList, pivotIndex + 1, h);
        }
    }

    public int partition(List<CyberneticOrgan> organList, int l, int h) {
        CyberneticOrgan pivotOrgan = organList.get(h);
        int i = l - 1;

        for(int j = l; j < h; j++){
            
            if (compareOrgans(organList.get(j), pivotOrgan) < 0){
                i++;
                swap(organList, i, j);
            }
        }
        swap(organList, i + 1, h);
        return i + 1;
    }

    public int compareOrgans(CyberneticOrgan o1, CyberneticOrgan o2) {
        int nameCompare = o1.id.compareTo(o2.id);
        if (nameCompare != 0) {
            return nameCompare;
        }

        int modelCompare = o1.model.compareTo(o2.model);
        if (modelCompare != 0) {
            return modelCompare;
        }

        return o1.compatibility.compareTo(o2.compatibility);
    }

    public void swap(List<CyberneticOrgan> organList, int i, int j) {
        CyberneticOrgan temp = organList.get(i);
        organList.set(i, organList.get(j));
        organList.set(j, temp);
    }

}




