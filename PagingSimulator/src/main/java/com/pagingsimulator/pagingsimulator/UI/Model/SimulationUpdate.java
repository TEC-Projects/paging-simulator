package com.pagingsimulator.pagingsimulator.UI.Model;

import com.pagingsimulator.pagingsimulator.Model.PagingAlgorithmSimulationStatus;
import javafx.util.Pair;

import java.util.ArrayList;

public class SimulationUpdate {
    private PagingAlgorithmSimulationStatus algorithmStatusUpdate;
    private ArrayList<Integer> RAMUsageMapping;
    private ArrayList<Pair<Integer, Integer>> RAMUsageTimeline;
    private ArrayList<Pair<Integer, Integer>> virtualRAMUsageTimeline;


    public SimulationUpdate() {
        this.algorithmStatusUpdate = null;
        this.RAMUsageMapping = new ArrayList<>();
        this.RAMUsageTimeline = new ArrayList<>();
        this.virtualRAMUsageTimeline = new ArrayList<>();
    }

    public SimulationUpdate(PagingAlgorithmSimulationStatus algorithmStatusUpdate, ArrayList<Integer> RAMUsageMapping, ArrayList<Pair<Integer, Integer>> RAMUsageTimeline, ArrayList<Pair<Integer, Integer>> virtualRAMUsageTimeline) {
        this.algorithmStatusUpdate = algorithmStatusUpdate;
        this.RAMUsageMapping = RAMUsageMapping;
        this.RAMUsageTimeline = RAMUsageTimeline;
        this.virtualRAMUsageTimeline = virtualRAMUsageTimeline;
    }

    public PagingAlgorithmSimulationStatus getAlgorithmStatusUpdate() {
        return algorithmStatusUpdate;
    }

    public void setAlgorithmStatusUpdate(PagingAlgorithmSimulationStatus algorithmStatusUpdate) {
        this.algorithmStatusUpdate = algorithmStatusUpdate;
    }

    public ArrayList<Integer> getRAMUsageMapping() {
        return RAMUsageMapping;
    }

    public void setRAMUsageMapping(ArrayList<Integer> RAMUsageMapping) {
        this.RAMUsageMapping = RAMUsageMapping;
    }

    public ArrayList<Pair<Integer, Integer>> getRAMUsageTimeline() {
        return RAMUsageTimeline;
    }

    public void setRAMUsageTimeline(ArrayList<Pair<Integer, Integer>> RAMUsageTimeline) {
        this.RAMUsageTimeline = RAMUsageTimeline;
    }

    public ArrayList<Pair<Integer, Integer>> getVirtualRAMUsageTimeline() {
        return virtualRAMUsageTimeline;
    }

    public void setVirtualRAMUsageTimeline(ArrayList<Pair<Integer, Integer>> virtualRAMUsageTimeline) {
        this.virtualRAMUsageTimeline = virtualRAMUsageTimeline;
    }
}
