package utils;

import org.testng.annotations.DataProvider;

import java.io.File;
import java.util.List;

public class DataProviders {

    @DataProvider(name="icData")
    public static Object[][] getExcelData() {
        // Path to the Excel file
        String currentDir = System.getProperty("user.dir");
        String filePath = currentDir + File.separator + "src/test/testData/icdata.xlsx";
        XLUtility xlUtil = new XLUtility();
        int rowCount = xlUtil.getRowCount(filePath);
        int columnCount = xlUtil.getColumnCount(filePath);
        List<List<Object>> data = xlUtil.readExcelcolumnwiseData(filePath);
        Object[][] dataProvider = new Object[rowCount][columnCount];

        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                dataProvider[i][j] = data.get(i).get(j);
            }
        }

        return dataProvider;
    }

    @DataProvider(name="icNameData")
    public static Object[] getIcNames() {
        return new Object[] {"OutlookMailIC", "GenericEailIC_singlePredictor", "Recommend_SharepointEDA"};
    }
}
