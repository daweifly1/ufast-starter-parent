package yb.ecp.fast.infra.security;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.util.StringUtil;

import javax.servlet.http.HttpSession;
import java.util.Date;

/**
 * Created by john on 2017/10/18.
 */
@Service
public class PreprocessRequestFilter extends ZuulFilter {
    private Logger mylog = LoggerFactory.getLogger(this.getClass());

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        //数字越小，越先执行

        return 20;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestUri = ctx.getRequest().getRequestURI();

        HttpSession httpSession = ctx.getRequest().getSession();


        ctx.addZuulRequestHeader("x-access-client", "gateway");
        String userId = (String) httpSession.getAttribute("uid");
        mylog.warn("Request URL:" + ctx.getRequest().getRequestURI()
                + ", id:" + httpSession.getId()
                + ", userId:" + userId
                + ", createTime:" + new Date(httpSession.getCreationTime())
                + ". lastAccessTime:" + new Date(httpSession.getLastAccessedTime()));


        if (StringUtil.isNullOrSpace(userId) != true) {
            ctx.addZuulRequestHeader("x-user-id", userId);
            //mylog.info("x-user-id header is : " + userId);
            return null;
        } else {
            ctx.addZuulRequestHeader("x-user-id", " ");
        }

        return null;
    }
}
