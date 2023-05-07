package com.pagingsimulator.pagingsimulator.Model;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.Future;

public class OptimalMachine extends Machine{
    private ArrayList<Integer> allProcesses;
    private HashMap<Integer, Queue<Integer>> earliestAccessToPages;
    private int currentInstruction;
    private ArrayList<Operation> operations;
    private ArrayList<Page> futurePages;
    private HashMap<Integer, ArrayList<Integer>> futureMemoryMap;

    public OptimalMachine(int totalMemory, int pageSize, ArrayList<Operation> operations) {
        super(totalMemory, pageSize);
        this.operations = operations;
        currentInstruction = 0;
        earliestAccessToPages = new HashMap<>();
        futurePages = new ArrayList<>();
        futureMemoryMap = new HashMap<>();
        allProcesses = new ArrayList<>();

        int pageCount = 0;
        int ptrCount = 0;
        int instructionCount = 0;
        for (Operation operation : operations) {
            switch (operation.getName()) {
                case "new" -> {
                    if(!allProcesses.contains(operation.getParameters().get(0))){
                        allProcesses.add(operation.getParameters().get(0));
                    }

                    ArrayList<Integer> createdPages = new ArrayList<>();

                    for (int i = 0; i < Math.ceil(operation.getParameters().get(1)/4000.0); i++) {
                        Page page = new Page(pageCount, operation.getParameters().get(0), -1, -1,-1, -1);
                        futurePages.add(page);
                        createdPages.add(pageCount);
                        earliestAccessToPages.put(pageCount++, new PriorityQueue<>() {
                        });
                    }

                    futureMemoryMap.put(ptrCount++, createdPages);
                }
                case "use" -> {
                    for(Integer pageId : futureMemoryMap.get(operation.getParameters().get(0))){
                        earliestAccessToPages.get(pageId).add(instructionCount);
                    }
                }
                case "delete" -> {
                    //TODO: DUNNO IF I NEED TO DO SOMETHING
                }
                case "kill" -> {
                    //TODO: DUNNO IF I NEED TO DO SOMETHING
                }
                default -> {
                }
            }
            instructionCount++;
        }
    }

    public void next(){
        Operation operation = operations.get(currentInstruction);
        switch (operation.getName()) {
            case "new" -> {
                newAlloc(operation.getParameters().get(0), operation.getParameters().get(1));
            }
            case "use" -> {
                use(operation.getParameters().get(0));
                for(Queue<Integer> uses : earliestAccessToPages.values()){
                    if(uses.isEmpty()){
                        continue;
                    }
                    if(uses.peek() == currentInstruction){
                        uses.remove();
                    }
                }
            }
            case "delete" -> {
                delete(operation.getParameters().get(0), false);
            }
            case "kill" -> {
                kill(operation.getParameters().get(0));
            }
            default -> {
            }
        }
        currentInstruction++;
    }

    @Override
    int selectPageToVRAM() {
        int latestAccessIndex = -1;
        int latestInstructionCount = 0;
        int realMemoryIndex = 0;
        for(Integer pageId : realMemory){
            Page page = pages.get(pageId);
            Queue<Integer> accessesToPage = earliestAccessToPages.get(pageId);
            if(accessesToPage.isEmpty()){
                return realMemoryIndex;
            }
            int instructionCount = accessesToPage.peek();
            if(instructionCount > latestInstructionCount && instructionCount != currentInstruction){
                latestAccessIndex = realMemoryIndex;
                latestInstructionCount = instructionCount;
            }
            realMemoryIndex++;
        };
        return latestAccessIndex;
    }

    @Override
    public long getNewMark(long simTime) {
        return 0;
    }

    @Override
    public long getUsedMark(long currentMark, long simTime) {
        return 0;
    }

    public ArrayList<Integer> getAllProcesses() {
        return allProcesses;
    }

    public void setAllProcesses(ArrayList<Integer> allProcesses) {
        this.allProcesses = allProcesses;
    }

    public HashMap<Integer, Queue<Integer>> getEarliestAccessToPages() {
        return earliestAccessToPages;
    }

    public void setEarliestAccessToPages(HashMap<Integer, Queue<Integer>> earliestAccessToPages) {
        this.earliestAccessToPages = earliestAccessToPages;
    }

    public int getCurrentInstruction() {
        return currentInstruction;
    }

    public void setCurrentInstruction(int currentInstruction) {
        this.currentInstruction = currentInstruction;
    }

    public ArrayList<Operation> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList<Operation> operations) {
        this.operations = operations;
    }

    public ArrayList<Page> getFuturePages() {
        return futurePages;
    }

    public void setFuturePages(ArrayList<Page> futurePages) {
        this.futurePages = futurePages;
    }

    public HashMap<Integer, ArrayList<Integer>> getFutureMemoryMap() {
        return futureMemoryMap;
    }

    public void setFutureMemoryMap(HashMap<Integer, ArrayList<Integer>> futureMemoryMap) {
        this.futureMemoryMap = futureMemoryMap;
    }
}
