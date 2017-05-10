package gui;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class UserControl extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Important User ArrayList 
	 * 1. Place: Name of User 
	 * 2. admin oder user => state
	 */
	ArrayList<String> userList = new ArrayList<>();

	// ----------------------------------------------------- //

	/**
	 * Create the dialog.
	 */
	public UserControl() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setTitle("Benutzerkontrolle");
		setBounds(100, 100, 375, 282);
		getContentPane().setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 434, 271);
			getContentPane().add(tabbedPane);
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Anlegen", null, panel, null);
				panel.setLayout(null);

				JLabel lblBenutzername = new JLabel("Benutzername");
				lblBenutzername.setBounds(10, 54, 232, 14);
				panel.add(lblBenutzername);

				JLabel lblPasswort = new JLabel("Passwort");
				lblPasswort.setBounds(10, 99, 232, 14);
				panel.add(lblPasswort);

				JTextPane txtpnLegenSieHier = new JTextPane();
				txtpnLegenSieHier.setEditable(false);
				txtpnLegenSieHier.setText("Legen Sie hier einen neuen Benutzer / Admin an.");
				txtpnLegenSieHier.setBounds(10, 11, 346, 20);
				panel.add(txtpnLegenSieHier);

				textField = new JTextField();
				textField.setBounds(10, 68, 346, 20);
				panel.add(textField);
				textField.setColumns(10);

				textField_1 = new JTextField();
				textField_1.setBounds(10, 112, 346, 20);
				panel.add(textField_1);
				textField_1.setColumns(10);

				JButton btnNewButton = new JButton("Abbrechen");
				btnNewButton.setBounds(10, 199, 120, 23);
				panel.add(btnNewButton);
				btnNewButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						setVisible(false);
						dispose();
						Menu.userControl = null;
					}
				});

				JButton btnNewButton_1 = new JButton("Hinzuf\u00FCgen");
				btnNewButton_1.setBounds(236, 199, 120, 23);
				panel.add(btnNewButton_1);
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Verwaltung", null, panel, null);
				panel.setLayout(null);

				JTextPane txtpnVerwaltenSieHier = new JTextPane();
				txtpnVerwaltenSieHier.setEditable(false);
				txtpnVerwaltenSieHier.setBounds(10, 11, 348, 20);
				txtpnVerwaltenSieHier.setText("Verwalten Sie hier Ihre Benutzer-/Admineintr\u00E4ge.");
				panel.add(txtpnVerwaltenSieHier);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 46, 348, 143);
				panel.add(scrollPane);

				JList list = new JList();
				scrollPane.setViewportView(list);

				JButton btnNewButton_2 = new JButton("Umbenennen");
				btnNewButton_2.setBounds(10, 200, 110, 23);
				panel.add(btnNewButton_2);

				JButton btnNewButton_3 = new JButton("L\u00F6schen");
				btnNewButton_3.setBounds(248, 200, 110, 23);
				panel.add(btnNewButton_3);

				JButton btnNewButton_4 = new JButton("PW \u00E4ndern");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnNewButton_4.setBounds(130, 200, 108, 23);
				panel.add(btnNewButton_4);
			}
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
				dispose();
				Menu.userControl = null;
			}
		});
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
	}
}
