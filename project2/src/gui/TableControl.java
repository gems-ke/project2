package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.JTextPane;
import javax.swing.JCheckBox;
import java.awt.Color;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.SwingConstants;

public class TableControl extends JDialog {
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			TableControl dialog = new TableControl();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public TableControl() {
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setTitle("Tabellenverwaltung");
		setBounds(100, 100, 375, 311);
		getContentPane().setLayout(null);
		{
			JPanel panel = new JPanel();
			panel.setBounds(0, 0, 354, 272);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JTree tree = new JTree();
			tree.setBounds(10, 11, 130, 250);
			panel.add(tree);
			
			JButton btnNewButton_1 = new JButton("Ok");
			btnNewButton_1.setBounds(150, 230, 194, 31);
			panel.add(btnNewButton_1);
			
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new TitledBorder(null, "Auswahl umbenennen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
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
			
			JButton btnNeueTabelle = new JButton("Neue Tabelle");
			btnNeueTabelle.setBounds(10, 51, 174, 23);
			panel_2.add(btnNeueTabelle);
			
			JCheckBox chckbxTabelleVerstecken = new JCheckBox("Tabelle verstecken");
			chckbxTabelleVerstecken.setHorizontalAlignment(SwingConstants.CENTER);
			chckbxTabelleVerstecken.setBounds(160, 197, 174, 23);
			panel.add(chckbxTabelleVerstecken);
		}
	}
}
