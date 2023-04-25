package com.pagingsimulator.pagingsimulator.Model;

import java.util.LinkedList;

public class PagingAlgorithmSimulationStatus {
    private LinkedList<OperationDetail> operations;
    private int simulationElapsedTime;
    private int thrashingTime;
    private int ramUsage;
    private int vRamUsage;
    private int internalFragmentationVolume;

    public PagingAlgorithmSimulationStatus(LinkedList<OperationDetail> operations) {
        this.operations = operations;
        this.simulationElapsedTime = 0;
        this.thrashingTime = 0;
        this.ramUsage = 0;
        this.vRamUsage = 0;
        this.internalFragmentationVolume = 0;
    }

    public LinkedList<OperationDetail> getOperations() {
        return operations;
    }

    public void setOperations(LinkedList<OperationDetail> operations) {
        this.operations = operations;
    }

    public int getSimulationElapsedTime() {
        return simulationElapsedTime;
    }

    public void setSimulationElapsedTime(int simulationElapsedTime) {
        this.simulationElapsedTime = simulationElapsedTime;
    }

    public int getThrashingTime() {
        return thrashingTime;
    }

    public void setThrashingTime(int thrashingTime) {
        this.thrashingTime = thrashingTime;
    }

    public int getRamUsage() {
        return ramUsage;
    }

    public void setRamUsage(int ramUsage) {
        this.ramUsage = ramUsage;
    }

    public int getVRamUsage() {
        return vRamUsage;
    }

    public void setVRamUsage(int vRamUsage) {
        this.vRamUsage = vRamUsage;
    }

    public int getInternalFragmentationVolume() {
        return internalFragmentationVolume;
    }

    public void setInternalFragmentationVolume(int internalFragmentationVolume) {
        this.internalFragmentationVolume = internalFragmentationVolume;
    }
}
