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
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

public class MainMenu extends JFrame {

	private JMenuBar menuBar = new JMenuBar();
	private JMenu menuEntry1 = new JMenu("Aktion");
	private JMenu menuEntry2 = new JMenu("Tabelle");
	private JMenu menuEntry3 = new JMenu("Admin");
	private JMenu menuEntry4 = new JMenu("Einstellungen");
	private JPanel contentPane;

	private final JScrollPane scrollPane = new JScrollPane();
	private final JTable table;

	// JScrollPane scrollPane = new JScrollPane(table);

	/**
	 * Create the frame.
	 */
	public MainMenu() {
		// Screen Sizes etc
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		Insets scnMax = Toolkit.getDefaultToolkit().getScreenInsets(getGraphicsConfiguration());
		int taskBarSize = scnMax.bottom;

		System.out.println("lul");

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
		scrollPane.setBounds(10, 11, (int) screenSize.getWidth() - 20, (int) screenSize.getHeight() - taskBarSize - 75);

		table = new JTable(200, ListManager.getColumnNameCount());
		contentPane.add(scrollPane);
		table.setRowHeight(30);
		table.setFillsViewportHeight(true);

		// Table HEADER
		JTableHeader th = table.getTableHeader();
		TableColumnModel tcm = th.getColumnModel();

		// Table row alignment of text
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(SwingConstants.RIGHT);
		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < ListManager.getColumnNameCount(); i++) {
			TableColumn tc = tcm.getColumn(i);
			tc.setHeaderValue(ListManager.getColumnNameElement(i));
			switch (i) {
			case 0:
				table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(0).setPreferredWidth(ListManager.getColumnWidthElement(0));
				break;
			case 1:
				table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
				break;
			case 2:
				table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
				break;
			case 3:
				table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
				break;
			case 4:
				table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
				break;
			case 5:
				table.getColumnModel().getColumn(5).setCellRenderer(leftRenderer);
				break;
			case 6:
				table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
				table.getColumnModel().getColumn(6).setPreferredWidth(ListManager.getColumnWidthElement(6));
				break;
			default:
				break;	
			}
		}
		th.repaint();
		table.setRowHeight(25);

		scrollPane.setViewportView(table);
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
	}
}
