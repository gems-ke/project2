package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

public class Popup extends JFrame implements Runnable{

	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public Popup(String message) {
		setBounds((int)(screenSize.getWidth()/2)-150, (int)(screenSize.getHeight()/2)-100, 333, 195);
		setAlwaysOnTop(true);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnOkay.setBounds(114, 122, 89, 23);
		contentPane.add(btnOkay);
		
		JLabel lblError = new JLabel(message);
		lblError.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setBounds(10, 11, 297, 69);
		contentPane.add(lblError);
		setVisible(true);
	}

	@Override
	public void run() {
		
	}
}
