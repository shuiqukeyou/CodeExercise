package com.lastation.exercise.bookSrore.login.ui;

import javax.swing.JPanel;

import com.lastation.exercise.bookSrore.ui.BookStroeMain;
import com.lastation.exercise.bookSrore.ui.DefaultJPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class WelcomeJPanel extends DefaultJPanel {

	/**
	 * Create the panel.
	 */
	public WelcomeJPanel(final BookStroeMain mainJFrame) {
		super(mainJFrame, "欢迎界面");
		
		JLabel label = new JLabel("我没啥想说的，所以没有");
		label.setFont(new Font("微软雅黑", Font.PLAIN, 16));
		label.setBounds(286, 238, 293, 35);
		add(label);
	}

}
