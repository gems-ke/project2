package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
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
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import data.ListManager;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

/**
 * Main Design Class of the Program. It holds all the Stuff to handle the
 * variables.
 */
public class Menu extends JFrame {
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
	/**
	 * the user box filled with users and admins (strings)
	 */
	public static JTextArea txtrUser;

	public static String activeUser = "";

	public static ArrayList<String> onlineUsers = new ArrayList<String>();
	/**
	 * The JTree Object and the important child stuff
	 */
	private JTree tree = new JTree();
	private final JScrollPane scrollPane = new JScrollPane();
	private static JTable table;
	private static TableColumnModel tableColumnModel;
	private static JTableHeader tableHead;
	private static DefaultTableCellRenderer rightRenderer, leftRenderer, centerRenderer;

	String response2 = "!updateDirectory * instinct highelo.txt legend.txt master.txt * rendem lol.txt lul.txt * tyler1 hehexdbitch.txt **";
	String[] userlist2 = response2.split(" ");
	/**
	 * Saved Instance objects of the (sub)nodes
	 */
	ArrayList<DefaultMutableTreeNode> nodeListEntry = new ArrayList<>();
	ArrayList<DefaultMutableTreeNode> subnodeListEntry = new ArrayList<>();

	// -----------------------------------------------------------------------------------------------
	// //

	/**
	 * Create the frame from the Constructor. ONLY VIEW STUFF ALLOWED
	 */
	public Menu(String currentUser) {
		activeUser = currentUser;
		// Screen Sizes etc
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

		// Setting up the Menu + Items
		setJMenuBar(menuBar);
		menuBar.add(menuEntry1);

		// Setting up Menu Entries
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
		contentPane.setLayout(null);

		// Table row alignment of text
		rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		tree.setBounds(10, 11, 197, 643);
		tree.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(tree);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 665, 197, 315);
		contentPane.add(scrollPane_2);
		txtrUser = new JTextArea();
		scrollPane_2.setViewportView(txtrUser);
		txtrUser.setEditable(false);

		txtrUser.setText("User1");
		txtrUser.setBorder(BorderFactory.createEtchedBorder());

		JTextArea txtrText = new JTextArea();
		txtrText.setEditable(false);
		txtrText.setRows(1);
		txtrText.setText("Text");
		txtrText.setBounds(217, 958, 1677, 22);
		txtrText.setBorder(BorderFactory.createEtchedBorder());
		contentPane.add(txtrText);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(217, 0, 1677, 947);
		contentPane.add(tabbedPane);

		table = new JTable(200, ListManager.getColumnNameCount());
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);
		table.setRowHeight(25);

		// Table HEADER
		tableHead = table.getTableHeader();
		tabbedPane.addTab("New tab", null, scrollPane, null);

		scrollPane.setViewportView(table);

		JScrollPane scrollPane_1 = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane_1, null);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);

		// TODO
		tabbedPane.setTitleAt(0, "BLA");

		this.init();
	}

	/**
	 * initializes the logical stuff
	 */
	public void init() {
		// JTREE UPDATE STUFF! Call it to handle JTree Updates and Adds/Deletes
		tableColumnModel = tableHead.getColumnModel();
		Menu.tableRepaint();
		this.prepareTreeStuff(); // !!
		// ------------------------------Wird später
		// eingefügt---------------------------------- //
		// für kevin auskommentiert
		// Main.ct.transmit("!requestUserlistUpdate");
		// Main.ct.transmit("!requestDirectoryUpdate");
		// auskommentieren, wenn du kein kevin bist
		simulateTransmissions();
		/*
		 * EventQueue.invokeLater(new Runnable() { public void run() {
		 * while(true){ txtrUser.repaint();
		 * 
		 * } } });
		 */
		// -------------------------------------------------------------------------------------
		// //
	}

	public void clickOnTreeEntry() {

	}

	/**
	 * Create the Standard JTree Components
	 */
	public void prepareTreeStuff() {
		// The default Tree model to !!! UPDATE !!! the JTree
		DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
		// HEADER - Connect the header to the JTree
		DefaultMutableTreeNode topTable = (DefaultMutableTreeNode) model.getRoot();
		tree = new JTree(topTable);
		// Title of the First Table Content
		topTable.setUserObject("Benchmark Tree");
		// Remove all standard children first
		topTable.removeAllChildren();
		// topTable.add(first);
		// topTable.add(new DefaultMutableTreeNode("Second"));
		// topTable.add(new DefaultMutableTreeNode("Third"));
		// first.add(new DefaultMutableTreeNode("First Child"));
		// REFRESH!!!
		DefaultMutableTreeNode node = null;
		DefaultMutableTreeNode subNode = null;
		boolean whileBool = true;

		for (int i = 1; i < userlist2.length; i++) {
			if (userlist2[i].equals("*")) {
				node = new DefaultMutableTreeNode(userlist2[i + 1]);
				int whileIterator = i + 2;
				whileBool = true;
				while (whileBool) {
					if (!userlist2[whileIterator].equals("*") && !userlist2[whileIterator].equals("**")) {
						subNode = new DefaultMutableTreeNode(userlist2[whileIterator]);
						node.add(subNode);
						subnodeListEntry.add(subNode);
					} else {
						whileBool = false;
					}
					whileIterator++;
				}
				nodeListEntry.add(node);
				topTable.add(node);
			}
		}
		model.reload(topTable);

		tree.addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
				/* if nothing is selected */
				if (node == null) {
					System.err.println("NO");
					return;
				}
				/* retrieve the node that was selected */
				Object nodeInfo = node.getUserObject();
				System.err.println(node.getUserObject());

				/* React to the node selection. */
				System.err.println("CLICK");
			}
		});
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
			if (onlineUsers.get(i + 1).equals("Admin")) {
				admins.add(onlineUsers.get(i));
			} else {
				users.add(onlineUsers.get(i));
			}
		}
		for (int i = 0; i < admins.size(); i++) {
			adminString = adminString + "\n" + admins.get(i) + "\n";
		}
		for (int i = 0; i < users.size(); i++) {
			userString = userString + "\n" + users.get(i) + "\n";
		}
		finalString = adminString + "\n" + userString;
		activeUser = finalString;
		txtrUser.setText(finalString);
		txtrUser.repaint();
	}

	/**
	 * javadoc vergessen
	 */
	public void simulateTransmissions() {
		String response = "!updateOnlineUsers tolu Admin";
		String[] userlist = response.split(" ");
		updateUserList(userlist);
	}
}
