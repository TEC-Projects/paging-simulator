package com.pagingsimulator.pagingsimulator.Model;

public class Page {
    private int pageId;
    private int PID;
    private boolean loaded;
    private int logicalAddress;
    private int memoryAddress;
    private int diskAddress;
    private int loadedAt;
    private long mark;

    public Page(int pageId, int PID, int diskAddress, int loadedAt, long mark) {
        this.pageId = pageId;
        this.PID = PID;
        loaded = true;
        logicalAddress = -1;
        memoryAddress = -1;
        this.diskAddress = diskAddress;
        this.loadedAt = loadedAt;
        this.mark = mark;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getPID() {
        return PID;
    }

    public void setPID(int PID) {
        this.PID = PID;
    }

    public boolean isLoaded() {
        return loaded;
    }

    public void setLoaded(boolean loaded) {
        this.loaded = loaded;
    }

    public int getLogicalAddress() {
        return logicalAddress;
    }

    public void setLogicalAddress(int logicalAddress) {
        this.logicalAddress = logicalAddress;
    }

    public int getMemoryAddress() {
        return memoryAddress;
    }

    public void setMemoryAddress(int memoryAddress) {
        this.memoryAddress = memoryAddress;
    }

    public int getDiskAddress() {
        return diskAddress;
    }

    public void setDiskAddress(int diskAddress) {
        this.diskAddress = diskAddress;
    }

    public int getLoadedAt() {
        return loadedAt;
    }

    public void setLoadedAt(int loadedAt) {
        this.loadedAt = loadedAt;
    }

    public long getMark() {
        return mark;
    }

    public void setMark(long mark) {
        this.mark = mark;
    }

    public void sendPageToVirtualMemory(){
        loaded = false;
        this.memoryAddress = pageId;
        this.diskAddress = -1;
        this.loadedAt = 0;
    }

    public void sendPageToRealMemory(int diskAddress, int loadedAt){
        loaded = true;
        this.diskAddress = diskAddress;
        this.loadedAt = loadedAt;
        memoryAddress = -1;
        logicalAddress = -1;
    }
}
