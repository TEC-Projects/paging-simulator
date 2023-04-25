package com.pagingsimulator.pagingsimulator.UI.Controller;

import com.pagingsimulator.pagingsimulator.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class SimulationController extends ScreenController {


    @FXML
    protected void navigateToMainMenuButtonEvent(ActionEvent event) throws IOException {
        Main.sceneManager.navigate(event, "/com/pagingsimulator/pagingsimulator/screens/mainMenu.fxml", Main.mainMenuController);
    }
}