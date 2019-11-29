package yb.ecp.fast.infra.infra.log;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.Debug;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

/**
 * @author wanghao
 * @Description 记录api请求log的filter
 * @date 2018-01-08 11:21
 */
@Service
public class LoggingApiFilter extends ZuulFilter {
    private Logger myLog = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        // 最后执行
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        // 统计执行时间
        Long duration = System.currentTimeMillis() - (Long) ctx.get(LoggingContextFilter.DURATION);
        HttpServletResponse response = ctx.getResponse();
        Debug.addRequestDebug("RESPONSE:: > " + response.getStatus());
        Debug.addRequestDebug("RESPONSE:: > duration:" + duration + "ms");

        StringBuffer sb = new StringBuffer("");
        // 打印日志
        for (String s : Debug.getRequestDebug()) {
            sb.append(s);
        }
        LogHelper.monitor(sb.toString());
        return null;
    }
}
