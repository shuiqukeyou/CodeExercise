package com.lastation.exercise.bookSrore.stock.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookEbiFactory;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;

public class StockQuery extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final BookEbi be = BookEbiFactory.getBookEbi();

	public StockQuery(List<StockValueObject> list, JFrame mainJFrame) {
		setBounds(mainJFrame.getX()+50, mainJFrame.getX()+50, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("查询结果");
		lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
		lblNewLabel.setBounds(226, 10, 115, 33);
		contentPanel.add(lblNewLabel);
		List<String> StrList = new ArrayList<>();
		for (StockValueObject svo : list) {
			StrList.add("书名：" + be.findBook(svo.getBookUuid()).getBookName() + "  数量：" + svo.getSumMun());
		}
		JList list_1 = new JList();
		list_1.setListData(StrList.toArray());
		list_1.setBounds(49, 65, 490, 118);
		contentPanel.add(list_1);
		
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
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
