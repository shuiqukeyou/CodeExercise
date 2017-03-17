package com.lastation.exercise.bookSrore.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class BookStroeMain extends JFrame {
	public static JPanel nowJPanel = null;
	private int userId;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					BookStroeMain frame = new BookStroeMain();
					frame.setSize(800, 600);
					frame.setLocation(300, 200);
					frame.setVisible(true);
					nowJPanel = new MainJPanel(frame);
					frame.setContentPane(nowJPanel);
					frame.validate();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookStroeMain() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		getContentPane().setLayout(null);
	}
	
	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
}
