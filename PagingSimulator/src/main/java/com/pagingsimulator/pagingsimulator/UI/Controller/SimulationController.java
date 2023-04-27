package com.pagingsimulator.pagingsimulator.UI.Controller;

import com.pagingsimulator.pagingsimulator.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SimulationController extends ScreenController implements Initializable {

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
    private void pauseSimulationButtonEvent(){

    }

    private void initializeRamUsageCharts(){
        optimalRAMChart.setTitle("RAM usage");
        otherRAMChart.setTitle("RAM usage");

        optimalXAxis.setAutoRanging(false);
        optimalXAxis.setLowerBound(0);
        optimalXAxis.setUpperBound(60);
        optimalXAxis.setTickUnit(5);

        otherXAxis.setAutoRanging(false);
        otherXAxis.setLowerBound(0);
        otherXAxis.setUpperBound(60);
        otherXAxis.setTickUnit(5);


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
        initializeRamUsageCharts();
    }
}