package com.online.crossroads.util;

import com.online.crossroads.core.UserDetail;
import com.online.crossroads.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by lenovo on 12-02-2016.
 */
@Component
public class SecurityUtil {

    /**
     * @return {@link User}
     */
    public User getLoggedInUser() {
        UserDetail userDetail = getLoggedInPrincipal();
        return CommonUtil.isNull(userDetail) ? null : userDetail.getUser();
    }

    /**
     * @return current Pricipal
     */
    public UserDetail getLoggedInPrincipal() {
        Authentication authentication = getAuthentication();
        if (CommonUtil.isNull(authentication) || CommonUtil.isNull((authentication.getPrincipal()))) {
            return null;
        }
        if (authentication.getPrincipal() instanceof UserDetail) {
            UserDetail principal = (UserDetail) authentication.getPrincipal();
            return principal;
        }

        return null;

    }

    /**
     * @return {@link Authentication}
     */
    private Authentication getAuthentication() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication();
    }

}
