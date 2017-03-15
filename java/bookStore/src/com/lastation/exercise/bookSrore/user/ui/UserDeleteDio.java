package com.lastation.exercise.bookSrore.user.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UserDeleteDio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private UserEbi uedi = UserBusinessFactory.getUserBusinessImpl();
	
	/**
	 * Create the dialog.
	 */
	public UserDeleteDio(final UserManage nowJPanl, BookStroeMain parentJF, final UserValueObject uvo) {
		setTitle("删除用户");
		setBounds(parentJF.getX()+150, parentJF.getX()+100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(47, 83, 81, 15);
		contentPanel.add(lblId);
		
		JLabel label = new JLabel("用 户 名：");
		label.setBounds(47, 114, 81, 15);
		contentPanel.add(label);
		
		JLabel label_1 = new JLabel("用户类型：");
		label_1.setBounds(47, 145, 81, 15);
		contentPanel.add(label_1);
		
		JLabel label_2 = new JLabel("删除用户");
		label_2.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label_2.setBounds(163, 10, 102, 30);
		contentPanel.add(label_2);
		
		textField = new JTextField(""+uvo.getUuid());
		textField.setEditable(false);
		textField.setBounds(126, 80, 66, 21);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(uvo.getUserName());
		textField_1.setEditable(false);
		textField_1.setBounds(126, 111, 111, 21);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField(uvo.getType().getName());
		textField_2.setEditable(false);
		textField_2.setBounds(126, 142, 111, 21);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("注意：一旦删除将无法恢复");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		lblNewLabel.setBounds(218, 184, 204, 26);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确定");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// 调用逻辑层
						Boolean boo = uedi.delete(uvo.getUuid());
						//返回结果
						if (boo) {
							JOptionPane.showMessageDialog(null,"删除成功");
							nowJPanl.listRefresh();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
							dispose();
						}
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
