package com.lastation.exercise.bookSrore.tool;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class FileIOUitl {
	
	private FileIOUitl(){
	}
	
	//读取数据文件
	@SuppressWarnings("unchecked")
	public static<E> List<E> FileInput(String name) {
		List<E> list = new ArrayList<>();
		File file = new File(name);
		if (file.exists()) {
			ObjectInputStream in = null;
			try {
				in = new ObjectInputStream(new FileInputStream(file));
				list = (List<E>)in.readObject();
			} catch (FileNotFoundException e) { //文件没找到
				JOptionPane.showMessageDialog(null, "未找到数据文件，准备创建新文件");
				e.printStackTrace();
			} catch (ClassNotFoundException e) {//文件中储存的类损坏
				JOptionPane.showMessageDialog(null, "数据文件损坏，准备创建新文件");
				e.printStackTrace();
			}catch (IOException e) {//文件打开失败
				JOptionPane.showMessageDialog(null, "数据文件打开失败");
				e.printStackTrace();
			} finally {
				if (in != null){
					try {
						in.close();
					} catch (IOException e) {//关流失败
						throw new RuntimeException("数据文件流关闭失败");
					}
				}
				
			}
		}
		return list;
	}
	
	public static<E> void FileOut(String name,List<E> list) {
		
		ObjectOutputStream out = null;
		try {
			out = new ObjectOutputStream(new FileOutputStream(name));
			out.writeObject(list);
		} catch (FileNotFoundException e1) {//文件不存在
			JOptionPane.showMessageDialog(null, "未找到数据文件");
			e1.printStackTrace();
		} catch (IOException e1) {//写入失败
			JOptionPane.showMessageDialog(null, "数据文件写入失败");
			e1.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {//关流失败
					throw new RuntimeException("文件流关闭失败");
				}
			}
		}
	}
	
}
