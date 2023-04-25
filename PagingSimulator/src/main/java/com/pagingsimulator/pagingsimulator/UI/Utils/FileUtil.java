package com.pagingsimulator.pagingsimulator.UI.Utils;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtil {

    public FileUtil(){}


    public File loadSimulationFile(FileChooser fileChooser){
        fileChooser.setTitle("Load simulation file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        return fileChooser.showOpenDialog(null);
    }

    public void generateSimulationFile(FileChooser fileChooser) throws Exception {
        fileChooser.setTitle("Save simulation file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        File newSimulationFile = new File(fileChooser.showSaveDialog(null).getAbsolutePath());
        try{
            FileWriter simulationFileWriter = new FileWriter(newSimulationFile);
            simulationFileWriter.write("HOLA");
            simulationFileWriter.close();
        }catch (IOException e){
            throw new Exception("Error generating simulation file");
        }
    }

}
