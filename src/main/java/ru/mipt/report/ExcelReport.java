package ru.mipt.report;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ExcelReport implements Report{
    private final String fileName;

    public ExcelReport(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public byte[] asBytes() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(fileName));
            book.write(bos);
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
            return new byte[0];
        }
        return bos.toByteArray();
    }

    @Override
    public void writeTo(OutputStream os) {
        try {
            HSSFWorkbook book = new HSSFWorkbook(new FileInputStream(fileName));
            for (Sheet sheet: book){
                for (Row row: sheet) {
                    for (Cell cell: row) {
                        String value = cell.getStringCellValue();
                        os.write((value + ' ').getBytes());
                    }
                    os.write(("\n").getBytes());
                }
            }
            book.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
