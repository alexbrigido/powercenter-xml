package br.com.firstdecision.powercenter.window;

import java.awt.Container;
import java.awt.event.ActionListener;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

public class JanelaPrincipal extends JFrame {

	public void init(ActionListener action) {
		Container janela = getContentPane();
		setLayout(null);
		setTitle("Gerador de Remessas");
		
		// Define os rótulos dos botões
		JLabel labelCep = new JLabel("Convênio: ");
		JLabel labelTel = new JLabel("Compromisso: ");
		JLabel labelCpf = new JLabel("CPF: ");
		JLabel labelData = new JLabel("Data Pagamento: ");
		labelCep.setBounds(50, 40, 100, 20);
		labelTel.setBounds(50, 80, 100, 20);
		labelCpf.setBounds(50, 120, 100, 20);
		labelData.setBounds(50, 160, 100, 20);

		// Define as máscaras
		MaskFormatter mascaraCep = null;
		MaskFormatter mascaraTel = null;
		MaskFormatter mascaraCpf = null;
		MaskFormatter mascaraData = null;

		try {
			mascaraCep = new MaskFormatter("######");
			mascaraTel = new MaskFormatter("####");
			mascaraCpf = new MaskFormatter("#########-##");
			mascaraData = new MaskFormatter("##/##/####");
			mascaraCep.setPlaceholderCharacter('_');
			mascaraTel.setPlaceholderCharacter('_');
			mascaraCpf.setPlaceholderCharacter('_');
			mascaraData.setPlaceholderCharacter('_');
		} catch (ParseException excp) {
			System.err.println("Erro na formatação: " + excp.getMessage());
			System.exit(-1);
		}
		
		
        //Seta as máscaras nos objetos JFormattedTextField
        JFormattedTextField jFormattedTextCep = new JFormattedTextField(mascaraCep);
        JFormattedTextField jFormattedTextTel = new JFormattedTextField(mascaraTel);
        JFormattedTextField jFormattedTextCpf = new JFormattedTextField(mascaraCpf);
        JFormattedTextField jFormattedTextData = new JFormattedTextField(mascaraData);
        jFormattedTextCep.setBounds(200,40,100,20);
        jFormattedTextTel.setBounds(200,80,100,20);
        jFormattedTextCpf.setBounds(200,120,100,20);
        jFormattedTextData.setBounds(200,160,100,20);
        
        //Adiciona os rótulos e os campos de textos com máscaras na tela
        janela.add(labelCep);
        janela.add(labelTel);
        janela.add(labelCpf);
        janela.add(labelData);
        janela.add(jFormattedTextCep);
        janela.add(jFormattedTextTel);
        janela.add(jFormattedTextCpf);
        janela.add(jFormattedTextData);
        
        JButton botao = new JButton("GERAR");
        botao.setBounds(150, 200, 100, 20);
        botao.addActionListener(action);
        janela.add(botao);
        
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
	}

}
