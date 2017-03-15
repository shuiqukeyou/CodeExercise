package com.lastation.exercise.bookSrore.user.vo;

import java.io.Serializable;

import com.lastation.exercise.bookSrore.common.UserEnum;

/**
 * 
 * @author <a href="mailto:shuiqukeyou@gmail.com">ShuiQu</a>
 * @version 2017年3月11日 下午1:01:39
 * @fileName UserValueObject.java
 */

public class UserValueObject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int uuid;
	private String userName;
	private String passWd;
	private UserEnum type;
	
	public UserValueObject(){
	}
	
	public UserValueObject(String username, String passWd, UserEnum type) {
		this.userName = username;
		this.passWd = passWd;
		this.type = type;
	}
	
	public UserValueObject(int uuid, String username, String passWd, UserEnum type) {
		this.uuid = uuid;
		this.userName = username;
		this.passWd = passWd;
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
	public String getPassWd() {
		return passWd;
	}
	public void setPassWd(String passWd) {
		this.passWd = passWd;
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
		UserValueObject other = (UserValueObject) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "id: " + uuid + "  用户名: " + userName + "  用户类型: " + type.getName();
	}

	public void update(UserValueObject user) {
		this.userName = user.userName;
		this.passWd = user.passWd;
		this.type = user.type;
		
	}
}
