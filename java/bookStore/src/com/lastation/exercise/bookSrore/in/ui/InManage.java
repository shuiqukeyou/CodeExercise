package com.lastation.exercise.bookSrore.in.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.print.attribute.HashAttributeSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookBusFactory;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.in.business.ebi.InEbi;
import com.lastation.exercise.bookSrore.in.business.factory.InEbiFactory;
import com.lastation.exercise.bookSrore.in.dao.dao.InDetailDAO;
import com.lastation.exercise.bookSrore.in.vo.InDetailQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.ui.DefaultJPanel;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserBusinessFactory;
import com.lastation.exercise.bookSrore.user.vo.UserQueryValueObject;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JComboBox;

import java.awt.Font;

public class InManage extends DefaultJPanel {
	private InEbi ib = InEbiFactory.getEbi();
	private JTextField tfdy1;
	private JList inMainList;
	private JList inDetailList;
	private UserEbi ue = UserBusinessFactory.getUserBusinessImpl();
	private BookEbi be = BookBusFactory.getBookEbi();
	private JTextField tfdm1;
	private JTextField tfdd1;
	private JTextField tfdd2;
	private JTextField tfdm2;
	private JTextField tfdy2;
	private JTextField tfdnum1;
	private JTextField tfdnum2;
	private JTextField tfdmoney1;
	private JTextField tfdmoney2;
	private List<InDetailValueObject> inDetailDataList;
	private List<InMainValueObject> inMainDateList = ib.findInAll();
	private List<BookValueObject> BookList = be.findAll();
	private List<UserValueObject> InUser = getInUser(inMainDateList);
	
