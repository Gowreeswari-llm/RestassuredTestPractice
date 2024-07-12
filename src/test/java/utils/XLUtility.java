package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XLUtility {

    public List<List<String>> readExcelData(String filePath) {
        List<List<String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                List<String> rowData = new ArrayList<>();

                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    rowData.add(cell.toString());
                }

                data.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public List<List<Object>> readExcelcolumnwiseData(String filePath) {
        List<List<Object>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                List<Object> rowData = new ArrayList<>();

                // Read first cell as String
                Cell firstCell = row.getCell(0);
                if (firstCell != null) {
                    firstCell.setCellType(CellType.STRING);
                    rowData.add(firstCell.getStringCellValue());
                } else {
                    rowData.add(""); // Add empty string if cell is null
                }

                // Read second cell as Integer
                Cell secondCell = row.getCell(1);
                if (secondCell != null && secondCell.getCellType() == CellType.NUMERIC) {
                    rowData.add((int) secondCell.getNumericCellValue());
                } else {
                    rowData.add(0); // Add 0 if cell is null or not numeric
                }

                data.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public int getRowCount(String filePath) {
        int rowCount = 0;

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            rowCount = workbook.getSheetAt(0).getLastRowNum() + 1; // +1 because rows are 0-based

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rowCount;
    }

    public int getColumnCount(String filePath) {
        int columnCount = 0;

        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            columnCount = workbook.getSheetAt(0).getRow(0).getLastCellNum();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return columnCount;
    }

}
