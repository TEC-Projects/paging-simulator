package com.pagingsimulator.pagingsimulator.Model;

public class Page {
    private int pageId;
    private int PID;
    private boolean loaded;
    private int logicalAddress;
    private int memoryAddress;
    private int diskAddress;
    private int loadTime;
    private int mark;

    public Page(int pageId, int PID, boolean loaded, int logicalAddress, int memoryAddress, int diskAddress, int loadTime, int mark) {
        this.pageId = pageId;
        this.PID = PID;
        this.loaded = loaded;
        this.logicalAddress = logicalAddress;
        this.memoryAddress = memoryAddress;
        this.diskAddress = diskAddress;
        this.loadTime = loadTime;
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

    public int getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(int loadTime) {
        this.loadTime = loadTime;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
}
