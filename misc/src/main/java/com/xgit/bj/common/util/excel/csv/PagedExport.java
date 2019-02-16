package com.xgit.bj.common.util.excel.csv;

import java.util.List;

/**
 * 分页执行
 */
public interface PagedExport<T> {

    /**
     * 分页处理
     *
     * @param params params
     */
    List<List<String>> process(int pageIndex, int pageSize, Object... params);

}
