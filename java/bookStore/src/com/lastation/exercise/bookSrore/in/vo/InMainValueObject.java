package com.lastation.exercise.bookSrore.in.vo;

import java.io.Serializable;

import com.lastation.exercise.bookSrore.tool.DateUitl;

public class InMainValueObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uuid;
	private long inDate; // 进货日期
	private int inUserUuid;// 进货人
	private String inUserName;
	
	public String getInUserName() {
		return inUserName;
	}
	public void setInUserName(String inUserName) {
		this.inUserName = inUserName;
	}
	public void update(InMainValueObject imvo) {
		this.inDate = imvo.inDate;
		this.inUserUuid = imvo.inUserUuid;
	}
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public long getInDate() {
		return inDate;
	}
	public void setInDate(long inDate) {
		this.inDate = inDate;
	}
	public int getInUserUuid() {
		return inUserUuid;
	}
	public void setInUserUuid(int inUserUuid) {
		this.inUserUuid = inUserUuid;
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
		InMainValueObject other = (InMainValueObject) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "进货时间：" + DateUitl.long2String(inDate) + ", 进货人：" + inUserName;
	}
	
}
