package com.xgit.bj.common.print;

import com.xgit.bj.common.print.constant.ConstValueOfCommon;
import com.xgit.bj.common.util.CollectionUtil;
import com.xgit.bj.common.util.ObjectCompareUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
public class ReflectUtil {

    private static Map<Class, SoftReference<List<Field>>> CLASS_FIELDLIST_MAP = new ConcurrentHashMap();
    private static Map<Class, SoftReference<Map<String, Field>>> CLASS_FIELDMAP_MAP = new ConcurrentHashMap();
    private static Map<String, String> fieldClassGetMethodNameMap = new HashMap();
    private static Map<Class<?>, Object> classValueMapForCreatBean;

    static {
        fieldClassGetMethodNameMap.put("name", "getName");
        fieldClassGetMethodNameMap.put("value", "getValue");

        classValueMapForCreatBean = new HashMap();

        classValueMapForCreatBean.put(String.class, "");
        classValueMapForCreatBean.put(BigDecimal.class, BigDecimal.ZERO);
    }

    public static List<Field> getFieldListByClass(Class objClass) {
        if ((objClass == null) || (objClass == Object.class)) {
            return null;
        }
        List<Field> fieldListOfRet = (List) CollectionUtil.getValueBySoftMap(CLASS_FIELDLIST_MAP, objClass);
        if (fieldListOfRet != null) {
            return fieldListOfRet;
        }
        fieldListOfRet = new ArrayList();

        Field[] fieldArray = objClass.getDeclaredFields();
        Map<String, Field> fieldNameAndFieldMap = new HashMap();

        Class objSuperClass = objClass.getSuperclass();
        Map<String, Field> mapOfTmp = CollectionUtil.convertCollToMap(getFieldListByClass(objSuperClass), "name");
        CollectionUtil.putAllOfMap(fieldNameAndFieldMap, mapOfTmp);
        mapOfTmp = CollectionUtil.convertCollToMap(Arrays.asList(fieldArray), "name");
        CollectionUtil.putAllOfMap(fieldNameAndFieldMap, mapOfTmp);
        fieldListOfRet.addAll(fieldNameAndFieldMap.values());

        CollectionUtil.putValueBySoftMap(CLASS_FIELDLIST_MAP, objClass, fieldListOfRet);

        return fieldListOfRet;
    }

    public static List<Field> getFieldListByClassWithin(Class<?> objClass, String[] fieldNameArray) {
        boolean isWithout = false;
        return getFieldListByClass(objClass, fieldNameArray, isWithout);
    }

    public static List<Field> getFieldListByClassWithout(Class<?> objClass, String[] fieldNameArray) {
        boolean isWithout = true;
        return getFieldListByClass(objClass, fieldNameArray, isWithout);
    }

    private static List<Field> getFieldListByClass(Class<?> objClass, String[] fieldNameArray, boolean isWithout) {
        List<Field> fieldList = getFieldListByClass(objClass);
        if (CollectionUtils.isNotEmpty(fieldList)) {
            Iterator<Field> iter = fieldList.iterator();
            while (iter.hasNext()) {
                Field field = (Field) iter.next();
                String fieldName = field.getName();
                boolean isInArray = CollectionUtil.isInArrayIgnoreCase(fieldNameArray, fieldName);
                if (((isInArray) && (isWithout)) || ((!isInArray) && (!isWithout))) {
                    iter.remove();
                }
            }
        }
        return fieldList;
    }

    public static <T> Field getField(Class<T> objClass, String fieldName) {
        if ((objClass == null) || (objClass == Object.class)) {
            return null;
        }
        Map<String, Field> fieldMapOfRet = (Map) CollectionUtil.getValueBySoftMap(CLASS_FIELDMAP_MAP, objClass);
        if (fieldMapOfRet != null) {
            return (Field) fieldMapOfRet.get(fieldName);
        }
        List<Field> fieldList = getFieldListByClass(objClass);
        if (CollectionUtils.isEmpty(fieldList)) {
            return null;
        }
        Map<String, Field> mapOfTmp = CollectionUtil.convertCollToMap(fieldList, "name");
        if (mapOfTmp == null) {
            return null;
        }
        CollectionUtil.putValueBySoftMap(CLASS_FIELDMAP_MAP, objClass, mapOfTmp);

        return (Field) mapOfTmp.get(fieldName);
    }

    public static Method genMethod(Class objClass, Field field, boolean isGetMethod) {
        Class fieldTypeClass = field.getType();

        String methodName = isGetMethod ? getGetMethodNameByField(field) : getSetMethodNameByField(field);
        Method method = null;
        try {
            method = isGetMethod ? objClass.getMethod(methodName, new Class[0]) : objClass.getMethod(methodName, new Class[]{fieldTypeClass});
        } catch (Exception e) {
            e.printStackTrace();
        }
        return method;
    }

    public static String getSetMethodNameByField(Field field) {
        Class fieldTypeClass = field.getType();
        String fieldName = field.getName();
        String preffix = "set";
        if (((fieldTypeClass == Boolean.class) || (fieldTypeClass == Boolean.TYPE)) &&
                (fieldName.toLowerCase().startsWith("is"))) {
            fieldName = fieldName.substring(2);
        }
        return getMethodNameByFieldName(fieldName, preffix);
    }

    public static String getGetMethodNameByField(Field field) {
        Class fieldTypeClass = field.getType();
        String fieldName = field.getName();
        String preffix = "get";
        if (CollectionUtil.isInArray(ConstValueOfCommon.booleanClassArray, fieldTypeClass)) {
            preffix = fieldName.toLowerCase().startsWith("is") ? "" : "is";
        }
        return getMethodNameByFieldName(fieldName, preffix);
    }

