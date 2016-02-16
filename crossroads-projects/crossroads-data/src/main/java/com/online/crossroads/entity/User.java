package com.online.crossroads.entity;

import com.online.crossroads.type.GenderType;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by lenovo on 12-02-2016.
 */
@Entity
@Table(name = "cr_user")
public class User extends AbstractAuditablEntity {

    @NotNull
    private String email;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime dob;

    private int failedLoginAttempt;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    protected DateTime lastPasswordUpdateDate;

    @Embedded
    @AttributeOverrides({@AttributeOverride(name = "name", column = @Column(name = "avatar_name")),
            @AttributeOverride(name = "url", column = @Column(name = "avatar_url"))})
    private Image avatar;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role_association", joinColumns = @JoinColumn(name = "cr_user"), inverseJoinColumns = @JoinColumn(name = "role"))
    private List<Role> roles;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public GenderType getGender() {
        return gender;
    }

    public void setGender(GenderType gender) {
        this.gender = gender;
    }

    public DateTime getDob() {
        return dob;
    }

    public void setDob(DateTime dob) {
        this.dob = dob;
    }

    public int getFailedLoginAttempt() {
        return failedLoginAttempt;
    }

    public void setFailedLoginAttempt(int failedLoginAttempt) {
        this.failedLoginAttempt = failedLoginAttempt;
    }

    public DateTime getLastPasswordUpdateDate() {
        return lastPasswordUpdateDate;
    }

    public void setLastPasswordUpdateDate(DateTime lastPasswordUpdateDate) {
        this.lastPasswordUpdateDate = lastPasswordUpdateDate;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}