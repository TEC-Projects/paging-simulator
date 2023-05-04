package com.pagingsimulator.pagingsimulator.Model;

import java.util.Random;

public class RandomMachine extends Machine{
    private Random random;

    public RandomMachine(int totalMemory, int pageSize, long randomSeed) {
        super(totalMemory, pageSize, -1);
        random = new Random(randomSeed);
    }

    @Override
    int selectPageToVRAM() {
        return random.nextInt(0, realMemory.size());
    }

    @Override
    public long getBaseMarking() {
        return baseMarking;
    }
}
