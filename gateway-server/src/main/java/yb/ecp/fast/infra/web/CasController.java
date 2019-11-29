package yb.ecp.fast.infra.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import yb.ecp.fast.infra.feign.CasClient;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.infra.infra.log.LogHelper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by john on 2017/11/30.
 */
@RestController
@RequestMapping("/cas")
public class CasController {
    @Autowired
    private CasClient casClient;

    @Value("${cas.defaultUrl:/cas}")
    private String defaultUrl;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public void login(@RequestParam("ticket") String ticket,
                      @RequestParam("target") String targetUrl,
                      HttpServletResponse httpServletResponse,
                      HttpSession httpSession) {
        try {
            ActionResult actionResult = casClient.ticket(ticket);
            LogHelper.debug("actionResult:"+actionResult);
            if (actionResult.getCode() != 0) {
                httpServletResponse.sendRedirect(defaultUrl);
                return;
            }
            String userId = (String) actionResult.getValue();
            if (yb.ecp.fast.infra.util.StringUtil.isNullOrSpace(userId)) {
                httpServletResponse.sendRedirect(defaultUrl);
                return;
            }
            httpSession.setAttribute("uid", userId);
            httpServletResponse.sendRedirect(targetUrl);
        } catch (Exception exc) {
            LogHelper.fatal("failed to process things", exc);
        }
    }
}
