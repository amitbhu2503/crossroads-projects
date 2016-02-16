package com.online.crossroads.service.impl;

import com.online.crossroads.core.UserDetail;
import com.online.crossroads.entity.User;
import com.online.crossroads.repository.UserRepository;
import com.online.crossroads.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenovo on 16-02-2016.
 */

@Service
@Transactional(readOnly = true)
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserDetail currentUser = null;
        User user = null;
        try {
            user = userRepository.findByEmail(username);
            if (CommonUtil.isNull(user)) {
                throw new UsernameNotFoundException("User not found for username " + username);
            }

        } catch (Exception ex) {
            throw new UsernameNotFoundException("User not found for username " + username, ex);
        }
        return getUser(user);
    }

    /**
     * @param user
     * @return {@link UserDetail}
     */
    private UserDetail getUser(User user) {
        return new UserDetail(user);
    }
}