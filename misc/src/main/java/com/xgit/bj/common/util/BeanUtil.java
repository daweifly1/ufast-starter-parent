package com.xgit.bj.common.util;

import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * 不同对象，属性的互相转化类,建议对应结构的类才使用
 */
public final class BeanUtil {

    private BeanUtil() {
    }

    public static <S, T> T do2bo(S source, Class<T> targetClass) {
        if (null == source) {
            return null;
        }
        T result;
        try {
            result = targetClass.newInstance();
            BeanUtils.copyProperties(source, result);
        } catch (Exception e) {
            throw new IllegalArgumentException("对象copy失败，请检查相关module", e);
        }
        return result;
    }

    public static <S, T> List<T> do2bo4List(List<S> source, Class<T> targetClass) {
        List<T> result = Lists.newArrayList();
        if (CollectionUtils.isEmpty(source)) {
            return result;
        }
        for (S obj : source) {
            result.add(do2bo(obj, targetClass));
        }
        return result;
    }
}
