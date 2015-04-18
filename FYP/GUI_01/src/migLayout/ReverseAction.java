package migLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.SwingWorker;

public class ReverseAction extends SwingWorker<Object, Object>{
	private final Logger log = Logger.getLogger( ReverseAction.class.getName() );

	MyGUI mg = null;
	String nextLine = null;

	ArrayList<String> firstFile = new ArrayList<String>();


	public ReverseAction(MyGUI obj){
		mg = obj;
	}

	//read line method
	public BufferedReader lineRead (){	
		StringReader sr = new StringReader(mg.displayTextMain.getText()); 
		BufferedReader br = new BufferedReader(sr); 
		return br;			

	}	

	@Override
	protected Object doInBackground() throws Exception {
		// TODO Auto-generated method stub
		BufferedReader br = lineRead();					//the value of j determines which stream to use
		try{
			//mg.progress.setValue(10);
			while((nextLine = br.readLine()) != null){
				String[] tokens = nextLine.split("\\s+");

				for(int i = tokens.length - 1; i >= 0; i--)
				{
					firstFile.add(tokens[i]);							
				}
				firstFile.add("\n");

			}
			mg.displayTextMain.setText("");
			for(int i = 0; i < firstFile.size(); i++){
				mg.displayTextMain.append(firstFile.get(i) + " ");
				//mg.progress.setValue(50);
			}
		}catch(IOException ex){
			log.log( Level.SEVERE, ex.toString(), ex );
		}finally{
			try {
				br.close();
				//mg.progress.setValue(100);
			} catch (IOException ex) {
				log.log( Level.SEVERE, ex.toString(), ex );
			}

		}					
		return null;
	}

}

