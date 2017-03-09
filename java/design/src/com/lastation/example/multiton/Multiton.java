package com.lastation.example.multiton;

import java.util.HashMap;
import java.util.Map;


public class Multiton {
	private static Map<Integer, Multiton> multitonPool  = new HashMap<>();
	
	private static int no = 0;
	
	private static int MAX = 3;
	
	public static synchronized Multiton getInstance(){
		Multiton obj = multitonPool.get(no);
		if (obj == null) {
			obj = new Multiton();
			multitonPool.put(no, obj);
		}
		no = (no+1)%MAX;
		return obj;
	}

}
