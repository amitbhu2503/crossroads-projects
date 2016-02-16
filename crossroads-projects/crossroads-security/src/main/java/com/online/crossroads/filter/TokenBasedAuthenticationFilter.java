package com.online.crossroads.filter;

import com.online.crossroads.core.UserDetail;
import com.online.crossroads.handler.TokenBasedAuthenticationSuccessHandlerImpl;
import com.online.crossroads.service.base.AuthTokenGeneratorService;
import com.online.crossroads.service.impl.NoOpAuthenticationManager;
import com.online.crossroads.util.SecurityConstant;
import com.online.crossroads.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * Created by lenovo on 16-02-2016.
 */
public class TokenBasedAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String TOKEN_FILTER_APPLIED = "TOKEN_FILTER_APPLIED";
    private AuthTokenGeneratorService authTokenGeneratorService;

    private HashOperations<String, String, Object> hashOperations;

    private SecurityUtil securityUtil;

    private ServerProperties serverProperties;

    public TokenBasedAuthenticationFilter(String defaultFilterProcessesUrl,
                                          AuthTokenGeneratorService authTokenGeneratorService, HashOperations<String, String, Object> hashOperations,
                                          SecurityUtil securityUtil, ServerProperties serverProperties) {
        super(defaultFilterProcessesUrl);
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher(defaultFilterProcessesUrl));
        super.setAuthenticationManager(new NoOpAuthenticationManager());
        setAuthenticationSuccessHandler(new TokenBasedAuthenticationSuccessHandlerImpl(securityUtil));
        this.authTokenGeneratorService = authTokenGeneratorService;
        this.hashOperations = hashOperations;
        this.securityUtil = securityUtil;
        this.serverProperties = serverProperties;
    }

    /**
     * @param arg0
     * @param arg1
     * @param arg2
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) arg0;
        HttpServletResponse response = (HttpServletResponse) arg1;

        if (request.getAttribute(TOKEN_FILTER_APPLIED) != null) {
            arg2.doFilter(request, response);
        } else {
            super.doFilter(arg0, arg1, arg2);
        }

    }

    /**
     * @param request
     * @param arg1
     * @return {@link Authentication}
     * @throws AuthenticationException
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse arg1)
            throws AuthenticationException, IOException, ServletException {

        AbstractAuthenticationToken userAuthenticationToken = null;

        try {

            request.setAttribute(TOKEN_FILTER_APPLIED, Boolean.TRUE);

            String token = request.getHeader(SecurityConstant.AUTH_HEADER);
            //String token = request.getParameter(SecurityConstant.TOKEN);
            userAuthenticationToken = authenticateByToken(token);

        } catch (Exception ex) {
            throw new AuthenticationServiceException("Authorization token not present in header");
        }

        if (userAuthenticationToken == null) {
            throw new AuthenticationServiceException("Bad Token");
        }

        return userAuthenticationToken;
    }

    /**
     * authenticate the user based on token
     *
     * @return {@link AbstractAuthenticationToken}
     */
    private AbstractAuthenticationToken authenticateByToken(String token) throws Exception {
        hashOperations.getOperations().expire(token, serverProperties.getSession().getTimeout(), TimeUnit.SECONDS);
        UserDetail userDetail = (UserDetail) hashOperations.get(token, SecurityConstant.TOKEN_MAP_USER);
        return new UsernamePasswordAuthenticationToken(userDetail, "", userDetail.getAuthorities());
    }
}