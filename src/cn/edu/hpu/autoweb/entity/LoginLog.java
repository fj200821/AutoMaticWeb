package cn.edu.hpu.autoweb.entity;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {

	private Integer id;
	private String UserId;
	private String LogIp;
	private Date LoginDateTime;
	private Date LogoutDateTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserId() {
		return UserId;
	}

	public void setUserId(String userId) {
		UserId = userId;
	}

	public String getLogIp() {
		return LogIp;
	}

	public void setLogIp(String logIp) {
		LogIp = logIp;
	}

	public Date getLoginDateTime() {
		return LoginDateTime;
	}

	public void setLoginDateTime(Date loginDateTime) {
		LoginDateTime = loginDateTime;
	}

	public Date getLogoutDateTime() {
		return LogoutDateTime;
	}

	public void setLogoutDateTime(Date logoutDateTime) {
		LogoutDateTime = logoutDateTime;
	}
}
