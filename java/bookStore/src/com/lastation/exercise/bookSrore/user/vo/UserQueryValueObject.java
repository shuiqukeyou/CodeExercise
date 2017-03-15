package com.lastation.exercise.bookSrore.user.vo;

import java.io.Serializable;

import com.lastation.exercise.bookSrore.common.UserEnum;

public class UserQueryValueObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int uuid;
	private String userName;
	private UserEnum type;
	
	public UserQueryValueObject(){
	}
	
	public UserQueryValueObject(int uuid, String username, UserEnum type) {
		this.uuid = uuid;
		this.userName = username;
		this.type = type;
	}
	
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public UserEnum getType() {
		return type;
	}
	public void setType(UserEnum type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + uuid;
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
		if (uuid != other.uuid)
			return false;
		return true;
	}


	
	
}
