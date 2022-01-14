package br.com.firstdecision.powercenter.window;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
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
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import br.com.firstdecision.powercenter.remessa.GeradorRemessa;
import br.com.firstdecision.powercenter.util.DataUtil;
import br.com.firstdecision.powercenter.util.StringUtil;
import br.com.firstdecision.powercenter.xml.CamposChavePixExcel;
import br.com.firstdecision.powercenter.xml.LeitorExcel;

public class JanelaPrincipal extends JFrame {

    JTextField jTextConvenio;
    JTextField jTextCompromisso; 
    JTextField jTextTipoCompromisso; 
    JFormattedTextField jFormattedTextData;
    JTextField jTextNsa;
    JTextField jTextQtde;
    
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
        jTextConvenio = new JTextField(new LengthRestrictedDocument(6), "600500", 0);
        jTextCompromisso = new JTextField(new LengthRestrictedDocument(4), "0001", 0);
        jTextTipoCompromisso = new JTextField(new LengthRestrictedDocument(2), "01", 0);
        jFormattedTextData = new JFormattedTextField(mascaraData);
        jTextNsa = new JTextField(new LengthRestrictedDocument(6), "000001", 0);
        jTextQtde = new JTextField(new LengthRestrictedDocument(6), "10", 0);
        jTextConvenio.setBounds(240,40,100,20);
        jTextCompromisso.setBounds(240,80,100,20);
        jTextTipoCompromisso.setBounds(240,120,100,20);
        jFormattedTextData.setBounds(240,160,100,20);
        jTextNsa.setBounds(240,200,100,20);
        jTextQtde.setBounds(240,240,100,20);

        jTextQtde.setToolTipText("Quantidade de registros A/B a serem gerados.");
        jFormattedTextData.setText(DataUtil.localDateToString(LocalDate.now(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        //Adiciona os rótulos e os campos de textos com máscaras na tela
        janela.add(labelConvenio);
        janela.add(labelTipoComp);
        janela.add(labelCompromisso);
        janela.add(labelData);
        janela.add(labelNsa);
        janela.add(labelQtde);
        janela.add(jTextConvenio);
        janela.add(jTextTipoCompromisso);
        janela.add(jTextCompromisso);
        janela.add(jFormattedTextData);
        janela.add(jTextNsa);
        janela.add(jTextQtde);
        
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
						validar("Convênio", jTextConvenio.getText());
						validar("Data Pagamento", jFormattedTextData.getText());
						validar("NSA", jTextNsa.getText());
						validar("Quantidade de Registros", jTextQtde.getText());
						
						LeitorExcel excel = new LeitorExcel();
						GeradorRemessa remessa = new GeradorRemessa();
						
						System.out.println("Lendo arquivo Excel...");
						Path path = Paths.get("");
						String fileExcel = path.toAbsolutePath().toString() + File.separator + "CHAVES-PIX.xlsx";
						System.out.println(fileExcel);
						List<CamposChavePixExcel> chaves = excel.gerarListaCampos(fileExcel);

						System.out.println("Gerando Remessa...");
						String convenio = StringUtil.completeAEsquerda(jTextConvenio.getText(), 6, '0');
						String compromisso = StringUtil.completeAEsquerda(jTextCompromisso.getText(), 4, '0');
						String data = DataUtil.removerBarras(jFormattedTextData.getText());
						String nsa = StringUtil.completeAEsquerda(jTextNsa.getText(), 6, '0');
						String qtde = jTextQtde.getText();
						remessa.gerarRemessa(chaves, convenio, compromisso, data, nsa, qtde);
						
						JOptionPane.showMessageDialog(null, "REMESSA GERADA COM SUCESSO.", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
			}
			
			private void validar(String campo, String valor) {
				if(StringUtil.isNullOrEmpty(valor)) {
					throw new RuntimeException("O campo "+campo+" é obrigatório.");
				}
			}
		};
	}
	
}
