package com.xgit.bj.common.print.constant;

import java.math.BigDecimal;
import java.math.MathContext;

public class ConstValueOfCommon {
    public static Class[] numberClassArray = {Integer.class, Integer.TYPE, Long.class, Long.TYPE};
    public static Class[] booleanClassArray = {Boolean.class, Boolean.TYPE};
    public static final Integer INTEGER_ZERO = Integer.valueOf(0);
    public static final Integer INTEGER_ONE = Integer.valueOf(1);
    public static final Integer INTEGER_TWO = Integer.valueOf(2);
    public static final BigDecimal BIGDECIMAL_2 = new BigDecimal("2");
    public static final BigDecimal BIGDECIMAL_5 = new BigDecimal("5");
    public static final BigDecimal BIGDECIMAL_10 = new BigDecimal("10");
    public static final BigDecimal BIGDECIMAL_100 = new BigDecimal("100");
    public static final BigDecimal BIGDECIMAL_P01 = new BigDecimal("0.01");
    public static final String STRING_EMPTY = "";
    public static final MathContext MC = new MathContext(10);
    public static final int INT_INVALID = 0;
    public static final int INT_VALID = 1;
    public static final int INT_FAIL = 0;
    public static final int INT_SUCCESS = 1;
    public static final String SPLIT_FIRST = ",";
    public static final String SPLIT_SECOND = ";";
    public static final String SPLIT_THIRD = "#";
    public static final String SPLIT_KEY_VALUE = ":";
    public static final String SPLIT_OR = "or";
    public static final String SPLIT_AND = "and";
    public static final String SPLIT_PICURL = "@@@";
    public static final String BASE64_PREFFIX = "##BASE64##";
    public static final String MC_SUBKEY_NAMESPLIT = " ";
    public static final String MC_SUBKEY_VALUESPLIT = ",";
}
