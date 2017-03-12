package com.lastation.exercise.bookSrore.user.vo;

import java.io.Serializable;

public class UserQueryValueObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String uuid;
	private String userName;
	private String passWd;
	private Integer type;
	
	public UserQueryValueObject(){
	}
	
	public UserQueryValueObject(String uuid, String username, String passWd, int type) {
		this.uuid = uuid;
		this.userName = username;
		this.passWd = passWd;
		this.type = type;
	}
	
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserQueryValueObject other = (UserQueryValueObject) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	
}
