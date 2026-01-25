package com.tinnt.finance_service.common.util;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CodeGenerator {

    private static final SecureRandom RND = new SecureRandom();
    private static final char[] ALPHANUM =
            "ABCDEFGHJKLMNPQRSTUVWXYZ23456789".toCharArray();

    private CodeGenerator() {}

    public static String generate(String prefix) {
        String date = LocalDate.now()
                .format(DateTimeFormatter.BASIC_ISO_DATE);

        StringBuilder sb = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            sb.append(ALPHANUM[RND.nextInt(ALPHANUM.length)]);
        }
        return prefix + "-" + date + "-" + sb;
    }

}
