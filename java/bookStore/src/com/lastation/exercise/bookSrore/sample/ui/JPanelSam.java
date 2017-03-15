package com.lastation.exercise.bookSrore.sample.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.user.ui.UserManage;

public class JPanelSam extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	
	public JPanelSam(final BookStroeMain mainJFrame) {
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(800, 600);
		
		JPanel jPanel = new JPanel();
		jPanel.setBackground(new Color(100, 149, 237));
		jPanel.setLocation(0, 0);
		jPanel.setSize(150, 600);
		add(jPanel);
		jPanel.setLayout(null);
		
		JButton btnBookManage = new JButton("图书管理");
		btnBookManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new UserManage(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnBookManage.setBounds(27, 77, 93, 23);
		jPanel.add(btnBookManage);
		
		JButton btnInManage = new JButton("进货管理");
		btnInManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 进货管理待写
			}
		});
		btnInManage.setBounds(27, 144, 93, 23);
		jPanel.add(btnInManage);
		
		JButton btnOutManage = new JButton("出售管理");
		btnOutManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 出售管理待写
			}
		});
		btnOutManage.setBounds(27, 200, 93, 23);
		jPanel.add(btnOutManage);
		
		JButton btnUserManage = new JButton("用户管理");
		btnUserManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new UserManage(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnUserManage.setBounds(27, 256, 93, 23);
		jPanel.add(btnUserManage);
		
		JLabel label = new JLabel("图书管理系统");
		label.setFont(new Font("微软雅黑", Font.BOLD, 36));
		label.setBounds(396, 13, 221, 67);
		add(label);
		
		JLabel label_1 = new JLabel("用户名：");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		label_1.setBounds(285, 195, 101, 33);
		add(label_1);
		
		textField = new JTextField();
		textField.setBounds(396, 195, 287, 32);
		add(textField);
		textField.setColumns(10);
		
		JLabel label_2 = new JLabel("密　码：");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		label_2.setBounds(285, 283, 101, 33);
		add(label_2);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(394, 283, 289, 33);
		add(passwordField);
		
	}
}
