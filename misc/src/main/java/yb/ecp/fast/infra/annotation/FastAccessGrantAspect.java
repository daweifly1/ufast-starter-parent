package yb.ecp.fast.infra.annotation;

import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import yb.ecp.fast.infra.annotation.FastMappingInfo;
import yb.ecp.fast.infra.constants.AccessLabel;
import yb.ecp.fast.infra.constants.ErrorCode;
import yb.ecp.fast.infra.feign.AppClient;
import yb.ecp.fast.infra.feign.AuthClient;
import yb.ecp.fast.infra.infra.ActionResult;
import yb.ecp.fast.infra.util.StringUtil;

@Aspect
@Component
public class FastAccessGrantAspect {

    @Autowired
    private AuthClient authClient;
    @Autowired
    private AppClient appClient;


    private ActionResult actionResult(ErrorCode code) {
        return new ActionResult(code.getCode(), code.getDesc());
    }

    @Around("@annotation(FastMappingInfo) && @annotation(fastMappingInfo)")
    public Object accessCheck(ProceedingJoinPoint proceedingJoinPoint, FastMappingInfo fastMappingInfo) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        String accessClient = request.getHeader("x-access-client");
        String userId = request.getHeader("x-user-id");
        String appId = request.getHeader("x-app-id");
        AccessLabel accesslevel = AccessLabel.DenyAll;
        if("oauth2".equals(accessClient)) {
            accesslevel = AccessLabel.Oauth2;
        } else if("gateway".equals(accessClient)) {
            accesslevel = AccessLabel.Client;
        } else if(StringUtil.isNullOrEmpty(accessClient)) {
            accesslevel = AccessLabel.FullTrusted;
        }

        if(fastMappingInfo.actionLevel() > accesslevel.getLevelNum()) {
            return this.actionResult(ErrorCode.AccessDenied);
        } else if(AccessLabel.FullTrusted.equals(accesslevel)) {
            return proceedingJoinPoint.proceed();
        } else {
            boolean needLogin = false;
            if(fastMappingInfo.needLogin() || fastMappingInfo.code() > 0L) {
                needLogin = true;
            }

            if(needLogin && StringUtil.isNullOrSpace(userId)) {
                return this.actionResult(ErrorCode.NeedLogin);
            } else if(fastMappingInfo.code() == 0L) {
                return proceedingJoinPoint.proceed();
            } else {
                ActionResult actionResult;
                if(StringUtil.isNullOrEmpty(appId)) {
                    actionResult = this.authClient.checkAuthCode(userId, fastMappingInfo.code());
                } else {
                    actionResult = this.actionResult(ErrorCode.Success);
                }

                return actionResult.getCode() != 0?actionResult:proceedingJoinPoint.proceed();
            }
        }
    }
}
