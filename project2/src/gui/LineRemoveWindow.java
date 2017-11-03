package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import data.TableData;
import main.Main;

import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.TextArea;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class LineRemoveWindow extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private int lastConfirmedID = -1;
	private String lastConfirmedTablename = "";

	/**
	 * Create the frame.
	 * @param tables 
	 * @param isAdmin 
	 * @param selectedID 
	 */
	public LineRemoveWindow(ArrayList<TableData> tables, String selectedTable, boolean isAdmin, int selectedID) {
		setResizable(false);
		setTitle("Zeile entfernen");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTabelle = new JLabel("Tabelle:");
		lblTabelle.setBounds(10, 11, 46, 14);
		contentPane.add(lblTabelle);
		
		JLabel lblKleinsteId = new JLabel("Kleinste ID:");
		lblKleinsteId.setBounds(10, 60, 67, 14);
		contentPane.add(lblKleinsteId);
		
		JLabel lblGrteId = new JLabel("Gr\u00F6\u00DFte ID:");
		lblGrteId.setBounds(10, 84, 67, 14);
		contentPane.add(lblGrteId);
		
		JLabel lblidmin = new JLabel("ID min");
		lblidmin.setBounds(87, 60, 51, 14);
		contentPane.add(lblidmin);
		
		JLabel lblidmax = new JLabel("ID max");
		lblidmax.setBounds(87, 84, 51, 14);
		contentPane.add(lblidmax);
		
		JLabel lblId = new JLabel("ID:");
		lblId.setBounds(10, 130, 23, 14);
		contentPane.add(lblId);
		
		textField = new JTextField();
		textField.setBounds(27, 127, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		if(selectedID != -1){
			textField.setText(""+selectedID);
		}
		
		JCheckBox chckbxBesttigen = new JCheckBox("ID best\u00E4tigen");
		chckbxBesttigen.setBounds(10, 203, 128, 23);
		contentPane.add(chckbxBesttigen);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(148, 11, 276, 240);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		textArea.setBackground(SystemColor.menu);
		textArea.setEditable(false);
		textArea.setLineWrap(true);
		scrollPane.setViewportView(textArea);
		
		JComboBox comboBox = new JComboBox();
		comboBox.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				for(int i = 0; i < tables.size(); i++){
					if(comboBox.getSelectedItem().toString().equals(tables.get(i).getTableName())){
						lblidmin.setText(tables.get(i).getData(1, "ID"));
						lblidmax.setText(tables.get(i).getData(tables.get(i).getLineCount(), "ID"));
					}
				}
				
			}
		});
		comboBox.setBounds(10, 29, 128, 20);
		
		for(int i = 0; i < tables.size(); i++){
			comboBox.addItem(tables.get(i).getTableName());
		}
		
		comboBox.setSelectedItem(selectedTable);
		
		contentPane.add(comboBox);
		
		JButton btnInhaltAnzeigen = new JButton("Inhalt anzeigen");
		btnInhaltAnzeigen.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				boolean isLegitID = true;
				textArea.setText("");
				int ID = 0;
				
				try{
					ID = Integer.parseInt(textField.getText());
				}catch(NumberFormatException ex){
					if(textField.getText().equals("")){
						Popup popEmptyID = new Popup("Bitte ID eingeben!");
					}else{
						Popup popEmptyID = new Popup("Invalide ID!");
					}
					System.out.println("ERROR!");
					isLegitID = false;
					ex.printStackTrace();
				}
				
				if(isLegitID){
					
					for(int i = 0; i < tables.size(); i++){
						if(comboBox.getSelectedItem().toString().equals(tables.get(i).getTableName())){
							
							boolean hasFound = false;
							for(int c = 1; c < tables.get(i).getLineCount()+1; c++){
								
								if(tables.get(i).getData(c, "ID").equals("" + ID)){
									hasFound = true;
									lastConfirmedID = c;
									lastConfirmedTablename = tables.get(i).getTableName();
									String[] columns = tables.get(i).getColumnArray();
									
									for(int x = 0; x < columns.length; x++){
										
										textArea.append(columns[x] + ": " + tables.get(i).getData(c, columns[x]));
										textArea.append("\n");
										
									}
									
								}
							}
							
							if(!hasFound){
								Popup popEmptyID = new Popup("Eine Zeile mit dieser ID existiert nicht!");
							}
							
						}
					}
					
				}
				
			}
		});
		btnInhaltAnzeigen.setBounds(10, 155, 128, 23);
		contentPane.add(btnInhaltAnzeigen);
		
		JButton btnLschen;
		
		if(isAdmin){
			btnLschen = new JButton("L\u00F6schen");
		}else{
			btnLschen = new JButton("Anfrage senden");
		}
		
		btnLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(lastConfirmedID == -1){
					Popup popEmptyID = new Popup("Bitte eine valide ID erst anzeigen lassen!");
				}else{
					if(chckbxBesttigen.isSelected()){
						String message = "";
						if(isAdmin){
							//split with &
							message += "!deleteLine&"+lastConfirmedTablename+"&"+lastConfirmedID;
							Main.ct.transmit(message);
						}else{
							message += "!requestDeleteLine&"+lastConfirmedTablename+"&"+lastConfirmedID;
							BegrundungLoschenPopup blp = new BegrundungLoschenPopup(message);
							dispose();
						}
					}else{
						Popup popEmptyID = new Popup("Bitte bestätigen sie diese ID!");
					}
				}
				
			}
		});
		btnLschen.setBounds(10, 228, 128, 23);
		contentPane.add(btnLschen);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
				dispose();
				Menu.lineRemoveWindow = null;
			}
		});
		
		setVisible(true);
	}
}
