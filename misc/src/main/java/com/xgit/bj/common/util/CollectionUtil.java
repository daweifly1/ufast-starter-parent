package com.xgit.bj.common.util;

import com.xgit.bj.common.print.constant.ConstValueOfCommon;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import java.lang.ref.SoftReference;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.function.Function;

/**
 * 集合工具类
 */
@Slf4j
public class CollectionUtil {

    public static <E, T> List<T> getPropertyList(List<E> list, Function<E, T> function) {
        List<T> idList = new ArrayList();
        for (E e : list) {
            idList.add(function.apply(e));
        }
        return idList;
    }

    /**
     * 分页处理idList
     *
     * @param idList      id列表
     * @param pageSize    每页大小
     * @param pageProcess 回调
     * @param args
     */
    public static <T> void split(List<T> idList, int pageSize, PageProcess<T> pageProcess, Object... args) {
        int totalPage = (idList.size() + pageSize - 1) / pageSize;
        for (int i = 0; i < totalPage; i++) {
            int fromIndex = i * pageSize;
            int toIndex = Math.min((i + 1) * pageSize, idList.size());
            List<T> pageIdList = idList.subList(fromIndex, toIndex);
            pageProcess.process(pageIdList, args);
        }
    }


    /**
     * 根据list对象中某key转为map
     *
     * @param list
     * @param function map中key的构造规则
     * @param <K>
     * @param <E>
     * @return
     */
    public static <K, E> Map<K, E> listToMap(List<E> list, Function<E, K> function) {
        Map<K, E> map = new HashMap<K, E>();
        for (E ele : list) {
            K key = function.apply(ele);
            map.put(key, ele);
        }
        return map;
    }

    public interface PageProcess<T> {
        /**
         * 分页处理
         *
         * @param pageIdList pageIdList
         * @param params     params
         */
        void process(List<T> pageIdList, Object... params);

    }

    public static <T> List<T> strToList(String str, Function<String, T> function) {
        List<T> list = new ArrayList<T>();
        String[] arr = str.split(",");
        for (String s : arr) {
            list.add(function.apply(s));
        }
        return list;
    }

    /**
     * Object转换为Map
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <M, N> Map<M, N> beanToMap(Object obj) {
        Map<M, N> map = new HashMap<M, N>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0, len = fields.length; i < len; i++) {
            M varName = (M) fields[i].getName();
            try {
                boolean accessFlag = fields[i].isAccessible();
                fields[i].setAccessible(true);
                N o = (N) fields[i].get(obj);
                if (o instanceof String) {
                    String m = (String) o;
                    if (StringUtils.isNotEmpty(m)) {
                        map.put(varName, o);
                    }
                } else {
                    if (o != null)
                        map.put(varName, o);
                }
                fields[i].setAccessible(accessFlag);
            } catch (IllegalArgumentException ex) {
                ex.printStackTrace();
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
        }
        return map;
    }

    public static <E> boolean isEmpty(List<E> list) {
        if (null == list || list.isEmpty()) {
            return true;
        }
        return false;
    }

    public static <E> boolean isNotEmpty(List<E> list) {
        return !isEmpty(list);
    }

    /**
     * 合并俩个list<T>集合
     */
    public static <T> List<T> mergeList(List<T> aList, List<T> bList) {
        if (CollectionUtils.isEmpty(aList) && CollectionUtils.isEmpty(bList)) {
            return aList;
        } else if (CollectionUtils.isNotEmpty(aList) && CollectionUtils.isEmpty(bList)) {
            return aList;
        } else if (CollectionUtils.isEmpty(aList) && CollectionUtils.isNotEmpty(bList)) {
            return bList;
        } else {
            aList.addAll(bList);
            return aList;
        }
    }

    /**
     * 字符串转换成List
     *
     * @param strs      字符串
     * @param separator 分隔符
     */
    public static List<Long> strToListLong(String strs, String separator) {
        if (strs == null || "".equals(strs) || separator == null || "".equals(separator)) {
            return new ArrayList<>();
        }
        List<Long> result = new ArrayList<>();
        for (String str : strs.split(separator)) {
            try {
                result.add(Long.parseLong(str.trim()));
            } catch (Exception e) {
                log.error("str to list error, strs: " + strs + " separator: " + separator, e);
            }
        }
        return result;
    }

    /**
     * 字符串转换成List
     */
    public static List<Integer> strToListInteger(String strs, String separator) {
        if (strs == null || "".equals(strs) || separator == null || "".equals(separator)) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        for (String str : strs.split(separator)) {
            try {
                result.add(Integer.parseInt(str.trim()));
            } catch (Exception e) {
                log.error("str to list error, strs: " + strs + " separator: " + separator, e);
            }
        }
        return result;
    }

