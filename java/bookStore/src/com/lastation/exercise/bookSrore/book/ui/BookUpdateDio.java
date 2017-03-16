package com.lastation.exercise.bookSrore.book.ui;

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

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookBusFactory;
import com.lastation.exercise.bookSrore.book.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;

public class BookUpdateDio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfdName;
	private JTextField tfdInPrice;
	private JTextField tfdNo;
	private JTextField tfdSalePrice;
	private BookEbi bebi = BookBusFactory.getBookEbi();


	public BookUpdateDio(final BookManage nowJPanl, BookStroeMain mainJFrame,
			final InMainValueObject uvo) {
		setBounds(mainJFrame.getX()+150, mainJFrame.getY()+100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("修改图书");
			label.setFont(new Font("微软雅黑", Font.BOLD, 24));
			label.setBounds(162, 13, 105, 29);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("书名：");
			label.setBounds(14, 90, 72, 18);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("书号：");
			label.setBounds(220, 90, 72, 18);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("进价：");
			label.setBounds(14, 139, 72, 18);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("售价：");
			label.setBounds(220, 139, 72, 18);
			contentPanel.add(label);
		}
		{
			tfdName = new JTextField();
			tfdName.setText(uvo.getBookName());
			tfdName.setBounds(61, 87, 145, 24);
			contentPanel.add(tfdName);
			tfdName.setColumns(10);
		}
		{
			tfdInPrice = new JTextField();
			tfdInPrice.setText(""+uvo.getInPrice());
			tfdInPrice.setBounds(61, 136, 145, 24);
			contentPanel.add(tfdInPrice);
			tfdInPrice.setColumns(10);
		}
		{
			tfdNo = new JTextField();
			tfdNo.setText(uvo.getBookNo());
			tfdNo.setBounds(267, 87, 153, 24);
			contentPanel.add(tfdNo);
			tfdNo.setColumns(10);
		}
		{
			tfdSalePrice = new JTextField();
			tfdSalePrice.setText(""+uvo.getSalePrice());
			tfdSalePrice.setBounds(267, 136, 153, 24);
			contentPanel.add(tfdSalePrice);
			tfdSalePrice.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("注意：每本书在后台有独立的隐藏ID，此处只提供修改数据");
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
			lblNewLabel.setBounds(14, 170, 420, 24);
			contentPanel.add(lblNewLabel);
		}
		{
			JLabel lblNewLabel_1 = new JLabel("功能，如果是要新增书籍请使用添加功能。");
			lblNewLabel_1.setForeground(Color.RED);
			lblNewLabel_1.setFont(new Font("微软雅黑", Font.BOLD, 15));
			lblNewLabel_1.setBounds(60, 193, 347, 18);
			contentPanel.add(lblNewLabel_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String name = tfdName.getText();
						String no = tfdNo.getText();
						String inStr = tfdInPrice.getText();
						String saleStr = tfdSalePrice.getText();
						Double in = 0.0;
						Double sale = 0.0;
						try {
							in = Double.valueOf(inStr);
						} catch (NumberFormatException er){
							JOptionPane.showMessageDialog(null, "请输入正确的进价");
						} 
						try {
							sale = Double.valueOf(saleStr);
						} catch (NumberFormatException er){
							JOptionPane.showMessageDialog(null, "请输入正确的进价");
						} 
						uvo.setBookName(name);
						uvo.setBookNo(no);
						uvo.setInPrice(in);
						uvo.setSalePrice(sale);
						Boolean boo = bebi.update(uvo);
						if (boo) {
							JOptionPane.showMessageDialog(null, "修改成功");
							nowJPanl.listRefresh();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "修改失败");
							dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("取消");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
