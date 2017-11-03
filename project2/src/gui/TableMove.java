package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class TableMove extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public TableMove(String tablename, ArrayList<String> tablelist) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 133, 164);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(tablename);
		lblNewLabel.setBounds(10, 11, 97, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblVerschiebenHinter = new JLabel("Verschieben nach:");
		lblVerschiebenHinter.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblVerschiebenHinter.setBounds(10, 36, 93, 14);
		contentPane.add(lblVerschiebenHinter);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addItem("START");
		
		for(int i = 0; i < tablelist.size(); i++){
			comboBox.addItem(tablelist.get(i));
		}
		
		comboBox.setBounds(10, 61, 93, 20);
		contentPane.add(comboBox);
		
		JButton btnSenden = new JButton("Senden");
		btnSenden.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.ct.transmit("!tableMove " + tablename + " " + tablename.split("#")[0] + "#" + comboBox.getSelectedItem().toString().toLowerCase());
			}
		});
		btnSenden.setBounds(10, 92, 89, 23);
		contentPane.add(btnSenden);
		
		setVisible(true);
	}
}