    /**
     * @param coll
     * @param str
     * @param <T>
     * @return
     */
    public static <T> String join(Collection<T> coll, String str) {
        if (coll == null || coll.isEmpty()) {
            return "";
        }
        if (str == null) {
            throw new IllegalArgumentException("str can\'t be null");
        }
        StringBuilder result = new StringBuilder();
        Iterator<T> it = coll.iterator();

        while (it.hasNext()) {
            Object t = it.next();
            if (t != null) {
                result.append(t).append(str);
            }
        }

        if (result.length() > 0) {
            result.delete(result.length() - str.length(), result.length());
        }
        return result.toString();
    }


    /**
     * 对list进行split
     */
    public static <T> List<List<T>> listSplit(List<T> srcList, int pageSize) {
        List<List<T>> result = new ArrayList<>();
        int totalPage = (srcList.size() + pageSize - 1) / pageSize;
        for (int i = 0; i < totalPage; i++) {
            int fromIndex = i * pageSize;
            int toIndex = Math.min((i + 1) * pageSize, srcList.size());
            result.add(srcList.subList(fromIndex, toIndex));
        }
        return result;
    }




    /**
     * Field.class的fieldName和getMethod的映射关系
     */
    private static Map<String, String> fieldClassGetMethodNameMap = new HashMap<String, String>();

    static {
        fieldClassGetMethodNameMap.put("name", "getName");
        fieldClassGetMethodNameMap.put("value", "getValue");
    }

    /**
     * 获得KeyValue对应的Value<br>
     * (兼容Map为空的情况,KeyValue忽略大小写)
     *
     * @param <T>
     * @param map
     * @return
     */
    public static <T> T getValueOfMapIgnoreCase(Map<String, T> map, String keyValue) {
        if (map != null && !map.isEmpty()) {
            for (String key : map.keySet()) {
                if (StringUtils.equalsIgnoreCase(key, keyValue))
                    return map.get(key);
            }
        }
        return null;
    }

    /**
     * @param obj
     * @param fieldName
     * @return
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @SuppressWarnings("unchecked")
    private static <T1, T2> T1 getKeyForCovertColl(T2 obj, String fieldName) throws SecurityException,
            NoSuchMethodException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        T1 key = null;
        // 读取对象中的字段
        if (obj instanceof Field && containsKeyOfMap(fieldClassGetMethodNameMap, fieldName)) {
            // 需要对Field对象做简化处理,防止死循环
            String methodNameOfGet = getValueOfMapIgnoreCase(fieldClassGetMethodNameMap, fieldName);
            Method methodOfGet = Field.class.getMethod(methodNameOfGet);
            key = (T1) methodOfGet.invoke(obj);
        } else
            key = (T1) ObjectCompareUtil.getValueByFieldName(obj, fieldName);
        return key;
    }

    /**
     * 查看Map是否包含某个Key<br>
     * (兼容Map为空的情况)
     *
     * @param map
     * @param key
     * @return
     */
    public static boolean containsKeyOfMap(Map<?, ?> map, Object key) {
        return map != null && !map.isEmpty() && map.containsKey(key);
    }

    /**
     * 检查value是否在数组array中
     *
     * @param <T>
     * @param array
     * @param value
     * @return
     */
    public static <T> boolean isInArray(T[] array, T value) {
        if (array != null && array.length > 0) {
            for (T item : array) {
                if ((item == null && item == value) || (item != null && item.equals(value)))
                    return true;
            }
        }
        return false;
    }

    /**
     * 添加一个值到Map中(Map的Value是一个List)
     *
     * @param <T1>
     * @param <T2>
     * @param map
     * @param key     如果Map没有包含Key,则会生成一个ArrayList
     * @param value
     * @param autoNew
     * @return
     */
    public static <T1, T2> Map<T1, List<T2>> putValueOfListMap(Map<T1, List<T2>> map, T1 key, T2 value, boolean autoNew) {
        // 1.尝试自动初分配一个空的Map
        if (map == null && autoNew)
            map = new HashMap<T1, List<T2>>();
        // 2.尝试设置值
        if (map != null) {
            List<T2> list = map.get(key);
            list = list != null ? list : new ArrayList<T2>();
            list.add(value);
            map.put(key, list);
        }
        // 3.返回结果
        return map;
    }

    public static boolean isInArrayIgnoreCase(String[] array, String value) {
        if ((array != null) && (array.length > 0)) {
            String[] arrayOfString = array;
            int j = array.length;
            for (int i = 0; i < j; i++) {
                String item = arrayOfString[i];
                if (StringUtils.equalsIgnoreCase(item, value)) {
                    return true;
                }
            }
        }
        return false;
    }
    public static <T1, T2> T2 getValueBySoftMap(Map<T1, SoftReference<T2>> map, T1 key) {
        SoftReference<T2> softr = (SoftReference) getValueOfMap(map, key);
        T2 value = softr != null ? softr.get() : null;
        if (value == null) {
            return null;
        }
        if ((value instanceof List)) {
            return (T2) addAllOfList(null, (List) value);
        }
        if ((value instanceof Set)) {
            return (T2) addAllOfSet(null, (Set) value);
        }
        if ((value instanceof Map)) {
            return (T2) putAllOfMap(null, (Map) value);
        }
        return value;
    }

