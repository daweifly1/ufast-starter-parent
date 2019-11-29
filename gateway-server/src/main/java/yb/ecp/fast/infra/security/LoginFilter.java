package yb.ecp.fast.infra.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import yb.ecp.fast.infra.infra.ActionResult;

import javax.servlet.http.HttpSession;
import java.io.InputStream;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;

/**
 * Created by john on 2017/10/18.
 */
@Service
public class LoginFilter extends ZuulFilter {
    private Logger mylog = LoggerFactory.getLogger(this.getClass());

    @Value("${fast.auth.login.url}")
    String loginUrls[];

    public LoginFilter() {

    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 900;
    }

    @Override
    public boolean shouldFilter() {

        RequestContext ctx = RequestContext.getCurrentContext();
        String requestUri = ctx.getRequest().getRequestURI();
        for (String url : loginUrls) {
            if (requestUri.matches(url)) {
                return true;
            }
        }
        return false;
    }

    protected String postUserLogin(RequestContext requestContext) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
        InputStream inputStream = requestContext.getResponseDataStream();
        ActionResult<String> actionResult = objectMapper.readValue(inputStream, ActionResult.class);
        inputStream.close();
        String userId = "";

        if (actionResult.getCode() != 0) {
            mylog.error(actionResult.getMessage());

        } else {
            userId = actionResult.getValue();
            actionResult.setValue(null);
        }
        requestContext.setResponseBody(objectMapper.writeValueAsString(actionResult));
        return userId;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpSession httpSession = ctx.getRequest().getSession();

        try {
            String userId = postUserLogin(ctx);
            if (yb.ecp.fast.infra.util.StringUtil.isNullOrSpace(userId) == false) {
                httpSession.setAttribute("uid", userId);
                mylog.warn("uid login:" + userId);
            }

        } catch (Exception exc) {
            mylog.error("failed to process things", exc);
        }
        return null;
    }
}