package com.pagingsimulator.pagingsimulator.UI.Utils;

import com.pagingsimulator.pagingsimulator.UI.Controllers.ScreenController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import com.pagingsimulator.pagingsimulator.Main;

import java.io.IOException;

public class SceneManager {

    public SceneManager() {
    }

    public void loadScene(Stage stage, String screenRoute) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(screenRoute));
        stage.setTitle("Paging simulator");
        Parent root = loader.load();
        Main.mainMenuController = loader.getController();
        stage.getIcons().add(new Image(getClass().getResource("/com/pagingsimulator/pagingsimulator/img/isotype.png").toString()));
        stage.setScene(new Scene(root, 900, 550));
        stage.show();
    }

    public void navigate(ActionEvent event, String screenRoute, ScreenController controller) throws IOException {
        FXMLLoader viewHandler = new FXMLLoader(getClass().getResource(screenRoute));
        Parent root = viewHandler.load();
        controller = viewHandler.getController();
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(new Scene(root, 900, 550));
        window.show();
    }

}
