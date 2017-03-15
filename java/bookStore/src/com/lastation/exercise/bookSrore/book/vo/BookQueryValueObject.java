package com.lastation.exercise.bookSrore.book.vo;

import java.io.Serializable;

public class BookQueryValueObject implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int uuid;
	private String bookNo;
	private String bookName;
	private double inPriceMin;
	private double inPriceMax;
	private double salePriceMin;
	private double salePriceMax;
	
	public BookQueryValueObject(){
	}
	
	public BookQueryValueObject(String bookNo, String bookName, double inPriceMin, double inPriceMax, double salePriceMin,double salePriceMax){
		this.bookNo = bookNo;
		this.bookName = bookName;
		this.inPriceMin = inPriceMin;
		this.inPriceMax = inPriceMax;
		this.salePriceMin = salePriceMin;
		this.salePriceMax = salePriceMax;
				
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

	public double getInPriceMin() {
		return inPriceMin;
	}

	public void setInPriceMin(double inPriceMin) {
		this.inPriceMin = inPriceMin;
	}

	public double getInPriceMax() {
		return inPriceMax;
	}

	public void setInPriceMax(double inPriceMax) {
		this.inPriceMax = inPriceMax;
	}

	public double getSalePriceMin() {
		return salePriceMin;
	}

	public void setSalePriceMin(double salePriceMin) {
		this.salePriceMin = salePriceMin;
	}

	public double getSalePriceMax() {
		return salePriceMax;
	}

	public void setSalePriceMax(double salePriceMax) {
		this.salePriceMax = salePriceMax;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
	
}
