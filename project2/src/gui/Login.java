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

	public JProgressBar progressBar = new JProgressBar();

	/**
	 * Create the dialog.
	 */
	public Login() {
		setBounds(100, 100, 319, 228);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		progressBar.setBounds(10, 126, 293, 24);
		contentPanel.add(progressBar);

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
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 161, 313, 33);
			contentPanel.add(buttonPane);
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
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Login");
	}

	public void setProgress(int percent) {
		progressBar.setValue(percent);
	}
}