    public static <T> List<T> addAllOfList(List<T> destList, List<T> souList) {
        if (destList == null) {
            destList = new ArrayList();
        }
        if (CollectionUtils.isNotEmpty(souList)) {
            destList.addAll(souList);
        }
        return destList;
    }

    public static <T> Set<T> addAllOfSet(Set<T> destSet, Collection<T> souSet) {
        if (destSet == null) {
            destSet = checkIsNumberOfCollection(souSet) ? new TreeSet() : new HashSet();
        }
        if (CollectionUtils.isNotEmpty(souSet)) {
            destSet.addAll(souSet);
        }
        return destSet;
    }


    public static <T1, T2> Map<T1, T2> putAllOfMap(Map<T1, T2> destMap, Map<T1, T2> sourMap) {
        if (isEmptyOfMap(sourMap)) {
            return destMap;
        }
        if (destMap == null) {
            destMap = checkKeyIsNumberOfMap(sourMap) ? new TreeMap() : new HashMap();
        }
        destMap.putAll(sourMap);
        return destMap;
    }

    private static <T1, T2> boolean checkKeyIsNumberOfMap(Map<T1, T2> map) {
        boolean isIntegerKey = false;
        if (isNotEmptyOfMap(map)) {
            Iterator localIterator = map.keySet().iterator();
            if (localIterator.hasNext()) {
                T1 key = (T1) localIterator.next();
                isIntegerKey = ((key instanceof Integer)) || ((key instanceof Long));
            }
        }
        return isIntegerKey;
    }

    private static <T> boolean checkIsNumberOfCollection(Collection<T> coll) {
        boolean isIntegerKey = false;
        if (CollectionUtils.isNotEmpty(coll)) {
            Iterator localIterator = coll.iterator();
            if (localIterator.hasNext()) {
                T value = (T) localIterator.next();
                isIntegerKey = ((value instanceof Integer)) || ((value instanceof Long));
            }
        }
        return isIntegerKey;
    }

    public static <T> Set<T> addOfSet(Set<T> destSet, T value) {
        if (value == null) {
            return destSet;
        }
        if (destSet == null) {
            boolean isIntegerKey = ((value instanceof Integer)) || ((value instanceof Long));
            destSet = isIntegerKey ? new TreeSet() : new HashSet();
        }
        destSet.add(value);

        return destSet;
    }

    public static <T> Collection<T> addAllOfCollection(Collection<T> destCollection, Collection<T> souCollection, Collection<T> newCollection) {
        if (destCollection == null) {
            destCollection = newCollection;
        }
        if (CollectionUtils.isNotEmpty(souCollection)) {
            destCollection.addAll(souCollection);
        }
        return destCollection;
    }

    public static <T> Collection<T> addOfCollection(Collection<T> destCollection, T value, Collection<T> newCollection) {
        if (destCollection == null) {
            destCollection = newCollection;
        }
        destCollection.add(value);

        return destCollection;
    }


    public static boolean isEmptyOfMap(Map<?, ?> map) {
        return (map == null) || (map.size() <= 0);
    }

    public static boolean isNotEmptyOfMap(Map<?, ?> map) {
        return !isEmptyOfMap(map);
    }

    public static <T1, T2> T2 getValueOfMap(Map<T1, T2> map, T1 key) {
        if ((isNotEmptyOfMap(map)) &&
                (map.containsKey(key))) {
            return (T2) map.get(key);
        }
        return null;
    }

    public static <T1, T2> Map<T1, T2> convertCollToMap(Collection<T2> coll, String fieldName) {
        if ((CollectionUtils.isEmpty(coll)) || (StringUtils.isBlank(fieldName))) {
            return null;
        }
        Map<T1, T2> map = null;
        for (T2 obj : coll) {
            try {
                T1 key = getKeyForCovertColl(obj, fieldName);
                if (map == null) {
                    if ((key != null) && (isInArray(ConstValueOfCommon.numberClassArray, key.getClass()))) {
                        map = new TreeMap();
                    } else {
                        map = new LinkedHashMap();
                    }
                }
                map.put(key, obj);
            } catch (Exception localException) {
            }
        }
        return map;
    }


    public static <T1, T2> void putValueBySoftMap(Map<T1, SoftReference<T2>> map, T1 key, T2 value) {
        if (map == null) {
            return;
        }
        SoftReference<T2> valueOfMap = null;
        if ((value instanceof List)) {
            valueOfMap = new SoftReference(addAllOfList(null, (List) value));
        } else if ((value instanceof Set)) {
            valueOfMap = new SoftReference(addAllOfSet(null, (Set) value));
        } else if ((value instanceof Map)) {
            valueOfMap = new SoftReference(putAllOfMap(null, (Map) value));
        } else {
            valueOfMap = new SoftReference(value);
        }
        map.put(key, valueOfMap);
    }


}
