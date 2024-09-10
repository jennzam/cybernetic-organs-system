package com.cybernetic;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;

public class OrganInventory {
    ArrayList<CyberneticOrgan> inventory;

    public OrganInventory() {
        inventory = new ArrayList<>();
    }

    public String addOrgan(CyberneticOrgan organ){
        inventory.add(organ);
        return "Added " + organ.model + " to inventory.";
    }

    public ArrayList<CyberneticOrgan> getOrganList(){
        return inventory;
    }

    public ArrayList<CyberneticOrgan> sortOrgansByModel(){
        Collections.sort(inventory, new Comparator<CyberneticOrgan>() {
            @Override
            public int compare(CyberneticOrgan o1, CyberneticOrgan o2) {
                return o1.model.compareTo(o2.model);
            }
        });
        return inventory;
    }

   public String removeOrgan(String model){
        for (CyberneticOrgan organ : inventory){
            if(organ.model.equals(model)){
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


}
