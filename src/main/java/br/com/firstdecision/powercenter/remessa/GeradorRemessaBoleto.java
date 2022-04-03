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
		
	public void gerarRemessa(String convenio, String compromisso, String dataPagamento, String dataVencimento, String nsa, String qtde, boolean geraJ52) throws Exception {
		
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
        	
        	RegistroSegmentoJ registro = new RegistroSegmentoJ();
        	registro.setNsr(sequencial);
        	registro.setNomeCedente(Constantes.favorecidos.get(RandomUtil.random(Constantes.favorecidos.size())));
        	registro.setDataPagamento(dataPagamento);
        	registro.setDataVencimento(dataVencimento);
        	Integer val = RandomUtil.random(100000);
        	String valor = StringUtil.completeAEsquerda(String.valueOf(val), 15, '0');
        	soma+=val;
        	
        	String barra = CodigoBarrasUtil.montarCodigoBarras(dataVencimento, Long.parseLong(valor));
        	
        	
        	

        	out.write("registro_segmento_j", registro);
        	
        	if(geraJ52) {
        		RegistroSegmentoJ52 reg = new RegistroSegmentoJ52();
        		reg.setNsr(++sequencial);
        		out.write("registro_segmento_j52", reg);        		
        	}
        	
        	sequencial++;
        }
        
        out.write("trailer_lote", new TrailerLote((sequencial + 1), soma));
        out.write("trailer_arquivo", new TrailerArquivo(sequencial + 3));
        
        out.flush();
        out.close();
	}
	
}
