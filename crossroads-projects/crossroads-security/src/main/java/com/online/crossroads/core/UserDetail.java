/*
 *
 *  * Copyright ©2015  The Aleph Group PTE limited ( ALEPH). Copyright protection includes content in the material generated from the software program. All information contained in the material is confidential and proprietary to ALEPH.   Any attempt to copy, reproduce or modify and re-distribute this software and its documentation without express prior written permission is expressly prohibited and may result in sever civil and criminal penalties.  Use of the Software is governed by the terms of the end user license agreement, if any, which accompanies or is included with the Software ("License Agreement") .Contact eng-operations@culturemachine.in for commercial licensing opportunities. All Rights Reserved.
 *  *
 *  * These libraries and applications are trademarks or registered trademarks of The Aleph Group PTE Limited, in Singapore, United States of America and/or other countries. All other trademarks are the property of their respective owners.
 *  *
 *  * Technologies described herein may be covered by existing U.S. patents or U.S. patent applications that are in progress.
 *  *
 *  * IN NO EVENT SHALL ALEPH BE LIABLE TO ANY PARTY FOR DIRECT, INDIRECT, SPECIAL, INCIDENTAL, OR CONSEQUENTIAL DAMAGES, INCLUDING LOST PROFITS, ARISING OUT OF THE USE OF THIS SOFTWARE AND ITS DOCUMENTATION, EVEN IF ALEPH HAS BEEN ADVISED OF THE POSSIBILITY OF SUCH DAMAGE. ALEPH SPECIFICALLY DISCLAIMS ANY WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE. THE SOFTWARE AND ACCOMPANYING DOCUMENTATION, IF ANY, PROVIDED HEREUNDER IS PROVIDED "AS IS". ALEPH HAS NO OBLIGATION TO PROVIDE MAINTENANCE, SUPPORT, UPDATES, ENHANCEMENTS, OR MODIFICATIONS.
 *
 */

package com.online.crossroads.core;

import com.online.crossroads.entity.Role;
import com.online.crossroads.entity.User;
import com.online.crossroads.type.StatusType;
import com.online.crossroads.util.CommonUtil;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by amitg on 19/6/15.
 */
public class UserDetail implements UserDetails {

	private User user;

	private Long id;

	private String firstName;

	private String lastName;

	private String email;

	private Collection<? extends GrantedService> services;

	public UserDetail(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Long getId() {
		return this.user.getId();
	}

	public void setId(Long id) {
		this.id = this.user.getId();
	}

	public String getFirstName() {
		return this.user.getFirstName();
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.user.getLastName();
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return this.user.getEmail();
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {

		if (CommonUtil.isEmpty(this.user.getRoles())) {
			return null;
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for (Role role : this.user.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role.getType().toString()));
		}

		return authorities;
	}

	@Override public String getPassword() {
		return this.user.getPassword();
	}

	@Override public String getUsername() {
		return this.user.getEmail();
	}

	@Override public boolean isAccountNonExpired() {
		return StatusType.ACTIVE.toString().equals(this.getUser().getStatus().toString());
	}

	@Override public boolean isAccountNonLocked() {
		return StatusType.ACTIVE.toString().equals(this.getUser().getStatus().toString());
	}

	@Override public boolean isCredentialsNonExpired() {
		return StatusType.ACTIVE.toString().equals(this.getUser().getStatus().toString());
	}

	@Override public boolean isEnabled() {
		return StatusType.ACTIVE.toString().equals(this.getUser().getStatus().toString());
	}

	public Collection<? extends GrantedService> getServices() {

//		Assert.notNull(this.user.getAccounts(), "A Principle must be associated with an account");

		//TODO Currently we supports only one account per user. However if requires, we can support multiple account per user use case
//		if (CommonUtil.isEmpty(this.user.getAccounts().get(0).getServices())) {
//			return null;
//		}

		List<GrantedService> grantedServices = new ArrayList<GrantedService>();
//		for (Service service : this.user.getAccounts().get(0).getServices()) {
//			grantedServices.add(new SimpleGrantedService(null));
//		}

		return grantedServices;
	}

}
