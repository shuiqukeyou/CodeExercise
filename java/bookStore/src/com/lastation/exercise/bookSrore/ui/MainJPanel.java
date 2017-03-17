package com.lastation.exercise.bookSrore.ui;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.common.UserEnum;
import com.lastation.exercise.bookSrore.tool.DefaultJPanel;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainJPanel extends DefaultJPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame mainJFrame;
	private JTextField tfdUserName;
	private JPasswordField pwd;

	/**
	 * Create the panel.
	 */
	
	public MainJPanel(final BookStroeMain mainJFrame) {
		super(mainJFrame, "图书管理系统");
		
		JLabel label = new JLabel("帐号：");
		label.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label.setBounds(246, 148, 72, 33);
		add(label);
		
		tfdUserName = new JTextField();
		tfdUserName.setBounds(356, 144, 260, 36);
		add(tfdUserName);
		tfdUserName.setColumns(10);
		
		JLabel label_1 = new JLabel("密码：");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label_1.setBounds(246, 276, 72, 33);
		add(label_1);
		
		pwd = new JPasswordField();
		pwd.setBounds(356, 276, 260, 32);
		add(pwd);
		
		JButton btnlogin = new JButton("登录");
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO 登录系统待写
				mainJFrame.setUserId(1);
			}
		});
		btnlogin.setBounds(297, 426, 93, 23);
		add(btnlogin);
	}
}
