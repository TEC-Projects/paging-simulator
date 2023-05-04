package com.pagingsimulator.pagingsimulator.Model;

import java.util.Random;

public class RandomMachine extends Machine{
    private Random random;

    public RandomMachine(int totalMemory, int pageSize, long randomSeed) {
        super(totalMemory, pageSize);
        random = new Random(randomSeed);
    }

    @Override
    int selectPageToVRAM() {
        return random.nextInt(0, realMemory.size());
    }

    @Override
    public long getNewMark() {
        return -1;
    }

    @Override
    public long getUsedMark(long currentMark) {
        return -1;
    }
}
