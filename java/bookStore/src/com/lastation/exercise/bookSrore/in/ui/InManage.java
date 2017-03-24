package com.lastation.exercise.bookSrore.in.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.lastation.exercise.bookSrore.book.business.ebi.BookEbi;
import com.lastation.exercise.bookSrore.book.business.factory.BookEbiFactory;
import com.lastation.exercise.bookSrore.book.vo.BookValueObject;
import com.lastation.exercise.bookSrore.in.business.ebi.InEbi;
import com.lastation.exercise.bookSrore.in.business.factory.InEbiFactory;
import com.lastation.exercise.bookSrore.in.vo.InDetailQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InDetailValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainQueryValueObject;
import com.lastation.exercise.bookSrore.in.vo.InMainValueObject;
import com.lastation.exercise.bookSrore.tool.DateUitl;
import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.ui.DefaultJPanel;
import com.lastation.exercise.bookSrore.user.business.ebi.UserEbi;
import com.lastation.exercise.bookSrore.user.business.factory.UserEbiFactory;
import com.lastation.exercise.bookSrore.user.vo.UserValueObject;

public class InManage extends DefaultJPanel {
	private InEbi ib = InEbiFactory.getInEbi();
	private JTextField tfdy1;
	private JList inMainList;
	private JList inDetailList;
	private UserEbi ue = UserEbiFactory.getUserEbi();
	private BookEbi be = BookEbiFactory.getBookEbi();
	private JTextField tfdm1;
	private JTextField tfdd1;
	private JTextField tfdd2;
	private JTextField tfdm2;
	private JTextField tfdy2;
	private JTextField tfdnum1;
	private JTextField tfdnum2;
	private JTextField tfdmoney1;
	private JTextField tfdmoney2;
	private Map<InMainValueObject, List<InDetailValueObject>> inData;
	private Set<InMainValueObject> inMainDateList;
	private List<UserValueObject> InUser;
	private List<BookValueObject> BookList = be.findAll();
	
	/**
	 * Create the panel.
	 */
	public InManage(final BookStroeMain mainJFrame) {
		super(mainJFrame, "进货管理");
		ListRefresh();
		setBounds(mainJFrame.getX(), mainJFrame.getY(), 800, 600);
		inMainList = new JList<>();
		inMainList.setBackground(SystemColor.control);
		inMainList.setBounds(169, 119, 380, 160);
		inMainList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				Integer inUuid = ((InMainValueObject)inMainList.getSelectedValue()).getUuid();
				dListRefresh(inUuid);
			}
		});
		mListRefresh();
		add(inMainList);
		JButton btnAdd = new JButton("进货");
		btnAdd.setForeground(Color.BLACK);
		btnAdd.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog addUserDio = new InAddDio(InManage.this,mainJFrame);
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
				String y1 = "0";
				if (tfdy1.getText().trim().length() > 0) {
					y1 = tfdy1.getText();
				}
				String y2 = ""+Integer.MAX_VALUE;
				if (tfdy2.getText().trim().length() > 0) {
					y2 = tfdy2.getText();
				}
				String m1 = "1";
				if (tfdm1.getText().trim().length() > 0) {
					m1 = tfdm1.getText();
				}
				String m2 = "12";
				if (tfdm2.getText().trim().length() > 0) {
					m2 = tfdm2.getText();
				}
				String d1 = "1";
				if (tfdd1.getText().trim().length() > 0) {
					d1 = tfdd1.getText();
				}
				String d2 = "31";
				if (tfdd2.getText().trim().length() > 0) {
					d2 = tfdd2.getText();
				}
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
				
				// 初始化主表查询
				InMainQueryValueObject imqvo = new InMainQueryValueObject();
				try {
					imqvo.setInDateMax(DateUitl.string2Long(y1+","+m1+","+d1+","+"0,0,0"));
					imqvo.setInDateMin(DateUitl.string2Long(y2+","+m2+","+d2+","+"23,59,59"));
				} catch (ParseException e1) {
					JOptionPane.showMessageDialog(null, "请输入正确的搜索日期条件");
					return;
				}
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
		ListRefresh();
		inMainList.setListData(inMainDateList.toArray());
	}
	private void ListRefresh(){
		inData = ib.findAll();
		inMainDateList = inData.keySet();
		InUser = getInUser(inMainDateList);
	}
	public void dListRefresh(int inUuid){
		List<InDetailValueObject> inDetailDataList = ib.findDInByInUuid(inUuid);
		List<String> inDetailStrList = new ArrayList<>();
		for (InDetailValueObject idvo:inDetailDataList) {
			BookValueObject book = be.findBook(idvo.getBookUuid());// 获取图书对象
			inDetailStrList.add("书名；" + book.getBookName() + " 数量：" + idvo.getNum() + " 总价：" + idvo.getSumMoney());
		}
		inDetailList.setListData(inDetailStrList.toArray());
	}
	
	//刷新查询部分的进货人列表，遍历InMain，只有至少进货一次的用户才会被添加进来
	private List<UserValueObject> getInUser(Set<InMainValueObject> mainList){
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


