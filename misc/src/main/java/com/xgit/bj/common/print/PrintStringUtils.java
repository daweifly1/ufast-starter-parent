package com.xgit.bj.common.print;

import org.apache.commons.lang.StringUtils;

import java.math.BigDecimal;

public final class PrintStringUtils {
    public static String getStringOfBigDecimal(BigDecimal value) {
        return value != null ? value.toString() : BigDecimal.ZERO.toString();
    }

    public static String getStringOfStringIgnoreCase(String value) {
        return StringUtils.trimToEmpty(value).toLowerCase();
    }

    public static String setFirstCharToLowerCase(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        if (value.length() == 1) {
            return value.toLowerCase();
        }
        return value.substring(0, 1).toLowerCase() + value.substring(1);
    }

    public static String setFirstCharToUpperCase(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        if (value.length() == 1) {
            return value.toUpperCase();
        }
        return value.substring(0, 1).toUpperCase() + value.substring(1);
    }

    public static void appendValueForToString(StringBuilder sb, String name, String value) {
        sb.append(name);
        sb.append(":");
        sb.append(value);
        sb.append(",");
    }
}
