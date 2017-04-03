package gui;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import data.ListManager;

import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTree;
import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

public class Menu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuEntry1 = new JMenu("Aktion");
	private JMenu menuEntry2 = new JMenu("Tabelle");
	private JMenu menuEntry3 = new JMenu("Admin");
	private JMenu menuEntry4 = new JMenu("Einstellungen");
	private JPanel contentPane;
	private JTree tree = new JTree();
	private DefaultTreeModel model;
	private DefaultMutableTreeNode topTable;
	
	private final JScrollPane scrollPane = new JScrollPane();
	private static JTable table;
	private static TableColumnModel tableColumnModel;
	private static JTableHeader tableHead;
	private static DefaultTableCellRenderer rightRenderer, leftRenderer, centerRenderer;

	/**
	 * Create the frame from the Constructor.
	 */
	public Menu() {
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
		contentPane.add(tree);
		
		JTextArea txtrUser = new JTextArea();
		txtrUser.setText("User1");
		txtrUser.setBounds(10, 665, 197, 315);
		contentPane.add(txtrUser);
		
		JTextArea txtrText = new JTextArea();
		txtrText.setEditable(false);
		txtrText.setRows(1);
		txtrText.setText("Text");
		txtrText.setBounds(217, 958, 1677, 22);
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
		
		tableColumnModel = tableHead.getColumnModel();
		Menu.tableRepaint();
		
		//prepare tree stuff
		this.prepareTreeStuff();
	}
	
	/**
	 * Create the Standard JTree Components
	 */
	public void prepareTreeStuff(){
		//The default Tree model to !!! UPDATE !!! the JTree
		DefaultTreeModel model = (DefaultTreeModel)tree.getModel();	
		//HEADER - Connect the header to the JTree
		DefaultMutableTreeNode topTable = (DefaultMutableTreeNode)model.getRoot();
		tree = new JTree(topTable);		
		//Title of the First Table Content
		topTable.setUserObject("Benchmark Tree");	
		//Remove all standard children first
		topTable.removeAllChildren();	
		//NODES / LEAFS	
		DefaultMutableTreeNode first = new DefaultMutableTreeNode("First");
		topTable.add(first);
		topTable.add(new DefaultMutableTreeNode("Second"));
		topTable.add(new DefaultMutableTreeNode("Third"));
		first.add(new DefaultMutableTreeNode("First Child"));
		//REFRESH!!!
		model.reload(topTable);
	}
	
	/**
	 * Call it to refresh the JTree Components
	 */
	public void refreshTree(){
		model.reload(topTable);
	}

	/**
	 * Use this function after you changed values in the ListManager class.
	 * It repaints the table and deletes all data.
	 * SAVE it before call this function.
	 */
	public static void tableRepaint() {
		// Main Table Routine
		for (int i = 0; i < ListManager.getColumnNameCount(); i++) {
			if(tableColumnModel != null){
				TableColumn tc = tableColumnModel.getColumn(i);
				tc.setHeaderValue(ListManager.getColumnNameElement(i));
			}	
			//Set the width of the columns
			table.getColumnModel().getColumn(i).setPreferredWidth(ListManager.getColumnWidthElement(i));
			
			//Set the Alignment of the columns
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
			table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
			table.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);
		}
		tableHead.repaint();
	}
}
