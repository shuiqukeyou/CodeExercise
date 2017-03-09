package com.lastation.example.decorator;

import java.io.FileReader;
import java.io.IOException;

public class DecotatorTest {
	public static void main(String[] args) {
		try {
			FileReader r = new FileReader("ReadTest.txt");
			MyBuffereReader br = new MyBuffereReader(r);
			int ch  = 0;
			while((ch = br.myReadChar()) != -1){
				System.out.print((char)ch);
			}
			
			br.close();
		} catch (IOException e){
			e.printStackTrace();
		}
		
	}
}
