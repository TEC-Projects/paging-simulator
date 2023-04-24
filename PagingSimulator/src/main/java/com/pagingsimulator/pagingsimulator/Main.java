package com.pagingsimulator.pagingsimulator;

import com.pagingsimulator.pagingsimulator.UI.Controllers.MainMenuController;
import com.pagingsimulator.pagingsimulator.UI.Controllers.SimulationController;
import com.pagingsimulator.pagingsimulator.UI.Utils.SceneManager;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static MainMenuController mainMenuController;
    public static SimulationController simulationController;
    public static SceneManager sceneManager = new SceneManager();

    @Override
    public void start(Stage stage) throws IOException {
        sceneManager.loadScene(stage, "/com/pagingsimulator/pagingsimulator/screens/mainMenu.fxml");
    }

    public static void main(String[] args) {
        launch(args);
    }
}