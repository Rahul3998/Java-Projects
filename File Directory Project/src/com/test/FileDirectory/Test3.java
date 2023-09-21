package com.test.FileDirectory;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Test3 extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test3 frame = new Test3();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Test3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 444);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 416, 385);
		contentPane.add(scrollPane);
		TableModel tm = new TableModel();
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(tm);
		
		
	}

	class TableModel extends DefaultTableModel{
		
		public TableModel() {
			List<String[]> list = new ArrayList<>();
			String[] ex1 = {"1","XYZ"};
			String[] ex2 = {"2","ABS"};
			list.add(ex1);
			list.add(ex2);
			setColumnIdentifiers(new String[] {"Srno","Data"});
			for(int i=0 ; i<list.size() ; i++) {
				addRow(list.get(i));
			}
		}
		
		
	}
}
