package com.lastation.exercise.bookSrore.stock.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookEbiFactory;
import com.lastation.exercise.bookSrore.stock.business.ebi.StockEbi;
import com.lastation.exercise.bookSrore.stock.business.factory.StockEbiFactory;
import com.lastation.exercise.bookSrore.stock.vo.StockQueryValueObject;
import com.lastation.exercise.bookSrore.stock.vo.StockValueObject;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.ui.DefaultJPanel;

public class StockJPanel extends DefaultJPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final BookEbi be = BookEbiFactory.getBookEbi();
	private StockEbi se = StockEbiFactory.getStockEbi();
	private JTextField tfdmin;
	private JTextField tfdmax;

	public StockJPanel(final BookStroeMain mainJFrame) {
		super(mainJFrame,"库存管理");
		
		JList list = new JList();
		list.setBackground(Color.LIGHT_GRAY);
		list.setBounds(300, 135, 370, 237);
		List<String> StrList = new ArrayList<>();
		final List<StockValueObject> svoList = se.findAll();
		for (StockValueObject svo : svoList) {
			StrList.add("书名：" + be.findBook(svo.getBookUuid()).getBookName() + "  数量：" + svo.getSumMun());
		}
		list.setListData(StrList.toArray());
		add(list);
		
		
		final JComboBox comboBox = new JComboBox();
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
		
		tfdmin = new JTextField();
		tfdmin.setBounds(455, 423, 54, 21);
		add(tfdmin);
		tfdmin.setColumns(10);
		
		JLabel label_2 = new JLabel("到");
		label_2.setBounds(519, 426, 54, 15);
		add(label_2);
		
		tfdmax = new JTextField();
		tfdmax.setBounds(545, 423, 66, 21);
		add(tfdmax);
		tfdmax.setColumns(10);
		
		JButton button = new JButton("查询");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer selectIndex = comboBox.getSelectedIndex();
				Integer bookUuid = 0;
				if (selectIndex > 1 ){
					bookUuid = svoList.get(selectIndex-1).getBookUuid();
				}
				
				String num1Str = tfdmin.getText();
				Integer num1 = 0;
				String num2Str = tfdmax.getText();
				Integer num2 = Integer.MAX_VALUE;
				if (num1Str.trim().length() > 0){
					try {
						num1 = Integer.decode(num1Str);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正常的最低数值");
						return;
					}
				}
				if (num2Str.trim().length() > 0){
					try {
						num2 = Integer.decode(num1Str);
					} catch (NumberFormatException er) {
						JOptionPane.showMessageDialog(null, "请输入正常的最低数值");
						return;
					}
				}
				StockQueryValueObject sqvo = new StockQueryValueObject();
				System.out.println(bookUuid);
				sqvo.setBookUuid(bookUuid);
				sqvo.setSumMun1(num1);
				sqvo.setSumMun2(num2);
				List<StockValueObject> result = se.findByQueryVO(sqvo);
				QueryDio(result,mainJFrame);
			}
		});
		button.setFont(new Font("SimSun", Font.PLAIN, 12));
		button.setBounds(668, 416, 93, 35);
		add(button);
		
	}
	
	private void QueryDio(List<StockValueObject> list, JFrame mainJFrame){
		JDialog stockQuery = new StockQuery(list,mainJFrame);
		stockQuery.setModal(true);
		stockQuery.setVisible(true);
	}
}
