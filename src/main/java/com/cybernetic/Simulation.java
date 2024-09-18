package com.cybernetic;
import java.util.Random;

public class Simulation {
    Heart heart;
    Lung lung;
    Brain brain;
    Random rand = new Random();

    public Simulation() {
        heart = new Heart();
        lung = new Lung();
        brain = new Brain();
    }

    public void startSimulation(int timeStep) {
        if (timeStep > 100 || heart.getHealth() == 0 || lung.getHealth() == 0 || brain.getHealth() == 0) {
            printSummary(timeStep);
            return;
        }

        System.out.println("\nTime: " + timeStep);
        heart.update(brain.getControlEfficiency(), lung.getOxygenLevel());
        lung.update(heart.getPumpRate());
        brain.update(lung.getOxygenLevel());


        System.out.println(heart);
        System.out.println(lung);
        System.out.println(brain);


        if (heart.getHealth() < 25) {
            System.out.println("ALERT at Time " + timeStep + ": Heart Critical! Health at " + heart.getHealth() + "%");
        }
        if (lung.getHealth() < 25) {
            System.out.println("ALERT at Time " + timeStep + ": Lung Critical! Health at " + lung.getHealth() + "%");
        }
        if (brain.getHealth() < 25) {
            System.out.println("ALERT at Time " + timeStep + ": Brain Critical! Health at " + brain.getHealth() + "%");
        }

        if (rand.nextInt(10) == 0) {
            triggerRandomEvent(timeStep);
        }


        startSimulation(timeStep + 1);

    }

    private void triggerRandomEvent(int timeStep) {
        int organ = rand.nextInt(3);
        int change = rand.nextInt(21) - 10;

        if (organ == 0) {
            heart.changeHealth(change);
            System.out.println("EVENT at Time " + timeStep + ": Random health change for Heart by " + change + "%");
        } else if (organ == 1) {
            lung.changeHealth(change);
            System.out.println("EVENT at Time " + timeStep + ": Random health change for Lung by " + change + "%");
        } else {
            brain.changeHealth(change);
            System.out.println("EVENT at Time " + timeStep + ": Random health change for Brain by " + change + "%");
        }
    }

    private void printSummary(int timeStep) {
        System.out.println("\nSimulation Ended at Time: " + timeStep);
        System.out.println(heart);
        System.out.println(lung);
        System.out.println(brain);

        if (heart.getHealth() > 0 && lung.getHealth() > 0 && brain.getHealth() > 0) {
            System.out.println("Simulation Result: Success");
        } else {
            System.out.println("Simulation Result: Failure");
        }
    }

    public static void main(String[] args) {
        Simulation sim = new Simulation();
        sim.startSimulation(1);
    }
}

