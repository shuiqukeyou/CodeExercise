package com.lastation.exercise.bookSrore.common.uuid.uuidEnum;

public enum UuidEnum {
	USER("USER"),
	BOOK("BookModel"),
	IN_MAIN("InMainModel"),
	IN_DETAIL("InDetailModel"),
	OUT_MAIN("OutMainModel"),
	OUT_DETAIL("OutDetailModel"),
	STOCK("Stock");
	
	private String VOName;
	
	private UuidEnum(String voName){
		this.VOName = voName;
	}

	public String getVOName() {
		return VOName;
	}

}
