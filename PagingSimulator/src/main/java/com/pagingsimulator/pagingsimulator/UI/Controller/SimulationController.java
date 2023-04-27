package com.pagingsimulator.pagingsimulator.UI.Controller;

import com.pagingsimulator.pagingsimulator.UI.Utils.SimulationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class SimulationController extends ScreenController implements Initializable {

    private SimulationUtil simulationUtil = new SimulationUtil();
    private ArrayList<String> processColors = new ArrayList<>();

    @FXML
    private Label
            simulationTitleLabel,
            simulationDetailLabel;

    @FXML
    private Button
            pauseSimulationButton;
    @FXML
    private NumberAxis
            optimalXAxis,
            otherXAxis;

    @FXML
    private AreaChart<NumberAxis, NumberAxis>
            optimalRAMChart,
            otherRAMChart;

    @FXML
    private HBox
            optimalRAMDistribution,
            otherRAMDistribution;



    @FXML
    private void pauseSimulationButtonEvent(){

    }

    private void initializeRamUsageCharts(){

        optimalXAxis.setAutoRanging(false);
        optimalXAxis.setLowerBound(0);
        optimalXAxis.setUpperBound(60);
        optimalXAxis.setTickUnit(5);

        otherXAxis.setAutoRanging(false);
        otherXAxis.setLowerBound(0);
        otherXAxis.setUpperBound(60);
        otherXAxis.setTickUnit(5);

        otherXAxis.setAutoRanging(false);
        otherXAxis.setLowerBound(0);
        otherXAxis.setUpperBound(60);
        otherXAxis.setTickUnit(5);

        ArrayList<Integer> procedures = new ArrayList<Integer>();


        Random rand = new Random();

        for (int i = 1; i < 101; i++) {
            procedures.add(rand.nextInt(50) - 25);
        }

        for (int i = 1; i < procedures.size(); i++) {
            Rectangle tempPane = new Rectangle(3, 20);
            if(procedures.get(i) > 0){
                tempPane.setStyle("-fx-fill:" + processColors.get(procedures.get(i)-1) + "; -fx-opacity: 0.8;");
            }else{
                tempPane.setStyle("-fx-fill: #e1e1e1;");
            }
            optimalRAMDistribution.getChildren().add(tempPane);
        }

        for (int i = 1; i < 101; i++) {
            Rectangle tempPane = new Rectangle(3, 20);
            if(i % 5 == 0){
                tempPane.getStyleClass().setAll("test");
            }
            otherRAMDistribution.getChildren().add(tempPane);
        }



//        XYChart.Series seriesApril= new XYChart.Series();
//        seriesApril.setName("April");
//        seriesApril.getData().add(new XYChart.Data(1, 102));
//        seriesApril.getData().add(new XYChart.Data(2, 340));
//        seriesApril.getData().add(new XYChart.Data(3, 405));
//        seriesApril.getData().add(new XYChart.Data(4, 408));
//        seriesApril.getData().add(new XYChart.Data(5, 415));
//        seriesApril.getData().add(new XYChart.Data(6, 400));
//        seriesApril.getData().add(new XYChart.Data(7, 460));
//
//        optimalRAMChart.getData().addAll(seriesApril);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processColors = simulationUtil.getProcessesColors(50);
        initializeRamUsageCharts();
    }
}