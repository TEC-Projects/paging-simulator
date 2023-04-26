package com.pagingsimulator.pagingsimulator.UI.Utils;

import com.pagingsimulator.pagingsimulator.Main;
import com.pagingsimulator.pagingsimulator.Model.Operation;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileUtil {

    private FileChooser fileChooser = new FileChooser();


    private ValidatorUtil validatorUtil;

    public FileUtil(){
        validatorUtil = new ValidatorUtil();
    }

    public File loadSimulationFile(){
        fileChooser.setTitle("Load simulation file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));
        return fileChooser.showOpenDialog(null);
    }

    public void generateSimulationFile(int numberOfOperations, int numberOfProcesses, String randomSeed) throws Exception {

        fileChooser.setTitle("Save simulation file");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt"));

        try{
            validatorUtil.randomSeedValidator(randomSeed);
            FileWriter simulationFileWriter = new FileWriter(fileChooser.showSaveDialog(null).getAbsolutePath());
            ArrayList<Operation> simulationOperations = Main.operationsFileManager.generateOperations(
                    Long.parseLong(randomSeed),
                    numberOfOperations,
                    numberOfProcesses);
            simulationFileWriter.write(Main.operationsFileManager.convertOperationsToFileString(simulationOperations));
            simulationFileWriter.close();
        }catch (Exception e){
            throw new Exception("Error generating simulation file");
        }
    }

}
