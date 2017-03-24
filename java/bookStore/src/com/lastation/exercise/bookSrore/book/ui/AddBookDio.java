package com.lastation.exercise.bookSrore.book.ui;

import java.awt.BorderLayout;
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

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookEbiFactory;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;

public class AddBookDio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfdBookName;
	private BookEbi bedi = BookEbiFactory.getBookEbi();
	private JTextField tfdBookNo;
	private JTextField tfdInPrice;
	private JTextField tfdSalePrice;


	public AddBookDio(final BookManage nowJPanl, BookStroeMain mainJFrame) {
		setBounds(mainJFrame.getX()+150, mainJFrame.getY()+100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			contentPanel.setLayout(null);
			JLabel label = new JLabel("书名：");
			label.setBounds(30, 101, 60, 18);
			contentPanel.add(label);
			
			tfdBookName = new JTextField();
			tfdBookName.setBounds(93, 98, 109, 24);
			contentPanel.add(tfdBookName);
			tfdBookName.setColumns(10);
			
			JLabel label_1 = new JLabel("进价：");
			label_1.setBounds(30, 147, 60, 18);
			contentPanel.add(label_1);
			
			JLabel label_2 = new JLabel("售价：");
			label_2.setBounds(216, 147, 53, 18);
			contentPanel.add(label_2);
			
			JLabel label_3 = new JLabel("添加新书籍");
			label_3.setFont(new Font("微软雅黑", Font.BOLD, 24));
			label_3.setBounds(154, 13, 120, 33);
			contentPanel.add(label_3);
			
			JLabel label_4 = new JLabel("书号：");
			label_4.setBounds(216, 101, 45, 18);
			contentPanel.add(label_4);
			
			tfdBookNo = new JTextField();
			tfdBookNo.setBounds(263, 98, 120, 24);
			contentPanel.add(tfdBookNo);
			tfdBookNo.setColumns(10);
			
			tfdInPrice = new JTextField();
			tfdInPrice.setBounds(93, 144, 109, 24);
			contentPanel.add(tfdInPrice);
			tfdInPrice.setColumns(10);
			
			tfdSalePrice = new JTextField();
			tfdSalePrice.setBounds(263, 144, 120, 24);
			contentPanel.add(tfdSalePrice);
			tfdSalePrice.setColumns(10);
			{
				JButton okButton = new JButton("确认");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String bookName = tfdBookName.getText();
						String bookNo = tfdBookNo.getText();
						String inStr = tfdInPrice.getText();
						String saleStr = tfdSalePrice.getText();
						double inPrice = 0;
						double salePrice = 0;
						if (bookName.length() <= 0) {
							JOptionPane.showMessageDialog(null, "请输入书名");
							return;
						}
						
						if (bookNo.length() <= 0) {
							JOptionPane.showMessageDialog(null, "请输入正确的书号");
							return;
						}
						try {
							inPrice = Float.parseFloat(inStr);
						} catch (NumberFormatException er) {
							JOptionPane.showMessageDialog(null, "请输入正确的进价");
							return;
						}
						try {
							salePrice = Float.parseFloat(saleStr);
						} catch (NumberFormatException er) {
							JOptionPane.showMessageDialog(null, "请输入正确的售价");
							return;
						}
						BookValueObject book = new BookValueObject();
						book.setBookName(bookName);
						book.setBookNo(bookNo);
						book.setInPrice(inPrice);
						book.setSalePrice(salePrice);
						
						Boolean boo = bedi.create(book);
						
						if (boo) {
							JOptionPane.showMessageDialog(null, "添加成功");
							nowJPanl.listRefresh();
							tfdBookName.setText("");
							tfdBookNo.setText("");
							tfdInPrice.setText("");
							tfdSalePrice.setText("");
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
	}



}
