package com.muxin.demo.jtl;

import java.util.ArrayList;
import java.util.List;

/**
* @projectname: muxin
* @filename: StageStats
* @author: yangxz
* @data:2025/12/13 11:44
* @description: 
*/
public class StageStats {
    int threads;
    long startTime;
    long endTime;

    List<Long> elapsedList = new ArrayList<>();
    long requestCount;

    public double avg() {
        return elapsedList.stream().mapToLong(Long::longValue).average().orElse(0);
    }

    public long percentile_bak(double p) {
        if (elapsedList.isEmpty()) return 0;
        elapsedList.sort(Long::compare);
        int index = (int) Math.ceil(p * elapsedList.size()) - 1;
        return elapsedList.get(Math.min(index, elapsedList.size() - 1));
    }

    public double percentile(double p) {
        if (elapsedList.isEmpty()) return 0.0;

        elapsedList.sort(Long::compare);
        int n = elapsedList.size();

        if (n == 1) {
            return elapsedList.get(0);
        }

        double rank = p * (n - 1);
        int lower = (int) Math.floor(rank);
        int upper = (int) Math.ceil(rank);

        long lowerVal = elapsedList.get(lower);
        long upperVal = elapsedList.get(upper);

        if (lower == upper) {
            return lowerVal;
        }

        return lowerVal + (rank - lower) * (upperVal - lowerVal);
    }






    public double tps() {
        double seconds = (endTime - startTime) / 1000.0;
        return seconds <= 0 ? 0 : requestCount / seconds;
    }
}