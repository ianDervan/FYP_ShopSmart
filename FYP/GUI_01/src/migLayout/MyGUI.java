package migLayout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Scrollbar;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;


public class MyGUI {

	JTextArea displayTextMain, displayTextSecondary;

	JButton buttGetFile;	
	JButton buttReverse;
	JButton buttSave;			
	JButton buttClearText;
	JButton buttEditText;			
	JButton buttSearch;
	JButton buttSelectAll;
	JButton buttUpperCase;	
	JButton buttPrint;
	JButton buttWordCount;			
	JButton buttDeleteFile;
	JButton buttLowerCase;			
	JButton buttCopyFile;
	JButton buttSomthingElse;
	
	JScrollPane sp1;
	JScrollPane sp2;
	
	JSlider slider;
	JComboBox comboBox;
	JComboBox comboBox1;
	JComboBox comboBox2;
	
	DefaultComboBoxModel model, model1, model2;
	
	JRadioButton rdbtn;
	JRadioButton rdbtn1;
	JRadioButton rdbtn2;

	JScrollBar scrollbar1;
	JScrollBar scrollbar2;
	
	protected int fontSize = 11;

	public MyGUI(){
		JFrame frame = new JFrame();
		frame.setSize(750,700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JPanel panelButtons1 = new JPanel();
		JPanel panelButtons2 = new JPanel();
		JPanel panelTextField = new JPanel();

		JPanel panelEditText= new JPanel();

		panelButtons1.setBorder(BorderFactory.createTitledBorder("Main Buttons"));
		panelButtons2.setBorder(BorderFactory.createTitledBorder("Secondary Buttons"));
		panelTextField.setBorder(BorderFactory.createTitledBorder("TextFields"));
		panelEditText.setBorder(BorderFactory.createTitledBorder("TextFields"));

		panelButtons1.setBorder(BorderFactory.createTitledBorder("Main Buttons"));
		panelButtons2.setBorder(BorderFactory.createTitledBorder("Secondary Buttons"));
		panelTextField.setBorder(BorderFactory.createTitledBorder("TextFields"));

		frame.getContentPane().setLayout(new MigLayout());
		frame.getContentPane().add(panelButtons1, "span, push, grow");
		frame.getContentPane().add(panelTextField, "span, push, grow");
		frame.getContentPane().add(panelEditText, "span, push, grow");
		frame.getContentPane().add(panelButtons2, "span, push, grow");

		panelButtons1.setLayout(new MigLayout());
		panelButtons2.setLayout(new MigLayout());
		panelTextField.setLayout(new MigLayout());
		panelEditText.setLayout(new MigLayout());
        
		buttGetFile = new JButton("Get File");	
		buttReverse = new JButton("Revese");
		buttSave = new JButton("Save");			
		buttClearText = new JButton("Clear Text");
		buttEditText = new JButton("Edit Text");			
		buttSearch = new JButton("Search");
		buttSelectAll = new JButton("Select All");
		buttUpperCase = new JButton("Upper Case");	
		buttPrint = new JButton("Print");
		buttWordCount = new JButton("Word Count");			
		buttDeleteFile = new JButton("Delete File");
		buttLowerCase = new JButton("LowerCase");			
		buttCopyFile = new JButton("Copy File");
		buttSomthingElse = new JButton("Somthing Else");

		displayTextMain = new JTextArea(30,30);

		model = new DefaultComboBoxModel();
		model.addElement("BLACK");
		model.addElement("RED");
		model.addElement("BLUE");
		model.addElement("GREEN");
		model.addElement("WHITE");
		model.addElement("ORANGE");
		model.addElement("BLACK");

		model1 = new DefaultComboBoxModel();
		model1.addElement("PLAIN     ");
		model1.addElement("BOLD");
		model1.addElement("ITALICS");
		
		model2 = new DefaultComboBoxModel();
		model2.addElement("VERDANDA");
		model2.addElement("ARIAL");
		model2.addElement("OPEN SANS");
		model2.addElement("TIMES NEW ROMAN");
		model2.addElement("SERIF");
		model2.addElement("Helvetica");

		comboBox = new JComboBox(model);
		comboBox1 = new JComboBox(model1);
		comboBox2 = new JComboBox(model2);
		
		slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);
		slider.setPreferredSize(new Dimension(100, 80));
		
		displayTextSecondary = new JTextArea(15,15);
		rdbtn = new JRadioButton("Font size 10");
		rdbtn1 = new JRadioButton("Font size 14");
		rdbtn2 = new JRadioButton("Font size 18");
		
		ButtonGroup group = new ButtonGroup();
		group.add(rdbtn);
		group.add(rdbtn1);
		group.add(rdbtn2);

		panelButtons1.setLayout(new MigLayout());
		panelButtons2.setLayout(new MigLayout());
		panelTextField.setLayout(new MigLayout());

		sp1 = new JScrollPane(displayTextMain);
        sp1.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		sp2 = new JScrollPane(displayTextSecondary);
        sp2.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
	

		slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 25);


		//trying to add panels of buttons as a separate panel so we can have 2 
		//panels of separate buttons then insert another panel containing the text field.
		
		panelButtons1.add(buttGetFile);
		panelButtons1.add(buttReverse);
		panelButtons1.add(buttSave);
		panelButtons1.add(buttClearText);
		panelButtons1.add(buttEditText);
		panelButtons1.add(buttSearch);
		panelButtons1.add(buttSelectAll); 
		panelButtons1.add(slider); 
		
		//panel containing text area inserted between the two panels of buttons


		//panelTextField.add(displayTextMain, "push, grow"); //textArea will grow when dragged

		panelTextField.add(sp1, "push, grow"); //textArea will grow when dragged


		//panelTextField.add(displayTextMain, "push, grow"); //textArea will grow when dragged
		

		panelTextField.add(sp1, "push, grow"); //textArea will grow when dragged




		panelEditText.add(rdbtn,"split 4, flowy, top, sgx" );
		panelEditText.add(rdbtn1,"sgx");
		panelEditText.add(rdbtn2,"sgx");
		panelEditText.add(slider,"sgx "); 

		panelEditText.add(comboBox,"split 3, flowy, top,gap 50px,gapbottom 20px, sgx"); 
		panelEditText.add(comboBox1,"gap 50px,gapbottom 20px,sgx"); 
		panelEditText.add(comboBox2,"gap 50px,sgx"); 

		panelEditText.add(sp2, " gap 50px,push, grow,wrap"); //textArea will grow when dragged

		//panelTextField.add(displayTextSecondary, "push, grow"); //textArea will grow when dragged

		panelButtons2.add(buttUpperCase);
		panelButtons2.add(buttPrint);
		panelButtons2.add(buttWordCount);
		panelButtons2.add(buttDeleteFile);
		panelButtons2.add(buttLowerCase);
		panelButtons2.add(buttCopyFile);
		panelButtons2.add(buttSomthingElse);

		frame.setVisible(true);
	}

}
