package com.xgit.bj.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;

import java.util.Locale;
import java.util.ResourceBundle;

@Slf4j
public class PropertyUtils {

    public static String config = "config";

    private ResourceBundle bundle = null;

    public PropertyUtils(String baseName) {
        if (StringUtils.isBlank(baseName)) {
            baseName = config;
        }
        Locale local = new Locale("zh", "CN");
        try {
            bundle = ResourceBundle.getBundle(baseName, local);
        } catch (Exception e) {
            log.error("load properties error", e);
        }
    }

    public String getProperty(String key) {
        String value = "";
        try {
            value = bundle.getString(key);
        } catch (Exception e) {
            log.error("get properties error, key: {}", key, e);
        }
        return value;
    }
}
