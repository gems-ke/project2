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
import java.util.ArrayList;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Button;
import java.awt.List;
import javax.swing.JComboBox;
import com.jgoodies.forms.factories.DefaultComponentFactory;

import data.BegrundData;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BegrundungsControl extends JFrame {
	
	private NewBegrundungDialog newBegrundungDialog = null;

	private JPanel contentPane;
	
	public ArrayList<BegrundData> begrundDataList = new ArrayList<BegrundData>();
	
	private JComboBox comboBoxTableSelect;
	private List listActive;
	private List listInactive;

	/**
	 * Create the frame.
	 */
	public void BegrundungsControlCreate() {
		setTitle("Begr\u00FCndungskontrolle");
		setResizable(false);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 701, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setVisible(true);
		this.setAlwaysOnTop(true);
		
		JLabel lblAktiv = new JLabel("Aktiv");
		lblAktiv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAktiv.setBounds(113, 11, 30, 14);
		contentPane.add(lblAktiv);
		
		JLabel lblInaktiv = new JLabel("Inaktiv");
		lblInaktiv.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblInaktiv.setBounds(537, 11, 41, 14);
		contentPane.add(lblInaktiv);
		
		Button buttonAdd = new Button("+");
		buttonAdd.setFont(new Font("Dialog", Font.PLAIN, 15));
		buttonAdd.setBounds(318, 223, 49, 47);
		buttonAdd.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (newBegrundungDialog == null && newBegrundungDialog == null) {
					setAlwaysOnTop(false);
					newBegrundungDialog = new NewBegrundungDialog();
				}
			}
		});
		contentPane.add(buttonAdd);
		
		Button buttonActivate = new Button("<<");
		buttonActivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("listInactive.getSelectedItem(): " + listInactive.getSelectedItem());
				if(listInactive.getSelectedItem()!=null){
					Main.ct.transmit("!changeBegrundung^" + comboBoxTableSelect.getSelectedItem().toString() + "^" + listInactive.getSelectedItem().toString() + "-");
					listActive.add(listInactive.getSelectedItem().toString());
					listInactive.remove(listInactive.getSelectedIndex());
				}
				
			}
		});
		buttonActivate.setFont(new Font("Dialog", Font.PLAIN, 15));
		buttonActivate.setBounds(318, 276, 49, 47);
		contentPane.add(buttonActivate);
		
		Button buttonDeactivate = new Button(">>");
		buttonDeactivate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("listActive.getSelectedItem(): " + listActive.getSelectedItem());
				if(listActive.getSelectedItem()!=null){
					Main.ct.transmit("!changeBegrundung^" + comboBoxTableSelect.getSelectedItem().toString() + "^" + listActive.getSelectedItem().toString() + "+");
					listInactive.add(listActive.getSelectedItem().toString());
					listActive.remove(listActive.getSelectedIndex());
				}
				
			}
		});
		buttonDeactivate.setFont(new Font("Dialog", Font.PLAIN, 15));
		buttonDeactivate.setBounds(318, 170, 49, 47);
		contentPane.add(buttonDeactivate);
		
		listActive = new List();
		listActive.setBounds(10, 32, 235, 420);
		//listActive.add("Tageszugabe2");
		contentPane.add(listActive);
		
		listInactive = new List();
		listInactive.setBounds(440, 31, 235, 420);
		contentPane.add(listInactive);
		
		comboBoxTableSelect = new JComboBox();
		comboBoxTableSelect.setBounds(261, 88, 163, 20);
		comboBoxTableSelect.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    
                    	updateBegrundungsData(comboBoxTableSelect.getSelectedItem().toString());
                    	
                    }

                }            
        );
		contentPane.add(comboBoxTableSelect);
		
		JLabel lblTabelle = new JLabel("Tabelle");
		lblTabelle.setBounds(325, 65, 34, 14);
		contentPane.add(lblTabelle);
		
		fillBegrundungsData(Main.it.mainmenu.currentBegrundungsString);
		
	}
	
	public void fillBegrundungsData(String message){
		
		begrundDataList.removeAll(begrundDataList);
		
		
		BegrundData begrunddata;
		
		String[] rawMessageData = message.split("&");
		String[] refinedMessageData;
		String tablename;
		ArrayList<String> activeData;
		ArrayList<String> inactiveData;
		
		for(int i = 1; i < rawMessageData.length; i++){
			
			activeData = new ArrayList<String>();
			inactiveData = new ArrayList<String>();
			
			refinedMessageData = rawMessageData[i].split("=");
			tablename = refinedMessageData[0];
			
			for(int c = 1; c < refinedMessageData.length; c++){
				
				if(refinedMessageData[c].substring(refinedMessageData[c].length()-1).equals("+")){
					activeData.add(refinedMessageData[c].substring(0, refinedMessageData[c].length()-1));
				}else{
					inactiveData.add(refinedMessageData[c].substring(0, refinedMessageData[c].length()-1));
				}
				
			}
			
			begrunddata = new BegrundData(tablename, activeData, inactiveData);
			begrundDataList.add(begrunddata);
			
			
		}
		
		comboBoxTableSelect.removeAllItems();
		for(int i = 0; i < begrundDataList.size(); i++){
			comboBoxTableSelect.addItem(begrundDataList.get(i).getTableName());
		}
		
	}
	
