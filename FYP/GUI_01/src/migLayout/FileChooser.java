package migLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFileChooser;
import javax.swing.SwingWorker;

public class FileChooser extends SwingWorker<Object, Object>{
	private final Logger log = Logger.getLogger( FileChooser.class.getName() );

	MyGUI mg = null;
	String nextLine = null;	


	public FileChooser(MyGUI obj)
	{
		mg = obj;
	}

	//void selectFile() throws IOException{
	@Override
	protected Object doInBackground() throws Exception {
		BufferedReader br = null;

		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setMultiSelectionEnabled(true);


		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

			File[] files = fileChooser.getSelectedFiles();
			//for loop
			for(int i = 0; i < files.length; i++){
				try{
					br = new BufferedReader(new FileReader(files[i]));
				}				
				catch(Exception ex){
					log.log( Level.SEVERE, ex.toString(), ex );
				}
				try{
					//mg.progress.setValue(20);
					while((nextLine = br.readLine()) != null)
					{
						mg.displayTextMain.append(nextLine + " \n");		//Print out file contents to textarea

					}
				}catch(IOException ex){
					log.log( Level.SEVERE, ex.toString(), ex );
				}finally{
					br.close();	
					//mg.progress.setValue(100);
				}
			}
		}
		else {			
			mg.displayTextMain.setText("No file selected");
		}
		return null;
	}
}