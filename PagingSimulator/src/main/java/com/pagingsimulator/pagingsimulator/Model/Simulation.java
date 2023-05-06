package com.pagingsimulator.pagingsimulator.Model;

import com.pagingsimulator.pagingsimulator.Main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Simulation {

    private boolean paused;
    private long randomSeed;
    private int numberOfOperations;
    private int numberOfProcesses;
    private Machine otherMachine;
    private Machine OPTMachine;
    private PagingAlgorithmSimulationStatus optimalAlgorithmStatus;
    private PagingAlgorithmSimulationStatus otherAlgorithmStatus;
    private ArrayList<Operation> operations;

    public Simulation(String pagingAlgorithm, long randomSeed, int numberOfOperations, int numberOfProcesses, ArrayList<Operation> operations, boolean isFileLoaded) {
        this.paused = false;
        this.numberOfOperations = numberOfOperations;
        this.numberOfProcesses = numberOfProcesses;
        this.randomSeed = randomSeed;
        this.operations = operations;
        switch (pagingAlgorithm){
            case "FIFO" -> {
                otherMachine = new FIFOMachine(400, 4);
            }
            case "Second Chance" -> {
                otherMachine = new SecondChanceMachine(400, 4);
            }
            case "Most recently used" -> {
                otherMachine = new MRUMachine(400, 4);
            }
            case "Random" -> {
                otherMachine = new RandomMachine(400, 4, randomSeed);
            }
            default -> {
                otherMachine = null;
            }
        };
        //TODO: CHANGE THIS SHIT TO OPT
        OPTMachine = new FIFOMachine(400, 4);

        otherAlgorithmStatus = new PagingAlgorithmSimulationStatus();
        optimalAlgorithmStatus = new PagingAlgorithmSimulationStatus();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    public void updateOptimalAlgorithmStatus(Collection<Page> pages, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume){
        optimalAlgorithmStatus.setPages(pages);
        optimalAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        optimalAlgorithmStatus.setThrashingTime(thrashingTime);
        optimalAlgorithmStatus.setRamUsage(ramUsage);
        optimalAlgorithmStatus.setVRamUsage(vRamUsage);
        optimalAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
    }

    public void updateOtherAlgorithmStatus(Collection<Page> pages, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume){
        otherAlgorithmStatus.setPages(pages);
        otherAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        otherAlgorithmStatus.setThrashingTime(thrashingTime);
        otherAlgorithmStatus.setRamUsage(ramUsage);
        otherAlgorithmStatus.setVRamUsage(vRamUsage);
        otherAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
    }

    public void runSimulation() throws InterruptedException {
        for(Operation operation : operations){
            while(paused){
                Thread.sleep(1000);
            }

            System.out.println(operation);

            switch (operation.getName()) {
                case "new" -> {
                    otherMachine.newAlloc(operation.getParameters().get(0), operation.getParameters().get(1));
                    OPTMachine.newAlloc(operation.getParameters().get(0), operation.getParameters().get(1));
                }
                case "use" -> {
                    otherMachine.use(operation.getParameters().get(0));
                    OPTMachine.use(operation.getParameters().get(0));
                }
                case "delete" -> {
                    otherMachine.delete(operation.getParameters().get(0), false);
                    OPTMachine.delete(operation.getParameters().get(0), false);
                }
                case "kill" -> {
                    otherMachine.kill(operation.getParameters().get(0));
                    OPTMachine.kill(operation.getParameters().get(0));
                }
                default -> {
                }
            }

            //TODO: Add simulated processes count to the status update. Attribute already included in model.

            updateOtherAlgorithmStatus(
                    otherMachine.getPages().values(),
                    otherMachine.getTime(),
                    otherMachine.getTrashing(),
                    otherMachine.getUsedRam(),
                    otherMachine.getVirtualMemory().size(),
                    100-otherMachine.getUsedRam()
            );
                System.out.println("Other " + otherAlgorithmStatus);

            updateOptimalAlgorithmStatus(
                    OPTMachine.getPages().values(),
                    OPTMachine.getTime(),
                    OPTMachine.getTrashing(),
                    OPTMachine.getUsedRam(),
                    OPTMachine.getVirtualMemory().size(),
                    100-OPTMachine.getUsedRam()
            );

            System.out.println("OPT " + optimalAlgorithmStatus);

        }
    }

}
