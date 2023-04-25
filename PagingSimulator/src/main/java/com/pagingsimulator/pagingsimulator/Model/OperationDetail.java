package com.pagingsimulator.pagingsimulator.Model;

public class OperationDetail {
    private int pageId;
    private int PID;
    private boolean loaded;
    private int logicalAddress;
    private int memoryAddress;
    private int diskAddress;
    private int loadTime;
    private int mark;

    public OperationDetail(int pageId, int PID, boolean loaded, int logicalAddress, int memoryAddress, int diskAddress, int loadTime, int mark) {
        this.pageId = pageId;
        this.PID = PID;
        this.loaded = loaded;
        this.logicalAddress = logicalAddress;
        this.memoryAddress = memoryAddress;
        this.diskAddress = diskAddress;
        this.loadTime = loadTime;
        this.mark = mark;
    }
}
