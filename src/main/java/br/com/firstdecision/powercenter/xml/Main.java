package br.com.firstdecision.powercenter.xml;

import java.util.List;
import java.util.Map;

public class Main {

	public static void main(String[] args) {
		TemplateSourceXML srcXml = new TemplateSourceXML();
		TemplateTargetXML trgXml = new TemplateTargetXML();
		LeitorExcel excel = new LeitorExcel();
		
		String sigla = "ICO";
		
		try {
			System.out.println("Lendo arquivo Excel...");
			Map<String, List<CamposExcel>> campos = excel.gerarListaCampos("Automatizacao_Campos.xlsm");
			/*
			System.out.println("Gerando XML Source...");
			for(List<CamposExcel> listCampos : campos.values()) {
				String nomeTabela = listCampos.get(0).table.toUpperCase();
				srcXml.gerarSourceXML(sigla, nomeTabela, listCampos);
			}
			*/
			System.out.println("Gerando XML Target...");
			for(List<CamposExcel> listCampos : campos.values()) {
				String nomeTabela = listCampos.get(0).table.toUpperCase();
				trgXml.gerarTargetXML(sigla, nomeTabela, listCampos);
			}
			
			System.out.println("Gerado com suscesso.");
		} catch (Exception e) {
			System.err.println("ERRO AO LER/GERAR ARQUIVOS: ");
			e.printStackTrace();
		}
	}
}
