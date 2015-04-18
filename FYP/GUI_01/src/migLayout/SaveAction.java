package migLayout;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;


import java.io.IOException;

import javax.swing.SwingWorker;


public class SaveAction extends SwingWorker<Object, Object>{
	
	MyGUI mg;
	
	public SaveAction(MyGUI obj){
		mg = obj;	
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		FileOutputStream fop = null;
		File file;
		String content = mg.displayTextMain.getText().toString();
		try {
 
			file = new File("C:/results.txt");
			fop = new FileOutputStream(file);
 
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			// get the content in bytes
			byte[] contentInBytes = content.getBytes();
 
			fop.write(contentInBytes);
			fop.flush();
			fop.close();
 
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fop != null) {
					fop.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
