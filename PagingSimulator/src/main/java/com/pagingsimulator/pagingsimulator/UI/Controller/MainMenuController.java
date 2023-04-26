package com.pagingsimulator.pagingsimulator.UI.Controller;

import com.pagingsimulator.pagingsimulator.UI.Model.SimulationRequest;
import com.pagingsimulator.pagingsimulator.UI.Utils.FileUtil;
import com.pagingsimulator.pagingsimulator.UI.Utils.SnackBarUtil;
import com.pagingsimulator.pagingsimulator.UI.Utils.ValidatorUtil;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.pagingsimulator.pagingsimulator.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ScreenController implements Initializable {

    private SimulationRequest simulationRequest = new SimulationRequest();

    private ValidatorUtil validatorUtil = new ValidatorUtil();
    private FileUtil fileUtil = new FileUtil();
    private SnackBarUtil snackBarUtil = new SnackBarUtil();

    @FXML
    private Button
            loadOperationsFileButton,
            startSimulationButton,
            downloadSimulationFileButton;
    @FXML
    private ComboBox<String>
            pagingAlgorithmsComboBox,
            numberOfOperationsComboBox,
            numberOfProcessesComboBox;

    @FXML
    private TextField
            randomSeedTextField,
            operationsFileNameTextField;
    @FXML
    private CheckBox loadFileCheckBox;
    @FXML
    private Label snackBarMessage;
    @FXML
    private StackPane snackBarPane;

    private void initializeComboBoxes() {
        pagingAlgorithmsComboBox.getItems().addAll("FIFO", "Second Chance", "Most recently used", "Random");
        numberOfOperationsComboBox.getItems().addAll("500", "1000", "5000");
        numberOfProcessesComboBox.getItems().addAll("10", "50", "100");

        pagingAlgorithmsComboBox.setValue("FIFO");
        numberOfOperationsComboBox.setValue("500");
        numberOfProcessesComboBox.setValue("10");

        numberOfProcessesComboBox.setDisable(true);
        numberOfOperationsComboBox.setDisable(true);

    }

    @FXML
    private void loadFileCheckEvent(){
        if(loadFileCheckBox.isSelected()){
            numberOfProcessesComboBox.setDisable(true);
            numberOfOperationsComboBox.setDisable(true);
            downloadSimulationFileButton.setDisable(true);
            loadOperationsFileButton.setDisable(false);
            randomSeedTextField.setDisable(!pagingAlgorithmsComboBox.getValue().equals("Random"));
        }else{
            numberOfProcessesComboBox.setDisable(false);
            numberOfOperationsComboBox.setDisable(false);
            downloadSimulationFileButton.setDisable(false);
            randomSeedTextField.setDisable(false);
            loadOperationsFileButton.setDisable(true);
        };
    }

    @FXML
    private void pagingAlgorithmsComboBoxSelected(){
        randomSeedTextField.setDisable(!pagingAlgorithmsComboBox.getValue().equals("Random") && loadFileCheckBox.isSelected());
    }

    @FXML
    private void loadOperationsFileButtonEvent(){
        try{
            simulationRequest.setOperationsFile(fileUtil.loadSimulationFile());
            operationsFileNameTextField.setText(simulationRequest.getOperationsFile().getName());
        }catch (Exception e){
            snackBarUtil.showSnackBar(e.getMessage(), "warning", snackBarPane, snackBarMessage);
        }
    }

    @FXML
    private void downloadSimulationFileButtonEvent() {
        try{
            fileUtil.generateSimulationFile(
                    Integer.parseInt(numberOfOperationsComboBox.getValue()),
                    Integer.parseInt(numberOfProcessesComboBox.getValue()),
                    randomSeedTextField.getText()
            );
            snackBarUtil.showSnackBar("Document generated successfully.", "success", snackBarPane, snackBarMessage);
        }catch (Exception e){
            snackBarUtil.showSnackBar(e.getMessage(), "warning", snackBarPane, snackBarMessage);
        }
    }

    @FXML
    private void startSimulationButtonEvent(ActionEvent event) throws InterruptedException, IOException {
        try{
            simulationRequest.setPagingAlgorithm(pagingAlgorithmsComboBox.getValue());

            if(loadFileCheckBox.isSelected()){
                validatorUtil.fileLoadValidator(simulationRequest.getOperationsFile());
                simulationRequest.setSimulationThroughOperationFile(true);

                if(pagingAlgorithmsComboBox.getValue().equals("Random")){
                    validatorUtil.randomSeedValidator(randomSeedTextField.getText());
                    simulationRequest.setRandomSeed(Long.valueOf(randomSeedTextField.getText()));
                }

            }else{
                validatorUtil.randomSeedValidator(randomSeedTextField.getText());
                simulationRequest.setNumberOfProcesses(Integer.parseInt((numberOfProcessesComboBox.getValue())));
                simulationRequest.setNumberOfOperations(Integer.parseInt((numberOfOperationsComboBox.getValue())));
                simulationRequest.setRandomSeed(Long.valueOf(randomSeedTextField.getText()));
                simulationRequest.setSimulationThroughOperationFile(false);
            }


        }catch (Exception e){
            snackBarUtil.showSnackBar(e.getMessage(), "warning", snackBarPane, snackBarMessage);
        }

        Main.sceneManager.navigate(event, "/com/pagingsimulator/pagingsimulator/screens/simulation.fxml", Main.simulationController);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        loadFileCheckBox.fire();
    }

}