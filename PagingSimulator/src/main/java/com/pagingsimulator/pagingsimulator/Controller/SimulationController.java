package com.pagingsimulator.pagingsimulator.Controller;

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
    public SimulationController(){
        operationsFileManager = new OperationsFileManager();
    }
    public void initializeSimulation(SimulationRequest simulationRequest) throws IOException {

//        Main.UISimulationController.initializeSimulationDetails(simulationRequest.getPagingAlgorithm(), simulationRequest.getNumberOfOperations(), simulationRequest.getNumberOfProcesses());
        ArrayList<Operation> operations;
        if(simulationRequest.getOperationsFile() == null){
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
                simulationRequest.getOperationsFile() == null);
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
