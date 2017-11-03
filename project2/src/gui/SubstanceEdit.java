package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.Main;

import java.awt.Label;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.Window.Type;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class SubstanceEdit extends JFrame {

	private JPanel contentPane;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	private JTextField textField;
	
	private String substance;
	private String tablename;
	private JTextField textField_1;

	/**
	 * Create the frame.
	 */
	public SubstanceEdit(String substance, String tablename, ArrayList<String> substances) {
		setType(Type.UTILITY);
		this.substance = substance;
		this.tablename = tablename;
		setIconImage(Toolkit.getDefaultToolkit().getImage(SubstanceEdit.class.getResource("/bath.png")));
		setBounds((int)(screenSize.getWidth()/4)-150, (int)(screenSize.getHeight()/4)-100, 160, 362);
		setTitle(substance);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JRadioButton rdbtnStoffUmbenennen = new JRadioButton("Stoff Umbenennen");
		rdbtnStoffUmbenennen.setBounds(6, 7, 121, 23);
		contentPane.add(rdbtnStoffUmbenennen);
		
		textField = new JTextField();
		textField.setBounds(16, 31, 111, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JRadioButton rdbtnStoffVerbergen = new JRadioButton("Stoff Un-/ Sichtbar");
		rdbtnStoffVerbergen.setBounds(6, 71, 132, 23);
		contentPane.add(rdbtnStoffVerbergen);
		
		JRadioButton rdbtnStoffLschen = new JRadioButton("Stoff L\u00F6schen");
		rdbtnStoffLschen.setBounds(6, 97, 109, 23);
		contentPane.add(rdbtnStoffLschen);
		
		JRadioButton rdbtnVerschiebenNach = new JRadioButton("Verschieben hinter...");
		rdbtnVerschiebenNach.setBounds(6, 123, 132, 23);
		contentPane.add(rdbtnVerschiebenNach);
		
		JRadioButton rdbtnNeuenStoffEinfgen = new JRadioButton("<html>Neuen Stoff <br> einf\u00FCgen hinter: </html>");
		rdbtnNeuenStoffEinfgen.setBounds(6, 187, 121, 37);
		contentPane.add(rdbtnNeuenStoffEinfgen);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(16, 147, 111, 20);
		comboBox.addItem("Am Anfang");
		for(int i = 0; i < substances.size(); i++){
			comboBox.addItem(substances.get(i));
		}
		contentPane.add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(16, 250, 111, 20);
		contentPane.add(textField_1);
		
		//radiobutton actionlistener
		
		rdbtnStoffUmbenennen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnStoffUmbenennen.isSelected()){
					rdbtnStoffVerbergen.setSelected(false);
					rdbtnStoffLschen.setSelected(false);
					rdbtnVerschiebenNach.setSelected(false);
					rdbtnNeuenStoffEinfgen.setSelected(false);
				}
			}
		});
		
		rdbtnStoffVerbergen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnStoffVerbergen.isSelected()){
					rdbtnStoffUmbenennen.setSelected(false);
					rdbtnStoffLschen.setSelected(false);
					rdbtnVerschiebenNach.setSelected(false);
					rdbtnNeuenStoffEinfgen.setSelected(false);
				}
			}
		});
		
		rdbtnStoffLschen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnStoffLschen.isSelected()){
					rdbtnStoffUmbenennen.setSelected(false);
					rdbtnStoffVerbergen.setSelected(false);
					rdbtnVerschiebenNach.setSelected(false);
					rdbtnNeuenStoffEinfgen.setSelected(false);
				}
			}
		});
		
		rdbtnVerschiebenNach.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnVerschiebenNach.isSelected()){
					rdbtnStoffUmbenennen.setSelected(false);
					rdbtnStoffVerbergen.setSelected(false);
					rdbtnStoffLschen.setSelected(false);
					rdbtnNeuenStoffEinfgen.setSelected(false);
				}
			}
		});
		
		rdbtnNeuenStoffEinfgen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(rdbtnNeuenStoffEinfgen.isSelected()){
					rdbtnStoffUmbenennen.setSelected(false);
					rdbtnStoffVerbergen.setSelected(false);
					rdbtnStoffLschen.setSelected(false);
					rdbtnVerschiebenNach.setSelected(false);
				}
			}
		});
		
		Button button = new Button("Best\u00E4tigen");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(rdbtnStoffUmbenennen.isSelected()){
					if(!textField.getText().contains(" ")){
						Main.ct.transmit("!substancerename " + tablename + " " + substance + " " + textField.getText());
					}
				}
				
				if(rdbtnStoffVerbergen.isSelected()){
					Main.ct.transmit("!substancehide " + tablename + " " + substance);
				}
				
				if(rdbtnStoffLschen.isSelected()){
					//TODO popup for substance delete confirmation!
					Main.ct.transmit("!substancedelete " + tablename + " " + substance);
				}
				
				if(rdbtnVerschiebenNach.isSelected()){
					//TODO change order and fill combobox with stoffarray
					String moveAfter;
					if(comboBox.getSelectedItem().toString().equals("Am Anfang")){
						moveAfter = "Name";
					}else{
						moveAfter = comboBox.getSelectedItem().toString();
					}
					
					Main.ct.transmit("!substancemove " + tablename + " " + substance + " " + moveAfter);
				}
				
				if(rdbtnNeuenStoffEinfgen.isSelected()){
					//TODO add new stoff after clicked one (localvar: substance)
					
					//check if new stoff name is legit (no spaces etc)
					Main.ct.transmit("!substanceadd " + tablename + " " + textField_1.getText() + " " + substance);
				}
				
				dispose();
				
			}
		});
		button.setBounds(35, 296, 70, 22);
		contentPane.add(button);
		
		
		Label label = new Label(substance);
		label.setBounds(35, 222, 62, 22);
		contentPane.add(label);
		
		textField_1.setColumns(10);
		this.setVisible(true);
	}
}
