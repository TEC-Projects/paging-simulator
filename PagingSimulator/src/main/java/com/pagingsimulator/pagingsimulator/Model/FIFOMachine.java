package com.pagingsimulator.pagingsimulator.Model;


import java.time.Instant;
import java.util.Date;

public class FIFOMachine extends Machine{
    public FIFOMachine(int totalMemory, int pageSize, int baseMarking) {
        super(totalMemory, pageSize, baseMarking);
    }

    @Override
    int selectPageToVRAM() {
        int tiniestMarkIndex = -1;
        long tiniestMark = Long.MAX_VALUE;
        for (int i = 0; i < realMemory.size(); i++) {
            Page page = pages.get(realMemory.get(i));
            if(tiniestMark > page.getMark()){
                tiniestMark = page.getMark();
                tiniestMarkIndex = i;
            }
        }
        return tiniestMarkIndex;
    }

    @Override
    public long getBaseMarking() {
        return Instant.now().getEpochSecond();
    }


}
