package com.muxin.demo;

/**
 * @projectname: bsfit-retail-mgr-parent
 * @filename: Main
 * @author: yangxz
 * @data:2024/8/30 10:25
 * @description:
 */

import java.io.*;

public class MergeFilesInDirectory {
    public static void main(String[] args) {
        String directoryPath = "/Users/midyoung/Desktop/mock_data/check_list_ol/rams_ol_checklist2"; // 替换为你的目录路径
        String outputFilePath = "/Users/midyoung/Desktop/mock_data/check_list_ol/rams_ol_checklist2_20240701-20240831.txt"; // 输出文件路径

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            File dir = new File(directoryPath);
            System.out.println("Starting to merge files in directory: " + directoryPath);
            mergeFilesRecursively(dir, writer);
            System.out.println("Files merged successfully into: " + outputFilePath);
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + outputFilePath);
            e.printStackTrace();
        }
    }

    private static void mergeFilesRecursively(File dir, BufferedWriter writer) throws IOException {
        if (!dir.exists()) {
            System.err.println("Directory does not exist: " + dir.getAbsolutePath());
            return;
        }

        File[] files = dir.listFiles();
        if (files == null) return;

        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("Entering directory: " + file.getAbsolutePath());
                // 递归处理子目录
                mergeFilesRecursively(file, writer);
            } else if (file.isFile() && file.getName().endsWith(".txt")) {
                System.out.println("Processing file: " + file.getAbsolutePath());
                // 处理文本文件
                try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        writer.write(line);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    System.err.println("Error reading file: " + file.getAbsolutePath());
                    e.printStackTrace();
                }
            }
        }
    }
}
