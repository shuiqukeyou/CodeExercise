package com.lastation.exercise.bookSrore.common.uuid.vo;

import java.io.Serializable;

public class UuidValueObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String VOName;
	private int Uuid;
	
	public UuidValueObject(){
	}
	
	public String getVOName() {
		return VOName;
	}
	public void setVOName(String vOName) {
		VOName = vOName;
	}
	public int getVOUuid() {
		return Uuid;
	}
	public void setUuid(int uuid) {
		this.Uuid = uuid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Uuid;
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
		UuidValueObject other = (UuidValueObject) obj;
		if (Uuid != other.Uuid)
			return false;
		return true;
	}
	
	
}
