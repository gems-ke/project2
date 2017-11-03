package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import data.TableData;
import main.Main;

import javax.swing.border.BevelBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class RowDeleteQueueWindow extends JFrame {

	private JPanel contentPane;
	TableData deletedata;
	ArrayList<TableData> tables;
	
	JTextArea textArea;
	JTextArea textArea_1;

	/**
	 * Create the frame.
	 * @param tables 
	 * @param tables 
	 */
	public RowDeleteQueueWindow(TableData deletedata1, ArrayList<TableData> tables) {
		this.tables = tables;
		this.deletedata = deletedata1;
		setTitle("Anfragen verbleibend: " + deletedata.getLineCount());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(400, 300, 448, 285);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEintragswerte = new JLabel("Eintragswerte:");
		lblEintragswerte.setBounds(222, 11, 85, 14);
		contentPane.add(lblEintragswerte);
		
		JButton btnLschen = new JButton("L\u00F6schen");
		btnLschen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateTextArea(true, false);
				
			}
		});
		btnLschen.setBounds(10, 227, 89, 23);
		contentPane.add(btnLschen);
		
		JButton btnBehalten = new JButton("Behalten");
		btnBehalten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				updateTextArea(false, false);
				
			}
		});
		btnBehalten.setBounds(123, 227, 89, 23);
		contentPane.add(btnBehalten);
		
		JLabel lblBegrndung = new JLabel("Begr\u00FCndung: ");
		lblBegrndung.setBounds(10, 11, 89, 14);
		contentPane.add(lblBegrndung);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(232, 36, 190, 214);
		contentPane.add(scrollPane);
		
		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 36, 202, 180);
		contentPane.add(panel);
		panel.setLayout(null);
		
		textArea_1 = new JTextArea();
		textArea_1.setEditable(false);
		textArea_1.setBounds(0, 0, 202, 180);
		panel.add(textArea_1);
		
		setVisible(true);
		setResizable(false);
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				setVisible(false);
				dispose();
				Main.it.mainmenu.rdqw = null;
			}
		});
		
		updateTextArea(false, true);
	}
	
	private void updateTextArea(boolean delete, boolean start){
		
		if(!start){
			//serverantwort abhängig besser
			
			if(!start && deletedata.getLineCount() != 0){
				deletedata.deleteLine(0);
				if(delete){
					Popup poptrue = new Popup("Eintrag wurde gelöscht.");
				}else{
					Popup popfalse = new Popup("Eintrag wird nicht gelöscht.");
				}
			}
			
			//Main.ct.transmit("!deleteline");
		}
		
		textArea.setText("");
		textArea_1.setText("");
		
		if(deletedata.getLineCount() == 0){
			
			textArea_1.setText("Keine Anfragen verbleibend.");
			
		}else{
			
			int id = Integer.parseInt(deletedata.getData(1, "identification"));
			String tablename = deletedata.getData(1, "tablename");
			
			textArea_1.append(deletedata.getData(1, "begrundung"));
			
			textArea.append("Tabelle: " + tablename);
			textArea.append("\n");
			textArea.append("Anfrage von: " + deletedata.getData(1, "requestinguser"));
			textArea.append("\n");
			for(int i = 0; i < tables.size(); i++){
				if(tablename.equals(tables.get(i).getTableName())){
					
					boolean hasFound = false;
					for(int c = 1; c < tables.get(i).getLineCount()+1; c++){
						
						if(tables.get(i).getData(c, "ID").equals("" + id)){
							hasFound = true;
							String[] columns = tables.get(i).getColumnArray();
							
							for(int x = 0; x < columns.length; x++){
								
								textArea.append(columns[x] + ": " + tables.get(i).getData(c, columns[x]));
								textArea.append("\n");
								
							}
							
						}
					}
					
				}
			}
			
		}
		
		setTitle("Anfragen verbleibend: " + deletedata.getLineCount());
		
	}
}
