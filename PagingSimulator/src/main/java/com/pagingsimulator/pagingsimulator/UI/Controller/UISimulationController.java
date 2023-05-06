package com.pagingsimulator.pagingsimulator.UI.Controller;

import com.pagingsimulator.pagingsimulator.Model.Page;
import com.pagingsimulator.pagingsimulator.Model.PagingAlgorithmSimulationStatus;
import com.pagingsimulator.pagingsimulator.UI.Model.SimulationUpdate;
import com.pagingsimulator.pagingsimulator.UI.Utils.DummyDataUtil;
import com.pagingsimulator.pagingsimulator.UI.Utils.SimulationUtil;
import com.pagingsimulator.pagingsimulator.UI.Utils.SnackBarUtil;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.TextAlignment;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;

public class UISimulationController extends ScreenController implements Initializable {

    private SimulationUtil simulationUtil = new SimulationUtil();
    private DummyDataUtil dummyDataUtil = new DummyDataUtil();
    private final SnackBarUtil snackBarUtil = new SnackBarUtil();
    private HashMap<Integer, String> processColors = new HashMap<>();
    private int numberOfProcesses;
    private boolean isPaused;
    private boolean hasStarted;
    @FXML
    private TableView<Page>
            optimalMMUTable,
            otherMMUTable;
    @FXML
    private AreaChart<NumberAxis, NumberAxis>
            optimalRAMChart,
            otherRAMChart,
            optimalVirtualRAMChart,
            otherVirtualRAMChart;
    @FXML
    private HBox
            otherRAMDistribution,
            optimalRAMDistribution;

    @FXML
    private StackPane snackBarPane;

    @FXML
    private Label
            pagingAlgorithmLabel,
            simulationSizeLabel,
            otherRAMUsageKB,
            otherRAMUsagePercentage,
            otherSimulatedProcesses,
            otherSimulationTime,
            otherThrashingLevelSeconds,
            otherThrashingPercentage,
            otherUnloadedPages,
            otherVirtualRAMUsageKB,
            otherVirtualRAMUsagePercentage,
            otherFragmentation,
            otherFragmentationPercentage,
            otherLoadedPages,
            optimalRAMUsageKB,
            optimalRAMUsagePercentage,
            optimalSimulatedProcesses,
            optimalSimulationTime,
            optimalThrashingLevelSeconds,
            optimalThrashingPercentage,
            optimalUnloadedPages,
            optimalVirtualRAMUsageKB,
            optimalVirtualRAMUsagePercentage,
            optimalFragmentation,
            optimalFragmentationPercentage,
            optimalLoadedPages,
            snackBarMessage;
    @FXML
    private NumberAxis
            optimalRAMXAxis,
            optimalRAMYAxis,
            optimalVirtualRAMXAxis,
            optimalVirtualRAMYAxis,
            otherRAMXAxis,
            otherRAMYAxis,
            otherVirtualRAMXAxis,
            otherVirtualRAMYAxis;
    @FXML
    private Button generalSimulationButton;


    @FXML
    private void generalSimulationButtonEvent(){
        if(!hasStarted){
            handleStartSimulation();
        }else{
            if(isPaused){
                handleResumeSimulation();
            }else{
                handlePauseSimulation();
            }
        }

        updateOptimalSimulationData(dummyDataUtil.getDummyOptimalSimulationUpdate());
        updateOtherSimulationData(dummyDataUtil.getDummyOtherSimulationUpdate());

    }

    public void handleSimulationCompleted(){
        snackBarUtil.showSnackBar("Simulation completed", "success", snackBarPane, snackBarMessage, false);
        generalSimulationButton.setVisible(false);
    }

    private void handlePauseSimulation(){
        //TODO: Call simulation pause function
        snackBarUtil.showSnackBar("Simulation paused", "info", snackBarPane, snackBarMessage, false);
        isPaused = true;
        generalSimulationButton.getStyleClass().setAll("btn", "btn-primary");
        generalSimulationButton.setText("RESUME SIMULATION");
    }

    private void handleStartSimulation(){
        //TODO: Call simulation start function
        hasStarted = true;
        generalSimulationButton.getStyleClass().setAll("btn", "btn-danger");
        generalSimulationButton.setText("PAUSE SIMULATION");
    }

    private void handleResumeSimulation(){
        //TODO: Call simulation resume function
        snackBarUtil.hideSnackBar(snackBarPane);
        isPaused = false;
        generalSimulationButton.getStyleClass().setAll("btn", "btn-danger");
        generalSimulationButton.setText("PAUSE SIMULATION");
    }


