package com.lastation.exercise.bookSrore.user.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.lastation.exercise.bookSrore.common.UserEnum;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserEbiFactory;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class AddUserDio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfdUserName;
	private JPasswordField pfdpwd2;
	private JPasswordField pfdpwd1;
	private UserEbi uedi = UserEbiFactory.getUserEbi();

	/**
	 * Create the dialog.
	 */
	public AddUserDio(final UserManage nowJPanl, BookStroeMain mainJFrame) {
		setTitle("添加用户");
		setBounds(mainJFrame.getX()+150, mainJFrame.getX()+100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(24, 81, 65, 15);
		contentPanel.add(label);
		
		tfdUserName = new JTextField();
		tfdUserName.setBounds(86, 78, 106, 21);
		contentPanel.add(tfdUserName);
		tfdUserName.setColumns(10);
		
		JLabel label_1 = new JLabel("密　码：");
		label_1.setBounds(24, 149, 65, 15);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("确认密码：");
		label_2.setBounds(202, 149, 81, 15);
		contentPanel.add(label_2);
		
		pfdpwd2 = new JPasswordField();
		pfdpwd2.setBounds(277, 146, 115, 21);
		contentPanel.add(pfdpwd2);
		
		pfdpwd1 = new JPasswordField();
		pfdpwd1.setBounds(86, 146, 106, 21);
		contentPanel.add(pfdpwd1);
		
		JLabel label_3 = new JLabel("添加新用户");
		label_3.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label_3.setBounds(158, 10, 125, 30);
		contentPanel.add(label_3);
		
		final JComboBox<String> userType = new JComboBox<String>();
		userType.setBounds(277, 78, 115, 21);
		userType.setModel(new DefaultComboBoxModel<>(
				new String[] {"—请选择—"}));
		for(UserEnum ue:UserEnum.values()){
			userType.addItem(ue.getName());
		}
		contentPanel.add(userType);
		
		JLabel label_4 = new JLabel("用户类型：");
		label_4.setBounds(202, 81, 81, 15);
		contentPanel.add(label_4);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("添加");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 收集,校验参数
						String name = tfdUserName.getText();
						Integer type = userType.getSelectedIndex();
						String pwd1 = String.valueOf(pfdpwd1.getPassword());
						String pwd2 = String.valueOf(pfdpwd2.getPassword());
						if (type == 0) {
							JOptionPane.showMessageDialog(null, "请选择用户类型");
							return;
						}
						if (!pwd1.equals(pwd2)) {
							JOptionPane.showMessageDialog(null, "两次密码输入不一致");
							return;
						}
						// 装载参数
						UserValueObject user = new UserValueObject();
						user.setUserName(name);
						user.setPassWd(pwd1);
						user.setType(UserEnum.getUserEnum(type));
						
						//调用逻辑层
						Boolean boo = uedi.create(user);
						
						//返回结果
						if (boo) {
							JOptionPane.showMessageDialog(null,"添加成功");
							nowJPanl.listRefresh();
							// 清空字段
							tfdUserName.setText("");
							userType.setSelectedIndex(0);
							pfdpwd1.setText("");
							pfdpwd2.setText("");
						} else {
							JOptionPane.showMessageDialog(null, "添加失败");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}
	
}
