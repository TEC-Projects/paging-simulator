package com.pagingsimulator.pagingsimulator.UI.Utils;

import java.util.ArrayList;
import java.util.Random;

public class SimulationUtil {

    public SimulationUtil() {
    }

    public ArrayList<String> getProcessesColors(int numberOfProcesses){

        ArrayList<String> processesColors = new ArrayList<>();

        Random rand = new Random();

        for (int i = 0; i < numberOfProcesses; i++) {
            processesColors.add(String.format("#%06x", rand.nextInt(0xffffff + 1)));
        }

        return processesColors;

    };

}
