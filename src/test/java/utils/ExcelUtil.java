package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtil {


    private static final String SHEET_CREDENTIALS = "credentials";
    private static final String SHEET_URLS        = "URLs";

    public static String getUserId(String filePath, String userName) throws IOException {
        return getCellByLookup(
                filePath,
                SHEET_CREDENTIALS,
                "UserName",  
                userName,     
                "Userid"    
        );
    }


    public static String getPassword(String filePath, String userName) throws IOException {
        return getCellByLookup(
                filePath,
                SHEET_CREDENTIALS,
                "UserName",   
                userName,     
                "Password"    
        );
    }


    public static String getUrl(String filePath, String pageName) throws IOException {
        return getCellByLookup(
                filePath,
                SHEET_URLS,
                "WebPage",   
                pageName,    
                "URL"        
        );
    }


    private static String getCellByLookup(
            String filePath,
            String sheetName,
            String Header,
            String Value,
            String targetHeader
    ) throws IOException {

   
        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new RuntimeException("Header row missing in sheet: " + sheetName);
            }

            int headerColIndex = -1;
            int targetColIndex = -1;

     
            DataFormatter formatter = new DataFormatter();

            for (Cell cell : headerRow) {
                String header = formatter.formatCellValue(cell).trim();

                if (header.equalsIgnoreCase(Header)) {
                	headerColIndex = cell.getColumnIndex();
                }
                if (header.equalsIgnoreCase(targetHeader)) {
                    targetColIndex = cell.getColumnIndex();
                }
            }

            if (headerColIndex == -1) {
                throw new RuntimeException("Lookup header not found: " + Header);
            }
            if (targetColIndex == -1) {
                throw new RuntimeException("Target header not found: " + targetHeader);
            }

            
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Cell lookupCell = row.getCell(headerColIndex);
                if (lookupCell == null) continue;

                String cellValue = formatter.formatCellValue(lookupCell).trim();

                if (cellValue.equalsIgnoreCase(Value)) {
                   
                    Cell targetCell = row.getCell(targetColIndex);
                    if (targetCell == null) {
                        throw new RuntimeException("Target cell empty for value: " + Value);
                    }

                    String result = formatter.formatCellValue(targetCell).trim();
                    return result;
                }
            }

            throw new RuntimeException("\"" + Value + "\" not found in sheet '" + sheetName + "' ");
        }
    }
}
