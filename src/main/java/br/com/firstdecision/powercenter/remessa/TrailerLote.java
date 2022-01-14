package br.com.firstdecision.powercenter.remessa;

public class TrailerLote {

	private String comeco = "10400015         ";
	private Integer quantidade;
	private Long somaValores;
	private String fim = "000000000000000000000000";
	
	public TrailerLote(int quant, long soma) {
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
	public Long getSomaValores() {
		return somaValores;
	}
	public void setSomaValores(Long somaValores) {
		this.somaValores = somaValores;
	}
	
}
