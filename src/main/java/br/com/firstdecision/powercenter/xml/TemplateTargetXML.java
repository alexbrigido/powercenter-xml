package br.com.firstdecision.powercenter.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import br.com.firstdecision.powercenter.util.Util;

public class TemplateTargetXML {
	
	public void gerarTargetXML(String sigla, String nomeTabela, List<CamposExcel> campos) throws IOException {
		try {
			FileWriter writer = new FileWriter("pc_target" + File.separator + "TGT_LDC_" + nomeTabela + ".xml");
			PrintWriter printer = new PrintWriter(writer);
			UUID uuid = UUID.randomUUID();
			
			printer.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
			printer.print("<!DOCTYPE POWERMART SYSTEM \"powrmart.dtd\">\n");
			printer.printf("<POWERMART CREATION_DATE=\"%s\" REPOSITORY_VERSION=\"186.95\">\n", Util.newDateToString("dd/MM/yyyy hh:mm:ss"));
			printer.print("<REPOSITORY NAME=\"RPS_CEF_DES_RJ\" VERSION=\"186\" CODEPAGE=\"Latin1\" DATABASETYPE=\"Oracle\">\n");
			
			printer.printf("<FOLDER NAME=\"PJI_LDC\" GROUP=\"\" OWNER=\"Administrator\" SHARED=\"NOTSHARED\" DESCRIPTION=\"\" PERMISSIONS=\"rwx---r--\" UUID=\"%s\">\n", uuid.toString());
			printer.printf("    <SHORTCUT COMMENTS =\"\" FOLDERNAME =\"%s_Compartilhada\" NAME =\"TGT_LDC_%s\" OBJECTSUBTYPE =\"Target Definition\" OBJECTTYPE =\"TARGET\" REFERENCETYPE =\"LOCAL\" REFOBJECTNAME =\"LDC_%s\" REPOSITORYNAME =\"RPS_CEF_DES_RJ\" VERSIONNUMBER =\"2\"/>\n", sigla, nomeTabela, nomeTabela);
			printer.print("</FOLDER>\n");
			
			printer.printf("<FOLDER NAME=\"%s_Compartilhada\" GROUP=\"\" OWNER=\"Administrator\" SHARED=\"SHARED\" DESCRIPTION=\"\" PERMISSIONS=\"rwx---r--\" UUID=\"%s\">\n", sigla, uuid.toString());
			printer.printf("  <TARGET BUSINESSNAME =\"\" CONSTRAINT =\"\" DATABASETYPE =\"Flat File\" DESCRIPTION =\"\" NAME =\"LDC_%s\" OBJECTVERSION =\"1\" TABLEOPTIONS =\"\" VERSIONNUMBER =\"5\">\n", nomeTabela);
			printer.printf("    <FLATFILE CODEPAGE =\"UTF-8\" CONSECDELIMITERSASONE =\"NO\" DELIMITED =\"YES\" DELIMITERS =\";\" ESCAPE_CHARACTER =\"\" KEEPESCAPECHAR =\"NO\" LINESEQUENTIAL =\"NO\" MULTIDELIMITERSASAND =\"NO\" NULLCHARTYPE =\"ASCII\" NULL_CHARACTER =\"*\" PADBYTES =\"1\" QUOTE_CHARACTER =\"DOUBLE\" REPEATABLE =\"NO\" ROWDELIMITER =\"0\" SKIPROWS =\"0\" STRIPTRAILINGBLANKS =\"NO\"/>\n");
			printer.printf("    <TARGETFIELD BUSINESSNAME =\"\" DATATYPE =\"string\" DESCRIPTION =\"\" FIELDNUMBER =\"1\" KEYTYPE =\"NOT A KEY\" NAME =\"ldc_ic_acao\" NULLABLE =\"NULL\" PICTURETEXT =\"\" PRECISION =\"2\" SCALE =\"0\"/>\n");
			printer.printf("    <TARGETFIELD BUSINESSNAME =\"\" DATATYPE =\"datetime\" DESCRIPTION =\"\" FIELDNUMBER =\"2\" KEYTYPE =\"NOT A KEY\" NAME =\"ldc_dh_acao\" NULLABLE =\"NULL\" PICTURETEXT =\"A  19 yyyy-mm-dd hh24:mi:ss\" PRECISION =\"29\" SCALE =\"9\"/>\n");
			printer.printf("    <TARGETFIELD BUSINESSNAME =\"\" DATATYPE =\"bigint\" DESCRIPTION =\"\" FIELDNUMBER =\"3\" KEYTYPE =\"NOT A KEY\" NAME =\"ldc_nu_execucao\" NULLABLE =\"NULL\" PICTURETEXT =\"\" PRECISION =\"19\" SCALE =\"0\"/>\n");
			gerarSourceField(campos, printer);
			printer.print("    <TABLEATTRIBUTE NAME =\"Datetime Format\" VALUE =\"A  10 yyyy-mm-dd\"/>\n");
			printer.print("    <TABLEATTRIBUTE NAME =\"Thousand Separator\" VALUE =\"None\"/>\n");
			printer.print("    <TABLEATTRIBUTE NAME =\"Decimal Separator\" VALUE =\".\"/>\n");
			printer.print("    <TABLEATTRIBUTE NAME =\"Line Endings\" VALUE =\"System default\"/>\n");
			printer.print("  </TARGET>\n");
			printer.print("</FOLDER>\n");
			
			printer.print("</REPOSITORY>\n");
			printer.print("</POWERMART>\n");
			writer.close();
		} catch (Exception e) {
			System.out.println("ERRO ao gerar Target XML da tabela: " + nomeTabela);
			e.printStackTrace();
		}
	}
	
	private void gerarSourceField(List<CamposExcel> campos, PrintWriter printer){
		int ordem = 4;
		for(CamposExcel campo : campos) {
			String value = String.format("    <TARGETFIELD BUSINESSNAME =\"\" DATATYPE =\"%s\" DESCRIPTION =\"\" FIELDNUMBER =\"%d\" KEYTYPE =\"NOT A KEY\" NAME =\"%s\" NULLABLE =\"%s\" PICTURETEXT =\"\" PRECISION =\"%d\" SCALE =\"0\"/>\n", 
					campo.getTipoTarget(),
					ordem,
					campo.name,
					campo.getNull(),
					campo.getDobroTamanho());
			printer.print(value);
			ordem++;
		}
	}
}
