package com.pagingsimulator.pagingsimulator.UI.Utils;

import com.pagingsimulator.pagingsimulator.Model.Page;
import com.pagingsimulator.pagingsimulator.Model.PagingAlgorithmSimulationStatus;

import java.util.LinkedList;
import java.util.Random;

public class DummyDataUtil {
    PagingAlgorithmSimulationStatus dummyOptimalSimulationStatus;
    PagingAlgorithmSimulationStatus dummyOtherSimulationStatus;
    Random rand;

    public DummyDataUtil(){

        rand = new Random();

        fillSimulationDummy(false, dummyOptimalSimulationStatus);
        fillSimulationDummy(true, dummyOtherSimulationStatus);
    }

    private void fillSimulationDummy(boolean withMark, PagingAlgorithmSimulationStatus simulationStatus){
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

        simulationStatus = new PagingAlgorithmSimulationStatus(dummySimulationPages);
        simulationStatus.setSimulationElapsedTime(rand.nextInt(500));
        simulationStatus.setThrashingTime(rand.nextInt(200));
        simulationStatus.setRamUsage(rand.nextInt(500));
        simulationStatus.setVRamUsage(rand.nextInt(1000));
        simulationStatus.setInternalFragmentationVolume(rand.nextInt(500));

    }

    public PagingAlgorithmSimulationStatus getDummyOptimalSimulationStatus() {
        return dummyOptimalSimulationStatus;
    }
    public PagingAlgorithmSimulationStatus getDummyOtherSimulationStatus() {
        return dummyOtherSimulationStatus;
    }

}