public void fillBegrundungsDataExternal(String message){
		
		begrundDataList.removeAll(begrundDataList);
		
		BegrundData begrunddata;
		
		String[] rawMessageData = message.split("&");
		String[] refinedMessageData;
		String tablename;
		ArrayList<String> activeData;
		ArrayList<String> inactiveData;
		
		for(int i = 1; i < rawMessageData.length; i++){
			
			activeData = new ArrayList<String>();
			inactiveData = new ArrayList<String>();
			
			refinedMessageData = rawMessageData[i].split("=");
			tablename = refinedMessageData[0];
			
			for(int c = 1; c < refinedMessageData.length; c++){
				
				if(refinedMessageData[c].substring(refinedMessageData[c].length()-1).equals("+")){
					System.out.println("extracting to activedata: " + refinedMessageData[c].substring(0, refinedMessageData[c].length()-1));
					activeData.add(refinedMessageData[c].substring(0, refinedMessageData[c].length()-1));
				}else{
					System.out.println("extracting to inactivedata: " + refinedMessageData[c].substring(0, refinedMessageData[c].length()-1));
					inactiveData.add(refinedMessageData[c].substring(0, refinedMessageData[c].length()-1));
				}
				
			}
			
			begrunddata = new BegrundData(tablename, activeData, inactiveData);
			begrundDataList.add(begrunddata);
			
			
		}
		
		
	}
	
	public void updateBegrundungsData(String tablename){
		
		for(int i = 0; i < begrundDataList.size(); i++){
			if(begrundDataList.get(i).getTableName().equals(tablename)){
				
				listActive.removeAll();
				listInactive.removeAll();
				
				for(int c = 0; c < begrundDataList.get(i).getActives().size(); c++){
					System.out.println("adding to begrundactivelist: " + begrundDataList.get(i).getActives().get(c));
					listActive.add(begrundDataList.get(i).getActives().get(c));
				}
				
				for(int c = 0; c < begrundDataList.get(i).getInactives().size(); c++){
					System.out.println("adding to begrundinactivelist: " + begrundDataList.get(i).getInactives().get(c));
					listInactive.add(begrundDataList.get(i).getInactives().get(c));
				}
				
			}
		}
		
		contentPane.updateUI();
		
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
					if(textField.getText().contains("+") || textField.getText().contains("-")){
						//popup window handling!
					}else{
						listActive.add(textField.getText());
						Main.ct.transmit("!createBegrundung^" + comboBoxTableSelect.getSelectedItem().toString() + "^" + textField.getText());
						System.out.println("begrundungs create string sent!");
					}
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
