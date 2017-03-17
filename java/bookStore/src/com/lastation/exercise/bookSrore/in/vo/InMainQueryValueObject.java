package com.lastation.exercise.bookSrore.in.vo;

public class InMainQueryValueObject {
	private int uuid;
	private long inDateMin;
	private long inDateMax;
	private int inUserUuid;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	
	public long getInDateMin() {
		return inDateMin;
	}
	public void setInDateMin(long inDateMin) {
		this.inDateMin = inDateMin;
	}
	public long getInDateMax() {
		return inDateMax;
	}
	public void setInDateMax(long inDateMax) {
		this.inDateMax = inDateMax;
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
		InMainQueryValueObject other = (InMainQueryValueObject) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
	
}
