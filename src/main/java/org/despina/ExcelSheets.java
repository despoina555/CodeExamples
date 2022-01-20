package org.despina;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.List;


public class ExcelSheets {


    static boolean buildExcelFromCSV(final String headerContent) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        createSheet(workbook, "Header", headerContent);
        try {
            String filename=System.getProperty("user.home") + "/Desktop/temp.xlsx";
            FileOutputStream fos = new FileOutputStream(filename);
            workbook.write(fos);
            fos.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

     private  static void createSheet(XSSFWorkbook workbook, final String sheetName, final String content) {
        XSSFSheet sheetHeader = workbook.createSheet(sheetName);
        /** Lambdas require ::
         * Any local variable, formal parameter, or exception parameter used but not declared in a lambda expression
         * must either be declared final or be effectively final
         *
         * In our case rowCount should be final or effectively final */
        final int[] rowCount = {0};
        String[] lines = content.split(System.lineSeparator());
        List<String> linesList = Arrays.asList(lines);
        linesList.forEach( it -> {
                    Row row = sheetHeader.createRow(rowCount[0]);
                    String[] cellValues = it.split(";");
                    writeEntry(cellValues, row);
                    rowCount[0]++;
                }
        );
    }

    private  static void writeEntry(String[] cellValues, Row row) {
        int columnCount = 0;
        for (String it : cellValues)  {
            Cell cell = row.createCell(columnCount);
            cell.setCellValue(it);
            columnCount++;
        }
    }
}
