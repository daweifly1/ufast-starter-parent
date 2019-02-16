package com.xgit.bj.common.util;


import com.xgit.bj.common.print.ReflectUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class ObjectCompareUtil {

    /**
     * 获取指定类下面,某个字段的值(通过反射实现)
     *
     * @param <T>
     * @param dataSource
     * @param fieldName
     * @return
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static <T> Object getValueByFieldName(T dataSource, String fieldName) {
        Class objClass = dataSource.getClass();
        Field field;
        Object value = null;
        try {
            field = ReflectUtil.getField(objClass, fieldName);
            if (field == null) {
                return null;
            }
            String methodNameOfGet = ReflectUtil.getGetMethodNameByField(field);
            Method methodOfGet = objClass.getMethod(methodNameOfGet);
            // 设置对象中的字段
            value = methodOfGet.invoke(dataSource);
        } catch (Exception e) {
            log.warn("", e);
        }
        return value;
    }

}
