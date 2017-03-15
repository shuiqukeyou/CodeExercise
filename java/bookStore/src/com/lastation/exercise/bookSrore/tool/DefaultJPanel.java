package com.lastation.exercise.bookSrore.tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.lastation.exercise.bookSrore.book.ui.BookManage;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.user.ui.UserManage;

import java.awt.FlowLayout;

public class DefaultJPanel extends JPanel {
	protected JButton btnBookManage;
	protected JButton btnInManage;
	protected JButton btnOutManage;
	protected JButton btnUserManage;

	/**
	 * Create the panel.
	 */
	public DefaultJPanel(final BookStroeMain mainJFrame, String title) {
		setBackground(Color.WHITE);
		setLayout(null);
		setSize(800, 600);
		
		JPanel jPanel = new JPanel();
		jPanel.setBackground(new Color(100, 149, 237));
		jPanel.setLocation(0, 0);
		jPanel.setSize(150, 600);
		add(jPanel);
		jPanel.setLayout(null);
		
		btnBookManage = new JButton("图书管理");
		btnBookManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new BookManage(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnBookManage.setBounds(27, 77, 93, 23);
		jPanel.add(btnBookManage);
		
		btnInManage = new JButton("进货管理");
		btnInManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 进货管理待写
			}
		});
		btnInManage.setBounds(27, 144, 93, 23);
		jPanel.add(btnInManage);
		
		btnOutManage = new JButton("出售管理");
		btnOutManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO 出售管理待写
			}
		});
		btnOutManage.setBounds(27, 200, 93, 23);
		jPanel.add(btnOutManage);
		
		btnUserManage = new JButton("用户管理");
		btnUserManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new UserManage(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnUserManage.setBounds(27, 256, 93, 23);
		jPanel.add(btnUserManage);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(149, 0, 665, 56);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		panel.add(lblNewLabel);
		
	}
}
