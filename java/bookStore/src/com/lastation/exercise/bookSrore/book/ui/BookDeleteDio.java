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
import com.lastation.exercise.bookSrore.book.business.factory.BookEbiFactory;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;

public class BookDeleteDio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField tfdName;
	private JTextField tfdInPrice;
	private JTextField tfdNo;
	private JTextField tfdSalePrice;
	private BookEbi bebi = BookEbiFactory.getBookEbi();


	public BookDeleteDio(final BookManage nowJPanl, BookStroeMain mainJFrame,
			final BookValueObject uvo) {
		setBounds(mainJFrame.getX()+150, mainJFrame.getY()+100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("删除图书");
			label.setFont(new Font("微软雅黑", Font.BOLD, 24));
			label.setBounds(162, 13, 105, 29);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("书名：");
			label.setBounds(14, 70, 72, 18);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("书号：");
			label.setBounds(220, 70, 72, 18);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("进价：");
			label.setBounds(14, 119, 72, 18);
			contentPanel.add(label);
		}
		{
			JLabel label = new JLabel("售价：");
			label.setBounds(220, 119, 72, 18);
			contentPanel.add(label);
		}
		{
			tfdName = new JTextField();
			tfdName.setEditable(false);
			tfdName.setText(uvo.getBookName());
			tfdName.setBounds(61, 67, 145, 24);
			contentPanel.add(tfdName);
			tfdName.setColumns(10);
		}
		{
			tfdInPrice = new JTextField();
			tfdInPrice.setEditable(false);
			tfdInPrice.setText(""+uvo.getInPrice());
			tfdInPrice.setBounds(61, 116, 145, 24);
			contentPanel.add(tfdInPrice);
			tfdInPrice.setColumns(10);
		}
		{
			tfdNo = new JTextField();
			tfdNo.setEditable(false);
			tfdNo.setText(uvo.getBookNo());
			tfdNo.setBounds(267, 67, 153, 24);
			contentPanel.add(tfdNo);
			tfdNo.setColumns(10);
		}
		{
			tfdSalePrice = new JTextField();
			tfdSalePrice.setEditable(false);
			tfdSalePrice.setText(""+uvo.getSalePrice());
			tfdSalePrice.setBounds(267, 116, 153, 24);
			contentPanel.add(tfdSalePrice);
			tfdSalePrice.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("注意：一旦删除将无法恢复");
			lblNewLabel.setForeground(Color.RED);
			lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
			lblNewLabel.setBounds(220, 187, 186, 24);
			contentPanel.add(lblNewLabel);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("确认");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Boolean boo = bebi.delete(uvo.getUuid());
						if (boo) {
							JOptionPane.showMessageDialog(null, "删除成功");
							nowJPanl.listRefresh();
							dispose();
						} else {
							JOptionPane.showMessageDialog(null, "删除失败");
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
