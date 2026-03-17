package com.muxin.demo.jtl;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xddf.usermodel.chart.*;

import java.io.FileOutputStream;
import java.util.List;

public class ExcelReportExporter {

    public static void export(List<StageStats> stages, String fileName) throws Exception {

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

            XSSFSheet sheet = workbook.createSheet("Summary");

            createHeader(sheet, workbook);
            writeData(sheet, stages);
            autoSize(sheet);

            createRtChart(sheet, stages.size());
            createTpsChart(sheet, stages.size());

            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                workbook.write(fos);
            }
        }
    }

    // ================= sheet content =================

    private static void createHeader(XSSFSheet sheet, XSSFWorkbook wb) {
        Row header = sheet.createRow(0);

        String[] titles = {
                "Threads", "Avg(ms)", "P99(ms)", "P99.9(ms)", "TPS", "Requests","startTime","endTime"
        };

        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);

        for (int i = 0; i < titles.length; i++) {
            Cell cell = header.createCell(i);
            cell.setCellValue(titles[i]);
            cell.setCellStyle(style);
        }
    }

    private static void writeData(XSSFSheet sheet, List<StageStats> stages) {
        int rowIdx = 1;
        for (StageStats s : stages) {
            Row row = sheet.createRow(rowIdx++);
            row.createCell(0).setCellValue(s.threads);
            row.createCell(1).setCellValue(s.avg());
            row.createCell(2).setCellValue(s.percentile(0.99));
            row.createCell(3).setCellValue(s.percentile(0.999));
            row.createCell(4).setCellValue(s.tps());
            row.createCell(5).setCellValue(s.requestCount);
            row.createCell(6).setCellValue(s.startTime);
            row.createCell(7).setCellValue(s.endTime);
        }
    }

    private static void autoSize(XSSFSheet sheet) {
        for (int i = 0; i < 6; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    // ================= charts =================

    /** Avg / P99 / P99.9 vs Threads */
    private static void createRtChart(XSSFSheet sheet, int rowCount) {

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(
                0, 0, 0, 0, 0, rowCount + 2, 8, rowCount + 18
        );

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("Response Time vs Threads");
        chart.setTitleOverlay(false);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("Threads");

        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("ms");

        XDDFDataSource<Double> threads =
                XDDFDataSourcesFactory.fromNumericCellRange(
                        sheet, new CellRangeAddress(1, rowCount, 0, 0));

        XDDFNumericalDataSource<Double> avg =
                XDDFDataSourcesFactory.fromNumericCellRange(
                        sheet, new CellRangeAddress(1, rowCount, 1, 1));

        XDDFNumericalDataSource<Double> p99 =
                XDDFDataSourcesFactory.fromNumericCellRange(
                        sheet, new CellRangeAddress(1, rowCount, 2, 2));

        XDDFNumericalDataSource<Double> p999 =
                XDDFDataSourcesFactory.fromNumericCellRange(
                        sheet, new CellRangeAddress(1, rowCount, 3, 3));

        XDDFLineChartData data = (XDDFLineChartData)
                chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        data.addSeries(threads, avg).setTitle("AVG", null);
        data.addSeries(threads, p99).setTitle("P99", null);
        data.addSeries(threads, p999).setTitle("P99.9", null);

        chart.plot(data);
    }

    /** TPS vs Threads */
    private static void createTpsChart(XSSFSheet sheet, int rowCount) {

        XSSFDrawing drawing = sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = drawing.createAnchor(
                0, 0, 0, 0, 8, rowCount + 2, 16, rowCount + 18
        );

        XSSFChart chart = drawing.createChart(anchor);
        chart.setTitleText("TPS vs Threads");
        chart.setTitleOverlay(false);

        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        bottomAxis.setTitle("Threads");

        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        leftAxis.setTitle("TPS");

        XDDFDataSource<Double> threads =
                XDDFDataSourcesFactory.fromNumericCellRange(
                        sheet, new CellRangeAddress(1, rowCount, 0, 0));

        XDDFNumericalDataSource<Double> tps =
                XDDFDataSourcesFactory.fromNumericCellRange(
                        sheet, new CellRangeAddress(1, rowCount, 4, 4));

        XDDFLineChartData data = (XDDFLineChartData)
                chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);

        data.addSeries(threads, tps).setTitle("TPS", null);
        chart.plot(data);
    }
}
