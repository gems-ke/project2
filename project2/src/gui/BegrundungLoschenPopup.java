package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BegrundungLoschenPopup extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BegrundungLoschenPopup(String message) {
		setTitle("Begr\u00FCndung f\u00FCr L\u00F6schung");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 293, 225);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBegrndung = new JLabel("Begr\u00FCndung:");
		lblBegrndung.setBounds(10, 11, 89, 14);
		contentPane.add(lblBegrndung);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(10, 36, 254, 107);
		contentPane.add(textArea);
		
		JButton btnAbsenden = new JButton("Absenden");
		btnAbsenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textArea.getText().equals("")){
					Popup popupEmpty = new Popup("Bitte Begründung eingeben!");
				}else{
					if(!textArea.getText().contains("&")){
						Main.ct.transmit(message + "&" + textArea.getText() +"&"+Main.it.currentUser);
						dispose();
					}else{
						Popup popupAnd = new Popup("Das zeichen '&' ist nicht erlaubt!'");
					}
				}
			}
		});
		btnAbsenden.setBounds(166, 154, 98, 23);
		contentPane.add(btnAbsenden);
		
		JButton btnAbbrechen = new JButton("Abbrechen");
		btnAbbrechen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnAbbrechen.setBounds(10, 154, 103, 23);
		contentPane.add(btnAbbrechen);
		setVisible(true);
	}

}
