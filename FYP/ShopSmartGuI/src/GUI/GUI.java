package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.FontUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;

import net.miginfocom.swing.MigLayout;



public class GUI {

	static int noOfRowsAdded;
	static int newRow;
	static int barcodeInDatabse;

	DefaultTableModel tableModel = new DefaultTableModel();

	JButton btnUpdateR;	
	JButton btnAddRow;	
	JButton btnUpdateB;		
	JButton btnUpdateM;		
	JButton btnUpdateOL;
	JButton btnUpdateD;
	JButton btnAddD;	
	JButton btnDeleteS;	
	JButton btnRefresh;	
	JButton btnAddB;	

	JTable tableSH;
	JTable tableR;
	JTable tableRNw;
	JTable tableM;
	JTable tableSL;
	JTable tableOL;
	JTable tableD;
	JTable tableB;

	JTextField newItemStock;
	JTextField newQtyStock;


	JTextField newBarcode;
	JTextField newItemPrice;
	JTextField newPrice;
	JTextField newStock;
	JTextField newX;
	JTextField newY;


	JTextField statusOut;

	JScrollPane scrollpaneSH;
	JScrollPane scrollpaneR;
	JScrollPane scrollpaneRNw;
	JScrollPane scrollpaneM;
	JScrollPane scrollpaneSL;
	JScrollPane scrollpaneOL;
	JScrollPane scrollpaneD;
	JScrollPane scrollpaneB;

	readData rd;

	JScrollPane scrollpaneFS;
	JScrollPane scrollpaneFSQ;

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
		btnUpdateD = new JButton("Update Delivery Times");
		btnDeleteS = new JButton("Delete");
		btnRefresh = new JButton("Refresh");
		btnAddB =  new JButton("Add New Item");

		newItemStock= new JTextField(29);
		statusOut  = new JTextField(35);
		newQtyStock = new JTextField(5);


		newBarcode = new JTextField(20);

		newItemPrice = new JTextField(32);
		newPrice = new JTextField(7);
		newStock = new JTextField(7);
		newX = new JTextField(5);
		newY = new JTextField(5);


