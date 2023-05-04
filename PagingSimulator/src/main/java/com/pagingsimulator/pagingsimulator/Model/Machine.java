package com.pagingsimulator.pagingsimulator.Model;

import java.util.ArrayList;
import java.util.HashMap;

abstract class Machine {
    protected int pageCount;
    protected int ptrCount;
    protected int usedRam;
    protected HashMap<Integer, Allocation> memoryMap;
    protected ArrayList<Integer> realMemory;
    protected ArrayList<Integer> virtualMemory;
    protected HashMap<Integer, Page> pages;
    protected HashMap<Integer, Process> processes;

    private void addPtrToProcess(int pid, int ptr){
        Process process = processes.get(pid);
        if(process != null){
            process.addPtr(ptr);
        }else{
            processes.put(pid, new Process(pid, ptr));
        }
    }

    private void deletePtrFromProcess(int pid, int ptr){
        Process process = processes.get(pid);
        if(process != null){
            process.deletePtr(ptr);
        }else{
            System.out.println("Lol this proccess dont exist");
        }
    }

    public Machine(int totalMemory, int pageSize) {
        ptrCount = 0;
        pageCount = 0;
        usedRam = 0;
        memoryMap = new HashMap<>();
        realMemory = new ArrayList<>(totalMemory/pageSize);
        virtualMemory = new ArrayList<>();
        pages = new HashMap<>();
        processes = new HashMap<>();
    }

    abstract int selectPageToVRAM();

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getUsedRam() {
        return usedRam;
    }

    public void setUsedRam(int usedRam) {
        this.usedRam = usedRam;
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

    public ArrayList<Integer> getRealMemory() {
        return realMemory;
    }

    public void setRealMemory(ArrayList<Integer> realMemory) {
        this.realMemory = realMemory;
    }

    public ArrayList<Integer> getVirtualMemory() {
        return virtualMemory;
    }

    public void setVirtualMemory(ArrayList<Integer> virtualMemory) {
        this.virtualMemory = virtualMemory;
    }

    public HashMap<Integer, Page> getPages() {
        return pages;
    }

    public void setPages(HashMap<Integer, Page> pages) {
        this.pages = pages;
    }

    public HashMap<Integer, Process> getProcesses() {
        return processes;
    }

    public void setProcesses(HashMap<Integer, Process> processes) {
        this.processes = processes;
    }

    public abstract long getNewMark();
    public abstract long getUsedMark(long currentMark);

    public int newAlloc(int pid, int allocSize, int simTime) {
        double pagesCount = Math.ceil(allocSize*1.0/4000.0);
        ArrayList<Integer> createdPages = new ArrayList<>();
        for (int i = 0; i < pagesCount; i++) {
            if (usedRam == realMemory.size()){
                int pageReplacedIndex = selectPageToVRAM();
                int pageReplacedId = realMemory.get(pageReplacedIndex);
                pages.get(pageReplacedId).sendPageToVirtualMemory();
                virtualMemory.add(pageReplacedId);
                createdPages.add(pageCount);
                realMemory.set(pageReplacedIndex, pageCount);
                pages.put(pageCount, new Page(pageCount++, pid, pageReplacedIndex, simTime, getNewMark()));
            }else{
                for (int j = 0; j < realMemory.size(); j++) {
                    if(realMemory.get(j) == -1){
                        createdPages.add(pageCount);
                        realMemory.set(j, pageCount);
                        pages.put(pageCount, new Page(pageCount++, pid, j, simTime, getNewMark()));
                    }
                }
            }
            usedRam++;
        }
        memoryMap.put(ptrCount, new Allocation(ptrCount, pid , createdPages));
        addPtrToProcess(pid, ptrCount);
        return ptrCount++;
    }

    public void use(int ptr, int simTime) {
        Allocation allocation = memoryMap.get(ptr);
        ArrayList<Integer> notFoundPages = new ArrayList<>();
        for(int pageId : allocation.getPageIds()){
            if (!realMemory.contains(pageId)){
                notFoundPages.add(pageId);
            };
            Page page = pages.get(pageId);
            page.setMark(getUsedMark(page.getMark()));
        }
        if(!notFoundPages.isEmpty()){
            for(int notFoundPageId : notFoundPages){
                int pageReplacedIndex = selectPageToVRAM();
                int pageReplacedId = realMemory.get(pageReplacedIndex);
                pages.get(pageReplacedId).sendPageToVirtualMemory();
                virtualMemory.add(pageReplacedId);
                realMemory.set(pageReplacedIndex, virtualMemory.remove(virtualMemory.indexOf(notFoundPageId)));
                pages.get(realMemory.get(pageReplacedIndex)).sendPageToRealMemory(pageReplacedIndex, simTime);
            }
        }
    }

    public void delete(int ptr){
        Allocation allocation = memoryMap.get(ptr);
        for (int pageId : allocation.getPageIds()){
            Page page = pages.get(pageId);
            if(page.isLoaded()){
                realMemory.set(page.getDiskAddress(), -1);
            }else{
                virtualMemory.remove((Integer) pageId);
            }
        }
        deletePtrFromProcess(allocation.getPid(), ptr);
        memoryMap.remove(ptr);
    }

    public void kill(int pid){
        Process process = processes.get(pid);
        for(Integer ptr : process.getPtrs()){
            delete(ptr);
        }
        processes.remove(pid);
    }
}
