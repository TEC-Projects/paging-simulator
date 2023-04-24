package com.pagingsimulator.pagingsimulator.UI.Controllers;

import com.pagingsimulator.pagingsimulator.Model.Simulation;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import com.pagingsimulator.pagingsimulator.Main;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController extends ScreenController implements Initializable {

    private Simulation simulation = new Simulation();

    private FileChooser fileChooser = new FileChooser();

    @FXML
    private Button loadOperationsFileButton;
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
    protected void navigateToSimulationButtonEvent(ActionEvent event) throws IOException {
        Main.sceneManager.navigate(event, "/com/pagingsimulator/pagingsimulator/screens/simulation.fxml", Main.simulationController);
    }

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
        simulation.setOperationsFile(fileChooser.showOpenDialog(null));
        operationsFileNameTextField.setText(simulation.getOperationsFile().getName());
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeComboBoxes();
        loadFileCheckBox.fire();
    }


}