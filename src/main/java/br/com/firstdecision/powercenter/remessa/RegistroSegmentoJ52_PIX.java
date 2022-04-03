package br.com.firstdecision.powercenter.remessa;

public class RegistroSegmentoJ52_PIX {

	private Integer codigoBanco = 104;
	private Integer loteServico;
	private Integer codigoRegistro = 3;
	private Integer nsr;
	private String codigoSegmento = "J";
	private String usoExclusioFebraban = " ";
	private Integer codigoMovimenoRemessa;
	private Integer identificacaoRegistroOpcional = 52;
	
	private Integer tipoInscricaoPagador;
	private Long numeroInscricaoPagador;
	private String nomePagador;
	
	private Integer tipoInscricaoBeneficiario;
	private Long numeroInscricaoBeneficiario;
	private String nomeBeneficiario;

	private String chavePagamentoURL;
	private String txid;
	
	public Integer getCodigoBanco() {
		return codigoBanco;
	}
	public void setCodigoBanco(Integer codigoBanco) {
		this.codigoBanco = codigoBanco;
	}
	public Integer getLoteServico() {
		return loteServico;
	}
	public void setLoteServico(Integer loteServico) {
		this.loteServico = loteServico;
	}
	public Integer getCodigoRegistro() {
		return codigoRegistro;
	}
	public void setCodigoRegistro(Integer codigoRegistro) {
		this.codigoRegistro = codigoRegistro;
	}
	public Integer getNsr() {
		return nsr;
	}
	public void setNsr(Integer nsr) {
		this.nsr = nsr;
	}
	public String getCodigoSegmento() {
		return codigoSegmento;
	}
	public void setCodigoSegmento(String codigoSegmento) {
		this.codigoSegmento = codigoSegmento;
	}
	public String getUsoExclusioFebraban() {
		return usoExclusioFebraban;
	}
	public void setUsoExclusioFebraban(String usoExclusioFebraban) {
		this.usoExclusioFebraban = usoExclusioFebraban;
	}
	public Integer getCodigoMovimenoRemessa() {
		return codigoMovimenoRemessa;
	}
	public void setCodigoMovimenoRemessa(Integer codigoMovimenoRemessa) {
		this.codigoMovimenoRemessa = codigoMovimenoRemessa;
	}
	public Integer getIdentificacaoRegistroOpcional() {
		return identificacaoRegistroOpcional;
	}
	public void setIdentificacaoRegistroOpcional(Integer identificacaoRegistroOpcional) {
		this.identificacaoRegistroOpcional = identificacaoRegistroOpcional;
	}
	public Integer getTipoInscricaoPagador() {
		return tipoInscricaoPagador;
	}
	public void setTipoInscricaoPagador(Integer tipoInscricaoPagador) {
		this.tipoInscricaoPagador = tipoInscricaoPagador;
	}
	public Long getNumeroInscricaoPagador() {
		return numeroInscricaoPagador;
	}
	public void setNumeroInscricaoPagador(Long numeroInscricaoPagador) {
		this.numeroInscricaoPagador = numeroInscricaoPagador;
	}
	public String getNomePagador() {
		return nomePagador;
	}
	public void setNomePagador(String nomePagador) {
		this.nomePagador = nomePagador;
	}
	public Integer getTipoInscricaoBeneficiario() {
		return tipoInscricaoBeneficiario;
	}
	public void setTipoInscricaoBeneficiario(Integer tipoInscricaoBeneficiario) {
		this.tipoInscricaoBeneficiario = tipoInscricaoBeneficiario;
	}
	public Long getNumeroInscricaoBeneficiario() {
		return numeroInscricaoBeneficiario;
	}
	public void setNumeroInscricaoBeneficiario(Long numeroInscricaoBeneficiario) {
		this.numeroInscricaoBeneficiario = numeroInscricaoBeneficiario;
	}
	public String getNomeBeneficiario() {
		return nomeBeneficiario;
	}
	public void setNomeBeneficiario(String nomeBeneficiario) {
		this.nomeBeneficiario = nomeBeneficiario;
	}
	public String getChavePagamentoURL() {
		return chavePagamentoURL;
	}
	public void setChavePagamentoURL(String chavePagamentoURL) {
		this.chavePagamentoURL = chavePagamentoURL;
	}
	public String getTxid() {
		return txid;
	}
	public void setTxid(String txid) {
		this.txid = txid;
	}
	
}