    private void updateOtherSimulationData(SimulationUpdate simulationUpdate){

    }

    private void updateOptimalSimulationData(SimulationUpdate simulationUpdate){
//        optimalRAMUsageKB.setText(String.valueOf(simulationUpdate.getAlgorithmStatusUpdate().getRamUsage()));
//        optimalFragmentation.setText(String.valueOf(simulationUpdate.getAlgorithmStatusUpdate().getInternalFragmentationVolume()));
//        optimalVirtualRAMUsageKB.setText(String.valueOf(simulationUpdate.getAlgorithmStatusUpdate().getVRamUsage()));
//        optimalThrashingLevelSeconds.setText(String.valueOf(simulationUpdate.getAlgorithmStatusUpdate().getThrashingTime()));

        optimalRAMChart.getData().addAll(simulationUtil.plottingDataFormatter(simulationUpdate.getRAMUsageTimeline()));
        optimalVirtualRAMChart.getData().addAll(simulationUtil.plottingDataFormatter(simulationUpdate.getVirtualRAMUsageTimeline()));

        optimalRAMDistribution.getChildren().addAll(simulationUtil.RAMUsageMappingFormatter(simulationUpdate.getRAMUsageMapping(), processColors));

    }

    private void setMMUTableColumns(TableView<Page> table){
        TableColumn<Page,Integer> pageIdColumn = new TableColumn<>("Page ID");
        pageIdColumn.setCellValueFactory(new PropertyValueFactory<Page, Integer>("pageId"));

        TableColumn<Page,Integer> PIDColumn = new TableColumn<>("PID");
        PIDColumn.setCellValueFactory(new PropertyValueFactory<Page, Integer>("PID"));

        TableColumn<Page,String> loadedColumn = new TableColumn<>("Loaded");
        loadedColumn.setCellValueFactory(new PropertyValueFactory<Page, String>("loaded"));

        TableColumn<Page,Integer> lAddressColumn = new TableColumn<>("L-add");
        lAddressColumn.setCellValueFactory(new PropertyValueFactory<Page, Integer>("logicalAddress"));

        TableColumn<Page,Integer> mAddressColumn = new TableColumn<>("M-add");
        mAddressColumn.setCellValueFactory(new PropertyValueFactory<Page, Integer>("memoryAddress"));

        TableColumn<Page,Integer> dAddressColumn = new TableColumn<>("D-add");
        dAddressColumn.setCellValueFactory(new PropertyValueFactory<Page, Integer>("diskAddress"));

        TableColumn<Page,Integer> loadTimeColumn = new TableColumn<>("L-Time");
        loadTimeColumn.setCellValueFactory(new PropertyValueFactory<Page, Integer>("loadTime"));

        TableColumn<Page,String> markColumn = new TableColumn<>("Mark");
        markColumn.setCellValueFactory(new PropertyValueFactory<Page, String>("mark"));

        table.getColumns().addAll(
                pageIdColumn,
                PIDColumn,
                loadedColumn,
                lAddressColumn,
                mAddressColumn,
                dAddressColumn,
                loadTimeColumn,
                markColumn
        );

    }

    private void initializeMMUTables(){

        setMMUTableColumns(optimalMMUTable);
        setMMUTableColumns(otherMMUTable);

    }

    private void initializeRAMUsageCharts(){

        configurePlotXAxis(optimalRAMXAxis);
        configurePlotXAxis(optimalVirtualRAMXAxis);
        configurePlotXAxis(otherRAMXAxis);
        configurePlotXAxis(otherVirtualRAMXAxis);

    }

    private void configurePlotXAxis(NumberAxis axis){
        axis.setAutoRanging(false);
        axis.setLowerBound(0);
        axis.setUpperBound(60);
        axis.setTickUnit(5);
    }

    public void initializeSimulationDetails(String pagingAlgorithm, int numberOfOperations, int numberOfProcesses, ArrayList<Integer> PIDs){
        this.numberOfProcesses = numberOfProcesses;
        processColors = simulationUtil.generateProcessesColors(PIDs);
        pagingAlgorithmLabel.setText(pagingAlgorithm);
        simulationSizeLabel.setText(simulationUtil.simulationSizeFormatter(numberOfOperations, numberOfProcesses));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        isPaused = false;
        hasStarted = false;
        initializeRAMUsageCharts();
        initializeMMUTables();
    }
}