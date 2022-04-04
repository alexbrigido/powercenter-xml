package br.com.firstdecision.powercenter.remessa;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.firstdecision.powercenter.util.CodigoBarrasUtil;
import br.com.firstdecision.powercenter.util.Constantes;
import br.com.firstdecision.powercenter.util.DataUtil;
import br.com.firstdecision.powercenter.util.RandomUtil;
import br.com.firstdecision.powercenter.util.StringUtil;

public class GeradorRemessaBoleto {
	
	
	/*
	 * campos de entrada do usuario:
	 * Tipo de movimento [usuario decide 0, 9]
	 * banco destino - usuario escolhe
	 * valor de desconto
	 * valor de multa
	 * valor do pagamento - usuario escolhe
	 * data de vencimento - usuario escolhe o calendario
	 * numero agendamento do cliente - geral automatico
	 * 
	 * */
	
	public void gerarRemessa(String convenio, String compromisso, String dataVencimento, String nsa, String qtde, boolean geraJ52) throws Exception {
		
        StreamFactory factory = StreamFactory.newInstance();
        InputStream is = getClass().getClassLoader().getResourceAsStream("layout_remessa.xml");
		factory.load(is);

		String fileRemessa = System.getProperty("user.dir") + File.separator + "BOLETO_"+DataUtil.localDateTimeToString(LocalDateTime.now()) + ".REM";
        BeanWriter out = factory.createWriter("registro_remessa", new File(fileRemessa));
        
        out.write("header_arquivo", new HeaderArquivo(convenio, nsa));
        out.write("header_lote", new HeaderLote(convenio));
        
        int quantidade = Integer.parseInt(qtde.trim());
        int sequencial = 1;
        long soma = 0L;
        for(int i=0; i<quantidade; i++) {
        	String nomeBeneficiario = Constantes.favorecidos.get(RandomUtil.random(Constantes.favorecidos.size()));
        	RegistroSegmentoJ registro = new RegistroSegmentoJ();
        	registro.setNsr(sequencial);
        	registro.setNomeCedente(nomeBeneficiario);
        	registro.setDataPagamento(DataUtil.removerBarras(dataVencimento));
        	registro.setDataVencimento(DataUtil.removerBarras(dataVencimento));
        	int val = RandomUtil.random(1000000);
//        	String valor = StringUtil.completeAEsquerda(String.valueOf(val), 15, '0');
        	soma+=val;
        	
        	registro.setValorPagamento((long) val);
        	registro.setValorDescontoAbatimento(0L);
        	registro.setValorMoraMulta(0L);
        	registro.setValorTitulo((long) val);
        	registro.setQuantidadeMoeda(1L);
        	registro.setNomeCedente("NOME DO CEDENTE");
        	registro.setNumeroDocumentoAtribuidoEmpresa(String.valueOf(RandomUtil.random(10000)));
        	
        	
        	String barra = CodigoBarrasUtil.montarCodigoBarras(dataVencimento, val);
        	registro.setCodigoBarras(barra);
        	out.write("registro_segmento_j", registro);
        	
        	/*
        	 * tipo pagador - usuario escolher
        	 * numero pagador - usuario escolher
        	 * nome pagador - usuario escolher (opcional - "NOME DO PAGADOR/CONVENENTE")
        	 * 
        	 * tipo beneficiario - usuario escolher (opcional - )
        	 * numero beneficiario - usuario escolher (opcinal - )
        	 * nome beneficiario - usuario escolher (opcional - )
        	 * 
        	 * tipo sacador - opcional
        	 * numero sacador - opcional
        	 * nome - opcional
        	 * */
        	if(geraJ52) {
        		RegistroSegmentoJ52 reg = new RegistroSegmentoJ52();
        		reg.setNsr(++sequencial);
        		reg.setTipoInscricaoPagador(2); // cnpj
        		reg.setNumeroInscricaoPagador(489828001046L);
        		reg.setNomePagador("CONVENIO 600500 EMPRESA XPTO");
        		reg.setTipoInscricaoBeneficiario(1); // cpf
        		reg.setNumeroInscricaoBeneficiario(10020030088L);
        		reg.setNomeBeneficiario(nomeBeneficiario);
        		out.write("registro_segmento_j52", reg);        		
        	}
        	
        	sequencial++;
        }
        
        out.write("trailer_lote", new TrailerLote((sequencial + 1), soma));
        out.write("trailer_arquivo", new TrailerArquivo(sequencial + 3));
        
        out.flush();
        out.close();
	}
	
	public static void main(String[] args) {
		GeradorRemessaBoleto g = new GeradorRemessaBoleto();
		try {
			g.gerarRemessa("600500", "0001", "20/04/2022", "000012", "2", true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
