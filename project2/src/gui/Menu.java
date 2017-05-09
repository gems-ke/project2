package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

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
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import data.ListManager;
import data.TableData;
import main.Main;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

/**
 * Main Design Class of the Program. It holds all the Stuff to handle the
 * variables.
 */
public class Menu extends JFrame implements MouseListener {
	/**
	 * Necessary serial ID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Menu Bar + Menu Entries
	 */
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuEntry1 = new JMenu("Aktion");
	private JMenu menuEntry2 = new JMenu("Tabelle");
	private JMenu menuEntry3 = new JMenu("Admin");
	private JMenu menuEntry4 = new JMenu("Einstellungen");
	/**
	 * The MAIN Content Panel
	 */
	private JPanel contentPane;

	public static String activeUser = "";

	public static ArrayList<String> onlineUsers = new ArrayList<String>();

	// Create the nodes.
	private DefaultMutableTreeNode top = new DefaultMutableTreeNode("");

	/**
	 * The JTree Object and the important child stuff
	 */
	private JTree tree = null;
	private final JScrollPane scrollPane = new JScrollPane();
	private static JTable table;
	private static TableColumnModel tableColumnModel;
	private static JTableHeader tableHead;
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
	private ArrayList<JTable> tableDynamic = new ArrayList<>();

	//protected static UserControl userControl = null;
	public JTextField textField;
	public JTextField textField_1;
	public JTextField textField_2;
	
    private static JTextArea textAreaUser;
    
    public static JButton btnSenden;
    
     ArrayList<TableData> tables = new ArrayList<TableData>();

	// -------------------------------------------------------------------------------------------
	// //
	/**
	 * Create the frame from the Constructor. ONLY VIEW STUFF ALLOWED
	 */
	public Menu(String currentUser) {
		// --------------- INIT --------------- //
		activeUser = currentUser;
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarSize = scnMax.bottom;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (int) screenSize.getWidth(), (int) screenSize.getHeight() - taskBarSize);
		setTitle("Programm");
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// --------------- MENU --------------- //
		// Setting up the Menu + Items
		setJMenuBar(menuBar);
		menuBar.add(menuEntry1);
		JMenuItem mntmNewMenuItem = new JMenuItem("Eintrag hinzuf\u00FCgen");
		menuEntry1.add(mntmNewMenuItem);
		JMenuItem mntmEintragBearbeiten = new JMenuItem("Eintrag bearbeiten");
		menuEntry1.add(mntmEintragBearbeiten);
		JSeparator separator_1 = new JSeparator();
		menuEntry1.add(separator_1);
		JMenuItem mntmnderungenHochladen = new JMenuItem("\u00C4nderungen hochladen");
		menuEntry1.add(mntmnderungenHochladen);
		JSeparator separator = new JSeparator();
		menuEntry1.add(separator);
		JMenuItem mntmAusloggen = new JMenuItem("Ausloggen");
		menuEntry1.add(mntmAusloggen);
		JMenuItem mntmAusloggenUndBeenden = new JMenuItem("Ausloggen und Beenden");
		menuEntry1.add(mntmAusloggenUndBeenden);
		menuBar.add(menuEntry2);
		JMenuItem mntmTabelleFormatieren = new JMenuItem("Tabelle formatieren");
		menuEntry2.add(mntmTabelleFormatieren);
		JMenuItem mntmTabelleBereinigen = new JMenuItem("Tabelle bereinigen");
		menuEntry2.add(mntmTabelleBereinigen);
		menuBar.add(menuEntry3);
		JMenuItem mntmBenutzerkontrolle = new JMenuItem("Benutzerkontrolle");
		menuEntry3.add(mntmBenutzerkontrolle);
		JMenuItem mntmKostenrechnung = new JMenuItem("Kostenrechnung");
		menuEntry3.add(mntmKostenrechnung);
		JMenuItem mntmnderungsverlauf = new JMenuItem("\u00C4nderungsverlauf");
		menuEntry3.add(mntmnderungsverlauf);
		JMenuItem mntmBackup = new JMenuItem("Backup");
		menuEntry3.add(mntmBackup);
		menuBar.add(menuEntry4);
		JMenuItem mntmFenstereinstellungen = new JMenuItem("Fenstereinstellungen");
		menuEntry4.add(mntmFenstereinstellungen);
		contentPane.setBorder(new EmptyBorder(5, 0, 0, 0));

		// --------------- MENU ITEM ON CLICK --------------- //
		mntmBenutzerkontrolle.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				/*if (userControl == null) {
					userControl = new UserControl();
				}*/
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

		// --------------- INFO BAR --------------- //
		JTextArea txtrText = new JTextArea();
		txtrText.setBounds(217, 958, 1677, 22);
		txtrText.setEditable(false);
		txtrText.setRows(1);
		txtrText.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(txtrText);

