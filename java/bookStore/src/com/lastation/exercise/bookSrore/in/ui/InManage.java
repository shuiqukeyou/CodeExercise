package com.lastation.exercise.bookSrore.in.ui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.tool.DefaultJPanel;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class InManage extends DefaultJPanel {
	private UserEbi ub = UserBusinessFactory.getUserBusinessImpl();
	private JTextField tfdid;
	private JTextField tfdName;
	private JList list;
	/**
	 * Create the panel.
	 */
	public InManage(final BookStroeMain mainJFrame) {
		super(mainJFrame, "进货管理");
		setBounds(mainJFrame.getX(), mainJFrame.getY(), 800, 600);
		list = new JList<>();
		list.setBounds(300, 78, 350, 200);
		final InManage nowJPanl =this;
		listRefresh();
		add(list);
		JButton btnAdd = new JButton("添加用户");
		btnAdd.setBounds(150, 500, 165, 50);
		add(btnAdd);
		
		JButton btnDel = new JButton("删除用户");
		btnDel.setBounds(312, 500, 165, 50);
		add(btnDel);
		
		JButton btnUpdate = new JButton("修改用户");
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

		
		JLabel label_1 = new JLabel("用户类型：");
		label_1.setBounds(451, 449, 93, 18);
		add(label_1);
		
		JButton btnQuery = new JButton("查找用户");
		btnQuery.setBounds(674, 445, 93, 27);
		add(btnQuery);
		JButton btnref = new JButton("刷新用户");
		btnref.setBounds(635, 500, 165, 50);
		add(btnref);
		
	}
	
	public void listRefresh(){
		List<UserValueObject> userList = ub.findAll();
		list.setListData(userList.toArray());
	}
}


