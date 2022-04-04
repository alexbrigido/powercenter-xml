package br.com.firstdecision.powercenter.remessa;

public class RegistroSegmentoJ {

	
	private Integer codigoBanco = 104;
	private Integer loteServico = 1;
	private Integer codigoRegistro = 3;
	private Integer nsr;
	private String codigoSegmento = "J";
	private Integer tipoMovimento = 0; // 0=inclusao; 9=exclusao
	private Integer codMovimento = 0; // inclusao de registro detalhe liberado
	
	// inico codigo de barras 44
	/*
	private Integer bancoDestino;
	private Integer codMoeda = 9;
	private Integer dvCodigoBarras;
	private Integer fatorVencimento;
	private Long valorDocumento;
	private String campoLivre;
	*/
	// fim codigo de barras
	private String codigoBarras;
	
	private String nomeCedente;
	private String dataVencimento; // DDMMAAAA
	private Long valorTitulo;
	private Long valorDescontoAbatimento;
	private Long valorMoraMulta;
	private String dataPagamento; // DDMMAAAA
	private Long valorPagamento;
	private Long quantidadeMoeda;
	private String numeroDocumentoAtribuidoEmpresa;
	private String filler1;
	private Long numeroDocumentoAtribuidoBanco;
	private String filler2;
	private Integer codigoMoeda = 9;
	private String usoFebraban;
	private String ocorrenciasRetorno = " ";
	
	
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
	public Integer getTipoMovimento() {
		return tipoMovimento;
	}
	public void setTipoMovimento(Integer tipoMovimento) {
		this.tipoMovimento = tipoMovimento;
	}
	public Integer getCodMovimento() {
		return codMovimento;
	}
	public void setCodMovimento(Integer codMovimento) {
		this.codMovimento = codMovimento;
	}
	/*
	public Integer getBancoDestino() {
		return bancoDestino;
	}
	public void setBancoDestino(Integer bancoDestino) {
		this.bancoDestino = bancoDestino;
	}
	public Integer getCodMoeda() {
		return codMoeda;
	}
	public void setCodMoeda(Integer codMoeda) {
		this.codMoeda = codMoeda;
	}
	public Integer getDvCodigoBarras() {
		return dvCodigoBarras;
	}
	public void setDvCodigoBarras(Integer dvCodigoBarras) {
		this.dvCodigoBarras = dvCodigoBarras;
	}
	public Integer getFatorVencimento() {
		return fatorVencimento;
	}
	public void setFatorVencimento(Integer fatorVencimento) {
		this.fatorVencimento = fatorVencimento;
	}
	public Long getValorDocumento() {
		return valorDocumento;
	}
	public void setValorDocumento(Long valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	public String getCampoLivre() {
		return campoLivre;
	}
	public void setCampoLivre(String campoLivre) {
		this.campoLivre = campoLivre;
	}
	*/
	public String getNomeCedente() {
		return nomeCedente;
	}
	public void setNomeCedente(String nomeCedente) {
		this.nomeCedente = nomeCedente;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public Long getValorTitulo() {
		return valorTitulo;
	}
	public void setValorTitulo(Long valorTitulo) {
		this.valorTitulo = valorTitulo;
	}
	public Long getValorDescontoAbatimento() {
		return valorDescontoAbatimento;
	}
	public void setValorDescontoAbatimento(Long valorDescontoAbatimento) {
		this.valorDescontoAbatimento = valorDescontoAbatimento;
	}
	public Long getValorMoraMulta() {
		return valorMoraMulta;
	}
	public void setValorMoraMulta(Long valorMoraMulta) {
		this.valorMoraMulta = valorMoraMulta;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	public Long getValorPagamento() {
		return valorPagamento;
	}
	public void setValorPagamento(Long valorPagamento) {
		this.valorPagamento = valorPagamento;
	}
	public Long getQuantidadeMoeda() {
		return quantidadeMoeda;
	}
	public void setQuantidadeMoeda(Long quantidadeMoeda) {
		this.quantidadeMoeda = quantidadeMoeda;
	}
	public String getNumeroDocumentoAtribuidoEmpresa() {
		return numeroDocumentoAtribuidoEmpresa;
	}
	public void setNumeroDocumentoAtribuidoEmpresa(String numeroDocumentoAtribuidoEmpresa) {
		this.numeroDocumentoAtribuidoEmpresa = numeroDocumentoAtribuidoEmpresa;
	}
	public String getFiller1() {
		return filler1;
	}
	public void setFiller1(String filler1) {
		this.filler1 = filler1;
	}
	public Long getNumeroDocumentoAtribuidoBanco() {
		return numeroDocumentoAtribuidoBanco;
	}
	public void setNumeroDocumentoAtribuidoBanco(Long numeroDocumentoAtribuidoBanco) {
		this.numeroDocumentoAtribuidoBanco = numeroDocumentoAtribuidoBanco;
	}
	public String getFiller2() {
		return filler2;
	}
	public void setFiller2(String filler2) {
		this.filler2 = filler2;
	}
	public Integer getCodigoMoeda() {
		return codigoMoeda;
	}
	public void setCodigoMoeda(Integer codigoMoeda) {
		this.codigoMoeda = codigoMoeda;
	}
	public String getUsoFebraban() {
		return usoFebraban;
	}
	public void setUsoFebraban(String usoFebraban) {
		this.usoFebraban = usoFebraban;
	}
	public String getOcorrenciasRetorno() {
		return ocorrenciasRetorno;
	}
	public void setOcorrenciasRetorno(String ocorrenciasRetorno) {
		this.ocorrenciasRetorno = ocorrenciasRetorno;
	}
	public String getCodigoBarras() {
		return codigoBarras;
	}
	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}
	
}
