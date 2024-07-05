package uitests;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
public class ReportComparision {
        public static void main(String[] args) throws IOException {
            // Use Selenium to download files if needed
//            WebDriver driver = new ChromeDriver();
//            driver.get("http://example.com/download-excel-file");
//            // Add code here to click download button, etc.
//            driver.quit();

            // Now compare the Excel files
            FileInputStream fis1 = new FileInputStream("Reports/TestReport1.xlsx");
            FileInputStream fis2 = new FileInputStream("Reports/TestReport2.xlsx");

            Workbook workbook1 = new XSSFWorkbook(fis1);
            Workbook workbook2 = new XSSFWorkbook(fis2);

            Sheet sheet1 = workbook1.getSheetAt(0);
            Sheet sheet2 = workbook2.getSheetAt(0);

            if (sheet1.getLastRowNum() != sheet2.getLastRowNum()) {
                System.out.println("Sheets have different number of rows");
                return;
            }

            for (int i = 0; i <= sheet1.getLastRowNum(); i++) {
                Row row1 = sheet1.getRow(i);
                Row row2 = sheet2.getRow(i);

                if (row1.getLastCellNum() != row2.getLastCellNum()) {
                    System.out.println("Row " + i + " has different number of cells");
                    continue;
                }

                for (int j = 0; j < row1.getLastCellNum(); j++) {
                    Cell cell1 = row1.getCell(j);
                    Cell cell2 = row2.getCell(j);

                    if (!cellEquals(cell1, cell2)) {
                        System.out.println("Difference at row " + i + ", column " + j);
                        System.out.println("File 1: " + getCellValueAsString(cell1));
                        System.out.println("File 2: " + getCellValueAsString(cell2));
                    }
                }
            }
            System.out.println("Comparison done");
            workbook1.close();
            workbook2.close();
            fis1.close();
            fis2.close();
        }

        private static boolean cellEquals(Cell cell1, Cell cell2) {
            if (cell1 == null && cell2 == null) {
                return true;
            }
            if (cell1 == null || cell2 == null) {
                return false;
            }
            return getCellValueAsString(cell1).equals(getCellValueAsString(cell2));
        }

        private static String getCellValueAsString(Cell cell) {
            if (cell == null) {
                return "";
            }
            switch (cell.getCellType()) {
                case STRING:
                    return cell.getStringCellValue();
                case NUMERIC:
                    return String.valueOf(cell.getNumericCellValue());
                case BOOLEAN:
                    return String.valueOf(cell.getBooleanCellValue());
                case FORMULA:
                    return cell.getCellFormula();
                default:
                    return "";
            }
        }
}

