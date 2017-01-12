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
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MainMenu extends JFrame {
	
	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuEntry1 = new JMenu("Aktion");
	private JMenu menuEntry2 = new JMenu("Tabelle");
	private JMenu menuEntry3 = new JMenu("Admin");
	private JMenu menuEntry4 = new JMenu("Einstellungen");
	private JPanel contentPane;
	
	Object[] titles = {"#ID", "Datum", "Uhrzeit", "Name", "Zugabe", "Stoff", "Begründung"};
	DefaultTableModel dtm;
	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table = new JTable(200,7);

	//JScrollPane scrollPane = new JScrollPane(table);

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		//Screen Sizes etc
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
        int taskBarSize = scnMax.bottom;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, (int) screenSize.getWidth(), (int) screenSize.getHeight()-taskBarSize);
		setTitle("Programm");
		setLocationRelativeTo(null);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        	
		//Setting up the Menu + Items
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
        contentPane.setLayout(null);
        scrollPane.setBounds(10, 11, (int) screenSize.getWidth()-20, (int) screenSize.getHeight()-taskBarSize-75);
        
        contentPane.add(scrollPane);
        table.setRowHeight(30);
        table.setFillsViewportHeight(true);
        
        scrollPane.setViewportView(table);
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
}
