package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class Main extends JFrame {
	
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
	public Main() {
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
		menuBar.add(menuEntry2);	
		menuBar.add(menuEntry3);	
		menuBar.add(menuEntry4);

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
