package br.com.firstdecision.powercenter.xml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LeitorExcel {

	Map<String, List<CamposExcel>> tabelasMap = new HashMap<>();
	
	public Map<String, List<CamposExcel>> gerarListaCampos(String fileName) throws IOException {
		FileInputStream arquivo = new FileInputStream(new File(fileName));
		
		XSSFWorkbook workbook = new XSSFWorkbook(arquivo);
        XSSFSheet sheetCampos = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheetCampos.iterator();
        List<CamposExcel> campos = new ArrayList<>();
        
        String tabela = null;
        String tabelaAtual = null;
        
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            if(row.getRowNum() == 0) {
            	continue;
            }
            Iterator<Cell> cellIterator = row.cellIterator();
            CamposExcel campo = new CamposExcel();

            while (cellIterator.hasNext()) {
                   Cell cell = cellIterator.next();
                   switch (cell.getColumnIndex()) {
                   case 0:
                         campo.setTable(cell.getStringCellValue());
                         tabelaAtual = cell.getStringCellValue();
                         break;
                   case 1:
                         campo.setName(cell.getStringCellValue());
                         break;
                   case 2:
                         campo.setComment(cell.getStringCellValue());
                         break;
                   case 3:
                         campo.setDataType(cell.getStringCellValue());
                         break;
                   case 4:
                         campo.setLength( (int) cell.getNumericCellValue());
                         break;
                   case 5:
                	   campo.setPrecision( (int) cell.getNumericCellValue());
                	   break;
                   case 6:
                	   campo.setPrimary(cell.getStringCellValue());
                	   break;
                   case 7:
                	   campo.setNullable(cell.getStringCellValue());
                	   break;
                   case 8:
                	   campo.setSistema(cell.getStringCellValue());
                	   break;
                   }
            }
            if(Objects.nonNull(tabela) && !tabela.equals(tabelaAtual)) {
            	tabelasMap.put(tabela, campos);
            	campos = new ArrayList<>();
            	campos.add(campo);
            	tabela = tabelaAtual;
            }else {
            	campos.add(campo);
            	tabela = tabelaAtual;
            }
        }
        workbook.close();
        arquivo.close();
     	return tabelasMap;
	}

}
