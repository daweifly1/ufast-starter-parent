package yb.ecp.fast.infra.feign;

import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yb.ecp.fast.infra.feign.vo.UserLoginVO;
import yb.ecp.fast.infra.infra.ActionResult;

@EnableFeignClients
@FeignClient("fast-auth-server")
public abstract interface AuthClient
{
  @RequestMapping(value={"/usrCtx/checkAuthCode"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public abstract ActionResult checkAuthCode(@RequestParam("userId") String paramString, @RequestParam("authCode") long paramLong)
    throws Exception;
  
  @RequestMapping(value={"/auth/authInfo"}, method={org.springframework.web.bind.annotation.RequestMethod.GET})
  public abstract ActionResult GetAuthId();
  
  @RequestMapping(value={"/auth/login"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
  public abstract ActionResult Login(@RequestBody UserLoginVO paramUserLoginVO);
}

