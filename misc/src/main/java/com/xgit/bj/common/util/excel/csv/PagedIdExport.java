package com.xgit.bj.common.util.excel.csv;

import java.util.List;

/**
 * 分页执行
 */
public interface PagedIdExport {

    /**
     * 分页处理
     *
     * @param pageIdList pageIdList
     * @param params     params
     */
    List<List<String>> process(List<Object> pageIdList, Object... params);

}
