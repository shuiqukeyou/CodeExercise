package com.lastation.exercise.bookSrore.common;

public enum UserEnum {
	ADMIN(1,"管理员"),BOOK(2,"图书管理员"),IN(3,"进货管理员"),OUT(4,"销售管理员"),STOCK(5,"库存管理员");
	
	private final int type;
	private final String name;
	
	private UserEnum(int type,String name){
		this.type = type;
		this.name = name;
	}
	
	public int getType() {
		return type;
	}
	
	public String getName() {
		return name;
	}
	
	public static UserEnum getUserEnum(int type) {
		switch (type) {
			case 1:
				return ADMIN;
			case 2:
				return BOOK;
			case 3:
				return  IN;
			case 4:
				return OUT;
			case 5:
				return STOCK;	
			default:
				return null;
		}
	}
}
