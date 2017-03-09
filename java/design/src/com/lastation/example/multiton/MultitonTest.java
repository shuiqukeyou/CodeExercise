package com.lastation.example.multiton;

public class MultitonTest {
	public static void main(String[] args) {
		for (int i = 0;i < 4;i++){
			Multiton obj = Multiton.getInstance();
			System.out.println(obj.hashCode());
		}
	}
}
