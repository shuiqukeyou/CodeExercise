package userManager.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import userManager.business.api.UserBuAPI;
import userManager.business.factory.UserBuFactory;
import userManager.vo.UserVO;

public class AddUserUI extends JFrame {

	private JPanel contentPane;
	private JTextField nameTF;
	private JTextField ageTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddUserUI frame = new AddUserUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AddUserUI() {
		setTitle("新增用户");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("姓名");
		label.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label.setBounds(61, 40, 64, 33);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("年龄");
		label_1.setFont(new Font("微软雅黑", Font.BOLD, 24));
		label_1.setBounds(61, 98, 67, 26);
		contentPane.add(label_1);
		
		nameTF = new JTextField();
		nameTF.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		nameTF.setBounds(132, 40, 169, 32);
		contentPane.add(nameTF);
		nameTF.setColumns(10);
		
		ageTF = new JTextField();
		ageTF.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		ageTF.setBounds(132, 98, 169, 29);
		contentPane.add(ageTF);
		ageTF.setColumns(10);
		
		JButton addBtn = new JButton("增加");
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnAddActionPerFormed(e);
			}


		});
		addBtn.setFont(new Font("幼圆", Font.BOLD, 20));
		addBtn.setBounds(79, 169, 93, 33);
		contentPane.add(addBtn);
		
		JButton exitBtn = new JButton("取消");
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		exitBtn.setFont(new Font("幼圆", Font.BOLD, 20));
		exitBtn.setBounds(241, 170, 93, 30);
		contentPane.add(exitBtn);
	}
	
	private void btnAddActionPerFormed(ActionEvent e) {
		// 收集参数
		String name = nameTF.getText();
		String strAge = ageTF.getText();
		int age = 0;
		try {
			age = Integer.parseInt(strAge);
		} catch (NumberFormatException error) {
			JOptionPane.showMessageDialog(this, "年龄输入错误");
			return;
		}
		
		//组织参数
		UserVO user = new UserVO();
		user.setName(name);
		user.setAge(age);
		
		//调用逻辑层
		UserBuAPI ubi = UserBuFactory.getbuImpl();
		Boolean boo =  ubi.create(user);
		
		//根据返回结果显示不同页面
		if (boo) {
			System.out.println("创建成功");
		} else {
			System.out.println("创建失败");
		}
	}

}
