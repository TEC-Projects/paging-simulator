package com.pagingsimulator.pagingsimulator.UI.Controller;

import com.pagingsimulator.pagingsimulator.UI.Model.SimulationRequest;
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

import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ScreenController implements Initializable {

    private SimulationRequest simulationRequest = new SimulationRequest();

    private FileChooser fileChooser = new FileChooser();

    private ValidatorUtil validatorUtil = new ValidatorUtil();

    @FXML
    private Button loadOperationsFileButton;
    @FXML
    private Button startSimulationButton;
    @FXML
    private ComboBox<String> pagingAlgorithmsComboBox;
    @FXML
    private ComboBox<String> numberOfOperationsComboBox;
    @FXML
    private ComboBox<String> numberOfProcessesComboBox;

    @FXML
    private TextField randomSeedTextField;
    @FXML
    private TextField operationsFileNameTextField;
    @FXML
    private CheckBox loadFileCheckBox;
    @FXML
    private Label warningLabelText;
    @FXML
    private StackPane warningLabelPane;

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
    private void loadOperationsFileButtonEvent(){
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        simulationRequest.setOperationsFile(fileChooser.showOpenDialog(null));
        operationsFileNameTextField.setText(simulationRequest.getOperationsFile().getName());
    }

    @FXML
    private void loadFileCheckEvent(){
        if(loadFileCheckBox.isSelected()){
            numberOfProcessesComboBox.setDisable(true);
            numberOfOperationsComboBox.setDisable(true);
            loadOperationsFileButton.setDisable(false);
        }else{
            numberOfProcessesComboBox.setDisable(false);
            numberOfOperationsComboBox.setDisable(false);
            loadOperationsFileButton.setDisable(true);
        };
    }

    @FXML
    private void startSimulationButtonEvent(ActionEvent event)throws InterruptedException {

        try{
            validatorUtil.simulationRequestValidator(randomSeedTextField.getText(), simulationRequest.getOperationsFile(), loadFileCheckBox.isSelected() );

            simulationRequest.setRandomSeed(randomSeedTextField.getText());
            simulationRequest.setPagingAlgorithm(pagingAlgorithmsComboBox.getValue());

            if(loadFileCheckBox.isSelected()){
                simulationRequest.setSimulationThroughOperationFile(true);
                simulationRequest.setNumberOfProcesses(Integer.parseInt((numberOfProcessesComboBox.getValue())));
                simulationRequest.setNumberOfOperations(Integer.parseInt((numberOfOperationsComboBox.getValue())));
            }else{
                simulationRequest.setSimulationThroughOperationFile(false);
            }

            Main.sceneManager.navigate(event, "/com/pagingsimulator/pagingsimulator/screens/simulation.fxml", Main.simulationController);

        }catch (Exception e){
            warningLabelPane.setVisible(true);
            warningLabelText.setText(e.getMessage());
            Timeline timer = new Timeline(
                    new KeyFrame(Duration.seconds(2), (ActionEvent aEvent)  -> warningLabelPane.setVisible(false))
            );
            timer.play();
            ;
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        loadFileCheckBox.fire();
    }


}