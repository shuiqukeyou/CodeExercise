package com.lastation.exercise.bookSrore.in.vo;

import java.io.Serializable;

public class InDetailValueObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uuid;
	private int inUuid;// 进货单ID
	private int bookUuid;// 书ID
	private int num;// 本数
	private double sumMoney;// 总价
	
	public void update(InDetailValueObject idvo) {
		this.inUuid = idvo.inUuid;
		this.bookUuid = idvo.bookUuid;
		this.num = idvo.num;
		this.sumMoney = idvo.sumMoney;
	}
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSumMoney() {
		return sumMoney;
	}
	public void setSumMoney(double sumMoney) {
		this.sumMoney = sumMoney;
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
		InDetailValueObject other = (InDetailValueObject) obj;
		if (uuid != other.uuid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "bookUuid=" + bookUuid + "  单价： " + num + "  总价：" + sumMoney ;
	}
	
	
}
