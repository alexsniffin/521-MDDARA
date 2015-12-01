package project;


import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DocumentLoader {
	private JFileChooser fc;
	private JTextArea text;
	private File mddaraFile;
	private FileReader location;
	
	public DocumentLoader (){
		fc= new JFileChooser();
		fc.addChoosableFileFilter(new Filter());
		fc.setAcceptAllFileFilterUsed(false);
	}
	
	
	public File getFile() {
		int loadVal = fc.showOpenDialog(null);

        if (loadVal == JFileChooser.APPROVE_OPTION) {
        	
        	mddaraFile = fc.getSelectedFile();
            return mddaraFile;
        }
        return null;
	}    
	
public void loadFile(){
	
	try {

		location = new FileReader(getFile());

		} catch (FileNotFoundException e1) {

		System.err.println("File not found");

		}

	if (location!=null){
int i=0;
String Name;
int SSN;
String [] CompoundName= new String[50];
int [] CompoundValue= new int[50];
try(BufferedReader b = new BufferedReader(location))
{
	String line;
	Name=b.readLine();
	System.out.println(Name);
	SSN=Integer.parseInt(b.readLine());
	System.out.println(SSN);
	while ((line=b.readLine())!=null)
	{
		String[] tokens= line.trim().split("\\t");
		CompoundName[i]=(tokens[0]);
		System.out.println(CompoundName[i]);
		int Value= Integer.parseInt(tokens[1]);
		CompoundValue[i]=(Value);
		System.out.println(CompoundValue[i]);
		
		i++;
	}
	b.close();
}
	
catch (IOException e){
	e.printStackTrace();
}
	}
}


		public class Filter extends FileFilter {

		   public boolean accept(File f) {
		    
		       if (f.isDirectory())
		           return true;

		       String extension = Utils.getExtension(f);
		       if (extension != null)
		           if (extension.equals(Utils.mddara))
		               return true;
		           else
		               return false;

		       return false;
		   }

		   public String getDescription() {
		       return "text file (.mddara)";
		   }
		}
		
		
		public static class Utils {
			
		    public final static String mddara = "mddara";
		 
		    /*
		     * Get the extension of a file.
		     */
		    public static String getExtension(File f) {
		        String ext = null;
		        String s = f.getName();
		        int i = s.lastIndexOf('.');
		 
		        if (i > 0 &&  i < s.length() - 1) {
		            ext = s.substring(i+1).toLowerCase();
		        }
		        return ext;
		    }
		}
		
	}



