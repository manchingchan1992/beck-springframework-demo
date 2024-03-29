package com.pccw.springframework.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import com.pccw.springframework.constant.CommonConstant;

public class OfficeUserDTO extends BaseDTO implements Serializable,UserDetails {

	private static final long serialVersionUID = 1L;

	private String userRecId;
	private String encodedUserRecId;
	private String loginId;
	private String password;
	private String confirmPassword;
	private String accountStatus;
	private String enName;
	private String cnName;
	private String email;
	private List<OfficeRoleDTO> roles = new ArrayList<OfficeRoleDTO>();

	public String getUserRecId() {
		return userRecId;
	}

	public void setUserRecId(String userRecId) {
		this.userRecId = userRecId;
	}

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountStatus() {
		return accountStatus;
	}

	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<OfficeRoleDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<OfficeRoleDTO> roles) {
		this.roles = roles;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		if(!CollectionUtils.isEmpty(roles)){
			for(OfficeRoleDTO role : roles){
				String roleName = role.getRoleId();
				authorities.add(new SimpleGrantedAuthority(roleName));
			}
		}
		return authorities;
	}

	public String getUsername() {
		return this.loginId;
	}

	public boolean isAccountNonExpired() {
		return true;
	}

	public boolean isAccountNonLocked() {
		return true;
	}

	public boolean isCredentialsNonExpired() {
		return true;
	}

	public boolean isEnabled() {
		if(CommonConstant.USER_ACCOUNT_ACTIVE.equals(this.accountStatus)){
			return true;
		}
		return false;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEncodedUserRecId() {
		return encodedUserRecId;
	}

	public void setEncodedUserRecId(String encodedUserRecId) {
		this.encodedUserRecId = encodedUserRecId;
	}
}
