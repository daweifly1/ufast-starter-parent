package com.xgit.bj.common.util.excel.csv;

import com.xgit.bj.common.util.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.ClientAbortException;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * csv导出工具类，支持大批量导出
 */
@Slf4j
public class CsvExportUtils {

    // BOM签名能否让excel认识这个文件时utf-8编码的
    private static final byte bow[] = {(byte) 0xEF, (byte) 0xBB, (byte) 0xBF};

    // 纯数字
    private static final Pattern pattern = Pattern.compile("[0-9]*");

    /**
     * 分页导出
     *
     * @param response    response
     * @param fileName    fileName
     * @param titles      标题行
     * @param pagedExport 分页执行
     * @param pageSize    每页大小
     * @param total       总数据量
     * @param params      参数
     */
    public static void doExport(HttpServletResponse response, String fileName, List<String> titles,
                                PagedExport pagedExport, int pageSize, int total, Object... params) {
        if (pageSize == 0) {
            throw new IllegalArgumentException("pageSize is 0");
        }

        response.setContentType("application/csv;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            // BOM签名能否让excel认识这个文件时utf-8编码的
            os.write(bow);
            os.write((CollectionUtil.join(titles, ",") + "\r\n").getBytes("utf-8"));
            os.flush();

            // 分页数目
            int totalPage = (total + pageSize - 1) / pageSize;
            log.info(String.format("CsvExportUtils doExport total: %s, pageSize: %s, totalPage: %s", total, pageSize, totalPage));
            // 分页执行，页码从1开始
            for (int i = 1; i < totalPage + 1; i++) {
                log.info("CsvExportUtils doExport page: " + i);
                // 该页每行数据
                try {
                    List<List<String>> pageStringList = pagedExport.process(i, pageSize, params);
                    log.info("CsvExportUtils doExport page data count: " + pageStringList.size());
                    for (List<String> pageString : pageStringList) {
                        List<String> pageString2 = CsvExportUtils.filter(pageString);
                        // 换行符替换了，防止影响csv换行
                        os.write((CollectionUtil.join(pageString2, ",") + "\r\n").getBytes("utf-8"));
                        // 刷新到浏览器
                        os.flush();
                    }

                } catch (Exception e) {
                    log.error("CsvExportUtils doExport error, i: " + i, e);
                    break;
                }
            }
        } catch (IOException e) {
            log.error("CsvExportUtils getOutputStream 1 error !", e);
        } finally {
            CsvExportUtils.close(os);
        }
    }

    /**
     * id集合拆分方式
     *
     * @param response   HttpServletResponse
     * @param fileName   fileName
     * @param titles     titles
     * @param list       需要拆分的id集合
     * @param pageExport 每页执行
     * @param pageSize   每页条数
     * @param params     一堆临时对象
     */
    public static void doExport(HttpServletResponse response, String fileName, List<String> titles, List list,
                                PagedIdExport pageExport, int pageSize, Object... params) {
        log.info("CsvExportUtils doExport1 id size: " + (list != null ? list.size() : 0) + " fileName:" + fileName);
        response.setContentType("application/csv;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=\"" + fileName);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            // BOM签名能否让excel认识这个文件时utf-8编码的
            os.write(bow);
            os.write((CollectionUtil.join(titles, ",") + "\r\n").getBytes("utf-8"));
            os.flush();
            // 多个页的id集合
            List<List<Object>> listSplit = CollectionUtil.listSplit(list, pageSize);
            int i = 1;
            for (List<Object> list1 : listSplit) {
                log.info("CsvExportUtils doExport1 page: " + i++);
                try {
                    // 一页的数据
                    List<List<String>> pageStringList = pageExport.process(list1, params);
                    log.info("CsvExportUtils doExport1 page data count: " + pageStringList.size());
                    for (List<String> pageString : pageStringList) {
                        List<String> pageString2 = CsvExportUtils.filter(pageString);
                        os.write((CollectionUtil.join(pageString2, ",") + "\r\n").getBytes("utf-8"));
                        // 刷新到浏览器
                        os.flush();
                    }
                } catch (ClientAbortException cae) {
                    log.error("CsvExportUtils doExport1 error, i: " + i + " cae:", cae);
                    break;
                } catch (Exception e) {
                    log.error("CsvExportUtils doExport1 page, i: " + i, e);
                }
            }
        } catch (IOException e) {
            log.error("CsvExportUtils getOutputStream error !", e);
        } finally {
            CsvExportUtils.close(os);
        }
    }


    public static void doExport(File storeFile, String fileName, List<String> titles, List list,
                                PagedIdExport pageExport, int pageSize, Object... params) {
        if (pageSize == 0) {
            throw new IllegalArgumentException("pageSize is 0");
        }
        log.info("CsvExportUtils doExport1 id size: " + (list != null ? list.size() : 0) + " fileName:" + fileName);
        OutputStream os = null;
        try {
            os = new FileOutputStream(storeFile);
            // BOM签名能否让excel认识这个文件时utf-8编码的
            os.write(bow);
            os.write((CollectionUtil.join(titles, ",") + "\r\n").getBytes("utf-8"));
            os.flush();
            // 多个页的id集合
            List<List<Object>> listSplit = CollectionUtil.listSplit(list, pageSize);
            int i = 1;
            for (List<Object> list1 : listSplit) {
                log.info("CsvExportUtils doExport1 page: " + i++);
                try {
                    // 一页的数据
                    List<List<String>> pageStringList = pageExport.process(list1, params);
                    log.info("CsvExportUtils doExport1 page data count: " + pageStringList.size());
                    for (List<String> pageString : pageStringList) {
                        List<String> pageString2 = CsvExportUtils.filter(pageString);
                        os.write((CollectionUtil.join(pageString2, ",") + "\r\n").getBytes("utf-8"));
                    }
                    // 刷新到浏览器
                    os.flush();
                } catch (Exception e) {
                    log.error("CsvExportUtils doExport1 page, i: " + i, e);
                }
            }
        } catch (IOException e) {
            log.error("CsvExportUtils getOutputStream error !", e);
        } finally {
            CsvExportUtils.close(os);
        }
    }

    // 换行符替换了，防止影响csv换行
    public static List<String> filter(List<String> pageString) {
        List<String> pageString2 = new ArrayList<>();
        for (String str : pageString) {
            if (StringUtils.isBlank(str)) {
                pageString2.add("");
                continue;
            }
            str = str.replaceAll("\\r", "").replaceAll("\\n", "").replaceAll(",", "");
            str = CsvExportUtils.defaultString(str);
            pageString2.add(str);
        }
        return pageString2;
    }

    private static void close(OutputStream os) {
        if (os != null) {
            try {
                os.close();
            } catch (IOException e) {
                log.error("CsvExportUtils os close() error !", e);
            }
        }
    }

    /**
     * csv 字符串显示
     *
     * @return 纯数字且超过12位加上'，防止excel转化成科学计数
     */
    public static String defaultString(String str) {
        if (str == null) {
            return "";
        }
        if (str.length() >= 12) {
            Matcher m = pattern.matcher(str);
            if (m.matches()) {
                return "'" + str;
            }
        }
        return str;
    }

    public static void main(String[] args) {
        Pattern pattern2 = Pattern.compile("[0-9]*");
        System.out.println(pattern2.matcher("123123").matches());
    }

}
