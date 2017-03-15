package com.lastation.exercise.bookSrore.ui;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.lastation.exercise.bookSrore.tool.DefaultJPanel;

public class MainJPanel extends DefaultJPanel {
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame mainJFrame;

	/**
	 * Create the panel.
	 */
	
	public MainJPanel(final BookStroeMain mainJFrame) {
		super(mainJFrame, "图书管理系统");
	}

	
}
