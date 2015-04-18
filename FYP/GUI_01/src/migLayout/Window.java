package migLayout;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class Window {

	private MyGUI mg;
	private FileChooser fc;
	private ReverseAction ra;
	private SaveAction sa;
	private CountAction ca;
	private UpperAction ua;
	private LowerAction la;

	private final Logger log = Logger.getLogger(Window.class.getName());

	public void openWindow(){

		mg = new MyGUI();	
		fc = new FileChooser(mg);
		ra = new ReverseAction(mg);
		sa = new SaveAction(mg);
		ca = new CountAction(mg);
		ua = new UpperAction(mg);
		la = new LowerAction(mg);
		


		mg.buttGetFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							fc.run();
							mg.displayTextSecondary.append("Attained text file\n");
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							log.log( Level.SEVERE, ex.toString(), ex );
						}   
					}
				});

			}
		});

		mg.buttReverse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							ra.run();
							mg.displayTextSecondary.append("Text Reversed\n");
							//mg.progress.setValue(0);
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							log.log( Level.SEVERE, ex.toString(), ex );
						} 
					}
				});
			}
		} );

		mg.buttClearText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							mg.displayTextMain.setText("");
							mg.displayTextSecondary.setText("");
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							log.log( Level.SEVERE, ex.toString(), ex );
						} 
					}
				});
			}
		} );
		
		mg.buttUpperCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						
						ua.run();
					}
				});
			}
		} );


		mg.buttSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							sa.run();
							mg.displayTextSecondary.append("File Saved\n");
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							log.log( Level.SEVERE, ex.toString(), ex );
						} 
					}
				});
			}
			
		} );
		mg.buttPrint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							PrinterJob pj = PrinterJob.getPrinterJob();
						
							mg.displayTextSecondary.append("Printer opened\n");
							    if (pj.printDialog()) {
							        try {pj.print();}
							        catch (PrinterException exc) {
							            System.out.println(exc);
							         }
							     }   
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							log.log( Level.SEVERE, ex.toString(), ex );
						} 
					}
				});
			}
			
		} );
		mg.buttWordCount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							ca.run();
							    
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							log.log( Level.SEVERE, ex.toString(), ex );
						} 
					}
				});
			}
			
		} );
		mg.buttLowerCase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{ 
				SwingUtilities.invokeLater(new Runnable(){
					public void run(){
						try {
							
							la.run();
							    
						} catch (Exception ex) {
							// TODO Auto-generated catch block
							log.log( Level.SEVERE, ex.toString(), ex );
						} 
					}
				});
			}
			
		} );
		
			
		

		mg.comboBox.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){

						String colours = (String) mg.comboBox.getSelectedItem();
						System.out.println(colours);
						if(colours.equals("BLACK"))
						{
							mg.displayTextMain.setForeground(Color.BLACK);
							mg.displayTextSecondary.append("Font colour set to Black\n");

						}
						else if(colours.equals("RED"))
						{
							mg.displayTextMain.setForeground(Color.RED);
							mg.displayTextSecondary.setForeground(Color.RED);
							mg.displayTextSecondary.append("Font colour set to Red\n");
						}
						else if(colours.equals("BLUE"))
						{
							mg.displayTextMain.setForeground(Color.BLUE);
							mg.displayTextSecondary.setForeground(Color.BLUE);
							mg.displayTextSecondary.append("Font colour set to Blue\n");
						}
						else if(colours.equals("GREEN"))
						{
							mg.displayTextMain.setForeground(Color.GREEN);
							mg.displayTextSecondary.setForeground(Color.GREEN);
							mg.displayTextSecondary.append("Font colour set to Green\n");
						}
						else if(colours.equals("WHITE"))
						{
							mg.displayTextMain.setForeground(Color.WHITE);
							mg.displayTextSecondary.setForeground(Color.WHITE);
							mg.displayTextSecondary.append("Font colour set to White\n");
						}
						else if(colours.equals("ORANGE"))
						{
							mg.displayTextMain.setForeground(Color.ORANGE);
							mg.displayTextSecondary.setForeground(Color.ORANGE);
							mg.displayTextSecondary.append("Font colour set to Orange\n");
						}

					}
				}            
				);

		mg.comboBox1.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){

						String fontType = (String) mg.comboBox1.getSelectedItem();

						if(fontType.equals("PLAIN     "))
						{

							mg.displayTextMain.setFont(mg.displayTextMain.getFont().deriveFont(Font.PLAIN));
						//	mg.displayTextSecondary.setFont(mg.displayTextMain.getFont().deriveFont(Font.PLAIN));
							mg.displayTextSecondary.append("Font text set to Plain\n");

						}
						else if(fontType.equals("ITALICS"))
						{
							mg.displayTextMain.setFont(mg.displayTextMain.getFont().deriveFont(Font.ITALIC));
						//	mg.displayTextSecondary.setFont(mg.displayTextMain.getFont().deriveFont(Font.ITALIC));
							mg.displayTextSecondary.append("Font text set to ITALIC\n");
						}
						else if(fontType.equals("BOLD"))
						{
							mg.displayTextMain.setFont(mg.displayTextMain.getFont().deriveFont(Font.BOLD));
						//	mg.displayTextSecondary.setFont(mg.displayTextMain.getFont().deriveFont(Font.BOLD));
							mg.displayTextSecondary.append("Font text set to BOLD\n");

						}


					}
				}            
				);

		mg.comboBox2.addActionListener(
				new ActionListener(){
					public void actionPerformed(ActionEvent e){

						String fontType = (String) mg.comboBox2.getSelectedItem();
						System.out.println(fontType);
						if(fontType.equals("VERDANDA"))
						{
							Font font = new Font("Verdanda", Font.PLAIN, 12);
							mg.displayTextMain.setFont(font);
							mg.displayTextSecondary.setFont(font);
							mg.displayTextSecondary.append("Font type set to Verdanda\n");
						}
						else if(fontType.equals("ARIAL"))
						{
							Font font = new Font("arial", Font.PLAIN, 12);
							mg.displayTextMain.setFont(font);
							mg.displayTextSecondary.setFont(font);
							mg.displayTextSecondary.append("Font type set to Arial\n");
						}
						else if(fontType.equals("OPEN SANS"))
						{
							Font font = new Font("open sans", Font.PLAIN, 12);
							mg.displayTextMain.setFont(font);
							mg.displayTextSecondary.setFont(font);
							mg.displayTextSecondary.append("Font type set to Open Sans\n");
						}
						else if(fontType.equals("TIMES NEW ROMAN"))
						{
							Font font = new Font("times new roman", Font.PLAIN, 12);
							mg.displayTextMain.setFont(font);
							mg.displayTextSecondary.setFont(font);
							mg.displayTextSecondary.append("Font type set to Times New Roman\n");
						}
						else if(fontType.equals("SERIF"))
						{
							Font font = new Font("Serif", Font.PLAIN, 12);
							mg.displayTextMain.setFont(font);
							mg.displayTextSecondary.setFont(font);
							mg.displayTextSecondary.append("Font type set to Serif\n");
						}
						else if(fontType.equals("Helvetica"))
						{
							Font font = new Font("Helvetica", Font.PLAIN, 12);
							mg.displayTextMain.setFont(font);
							mg.displayTextSecondary.setFont(font);
							mg.displayTextSecondary.append("Font type set to Helvetica\n");
						}

					}
				}            
				);


		



	

		mg.rdbtn.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Do something here...
				mg.fontSize =10;
				System.out.println(mg.fontSize);

				Font font = new Font("", Font.BOLD, mg.fontSize);
				mg.displayTextMain.setFont(font);
				
				

			}
		});

		mg.rdbtn1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Do something here...
				mg.fontSize =14;

				Font font = new Font("", Font.BOLD, mg.fontSize);
				mg.displayTextMain.setFont(font);
				
			
			}
		});
		mg.rdbtn2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// Do something here...
				mg.fontSize =18;

				Font font = new Font("", Font.BOLD,mg.fontSize);
				mg.displayTextMain.setFont(font);
				
				
			}
		});



		mg.slider.setMinorTickSpacing(2);
		mg.slider.setMajorTickSpacing(10);
		mg.slider.setPaintTicks(true);
		mg.slider.setPaintLabels(true);

		mg.slider.setLabelTable(mg.slider.createStandardLabels(10));

		mg.displayTextMain.append("Hello now");



		mg.slider.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {

				mg.fontSize = mg.slider.getValue();


				if(mg.fontSize>11)
				{
					//mg.displayTextMain.setFont(mg.displayTextMain.getFont().deriveFont(mg.fontSize));
					Font font = new Font("", Font.PLAIN,mg.fontSize);
					mg.displayTextMain.setFont(font);
					//mg.displayTextSecondary.setFont(font);
					//mg.displayTextMain.setForeground(Color.BLUE);

				}

			}



		});


	}
}

