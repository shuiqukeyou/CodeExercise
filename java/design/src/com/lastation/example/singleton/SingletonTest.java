package com.lastation.example.singleton;

import com.lastation.example.singleton.demo1.Singleton1;
import com.lastation.example.singleton.demo2.Singleton2;

public class SingletonTest {
	public static void main(String[] args) {
		Singleton1 sing1_1 = Singleton1.getInstance();
		System.out.println(sing1_1.hashCode());
		Singleton1 sing1_2 = Singleton1.getInstance();
		System.out.println(sing1_2.hashCode());
		
		Singleton2 sing2_1 = Singleton2.getInstance();
		System.out.println(sing2_1.hashCode());
		Singleton2 sing2_2 = Singleton2.getInstance();
		System.out.println(sing2_2.hashCode());
		
	}
}
