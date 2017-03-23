package com.lastation.exercise.bookSrore.stock.vo;

public class StockQueryValueObject {
	private int uuid;
	private int bookUuid;
	private int sumMun;
	public int getUuid() {
		return uuid;
	}
	public void setUuid(int uuid) {
		this.uuid = uuid;
	}
	public int getBookUuid() {
		return bookUuid;
	}
	public void setBookUuid(int bookUuid) {
		this.bookUuid = bookUuid;
	}
	public int getSumMun() {
		return sumMun;
	}
	public void setSumMun(int sumMun) {
		this.sumMun = sumMun;
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
		StockQueryValueObject other = (StockQueryValueObject) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
	
}
