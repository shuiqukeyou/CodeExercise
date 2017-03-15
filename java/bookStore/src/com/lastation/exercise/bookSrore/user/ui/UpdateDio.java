package com.lastation.exercise.bookSrore.user.ui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UpdateDio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private UserEbi uedi = UserBusinessFactory.getUserBusinessImpl();
	private JTextField tfdName;
	private JPasswordField pwd1;
	private JPasswordField pwd2;

	/**
	 * Create the dialog.
	 */
	public UpdateDio(final UserManage nowJPanl, BookStroeMain parentJF, final UserValueObject uvo) {
		setTitle("删除用户");
		setBounds(parentJF.getX()+150, parentJF.getX()+100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(14, 68, 69, 15);
		contentPanel.add(lblId);
		
		JLabel label = new JLabel("原用户名：");
		label.setBounds(14, 96, 83, 27);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("原 类 型：");
		label_1.setBounds(14, 136, 83, 16);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("修改用户");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label_2.setBounds(163, 10, 102, 30);
		contentPanel.add(label_2);
		
		textField = new JTextField(""+uvo.getUuid());
		textField.setEditable(false);
		textField.setBounds(97, 64, 66, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(uvo.getUserName());
		textField_1.setEditable(false);
		textField_1.setBounds(97, 98, 111, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(uvo.getType().getName());
		textField_2.setEditable(false);
		textField_2.setBounds(97, 133, 111, 21);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_3 = new JLabel("新用户名：");
		label_3.setBounds(222, 96, 85, 21);
		contentPanel.add(label_3);
		
		JLabel label_4 = new JLabel("新 类 型：");
		label_4.setBounds(222, 136, 85, 16);
		contentPanel.add(label_4);
		
		tfdName = new JTextField();
		tfdName.setBounds(300, 98, 111, 21);
		contentPanel.add(tfdName);
		tfdName.setColumns(10);
		
		JLabel label_5 = new JLabel("新 密 码：");
		label_5.setBounds(14, 177, 83, 19);
		contentPanel.add(label_5);
		
		pwd1 = new JPasswordField();
		pwd1.setBounds(97, 175, 111, 21);
		contentPanel.add(pwd1);
		
		JLabel label_6 = new JLabel("重复密码：");
		label_6.setBounds(222, 177, 85, 17);
		contentPanel.add(label_6);
		
		pwd2 = new JPasswordField();
		pwd2.setBounds(300, 175, 111, 21);
		contentPanel.add(pwd2);
		
		JLabel label_7 = new JLabel("注：不需要修改的部分不填");
		label_7.setFont(new Font("微软雅黑", Font.BOLD, 16));
		label_7.setForeground(Color.RED);
		label_7.setBounds(207, 57, 200, 21);
		contentPanel.add(label_7);
		
		final JComboBox<String> userType = new JComboBox<String>();
		userType.setBounds(300, 134, 111, 21);
		userType.setModel(new DefaultComboBoxModel<>(
				new String[] {"—请选择—"}));
		for(UserEnum ue:UserEnum.values()){
			userType.addItem(ue.getName());
		}
		contentPanel.add(userType);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 修改标签
						Boolean changeTag = false;
						// 收集参数
						String newName = tfdName.getText();
						Integer newType = userType.getSelectedIndex();
						String newPwd1 = String.valueOf(pwd1.getPassword());
						String newPwd2 = String.valueOf(pwd2.getPassword());

						// 组装新对象,验证参数
						if (newPwd1.length() !=0) {
							if (newPwd1.equals(newPwd2)) {
								JOptionPane.showMessageDialog(null, "两次密码输入不一致");
								return;
							}
							uvo.setPassWd(newPwd1);
							changeTag = true;
						}
						if (newName.trim().length() != 0) {
							uvo.setUserName(newName.trim());
							changeTag = true;
						}
						if (newType !=0) {
							uvo.setType(UserEnum.getUserEnum(newType));
							changeTag = true;
						}
						
						// 如果至少一项被修改了，则调用逻辑层
						if (changeTag) {
							Boolean boo = uedi.update(uvo);
							// 返回结果
							if (boo) {
								JOptionPane.showMessageDialog(null,"修改成功");
								nowJPanl.listRefresh();
								dispose();
								return;
							} else {
								JOptionPane.showMessageDialog(null, "修改失败");
								dispose();
								return;
							}
						}
						JOptionPane.showMessageDialog(null,"没有修改任何项目");
						
					}
				});
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
				buttonPane.add(cancelButton);
			}
		}
	}
}
