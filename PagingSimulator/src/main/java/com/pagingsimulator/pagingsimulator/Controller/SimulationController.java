package com.pagingsimulator.pagingsimulator.Controller;

import com.pagingsimulator.pagingsimulator.Controller.Utils.DummyDataUtil;
import com.pagingsimulator.pagingsimulator.Controller.Utils.OperationsFileManager;
import com.pagingsimulator.pagingsimulator.Main;
import com.pagingsimulator.pagingsimulator.Model.Operation;
import com.pagingsimulator.pagingsimulator.Model.Simulation;
import com.pagingsimulator.pagingsimulator.UI.Model.SimulationRequest;

import java.io.IOException;
import java.util.ArrayList;

public class SimulationController {
    private Simulation simulation;
    private OperationsFileManager operationsFileManager;
    private DummyDataUtil dummyDataUtil;

    public SimulationController(){
        operationsFileManager = new OperationsFileManager();
        dummyDataUtil = new DummyDataUtil();
    }
    public void initializeSimulation(SimulationRequest simulationRequest) throws IOException {

        ArrayList<Operation> operations;
        if(simulationRequest.isSimulationThroughOperationFile()){
            operations = operationsFileManager.generateOperations(simulationRequest.getRandomSeed(), simulationRequest.getNumberOfOperations(), simulationRequest.getNumberOfProcesses());
        }else{
            operations = operationsFileManager.retrieveOperationsFromFile(simulationRequest.getOperationsFile());
        }
        simulation = new Simulation(
                simulationRequest.getPagingAlgorithm(),
                simulationRequest.getRandomSeed(),
                simulationRequest.getNumberOfOperations(),
                simulationRequest.getNumberOfProcesses(),
                operations,
                simulationRequest.isSimulationThroughOperationFile());

        //TODO: Extract the list of PIDs to initialize GUI. Right now filled by dummy data.
        Main.UISimulationController.initializeSimulationDetails(
                simulationRequest.getPagingAlgorithm(),
                simulationRequest.isSimulationThroughOperationFile(),
                simulationRequest.getNumberOfOperations(),
                simulationRequest.getNumberOfProcesses(),
                dummyDataUtil.getPIDs());

    }

    public void startSimulation(){
        new Thread(() -> {
            try {
                simulation.runSimulation();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }

    public void pauseSimulation(){
        simulation.setPaused(!simulation.isPaused());
    }
}
