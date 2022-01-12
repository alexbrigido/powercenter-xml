package br.com.firstdecision.powercenter.xml;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;

import br.com.firstdecision.powercenter.util.Util;

public class TemplateMappingXML {
	
	public void gerarTargetXML(String sigla, String nomeTabela, List<CamposExcel> campos) throws IOException {
		try {
			FileWriter writer = new FileWriter("pc_mapping" + File.separator + "MPG_LDC_" + nomeTabela + ".xml");
			PrintWriter printer = new PrintWriter(writer);
			UUID uuid = UUID.randomUUID();
			
			printer.print("<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>\n");
			printer.print("<!DOCTYPE POWERMART SYSTEM \"powrmart.dtd\">\n");
			printer.printf("<POWERMART CREATION_DATE=\"%s\" REPOSITORY_VERSION=\"186.95\">\n", Util.newDateToString("dd/MM/yyyy hh:mm:ss"));
			printer.print("<REPOSITORY NAME=\"RPS_CEF_DES_RJ\" VERSION=\"186\" CODEPAGE=\"Latin1\" DATABASETYPE=\"Oracle\">\n");
			
			printer.printf("<FOLDER NAME=\"%s_Compartilhada\" GROUP=\"\" OWNER=\"Administrator\" SHARED=\"SHARED\" DESCRIPTION=\"\" PERMISSIONS=\"rwx---r--\" UUID=\"%s\">\n", sigla, uuid.toString());
			printer.printf("  <EXPRMACRO DESCRIPTION =\"Tratamento para eliminar caracteres tais como: aspas duplas.\" EXPRESSION =\"REPLACESTR(1,REPLACESTR(1,REPLACESTR(1,LTRIM(RTRIM(CAMPO)),&apos;&quot;&apos;,&apos;&quot;&quot;&apos;),CHR(13),&apos;&apos;),CHR(10),&apos;&apos;)&#xD;&#xA;\" MACROTYPE =\"Public\" NAME =\"STRING_FORMAT\" OBJECTVERSION =\"1\" PROTOTYPE =\"NSTRING STRING_FORMAT( CAMPO as string )\" RETURNTYPE =\"nstring\" VERSIONNUMBER =\"90\">\n", nomeTabela);
			printer.printf("      <MACROARGUMENT ARGORDER =\"1\" DATATYPE =\"string\" NAME =\"CAMPO\" PRECISION =\"99999999\" SCALE =\"0\"/>\n");
			printer.printf("  </EXPRMACRO>\n");
			printer.printf("  <MAPPING DESCRIPTION =\"\" ISVALID =\"YES\" NAME =\"MPG_LDC_1020_0023_ICOTBC27_TPOSTCO\" OBJECTVERSION =\"1\" VERSIONNUMBER =\"1\">\n");
			printer.printf("    <TRANSFORMATION DESCRIPTION =\"\" NAME =\"SQF_SRC_ICOTBC27_TPOSTCO\" OBJECTVERSION =\"1\" REUSABLE =\"NO\" TYPE =\"Source Qualifier\" VERSIONNUMBER =\"1\">\n");
			printer.print("       <TRANSFORMFIELD DATATYPE =\"string\" DEFAULTVALUE =\"\" DESCRIPTION =\"\" NAME =\"CO_TIPO_STCO_CNVNO\" PICTURETEXT =\"\" PORTTYPE =\"INPUT/OUTPUT\" PRECISION =\"1\" SCALE =\"0\"/>\n");
			printer.print("       <TRANSFORMFIELD DATATYPE =\"string\" DEFAULTVALUE =\"\" DESCRIPTION =\"\" NAME =\"DE_TIPO_STCO_CNVNO\" PICTURETEXT =\"\" PORTTYPE =\"INPUT/OUTPUT\" PRECISION =\"50\" SCALE =\"0\"/>\n");

			
			printer.print("\n");
			printer.print("\n");
			printer.print("\n");
			printer.print("\n");
			printer.print("\n");
			printer.print("\n");
			printer.print("\n");
			printer.print("\n");
			printer.print("  </MAPPING>\n");

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
