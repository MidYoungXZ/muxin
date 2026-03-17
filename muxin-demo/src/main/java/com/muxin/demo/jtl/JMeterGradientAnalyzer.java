package com.muxin.demo.jtl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class JMeterGradientAnalyzer {

    static DateTimeFormatter formatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
                    .withZone(ZoneId.systemDefault());

    public static void main(String[] args) throws Exception {

//        if (args.length < 5) {
//            System.err.println("Usage: java JMeterGradientAnalyzer <jtlFile> <startThreads> <step> <maxThreads> <stageSeconds>");
//            return;
//        }

        String jtlFile = "C:\\Users\\bangsun\\Desktop\\100jd_tid.jtl";
        int startThreads = 10;
        int step = 20;
        int maxThreads = 70;
        long stageMillis = Long.parseLong("300") * 1000;

        Map<Integer, StageStats> stageMap = new LinkedHashMap<>();

        int currentThreads = startThreads;
        StageStats currentStage = null;

        try (BufferedReader br = new BufferedReader(new FileReader(jtlFile))) {
            String header = br.readLine(); // skip header
            String line;

            while ((line = br.readLine()) != null && currentThreads <= maxThreads) {
                String[] cols = line.split(",", -1);

                long timeStamp = Long.parseLong(cols[0]);
                long elapsed = Long.parseLong(cols[1]);
                int grpThreads = Integer.parseInt(cols[11]);

                // 进入新的梯度阶段
                if (grpThreads == currentThreads && currentStage == null) {
                    currentStage = new StageStats();
                    currentStage.threads = currentThreads;
                    currentStage.startTime = timeStamp;
                    stageMap.put(currentThreads, currentStage);
                }

                // 统计当前阶段数据
                if (currentStage != null) {
                    if (timeStamp - currentStage.startTime <= stageMillis) {
                        currentStage.elapsedList.add(elapsed);
                        currentStage.requestCount++;
                        currentStage.endTime = timeStamp;
                    } else {
                        // 当前阶段结束，进入下一个梯度
                        currentThreads += step;
                        currentStage = null;
                    }
                }
            }
        }

//        printResult(stageMap);

        printResult(stageMap);

// ===== Excel export (optional enhancement) =====
        List<StageStats> stages = new ArrayList<>(stageMap.values());
        ExcelReportExporter.export(stages, "jmeter-gradient-report.xlsx");

    }

    private static void printResult(Map<Integer, StageStats> stageMap) {
        System.out.println("Threads | StartTime | EndTime | Avg(ms) | P99(ms) | P99.9(ms) | TPS | Requests");

        for (StageStats s : stageMap.values()) {
            System.out.printf(
//                    "%d | %s | %s | %.2f | %d | %d | %.2f | %d%n",
                    "%d | %s | %s | %.2f | %.2f | %.2f | %.2f | %d%n",
                    s.threads,
                    formatTime(s.startTime),
                    formatTime(s.endTime),
                    s.avg(),
                    s.percentile(0.99),
                    s.percentile(0.999),
                    s.tps(),
                    s.requestCount
            );
        }
    }

    private static String formatTime(long ts) {
        return ts + " (" + formatter.format(Instant.ofEpochMilli(ts)) + ")";
    }




}
