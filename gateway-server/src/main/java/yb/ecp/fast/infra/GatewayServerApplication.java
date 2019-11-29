package yb.ecp.fast.infra;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaClientConfigBean;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaRegistration;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaServiceRegistry;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.DefaultCookieSerializer;
import yb.ecp.fast.infra.infra.eureka.EurekaDeregister;
import yb.ecp.fast.infra.infra.monitor.MemoryMonitor;
import yb.ecp.fast.infra.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients
@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 30000)
@EnableScheduling
public class GatewayServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayServerApplication.class, args);
    }

    @Bean(initMethod = "showDeregisterInfo", destroyMethod = "deregister")
    public EurekaDeregister eurekaDeregister(EurekaRegistration registration, EurekaServiceRegistry serviceRegistry, EurekaClientConfigBean eurekaClientConfigBean) {
        return new EurekaDeregister(registration, serviceRegistry, eurekaClientConfigBean);
    }

    @Bean
    public MemoryMonitor memoryMonitor() {
        return new MemoryMonitor() {
            @Scheduled(fixedRate = 5L * 60 * 1000)
            @Override
            public void scheduleMonitor() {
                takeMemoryLog();
            }
        };
    }

    /**
     * 同域名下部署时为了防止session冲突,可以设置session-cookie名称，默认为SESSION，
     *
     * @param name
     * @return
     */
    @Bean
    public DefaultCookieSerializer defaultCookieSerializer(@Value("${server.session.cookie.name:SESSION}") String name,
                                                           @Value("${server.session.cookie.max-age:-1}") Integer maxAge,
                                                           @Value("${server.session.timeout:1800}") Integer timeout,
                                                           RedisOperationsSessionRepository sessionRepository) {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName(name);
        cookieSerializer.setCookieMaxAge(maxAge);
        sessionRepository.setDefaultMaxInactiveInterval(timeout);
        return cookieSerializer;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean(@Value("${fast.webfront.root-locations}") String[] rootLocations) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        List<String> urlPatterns = new ArrayList<>();
        for (String s : rootLocations) {
            if (StringUtil.isNullOrEmpty(s)) {
                continue;
            }
            if (s.startsWith("/")) {
                urlPatterns.add(s);
                continue;
            } else {
                urlPatterns.add("/" + s);
            }
        }
        filterRegistrationBean.setUrlPatterns(urlPatterns);
        filterRegistrationBean.setFilter(new Filter() {
            @Override
            public void init(FilterConfig filterConfig) throws ServletException {

            }

            @Override
            public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
                if (request instanceof HttpServletRequest
                        && response instanceof HttpServletResponse) {
                    HttpServletRequest httpRequest = (HttpServletRequest) request;
                    HttpServletResponse httpResponse = (HttpServletResponse) response;
                    httpResponse.sendRedirect(httpRequest.getRequestURI() + "/index.html");
                }
            }

            @Override
            public void destroy() {

            }
        });

        return filterRegistrationBean;
    }


}
