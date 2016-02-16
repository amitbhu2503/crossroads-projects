package com.online.crossroads.entity;

import com.online.crossroads.type.RoleType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * Created by lenovo on 12-02-2016.
 */
@Entity
@Table(name = "role")
public class Role extends AbstractAuditablEntity {

    @NotNull
    @Enumerated(EnumType.STRING)
    private RoleType type;

    private String description;

    public RoleType getType() {
        return type;
    }

    public void setType(RoleType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
