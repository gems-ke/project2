package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Window;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;

import main.Main;

import javax.swing.SwingConstants;
import javax.swing.JTextArea;

public class TableControl extends JDialog {
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private NewDirectoryDialog newDirectoryDialog = null;
	private NewTableDialog newTableDialog = null;
	private JPanel panel = new JPanel();
	private JTree tree = null;
	private JScrollPane scroll = null;
	DefaultMutableTreeNode top;

	/**
	 * Create the dialog.
	 * @param substanceshidden 
	 */
	public TableControl(DefaultMutableTreeNode top, ArrayList<String> halls, ArrayList<String> baths, ArrayList<String> substances, ArrayList<String> bathshidden, ArrayList<String> substanceshidden) {
		
		this.top = top;
		tree = new JTree(this.top);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		setTitle("Tabellenverwaltung - Tabelle: TESTNAME.TXT");
		setBounds(100, 100, 375, 311);
		getContentPane().setLayout(null);
		{
			panel.setBounds(0, 0, 354, 272);
			getContentPane().add(panel);
			panel.setLayout(null);

			JButton btnNewButton_1 = new JButton("Ok");
			btnNewButton_1.setBounds(150, 230, 194, 31);

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

			JCheckBox chckbxTabelleVerstecken = new JCheckBox("verstecken/ anzeigen");
			chckbxTabelleVerstecken.setHorizontalAlignment(SwingConstants.CENTER);
			chckbxTabelleVerstecken.setBounds(160, 197, 174, 23);
			panel.add(chckbxTabelleVerstecken);
			
			//tree.setBounds(10, 11, 130, 250);

	        scroll = new JScrollPane(this.tree);
	        scroll.setBounds(10, 11, 130, 250);
			panel.add(scroll);
			
			btnNewButton_1.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					if(chckbxTabelleVerstecken.isSelected()){
						
						if(tree.getSelectionPath().getPathCount() == 3){
							Main.ct.transmit("!tablehide " + tree.getSelectionPath().getPathComponent(1).toString() + "#" + tree.getSelectionPath().getLastPathComponent().toString().toLowerCase());
						}
						
					}
					
					setVisible(false);
					dispose();
					Menu.tableControl = null;
				}
			});
			panel.add(btnNewButton_1);
			
	        this.tree.setCellRenderer(new DefaultTreeCellRenderer() {
	            private ImageIcon baseIcon = new ImageIcon("resources/base.jpg");
	            private ImageIcon hallIcon = new ImageIcon("resources/hall.png");
	            private ImageIcon tableIcon = new ImageIcon("resources/table.png");
	            private ImageIcon tubeIcon = new ImageIcon("resources/tube.png");
	            private ImageIcon hiddenTableIcon = new ImageIcon("resources/tablehidden.png");
	            private ImageIcon hiddenTubeIcon = new ImageIcon("resources/tubehidden.png");
	            
	            @Override
	            public Component getTreeCellRendererComponent(JTree tree,
	                    Object value, boolean selected, boolean expanded,
	                    boolean isLeaf, int row, boolean focused) {
	                Component c = super.getTreeCellRendererComponent(tree, value,
	                        selected, expanded, isLeaf, row, focused);
	                
	                //first base icon
	                if (row == 0){
	                    setIcon(baseIcon);
	                }

	                if(halls.contains(value.toString())){
	                	setIcon(hallIcon);
	                }
	                
	                if(baths.contains(value.toString())){
	                	
	                	TreePath pathPointer = tree.getPathForRow(row);
	                	
	                	if(pathPointer != null){
	                    	System.out.println("pathcount: " + pathPointer.getPathCount());
	                    	
	                    	if(pathPointer.getPathCount() == 3){
	                    		
	                        	System.out.println("pathPointerresult: " + pathPointer.getPathComponent(1).toString() + "#" + value.toString().toLowerCase());
	                        	
	                        	if(bathshidden.size() > 0 && bathshidden.contains(pathPointer.getPathComponent(1).toString() + "#" + value.toString().toLowerCase())){
	                        		System.out.println("bathshiddenentry: " + bathshidden.get(0) + " test object identity: " + bathshidden.contains(pathPointer.getPathComponent(1).toString() + "#" + value.toString().toLowerCase()) + "with: " + pathPointer.getPathComponent(1).toString() + "#" + value.toString().toLowerCase());
	                        		setIcon(hiddenTableIcon);
	                        	}else{
	                        		
	                        		setIcon(tableIcon);
	                        	}
	                    		
	                    	}
	                	}
	                	
	                }
	                
	                if(substances.contains(value.toString())){
	                	
	                	TreePath pathPointer = tree.getPathForRow(row);
	                	
	                	if(pathPointer != null){
	                    	System.out.println("pathcount: " + pathPointer.getPathCount());
	                    	
	                    	if(pathPointer.getPathCount() > 3){
	                    		
	                        	System.out.println("pathPointerresult: " + pathPointer.getPathComponent(1).toString() + "#" + pathPointer.getPathComponent(2).toString() + "#" + value.toString());
	                        	
	                        	if(substanceshidden.size() > 0 && substanceshidden.contains(pathPointer.getPathComponent(1).toString() + "#" + pathPointer.getPathComponent(2).toString().toLowerCase() + "#" + value.toString())){
	                        		System.out.println("substanceshiddenentry: " + substanceshidden.get(0) + " test object identity: " + substanceshidden.contains(pathPointer.getPathComponent(1).toString() + "#" + pathPointer.getPathComponent(2).toString().toLowerCase() + "#" + value.toString()) + "with: " + pathPointer.getPathComponent(1).toString() + "#" + pathPointer.getPathComponent(2).toString().toLowerCase() + "#" + value.toString());
	                        		setIcon(hiddenTubeIcon);
	                        	}else{
	                        		
	                        		setIcon(tubeIcon);
	                        	}
	                    		
	                    	}
	                	}

	                }

	                
	                return c;
	            }
	        });
	        
			for (int i = 0; i < tree.getRowCount(); i++) {
				tree.expandRow(i);
			}
			for (int i = 0; i < tree.getRowCount(); i++) {
				System.out.println("path component count: " + tree.getPathForRow(i).getPathCount());
				if(tree.getPathForRow(i).getPathCount() > 2){
			         tree.collapseRow(i);
				}
			}
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
	
	public void updateTree(DefaultMutableTreeNode top){
		//new top and icon etc
		this.top = top;
		System.out.println("LEAF COUNT: " + top.getLeafCount());
		
        tree.updateUI();
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
		for (int i = 0; i < tree.getRowCount(); i++) {
			System.out.println("path component count: " + tree.getPathForRow(i).getPathCount());
			if(tree.getPathForRow(i).getPathCount() > 2){
		         tree.collapseRow(i);
			}
		}
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
					Main.ct.transmit("!createSubfolder " + textField.getText());
					System.out.println("subfolder creation with name triggered: " + textField.getText());
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
			setBounds(100, 100, 300, 370);
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

			JButton btnNewButton = new JButton("Abbrechen");
			btnNewButton.setBounds(10, 307, 130, 23);
			contentPanel.add(btnNewButton);
			btnNewButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					setVisible(false);
					dispose();
					newTableDialog = null;
					((Window) getParent()).setAlwaysOnTop(true);
				}
			});

			JLabel lblSpaltenEintragen = new JLabel("Stoffe eintragen:");
			lblSpaltenEintragen.setBounds(10, 62, 274, 14);
			contentPanel.add(lblSpaltenEintragen);
			
			JLabel lblOrdnerAuswählen = new JLabel("Tabelle in diesem Ordner speichern:");
			lblOrdnerAuswählen.setBounds(10, 237, 274, 14);
			contentPanel.add(lblOrdnerAuswählen);
			
			String[] comboEntry = new String[Main.it.mainmenu.tableFolderList.size()];
			
			for(int i = 0; i < Main.it.mainmenu.tableFolderList.size(); i++){
				comboEntry[i] = Main.it.mainmenu.tableFolderList.get(i);
			}
			
			JComboBox comboBox = new JComboBox(comboEntry);
			comboBox.setBounds(10, 257, 274, 20);
			contentPanel.add(comboBox);

			//TODO zu scrolled pane
			
			JTextArea textArea = new JTextArea();
			JScrollPane scroll = new JScrollPane (textArea);
			scroll.setVisible(true);
			textArea.setEditable(true);
			scroll.setBounds(10, 78, 264, 148);
			contentPanel.add(scroll);
			
			JButton btnSenden = new JButton("Senden");
			btnSenden.setBounds(154, 307, 130, 23);
			contentPanel.add(btnSenden);
			btnSenden.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					
					String lines[] = textArea.getText().split("\\r?\\n");
					
				    Set<String> set = new HashSet<>();
				    
				    boolean duplicateSubstance = false;

				    for (String t: lines){
				        if (!set.add(t)){
				        	Popup popEmptyID = new Popup("Stoff(e) doppelt vorhanden!");
				        	duplicateSubstance = true;
				        }
				    
				    }
				    
				    if(!duplicateSubstance){
						String columns = "Datum Uhrzeit Name";
						
						for(int i = 0; i < lines.length; i++){
							columns += " " + lines[i];
						}
						
						Main.ct.transmit("!createTable " + textField.getText() + " " + Main.it.mainmenu.tableFolderList.get(comboBox.getSelectedIndex()) + " " + columns + " Begründung");
						Main.ct.transmit("!requestDirectoryUpdate");
						Main.ct.transmit("!requestTableUpdateReset");
						setVisible(false);
						dispose();
						newTableDialog = null;
						((Window) getParent()).setAlwaysOnTop(true);
				    }
					
				}
			});
			
			/*JButton btnHinzufgen = new JButton("Hinzufuegen");
			btnHinzufgen.setBounds(165, 30, 119, 23);
			contentPanel.add(btnHinzufgen);*/
			
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
