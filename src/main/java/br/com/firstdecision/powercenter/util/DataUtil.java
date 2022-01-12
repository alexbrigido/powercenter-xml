package br.com.firstdecision.powercenter.util;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DataUtil {

    public static final String DIA_HORA = "yyyyMMdd_HHmmss";

    public static final Long QTD_MILISEGUNDOS_DIA = 86400000L;


    @SuppressWarnings("unused")
    private static DataUtil instancia = new DataUtil();

    private static final Map<Integer, String> NOMES_MESES = new HashMap<Integer, String>();
    private static final Map<Integer, String> NOMES_MESES_ABREVIADOS = new HashMap<Integer, String>();
    private static final DateTimeFormatter DATA_HORA_PATTERN = DateTimeFormatter.ofPattern(DataUtil.DIA_HORA);
    private static final int A_YEAR = 1970;
    private static final Month A_MONTH = Month.JANUARY;
    private static final int A_DAY = 1;

    private DataUtil() {
    }

    public static String getNomeMes(int numeroMes) {
        return NOMES_MESES.get(numeroMes);
    }

    public static String getAbreviacaoMes(int numeroMes) {
        return NOMES_MESES_ABREVIADOS.get(numeroMes);
    }

    public static LocalTime stringToLocalTime(String value) {
        return LocalTime.parse(value, DATA_HORA_PATTERN);
    }

    public static LocalDate stringToLocalDate(String value) {
        return LocalDate.parse(value, DATA_HORA_PATTERN);
    }

    public static LocalDateTime stringToLocalDateTime(String value) {
        return LocalDateTime.parse(value, DATA_HORA_PATTERN);
    }

    public static LocalTime stringToLocalTimeSafe(String value) {
        if (value == null) {
            return null;
        }
        return LocalTime.parse(value, DATA_HORA_PATTERN);
    }

    public static LocalDate stringToLocalDateSafe(String value) {
        if (value == null) {
            return null;
        }
        return LocalDate.parse(value, DATA_HORA_PATTERN);
    }

    public static LocalDateTime stringToLocalDateTimeSafe(String value) {
        if (value == null) {
            return null;
        }
        return LocalDateTime.parse(value, DATA_HORA_PATTERN);
    }

    public static String localTimeToString(LocalTime value) {
        return localTimeToString(value, DATA_HORA_PATTERN);
    }

    public static String localTimeToString(LocalTime value, DateTimeFormatter pattern) {
        return value.format(pattern);
    }

    public static String localDateToString(LocalDate value) {
        return localDateToString(value, DATA_HORA_PATTERN);
    }

    public static String localDateToString(LocalDate value, DateTimeFormatter formatter) {
        return value.format(formatter);
    }

    public static String localDateTimeToString(LocalDateTime value) {
        return localDateTimeToString(value, DATA_HORA_PATTERN);
    }

    public static String localDateTimeToString(LocalDateTime value, DateTimeFormatter pattern) {
        return value.format(pattern);
    }

    public static Date localDateTimeToDate(LocalDateTime value) {
        return Date.from(value.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Date localDateToDate(LocalDate value) {
        return Date.from(value.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static Date localTimeToDate(LocalTime value) {
        Instant instant = value.atDate(LocalDate.of(A_YEAR, A_MONTH, A_DAY)).atZone(ZoneId.systemDefault()).toInstant();
        return Date.from(instant);
    }

    public static Calendar localDateTimeToCalendar(LocalDateTime value) {
        return GregorianCalendar.from(value.atZone(ZoneId.systemDefault()));
    }

    public static Calendar localDateToCalendar(LocalDate value) {
        return GregorianCalendar.from(value.atStartOfDay(ZoneId.systemDefault()));
    }

    public static Calendar localTimeToCalendar(LocalTime value) {
        ZonedDateTime instant = value.atDate(LocalDate.of(A_YEAR, A_MONTH, A_DAY)).atZone(ZoneId.systemDefault());
        return GregorianCalendar.from(instant);
    }

    public static Date localDateToDateSafe(LocalDate value) {
        if (value == null) {
            return null;
        }
        return localDateToDate(value);
    }

    public static Date localTimeToDateSafe(LocalTime value) {
        if (value == null) {
            return null;
        }
        return localTimeToDate(value);
    }

    public static LocalDateTime dateToLocalDateTime(Date data) {
        return data.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

    }
    
    public static Date stringToDate(String data, String format) {
    	try {
			return new SimpleDateFormat(format).parse(data);
		} catch (Exception e) {
			throw new RuntimeException("Data com formato inválido: "+data+"."+
					"Formate a data com o padrão: AAAA-MM-DD.");
		}
    }
    
    public static java.sql.Date toSqlDate(Date data){
    	return Objects.nonNull(data) ? new java.sql.Date(data.getTime()) : null;
    }
    
    /**
     * Incrementa os meses de uma determinada data.
     * Retorna a data no formato String DD/MM/AAAA
     * */
    public static String addMes(LocalDate data, long meses) {
    	LocalDate local = data.plusMonths(meses);
    	return localDateToString(local);
    }
	
	public static LocalDate dateToLocalDate(Date dateToConvert) {
	    return new java.sql.Date(dateToConvert.getTime()).toLocalDate();
	}


}
