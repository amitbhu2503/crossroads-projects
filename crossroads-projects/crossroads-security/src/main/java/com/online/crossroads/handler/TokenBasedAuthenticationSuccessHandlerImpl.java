package com.online.crossroads.handler;

import com.online.crossroads.util.SecurityConstant;
import com.online.crossroads.util.SecurityUtil;
import org.jboss.logging.MDC;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lenovo on 16-02-2016.
 */
public class TokenBasedAuthenticationSuccessHandlerImpl extends SimpleUrlAuthenticationSuccessHandler {

    private SecurityUtil securityUtil;

    public TokenBasedAuthenticationSuccessHandlerImpl(SecurityUtil securityUtil) {
        super();
        this.securityUtil = securityUtil;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        //PUT user email is MDC
        MDC.put(SecurityConstant.AUTH_TOKEN, request.getHeader(SecurityConstant.AUTH_HEADER));
        String context = request.getContextPath();
        String fullURL = request.getRequestURI();
        String url = fullURL.substring(fullURL.indexOf(context) + context.length());

        request.getRequestDispatcher(url).forward(request, response);
    }

}
