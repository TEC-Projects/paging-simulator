package com.pagingsimulator.pagingsimulator.UI.Utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.util.Objects;

public class SnackBarUtil {

    public SnackBarUtil(){}

    public void showSnackBar(String message, String type, StackPane snackBarPane, Label snackBarLabel){
        if(Objects.equals(type, "warning")){
            snackBarPane.getStyleClass().setAll("alert-warning", "alert");
        }else{
            snackBarPane.getStyleClass().setAll("alert-success", "alert");
        }
        snackBarPane.setVisible(true);
        snackBarLabel.setText(message);
        Timeline timer = new Timeline(
                new KeyFrame(Duration.seconds(2), (ActionEvent aEvent)  -> snackBarPane.setVisible(false))
        );
        timer.play();
    }


}
