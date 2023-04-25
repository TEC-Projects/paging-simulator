package com.pagingsimulator.pagingsimulator.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class MMU {
    private int pageCount;
    private int ptrCount;
    private HashMap<Integer, Allocation> memoryMap;
    private Page[] realMemory;
    private ArrayList<Page> virtualMemory;
    private HashMap<Integer, Page> pages;
    private ArrayList<Process> processes;

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getPtrCount() {
        return ptrCount;
    }

    public void setPtrCount(int ptrCount) {
        this.ptrCount = ptrCount;
    }

    public HashMap<Integer, Allocation> getMemoryMap() {
        return memoryMap;
    }

    public void setMemoryMap(HashMap<Integer, Allocation> memoryMap) {
        this.memoryMap = memoryMap;
    }

    public Page[] getRealMemory() {
        return realMemory;
    }

    public void setRealMemory(Page[] realMemory) {
        this.realMemory = realMemory;
    }

    public ArrayList<Page> getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(ArrayList<Page> virtualMemory) {
        this.virtualMemory = virtualMemory;
    }

    public HashMap<Integer, Page> getPages() {
        return pages;
    }

    public void setPages(HashMap<Integer, Page> pages) {
        this.pages = pages;
    }

    public ArrayList<Process> getProcesses() {
        return processes;
    }

    public void setProcesses(ArrayList<Process> processes) {
        this.processes = processes;
    }
}
