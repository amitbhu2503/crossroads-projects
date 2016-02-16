package com.online.crossroads.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by lenovo on 12-02-2016.
 */

@Entity
@Table(name = "user_role_association")
public class UserRoleAssociation extends AbstractAuditablEntity {

    @ManyToOne
    @JoinColumn(name = "cr_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role", nullable = false)
    private Role role;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}