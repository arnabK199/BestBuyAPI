package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class getExcelData {
	
public static List<String> getData(String sheetName , String testCaseName) throws IOException {
		
		int col=0;
		int k=0;

		List<String> list = new ArrayList<String>();
		File file = new File("C:\\My_selenium\\BestBuyAPI.xlsx");
		
		FileInputStream fis = new FileInputStream(file);
		
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		int noOfSheets =wb.getNumberOfSheets();
		
		for(int i=0; i < noOfSheets; i++) {
			
			if(wb.getSheetName(i).equalsIgnoreCase(sheetName)) {
				
				XSSFSheet sheet = wb.getSheetAt(i);
				Iterator<Row> rowit = sheet.iterator();
				Row firstRow = rowit.next();
				
				Iterator<Cell> c =firstRow.cellIterator();
				while(c.hasNext()) {
					Cell c1 = c.next();
					if(c1.getStringCellValue().equals("testcase")) {
						col = k;
					}
					k++;
				}	
				
				while(rowit.hasNext()){
					Row r = rowit.next();
					if(r.getCell(col).getStringCellValue().equalsIgnoreCase(testCaseName)) {
						
						Iterator<Cell> c2 = r.cellIterator();
						while(c2.hasNext()) {
							Cell ctype  = c2.next();
							if(ctype.getCellTypeEnum() == CellType.STRING) {
								list.add(ctype.getStringCellValue());
							}else {
								int i1 =(int) ctype.getNumericCellValue();
								list.add(String.valueOf(i1));
							}
						}
					}
				}
			}
				
			
		}
		
		return list;

}
}

