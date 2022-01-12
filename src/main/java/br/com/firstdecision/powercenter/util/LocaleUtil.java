package br.com.firstdecision.powercenter.util;

import java.util.Locale;

public final class LocaleUtil {

    public static final Locale PT_BR = new Locale("pt", "BR");

    @SuppressWarnings("unused")
    private static LocaleUtil instancia = new LocaleUtil();

    private LocaleUtil() {
    }

}