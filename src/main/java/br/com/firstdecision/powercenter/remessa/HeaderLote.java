package br.com.firstdecision.powercenter.remessa;

public class HeaderLote {

	private String comeco = "10400011c3045040 200394445018817";
	private String convenio;
	private String fim = "01000101P     0016790001000020321 MINISTERIO DA EDUCACAO        000000                                  ESPL. MIN. BLOCO L, SN        00000ASA NORTE      BRASILIA            70047900DF";
	
	public HeaderLote(String convenio) {
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
