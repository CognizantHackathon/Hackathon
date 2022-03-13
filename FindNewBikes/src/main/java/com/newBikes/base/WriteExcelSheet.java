package com.newBikes.base;

import java.io.FileOutputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WriteExcelSheet extends baseUI {


public static void writeData(ArrayList<String> arr,String sheetName,String pageTitle,String fileName) {


//create workbook
XSSFWorkbook workbook=new XSSFWorkbook();

//create a sheet
XSSFSheet sheet=workbook.createSheet(sheetName);

//create row
Row row1 = sheet.createRow(0);

//Create cell
Cell cell1 = row1.createCell(0);

cell1.setCellValue(pageTitle);

int count=0;
for(int i=1;i<=arr.size();i++) {

Row row=sheet.createRow(i);
Cell cell=row.createCell(0);
cell.setCellValue(arr.get(count++));


}

try {
//Writing the data to a cell using output stream
FileOutputStream outfile=new FileOutputStream(System.getProperty("user.dir")+"\\OutPutExcelSheet\\"+fileName);
workbook.write(outfile);
outfile.close();
workbook.close();

}catch(Exception e) {
e.printStackTrace();
}

}

}

