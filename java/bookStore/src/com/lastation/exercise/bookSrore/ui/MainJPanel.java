package com.lastation.exercise.bookSrore.ui;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.common.UserEnum;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class MainJPanel extends DefaultJPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame mainJFrame;
	private JTextField tfdUserName;
	private JPasswordField pwd;
	private UserEbi ue = UserBusinessFactory.getUserBusinessImpl();

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
				String userName = tfdUserName.getText();
				String passwd = String.valueOf(pwd.getPassword());
				UserQueryValueObject uqvo = new UserQueryValueObject();
				uqvo.setUserName(userName);
				List<UserValueObject> users = ue.findByCondition(uqvo);
				if (users.size() <= 0){
					JOptionPane.showMessageDialog(null, "帐号或密码错误");
					return;
				}
				UserValueObject user = users.get(0);
				if (!user.getPassWd().equals(passwd)) {
					JOptionPane.showMessageDialog(null, "帐号或密码错误");
					return;
				}
				mainJFrame.setUserId(user.getUuid());
				JOptionPane.showMessageDialog(null, "登录成功");
				reUser();
			}
		});
		btnlogin.setBounds(297, 426, 93, 23);
		add(btnlogin);
	}
}
