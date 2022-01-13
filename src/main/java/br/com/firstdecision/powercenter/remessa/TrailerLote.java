package br.com.firstdecision.powercenter.remessa;

public class TrailerLote {

	private String comeco = "10400015         ";
	private Integer quantidade;
	private Integer somaValores;
	private String fim = "000000000000000000000000";
	
	public TrailerLote(int quant, int soma) {
		this.quantidade = quant;
		this.somaValores = soma;
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
	public Integer getSomaValores() {
		return somaValores;
	}
	public void setSomaValores(Integer somaValores) {
		this.somaValores = somaValores;
	}
	
}
