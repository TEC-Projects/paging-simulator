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

    }
    public void initializeSimulation(SimulationRequest simulationRequest) throws IOException {
        if(simulationRequest.getOperationsFile() == null){
            ArrayList<Operation> operations = operationsFileManager.generateOperations(simulationRequest.getRandomSeed(), simulationRequest.getNumberOfOperations(), simulationRequest.getNumberOfProcesses());
        }else{
            ArrayList<Operation> operations = operationsFileManager.retrieveOperationsFromFile(simulationRequest.getOperationsFile());
        }
        simulation = new Simulation(
                simulationRequest.getPagingAlgorithm(),
                simulationRequest.getRandomSeed(),
                simulationRequest.getNumberOfOperations(),
                simulationRequest.getNumberOfProcesses());
    }
}
