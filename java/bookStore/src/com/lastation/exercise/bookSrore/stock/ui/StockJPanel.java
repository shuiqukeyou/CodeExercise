package com.lastation.exercise.bookSrore.stock.ui;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JList;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookBusFactory;
import com.lastation.exercise.bookSrore.stock.business.ebi.StockEbi;
import com.lastation.exercise.bookSrore.stock.business.factory.StockEBIFactory;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.ui.DefaultJPanel;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class StockJPanel extends DefaultJPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BookEbi be = BookBusFactory.getBookEbi();
	private StockEbi se = StockEBIFactory.getEbi();
	private JTextField rdfmin;
	private JTextField tfdmax;

	public StockJPanel(final BookStroeMain mainJFrame) {
		super(mainJFrame,"库存管理");
		
		JList list = new JList();
		list.setBackground(Color.LIGHT_GRAY);
		list.setBounds(300, 135, 370, 237);
		List<String> StrList = new ArrayList<>();
		List<StockValueObject> svoList = se.findAll();
		for (StockValueObject svo : svoList) {
			StrList.add("书名：" + be.findBook(svo.getBookUuid()).getBookName() + "  数量：" + svo.getSumMun());
		}
		list.setListData(StrList.toArray());
		add(list);
		
		JButton button = new JButton("查询");
		button.setFont(new Font("SimSun", Font.PLAIN, 12));
		button.setBounds(668, 416, 93, 35);
		add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(256, 423, 129, 21);
		comboBox.setModel(new DefaultComboBoxModel<>(
				new String[] {"—请选择—"}));
		for (StockValueObject svo:svoList) {
			comboBox.addItem(be.findBook(svo.getBookUuid()).getBookName());
		}
		add(comboBox);
		
		JLabel label = new JLabel("书名：");
		label.setBounds(198, 426, 48, 15);
		add(label);
		
		JLabel label_1 = new JLabel("数量：");
		label_1.setBounds(413, 426, 54, 15);
		add(label_1);
		
		rdfmin = new JTextField();
		rdfmin.setBounds(455, 423, 54, 21);
		add(rdfmin);
		rdfmin.setColumns(10);
		
		JLabel label_2 = new JLabel("到");
		label_2.setBounds(519, 426, 54, 15);
		add(label_2);
		
		tfdmax = new JTextField();
		tfdmax.setBounds(545, 423, 66, 21);
		add(tfdmax);
		tfdmax.setColumns(10);
		
	}
}
