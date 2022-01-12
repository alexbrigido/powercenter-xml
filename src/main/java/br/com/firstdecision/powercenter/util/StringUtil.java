package br.com.firstdecision.powercenter.util;

import java.nio.charset.StandardCharsets;
import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Formatter;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {


    public static final String EMPTY = "";
    public static final Character WHITE_SPACE = ' ';
    private static final String[] EMPTY_STRING_ARRAY = new String[0];
    private static final List<String> PALAVRAS_PARA_NAO_NORMALIZAR_EM_NOMES = Arrays.asList("de", "dos", "do", "das", "da");

    @SuppressWarnings("unused")
    private static StringUtil instancia = new StringUtil();

    private StringUtil() {

    }

    public static boolean isNullOrBlank(String value) {
        return value == null ? true : value.replaceAll("\\s", "").isEmpty();
    }

    public static boolean isNullOrEmpty(String value) {
        return value == null ? true : value.trim().isEmpty();
    }

    public static String convertByteArrayToString(byte[] byteArray, String format) {
        StringBuilder sb = new StringBuilder();
        Formatter f = new Formatter(sb);
        for (byte b : byteArray) {
            f.format(format, b);
        }
        f.close();
        return sb.toString();
    }

    public static boolean isEmpty(String value) {
        return value == null ? true : value.trim().isEmpty();
    }

    public static String getKeyValue(String matrix, String key) {

        String[] aux = matrix.split(",");

        for (int i = 0; i < aux.length; i++) {

            String pointer = aux[i];

            if (pointer.indexOf(key) != -1) {
                int index = aux[i].indexOf('=');
                int length = aux[i].length();

                return pointer.substring(index + 1, length);
            }

        }
        return null;

    }

    public static String merge(Collection<String> strings) {
        return merge(", ", strings.toArray(EMPTY_STRING_ARRAY));
    }

    public static String merge(String separator, String... strings) {
        StringBuilder sb = new StringBuilder();
        for (String s : strings) {
            sb.append(s);
            sb.append(separator);
        }
        sb.delete(sb.length() - separator.length(), sb.length());
        return sb.toString();
    }

    public static String nullSafeTrim(String value) {
        return value == null ? value : value.trim();
    }

    public static String valueOf(Object objeto) {
        return objeto == null ? null : objeto.toString();
    }

    public static String completeAEsquerda(String value, int quantidadeMax, char c) {
        if (value == null || value.length() >= quantidadeMax) {
            return value;
        }
        StringBuilder sb = new StringBuilder();
        for (int i = value.length(); i < quantidadeMax; i++) {
            sb.append(c);
        }
        sb.append(value);
        return sb.toString();
    }

    public static String completeADireita(String value, int quantidadeMax, char c) {
        if (value == null || value.length() >= quantidadeMax) {
            return value;
        }
        StringBuilder sb = new StringBuilder(value);
        for (int i = value.length(); i < quantidadeMax; i++) {
            sb.append(c);
        }
        return sb.toString();

    }

    public static String[] splitDigitos(String valor, Integer quantidade) {
        Pattern compile = Pattern.compile("\\d{" + quantidade + "}");
        Matcher matcher = compile.matcher(valor);

        List<String> array = new ArrayList<String>();

        while (matcher.find()) {
            array.add(matcher.group());
        }
        return array.toArray(new String[] {});
    }

    public static String toLowerCase(String s) {
        return s.toLowerCase(LocaleUtil.PT_BR);
    }

    public static String toUpperCase(String s) {
        return s.toUpperCase(LocaleUtil.PT_BR);
    }

    public static byte[] getBytes(String s) {
        return s.getBytes(StandardCharsets.UTF_8);
    }

    public static int countOccurrences(String str, char c) {
        int count = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == c) {
                count++;
            }
        }
        return count;
    }

    public static boolean hasSomenteNumeros(String str) {
        return str.matches("\\d+");
    }

    public static boolean hasTamanhoValido(String str, int tam) {
        return str.length() == tam;
    }

    public static String removeNaoDigitos(String value) {
        if (isEmpty(value)) {
            return value;
        }
        return value.replaceAll("\\D", "");
    }

    public static String removeNaoAlfanumerico(String value) {
        if (isEmpty(value)) {
            return value;
        }
        return value.replaceAll("[^\\p{L}\\d]", "");
    }

    public static String removeNonASCIICharacters(String str) {
        //http://en.wikipedia.org/wiki/ASCII#ASCII_printable_characters
        return str.replaceAll("[^\\x20-\\x7e]", "");
    }

    public static String normalizaNomePessoaCompleto(String nomeCompleto) {
        if (isEmpty(nomeCompleto)) {
            return nomeCompleto;
        }
        StringBuilder nomeFinal = new StringBuilder();

        String nomeMinusculo = nomeCompleto.toLowerCase(new Locale("pt-BR"));

        String[] nomes = nomeMinusculo.split("\\s+");

        for (String parteNome : nomes) {
            if (!PALAVRAS_PARA_NAO_NORMALIZAR_EM_NOMES.contains(parteNome)) {

                char[] parteNomeArray = parteNome.toCharArray();
                parteNomeArray[0] = Character.toUpperCase(parteNomeArray[0]);
                nomeFinal.append(parteNomeArray);
            } else {

                nomeFinal.append(parteNome);
            }
            nomeFinal.append(' ');
        }

        nomeFinal.deleteCharAt(nomeFinal.length() - 1);
        return nomeFinal.toString();
    }

    public static String removeAcentosAndToUpper(String str) {
        return StringUtil.toUpperCase(Normalizer.normalize(str, Normalizer.Form.NFD).replaceAll("[^\\p{ASCII}]", ""));
    }

    public static String retornaTextoConcatenado(String separador, String... campos) {
        StringBuilder texto = new StringBuilder();
        int contador = 0;
        for (String campo : campos) {
            contador++;
            texto.append(campo.trim());
            texto.append(" ");
            if (campos.length == contador) {
                break;
            }
            texto.append(separador);
            texto.append(" ");
        }
        return texto.toString();
    }

    public static String retornaSimNao(boolean isSimNao) {
        return isSimNao ? "Sim" : "Não";
    }
    
	public static boolean isCPF(String numero) {
		return Objects.nonNull(numero) ? numero.trim().length() == 11 : false;
	}

	public static boolean isCNPJ(String numero) {
		return Objects.nonNull(numero) ? numero.trim().length() == 14 : false;
	}

}
