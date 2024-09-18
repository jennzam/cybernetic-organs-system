package com.cybernetic;

import java.util.Random;

public class Brain {
        int controlEfficiency;
        int health;
        Random rand = new Random();

        public Brain(){
            this.controlEfficiency = 90;
            this.health = 100;
        }

        public void update(int lungOxygenLevel){
            this.controlEfficiency -= lungOxygenLevel / 20 - 3 + (rand.nextInt(4) - 2);
            this.controlEfficiency = Math.max(50, Math.min(100, this.controlEfficiency));

            this.health += -1 + (lungOxygenLevel / 25) - 2;
            this.health = Math.max(0, Math.min(100, this.health));
        }

        public int getControlEfficiency(){
            return this.controlEfficiency;
        }

        public int getHealth(){
            return this.health;
        }

        public void changeHealth(int healthAmount){
            this.health += healthAmount;
            this.health = Math.max(0, Math.min(100, this.health));
        }

    @Override
    public String toString() {
        return "Brain Health: " + health + " | Control Efficiency: " + controlEfficiency;
    }

}