		newItemStock.setBorder(  (BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(1, 5, 1, 1), null)));
		newQtyStock.setBorder(  (BorderFactory.createCompoundBorder(
				BorderFactory.createEmptyBorder(1, 5, 1, 1), null)));
		statusOut.setBorder(BorderFactory.createCompoundBorder(
				statusOut.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));

		newItemPrice.setBorder(BorderFactory.createCompoundBorder(
				newItemPrice.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		newItemStock.setBorder(BorderFactory.createCompoundBorder(
				newItemStock.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		newBarcode.setBorder(BorderFactory.createCompoundBorder(
				newBarcode.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		newPrice.setBorder(BorderFactory.createCompoundBorder(
				newPrice.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		newStock.setBorder(BorderFactory.createCompoundBorder(
				newStock.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		newX.setBorder(BorderFactory.createCompoundBorder(
				newX.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));
		newY.setBorder(BorderFactory.createCompoundBorder(
				newY.getBorder(),BorderFactory.createEmptyBorder(1, 5, 1, 1)));


		newItemPrice.setText("Enter Item Name");
		newBarcode.setText("Enter Barcode");
		newPrice.setText("Enter Price");
		newStock.setText("Enter Stock");
		newX.setText("X");
		newY.setText("Y");

		newItemStock.setText("Enter Item Name");
		statusOut.setText("ShopSmart Control Panel Started...");


		newQtyStock.setText("Qty");
		scrollpaneFS = new JScrollPane(newItemStock);
		scrollpaneFSQ = new JScrollPane(newQtyStock);

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
		JPanel status= new JPanel();
		JPanel statusRight= new JPanel();

		JPanel olOptions= new JPanel();

		JPanel border= new JPanel();
		JPanel border1= new JPanel();
		JPanel border2= new JPanel();
		JPanel border3= new JPanel();
		JPanel borderStatus= new JPanel();

		status.setBorder(BorderFactory.createTitledBorder("Status"));
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
		//borderStatus.setBorder(new EmptyBorder(0, 50,5,5));

		frame.getContentPane().setLayout(new MigLayout());

		try{

			tableSH = new JTable(rd.insertData("staffhours",2,7));

			tableR = new JTable(rd.insertData("rota",2,7));

			tableRNw = new JTable(rd.insertData("rotanw",2,7));

			tableM = new JTable(rd.insertData("manage",2,7));


			tableB = new JTable(rd.insertData("barcodes",2,8));	

			tableB.setPreferredScrollableViewportSize(tableB.getPreferredSize());
			tableB.getColumn("Barcode").setPreferredWidth(160);
			tableB.getColumn("Item").setPreferredWidth(400);

			tableB.getColumn("X").setPreferredWidth(60);
			tableB.getColumn("Y").setPreferredWidth(60);

			tableOL = new JTable(rd.insertData("orderlist",1,4));
			tableOL.getColumn("ID").setPreferredWidth(60);
			tableOL.getColumn("Qty").setPreferredWidth(60);
			tableOL.getColumn("Item").setPreferredWidth(400);

			tableSL = new JTable(rd.insertData("shoppinglists",2,5));
			tableSL.getColumn("ShoppingList").setPreferredWidth(350);
			tableSL.getColumn("UserName").setPreferredWidth(150);
		    tableSL.getColumn("DayTime").setPreferredWidth(150);

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
		scrollpaneOL.setPreferredSize(new Dimension(400,150));


		scrollpaneSL = new JScrollPane( tableSL);
		scrollpaneSL.setPreferredSize(new Dimension(400,215));


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

		orderList.setPreferredSize(new Dimension(420,240));
		orderList.add(scrollpaneOL);

		olOptions.setPreferredSize(new Dimension(408,55));

		scrollpaneFS.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollpaneFSQ.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		//scrollpaneFS.setBorder(null);

		btnDeleteS.setPreferredSize(new Dimension(120,23));

		olOptions.add(scrollpaneFS,BorderLayout.WEST);
		olOptions.add(scrollpaneFSQ,BorderLayout.WEST);
		olOptions.add(btnAddD,BorderLayout.WEST);
		olOptions.add(btnUpdateOL,BorderLayout.SOUTH);
		olOptions.add(btnDeleteS,BorderLayout.SOUTH);
		orderList.add(olOptions,BorderLayout.CENTER);


		left.setPreferredSize(new Dimension(440,700));

		left.add(staffHours,"wrap,gapbottom 5px");
		left.add(rota,"wrap ,growx,gapbottom 5px");
		left.add(rotaNw,"wrap,growx,gapbottom 5px");
		left.add(manage,"wrap,growx,gapbottom 5px");

		center.setPreferredSize(new Dimension(440,700));

		center.add(deliveries,"wrap,growx,gapbottom 5px");
		center.add(orderList,"wrap,gapbottom 5px");
		center.add(shoppingList,"wrap ,growx,gapbottom 5px");

		status.setPreferredSize(new Dimension(490,50));


		right.setPreferredSize(new Dimension(500,50));

		btnRefresh.setPreferredSize(new Dimension(120,22));

		status.add(statusOut,BorderLayout.WEST);
		status.add(borderStatus,BorderLayout.WEST);
		status.add(btnRefresh,BorderLayout.WEST);
		right.add(status,"wrap,gapbottom 5px");

		barcodes.setPreferredSize(new Dimension(490,610));

		btnAddB.setPreferredSize(new Dimension(90,23));
		btnUpdateB.setPreferredSize(new Dimension(90,23));


		barcodes.add(newBarcode,BorderLayout.WEST);
		barcodes.add(newItemPrice,BorderLayout.WEST);
		barcodes.add(newPrice,BorderLayout.SOUTH);
		barcodes.add(newStock,BorderLayout.SOUTH);
		barcodes.add(newX,BorderLayout.SOUTH);
		barcodes.add(newY,BorderLayout.SOUTH);
		barcodes.add(btnAddB,BorderLayout.SOUTH);
		barcodes.add(btnUpdateB,BorderLayout.SOUTH);

		right.add(barcodes);




		frame.add(BorderLayout.NORTH,border);
		frame.add(BorderLayout.WEST,border1);
		frame.add(BorderLayout.WEST,left);
		frame.add(BorderLayout.CENTER,center);
		frame.add(BorderLayout.EAST,right);
		//frame.add(BorderLayout.CENTER,status);



		btnUpdateR.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							statusOut.setText("");

							statusOut.setText("Rota Was Updated...");

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
							statusOut.setText("");

							statusOut.setText("Staff Operations Were Changed...");

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
							statusOut.setText("");

							statusOut.setText("Delivery Times Were Changed...");
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
							statusOut.setText("");

							statusOut.setText("Order List Updated...");
						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		btnUpdateB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							rd.getTable( tableB,5 );
							statusOut.setText("");

							statusOut.setText("Barcodes Updated...");
						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		btnAddB.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
						
							if(!newItemPrice.getText().equals("Enter Item Name") && !newBarcode.getText().equals("Enter Barcode")&& !newPrice.getText().equals("Enter Price") &&  !newStock.getText().equals("Enter Stock")&& !newX.getText().equals("X")&& !newY.getText().equals("Y"))
							{
								System.out.print("INNNNNNNNNN");
								rd.setUpNewItem(newItemPrice.getText(),newBarcode.getText(),newPrice.getText(),newStock.getText(),newX.getText(),newY.getText());
								if(barcodeInDatabse!=1)
								{
									statusOut.setText("");
									statusOut.setText("New Item Added To Barcodes List...");
									newItemPrice.setText("");
									newBarcode.setText("");
									newPrice.setText("");
									newStock.setText("");
									newX.setText("");
									newY.setText("");
									
									DefaultTableModel model = (DefaultTableModel) tableB.getModel();
									model.addRow(new Object[]{newItemPrice.getText(),newBarcode.getText(),newPrice.getText(),newStock.getText(),newX.getText(),newY.getText()});
								}
								else
								{
									statusOut.setText("");
									statusOut.setText("Barcode Is Allready In Databse,Use Update Instead...");

								}
							}
							else
							{
								statusOut.setText("");
								statusOut.setText("Please Change All Default Values...");
								
							}
						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		btnRefresh.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {

							frame.setVisible(false);
							
							
							//frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
							
							setUpGUI();

//							for (int i = 0; i < tableOL.getRowCount(); i++)
//								for(int j = 0; j < tableOL.getColumnCount(); j++) {
//									tableOL.setValueAt("", i, j);
//								}
							
//							frame.revalidate();
//							frame.repaint();

							//							orderList.revalidate();
							//							orderList.repaint();
							//							   					


						} catch (Exception ex) {

						}   
					}
				});

			}
		});
		btnDeleteS.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {


							rd.delteItemFS();

							statusOut.setText("");
							statusOut.setText("Order List Cleared...");

						}
						catch (Exception ex) {

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

							if(!newItemStock.getText().equals("Enter Item Name") && !newQtyStock.getText().equals("Qty"))
							{

								rd.addNew(newItemStock.getText(),newQtyStock.getText());

								if(newRow !=1)
								{
									statusOut.setText("");

									statusOut.setText("Item Was Added To Orderlist...");
									DefaultTableModel model = (DefaultTableModel) tableOL.getModel();
									model.addRow(new Object[]{noOfRowsAdded, newItemStock.getText(), newQtyStock.getText()});
								}
								else
								{
									statusOut.setText("");

									statusOut.setText("Item Allready In Orderlist,Please Use Update Instead...");

								}
							}
							else {

								statusOut.setText("");
								statusOut.setText("Please Enter Item Name and Qty. To Be Added...");

							}

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
