package com.pagingsimulator.pagingsimulator.UI.Controller;

import com.pagingsimulator.pagingsimulator.Model.Page;
import com.pagingsimulator.pagingsimulator.Model.PagingAlgorithmSimulationStatus;
import com.pagingsimulator.pagingsimulator.UI.Utils.DummyDataUtil;
import com.pagingsimulator.pagingsimulator.UI.Utils.SimulationUtil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

public class UISimulationController extends ScreenController implements Initializable {

    private SimulationUtil simulationUtil = new SimulationUtil();
    private DummyDataUtil dummyDataUtil = new DummyDataUtil();
    private ArrayList<String> processColors = new ArrayList<>();


    @FXML
    private TableView<Page>
            optimalMMUTable,
            otherMMUTable;
    @FXML
    private AreaChart<NumberAxis, NumberAxis>
            optimalRAMChart,
            otherRAMChart;
    @FXML
    private HBox
            otherRAMDistribution,
            optimalRAMDistribution;
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
            optimalLoadedPages;
    @FXML
    private NumberAxis
            otherXAxis,
            optimalXAxis;
    @FXML
    private Button
            pauseSimulationButton,
            testFillButton;


    @FXML
    private void pauseSimulationButtonEvent(){

    }

    @FXML
    private void testFillButtonButtonEvent(){
        updateOtherSimulationData(dummyDataUtil.getDummyOtherSimulationStatus());
        updateOptimalSimulationData(dummyDataUtil.getDummyOptimalSimulationStatus());
    }


    private void updateOtherSimulationData(PagingAlgorithmSimulationStatus simulationStatus){

    }

    private void updateOptimalSimulationData(PagingAlgorithmSimulationStatus simulationStatus){
        optimalRAMUsageKB.setText(String.valueOf(simulationStatus.getRamUsage()));
        optimalFragmentation.setText(String.valueOf(simulationStatus.getInternalFragmentationVolume()));
        optimalVirtualRAMUsageKB.setText(String.valueOf(simulationStatus.getVRamUsage()));
        optimalThrashingLevelSeconds.setText(String.valueOf(simulationStatus.getThrashingTime()));
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

        optimalXAxis.setAutoRanging(false);
        optimalXAxis.setLowerBound(0);
        optimalXAxis.setUpperBound(60);
        optimalXAxis.setTickUnit(5);

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

    public void initializeSimulationDetails(String pagingAlgorithm, int numberOfOperations, int numberOfProcesses){
        pagingAlgorithmLabel.setText(pagingAlgorithm);
        simulationSizeLabel.setText(simulationUtil.simulationSizeFormatter(numberOfOperations, numberOfProcesses));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        processColors = simulationUtil.getProcessesColors(50);
        initializeRAMUsageCharts();
        initializeMMUTables();
    }
}