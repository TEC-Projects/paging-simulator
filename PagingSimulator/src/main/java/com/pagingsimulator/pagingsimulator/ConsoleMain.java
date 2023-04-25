package com.pagingsimulator.pagingsimulator;

import com.pagingsimulator.pagingsimulator.Controller.Utils.OperationsFileManager;

import java.io.File;
import java.io.IOException;

public class ConsoleMain {
    public static void main(String[] args) throws IOException {
        OperationsFileManager operationsFileManager = new OperationsFileManager();
//        System.out.println(operationsFileManager.retrieveOperationsFromFile(new File("C:\\Users\\joshg\\Documents\\Projects\\paging-simulator\\PagingSimulator\\src\\main\\java\\com\\pagingsimulator\\pagingsimulator\\op.txt")));
        System.out.println(operationsFileManager.convertOperationsToFileString(operationsFileManager.generateOperations(123, 200, 10)));
    }
}
