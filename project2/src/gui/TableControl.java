package gui;

import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class TableControl extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private NewDirectoryDialog newDirectoryDialog = null;
	private NewTableDialog newTableDialog = null;

	/**
	 * Create the dialog.
	 */
	public TableControl() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setTitle("Tabellenverwaltung - Tabelle: TESTNAME.TXT");
		setBounds(100, 100, 375, 311);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 354, 272);
			getContentPane().add(panel);
			panel.setLayout(null);

			JButton btnNewButton_1 = new JButton("Ok");
			btnNewButton_1.setBounds(150, 230, 194, 31);
			panel.add(btnNewButton_1);
			btnNewButton_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					setVisible(false);
					dispose();
					Menu.tableControl = null;
				}
			});

			JPanel panel_1 = new JPanel();
			panel_1.setBorder(
					new TitledBorder(null, "Auswahl umbenennen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_1.setBounds(150, 107, 194, 83);
			panel.add(panel_1);
			panel_1.setLayout(null);

			textField = new JTextField();
			textField.setBounds(10, 24, 174, 20);
			panel_1.add(textField);
			textField.setColumns(10);

			JButton btnNewButton = new JButton("Umbenennen");
			btnNewButton.setBounds(10, 49, 174, 23);
			panel_1.add(btnNewButton);

			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new TitledBorder(null, "Erstellen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel_2.setBounds(150, 11, 194, 91);
			panel.add(panel_2);
			panel_2.setLayout(null);

			JButton btnNeuerOrdner = new JButton("Neuer Ordner");
			btnNeuerOrdner.setBounds(10, 21, 174, 23);
			panel_2.add(btnNeuerOrdner);
			btnNeuerOrdner.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					if (newDirectoryDialog == null && newTableDialog == null) {
						setAlwaysOnTop(false);
						newDirectoryDialog = new NewDirectoryDialog();
					}
				}
			});

			JButton btnNeueTabelle = new JButton("Neue Tabelle");
			btnNeueTabelle.setBounds(10, 51, 174, 23);
			panel_2.add(btnNeueTabelle);
			btnNeueTabelle.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					if (newTableDialog == null && newDirectoryDialog == null) {
						setAlwaysOnTop(false);
						newTableDialog = new NewTableDialog();
					}
				}
			});

			JCheckBox chckbxTabelleVerstecken = new JCheckBox("Tabelle verstecken");
			chckbxTabelleVerstecken.setHorizontalAlignment(SwingConstants.CENTER);
			chckbxTabelleVerstecken.setBounds(160, 197, 174, 23);
			panel.add(chckbxTabelleVerstecken);

			JTree tree = new JTree();
			tree.setBounds(10, 11, 130, 250);
			panel.add(tree);
		}
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
				dispose();
				Menu.tableControl = null;
			}
		});
		setLocationRelativeTo(null);
		setResizable(false);
		setAlwaysOnTop(true);
	}

	/**
	 * Inner class to handle new Directory JDialog Stuff
	 *
	 */
	public class NewDirectoryDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField textField;

		public NewDirectoryDialog() {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			setTitle("Neuer Ordner");
			setBounds(100, 100, 300, 146);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);

			JLabel lblNameFrDen = new JLabel("Name f\u00FCr den neuen Ordner eingeben:");
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
					setVisible(false);
					dispose();
					newDirectoryDialog = null;
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
					newDirectoryDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					setVisible(false);
					dispose();
					newDirectoryDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});
			setLocationRelativeTo(null);
			setResizable(false);
			setAlwaysOnTop(true);
		}
	}

	/**
	 * 
	 */
	public class NewTableDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		private final JPanel contentPanel = new JPanel();
		private JTextField textField;

		public NewTableDialog() {
			this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			this.setVisible(true);
			setTitle("Neue Tabelle");
			setBounds(100, 100, 300, 300);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);

			JLabel lblNameFrDen = new JLabel("Name f\u00FCr die neue Tabelle eingeben:");
			lblNameFrDen.setBounds(10, 11, 264, 20);
			contentPanel.add(lblNameFrDen);

			textField = new JTextField();
			textField.setBounds(10, 31, 145, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
			
			JButton btnSenden = new JButton("Senden");
			btnSenden.setBounds(154, 237, 130, 23);
			contentPanel.add(btnSenden);
			btnSenden.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					setVisible(false);
					dispose();
					newTableDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			JButton btnNewButton = new JButton("Abbrechen");
			btnNewButton.setBounds(10, 237, 130, 23);
			contentPanel.add(btnNewButton);
			btnNewButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					setVisible(false);
					dispose();
					newTableDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			JLabel lblSpaltenEintragen = new JLabel("Spalten eintragen:");
			lblSpaltenEintragen.setBounds(10, 62, 274, 14);
			contentPanel.add(lblSpaltenEintragen);

			JTextArea textArea = new JTextArea();
			textArea.setEditable(true);
			textArea.setBounds(10, 78, 264, 148);
			contentPanel.add(textArea);
			
			JButton btnHinzufgen = new JButton("Hinzufuegen");
			btnHinzufgen.setBounds(165, 30, 119, 23);
			contentPanel.add(btnHinzufgen);
			
			addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosed(WindowEvent e) {
					setVisible(false);
					dispose();
					newTableDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			setLocationRelativeTo(null);
			setResizable(false);
			setAlwaysOnTop(true);
		}
	}
}
