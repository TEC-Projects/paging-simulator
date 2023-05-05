package com.pagingsimulator.pagingsimulator.UI.Utils;

import javafx.scene.chart.XYChart;
import javafx.scene.shape.Rectangle;
import javafx.util.Pair;

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

    public String simulationSizeFormatter(int numberOfOperations, int numberOfProcesses){
        return numberOfProcesses + " processes & " + numberOfOperations + " operations";
    }

    public XYChart.Series plottingDataFormatter(ArrayList<Pair<Integer, Integer>> plotData){
        XYChart.Series series = new XYChart.Series<>();
        for (Pair<Integer, Integer> point : plotData) {
            series.getData().add(new XYChart.Data<Number, Number>(point.getKey(), point.getValue()));
        }
        return series;
    }

    public ArrayList<Rectangle> RAMUsageMappingFormatter(ArrayList<Integer> RAMUsage){
        ArrayList<Rectangle> tempList = new ArrayList<>();

        for (int i = 1; i < RAMUsage.size(); i++) {
            Rectangle tempPane = new Rectangle(3, 20);
            if(RAMUsage.get(i) > 0){
//                tempPane.setStyle("-fx-fill:" + processColors.get(procedures.get(i)-1) + "; -fx-opacity: 0.8;");
            }else{
                tempPane.setStyle("-fx-fill: #e1e1e1;");
            }

            tempList.add(tempPane);

        }

        return tempList;
    }

}
