package com.pagingsimulator.pagingsimulator.UI.Model;

import java.io.File;

public class SimulationRequest {
    private String pagingAlgorithm;
    private Long randomSeed;
    private Boolean simulationThroughOperationFile;
    private File operationsFile;
    private int numberOfProcesses;
    private int numberOfOperations;

    public SimulationRequest(){
        simulationThroughOperationFile = true;
    };

    public SimulationRequest(String pagingAlgorithm, Long randomSeed, Boolean simulationThroughOperationFile, File operationsFile, int numberOfProcesses, int numberOfOperations) {
        this.pagingAlgorithm = pagingAlgorithm;
        this.randomSeed = randomSeed;
        this.simulationThroughOperationFile = simulationThroughOperationFile;
        this.operationsFile = operationsFile;
        this.numberOfProcesses = numberOfProcesses;
        this.numberOfOperations = numberOfOperations;
    }

    public String getPagingAlgorithm() {
        return pagingAlgorithm;
    }

    public void setPagingAlgorithm(String pagingAlgorithm) {
        this.pagingAlgorithm = pagingAlgorithm;
    }

    public Long getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(Long randomSeed) {
        this.randomSeed = randomSeed;
    }

    public Boolean isSimulationThroughOperationFile() {
        return simulationThroughOperationFile;
    }

    public void setSimulationThroughOperationFile(Boolean simulationThroughOperationFile) {
        this.simulationThroughOperationFile = simulationThroughOperationFile;
    }

    public File getOperationsFile() {
        return operationsFile;
    }

    public void setOperationsFile(File operationsFile) {
        this.operationsFile = operationsFile;
    }

    public int getNumberOfProcesses() {
        return numberOfProcesses;
    }

    public void setNumberOfProcesses(int numberOfProcesses) {
        this.numberOfProcesses = numberOfProcesses;
    }

    public int getNumberOfOperations() {
        return numberOfOperations;
    }

    public void setNumberOfOperations(int numberOfOperations) {
        this.numberOfOperations = numberOfOperations;
    }
}
