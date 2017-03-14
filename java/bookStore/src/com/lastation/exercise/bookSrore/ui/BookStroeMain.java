package com.lastation.exercise.bookSrore.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BookStroeMain extends JFrame {
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookStroeMain frame = new BookStroeMain();
					frame.setSize(800, 600);
					frame.setLocation(300, 200);
					frame.setVisible(true);
					JPanel panel = new MainJPanel(frame);
					frame.setContentPane(panel);
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
}
