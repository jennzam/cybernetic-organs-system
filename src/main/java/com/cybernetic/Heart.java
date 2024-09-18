package com.cybernetic;

import java.util.Random;

public class Heart {
    int pumpRate;
    int health;
    Random rand = new Random();

    public Heart(){
        this.pumpRate = 70;
        this.health = 100;
    }

    public void update(int brainControlEfficiency, int lungOxygenLevel){
        this.pumpRate += brainControlEfficiency / 10 - 5 + (rand.nextInt(8) - 4);
        this.pumpRate = Math.max(40, Math.min(100, this.pumpRate));

        this.health += -1 + (lungOxygenLevel / 25) - 2;
        this.health = Math.max(0, Math.min(100, this.health));
    }

    public int getPumpRate(){
        return this.pumpRate;
    }

    public int getHealth(){
        return this.health;
    }

    public void changeHealth(int healthAmount){
        this.health -= healthAmount;
        this.health = Math.max(0, Math.min(100, this.health));
    }

    @Override
    public String toString(){
        return "Heart Health: " + health + " | Pump Rate: " + pumpRate;
    }
}