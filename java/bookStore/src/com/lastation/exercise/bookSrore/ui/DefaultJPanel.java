package com.lastation.exercise.bookSrore.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.lastation.exercise.bookSrore.book.ui.BookManage;
import com.lastation.exercise.bookSrore.common.UserEnum;
import com.lastation.exercise.bookSrore.in.ui.InManage;
import com.lastation.exercise.bookSrore.login.ui.MainJPanel;
import com.lastation.exercise.bookSrore.stock.ui.StockJPanel;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.ui.UserManage;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

import java.awt.FlowLayout;

import javax.swing.SwingConstants;

public class DefaultJPanel extends JPanel {
	protected JButton btnBookManage;
	protected JButton btnInManage;
	protected JButton btnOutManage;
	protected JButton btnUserManage;
	private UserEbi ue = UserBusinessFactory.getUserBusinessImpl();
	private BookStroeMain mainJFrame;
	private JLabel libuser;

	/**
	 * Create the panel.
	 */
	public DefaultJPanel(final BookStroeMain mainJFrame, String title) {
		this.mainJFrame = mainJFrame;
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
		btnBookManage.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnBookManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserValueObject user = ue.findUser(mainJFrame.getUserId());
				if (user == null) {
					JOptionPane.showMessageDialog(null, "请先登录");
					return;
				}
				UserEnum userType = user.getType();
				if ((!userType.equals(UserEnum.ADMIN)) && (!userType.equals(UserEnum.BOOK))) {
					JOptionPane.showMessageDialog(null, "没有权限");
					return;
				}
				JPanel panel = new BookManage(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnBookManage.setBounds(25, 125, 100, 35);
		jPanel.add(btnBookManage);
		
		btnInManage = new JButton("进货管理");
		btnInManage.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnInManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserValueObject user = ue.findUser(mainJFrame.getUserId());
				if (user == null) {
					JOptionPane.showMessageDialog(null, "请先登录");
					return;
				}
				UserEnum userType = user.getType();
				if ((!userType.equals(UserEnum.ADMIN)) && (!userType.equals(UserEnum.IN))) {
					JOptionPane.showMessageDialog(null, "没有权限");
					return;
				}
				JPanel panel = new InManage(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnInManage.setBounds(25, 180, 100, 35);
		jPanel.add(btnInManage);
		
		btnOutManage = new JButton("出售管理");
		btnOutManage.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnOutManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserValueObject user = ue.findUser(mainJFrame.getUserId());
				if (user == null) {
					JOptionPane.showMessageDialog(null, "请先登录");
					return;
				}
				UserEnum userType = user.getType();
				if ((!userType.equals(UserEnum.ADMIN)) && (!userType.equals(UserEnum.OUT))) {
					JOptionPane.showMessageDialog(null, "没有权限");
					return;
				}
				JOptionPane.showMessageDialog(null, "可进货模块几乎一样的代码所以懒的写");
				
			}
		});
		btnOutManage.setBounds(25, 238, 100, 35);
		jPanel.add(btnOutManage);
		
		btnUserManage = new JButton("用户管理");
		btnUserManage.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnUserManage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserValueObject user = ue.findUser(mainJFrame.getUserId());
				if (user == null) {
					JOptionPane.showMessageDialog(null, "请先登录");
					return;
				}
				UserEnum userType = user.getType();
				if (!userType.equals(UserEnum.ADMIN)) {
					JOptionPane.showMessageDialog(null, "没有权限");
					return;
				}
				JPanel panel = new UserManage(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnUserManage.setBounds(25, 353, 100, 35);
		jPanel.add(btnUserManage);
		
		JButton btnLogOff = new JButton("注销");
		btnLogOff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainJFrame.setUserId(0);
				JPanel panel = new MainJPanel(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnLogOff.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnLogOff.setBounds(25, 412, 100, 35);
		jPanel.add(btnLogOff);
		
		JLabel lblNewLabel_1 = new JLabel("当前用户：");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(10, 28, 100, 28);
		jPanel.add(lblNewLabel_1);
		
		this.libuser = new JLabel("TEST");
		reUser();
		libuser.setHorizontalAlignment(SwingConstants.CENTER);
		libuser.setBounds(25, 69, 85, 26);
		
		jPanel.add(libuser);
		
		JButton btnNewButton = new JButton("库存管理");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserValueObject user = ue.findUser(mainJFrame.getUserId());
				if (user == null) {
					JOptionPane.showMessageDialog(null, "请先登录");
					return;
				}
				UserEnum userType = user.getType();
				if ((!userType.equals(UserEnum.ADMIN)) && (!userType.equals(UserEnum.STOCK))) {
					JOptionPane.showMessageDialog(null, "没有权限");
					return;
				}
				JPanel panel = new StockJPanel(mainJFrame);
				mainJFrame.setContentPane(panel);
				mainJFrame.validate();
			}
		});
		btnNewButton.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnNewButton.setBounds(25, 294, 100, 35);
		jPanel.add(btnNewButton);
	
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(149, 0, 665, 56);
		add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblNewLabel = new JLabel(title);
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		panel.add(lblNewLabel);
		
	}
	
	protected void reUser(){
		UserValueObject user = ue.findUser(mainJFrame.getUserId());
		if (user == null) {
			libuser.setText("无");
		} else {
			libuser.setText(user.getUserName() + " " + user.getType().getName());
		}
	}
}
