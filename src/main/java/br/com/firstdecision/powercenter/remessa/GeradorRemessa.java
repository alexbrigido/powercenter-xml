package br.com.firstdecision.powercenter.remessa;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import org.beanio.BeanIOConfigurationException;
import org.beanio.BeanWriter;
import org.beanio.StreamFactory;

import br.com.firstdecision.powercenter.util.DataUtil;
import br.com.firstdecision.powercenter.window.JanelaPrincipal;
import br.com.firstdecision.powercenter.xml.CamposChavePixExcel;

public class GeradorRemessa {
	
	
	public void gerarRemessa(List<CamposChavePixExcel> chaves) throws BeanIOConfigurationException, IOException {
		
		// create a BeanIO StreamFactory
        StreamFactory factory = StreamFactory.newInstance();
        // load the mapping file from the working directory
        InputStream is = getClass().getClassLoader().getResourceAsStream("layout_remessa.xml");
		factory.load(is);

        // create a BeanWriter to write to "output.csv"
        BeanWriter outSegA = factory.createWriter("registro_segmento_a", new File("PIX8_"+DataUtil.localDateTimeToString(LocalDateTime.now())));
        BeanWriter outSegB = factory.createWriter("registro_segmento_b", new File("PIX8_"+DataUtil.localDateTimeToString(LocalDateTime.now())));
        
        for(CamposChavePixExcel chave : chaves) {
        	RegistroSegmentoA registro = new RegistroSegmentoA();
        	registro.setNomeFavor(chave.getChave());
        	outSegA.write(registro);
        }
        outSegA.flush();
        outSegA.close();
	}
	
}
