package br.com.firstdecision.powercenter.remessa;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.firstdecision.powercenter.util.Constantes;
import br.com.firstdecision.powercenter.util.DataUtil;
import br.com.firstdecision.powercenter.util.RandomUtil;
import br.com.firstdecision.powercenter.util.StringUtil;
import br.com.firstdecision.powercenter.xml.CamposChavePixExcel;

public class GeradorRemessa {
		
	public void gerarRemessa(List<CamposChavePixExcel> chaves, String convenio, String compromisso, String data, String nsa, String qtde) throws Exception {
		
        StreamFactory factory = StreamFactory.newInstance();
        InputStream is = getClass().getClassLoader().getResourceAsStream("layout_remessa.xml");
		factory.load(is);

		String fileRemessa = System.getProperty("user.dir") + File.separator + "PIX8_"+DataUtil.localDateTimeToString(LocalDateTime.now());
        BeanWriter out = factory.createWriter("registro_remessa", new File(fileRemessa));
        
        out.write("header_arquivo", new HeaderArquivo(convenio, nsa));
        out.write("header_lote", new HeaderLote(convenio));
        
        int quantidade = Integer.parseInt(qtde.trim());
        int sequencial = 1;
        long soma = 0L;
        for(int i=0; i<quantidade; i++) {
        	CamposChavePixExcel chave = getChave(chaves, i);
        	
        	RegistroSegmentoA registro = new RegistroSegmentoA();
        	registro.setNomeFavor(Constantes.favorecidos.get(RandomUtil.random(Constantes.favorecidos.size())));
        	registro.setDataEfetivPagto(data);
        	registro.setDataPagto(data);
        	Integer val = RandomUtil.random(100000);
        	String valor = StringUtil.completeAEsquerda(String.valueOf(val), 15, '0');
        	soma+=val;
        	registro.setValorEfetivPagto(valor);
        	registro.setValorPagto(valor);
        	registro.setNsr(sequencial);
        	registro.setTipoMoeda("BRL");
        	registro.setQtdMoeda("000000000000000");
        	registro.setQtdParcelas("00");
        	registro.setNumParcela("00");
        	registro.setFormParc("0");
        	registro.setCodFinaliDoc("01");
        	registro.setNumAtribEmpr("150000");
        	registro.setTipoConta("0");
        	registro.setAvisoFavor("0");
        	out.write("registro_segmento_a", registro);
        	
        	RegistroSegmentoB regb = new RegistroSegmentoB();
        	regb.setFormaIniciacao(resolverIniciacao(chave.getTipo()));
        	regb.setInformacao10Txid("004".equals(regb.getFormaIniciacao()) ? " " : chave.getTipo());
        	regb.setInformacao11IdPagamento(resolverCampoB11(regb.getFormaIniciacao(), chave.getTipo()));
        	regb.setInformacao12IdFavorecidoChavePix(resolverChavePix(chave.getTipo(), chave.getChave()));
			regb.setSequencialRegistro(++sequencial);
        	out.write("registro_segmento_b", regb);
        	sequencial++;
        }
        
        out.write("trailer_lote", new TrailerLote((sequencial + 1), soma));
        out.write("trailer_arquivo", new TrailerArquivo(sequencial + 3));
        
        out.flush();
        out.close();
	}
	
	private CamposChavePixExcel getChave(List<CamposChavePixExcel> chaves, int indiceAtual) {
		int index = indiceAtual >= chaves.size() ? RandomUtil.random(chaves.size()) : indiceAtual;
    	return chaves.get(index);
	}
	
	private String resolverIniciacao(String tipo) {
		if(tipo.contains("TELEF") || tipo.contains("PHONE") || tipo.contains("CEL")) {
			return "001";
		}
		if(tipo.contains("EMAIL") || tipo.contains("MAIL")) {
			return "002";
		}
		if(tipo.contains("CPF") || tipo.contains("CNPJ")) {
			return "003";
		}
		if(tipo.contains("ALEAT")) {
			return "004";
		}
		return "005";
	}
	
	private String resolverCampoB11(String forma, String tipo) {
		switch (Integer.parseInt(forma)) {
		case 1:return "TELEFONE";
		case 2:return "E-MAIL";
		case 3:return tipo;
		case 4:return "CHAVE ALEATORIA";
		case 5:return "CONTA";
		default: return "CONTA";
		}
	}
	
	private String resolverChavePix(String tipo, String chave) {
		if(tipo.contains("CPF")) {
			return StringUtil.completeAEsquerda(chave, 11, '0');
		}
		if(tipo.contains("CNPJ")) {
			return StringUtil.completeAEsquerda(chave, 14, '0');
		}
		return chave;
	}
	
}
