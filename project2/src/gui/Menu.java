package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import data.ListManager;
import data.TableData;
import data.TableLine;
import main.Main;
import networking.Client;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import java.awt.Color;
import javax.swing.border.CompoundBorder;

/**
 * Main Design Class of the Program. It holds all the Stuff to handle the
 * variables.
 */
public class Menu extends JFrame implements MouseListener {
	/**
	 * Necessary serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	private static int firstrun = 1;
	
	private static String currentTable = "";
	/**
	 * Menu Bar + Menu Entries
	 */
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuEntry1 = new JMenu("Aktion");
	private JMenu menuEntry3 = new JMenu("Admin");
	/**
	 * The MAIN Content Panel
	 */
	private JPanel contentPane;
	
	//Array and indexcoutner to connect tabindex to tablename
	private ArrayList<String> tabIndicator = new ArrayList<String>();

	public static String activeUser = "";

	public static ArrayList<String> onlineUsers = new ArrayList<String>();

	public static ArrayList<String> existingUsers;
	
	public static ArrayList<ListManager> tableDataArray = new ArrayList<ListManager>();
	public static ArrayList<String> testArray = new ArrayList<String>();
	
	public static ArrayList<String> tableNames;
	ArrayList<TableData> tables = new ArrayList<TableData>();

	// Create the nodes.
	public static DefaultMutableTreeNode top = new DefaultMutableTreeNode("");
	
	JPanel panel;

	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
	int taskBarSize = scnMax.bottom;

	/**
	 * The JTree Object and the important child stuff
	 */
	private JTree tree = null;
	@SuppressWarnings("unused")
	private static TableColumnModel tableColumnModel;
	private static DefaultTableCellRenderer rightRenderer, leftRenderer, centerRenderer;
	private JTabbedPane tabbedPane;

	private String response2 = "!updateDirectory * instinct highelo.txt legend.txt master.txt * rendem lol.txt lul.txt * tyler1 hehexdbitch.txt **";
	private String[] userlist2 = response2.split(" ");
	/**
	 * Saved Instance objects of the (sub)nodes
	 */
	private ArrayList<DefaultMutableTreeNode> nodeListEntry = new ArrayList<>();
	private ArrayList<DefaultMutableTreeNode> subnodeListEntry = new ArrayList<>();

	// dynamic tabs to add from JTree
	private ArrayList<JScrollPane> scrollPaneDynamic = new ArrayList<>();
	private static ArrayList<JTable> tableDynamic = new ArrayList<>();

	protected static UserControl userControl = null;
	protected static TableControl tableControl = null;
	protected static BegrundungsControl begrundControl = null;
	public JComboBox textField;

	private static JTextArea textAreaUser;

	public static JButton btnSenden;

	private ArrayList<String> tableNamess = new ArrayList<String>();
	
	ArrayList<DefaultTableModel> models = new ArrayList<DefaultTableModel>();
	ArrayList<JTableHeader> tableHeads = new ArrayList<JTableHeader>();
	public static ArrayList<String> tableFolderList = new ArrayList<String>();
	@SuppressWarnings("unused")
	private static JTableHeader tableHead;
	private JComboBox textField_3;
	private JTextField textField_1;
	private JTextField textField_2;
	ArrayList<String> stoffe = new ArrayList<String>();
	JTextField comboBox = null;
	JTextArea textArea = new JTextArea();

	// TIME AND DATE VAR STUFF
	DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
	DateFormat timeFormat = new SimpleDateFormat("HH:mm");
	Date date = new Date();

	// ---------------------------------------------------------------------------------------
	// //
	/**
	 * Create the frame from the Constructor. ONLY VIEW STUFF ALLOWED
	 */
	public Menu(String currentUser) {
		// --------------- INIT --------------- //
		activeUser = currentUser;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (int) screenSize.getWidth(), (int) screenSize.getHeight() - taskBarSize);
		setTitle("Programm");
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		TableLine.initIndexArray(); // Set the standard index settings for
									// tables
		stoffe.add("TEST1");
		stoffe.add("TEST2");
		stoffe.add("TEST3");

