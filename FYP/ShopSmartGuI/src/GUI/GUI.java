package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;



public class GUI {

	static int noOfRowsAdded;

	JButton btnUpdateR;	
	JButton btnAddRow;	
	JButton btnUpdateB;		
	JButton btnUpdateM;		
	JButton btnUpdateOL;
	JButton btnUpdateD;
	JButton btnAddD;	

	JTable tableSH;
	JTable tableR;
	JTable tableRNw;
	JTable tableM;
	JTable tableSL;
	JTable tableOL;
	JTable tableD;
	JTable tableB;

	JScrollPane scrollpaneSH;
	JScrollPane scrollpaneR;
	JScrollPane scrollpaneRNw;
	JScrollPane scrollpaneM;
	JScrollPane scrollpaneSL;
	JScrollPane scrollpaneOL;
	JScrollPane scrollpaneD;
	JScrollPane scrollpaneB;

	readData rd;




	public void setUpGUI() {

		JFrame frame = new JFrame("ShopSmart Control panel");

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setSize(screenSize.width, screenSize.height);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		rd= new readData();

		btnAddD = new JButton("Add New Item");
		btnUpdateB = new JButton("Update Barcodes");
		btnUpdateD = new JButton("Update Delivery Times");
		btnUpdateOL = new JButton("Update Order List");
		btnUpdateR = new JButton("Update Rota");
		btnAddRow = new JButton("Add Row");
		btnUpdateM = new JButton("Update Staff Operations");

		//JPanel rotaB = new JPanel();
		//rotaB.add(btnUpdateR);


		JPanel left= new JPanel(new MigLayout());
		JPanel center= new JPanel(new MigLayout());
		JPanel right= new JPanel(new MigLayout());

		JPanel staffHours= new JPanel();
		JPanel rota= new JPanel();
		JPanel rotaNw= new JPanel();
		JPanel manage= new JPanel();
		JPanel deliveries= new JPanel();
		JPanel orderList= new JPanel();
		JPanel shoppingList= new JPanel();
		JPanel barcodes= new JPanel();

		JPanel border= new JPanel();
		JPanel border1= new JPanel();
		JPanel border2= new JPanel();
		JPanel border3= new JPanel();

		staffHours.setBorder(BorderFactory.createTitledBorder("Staff Hours"));
		rota.setBorder(BorderFactory.createTitledBorder("Rota"));
		rotaNw.setBorder(BorderFactory.createTitledBorder("Rota Next Week"));
		manage.setBorder(BorderFactory.createTitledBorder("Manage"));
		barcodes.setBorder(BorderFactory.createTitledBorder("Barcodes"));
		orderList.setBorder(BorderFactory.createTitledBorder("Order List"));
		shoppingList.setBorder(BorderFactory.createTitledBorder("Pre-Orderdered Shopping Lists"));
		deliveries.setBorder(BorderFactory.createTitledBorder("Deliveries"));

		border.setBorder(new EmptyBorder(0, 0,0,0));
		border1.setBorder(new EmptyBorder(0, 0,5,5));
		border2.setBorder(new EmptyBorder(0, 0,5,5));
		border3.setBorder(new EmptyBorder(0, 0,5,5));

		frame.getContentPane().setLayout(new MigLayout());

		try{

			tableSH = new JTable(rd.insertData("staffhours",2,7));

			tableR = new JTable(rd.insertData("rota",2,7));

			tableRNw = new JTable(rd.insertData("rotanw",2,7));

			tableM = new JTable(rd.insertData("manage",2,7));


			tableB = new JTable(rd.insertData("barcodes",1,7));	

			tableB.setPreferredScrollableViewportSize(tableB.getPreferredSize());
			tableB.getColumn("barcode").setPreferredWidth(160);
			tableB.getColumn("item").setPreferredWidth(400);

			tableB.getColumn("X").setPreferredWidth(60);
			tableB.getColumn("Y").setPreferredWidth(60);

			tableOL = new JTable(rd.insertData("orderlist",1,4));
			tableOL.getColumn("ID").setPreferredWidth(60);
			tableOL.getColumn("Qty").setPreferredWidth(60);
			tableOL.getColumn("Item").setPreferredWidth(400);

			tableSL = new JTable(rd.insertData("shoppinglists",3,5));
			tableSL.getColumn("ShoppingList").setPreferredWidth(400);
			tableSL.getColumn("CustomerName").setPreferredWidth(150);

			tableD = new JTable(rd.insertData("deliveries",2,8));

		} catch (SQLException e) {
			e.printStackTrace();
		}


		scrollpaneSH = new JScrollPane( tableSH );
		scrollpaneSH.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollpaneSH.setPreferredSize(new Dimension(400,90));


		scrollpaneR = new JScrollPane( tableR );
		scrollpaneR.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollpaneR.setPreferredSize(new Dimension(400,138));


		scrollpaneRNw = new JScrollPane( tableRNw );
		scrollpaneRNw.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollpaneRNw.setPreferredSize(new Dimension(400,138));


		scrollpaneM = new JScrollPane( tableM );
		scrollpaneM.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollpaneM.setPreferredSize(new Dimension(400,90));



		scrollpaneB= new JScrollPane( tableB );

		scrollpaneOL = new JScrollPane( tableOL );
		scrollpaneOL.setPreferredSize(new Dimension(400,165));


		scrollpaneSL = new JScrollPane( tableSL);
		scrollpaneSL.setPreferredSize(new Dimension(400,225));


		scrollpaneD = new JScrollPane( tableD);
		scrollpaneD.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		scrollpaneD.setPreferredSize(new Dimension(400,106));

		staffHours.add(scrollpaneSH);

		rota.setPreferredSize(new Dimension(350,200));
		rota.add(scrollpaneR);
		rota.add(btnUpdateR,BorderLayout.CENTER);

		rotaNw.add(scrollpaneRNw);

		manage.setPreferredSize(new Dimension(350,150));
		manage.add(scrollpaneM);
		manage.add(btnUpdateM,BorderLayout.CENTER);

		barcodes.add(scrollpaneB);




		shoppingList.add(scrollpaneSL);

		deliveries.setPreferredSize(new Dimension(400,165));
		deliveries.add(scrollpaneD);
		deliveries.add(btnUpdateD,BorderLayout.CENTER);

		orderList.setPreferredSize(new Dimension(420,230));
		orderList.add(scrollpaneOL);
		orderList.add(btnAddRow,BorderLayout.WEST);
		orderList.add(btnAddD,BorderLayout.WEST);
		orderList.add(btnUpdateOL,BorderLayout.WEST);
		


		left.setPreferredSize(new Dimension(440,700));

		left.add(staffHours,"wrap,gapbottom 5px");
		left.add(rota,"wrap ,growx,gapbottom 5px");
		left.add(rotaNw,"wrap,growx,gapbottom 5px");
		left.add(manage,"wrap,growx,gapbottom 5px");

		center.setPreferredSize(new Dimension(440,700));

		center.add(deliveries,"wrap,growx,gapbottom 5px");
		center.add(orderList,"wrap,gapbottom 5px");
		center.add(shoppingList,"wrap ,growx,gapbottom 5px");

		right.add(barcodes);

		frame.add(BorderLayout.NORTH,border);
		frame.add(BorderLayout.WEST,border1);
		frame.add(BorderLayout.WEST,left);
		frame.add(BorderLayout.CENTER,center);
		frame.add(BorderLayout.EAST,right);

		btnUpdateR.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {

							rd.getTable( tableR,1 );
						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		btnUpdateM.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {

							rd.getTable( tableM,2 );
						} catch (Exception ex) {

						}   
					}
				});

			}
		});

		btnUpdateD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {

							rd.getTable( tableD,3 );
						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		btnUpdateOL.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {

							rd.getTable( tableOL,4 );
						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		
		btnAddRow.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							
							
							//System.out.print(" ADDED"+ 	noOfRowsAdded);
							rd.addRow();
							DefaultTableModel model = (DefaultTableModel) tableOL.getModel();
							model.addRow(new Object[]{noOfRowsAdded, "", ""});
						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		btnAddD.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							//rd.getTable( tableOL,5);
						} catch (Exception ex) {

						}   
					}
				});

			}
		});

		frame.setVisible(true);

		//staffhoursD.getSHData();;
	}



}
