package com.pagingsimulator.pagingsimulator.Model;

import com.pagingsimulator.pagingsimulator.Main;
import com.pagingsimulator.pagingsimulator.UI.Model.SimulationUpdate;
import javafx.application.Platform;

import java.util.*;

public class Simulation {

    private boolean paused;
    private long randomSeed;
    private int numberOfOperations;
    private int numberOfProcesses;
    private Machine otherMachine;
    private OptimalMachine OPTMachine;
    private PagingAlgorithmSimulationStatus optimalAlgorithmStatus;
    private PagingAlgorithmSimulationStatus otherAlgorithmStatus;
    private ArrayList<Operation> operations;
    private Queue<Integer> ramUsageHistorical;
    private Queue<Integer> vramUsageHistorical;

    public Simulation(String pagingAlgorithm, long randomSeed, int numberOfOperations, int numberOfProcesses, ArrayList<Operation> operations, boolean isFileLoaded) {
        this.paused = false;
        this.numberOfOperations = numberOfOperations;
        this.numberOfProcesses = numberOfProcesses;
        this.randomSeed = randomSeed;
        this.operations = operations;

        switch (pagingAlgorithm){
            case "FIFO" -> {
                otherMachine = new FIFOMachine(400000, 4000);
            }
            case "Second Chance" -> {
                otherMachine = new SecondChanceMachine(400000, 4000);
            }
            case "Most recently used" -> {
                otherMachine = new MRUMachine(400000, 4000);
            }
            case "Random" -> {
                otherMachine = new RandomMachine(400000, 4000, randomSeed);
            }
            default -> {
                otherMachine = null;
            }
        };

        OPTMachine = new OptimalMachine(400000, 4000, operations);

        otherAlgorithmStatus = new PagingAlgorithmSimulationStatus();
        optimalAlgorithmStatus = new PagingAlgorithmSimulationStatus();

        ramUsageHistorical = new LinkedList<>();
        vramUsageHistorical = new LinkedList<>();
    }

    public boolean isPaused() {
        return paused;
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }


    public void updateOptimalAlgorithmStatus(Collection<Page> pages, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume, int processCount, int loadedPages, int unloadedPages){
        optimalAlgorithmStatus.setPages(pages);
        optimalAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        optimalAlgorithmStatus.setThrashingTime(thrashingTime);
        optimalAlgorithmStatus.setRamUsage(ramUsage);
        optimalAlgorithmStatus.setVRamUsage(vRamUsage);
        optimalAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
        optimalAlgorithmStatus.setSimulatedProcesses(processCount);
        optimalAlgorithmStatus.setUnloadedPages(unloadedPages);
        optimalAlgorithmStatus.setLoadedPages(loadedPages);
    }

    public void updateOtherAlgorithmStatus(Collection<Page> pages, int simulationElapsedTime, int thrashingTime, int ramUsage, int vRamUsage, int internalFragmentationVolume, int processCount, int loadedPages, int unloadedPages){
        otherAlgorithmStatus.setPages(pages);
        otherAlgorithmStatus.setSimulationElapsedTime(simulationElapsedTime);
        otherAlgorithmStatus.setThrashingTime(thrashingTime);
        otherAlgorithmStatus.setRamUsage(ramUsage);
        otherAlgorithmStatus.setVRamUsage(vRamUsage);
        otherAlgorithmStatus.setInternalFragmentationVolume(internalFragmentationVolume);
        otherAlgorithmStatus.setSimulatedProcesses(processCount);
        otherAlgorithmStatus.setLoadedPages(loadedPages);
        otherAlgorithmStatus.setUnloadedPages(unloadedPages);
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
                }
                case "use" -> {
                    otherMachine.use(operation.getParameters().get(0));
                }
                case "delete" -> {
                    otherMachine.delete(operation.getParameters().get(0), false);
                }
                case "kill" -> {
                    otherMachine.kill(operation.getParameters().get(0));
                }
                default -> {
                }
            }
            OPTMachine.next();

            //TODO: Add simulated processes count to the status update. Attribute already included in model.

            int ramUsageOther = (otherMachine.getPages().size() - otherMachine.virtualMemory.size())*(otherMachine.getPageSize()/1000);

            updateOtherAlgorithmStatus(
                    otherMachine.getPages().values(),
                    otherMachine.getTime(),
                    otherMachine.getTrashing(),
                    ramUsageOther,
                    otherMachine.getVirtualMemory().size()*(otherMachine.getPageSize()/1000),
                    (otherMachine.getTotalMemory()/1000) - ramUsageOther,
                    otherMachine.getProcesses().size(),
                    (otherMachine.getPages().size() - otherMachine.virtualMemory.size()),
                    otherMachine.virtualMemory.size()
            );
            System.out.println("Other " + otherAlgorithmStatus);

            int ramUsageOPT = (OPTMachine.getPages().size() - OPTMachine.virtualMemory.size())*(OPTMachine.getPageSize()/1000);

            updateOptimalAlgorithmStatus(
                    OPTMachine.getPages().values(),
                    OPTMachine.getTime(),
                    OPTMachine.getTrashing(),
                    ramUsageOPT,
                    OPTMachine.getVirtualMemory().size()*(OPTMachine.getPageSize()/1000),
                    (otherMachine.getTotalMemory()/1000) - ramUsageOther,
                    OPTMachine.getProcesses().size(),
                    (OPTMachine.getPages().size() - OPTMachine.virtualMemory.size()),
                    OPTMachine.virtualMemory.size()
            );



            System.out.println("OPT " + optimalAlgorithmStatus);
            Platform.runLater(this::updateSimulationDataOnGUI);
            Thread.sleep(250);
        }
    }

    private void updateSimulationDataOnGUI(){
        SimulationUpdate simulationUpdateOPT = new SimulationUpdate();
        simulationUpdateOPT.setAlgorithmStatusUpdate(optimalAlgorithmStatus);
        ArrayList<Integer> ramUsageMappingOPT = new ArrayList<>();
        for (Integer pageId : OPTMachine.realMemory){
            if(pageId == -1){
                ramUsageMappingOPT.add(pageId);
            }else{
                ramUsageMappingOPT.add(OPTMachine.pages.get(pageId).getPID());
            }
        }
        simulationUpdateOPT.setRAMUsageMapping(ramUsageMappingOPT);

        //TODO: DO THIS SHIT
        simulationUpdateOPT.setRAMUsageTimeline(ramUsageHistorical.toArray());
//        simulationUpdateOPT.setVirtualRAMUsageTimeline();

        Main.UISimulationController.updateOptimalSimulationData(simulationUpdateOPT);

        SimulationUpdate simulationUpdateOther = new SimulationUpdate();
        simulationUpdateOther.setAlgorithmStatusUpdate(otherAlgorithmStatus);
        ArrayList<Integer> ramUsageMappingOther = new ArrayList<>();
        for (Integer pageId : otherMachine.realMemory){
            if(pageId == -1){
                ramUsageMappingOther.add(pageId);
            }else{
                ramUsageMappingOther.add(otherMachine.pages.get(pageId).getPID());
            }
        }

        simulationUpdateOther.setRAMUsageMapping(ramUsageMappingOther);

        Main.UISimulationController.updateOtherSimulationData(simulationUpdateOther);
    }

    public ArrayList<Integer> getAllProcesses(){
        return OPTMachine.getAllProcesses();
    }
}
