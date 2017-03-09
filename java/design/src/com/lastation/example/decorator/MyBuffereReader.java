package com.lastation.example.decorator;

import java.io.FileReader;
import java.io.IOException;

// 带缓存的readchar
public class MyBuffereReader {
	private char[] chub = new char[1024];
	private int count = 0;
	private int post = 0;
	
	// 待读取的文件对象
	private FileReader r = null;
	
	public MyBuffereReader(FileReader r){
		super();
		this.r = r;
	}
	
	public int myReadChar() throws IOException {
		// 若字符缓存长度小于等于0，则重新加载
		if (count <= 0) {
			count = r.read(chub);
			if (count == -1){
				return -1;
			}
			post = 0;
		}
		char ch = chub[post];
		post++;
		count--;
		return ch;
	}
	
	public void close() throws IOException {
		r.close();
	}
}
