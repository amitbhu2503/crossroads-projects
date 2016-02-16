package com.online.crossroads.repository;

import com.online.crossroads.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by lenovo on 16-02-2016.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * @param email
     * @return {@link User}
     */
    @Transactional(readOnly = true)
    User findByEmail(String email);

    /**
     * @param id
     * @return {@link User}
     */
    @Transactional(readOnly = true)
    User findById(long id);
}