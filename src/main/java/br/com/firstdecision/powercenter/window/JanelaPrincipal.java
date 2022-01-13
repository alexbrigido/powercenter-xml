package br.com.firstdecision.powercenter.window;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import br.com.firstdecision.powercenter.remessa.GeradorRemessa;
import br.com.firstdecision.powercenter.util.DataUtil;
import br.com.firstdecision.powercenter.util.StringUtil;
import br.com.firstdecision.powercenter.xml.CamposChavePixExcel;
import br.com.firstdecision.powercenter.xml.LeitorExcel;

public class JanelaPrincipal extends JFrame {

    JFormattedTextField jFormattedTextConvenio;
    JFormattedTextField jFormattedTextCompromisso; 
    JFormattedTextField jFormattedTextTipoCompromisso; 
    JFormattedTextField jFormattedTextData;
    JFormattedTextField jFormattedTextNsa;
    JFormattedTextField jFormattedTextQtde;
    
	public void init() {
		Container janela = getContentPane();
		setLayout(null);
		setTitle("Gerador de Remessas CNAB 240 - PIX");
		
		// Define os rótulos dos botões
		JLabel labelConvenio = new JLabel("Convênio: ");
		JLabel labelCompromisso = new JLabel("Compromisso: ");
		JLabel labelTipoComp = new JLabel("Tipo Compromisso: ");
		JLabel labelData = new JLabel("Data Pagamento: ");
		JLabel labelNsa = new JLabel("Sequencial Remessa (NSA): ");
		JLabel labelQtde = new JLabel("Quantidade Registros: ");
		labelConvenio.setBounds(50, 40, 100, 20);
		labelCompromisso.setBounds(50, 80, 100, 20);
		labelTipoComp.setBounds(50, 120, 120, 20);
		labelData.setBounds(50, 160, 100, 20);
		labelNsa.setBounds(50, 200, 180, 20);
		labelQtde.setBounds(50, 240, 150, 20);

		// Define as máscaras
		MaskFormatter mascaraConvenio = null;
		MaskFormatter mascaraCompromisso = null;
		MaskFormatter mascaraTipoComp = null;
		MaskFormatter mascaraData = null;

		try {
			mascaraConvenio = new MaskFormatter("######");
			mascaraCompromisso = new MaskFormatter("####");
			mascaraTipoComp = new MaskFormatter("##");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraConvenio.setPlaceholderCharacter('_');
			mascaraCompromisso.setPlaceholderCharacter('_');
			mascaraTipoComp.setPlaceholderCharacter('_');
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		
		
        //Seta as máscaras nos objetos JFormattedTextField
        jFormattedTextConvenio = new JFormattedTextField(mascaraConvenio);
        jFormattedTextCompromisso = new JFormattedTextField(mascaraCompromisso);
        jFormattedTextTipoCompromisso = new JFormattedTextField(mascaraTipoComp);
        jFormattedTextData = new JFormattedTextField(mascaraData);
        jFormattedTextNsa = new JFormattedTextField();
        jFormattedTextQtde = new JFormattedTextField();
        jFormattedTextConvenio.setBounds(240,40,100,20);
        jFormattedTextCompromisso.setBounds(240,80,100,20);
        jFormattedTextTipoCompromisso.setBounds(240,120,100,20);
        jFormattedTextData.setBounds(240,160,100,20);
        jFormattedTextNsa.setBounds(240,200,100,20);
        jFormattedTextQtde.setBounds(240,240,100,20);
        
        // valores default
        jFormattedTextConvenio.setText("600500");
        jFormattedTextCompromisso.setText("0001");
        jFormattedTextTipoCompromisso.setText("01");
        jFormattedTextNsa.setText("000001");
        jFormattedTextQtde.setText("10");
        jFormattedTextData.setText(DataUtil.localDateToString(LocalDate.now(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        //Adiciona os rótulos e os campos de textos com máscaras na tela
        janela.add(labelConvenio);
        janela.add(labelTipoComp);
        janela.add(labelCompromisso);
        janela.add(labelData);
        janela.add(labelNsa);
        janela.add(labelQtde);
        janela.add(jFormattedTextConvenio);
        janela.add(jFormattedTextTipoCompromisso);
        janela.add(jFormattedTextCompromisso);
        janela.add(jFormattedTextData);
        janela.add(jFormattedTextNsa);
        janela.add(jFormattedTextQtde);
        
        JButton botao = new JButton("GERAR");
        botao.setBounds(150, 280, 100, 20);
        botao.addActionListener(action());
        janela.add(botao);
        
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}

	public ActionListener action() {
		return new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				perform(e);				
			}
			private void perform(ActionEvent evt) {

					try {
						
						LeitorExcel excel = new LeitorExcel();
						GeradorRemessa remessa = new GeradorRemessa();
						
						System.out.println("Lendo arquivo Excel...");
						Path path = Paths.get("");
						String fileExcel = path.toAbsolutePath().toString() + File.separator + "CHAVES-PIX.xlsx";
						System.out.println(fileExcel);
						List<CamposChavePixExcel> chaves = excel.gerarListaCampos(fileExcel);

						System.out.println("Gerando Remessa...");
						String convenio = StringUtil.completeAEsquerda(jFormattedTextConvenio.getText(), 6, '0');
						String compromisso = StringUtil.completeAEsquerda(jFormattedTextCompromisso.getText(), 4, '0');
						String data = DataUtil.removerBarras(jFormattedTextData.getText());
						String nsa = StringUtil.completeAEsquerda(jFormattedTextNsa.getText(), 6, '0');
						String qtde = jFormattedTextQtde.getText();
						remessa.gerarRemessa(chaves, convenio, compromisso, data, nsa, qtde);
						
						JOptionPane.showMessageDialog(null, "REMESSA GERADA COM SUCESSO.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
			}
		};
	}
	
}
