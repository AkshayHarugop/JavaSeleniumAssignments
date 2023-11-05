package dataRead;

//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.LinkedHashMap;
//import java.util.List;
//
//import com.google.common.collect.Table.Cell;

//import org.apache.poi.EncryptedDocumentException;
//import org.apache.poi.ss.usermodel.Cell;
//import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Sheet;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelDataReadAndWrite {
/*
	public static void main(String[] args) throws EncryptedDocumentException, FileNotFoundException, IOException {
		File file = new File("C:\\DassaultSystemes\\3DX-TAF\\admin_settings\\GLOBAL_PARAMETERS_V8.xlsx");
	    Workbook workbook = WorkbookFactory.create(new FileInputStream(file));
	    Sheet sheet = workbook.getSheetAt(1);
	   // Row row = sheet.getRow(0);
	    int column_index_1 =1;
	    int column_index_2 =0;
	    List<Cell> list = new ArrayList<Cell>();
	    List<Cell> list1 = new ArrayList<Cell>();
	    for (Row r : sheet) {
	        if (r.getRowNum()==0) continue;
	        Cell c_1 = (com.google.common.collect.Table.Cell) r.getCell(column_index_2);
	        if (c_1 != null) {
	           list1.add(c_1);
	        }
	    }	
	    System.out.println(list1);
	 for (Row r : sheet) {
	        if (r.getRowNum()==0) continue;
	        Cell c_1 = (com.google.common.collect.Table.Cell) r.getCell(column_index_1);
	        if (c_1 != null) {
	           list.add(c_1);
	        }
	    }
	 System.out.println(list);
	 LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
	 for(int a = 0;a<list1.size();a++) {
		 map.put(list1.get(a), list.get(a));
	 }
	 System.out.println(map);
	
	}
	
	*/

}
