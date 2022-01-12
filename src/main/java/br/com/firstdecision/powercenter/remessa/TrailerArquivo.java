package br.com.firstdecision.powercenter.remessa;

public class TrailerArquivo {

	private String comeco = "10499999         000001";
	private Integer quantidade;
	private String fim = "000000";
	
	public TrailerArquivo(int quant){
		this.quantidade = quant;
	}
	
	public String getComeco() {
		return comeco;
	}
	public void setComeco(String comeco) {
		this.comeco = comeco;
	}
	public Integer getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}

}
