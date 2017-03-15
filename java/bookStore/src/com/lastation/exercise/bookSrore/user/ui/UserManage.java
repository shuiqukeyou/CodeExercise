package com.lastation.exercise.bookSrore.user.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.common.UserEnum;
import com.lastation.exercise.bookSrore.tool.DefaultJPanel;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class UserManage extends DefaultJPanel {
	
	private UserEbi ub = UserBusinessFactory.getUserBusinessImpl();
	private JTextField tfdid;
	private JTextField tfdName;
	private JList list;

	/**
	 * Create the panel.
	 */

	public UserManage(final BookStroeMain mainJFrame) {
		super(mainJFrame, "用户管理");
		setBounds(mainJFrame.getX(), mainJFrame.getY(), 800, 600);
		list = new JList<>();
		list.setBounds(300, 78, 350, 200);
		final UserManage nowJPanl =this;
		listRefresh();
		add(list);
		JButton btnAdd = new JButton("添加用户");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog addUserDio = new AddUserDio(nowJPanl,mainJFrame);
				addUserDio.setModal(true);
				addUserDio.setVisible(true);
			}
		});
		btnAdd.setBounds(150, 500, 165, 50);
		add(btnAdd);
		
		JButton btnDel = new JButton("删除用户");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserValueObject uvo = (UserValueObject) list.getSelectedValue();
				if (uvo == null) {
					JOptionPane.showMessageDialog(null,"请选择需要删除的用户");
					return;
				}
				JDialog DeleteDio = new UserDeleteDio(nowJPanl,mainJFrame,uvo);
				DeleteDio.setModal(true);
				DeleteDio.setVisible(true);
			}
		});
		btnDel.setBounds(312, 500, 165, 50);
		add(btnDel);
		
		JButton btnUpdate = new JButton("修改用户");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserValueObject uvo = (UserValueObject) list.getSelectedValue();
				if (uvo == null) {
					JOptionPane.showMessageDialog(null,"请选择需要修改的用户");
					return;
				}
				JDialog UpdateDio = new UpdateDio(nowJPanl,mainJFrame,uvo);
				UpdateDio.setModal(true);
				UpdateDio.setVisible(true);
			}
		});
		btnUpdate.setBounds(475, 500, 165, 50);
		add(btnUpdate);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(183, 449, 28, 18);
		add(lblId);
		
		tfdid = new JTextField();
		tfdid.setBounds(215, 446, 58, 24);
		add(tfdid);
		tfdid.setColumns(10);
		
		JLabel label = new JLabel("用户名：");
		label.setBounds(287, 449, 72, 18);
		add(label);
		
		tfdName = new JTextField();
		tfdName.setBounds(351, 446, 86, 24);
		add(tfdName);
		tfdName.setColumns(10);
		
		final JComboBox<String> userType = new JComboBox<String>();
		userType.setBounds(527, 446, 104, 24);
		userType.setModel(new DefaultComboBoxModel<>(
				new String[] {"—请选择—"}));
		for(UserEnum ue:UserEnum.values()){
			userType.addItem(ue.getName());
		}
		add(userType);
		
		JLabel label_1 = new JLabel("用户类型：");
		label_1.setBounds(451, 449, 93, 18);
		add(label_1);
		
		JButton btnQuery = new JButton("查找用户");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserQueryValueObject uqvo = new UserQueryValueObject();
				String uuidStr = tfdid.getText();
				String name = tfdName.getText();
				Integer type = userType.getSelectedIndex();
				Integer uuid = 0;
				if (uuidStr.trim().length() !=0) {
					try{
						uuid = Integer.decode(uuidStr);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正确的id");
						return;
					}
				}
				uqvo.setUuid(uuid);
				
				if (name.length() !=0) {
					uqvo.setUserName(name);
				}
				
				if (type != 0) {
					uqvo.setType(UserEnum.getUserEnum(type));
				}
				
				List userlist = ub.findByCondition(uqvo);
				list.setListData(userlist.toArray());
			}
		});
		btnQuery.setBounds(674, 445, 93, 27);
		add(btnQuery);
		JButton btnref = new JButton("刷新用户");
		btnref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listRefresh();
			}
		});
		btnref.setBounds(635, 500, 165, 50);
		add(btnref);
		
	}
	
	public void listRefresh(){
		List<UserValueObject> userList = ub.findAll();
		list.setListData(userList.toArray());
	}
}
