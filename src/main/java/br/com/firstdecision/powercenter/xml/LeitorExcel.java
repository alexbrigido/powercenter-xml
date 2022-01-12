package br.com.firstdecision.powercenter.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeitorExcel {
	
	public List<CamposChavePixExcel> gerarListaCampos(String fileName) throws IOException {
		FileInputStream arquivo = new FileInputStream(new File(fileName));
		
		XSSFWorkbook workbook = new XSSFWorkbook(arquivo);
        XSSFSheet sheetCampos = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheetCampos.iterator();
        List<CamposChavePixExcel> campos = new ArrayList<CamposChavePixExcel>();
        
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getRowNum() == 0) {
            	continue;
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            CamposChavePixExcel campo = new CamposChavePixExcel();

            while (cellIterator.hasNext()) {
                   Cell cell = cellIterator.next();
                   switch (cell.getColumnIndex()) {
                   case 0:
                         campo.setTipo(cell.getStringCellValue());
                         break;
                   case 1:
                         campo.setChave(cell.getStringCellValue());
                         break;
                   }
            }
           	campos.add(campo);
        }
        workbook.close();
        arquivo.close();
     	return campos;
	}

}
