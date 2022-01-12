package br.com.firstdecision.powercenter.remessa;

import java.io.File;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.firstdecision.powercenter.util.DataUtil;
import br.com.firstdecision.powercenter.xml.CamposChavePixExcel;

public class GeradorRemessa {
	
	
	public void gerarRemessa(List<CamposChavePixExcel> chaves, String convenio, String compromisso, String data, String nsa, String qtde) throws Exception {
		
        StreamFactory factory = StreamFactory.newInstance();
        InputStream is = getClass().getClassLoader().getResourceAsStream("layout_remessa.xml");
		factory.load(is);

        BeanWriter out = factory.createWriter("registro_remessa", new File("PIX8_"+DataUtil.localDateTimeToString(LocalDateTime.now())));
        
        out.write("header_arquivo", new HeaderArquivo(convenio, nsa));
        out.write("header_lote", new HeaderLote(convenio));
        
        Integer quantidade = Integer.parseInt(qtde);
        
        for(int i=0; i<quantidade; i++) {
        	int index = i > chaves.size() ? i - chaves.size() : i;
        	CamposChavePixExcel chave = chaves.get(index);
        	
        	RegistroSegmentoA registro = new RegistroSegmentoA();
        	registro.setNomeFavor(chave.getChave());
        	registro.setDataEfetivPagto(data);
        	registro.setDataPagto(data);
        	out.write("registro_segmento_a", registro);
        	
        }
        
        out.write("trailer_lote", new TrailerLote(quantidade));
        out.write("trailer_arquivo", new TrailerArquivo(quantidade));
        
        out.flush();
        out.close();
	}
	
}
