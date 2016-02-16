package com.online.crossroads.handler;

import com.online.crossroads.core.UserDetail;
import com.online.crossroads.service.base.AuthTokenGeneratorService;
import com.online.crossroads.util.SecurityConstant;

import com.online.crossroads.util.SecurityUtil;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 03-02-2016.
 */

@EnableConfigurationProperties({ ServerProperties.class }) @Component
public class AuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ServerProperties serverProperties;

    @Autowired
    private AuthTokenGeneratorService authTokenGeneratorService;

    @Autowired
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private SecurityUtil securityUtil;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        logger.info("Login successful. Setting up post login properties  for user {}", authentication.getName());
        postProcessing(request, response, authentication);
    }

    /**
     * @param request
     * @param response
     */
    private void postProcessing(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
            throws IOException {
        //Set session timeout
        request.getSession().setMaxInactiveInterval(serverProperties.getSessionTimeout());
        //Generate auth token
        final String authToken = authTokenGeneratorService.generateSecret();
        //Set auth token in response
        response.addHeader(SecurityConstant.AUTH_HEADER, authToken);
        UserDetail userDetail = securityUtil.getLoggedInPrincipal();
        //in response too
//		UserDTO userDTO = new UserDTO(userDetail.getFirstName(), userDetail.getLastName());
//		response.getOutputStream().print(new ObjectMapper().writeValueAsString(userDTO));
        //Save token mapping in cache
        hashOperations.put(authToken, SecurityConstant.TOKEN_MAP_USER, userDetail);
        hashOperations.getOperations().expire(authToken, serverProperties.getSessionTimeout(), TimeUnit.SECONDS);
        MDC.put(SecurityConstant.AUTH_TOKEN, authToken);
    }
}
