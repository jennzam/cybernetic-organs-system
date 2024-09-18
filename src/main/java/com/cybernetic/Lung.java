package com.cybernetic;

import java.util.Random;

public class Lung {
        int oxygenLevel;
        int health;
        Random rand = new Random();

        public Lung(){
            this.oxygenLevel = 95;
            this.health = 100;
        }

        public void update(int heartPumpRate){
            this.oxygenLevel += heartPumpRate / 20 - 3 + (rand.nextInt(4) - 2);
            this.oxygenLevel = Math.max(70, Math.min(100, this.oxygenLevel));

            this.health += -1 + (heartPumpRate / 25) - 2;
            this.health = Math.max(0, Math.min(100, this.health));
        }

        public int getOxygenLevel(){
            return this.oxygenLevel;
        }

        public int getHealth(){
            return this.health;
        }

        public void changeHealth(int healthAmount){
            this.health += healthAmount;
            this.health = Math.max(0, Math.min(100, this.health));
        }

        @Override
        public String toString(){
            return "Lung Health: " + health + " | Oxygen Level: " + oxygenLevel;
        }
}
