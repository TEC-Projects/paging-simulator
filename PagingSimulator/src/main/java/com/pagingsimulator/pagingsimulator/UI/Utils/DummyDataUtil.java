package com.pagingsimulator.pagingsimulator.UI.Utils;

import com.pagingsimulator.pagingsimulator.Model.Page;
import com.pagingsimulator.pagingsimulator.Model.PagingAlgorithmSimulationStatus;
import com.pagingsimulator.pagingsimulator.UI.Model.SimulationUpdate;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;

public class DummyDataUtil {
    SimulationUpdate dummyOptimalSimulationUpdate;
    SimulationUpdate dummyOtherSimulationUpdate;
    Random rand;

    public DummyDataUtil(){

        rand = new Random();

        dummyOptimalSimulationUpdate = new SimulationUpdate();
        dummyOtherSimulationUpdate = new SimulationUpdate();

        fillSimulationUpdateDummy(false, dummyOptimalSimulationUpdate);
        fillSimulationUpdateDummy(true, dummyOtherSimulationUpdate);
    }

    private void fillSimulationUpdateDummy(boolean withMark, SimulationUpdate simulationUpdate){
        LinkedList<Page> dummySimulationPages = new LinkedList<>();

        for (int i = 0; i < 10; i++) {

            int currentPID =  rand.nextInt(5);
            boolean isLoaded = i % 2 == 0;
            int memoryAddress = isLoaded ? rand.nextInt(10) : -1;
            int diskAddress = !isLoaded ? rand.nextInt(100) : -1;
            int loadTime = isLoaded ? rand.nextInt(300) : -1;
            int mark;

            if(withMark){
                mark = rand.nextInt(100) < 50 ? rand.nextInt(300) : -1;
            }else {
                mark = -1;
            }

            dummySimulationPages.add(new Page(i, currentPID, diskAddress, loadTime, mark));
        }

        PagingAlgorithmSimulationStatus simulationStatus = new PagingAlgorithmSimulationStatus(dummySimulationPages);
        simulationStatus.setSimulationElapsedTime(rand.nextInt(500));
        simulationStatus.setThrashingTime(rand.nextInt(200));
        simulationStatus.setRamUsage(rand.nextInt(500));
        simulationStatus.setVRamUsage(rand.nextInt(1000));
        simulationStatus.setInternalFragmentationVolume(rand.nextInt(300));

        ArrayList<Pair<Integer, Integer>> RAMTimeline = new ArrayList<>();
        ArrayList<Pair<Integer, Integer>> VirtualRAMTimeline = new ArrayList<>();

        for (int i = 0; i < 16; i++) {
            RAMTimeline.add(new Pair<>(i, i+20));
            VirtualRAMTimeline.add(new Pair<>(i, i+1));
        }

        ArrayList<Integer> RAMMapping = new ArrayList<Integer>();

        Random rand = new Random();

        for (int i = 1; i < 50 + 1; i++) {
            RAMMapping.add(rand.nextInt(50) - 25);
        }


        simulationUpdate.setAlgorithmStatusUpdate(simulationStatus);
        simulationUpdate.setRAMUsageMapping(RAMMapping);
        simulationUpdate.setRAMUsageTimeline(RAMTimeline);
        simulationUpdate.setVirtualRAMUsageTimeline(VirtualRAMTimeline);

    }

    public SimulationUpdate getDummyOptimalSimulationUpdate() {
        return dummyOptimalSimulationUpdate;
    }

    public SimulationUpdate getDummyOtherSimulationUpdate() {
        return dummyOtherSimulationUpdate;
    }

}


