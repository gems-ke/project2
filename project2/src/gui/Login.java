package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JCheckBox;

public class Login extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	public static JPasswordField passwordField;
	public static JTextField textField;
	public static JButton okButton = new JButton("Login");
	public static JCheckBox chckbxAdmin;

	public JProgressBar progressBar = new JProgressBar();

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 319, 256);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		progressBar.setBounds(10, 149, 293, 24);
		contentPanel.add(progressBar);

		JLabel infoLabel = new JLabel("");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(10, 115, 293, 24);
		contentPanel.add(infoLabel);

		JLabel lblBenutzername = new JLabel("Benutzername");
		lblBenutzername.setBounds(10, 17, 293, 20);
		contentPanel.add(lblBenutzername);

		JLabel lblPasswort = new JLabel("Passwort");
		lblPasswort.setBounds(10, 67, 293, 20);
		contentPanel.add(lblPasswort);

		passwordField = new JPasswordField();
		passwordField.setBounds(10, 84, 293, 20);
		contentPanel.add(passwordField);

		textField = new JTextField();
		textField.setBounds(10, 36, 293, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		chckbxAdmin = new JCheckBox("Admin");
		chckbxAdmin.setBounds(10, 119, 97, 23);
		contentPanel.add(chckbxAdmin);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Login");
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{

				okButton.setActionCommand("Ok");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				// old on click listener

				/*
				 * loginp.start();
				 * 
				 * if(loginp.getLoginStatus() == 1){ new
				 * MainMenu().setVisible(true); dispose(); }else
				 * if(loginp.getLoginStatus() == 0){
				 * 
				 * }
				 * 
				 * }
				 */

			}
		}
	}

	public void setProgress(int percent) {
		progressBar.setValue(percent);
	}
}
