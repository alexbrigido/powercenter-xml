package br.com.firstdecision.powercenter.util;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CodigoBarrasUtil {

	public static void main(String[] args) {
		// ATENÇÃO: Se você ainda não entende os dados abaixo.
		// revise as dicas anteriores antes de continuar
		String banco = "104";
		String moeda = "9";
		String carteira = "1"; //
		String emissao = "4"; // cedente
		String nossoNumero = "222333777777777";
		long valor = 49090L; // sempre duas casas decimais
		String vencimento = "23/06/2022";
		int fator = fatorVencimento(vencimento);
		String nossoNumeroCompleto = carteira + emissao + nossoNumero;
		String cedente = "005507";
		int dvCedente = dvCodigoCedente(cedente);
		String campoLivre = montarCampoLivre(cedente, dvCedente, nossoNumeroCompleto);
		String codigoBarras = montarCodigoBarras( vencimento,  valor);

		// vamos mostrar o resultado
		System.out.println("O código de barras completo é: " + codigoBarras);
	}

	// função que monta o código de barras
	public static String montarCodigoBarras(String vencimento, long valor) {

		String banco = "104";
		String moeda = "9";
		String carteira = "1"; //
		String emissao = "4"; // cedente
		String nossoNumero = "222333777777777";
		int fator = fatorVencimento(vencimento);
		String nossoNumeroCompleto = carteira + emissao + nossoNumero;
		String cedente = "005507";
		int dvCedente = dvCodigoCedente(cedente);
		String campoLivre = montarCampoLivre(cedente, dvCedente, nossoNumeroCompleto);
		
		// precisamos tratar o valor do título
//		NumberFormat nf = NumberFormat.getInstance();
//		String strValor = nf.format(valor);
//		strValor = strValor.replaceAll("\\.", "");
//		strValor = strValor.replaceAll(",", "");
		String strValor = StringUtil.zeroEsquerda(String.valueOf(valor), 10) ;//String.format("%1$10s", strValor).replace(' ', '0');

		// código de barras provisório
		String codigoBarras = banco + moeda + fator + strValor + campoLivre;

		// vamos calcular o dígito verificador
		int dvCodigoBarras = dvCodigoBarras(codigoBarras);

		// código de barras completo
		codigoBarras = banco + moeda + dvCodigoBarras + fator + strValor + campoLivre;

		return codigoBarras;
	}

	// função que monta o dígito verificador do código de barras
	public static int dvCodigoBarras(String codigoBarras) {
		// vamos definir os índices de múltiplicação
		String indices = "4329876543298765432987654329876543298765432";
		int soma = 0;

		// fazemos a multiplicação coluna por coluna agora
		// fazemos a multiplicação coluna por coluna agora
		for (int i = 0; i < codigoBarras.length(); i++) {
			soma = soma + Integer.parseInt(String.valueOf(codigoBarras.charAt(i)))
					* Integer.parseInt(String.valueOf(indices.charAt(i)));
		}

		// obtemos o resto da divisão da soma por onze
		int resto = soma % 11;

		// subtraímos onze pelo resto da divisão
		int digito = 11 - resto;

		// atenção: Se o resultado da subtração for
		// maior que 9 (nove) ou igual a 0, o dígito será 1 (um)
		if ((digito > 9) || (digito == 0)) {
			digito = 1;
		}

		System.out.println("O DV Geral do Código de Barras é: " + digito);

		return digito;
	}

	// recebe o vencimento no formato dia/mês/ano
	// e retorna o fator de vencimento
	public static int fatorVencimento(String vencimento) {
		int fator = 0;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dtVencimento = sdf.parse(vencimento);
			// vamos construir a data base 07/10/1997
			Date dtBase = sdf.parse("07/10/1997");

			// finalmente obtemos o fator de vencimento
			long fatorMilisegundos = dtVencimento.getTime() - dtBase.getTime();
			fator = (int) (fatorMilisegundos / (1000 * 60 * 60 * 24));
		} catch (ParseException erro) {
			System.out.println("Erro: " + erro.getMessage());
		}

		return fator;
	}

	// recebe o fator de vencimento e retorna a data
	// de vencimento no formato dia/mês/ano
	public static String obterVencimento(int fator) {
		String vencimento = "00/00/0000";

		// vamos construir a data base 07/10/1997
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date dtBase = sdf.parse("07/10/1997");

			// vamos adicionar dias a essa data
			Calendar cal = Calendar.getInstance();
			cal.setTime(dtBase);
			cal.add(Calendar.DAY_OF_MONTH, fator);

			// agora obtemos o vencimento
			vencimento = sdf.format(cal.getTime());
		} catch (ParseException erro) {
			System.out.println("Erro: " + erro.getMessage());
		}

		// e retornamos o vencimento no formato DD/MM/YYYY
		return vencimento;
	}

	// função que recebe o código do cedente
	// e retorna o seu dígito verificador
	public static int dvCodigoCedente(String cedente) {
		int digito = -1;

		// o código do cedente possui mais que 6 dígitos?
		if (cedente.length() > 6) {
			System.out.println("O código do cedente não pode ter " + " mais que 6 dígitos.");
			System.exit(1);
		}

		// primeiro vamos remover os espaços do número do cedente
		cedente = cedente.trim();
		// agora precisamos adicionar os zeros necessários
		// para completar 6 posições
		cedente = String.format("%1$6s", cedente).replace(' ', '0');
		// agora vamos definir os índices de múltiplicação
		String indices = "765432";
		// e aqui a soma da multiplicação coluna por coluna
		int soma = 0;

		// fazemos a multiplicação coluna por coluna agora
		// fazemos a multiplicação coluna por coluna agora
		for (int i = 0; i < cedente.length(); i++) {
			soma = soma + Integer.parseInt(String.valueOf(cedente.charAt(i)))
					* Integer.parseInt(String.valueOf(indices.charAt(i)));
		}

		// Obs.: Quando o Total da Soma for MENOR que o quociente (no
		// caso 11), pular o 3º PASSO, ou seja, o Total da Soma deverá ser
		// diminuído diretamente do quociente, obtendo-se o DV como
		// resultado.
		if (soma < 11) {
			digito = 11 - soma;
		} else {
			// obtemos o resto da divisão da soma por onze
			int resto = soma % 11;

			// subtraímos onze pelo resto da divisão
			digito = 11 - resto;
		}

		// atenção: Se o resultado da subtração for
		// maior que 9 (nove), o dígito será 0 (zero)
		if (digito > 9) {
			digito = 0;
		}

		return digito;
	}

	// monta as 25 posições do campo livre
	public static String montarCampoLivre(String cedente, int dvCedente, String nossoNumeroCompleto) {

		// muita atenção às subtrings. Revise e revise
		String campoLivre = cedente + dvCedente + nossoNumeroCompleto.substring(2, 5)
				+ nossoNumeroCompleto.substring(0, 1) + nossoNumeroCompleto.substring(5, 8)
				+ nossoNumeroCompleto.substring(1, 2) + nossoNumeroCompleto.substring(8, 17);

		// agora vamos calcular o dígito verificador do
		// campo livre
		int dvCampoLivre = dvCampoLivre(campoLivre);

		return campoLivre + dvCampoLivre;
	}

	// função que recebe o campo livre
	// e retorna o seu dígito verificador
	public static int dvCampoLivre(String campoLivre) {
		// agora vamos definir os índices de múltiplicação
		String indices = "987654329876543298765432";
		// e aqui a soma da multiplicação coluna por coluna
		int soma = 0;

		// fazemos a multiplicação coluna por coluna agora
		for (int i = 0; i < campoLivre.length(); i++) {
			soma = soma + Integer.parseInt(String.valueOf(campoLivre.charAt(i)))
					* Integer.parseInt(String.valueOf(indices.charAt(i)));
		}

		// obtemos o resto da divisão da soma por onze
		int resto = soma % 11;

		// subtraímos onze pelo resto da divisão
		int digito = 11 - resto;

		// atenção: Se o resultado da subtração for
		// maior que 9 (nove), o dígito será 0 (zero)
		if (digito > 9) {
			digito = 0;
		}

		return digito;
	}

	// função que recebe o nosso número
	// e retorna o seu dígito verificador
	public static int dvNossoNumero(String nossoNumero) {
		// o nosso número possui mais que 17 dígitos?
		if (nossoNumero.length() > 17) {
			System.out.println("O Nosso Número não pode ter " + " mais que 17 dígitos.");
			System.exit(1);
		}

		// agora vamos definir os índices de multiplicação
		String indices = "29876543298765432";
		// e aqui a soma da multiplicação coluna por coluna
		int soma = 0;

		// fazemos a multiplicação coluna por coluna agora
		for (int i = 0; i < nossoNumero.length(); i++) {
			soma = soma + Integer.parseInt(String.valueOf(nossoNumero.charAt(i)))
					* Integer.parseInt(String.valueOf(indices.charAt(i)));
		}

		// obtemos o resto da divisão da soma por onze
		int resto = soma % 11;

		// subtraímos onze pelo resto da divisão
		int digito = 11 - resto;

		// atenção: Se o resultado da subtração for
		// maior que 9 (nove), o dígito será 0 (zero)
		if (digito > 9) {
			digito = 0;
		}

		return digito;
	}
}
