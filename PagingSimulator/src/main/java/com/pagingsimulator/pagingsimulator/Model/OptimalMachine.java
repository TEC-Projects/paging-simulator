package com.pagingsimulator.pagingsimulator.Model;

public class OptimalMachine extends Machine{

    public OptimalMachine(int totalMemory, int pageSize) {
        super(totalMemory, pageSize);
    }

    @Override
    int selectPageToVRAM() {
        return 0;
    }

    @Override
    public long getNewMark() {
        return 0;
    }

    @Override
    public long getUsedMark(long currentMark) {
        return 0;
    }
}
