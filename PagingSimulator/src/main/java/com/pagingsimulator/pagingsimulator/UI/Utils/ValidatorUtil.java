package com.pagingsimulator.pagingsimulator.UI.Utils;

import java.io.File;

public class ValidatorUtil {

    public ValidatorUtil(){};

    public void simulationRequestValidator(String randomSeed, File simulationFileRoute, Boolean isFileLoaded) throws Exception {
        try{
            Long.parseLong(randomSeed);
        }catch (Exception e){
            throw new Exception("Invalid random seed.");
        }
        if(isFileLoaded && simulationFileRoute == null){
            throw new Exception("A simulation file must be selected.");
        }
    }
}
