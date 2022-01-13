package br.com.firstdecision.powercenter.remessa;

public class RegistroSegmentoB {

	private String comeco = "10400013";
	private Integer sequencialRegistro;
	private String codSegmento = "B";
	private String formaIniciacao;
	private String meio = "100008703400182";
	private String informacao10Txid;
	private String informacao11IdPagamento;
	private String informacao12IdFavorecidoChavePix;
	private String fim = " ";
	
	public Integer getSequencialRegistro() {
		return sequencialRegistro;
	}
	public void setSequencialRegistro(Integer sequencialRegistro) {
		this.sequencialRegistro = sequencialRegistro;
	}
	public String getComeco() {
		return comeco;
	}
	public void setComeco(String comeco) {
		this.comeco = comeco;
	}
	public String getFormaIniciacao() {
		return formaIniciacao;
	}
	public void setFormaIniciacao(String formaIniciacao) {
		this.formaIniciacao = formaIniciacao;
	}
	public String getMeio() {
		return meio;
	}
	public void setMeio(String meio) {
		this.meio = meio;
	}
	public String getInformacao10Txid() {
		return informacao10Txid;
	}
	public void setInformacao10Txid(String informacao10Txid) {
		this.informacao10Txid = informacao10Txid;
	}
	public String getInformacao11IdPagamento() {
		return informacao11IdPagamento;
	}
	public void setInformacao11IdPagamento(String informacao11IdPagamento) {
		this.informacao11IdPagamento = informacao11IdPagamento;
	}
	public String getInformacao12IdFavorecidoChavePix() {
		return informacao12IdFavorecidoChavePix;
	}
	public void setInformacao12IdFavorecidoChavePix(String informacao12IdFavorecidoChavePix) {
		this.informacao12IdFavorecidoChavePix = informacao12IdFavorecidoChavePix;
	}
	public String getFim() {
		return fim;
	}
	public void setFim(String fim) {
		this.fim = fim;
	}
	public String getCodSegmento() {
		return codSegmento;
	}
	public void setCodSegmento(String codSegmento) {
		this.codSegmento = codSegmento;
	}
	
}
