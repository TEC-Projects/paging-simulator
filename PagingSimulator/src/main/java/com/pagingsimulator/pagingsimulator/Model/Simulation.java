package com.pagingsimulator.pagingsimulator.Model;

import java.util.LinkedList;

public class Simulation {

    private boolean paused;
    private long randomSeed;
    private int numberOfOperations;
    private int numberOfProcesses;
    private Machine machine;
    private PagingAlgorithmSimulationStatus optimalAlgorithmStatus;
    private PagingAlgorithmSimulationStatus otherAlgorithmStatus;

    public Simulation(String pagingAlgorithm, long randomSeed, int numberOfOperations, int numberOfProcesses) {
        this.paused = false;
        this.numberOfOperations = numberOfOperations;
        this.numberOfProcesses = numberOfProcesses;
        this.randomSeed = randomSeed;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    public void updateOptimalAlgorithmStatus(LinkedList<Page> pages, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume){
        optimalAlgorithmStatus.setPages(pages);
        optimalAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        optimalAlgorithmStatus.setThrashingTime(thrashingTime);
        optimalAlgorithmStatus.setRamUsage(ramUsage);
        optimalAlgorithmStatus.setVRamUsage(vRamUsage);
        optimalAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
    }

    public void updateOtherAlgorithmStatus(LinkedList<Page> pages, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume){
        otherAlgorithmStatus.setPages(pages);
        otherAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        otherAlgorithmStatus.setThrashingTime(thrashingTime);
        otherAlgorithmStatus.setRamUsage(ramUsage);
        otherAlgorithmStatus.setVRamUsage(vRamUsage);
        otherAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
    }

}
