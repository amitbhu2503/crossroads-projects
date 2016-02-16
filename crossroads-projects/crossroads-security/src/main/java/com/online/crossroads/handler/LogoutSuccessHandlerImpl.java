package com.online.crossroads.handler;

import com.online.crossroads.util.CommonUtil;
import com.online.crossroads.util.SecurityConstant;
import org.apache.log4j.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 03-02-2016.
 */

@Component
public class LogoutSuccessHandlerImpl extends AbstractAuthenticationTargetUrlRequestHandler
        implements LogoutSuccessHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Resource(name = "redisTemplate") private HashOperations<String, String, Object> hashOperations;

    public void onLogoutSuccess(HttpServletRequest arg0, HttpServletResponse arg1, Authentication arg2)
            throws IOException, ServletException {

        postProcessor(arg0, arg1, arg2);

    }

    /**
     * @param request
     * @param response
     * @param authentication
     */
    private void postProcessor(HttpServletRequest request, HttpServletResponse response,
                               Authentication authentication) {
        String token = request.getHeader(SecurityConstant.AUTH_HEADER);
        if (CommonUtil.isNotEmpty(token)) {
            logger.info("Logging out for token {}", token);
            hashOperations.delete(token, SecurityConstant.TOKEN_MAP_USER);
            hashOperations.getOperations().delete(token);
        }

        //Clear MDC
        MDC.remove(SecurityConstant.AUTH_TOKEN);

    }

}