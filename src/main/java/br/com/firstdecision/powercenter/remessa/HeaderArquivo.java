package br.com.firstdecision.powercenter.remessa;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import br.com.firstdecision.powercenter.util.DataUtil;

public class HeaderArquivo {

	private String comeco = "10400000         200394445018817";
	private String convenio;
	private String meio = "01PP6301PP    0016790001000020321 MINISTERIO DA EDUCACAO        CAIXA ECONOMICA FEDERAL                 0";
	private String dataHora;
	private String nsa;
	private String fim = "240    12310202202391322011108000000PIX                 15000000055022MS1810 ";
	
	public HeaderArquivo(String convenio, String nsa) {
		this.convenio = convenio;
		this.nsa = nsa;
		this.dataHora = DataUtil.localDateTimeToString(LocalDateTime.now(), DateTimeFormatter.ofPattern("ddMMyyyyHHmmss"));
	}

	public String getComeco() {
		return comeco;
	}

	public void setComeco(String comeco) {
		this.comeco = comeco;
	}

	public String getConvenio() {
		return convenio;
	}

	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}

	public String getFim() {
		return fim;
	}

	public void setFim(String fim) {
		this.fim = fim;
	}

	public String getNsa() {
		return nsa;
	}

	public void setNsa(String nsa) {
		this.nsa = nsa;
	}

	public String getMeio() {
		return meio;
	}

	public void setMeio(String meio) {
		this.meio = meio;
	}

	public String getDataHora() {
		return dataHora;
	}

	public void setDataHora(String dataHora) {
		this.dataHora = dataHora;
	}

}
