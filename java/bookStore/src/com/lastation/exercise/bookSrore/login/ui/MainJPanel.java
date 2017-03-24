package com.lastation.exercise.bookSrore.login.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.common.UserEnum;
import com.lastation.exercise.bookSrore.login.business.ebi.LoginEbi;
import com.lastation.exercise.bookSrore.login.business.factory.LoginEbiFactory;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.ui.DefaultJPanel;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserEbiFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

public class MainJPanel extends DefaultJPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame mainJFrame;
	private JTextField tfdUserName;
	private JPasswordField pwd;
	private UserEbi ue = UserEbiFactory.getUserEbi();
	private LoginEbi le = LoginEbiFactory.getLoginEbi();

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
		btnlogin.setFont(new Font("微软雅黑", Font.PLAIN, 24));
		btnlogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = tfdUserName.getText();
				String passwd = String.valueOf(pwd.getPassword());
				Map<String, Object> result = le.Login(userName, passwd);
				UserValueObject user = (UserValueObject) result.get("user");
				if (user == null) {
					JOptionPane.showMessageDialog(null, result.get("error"));
					return;
				}
				mainJFrame.setUserId(user.getUuid());
				JOptionPane.showMessageDialog(null, "登录成功");
				reUser(); 
				mainJFrame.setContentPane(new WelcomeJPanel(mainJFrame));
			}
		});
		btnlogin.setBounds(246, 399, 130, 50);
		btnlogin.setMnemonic(KeyEvent.VK_ENTER); // 为确认键绑定回车键
		add(btnlogin);
		
	}
}
