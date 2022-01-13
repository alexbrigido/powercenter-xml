package br.com.firstdecision.powercenter.remessa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import br.com.firstdecision.powercenter.util.DataUtil;

public class RegistroSegmentoA {

	private String filler1 = "10400013";
	private Integer nsr;
	private String codSegmento = "A";
	private String tipoMov = "1";
	private String codInstrMov = "00";
	
	private String codCompens = "009";
	private String bancoFavorecido = "104";
	
	private String codAgencia = "00664";
	private String dvAgencia = "5";
	private String numConta = "000000140382";
	private String devNumConta = "5";
	private String dvAgeConta = " ";
	
	private String nomeFavor = " ";
	private String numAtribEmpr = " ";
	private String filler2 = " ";
	private String tipoConta = " ";
	
	private String dataPagto = " ";
	private String tipoMoeda = " ";
	private String qtdMoeda = " ";
	
	private String valorPagto = " ";
	private String numAtribBanco = " ";
	private String filler3 = " ";
	
	private String qtdParcelas = " ";
	private String indBloqueio = " ";
	private String formParc = " ";
	private String numPeriodo = " ";
	private String numParcela = " ";
	private String dataEfetivPagto = " ";
	private String valorEfetivPagto = " ";
	
	private String informacao2 = " ";
	private String codFinaliDoc = " ";
	private String usoFebraban = " ";
	private String avisoFavor = " ";
	private String ocor = " ";
	
	
	
	public RegistroSegmentoA() {
		setDataEfetivPagto(DataUtil.localDateToString(LocalDate.now(), DateTimeFormatter.ofPattern("yyyyMMdd")));
		setDataPagto(DataUtil.localDateToString(LocalDate.now(), DateTimeFormatter.ofPattern("yyyyMMdd")));
	}
	
	public String getFiller1() {
		return filler1;
	}
	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}
	public Integer getNsr() {
		return nsr;
	}
	public void setNsr(Integer nsr) {
		this.nsr = nsr;
	}
	public String getCodSegmento() {
		return codSegmento;
	}
	public void setCodSegmento(String codSegmento) {
		this.codSegmento = codSegmento;
	}
	public String getTipoMov() {
		return tipoMov;
	}
	public void setTipoMov(String tipoMov) {
		this.tipoMov = tipoMov;
	}
	public String getCodInstrMov() {
		return codInstrMov;
	}
	public void setCodInstrMov(String codInstrMov) {
		this.codInstrMov = codInstrMov;
	}
	public String getCodCompens() {
		return codCompens;
	}
	public void setCodCompens(String codCompens) {
		this.codCompens = codCompens;
	}
	public String getBancoFavorecido() {
		return bancoFavorecido;
	}
	public void setBancoFavorecido(String bancoFavorecido) {
		this.bancoFavorecido = bancoFavorecido;
	}
	public String getCodAgencia() {
		return codAgencia;
	}
	public void setCodAgencia(String codAgencia) {
		this.codAgencia = codAgencia;
	}
	public String getDvAgencia() {
		return dvAgencia;
	}
	public void setDvAgencia(String dvAgencia) {
		this.dvAgencia = dvAgencia;
	}
	public String getNumConta() {
		return numConta;
	}
	public void setNumConta(String numConta) {
		this.numConta = numConta;
	}
	public String getDevNumConta() {
		return devNumConta;
	}
	public void setDevNumConta(String devNumConta) {
		this.devNumConta = devNumConta;
	}
	public String getNomeFavor() {
		return nomeFavor;
	}
	public void setNomeFavor(String nomeFavor) {
		this.nomeFavor = nomeFavor;
	}
	public String getNumAtribEmpr() {
		return numAtribEmpr;
	}
	public void setNumAtribEmpr(String numAtribEmpr) {
		this.numAtribEmpr = numAtribEmpr;
	}
	public String getFiller2() {
		return filler2;
	}
	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}
	public String getTipoConta() {
		return tipoConta;
	}
	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}
	public String getDataPagto() {
		return dataPagto;
	}
	public void setDataPagto(String dataPagto) {
		this.dataPagto = dataPagto;
	}
	public String getTipoMoeda() {
		return tipoMoeda;
	}
	public void setTipoMoeda(String tipoMoeda) {
		this.tipoMoeda = tipoMoeda;
	}
	public String getQtdMoeda() {
		return qtdMoeda;
	}
	public void setQtdMoeda(String qtdMoeda) {
		this.qtdMoeda = qtdMoeda;
	}
	public String getValorPagto() {
		return valorPagto;
	}
	public void setValorPagto(String valorPagto) {
		this.valorPagto = valorPagto;
	}
	public String getNumAtribBanco() {
		return numAtribBanco;
	}
	public void setNumAtribBanco(String numAtribBanco) {
		this.numAtribBanco = numAtribBanco;
	}
	public String getFiller3() {
		return filler3;
	}
	public void setFiller3(String filler3) {
		this.filler3 = filler3;
	}
	public String getQtdParcelas() {
		return qtdParcelas;
	}
	public void setQtdParcelas(String qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}
	public String getIndBloqueio() {
		return indBloqueio;
	}
	public void setIndBloqueio(String indBloqueio) {
		this.indBloqueio = indBloqueio;
	}
	public String getFormParc() {
		return formParc;
	}
	public void setFormParc(String formParc) {
		this.formParc = formParc;
	}
	public String getNumPeriodo() {
		return numPeriodo;
	}
	public void setNumPeriodo(String numPeriodo) {
		this.numPeriodo = numPeriodo;
	}
	public String getNumParcela() {
		return numParcela;
	}
	public void setNumParcela(String numParcela) {
		this.numParcela = numParcela;
	}
	public String getDataEfetivPagto() {
		return dataEfetivPagto;
	}
	public void setDataEfetivPagto(String dataEfetivPagto) {
		this.dataEfetivPagto = dataEfetivPagto;
	}
	public String getValorEfetivPagto() {
		return valorEfetivPagto;
	}
	public void setValorEfetivPagto(String valorEfetivPagto) {
		this.valorEfetivPagto = valorEfetivPagto;
	}
	public String getInformacao2() {
		return informacao2;
	}
	public void setInformacao2(String informacao2) {
		this.informacao2 = informacao2;
	}
	public String getCodFinaliDoc() {
		return codFinaliDoc;
	}
	public void setCodFinaliDoc(String codFinaliDoc) {
		this.codFinaliDoc = codFinaliDoc;
	}
	public String getUsoFebraban() {
		return usoFebraban;
	}
	public void setUsoFebraban(String usoFebraban) {
		this.usoFebraban = usoFebraban;
	}
	public String getAvisoFavor() {
		return avisoFavor;
	}
	public void setAvisoFavor(String avisoFavor) {
		this.avisoFavor = avisoFavor;
	}
	public String getOcor() {
		return ocor;
	}
	public void setOcor(String ocor) {
		this.ocor = ocor;
	}

	public String getDvAgeConta() {
		return dvAgeConta;
	}

	public void setDvAgeConta(String dvAgeConta) {
		this.dvAgeConta = dvAgeConta;
	}

	
}
