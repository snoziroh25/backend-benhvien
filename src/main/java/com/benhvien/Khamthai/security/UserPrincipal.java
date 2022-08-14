
package com.benhvien.Khamthai.security;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.benhvien.Khamthai.model.AccountModel;


public class UserPrincipal implements UserDetails {
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String id;

	private String username;

	private String password;

	private Boolean active;

	private String roleId;

	private Collection<? extends GrantedAuthority> authorities;

	public UserPrincipal(String id, String username, String password, String roleId,
			Collection<? extends GrantedAuthority> authorities) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.active = true;
		this.roleId = roleId;
		this.authorities = authorities;
	}

	public static UserPrincipal create(AccountModel account) {
		return new UserPrincipal(account.getId(), account.getUsername(), account.getPassword(), account.getRoleId(),
				null);
	}

	public String getRoleId() {
		return roleId;
	}

	public String getId() {
		return id;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public Boolean getActive() {
		return active;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if (active == true) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		UserPrincipal that = (UserPrincipal) o;
		return Objects.equals(id, that.id);
	}

	@Override
	public int hashCode() {

		return Objects.hash(id);
	}

}
