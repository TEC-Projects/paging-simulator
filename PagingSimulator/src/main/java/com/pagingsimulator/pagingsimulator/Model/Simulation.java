package com.pagingsimulator.pagingsimulator.Model;

import java.io.File;

public class Simulation {
    private String pagingAlgorithm;
    private String randomSeed;
    private Boolean isOperationsFileLoaded;
    private File operationsFile;
    private int numberOfProcesses;
    private int numberOfOperations;

    public Simulation(){
        isOperationsFileLoaded = true;
    };

    public String getPagingAlgorithm() {
        return pagingAlgorithm;
    }

    public void setPagingAlgorithm(String pagingAlgorithm) {
        this.pagingAlgorithm = pagingAlgorithm;
    }

    public String getRandomSeed() {
        return randomSeed;
    }

    public void setRandomSeed(String randomSeed) {
        this.randomSeed = randomSeed;
    }

    public Boolean getOperationsFileLoaded() {
        return isOperationsFileLoaded;
    }

    public void setOperationsFileLoaded(Boolean operationsFileLoaded) {
        isOperationsFileLoaded = operationsFileLoaded;
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
