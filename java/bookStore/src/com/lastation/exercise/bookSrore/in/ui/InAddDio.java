package com.lastation.exercise.bookSrore.in.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
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
import javax.swing.border.EmptyBorder;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookBusFactory;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.in.business.ebi.InEbi;
import com.lastation.exercise.bookSrore.in.business.factory.InEbiFactory;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class InAddDio extends JDialog {
	
	private final JPanel contentPanel = new JPanel(); 
	private JTextField tfdsum;
	private InEbi ie = InEbiFactory.getEbi(); // 进货逻辑层
	private BookEbi be = BookBusFactory.getBookEbi(); //图书逻辑层
	private List<InDetailValueObject> inlist = new ArrayList<InDetailValueObject>();//进货详情列表
	private InMainValueObject ivo = new InMainValueObject(); //初始化进货总表
	private JTextField tfdInUser; //用户名
	private UserValueObject user; // 用户对象
	private UserEbi ue =UserBusinessFactory.getUserBusinessImpl(); //用户逻辑层
	private List<String> DetailList = new ArrayList<>(); // 显示在列表中的字符串数组

	public InAddDio(final InManage nowJPanl, BookStroeMain mainJFrame) {
		user = ue.findUser(mainJFrame.getUserId());// 获取当前操作的用户
		
		setTitle("进货");
		setBounds(mainJFrame.getX()+100, mainJFrame.getX()+50, 620, 400);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel label = new JLabel("选择图书：");
		label.setBounds(14, 37, 81, 18);
		contentPanel.add(label);
		
		final JComboBox<String> cbbBook = new JComboBox<String>();
		final List<BookValueObject> bookList = be.findAll();
		cbbBook.setModel(new DefaultComboBoxModel<>(
				new String[] {"—请选择—"}));
		for (BookValueObject bvo:bookList) {
			cbbBook.addItem(bvo.toInString());
		}
		cbbBook.setBounds(88, 36, 376, 21);
		contentPanel.add(cbbBook);
		
		JLabel label_1 = new JLabel("进货明细：");
		label_1.setBounds(14, 68, 81, 26);
		contentPanel.add(label_1);
		
		final JList DioList = new JList();
		DioList.setBounds(24, 111, 464, 161);
		contentPanel.add(DioList);
		
		JLabel label_2 = new JLabel("数量：");
		label_2.setBounds(474, 37, 54, 17);
		contentPanel.add(label_2);
		
		tfdsum = new JTextField();
		tfdsum.setBounds(516, 36, 76, 21);
		contentPanel.add(tfdsum);
		tfdsum.setColumns(10);
		
		JButton btnAddBook = new JButton("添加图书");
		btnAddBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = cbbBook.getSelectedIndex();
				if (index == 0) {
					JOptionPane.showMessageDialog(null, "请选择一本图书");
					return;
				}
				String sumStr = tfdsum.getText();
				if (sumStr.trim().length() <= 0) {
					JOptionPane.showMessageDialog(null, "请输入进货数量");
					return;
				}
				Integer sum = 0;
				try {
					sum = Integer.valueOf(sumStr);
				}catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "请输入正确的进货数量");
					return;
				}
				if (sum <= 0) {
					JOptionPane.showMessageDialog(null, "请输入正确的进货数量");
					return;
				}
				BookValueObject book = bookList.get(index-1); // 获取选择的图书
				int bookid = book.getUuid();
				double bookMoney = book.getInPrice();
				InDetailValueObject idvo = new InDetailValueObject();
				idvo.setBookUuid(bookid); // 设置图书ID
				idvo.setNum(sum); // 设置总本数
				idvo.setSumMoney(sum*bookMoney); // 设置总价
				inlist.add(idvo); // 送入详情队列
				String listStr = "书名：《" + book.getBookName() + "》  图书号："  + book.getBookNo()  +  " 进价：" + bookMoney + "  数量： " + sum;
				DetailList.add(listStr);
				DioList.setListData(DetailList.toArray());
			}
		});
		btnAddBook.setBounds(498, 103, 93, 33);
		contentPanel.add(btnAddBook);
		
		JLabel lblNewLabel = new JLabel("当前操作人：");
		lblNewLabel.setBounds(395, 69, 93, 24);
		contentPanel.add(lblNewLabel);
		tfdInUser = new JTextField();
		tfdInUser.setEditable(false);
		tfdInUser.setText(user.getUserName());
		tfdInUser.setBounds(506, 69, 86, 24);
		contentPanel.add(tfdInUser);
		tfdInUser.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (inlist.size() <= 0) {
							JOptionPane.showMessageDialog(null, "没有添加任何书籍");
							return;
						}
						ivo.setInDate(new Date().getTime()); // 设置进货时间
						ivo.setInUserUuid(user.getUuid()); // 设置进货人UUID
						ivo.setInUserName(user.getUserName());
						boolean boo = ie.create(ivo, inlist);
						if (boo) {
							nowJPanl.mListRefresh();
							JOptionPane.showMessageDialog(null, "添加进货成功");
							dispose();
						}else {
							JOptionPane.showMessageDialog(null, "添加进货失败");
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("退出");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
