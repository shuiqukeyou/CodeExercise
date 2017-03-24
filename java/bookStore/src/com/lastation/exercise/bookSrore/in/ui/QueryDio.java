package com.lastation.exercise.bookSrore.in.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.in.business.ebi.InEbi;
import com.lastation.exercise.bookSrore.in.business.factory.InEbiFactory;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;
import com.lastation.exercise.bookSrore.tool.DateUitl;

public class QueryDio extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final BookEbi be = BookEbiFactory.getBookEbi();
	private final InEbi ie = InEbiFactory.getInEbi();

	public QueryDio(List<InDetailValueObject> list, JFrame mainJFrame) {
		setTitle("查询结果");
		setBounds(mainJFrame.getX()+50, mainJFrame.getX()+50, 600, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNewLabel = new JLabel("查询结果");
			lblNewLabel.setFont(new Font("微软雅黑", Font.BOLD, 24));
			lblNewLabel.setBounds(256, 13, 115, 33);
			contentPanel.add(lblNewLabel);
		}
		List<String> listStr = new ArrayList<>();
		
		for (InDetailValueObject idvo:list) {
			listStr.add("时间:"+ DateUitl.long2String(ie.findMInByInUuid(idvo.getInUuid()).getInDate()) +" 书名：" + be.findBook(idvo.getBookUuid()).getBookName() + " 本数：" + idvo.getNum() + " 总价：" + idvo.getSumMoney());
		}
		
		JList listData = new JList();
		listData.setListData(listStr.toArray());
		listData.setBounds(14, 60, 556, 151);
		contentPanel.add(listData);
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
