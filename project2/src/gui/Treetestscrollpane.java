package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.JScrollPane;
import javax.swing.JTree;

public class Treetestscrollpane extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public Treetestscrollpane(JTree tree, ArrayList<String> halls, ArrayList<String> baths, ArrayList<String> substances) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 240);
		tree.setBounds(10, 11, 414, 240);
		
		contentPane.add(tree);
		
		tree.setCellRenderer(new DefaultTreeCellRenderer() {
            private ImageIcon baseIcon = new ImageIcon("resources/base.jpg");
            private ImageIcon hallIcon = new ImageIcon("resources/hall.png");
            private ImageIcon tableIcon = new ImageIcon("resources/table.png");
            private ImageIcon tubeIcon = new ImageIcon("resources/tube.png");
            
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
                	setIcon(tableIcon);
                }
                
                if(substances.contains(value.toString())){
                	setIcon(tubeIcon);
                }

                
                return c;
            }
        });
		
		
		//scrollPane.setViewportView(tree);
		
		this.setVisible(true);
	}
}