	/**
	 * Create the panel.
	 */
	public InManage(final BookStroeMain mainJFrame) {
		super(mainJFrame, "进货管理");
		setBounds(mainJFrame.getX(), mainJFrame.getY(), 800, 600);
		final List<InMainValueObject> inMainDateList = ib.findInAll();
		inMainList = new JList<>();
		inMainList.setBackground(SystemColor.control);
		inMainList.setBounds(169, 119, 380, 160);
		inMainList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer inUuid = inMainDateList.get(inMainList.getSelectedIndex()).getUuid();
				dListRefresh(inUuid);
			}
		});
		final InManage nowJPanl =this;
		mListRefresh();
		add(inMainList);
		JButton btnAdd = new JButton("进货");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog addUserDio = new InAddDio(nowJPanl,mainJFrame);
				addUserDio.setModal(true);
				addUserDio.setVisible(true);
			}
		});
		btnAdd.setBounds(587, 117, 165, 50);
		add(btnAdd);
		
		JLabel lblId = new JLabel("进货日期:");
		lblId.setBounds(565, 279, 72, 18);
		add(lblId);
		
		tfdy1 = new JTextField();
		tfdy1.setBounds(565, 307, 41, 24);
		add(tfdy1);
		tfdy1.setColumns(10);
		
		JLabel label = new JLabel("进货人：");
		label.setBounds(565, 378, 72, 18);
		add(label);
		
		inDetailList = new JList<Object>();
		inDetailList.setBackground(SystemColor.control);
		inDetailList.setBounds(169, 335, 352, 207);
		add(inDetailList);
		
		JLabel label_1 = new JLabel("进货单");
		label_1.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_1.setBounds(327, 77, 67, 23);
		add(label_1);
		
		JLabel label_2 = new JLabel("进货明细");
		label_2.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label_2.setBounds(316, 307, 78, 23);
		add(label_2);
		
		JLabel label_3 = new JLabel("年");
		label_3.setBounds(610, 310, 30, 18);
		add(label_3);
		
		tfdm1 = new JTextField();
		tfdm1.setBounds(632, 307, 30, 24);
		add(tfdm1);
		tfdm1.setColumns(10);
		
		tfdd1 = new JTextField();
		tfdd1.setBounds(696, 307, 30, 24);
		add(tfdd1);
		tfdd1.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("月");
		lblNewLabel.setBounds(670, 310, 30, 18);
		add(lblNewLabel);
		
		tfdd2 = new JTextField();
		tfdd2.setColumns(10);
		tfdd2.setBounds(722, 344, 30, 24);
		add(tfdd2);
		
		JLabel label_4 = new JLabel("月");
		label_4.setBounds(696, 347, 30, 18);
		add(label_4);
		
		tfdm2 = new JTextField();
		tfdm2.setColumns(10);
		tfdm2.setBounds(658, 344, 30, 24);
		add(tfdm2);
		
		JLabel label_5 = new JLabel("年");
		label_5.setBounds(636, 347, 30, 18);
		add(label_5);
		
		tfdy2 = new JTextField();
		tfdy2.setColumns(10);
		tfdy2.setBounds(591, 344, 41, 24);
		add(tfdy2);
		
		JLabel lblNewLabel_1 = new JLabel("日");
		lblNewLabel_1.setBounds(733, 310, 41, 18);
		add(lblNewLabel_1);
		
		JLabel label_6 = new JLabel("日");
		label_6.setBounds(759, 349, 41, 18);
		add(label_6);
		
		JLabel label_7 = new JLabel("到");
		label_7.setFont(new Font("宋体", Font.BOLD, 15));
		label_7.setBounds(565, 345, 29, 20);
		add(label_7);
		
		JLabel lblNewLabel_2 = new JLabel("书　名：");
		lblNewLabel_2.setBounds(565, 413, 72, 18);
		add(lblNewLabel_2);
		
		final JComboBox cbbuser = new JComboBox();
		cbbuser.setBounds(632, 375, 139, 24);
		cbbuser.setModel(new DefaultComboBoxModel<>(
				new String[] {"—请选择—"}));
		for(UserValueObject user:InUser){
			cbbuser.addItem(user.getUserName());
		}
		add(cbbuser);
		
		final JComboBox cbbbook = new JComboBox();
		cbbbook.setBounds(632, 409, 139, 24);
		cbbbook.setModel(new DefaultComboBoxModel<>(
				new String[] {"—请选择—"}));
		for(BookValueObject book:BookList){
			cbbbook.addItem(book.getBookName());
		}
		add(cbbbook);
		
		tfdnum1 = new JTextField();
		tfdnum1.setBounds(614, 444, 50, 24);
		add(tfdnum1);
		tfdnum1.setColumns(10);
		
		JLabel label_8 = new JLabel("本数：");
		label_8.setBounds(565, 447, 72, 18);
		add(label_8);
		
		tfdnum2 = new JTextField();
		tfdnum2.setColumns(10);
		tfdnum2.setBounds(702, 444, 50, 24);
		add(tfdnum2);
		
		JLabel lblNewLabel_3 = new JLabel("——");
		lblNewLabel_3.setBounds(670, 448, 36, 18);
		add(lblNewLabel_3);
		
		JLabel label_9 = new JLabel("总价：");
		label_9.setBounds(565, 481, 50, 18);
		add(label_9);
		
		tfdmoney1 = new JTextField();
		tfdmoney1.setColumns(10);
		tfdmoney1.setBounds(614, 475, 50, 24);
		add(tfdmoney1);
		
		JLabel label_10 = new JLabel("——");
		label_10.setBounds(670, 479, 36, 18);
		add(label_10);
		
		tfdmoney2 = new JTextField();
		tfdmoney2.setColumns(10);
		tfdmoney2.setBounds(702, 475, 50, 24);
		add(tfdmoney2);
		
		JButton btnQuery = new JButton("查找记录");
		btnQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<InDetailValueObject> result = new ArrayList<>();
				String y1s = tfdy1.getText();
				Integer y1 = 0;
				String y2s = tfdy2.getText();
				Integer y2 = Integer.MAX_VALUE;
				String m1s = tfdm1.getText();
				Integer m1 = 1;
				String m2s = tfdm2.getText();
				Integer m2 = 12;
				String d1s = tfdd1.getText();
				Integer d1 = 1;
				String d2s = tfdd2.getText();
				Integer d2 = 31;
				UserValueObject user = null;
				Integer bookUuid = 0;
				if (cbbuser.getSelectedIndex() > 0){
					user = InUser.get(cbbuser.getSelectedIndex()-1);
				}
				if (cbbbook.getSelectedIndex() > 0) {
					bookUuid = BookList.get(cbbbook.getSelectedIndex()-1).getUuid();
				}
				String num1s = tfdnum1.getText();
				Integer num1 = 0;
				String num2s = tfdnum2.getText();
				Integer num2 = Integer.MAX_VALUE;
				String money1s = tfdmoney1.getText();
				Double money1 = 0.0;
				String money2s = tfdmoney2.getText();
				Double money2 = Double.MAX_VALUE;
				
				try {
					if (y1s.trim().length() > 0) {
						y1 = Integer.valueOf(y1s);
					}
					if (y2s.trim().length() > 0) {
						y2 = Integer.valueOf(y2s);
					}
					if (m1s.trim().length() > 0) {
						m1 = Integer.valueOf(m1s);
					}
					if (m2s.trim().length() > 0) {
						m2 = Integer.valueOf(m2s);
					}
					if (d1s.trim().length() > 0) {
						d1 = Integer.valueOf(d1s);
					}
					if (d2s.trim().length() > 0) {
						d2 = Integer.valueOf(d2s);
					}
					if (num1s.trim().length() > 0) {
						num1 = Integer.valueOf(num1s);
					}
					if (num2s.trim().length() > 0) {
						num2 = Integer.valueOf(num2s);
					}
					if (money1s.trim().length() > 0) {
						money1 = Double.valueOf(money1s);
					}
					if (money2s.trim().length() > 0) {
						money2 = Double.valueOf(money2s);
					}
				} catch (NumberFormatException er) {
					JOptionPane.showMessageDialog(null, "搜索条件格式错误");
					return;
				}
				Calendar date1 = Calendar.getInstance(); 
				Calendar date2 = Calendar.getInstance(); 
				try {
					date1.set(y1, m1, d1);
					date2.set(y2, m2, d2);
				} catch (ArrayIndexOutOfBoundsException er) {
					JOptionPane.showMessageDialog(null, "日期格式错误");
					return;
				}
				// 初始化主表查询
				InMainQueryValueObject imqvo = new InMainQueryValueObject();
				imqvo.setInDateMax(date2.getTimeInMillis());
				imqvo.setInDateMin(date1.getTimeInMillis());
				if (user != null) {
					imqvo.setInUserUuid(user.getUuid());
				} else {
					imqvo.setInUserUuid(0);
				}
				
				// 初始化子表查询
				InDetailQueryValueObject idqvo = new InDetailQueryValueObject();
				idqvo.setNumMin(num1);
				idqvo.setNumMax(num2);
				idqvo.setSumMoneyMin(money1);
				idqvo.setSumMoneyMax(money2);
				idqvo.setBookUuid(bookUuid);
				
				List<InMainValueObject> reList = ib.findInMByQuery(imqvo);
				if (reList.size() <= 0) {
					QueryDio(result,mainJFrame);
					return;
				}
				for (InMainValueObject imvp : reList) {
					idqvo.setInUuid(imvp.getUuid());
					List<InDetailValueObject> tempList = ib.findInEByQuery(idqvo);
					if (tempList.size() > 0) {
						for (InDetailValueObject idvo :tempList) {
							result.add(idvo);
						}
					}
				}
				QueryDio(result,mainJFrame);
			}
		});
		btnQuery.setBounds(681, 507, 93, 34);
		add(btnQuery);
		
	}
	
	public void mListRefresh(){
		inMainDateList = ib.findInAll();
		List<String> inMainStrList = new ArrayList<>();
		for (InMainValueObject imvo:inMainDateList) {
			UserValueObject user = ue.findUser(imvo.getInUserUuid()); // 根据ID获取到用户
			Calendar date = Calendar.getInstance();
			date.setTimeInMillis(imvo.getInDate());
			inMainStrList.add("进货时间；" + printDate(date) + " 进货人：" + user.getUserName());

		}
		inMainList.setListData(inMainStrList.toArray());
	}
	
	public void dListRefresh(int inUuid){
		inDetailDataList = ib.findDInByInUuid(inUuid);
		List<String> inDetailStrList = new ArrayList<>();
		for (InDetailValueObject idvo:inDetailDataList) {
			BookValueObject book = be.findBook(idvo.getBookUuid());// 获取图书对象
			inDetailStrList.add("书名；" + book.getBookName() + " 数量：" + idvo.getNum() + " 总价：" + idvo.getSumMoney());

		}
		inDetailList.setListData(inDetailStrList.toArray());
	}
	
	private String printDate(Calendar date){
		String hour = String.format("%02d", date.get(Calendar.HOUR_OF_DAY)); 
		String min = String.format("%02d", date.get(Calendar.MINUTE)); 
		String sec = String.format("%02d", date.get(Calendar.SECOND)); 
		
		return date.get(Calendar.YEAR)+ "年" + date.get(Calendar.MONTH) + "月" + 
				date.get(Calendar.DAY_OF_MONTH) + "日" + hour +
				":" + min + ":" + sec;
	}
	
	private List<UserValueObject> getInUser(List<InMainValueObject> mainList){
		Set<UserValueObject> inUserId = new HashSet<>();
		List<UserValueObject> result = new ArrayList<>();
		for (InMainValueObject imvo:mainList) {
			inUserId.add(ue.findUser(imvo.getInUserUuid()));
		}
		for (UserValueObject imvo:inUserId) {
			result.add(imvo);
		}
		return result;
	}
	
	private void QueryDio(List<InDetailValueObject> list, JFrame mainJFrame){
		JDialog addUserDio = new QueryDio(list,mainJFrame);
		addUserDio.setModal(true);
		addUserDio.setVisible(true);
	}
}


