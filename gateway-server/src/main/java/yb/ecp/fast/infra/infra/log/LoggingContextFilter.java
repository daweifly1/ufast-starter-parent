package yb.ecp.fast.infra.infra.log;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.Debug;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.util.HTTPRequestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.Route;
import org.springframework.cloud.netflix.zuul.filters.SimpleRouteLocator;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author wanghao
 * @Description 设定日志上下文过滤器
 * @date 2018-01-08 10:59
 */
@Service
public class LoggingContextFilter extends ZuulFilter {

    private Logger myLog = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleRouteLocator simpleRouteLocator;

    @Autowired
    private ZuulProperties zuulProperties;
    public static final String USER_IP = "USER_IP";
    public static final String DURATION = "DURATION";

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest req = ctx.getRequest();
        ctx.set(DURATION, System.currentTimeMillis());
        Debug.addRequestDebug("REQUEST:: > " + req.getScheme() + " " + HTTPRequestUtils.getInstance().getClientIP(req) + ":" + req.getRemotePort());
        Debug.addRequestDebug("REQUEST:: > " + req.getMethod() + " " + req.getRequestURI() + " " + req.getProtocol());
        Route route = simpleRouteLocator.getMatchingRoute(req.getRequestURI());
        if (route != null)
            Debug.addRequestDebug("REQUEST:: > matched route:" + route.toString());
        return null;
    }
}
