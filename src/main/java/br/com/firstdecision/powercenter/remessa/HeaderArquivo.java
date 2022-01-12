package br.com.firstdecision.powercenter.remessa;

public class HeaderArquivo {

	private String comeco = "10400000         200394445018817";
	private String convenio;
	private String fim = "01PP6301PP    0016790001000020321 MINISTERIO DA EDUCACAO        CAIXA ECONOMICA FEDERAL                 12310202202391322011108000000PIX                 15000000055022MS1810";
	
	public HeaderArquivo(String convenio) {
		this.convenio = convenio;
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

}
