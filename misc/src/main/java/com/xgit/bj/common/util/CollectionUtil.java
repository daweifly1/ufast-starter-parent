package com.xgit.bj.common.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 集合工具类
 */
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


}
