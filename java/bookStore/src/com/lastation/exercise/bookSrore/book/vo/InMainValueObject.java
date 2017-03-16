package com.lastation.exercise.bookSrore.book.vo;

import java.io.Serializable;

public class InMainValueObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uuid;
	private String bookNo;
	private String bookName;
	private double inPrice;
	private double salePrice;
	
	public InMainValueObject(){
	}
	
	public InMainValueObject(String bookNo, String bookName, double inPrice, double salePrice){
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.inPrice = inPrice;
		this.salePrice = salePrice;
				
	}
	
	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public String getBookNo() {
		return bookNo;
	}

	public void setBookNo(String bookNo) {
		this.bookNo = bookNo;
	}

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public double getInPrice() {
		return inPrice;
	}

	public void setInPrice(double inPrice) {
		this.inPrice = inPrice;
	}

	public double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(double salePrice) {
		this.salePrice = salePrice;
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

	public void update(InMainValueObject book) {
		this.bookNo = book.bookNo;
		this.bookName = book.bookName;
		this.inPrice = book.inPrice;
		this.salePrice = book.salePrice;
	}

	@Override
	public String toString() {
		return "ID：" + uuid + "  书名：" + bookName + "  书号：" + bookNo + "  进价：" + inPrice + "  售价" + salePrice;
	}
	
	
}
