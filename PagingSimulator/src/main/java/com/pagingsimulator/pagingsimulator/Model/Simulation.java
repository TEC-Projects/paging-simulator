package com.pagingsimulator.pagingsimulator.Model;

import java.util.LinkedList;

public class Simulation {

    private boolean paused;
    private String pagingAlgorithm;
    private int numberOfOperations;
    private int numberOfProcesses;
    private PagingAlgorithmSimulationStatus optimalAlgorithmStatus;
    private PagingAlgorithmSimulationStatus otherAlgorithmStatus;

    public Simulation(String pagingAlgorithm, int numberOfOperations, int numberOfProcesses, PagingAlgorithmSimulationStatus optimalAlgorithmStatus, PagingAlgorithmSimulationStatus otherAlgorithmStatus) {
        this.paused = false;
        this.pagingAlgorithm = pagingAlgorithm;
        this.numberOfOperations = numberOfOperations;
        this.numberOfProcesses = numberOfProcesses;
        this.optimalAlgorithmStatus = optimalAlgorithmStatus;
        this.otherAlgorithmStatus = otherAlgorithmStatus;
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    public void updateOptimalAlgorithmStatus(LinkedList<OperationDetail> operations, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume){
        optimalAlgorithmStatus.setOperations(operations);
        optimalAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        optimalAlgorithmStatus.setThrashingTime(thrashingTime);
        optimalAlgorithmStatus.setRamUsage(ramUsage);
        optimalAlgorithmStatus.setVRamUsage(vRamUsage);
        optimalAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
    }

    public void updateOtherAlgorithmStatus(LinkedList<OperationDetail> operations, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume){
        otherAlgorithmStatus.setOperations(operations);
        otherAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        otherAlgorithmStatus.setThrashingTime(thrashingTime);
        otherAlgorithmStatus.setRamUsage(ramUsage);
        otherAlgorithmStatus.setVRamUsage(vRamUsage);
        otherAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
    }

}