		this.comboBox = new JTextField();

		// --------------- MENU --------------- //
		// Setting up the Menu + Items
		setJMenuBar(menuBar);
		menuBar.add(menuEntry1);
		JMenuItem mntmAusloggen = new JMenuItem("Ausloggen");
		menuEntry1.add(mntmAusloggen);
		JMenuItem mntmAusloggenUndBeenden = new JMenuItem("Ausloggen und Beenden");
		menuEntry1.add(mntmAusloggenUndBeenden);
		menuBar.add(menuEntry3);
		JMenuItem mntmBenutzerkontrolle = new JMenuItem("Benutzerverwaltung");
		menuEntry3.add(mntmBenutzerkontrolle);

		JMenuItem mntmTabelleVerwalten = new JMenuItem("Tabellenverwaltung");
		menuEntry3.add(mntmTabelleVerwalten);

		JMenuItem mntmBackupVerwalten = new JMenuItem("Backupverwaltung");
		menuEntry3.add(mntmBackupVerwalten);

		JSeparator separator_2 = new JSeparator();
		menuEntry3.add(separator_2);

		JMenuItem mntmEintragZwischenschieben = new JMenuItem("Eintrag zwischenschieben");
		menuEntry3.add(mntmEintragZwischenschieben);
		JMenuItem mntmnderungsverlauf = new JMenuItem("\u00C4nderungsverlauf");
		menuEntry3.add(mntmnderungsverlauf);

		JSeparator separator = new JSeparator();
		menuEntry3.add(separator);

		JMenuItem mntmStatistik = new JMenuItem("Statistik");
		menuEntry3.add(mntmStatistik);
		JMenuItem mntmKostenrechnung = new JMenuItem("Kostenrechnung");
		menuEntry3.add(mntmKostenrechnung);
		
		menuEntry3.add(separator);
		
		JMenuItem mntmBergrundung = new JMenuItem("Begründungen bearbeiten");
		menuEntry3.add(mntmBergrundung);
		
		contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));

		// --------------- MENU ITEM ON CLICK --------------- //
		mntmAusloggen.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				// TODO einfaches neustarten des programms nicht so einfach
				// mÃ¶glich
			}
		});
		
		mntmBergrundung.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (begrundControl == null) {
					begrundControl = new BegrundungsControl();
				}
			}
		});

		mntmAusloggenUndBeenden.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				System.exit(0);
			}
		});

		mntmBenutzerkontrolle.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (userControl == null) {
					userControl = new UserControl();
				}
			}
		});

		mntmTabelleVerwalten.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				if (tableControl == null) {
					tableControl = new TableControl();
				}
			}
		});

		// --------------- TABLE ROWS --------------- //
		rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.setLayout(null);

		// --------------- TABBED PANES --------------- //
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		this.tabbedPane.addMouseListener(this);
		tabbedPane.setBounds((int) (screenSize.getWidth() * 0.1f) + 15, 0, (int) (screenSize.getWidth() * 0.71f),
				(int) (screenSize.getHeight() * 0.9f - taskBarSize));
		contentPane.add(tabbedPane);

		// ADD HERE THE FIRST TAB WITH SCROLLPANE
		// tabbedPane.addTab("Readme", null, scrollPane, null);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// --------------- INITIALIZE MAIN CONTENT --------------- //
		this.init();
	}

	/**
	 * initializes the logical stuff
	 */
	public void init() {
		/*try {
			tableColumnModel = tableHead.getColumnModel();
			Menu.columnSettings(Menu.tableDynamic.get(0));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}*/

		
		this.initTreeStuff();
		Main.ct.transmit("!requestUserlistUpdate");
		Main.ct.transmit("!requestDirectoryUpdate");
		Main.ct.transmit("!requestUserExistingUpdate");

		// auskommentieren, wenn du kein kevin bist
		// simulateTransmissions();
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() {
		 * while(true){ txtrUser.repaint();
		 * 
		 * } } });
		 */
		// ----------------------------------------------------------------------------------
		// //
		// simulateTransmissions();
	}

	/*
	 * Use this method to define JTree Elements and the Object itself
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initTreeStuff() {
		// Create the JTree Object and the action listener
		this.tree = new JTree(this.top);
		tree.setBounds(10, 11, (int) (screenSize.getWidth() * 0.1f),
				(int) (screenSize.getHeight() * 0.897f - taskBarSize));
		// 1920 breite - 1080 hÃ¶he
		// (int) screenSize.getWidth(), (int) screenSize.getHeight()
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.addMouseListener(this);
		this.tree.setBorder(BorderFactory.createEtchedBorder());
		this.contentPane.add(tree);

		panel = new JPanel();
		panel.setBorder(new TitledBorder(new CompoundBorder(null, UIManager.getBorder("TitledBorder.border")),
				"Eintrag hinzuf\u00FCgen", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds((int) (screenSize.getWidth() * 0.83f), (int) (screenSize.getHeight() * 0.3f),
				(int) (screenSize.getWidth() * 0.1567), (int) (screenSize.getHeight() * 0.555f));

		contentPane.add(panel);
		panel.setLayout(null);

		// --------------------- NEUER STOFF BEREICH ----------------- //

		// (int) (panel.getWidth() * 0.9)

		JLabel lblNewLabel = new JLabel("Tabelle");
		lblNewLabel.setBounds(10, 22, 142, 14);
		panel.add(lblNewLabel);

		// Tabelle combobvox
		textField = new JComboBox(tableNamess.toArray());
		textField.setBounds(10, 37, (int) (panel.getWidth() * 0.9), 20); // 281
																			// breite
		
        textField.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                    	String[] columnNames = new String[0];
                    	
                    	for(int i = 0; i < tables.size(); i++){
                    		
                    		System.out.println("tables value: " + tables.get(i).getTableName() + " compare to combobox value: " + textField.getSelectedItem().toString() + " result: " + tables.get(i).getTableName().equals(textField.getSelectedItem().toString()));
                    		
                        	if(tables.get(i).getTableName().equals(textField.getSelectedItem().toString())){
                        		columnNames = tables.get(i).getColumnArray();
                        	}
                    		
                    	}
                    	
                    	//textField_3 = new JComboBox(columnNames);
                    	
                    	textField_3.removeAllItems();
                    	
                    	for(int i = 0; i < columnNames.length; i++){
                    		if(!columnNames[i].equals("Datum") && !columnNames[i].equals("Name") && !columnNames[i].equals("Uhrzeit") && !columnNames[i].equals("Begründung"))
                    		textField_3.addItem(columnNames[i]);
                    	}
                    	
                    	}

                }            
        );
        
		panel.add(textField);

		JLabel lblStoff = new JLabel("Zugabe");
		lblStoff.setBounds(10, 114, (int) (panel.getWidth() * 0.9), 14);
		panel.add(lblStoff);

		comboBox.setBounds(10, 127, (int) (panel.getWidth() * 0.9), 20);
		panel.add(comboBox);

		JLabel lblBegrndung = new JLabel("Begruendung");
		lblBegrndung.setBounds(10, 250, (int) (panel.getWidth() * 0.9), 14);
		panel.add(lblBegrndung);

		textArea.setBounds(10, 266, (int) (panel.getWidth() * 0.9), 100);
		panel.add(textArea);

		JLabel lblEinheit = new JLabel("Stoff");
		lblEinheit.setBounds(10, 68, 130, 14);
		panel.add(lblEinheit);

		// EINHEIT Textfeld
		textField_3 = new JComboBox(stoffe.toArray());
		textField_3.setBounds(10, 83, (int) (panel.getWidth() * 0.9), 20);
		panel.add(textField_3);

		btnSenden = new JButton("Senden");
		btnSenden.setBounds(10, 377, (int) (panel.getWidth() * 0.9), 34);
		panel.add(btnSenden);

		textField_1 = new JTextField(); // uhrzeit
		textField_1.setColumns(10);
		textField_1.setBounds(10, 173, (int) (panel.getWidth() * 0.9), 20);
		panel.add(textField_1);
		textField_1.setText(timeFormat.format(date));

		JLabel lblUhrzeitleerFr = new JLabel("Zugabe-Uhrzeit");
		lblUhrzeitleerFr.setBounds(10, 158, (int) (panel.getWidth() * 0.9), 14);
		panel.add(lblUhrzeitleerFr);

		textField_2 = new JTextField(); // datum
		textField_2.setColumns(10);
		textField_2.setBounds(10, 219, (int) (panel.getWidth() * 0.9), 20);
		panel.add(textField_2);
		textField_2.setText(dateFormat.format(date));

		JLabel lblZugabedatum = new JLabel("Zugabe-Datum");
		lblZugabedatum.setBounds(10, 204, (int) (panel.getWidth() * 0.9), 14);
		panel.add(lblZugabedatum);

		//TODO zeile hinzufügen überarbeiten!
		btnSenden.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				
				String[] data = new String[6];
				
				data[0] = textField.getSelectedItem().toString();
				data[1] = textField_3.getSelectedItem().toString();
				data[2] = comboBox.getText();

				// Set the new table column line
				if (textField_2.getText().toString().equals("")) {
					data[3] = dateFormat.format(date);
				} else {
					data[3] = textField_2.getText().toString();
				}
				if (textField_1.getText().toString().equals("")) {
					data[4] = timeFormat.format(date);
				} else {
					data[4] = textField_1.getText().toString();
				}
				//addRowData(dateAndTime[0], dateAndTime[1]);
				data[5] = textArea.getText();
				
				addRowData(data);
			}
		});

		// Start timer thread to update text date ui
		new TimerThread().start();

		// ----------------------------- ------------------------ //

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Benutzer", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds((int) (screenSize.getWidth() * 0.83f), 25, (int) (screenSize.getWidth() * 0.157),
				(int) (screenSize.getHeight() * 0.3f - taskBarSize));

		contentPane.add(panel_1);
		panel_1.setLayout(null);

		textAreaUser = new JTextArea();
		textAreaUser.setEditable(false);
		textAreaUser.setBounds((int) (screenSize.getWidth() * 0.005), (int) (screenSize.getHeight() * 0.02),
				+(int) (panel_1.getWidth() * 0.94f), (int) (panel_1.getHeight() * 0.9f));

		panel_1.add(textAreaUser);
		this.top.setUserObject("Benchmark Tree");

		// Fill the Tree with new content
		this.fillTree(userlist2);
		
	}

	/**
	 * Adds new row elements
	 */
	//TODO addrowdata überarbeiten für neue tabledata konzept
	public void addRowData(String[] data) {
		
		String message = "!appendTableLine";
		
		for(int i = 0; i < data.length; i++){
			message += " " + data[i];
		}
		
		System.out.println("transmitted message: " + message);
		Main.ct.transmit(message);
		
		/*for(int i = 0; i < tables.size(); i++){
			if(data[0].equals(tables.get(i).getTableName())){
				tables.get(i).addLine(data);
			}
		}*/
		
		/*Vector<String> vec = new Vector<String>();
		vec.add(data[3]);
		vec.add(data[4]);
		vec.add(Client.currentUserName);
		
		for(int i = 0; i < tables.size(); i++){
			if(tables.get(i).equals(data[0])){
				for(int x = 0; x < tables.get(i).getColumnArray().length; x++){
					if(!tables.get(i).getColumnArray()[x].equals("Datum") && !tables.get(i).getColumnArray()[x].equals("Uhrzeit") && !tables.get(i).getColumnArray()[x].equals("Name") && !tables.get(i).getColumnArray()[x].equals("Begründung")){
						if(data[1].equals(tables.get(i).getColumnArray()[x])){
							vec.add(data[2]);
						}else{
							vec.add("");
						}
					}
				}
			}
		}
		
		vec.add(data[5]);
		
		Object[] obj = new Object[] {data[3], data[4],
				Client.currentUserName, comboBox.getText().toString(), textField.getSelectedItem().toString(),
				textField_3.getSelectedItem().toString(), textArea.getText().toString() };
		
		Object[] row;
		models.get(tabbedPane.getSelectedIndex())
				.addRow(vec);*/
		
		
	}
	
	public void currentTabRefresh(){
		
		if(this.tabbedPane.getTabCount() != 0){
		
				
				this.tabbedPane.remove(0);
				// add this table element to the view
				
				// --------------- DOUBLE CLICK HANDLER --------------- //
				// Create a new Tab Element w/ models
				if(models.size() != 0){
					models.remove(0);
				}
				
				if(tableDynamic.size() != 0){
					tableDynamic.remove(0);
				}
					
				models.add(new DefaultTableModel());
				
				for(int i = 0; i < tableDataArray.size(); i++){
					
					System.out.println("truecheck on right tab open: " + tableDataArray.get(i).tableName.equals(currentTable) + " with tabledataarray result: " + tableDataArray.get(i).tableName + " compared with: " + currentTable);
					if(tableDataArray.get(i).tableName.equals(currentTable)){
						for (int ix = 0; ix < tableDataArray.get(i).columnNames.size(); ++ix) {
							models.get(tableDynamic.size()).addColumn(tableDataArray.get(i).getColumnNameElement(ix));
						}
					}
					
				}

				tabIndicator.add(currentTable);
				
				this.scrollPaneDynamic.add(new JScrollPane());
				// this.tableDynamic.add(new JTable(200,
				// ListManager.getColumnNameCount()));
				Menu.tableDynamic.add(new JTable(models.get(tableDynamic.size())));

				// initialize this tab element
				Menu.tableDynamic.get(tableDynamic.size() - 1).setFillsViewportHeight(true);
				Menu.tableDynamic.get(tableDynamic.size() - 1).setRowHeight(25);
				
				System.out.println("currentTable: " + currentTable);
				String[] tabNameR = currentTable.split("#");
				String tabName = tabNameR[1].substring(0, 1).toUpperCase() + tabNameR[1].substring(1);
				
				this.tabbedPane.addTab(tabName, null,
						scrollPaneDynamic.get(tableDynamic.size() - 1), null);
				this.scrollPaneDynamic.get(tableDynamic.size() - 1)
						.setViewportView(tableDynamic.get(tableDynamic.size() - 1));
				// tableColumnModel = tableHead.getColumnModel();

				System.out.println("tables size:" + tables.size());
				
				for(int i = 0; i < tables.size(); i++){
					
					System.out.println("tablename: " + currentTable + "comparing string in list: " + tables.get(i).getTableName());
					if(tables.get(i).getTableName().equals(currentTable)){
						
						currentTable = tables.get(i).getTableName();
						
						String[] testData = new String[tables.get(i).getColumnCount()];
						
						for(int y = 0; y < testData.length; y++){
							testData[y] = Integer.toString(y);
						}
						
						for(int x = 0; x < tables.get(i).getLineCount(); x++){
							fillRowData(tables.get(i).getLineArray(x));
						}
					}
				}
		}
				
				
				//Menu.columnSettings(tableDynamic.get(tableDynamic.size() - 1));
				
		
	}
	
	public void fillRowData(String[] data){
		
		Vector<String> vec = new Vector<String>();
		
		/*vec.addElement(id);
		vec.addElement(datum);
		vec.addElement(uhrzeit);
		vec.addElement(name);*/
		
		for(int i = 0; i < data.length; i++){
			vec.addElement(data[i]);
		}
		
		//vec.addElement(begrundung);
		
		models.get(models.size()-1).addRow(vec);
	}

	/**
	 * Reload this method to save new Tree elements
	 */
	public void fillTree(String[] list) {
		tableNames = new ArrayList<String>();
		tableFolderList = new ArrayList<String>();
		this.contentPane.remove(tree);
		this.top.removeAllChildren();
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode subNode = null;
		boolean whileBool = true;

		// Load all Nodes/Leafs for the JTree Structure
		for (int i = 1; i < list.length; i++) {
			if (list[i].equals("*")) {
				node = new DefaultMutableTreeNode(list[i + 1]);
				tableFolderList.add(list[i + 1]);
				int whileIterator = i + 2;
				whileBool = true;
				while (whileBool) {
					if (!list[whileIterator].equals("*") && !list[whileIterator].equals("**")) {
						subNode = new DefaultMutableTreeNode(
								list[whileIterator].replaceAll("[^A-Za-z]", "").substring(0, 1).toUpperCase()
										+ list[whileIterator].replaceAll("[^A-Za-z]", "").substring(1));
						tableNames.add(list[whileIterator]);
						node.add(subNode);
						subnodeListEntry.add(subNode);
					} else {
						whileBool = false;
					}
					whileIterator++;
				}
				nodeListEntry.add(node);
				this.top.add(node);
			}
		}
		this.contentPane.add(tree);
		tree.updateUI();
		for (int i = 0; i < tree.getRowCount(); i++) {
			tree.expandRow(i);
		}
		System.out.println("tableFolderList size: " + tableFolderList.size());
		
		for(int i = 0; i < tableFolderList.size(); i++){
			System.out.println("tablefolder entry: " + tableFolderList.get(i));
		}
		
		String sexy = "";
		for(int i = 0; i < tableNames.size(); i++){
			sexy += " " + tableNames.get(i);
		}
		
		System.out.println("tablename length: " + tableNames.size() + " values:" + sexy);
		if(!tableNames.get(0).startsWith("highelo.txt")){
			if(firstrun == 1){
				Main.ct.transmit("!requestTableAllData");
				firstrun = 0;
			}
		}
		
		
	}

	/*private void updateTabledata(String tablename) {
		
		for(int i = 0; i < tableNames.size(); i++){
			Main.ct.transmit("!requestTableData " + tableNames.get(i));
		}
		System.out.println("tabledata requested for " + tableNames.size() + " tables.");
		
	}*/

	/**
	 * Use this function after you changed values in the ListManager class. It
	 * repaints the table and deletes all data. SAVE it before call this
	 * function.
	 */
	/*public static void columnSettings(JTable table) {
		// Main Table Routine
		for (int i = 0; i < ListManager.getColumnNameCount(); i++) {
			if (tableColumnModel != null) {
				TableColumn tc = tableColumnModel.getColumn(i);
				tc.setHeaderValue(ListManager.getColumnNameElement(i));
			}
			// Set the width of the columns
			table.getColumnModel().getColumn(i).setPreferredWidth(ListManager.getColumnWidthElement(i));
			// Set the Alignment of the columns
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
			table.getColumnModel().getColumn(6).setCellRenderer(leftRenderer);
		}
		try {
			tableHead.repaint();
		} catch (NullPointerException e1) {
			e1.printStackTrace();
		}
	}*/

	/**
	 * Javadoc nicht vergessen
	 * 
	 * @param userlist
	 *            the userlist
	 */
	
	public static void updateUserList(String[] userlist) {
		onlineUsers = new ArrayList<String>();
		ArrayList<String> admins = new ArrayList<String>();
		ArrayList<String> users = new ArrayList<String>();

		String adminString = "-Admins-";
		String userString = "\n -Benutzer-";
		String finalString = "";

		for (int i = 0; i < userlist.length - 1; i++) {
			onlineUsers.add(userlist[i + 1]);
		}
		for (int i = 0; i < onlineUsers.size(); i = i + 2) {
			if (onlineUsers.get(i + 1).equals("admin")) {
				admins.add(onlineUsers.get(i));
			} else {
				users.add(onlineUsers.get(i));
			}
		}
		for (int i = 0; i < admins.size(); i++) {
			adminString = adminString + "\n" + admins.get(i);
		}
		for (int i = 0; i < users.size(); i++) {
			userString = userString + "\n" + users.get(i);
		}
		finalString = adminString + "\n" + userString;
		activeUser = finalString;
		textAreaUser.setText(finalString);
		textAreaUser.repaint();
	}

	public static void updateExistingUsers(String message) {
		
		existingUsers = new ArrayList<String>();

		String[] users = message.split("\\*");

		for (int i = 1; i < users.length; i++) {
			existingUsers.add(users[i]);
		}

		if (userControl != null) {
			userControl.updateUserList();
		}

	}
	
	public void resetTableData(){
		System.out.println("resetted table data!");
		tables = new ArrayList<TableData>();
		textField.removeAllItems();
		testArray = new ArrayList<String>();
		tableDataArray = new ArrayList<ListManager>();
		
		Main.ct.transmit("!requestTableAllData");
	}

	/**
	 * javadoc vergessen
	 */
	public void simulateTransmissions() {
		String response = "!updateOnlineUsers tolu Admin";
		String[] userlist = response.split(" ");
		updateUserList(userlist);
	}

	/**
	 * Called, when you (double) click on aJTree Element
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int selRow = tree.getRowForLocation(e.getX(), e.getY());
		TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

		Object node = tree.getLastSelectedPathComponent();
		TreeNode treeNode = (TreeNode) node;

		// close the tab with MIDDLE mouse button
		// if (e.getButton() == 2) {
		// tabbedPane.removeTabAt(tabbedPane.getSelectedIndex());
		// }

		if (selRow != -1) {
			
			if (e.getButton() == 1 && e.getClickCount() == 2 && treeNode.isLeaf()) {
				
				// add this table element to the view
				String tabName = selPath.getLastPathComponent().toString();
				
				String foldername = selPath.getPathComponent(1).toString();
				System.out.println("foldername: " + foldername);
				
				// --------------- DOUBLE CLICK HANDLER --------------- //
				// Create a new Tab Element w/ models
				if(models.size() != 0){
					models.remove(0);
				}
				
				if(this.tabbedPane.getTabCount() != 0){
					this.tabbedPane.remove(0);
				}
				
				if(tableDynamic.size() != 0){
					tableDynamic.remove(0);
				}
					
				models.add(new DefaultTableModel());
				
				for(int i = 0; i < tableDataArray.size(); i++){
					
					System.out.println("truecheck on right tab open: " + tableDataArray.get(i).tableName.equals(foldername.toLowerCase() + "#" + tabName.toLowerCase()) + " with tabledataarray result: " + tableDataArray.get(i).tableName + " compared with: " + foldername.toLowerCase() + "#" + tabName.toLowerCase());
					if(tableDataArray.get(i).tableName.equals(foldername.toLowerCase() + "#" + tabName.toLowerCase())){
						for (int ix = 0; ix < tableDataArray.get(i).columnNames.size(); ++ix) {
							models.get(tableDynamic.size()).addColumn(tableDataArray.get(i).getColumnNameElement(ix));
						}
					}
					
				}

				tabIndicator.add(foldername.toLowerCase() + "#" + tabName.toLowerCase());
				
				currentTable = foldername.toLowerCase() + "#" + tabName.toLowerCase();
				
				this.scrollPaneDynamic.add(new JScrollPane());
				// this.tableDynamic.add(new JTable(200,
				// ListManager.getColumnNameCount()));
				Menu.tableDynamic.add(new JTable(models.get(tableDynamic.size())));

				// initialize this tab element
				Menu.tableDynamic.get(tableDynamic.size() - 1).setFillsViewportHeight(true);
				Menu.tableDynamic.get(tableDynamic.size() - 1).setRowHeight(25);
				
				this.tabbedPane.addTab(tabName, null,
						scrollPaneDynamic.get(tableDynamic.size() - 1), null);
				this.scrollPaneDynamic.get(tableDynamic.size() - 1)
						.setViewportView(tableDynamic.get(tableDynamic.size() - 1));
				// tableColumnModel = tableHead.getColumnModel();

				System.out.println("tables size:" + tables.size());
				
				for(int i = 0; i < tables.size(); i++){
					
					System.out.println("tablename: " + foldername.toLowerCase() + "#" + tabName.toLowerCase() + "comparing string in list: " + tables.get(i).getTableName());
					if(tables.get(i).getTableName().equals(foldername.toLowerCase() + "#" + tabName.toLowerCase())){
						
						
						String[] testData = new String[tables.get(i).getColumnCount()];
						
						for(int y = 0; y < testData.length; y++){
							testData[y] = Integer.toString(y);
						}
						
						for(int x = 0; x < tables.get(i).getLineCount(); x++){
							fillRowData(tables.get(i).getLineArray(x));
						}
					}
				}
				
				
				//Menu.columnSettings(tableDynamic.get(tableDynamic.size() - 1));
				
			}
		}
	}

	/**
	 * Class to handle async callbacks for actual time
	 */
	private class TimerThread extends Thread {
		public void run() {
			while (true) {
				timeFormat = new SimpleDateFormat("HH:mm");
				date = new Date();
				textField_1.setText(timeFormat.format(date));
				textField_1.validate();
				textField_1.repaint();

				try {
					sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

	public void updateTableData(String tableData) {
		
		System.out.println("tabledata string: " + tableData);
		tables.add(new TableData(tableData));
		System.out.println("tables size: " + tables.size() + " last table name: " + tables.get(tables.size() - 1).getTableName() + " table last column name: " + tables.get(tables.size() -1).getColumnName(tables.get(tables.size()-1).getColumnCount()) + " first line last column data: " + tables.get(tables.size()-1).getData(1, tables.get(tables.size() -1).getColumnName(tables.get(tables.size()-1).getColumnCount())));
		//System.out.println("Table: " + tables.get(tables.size()-1).getTableName() + " 2nd column name: " + tables.get(tables.size()).getColumnName(2) + " first line second column data: " + tables.get(0).getData(1, tables.get(tables.size()).getColumnName(2)));
		
		ListManager lm = new ListManager();
		lm.tableName = tables.get(tables.size()-1).getTableName();
		lm.columnNames = tables.get(tables.size()-1).getTableColumnStringArray();
		
		System.out.println("lm column size: " + lm.columnNames.size() + " lm table name: " + lm.tableName);
		
		//for(int i = 0; i < tables.get)
		
		testArray.add(lm.tableName);
		tableDataArray.add(lm);
		
		
		String foldernode = "DHR_1";
		String tabName = "Beize";
		
		if(tableDataArray.size() == 1){
			System.out.println("TDA#1: " + tableDataArray.get(0).tableName);
			
			System.out.println("TESTA#1: " + testArray.get(0));
		}
		
		if(tableDataArray.size() == 2){
			System.out.println("TDA#1: " + tableDataArray.get(0).tableName);
			System.out.println("TDA#2: " + tableDataArray.get(1).tableName);
			
			System.out.println("TESTA#1: " + testArray.get(0));
			System.out.println("TESTA#2: " + testArray.get(1));
		}
		
		
		for(int i = 0; i < tables.size(); i++){
			tableNames.add(tables.get(i).getTableName());
		}
		
		
		textField.addItem(lm.tableName);
		
		/*for(int i = 0; i < tableDataArray.size(); i++){
			System.out.println("the comparison of strings: " + tableDataArray.get(i).tableName.equals(foldernode.toLowerCase() + "#" + tabName.toLowerCase()) + " between tabname: " + foldernode.toLowerCase() + "#" + tabName.toLowerCase() + " and the lm array namevar: " + tableDataArray.get(i).tableName);
			
			if(tableDataArray.get(i).tableName.equals(foldernode.toLowerCase() + "#" + tabName.toLowerCase())){
				System.out.println(" true triggered!!!!!");
			}
		}*/
		
	}
	
}
