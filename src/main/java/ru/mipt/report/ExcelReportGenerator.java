package ru.mipt.report;


import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import ru.mipt.garage.Car;

import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class ExcelReportGenerator<T> implements ReportGenerator<T> {
    private final String fileName;

    public ExcelReportGenerator(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Report generate(List<T> entities) {
        Class<?> clazz = entities.get(0).getClass();

        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet(clazz.getSimpleName());
        setNames(sheet, clazz);
        fillTable(sheet, entities);

        try {
            book.write(new FileOutputStream(fileName));
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return new ExcelReport(fileName);
    }

    private void setNames(Sheet sheet, Class<?> clazz) {
        Row row = sheet.createRow(0);

        Field[] fields = clazz.getDeclaredFields();

        int cellIdx = 0;
        for (Field field: fields) {
            Cell cell = row.createCell(cellIdx);
            cell.setCellValue(field.getName());
            ++cellIdx;
        }
    }

    private void fillTable(Sheet sheet, List<T> entities) {
        int rowIdx = 0;
        Field[] fields = entities.get(0).getClass().getDeclaredFields();
        for (T entity: entities) {
            ++rowIdx;
            Row row  = sheet.createRow(rowIdx);
            int cellIdx = 0;
            for (Field field: fields) {
                Cell cell = row.createCell(cellIdx);
                field.setAccessible(true);
                try {
                    Object val = field.get(entity);
                    if (val != null) {
                        cell.setCellValue(val.toString());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                ++cellIdx;
            }
        }
    }
}
