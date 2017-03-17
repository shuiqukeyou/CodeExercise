package com.lastation.exercise.bookSrore.in.vo;

public class InDetailQueryValueObject {
	private int uuid;
	private int inUuid;// 进货单ID
	private int bookUuid;// 书ID
	private int numMin;// 本数下界
	private int numMax;// 本数上界
	private double sumMoneyMin;// 总价上界
	private double sumMoneyMax;// 总价下界
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public int getInUuid() {
		return inUuid;
	}
	public void setInUuid(int inUuid) {
		this.inUuid = inUuid;
	}
	public int getBookUuid() {
		return bookUuid;
	}
	public void setBookUuid(int bookUuid) {
		this.bookUuid = bookUuid;
	}
	
	public int getNumMin() {
		return numMin;
	}
	public void setNumMin(int numMin) {
		this.numMin = numMin;
	}
	public int getNumMax() {
		return numMax;
	}
	public void setNumMax(int numMax) {
		this.numMax = numMax;
	}
	public double getSumMoneyMin() {
		return sumMoneyMin;
	}
	public void setSumMoneyMin(double sumMoneyMin) {
		this.sumMoneyMin = sumMoneyMin;
	}
	public double getSumMoneyMax() {
		return sumMoneyMax;
	}
	public void setSumMoneyMax(double sumMoneyMax) {
		this.sumMoneyMax = sumMoneyMax;
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
		InDetailQueryValueObject other = (InDetailQueryValueObject) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
	
}