    public static <T1, T2> T1 getValueByFieldName(T2 obj, String fieldName) {
        if ((obj == null) || (StringUtils.isBlank(fieldName))) {
            return null;
        }
        T1 value = null;
        if (((obj instanceof Field)) && (CollectionUtil.containsKeyOfMap(fieldClassGetMethodNameMap, fieldName))) {
            String methodNameOfGet = (String) CollectionUtil.getValueOfMapIgnoreCase(fieldClassGetMethodNameMap, fieldName);
            try {
                Method methodOfGet = Field.class.getMethod(methodNameOfGet, new Class[0]);
                value = (T1) methodOfGet.invoke(obj, new Object[0]);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } else {
            value = (T1) ObjectCompareUtil.getValueByFieldName(obj, fieldName);
        }
        return value;
    }

    private static String getMethodNameByFieldName(String fieldName, String preffix) {
        StringBuilder methodName = new StringBuilder(16);
        methodName.append(preffix);
        methodName.append(PrintStringUtils.setFirstCharToUpperCase(fieldName));

        return PrintStringUtils.setFirstCharToLowerCase(methodName.toString());
    }

    public static <T> String genToString(T obj) {
        return genToString(obj, null);
    }

    public static <T> String genToString(T obj, String[] fieldNameArray) {
        StringBuilder sb = new StringBuilder(256);
        Map<String, String> map = genToStringOfMap(obj, fieldNameArray);
        if (CollectionUtil.isNotEmptyOfMap(map)) {
            for (String name : map.keySet()) {
                String value = (String) map.get(name);
                PrintStringUtils.appendValueForToString(sb, name, value);
            }
        }
        return sb.toString();
    }

    private static <T> Map<String, String> genToStringOfMap(T obj, String[] fieldNameArray) {
        Map<String, String> map = new LinkedHashMap();
        Class objClass = obj.getClass();
//        Field field;
//        String fieldName;
        if (null == fieldNameArray || fieldNameArray.length < 1) {
            List<Field> fieldList = getFieldListByClass(objClass);
            for (Iterator localIterator = fieldList.iterator(); localIterator.hasNext(); ) {
                Field field = (Field) localIterator.next();
                if (!Modifier.isFinal(field.getModifiers())) {
                    String fieldName = field.getName();
                    updateMapForGenToStringOfMap(fieldName, field, obj, map);
                }
            }
        } else {
            int localField1 = fieldNameArray.length;
            for (int i = 0; i < localField1; i++) {
                String fieldName = fieldNameArray[i];
                if (!StringUtils.isBlank(fieldName)) {
                    try {
                        Field field = objClass.getDeclaredField(fieldName);
                        updateMapForGenToStringOfMap(fieldName, field, obj, map);
                    } catch (Exception ex) {
                        log.debug(ex.getMessage());
                    }
                }
            }
        }
        return map;
    }

    private static <T> void updateMapForGenToStringOfMap(String fieldName, Field field, T obj, Map<String, String> map) {
        try {
            Class<?> objClass = obj.getClass();
            String methodNameOfGet = getGetMethodNameByField(field);
            Method methodOfGet = objClass.getMethod(methodNameOfGet, new Class[0]);

            Object value = methodOfGet.invoke(obj, new Object[0]);
            map.put(fieldName, String.valueOf(value));
        } catch (Exception ex) {
            log.debug(ex.getMessage());
        }
    }

    public static <T> T cloneObj(T objSrc) {
        T objRet = null;
        if (objSrc == null) {
            return objRet;
        }
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(out);
            oo.writeObject(objSrc);
            out.flush();
            out.close();

            ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(in);
            objRet = (T) oi.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objRet;
    }

    public static <T1, T2> List<T1> convertList(Class<T1> desClass, List<T2> souList, boolean isFilterEnum) {
        List<T1> desList = new ArrayList();
        if ((souList == null) || (souList.size() == 0)) {
            return desList;
        }
        for (T2 sou : souList) {
            T1 des = convertObj(desClass, sou, isFilterEnum);
            if (des != null) {
                desList.add(des);
            }
        }
        return desList;
    }

    public static <T1, T2> T1 convertObj(Class<T1> desClass, T2 sou, boolean isFilterEnum) {
        T1 des = null;
        try {
            des = desClass.newInstance();
            convertObj(des, sou, isFilterEnum);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return des;
    }

    public static <T1, T2> void convertObj(T1 des, T2 sou, boolean isFilterEnum) {
        if ((des == null) || (sou == null)) {
            return;
        }
        Class desClass = des.getClass();
        Class souClass = sou.getClass();
        List<Field> fieldList = getFieldListByClass(souClass);
        if (CollectionUtils.isEmpty(fieldList)) {
            return;
        }
        for (Field field : fieldList) {
            Class fieldTypeClass = field.getType();
            if (((!isFilterEnum) || (!fieldTypeClass.isEnum())) && (!Modifier.isFinal(field.getModifiers()))) {
                String fieldName = field.getName();
                if ((getField(souClass, fieldName) != null) && (getField(desClass, fieldName) != null)) {
                    try {
                        String methodNameOfGet = getGetMethodNameByField(field);
                        String methodNameOfSet = getSetMethodNameByField(field);

                        Method methodOfGet = souClass.getMethod(methodNameOfGet, new Class[0]);
                        Method methodOfSet = desClass.getMethod(
                                methodNameOfSet, new Class[]{fieldTypeClass});

                        Object value = methodOfGet.invoke(sou, new Object[0]);
                        methodOfSet.invoke(des, new Object[]{value});
                    } catch (Exception ex) {
                        log.debug("", ex);
                    }
                }
            }
        }
    }
}
