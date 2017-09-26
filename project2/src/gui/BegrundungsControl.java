package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import gui.TableControl.NewDirectoryDialog;
import gui.TableControl.NewTableDialog;
import main.Main;

import java.awt.ScrollPane;
import java.awt.TextArea;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Button;

public class BegrundungsControl extends JFrame {
	
	private NewBegrundungDialog newBegrundungDialog = null;

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public BegrundungsControl() {
		setTitle("Begr\u00FCndungskontrolle");
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		
		TextArea textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setBounds(10, 31, 267, 421);
		contentPane.add(textArea);
		
		TextArea textArea_1 = new TextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(408, 31, 267, 421);
		contentPane.add(textArea_1);
		
		JLabel lblNewLabel = new JLabel("Aktiv");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(126, 11, 30, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblInaktiv = new JLabel("Inaktiv");
		lblInaktiv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInaktiv.setBounds(522, 11, 41, 14);
		contentPane.add(lblInaktiv);
		
		Button button = new Button(">>");
		button.setFont(new Font("Dialog", Font.PLAIN, 15));
		button.setBounds(310, 117, 49, 47);
		contentPane.add(button);
		
		Button button_1 = new Button("+");
		button_1.setFont(new Font("Dialog", Font.PLAIN, 15));
		button_1.setBounds(310, 170, 49, 47);
		button_1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (newBegrundungDialog == null && newBegrundungDialog == null) {
					setAlwaysOnTop(false);
					newBegrundungDialog = new NewBegrundungDialog();
				}
			}
		});
		contentPane.add(button_1);
		
		Button button_2 = new Button("<<");
		button_2.setFont(new Font("Dialog", Font.PLAIN, 15));
		button_2.setBounds(310, 223, 49, 47);
		contentPane.add(button_2);
	}
	
	public class NewBegrundungDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField textField;

		public NewBegrundungDialog() {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			setTitle("Neuer Ordner");
			setBounds(100, 100, 300, 146);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);

			JLabel lblNameFrDen = new JLabel("Begründung hinzufügen:");
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
					System.out.println("begründung creation with name triggered: " + textField.getText());
					//if - und + nicht enthalten
					setVisible(false);
					dispose();
					newBegrundungDialog = null;
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
					newBegrundungDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					setVisible(false);
					dispose();
					newBegrundungDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});
			setLocationRelativeTo(null);
			setResizable(false);
			setAlwaysOnTop(true);
		}
	}
}
