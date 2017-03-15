package com.lastation.exercise.bookSrore.book.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookBusFactory;
import com.lastation.exercise.bookSrore.book.vo.BookQueryValueObject;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.tool.DefaultJPanel;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;

public class BookManage extends DefaultJPanel {
	private BookEbi ub = BookBusFactory.getBookEbi();
	private JTextField tfdName;
	private JTextField tfdNo;
	private JList list;
	private JTextField tfdInMin;
	private JTextField tfdInMax;
	private JTextField tfdSaleMin;
	private JTextField tfdsaleMax;
	private JTextField tfdId;
	/**
	 * Create the panel.
	 */
	public BookManage(final BookStroeMain mainJFrame) {
		super(mainJFrame,"图书管理");
		list = new JList<>();
		list.setBounds(200, 78, 552, 200);
		final BookManage nowJPanl =this;
		listRefresh();
		add(list);
		JButton btnAdd = new JButton("添加书籍");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog addBookDio = new AddBookDio(nowJPanl,mainJFrame);
				addBookDio.setModal(true);
				addBookDio.setVisible(true);
			}
		});
		btnAdd.setBounds(150, 500, 165, 50);
		add(btnAdd);
		
		JButton btnDel = new JButton("删除书籍");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookValueObject uvo = (BookValueObject) list.getSelectedValue();
				if (uvo == null) {
					JOptionPane.showMessageDialog(null,"请选择需要删除的书籍");
					return;
				}
				JDialog DeleteDio = new BookDeleteDio(nowJPanl,mainJFrame,uvo);
				DeleteDio.setModal(true);
				DeleteDio.setVisible(true);
			}
		});
		btnDel.setBounds(312, 500, 165, 50);
		add(btnDel);
		
		JButton btnUpdate = new JButton("修改书籍");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookValueObject uvo = (BookValueObject) list.getSelectedValue();
				if (uvo == null) {
					JOptionPane.showMessageDialog(null,"请选择需要修改的书籍");
					return;
				}
				JDialog UpdateDio = new BookUpdateDio(nowJPanl,mainJFrame,uvo);
				UpdateDio.setModal(true);
				UpdateDio.setVisible(true);
			}
		});
		btnUpdate.setBounds(475, 500, 165, 50);
		add(btnUpdate);
		
		JLabel lblId = new JLabel("书名：");
		lblId.setBounds(296, 348, 45, 18);
		add(lblId);
		
		tfdName = new JTextField();
		tfdName.setBounds(346, 345, 111, 24);
		add(tfdName);
		tfdName.setColumns(10);
		
		JLabel label = new JLabel("书号：");
		label.setBounds(486, 348, 72, 18);
		add(label);
		
		tfdNo = new JTextField();
		tfdNo.setBounds(536, 345, 104, 24);
		add(tfdNo);
		tfdNo.setColumns(10);
		
		JButton btnQuery = new JButton("查找书籍");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BookQueryValueObject bqvo = new BookQueryValueObject();
				String idStr = tfdId.getText();
				String name = tfdName.getText();
				String no = tfdNo.getText();
				String inMinStr = tfdInMin.getText();
				String inMaxStr = tfdInMax.getText();
				String saleMinStr = tfdSaleMin.getText();
				String saleMaxStr = tfdsaleMax.getText();
				
				if (idStr.length() > 0) {
					try {
						Integer uuid = Integer.parseInt(idStr);
						bqvo.setUuid(uuid);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正确的ID");
						QueryRefresh();
						return;
					}
				} 
				
				
				if (name.trim().length() !=0) {
					bqvo.setBookName(name);
				}
				
				if (no.length() !=0) {
					bqvo.setBookNo(no.trim());
				}
				
				if (inMinStr.length() > 0) {
					try {
						Double inMin = Double.parseDouble(inMinStr);
						bqvo.setInPriceMin(inMin);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正确最低进价");
						QueryRefresh();
						return;
					}
				} else {
					bqvo.setInPriceMin(0);
				}
				
				if (inMaxStr.length() > 0) {
					try {
						Double inMax = Double.parseDouble(inMaxStr);
						bqvo.setInPriceMax(inMax);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正确最高进价");
						QueryRefresh();
						return;
					}
				} else {
					bqvo.setInPriceMax(Double.MAX_VALUE);
				}
				if (saleMinStr.length() > 0) {
					try {
						Double saleMin = Double.parseDouble(saleMinStr);
						bqvo.setSalePriceMin(saleMin);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正确最低售价");
						QueryRefresh();
						return;
					}
					
				} else {
					bqvo.setInPriceMin(0);
				}
				if (saleMaxStr.length() > 0) {
					try {
						Double saleMax = Double.parseDouble(saleMaxStr);
						bqvo.setSalePriceMax(saleMax);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正确最高售价");
						QueryRefresh();
						return;
					}
				}else {
					bqvo.setSalePriceMax(Double.MAX_VALUE);
				}

				
				List booklist = ub.findByCondition(bqvo);
				list.setListData(booklist.toArray());
				QueryRefresh();
			}
		});
		btnQuery.setBounds(672, 344, 93, 27);
		add(btnQuery);
		JButton btnref = new JButton("刷新书籍");
		btnref.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listRefresh();
				QueryRefresh();
			}
		});
		btnref.setBounds(635, 500, 165, 50);
		add(btnref);
		
		tfdInMin = new JTextField();
		tfdInMin.setBounds(270, 392, 71, 24);
		add(tfdInMin);
		tfdInMin.setColumns(10);
		
		JLabel label_2 = new JLabel("进价范围：");
		label_2.setBounds(181, 395, 75, 18);
		add(label_2);
		
		JLabel lblNewLabel = new JLabel("售价范围：");
		lblNewLabel.setBounds(488, 395, 86, 18);
		add(lblNewLabel);
		
		tfdInMax = new JTextField();
		tfdInMax.setColumns(10);
		tfdInMax.setBounds(386, 392, 71, 24);
		add(tfdInMax);
		
		JLabel lblNewLabel_1 = new JLabel("——");
		lblNewLabel_1.setBounds(350, 398, 39, 18);
		add(lblNewLabel_1);
		
		JLabel label_3 = new JLabel("——");
		label_3.setBounds(654, 395, 39, 18);
		add(label_3);
		
		tfdSaleMin = new JTextField();
		tfdSaleMin.setColumns(10);
		tfdSaleMin.setBounds(560, 392, 80, 24);
		add(tfdSaleMin);
		
		tfdsaleMax = new JTextField();
		tfdsaleMax.setColumns(10);
		tfdsaleMax.setBounds(693, 392, 72, 24);
		add(tfdsaleMax);
		
		JLabel lblId_1 = new JLabel("ID：");
		lblId_1.setBounds(184, 348, 72, 18);
		add(lblId_1);
		
		tfdId = new JTextField();
		tfdId.setBounds(218, 345, 71, 24);
		add(tfdId);
		tfdId.setColumns(10);
		
	}
	
	public void listRefresh(){
		List<BookValueObject> BookList = ub.findAll();
		list.setListData(BookList.toArray());
	}
	
	private void QueryRefresh(){
		tfdId.setText("");
		tfdName.setText("");
		tfdNo.setText("");
		tfdInMax.setText("");
		tfdInMin.setText("");
		tfdsaleMax.setText("");
		tfdSaleMin.setText("");
	}
}

