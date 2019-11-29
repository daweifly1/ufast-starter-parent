package yb.ecp.fast.infra.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import yb.ecp.fast.infra.infra.ActionResult;

/**
 * Created by john on 2017/11/30.
 */
@FeignClient("${cas.feign.name}")
public interface CasClient {
    @RequestMapping(value = "/cas/ticket",method = RequestMethod.GET)
    ActionResult ticket(@RequestHeader("x-cas-ticket") String ticket);
}
