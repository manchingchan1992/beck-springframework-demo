package cn.org.polaris.action;

import cn.org.polaris.dto.InternalUserDTO;

public class IndexAction {
	
	private InternalUserDTO user;

	public InternalUserDTO getUser() {
		return user;
	}

	public void setUser(InternalUserDTO user) {
		this.user = user;
	}

	public String index() {
		return "success";
	}
	
	public String login(){
		System.out.println("##################" + user.getLoginID() + "," + user.getPassword());
		return "success";
	}
}