		// --------------- TABBED PANES --------------- //
		this.tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(217, 0, 1365, 947);
		contentPane.add(tabbedPane);
		table = new JTable(200, ListManager.getColumnNameCount());
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);

		// Table HEADER
		tableHead = table.getTableHeader();

		// ADD HERE THE FIRST TAB WITH SCROLLPANE
		tabbedPane.addTab("New tab", null, scrollPane, null);
		scrollPane.setViewportView(table);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		tabbedPane.setTitleAt(0, "Readme");

		// --------------- INITIALIZE MAIN CONTENT --------------- //
		this.init();
	}

	/**
	 * initializes the logical stuff
	 */
	public void init() {
		// JTREE UPDATE STUFF! Call it to handle JTree Updates and Adds/Deletes
		tableColumnModel = tableHead.getColumnModel();
		Menu.tableRepaint();
		this.initTreeStuff();
		// ------------------------------Wird später
		// eingefügt---------------------------------- //
		// für kevin auskommentiert
		Main.ct.transmit("!requestUserlistUpdate");
		Main.ct.transmit("!requestDirectoryUpdate");
		// auskommentieren, wenn du kein kevin bist
		// simulateTransmissions();
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() {
		 * while(true){ txtrUser.repaint();
		 * 
		 * } } });
		 */
		// -------------------------------------------------------------------------------------
		// //
		//simulateTransmissions();
	}

	/*
	 * Use this method to define JTree Elements and the Object itself
	 */
	public void initTreeStuff() {
		// Create the JTree Object and the action listener
		this.tree = new JTree(this.top);
		tree.setBounds(10, 11, 197, 969);
		this.tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
		this.tree.addMouseListener(this);
		this.tree.setBorder(BorderFactory.createEtchedBorder());
		this.contentPane.add(tree);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Eintrag hinzuf\u00FCgen", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(1592, 652, 302, 295);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnSenden = new JButton("Senden");
		btnSenden.setBounds(10, 250, 282, 34);
		panel.add(btnSenden);
		
		textField = new JTextField();
		textField.setBounds(10, 36, 282, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(10, 80, 282, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(10, 125, 282, 114);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Zugabe");
		lblNewLabel.setBounds(10, 21, 282, 14);
		panel.add(lblNewLabel);
		
		JLabel lblStoff = new JLabel("Stoff");
		lblStoff.setBounds(10, 67, 282, 14);
		panel.add(lblStoff);
		
		JLabel lblBegrndung = new JLabel("Begründung");
		lblBegrndung.setBounds(10, 111, 282, 14);
		panel.add(lblBegrndung);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Benutzer", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(1592, 25, 302, 594);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textAreaUser = new JTextArea();
		textAreaUser.setBounds(10, 21, 282, 562);
		panel_1.add(textAreaUser);
		
		this.top.setUserObject("Benchmark Tree");

		// Fill the Tree with new content
		this.fillTree(userlist2);
	}

	/**
	 * Reload this method to save new Tree elements
	 */
	public void fillTree(String[] list) {
		this.contentPane.remove(tree);
		this.top.removeAllChildren();
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode subNode = null;
		boolean whileBool = true;

		// Load all Nodes/Leafs for the JTree Structure
		for (int i = 1; i < list.length; i++) {
			if (list[i].equals("*")) {
				node = new DefaultMutableTreeNode(list[i + 1]);
				int whileIterator = i + 2;
				whileBool = true;
				while (whileBool) {
					if (!list[whileIterator].equals("*") && !list[whileIterator].equals("**")) {
						subNode = new DefaultMutableTreeNode(list[whileIterator].replaceAll("[^A-Za-z]", "").substring(0, 1).toUpperCase() + list[whileIterator].replaceAll("[^A-Za-z]", "").substring(1));
						node.add(subNode);
						
						//TODO extended subnode f�r get echten namen implementieren !!
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
	}

	/**
	 * Use this function after you changed values in the ListManager class. It
	 * repaints the table and deletes all data. SAVE it before call this
	 * function.
	 */
	public static void tableRepaint() {
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
			table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
			table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
			table.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);
		}
		tableHead.repaint();
	}

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

	/**
	 * javadoc vergessen
	 */
	public void simulateTransmissions() {
		String response = "!updateOnlineUsers tolu Admin";
		String[] userlist = response.split(" ");
		updateUserList(userlist);
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

	/**
	 * Called, when you (double) click on aJTree Element
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		int selRow = tree.getRowForLocation(e.getX(), e.getY());
		TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());

		Object node = tree.getLastSelectedPathComponent();
		TreeNode treeNode = (TreeNode) node;

		if (selRow != -1) {
			if (e.getClickCount() == 1) {
				System.out.println("MOUSE CLICK 1");
			} else if (e.getClickCount() == 2 && treeNode.isLeaf()) {
				// --------------- DOUBLE CLICK HANDLER --------------- //
				// Create a new Tab Element
				this.scrollPaneDynamic.add(new JScrollPane());
				this.tableDynamic.add(new JTable(200, ListManager.getColumnNameCount()));
				// initialize this tab element
				this.tableDynamic.get(tableDynamic.size() - 1).setFillsViewportHeight(true);
				this.tableDynamic.get(tableDynamic.size() - 1).setRowHeight(25);
				// add this table element to the view
				this.tabbedPane.addTab(selPath.getLastPathComponent().toString(), null,
						scrollPaneDynamic.get(tableDynamic.size() - 1), null);
				this.scrollPaneDynamic.get(tableDynamic.size() - 1)
						.setViewportView(tableDynamic.get(tableDynamic.size() - 1));
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}
}