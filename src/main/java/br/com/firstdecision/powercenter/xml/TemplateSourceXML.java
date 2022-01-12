package br.com.firstdecision.powercenter.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.UUID;

import br.com.firstdecision.powercenter.util.Util;

public class TemplateSourceXML {

	public void gerarSourceXML(String sigla, String nomeTabela, Collection<CamposExcel> campos) {
		try {
			FileWriter writer = new FileWriter("pc_source" + File.separator + nomeTabela + ".xml");
			PrintWriter printer = new PrintWriter(writer);
			UUID uuid = UUID.randomUUID();
			
			printer.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
			printer.print("<!DOCTYPE POWERMART SYSTEM \"powrmart.dtd\">\n");
			printer.printf("<POWERMART CREATION_DATE=\"%s\" REPOSITORY_VERSION=\"186.95\">\n", Util.newDateToString("dd/MM/yyyy hh:mm:ss"));
			printer.print("<REPOSITORY NAME=\"RPS_CEF_DES_RJ\" VERSION=\"186\" CODEPAGE=\"Latin1\" DATABASETYPE=\"Oracle\">\n");
			printer.printf("<FOLDER NAME=\"%s_Compartilhada\" GROUP=\"\" OWNER=\"Administrator\" SHARED=\"SHARED\" DESCRIPTION=\"\" PERMISSIONS=\"rwx---r--\" UUID=\"%s\">\n", sigla, uuid.toString());
			printer.printf("<SOURCE BUSINESSNAME =\"\" DATABASETYPE =\"DB2\" DBDNAME =\"DB2zOs_D1DF\" DESCRIPTION =\"\" NAME =\"%s\" OBJECTVERSION =\"1\" OWNERNAME =\"%s\" VERSIONNUMBER =\"1\">\n", nomeTabela, sigla);
			gerarSourceField(campos, printer);
			printer.print("</SOURCE>\n");
			printer.print("</FOLDER>\n");
			printer.print("</REPOSITORY>\n");
			printer.print("</POWERMART>\n");
			writer.close();			
		} catch (Exception e) {
			System.out.println("ERRO ao gerar Source XML da tabela: " + nomeTabela);
			e.printStackTrace();
		}
	}
	
	private void gerarSourceField(Collection<CamposExcel> campos, PrintWriter printer){
		int ordem = 1;
		int ordem0 = 0;
		for(CamposExcel campo : campos) {
			String value = String.format("  <SOURCEFIELD BUSINESSNAME =\"\" DATATYPE =\"%s\" DESCRIPTION =\"\" FIELDNUMBER =\"%d\" FIELDPROPERTY =\"0\" FIELDTYPE =\"ELEMITEM\" HIDDEN =\"NO\" KEYTYPE =\"%s\" LENGTH =\"0\" LEVEL =\"0\" NAME =\"%s\" NULLABLE =\"%s\" OCCURS =\"0\" OFFSET =\"0\" PHYSICALLENGTH =\"%d\" PHYSICALOFFSET =\"%d\" PICTURETEXT =\"\" PRECISION =\"%d\" SCALE =\"%d\" USAGE_FLAGS =\"\"/>\n", 
					campo.dataType,
					ordem,
					campo.getKey(),
					campo.name,
					campo.getNull(),
					campo.length,
					ordem0,
					campo.precision,
					campo.length);
			printer.print(value);
			ordem++;
			ordem0++;
		}
	}
}
