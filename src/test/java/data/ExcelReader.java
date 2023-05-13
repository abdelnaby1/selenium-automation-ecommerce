package data;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ExcelReader {
    static FileInputStream fs = null;

    public FileInputStream getFileInputStream(String filePath){
        File srcFile = new File(filePath);
        FileInputStream fis;
        try {
            fis = new FileInputStream(srcFile);
        } catch (FileNotFoundException e) {
            System.out.println("error message: " + e.getMessage());
            throw new RuntimeException(e);
        }
        return fis;
    }
    public Object[][] getExcelData(String filePath) throws IOException {
        fs = getFileInputStream(filePath);
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
//            XSSFSheet sheet = workbook.getSheet("Sheet1");
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getLastRowNum() + 1;
        int cols = 4;
        String[][] excelData = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                XSSFRow row = sheet.getRow(i);
                excelData[i][j] = row.getCell(j).toString();
            }
        }
        workbook.close();

        return excelData;
    }
}
