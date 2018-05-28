package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import main.Main;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class MultisendWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	
	//date format day/hour

	/**
	 * Create the frame.
	 * @param ArrayList of Strings - tablenames
 	 * @param ArrayList of Strings - Stoffe in Array
 	 * @param ArrayList of Strings - Begrundungen
 	 * 
	 * current selected table?
	 * 
	 * @return Creates the Multisend window
	 */
	public MultisendWindow(String tablename, ArrayList<String> stoffe, ArrayList<String> begrundungen) {
		setTitle("Eintrag senden als: " + Main.it.currentUser);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1180, 234);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(10, 21, 145, 20);
		comboBox.addItem(tablename);
		contentPane.add(comboBox);
		
		JLabel lblTabelle = new JLabel("Tabelle:");
		lblTabelle.setBounds(10, 5, 46, 14);
		contentPane.add(lblTabelle);
		
		JLabel lblDatum = new JLabel("Datum:");
		lblDatum.setBounds(10, 52, 46, 14);
		contentPane.add(lblDatum);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.RIGHT);
		textField.setBounds(10, 70, 20, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel label = new JLabel(".");
		label.setBounds(33, 76, 8, 14);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_1.setColumns(10);
		textField_1.setBounds(40, 70, 20, 20);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel(".");
		label_1.setBounds(63, 76, 8, 14);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_2.setColumns(10);
		textField_2.setBounds(70, 70, 34, 20);
		contentPane.add(textField_2);
		
		JLabel lblUhrzeit = new JLabel("Uhrzeit:");
		lblUhrzeit.setBounds(10, 101, 46, 14);
		contentPane.add(lblUhrzeit);
		
		textField_3 = new JTextField();
		textField_3.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_3.setColumns(10);
		textField_3.setBounds(10, 117, 20, 20);
		contentPane.add(textField_3);
		
		JLabel label_2 = new JLabel(":");
		label_2.setBounds(33, 123, 8, 14);
		contentPane.add(label_2);
		
		textField_4 = new JTextField();
		textField_4.setHorizontalAlignment(SwingConstants.RIGHT);
		textField_4.setColumns(10);
		textField_4.setBounds(40, 117, 20, 20);
		contentPane.add(textField_4);
		
		JLabel lblBegrndung = new JLabel("Begr\u00FCndung:");
		lblBegrndung.setBounds(10, 148, 77, 14);
		contentPane.add(lblBegrndung);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBounds(10, 165, 145, 20);
		contentPane.add(comboBox_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(172, 17, 982, 168);
		contentPane.add(scrollPane);
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("lol");
		model.addColumn("lel");
		
		String[] data = new String[2];
		data[0] = "haha";
		data[1] = "hehe";
		
		model.addRow(data);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		/*TableModel dataModel = new MyTableModel();
		table.setModel(dataModel);*/
	}
}