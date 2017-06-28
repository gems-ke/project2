package gui;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import gui.TableControl.NewDirectoryDialog;
import main.Main;

import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class UserControl extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JCheckBox chckbxAdmin;
	
	private NewRenameDialog newRenameDialog = null;

	/**
	 * Important User ArrayList 1. Place: Name of User 2. admin oder user =>
	 * state
	 */
	ArrayList<String> userList = new ArrayList<>();
	
	static JList list = new JList();

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

				JLabel txtpnLegenSieHier = new JLabel();
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
				btnNewButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(textField.getText().equals("")){
							Popup popup = new Popup("Bitte Name eintragen!");
						}else{
							if(textField_1.getText().equals("")){
								Popup popup = new Popup("Bitte Passwort eintragen!");
							}else{
								if(chckbxAdmin.isSelected()){
									Main.ct.transmit("!createUser " + textField.getText() + " " + textField_1.getText() + " Admin");
								}else{
									Main.ct.transmit("!createUser " + textField.getText() + " " + textField_1.getText() + " Benutzer");
								}
							}
						}
					}
				});
				panel.add(btnNewButton_1);

				chckbxAdmin = new JCheckBox("Admin");
				chckbxAdmin.setBounds(6, 139, 97, 23);
				panel.add(chckbxAdmin);
			}
			{
				JPanel panel = new JPanel();
				tabbedPane.addTab("Verwaltung", null, panel, null);
				panel.setLayout(null);

				JLabel txtpnVerwaltenSieHier = new JLabel();
				txtpnVerwaltenSieHier.setBounds(10, 11, 348, 20);
				txtpnVerwaltenSieHier.setText("Verwalten Sie hier Ihre Benutzer-/Admineintr\u00E4ge.");
				panel.add(txtpnVerwaltenSieHier);

				JScrollPane scrollPane = new JScrollPane();
				scrollPane.setBounds(10, 46, 348, 143);
				panel.add(scrollPane);

				list = new JList();
				updateUserList();
				scrollPane.setViewportView(list);

				JButton btnNewButton_2 = new JButton("Umbenennen");
				btnNewButton_2.setBounds(10, 200, 110, 23);
				btnNewButton_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						System.out.println("selection: " + list.getSelectedValue());
						
						if (newRenameDialog == null && list.getSelectedValue() != null) {
							setAlwaysOnTop(false);
							newRenameDialog = new NewRenameDialog((String)list.getSelectedValue(), "username");
						}
						
						if(list.getSelectedValue() == null){
							Popup pop = new Popup("Es wurde kein Benutzer ausgewählt.");
						}
						
					}
				});
				panel.add(btnNewButton_2);

				JButton btnNewButton_3 = new JButton("L\u00F6schen");
				btnNewButton_3.setBounds(248, 200, 110, 23);
				btnNewButton_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						Main.ct.transmit("!deleteUser " + (String)list.getSelectedValue());
						Popup pop = new Popup("Benutzer gelöscht.");
						Main.ct.transmit("!requestUserExistingUpdate");
					}
				});
				panel.add(btnNewButton_3);

				JButton btnNewButton_4 = new JButton("PW \u00E4ndern");
				btnNewButton_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						System.out.println("selection: " + list.getSelectedValue());
						
						if (newRenameDialog == null && list.getSelectedValue() != null) {
							setAlwaysOnTop(false);
							newRenameDialog = new NewRenameDialog((String)list.getSelectedValue(), "passwort");
						}
						
						if(list.getSelectedValue() == null){
							Popup pop = new Popup("Es wurde kein Benutzer ausgewählt.");
						}
						
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
	
	public static void updateUserList(){
		
		DefaultListModel dlm = new DefaultListModel();
		
		list.removeAll();
		
		System.out.println("Existing Users Size: " + Menu.existingUsers.size());
		
		for(int i = 0; i < Menu.existingUsers.size(); i++){
			dlm.addElement(Menu.existingUsers.get(i));
		}
		
		list.setModel(dlm);
		list.updateUI();
		
	}
	
	public class NewRenameDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField textField;
		private String changeParameterName = "";
		private String mode = "";
		private JLabel lblNameFrDen;

		public NewRenameDialog(String selectedName, String toChange) {
			changeParameterName = selectedName;
			mode = toChange;
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			setTitle("Neuer Ordner");
			setBounds(100, 100, 300, 146);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);

			if(mode.equals("username")){
				lblNameFrDen = new JLabel("Neuer Name f\u00FCr den Benutzer eingeben:");
			}else if(mode.equals("passwort")){
				lblNameFrDen = new JLabel("Neues Passwort f\u00FCr den Benutzer eingeben:");
			}

			lblNameFrDen.setBounds(10, 11, 264, 20);
			contentPanel.add(lblNameFrDen);

			textField = new JTextField();
			textField.setBounds(10, 36, 264, 20);
			contentPanel.add(textField);
			textField.setColumns(10);

			JButton btnSenden = new JButton("Senden");
			btnSenden.setBounds(147, 73, 127, 23);
			contentPanel.add(btnSenden);
			btnSenden.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					Main.ct.transmit("!changeUserData*" + mode + "*" + changeParameterName + "*" + textField.getText());
					setVisible(false);
					dispose();
					newRenameDialog = null;
					Main.ct.transmit("!requestUserExistingUpdate");
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			JButton btnAbbrechen = new JButton("Abbrechen");
			btnAbbrechen.setBounds(10, 73, 127, 23);
			contentPanel.add(btnAbbrechen);
			btnAbbrechen.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					setVisible(false);
					dispose();
					newRenameDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					setVisible(false);
					dispose();
					newRenameDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});
			setLocationRelativeTo(null);
			setResizable(false);
			setAlwaysOnTop(true);
		}
	}
}
