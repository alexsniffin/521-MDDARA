package project.gui;


import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Loads in a document from a .mddara file type
 * 
 * @project 521_Project
 * @author Alexander Sniffin, William Craft
 * @date Dec 1, 2015
 */
public class DocumentLoader {
	
	private String Name;
	private int SSN;
	private int doctorID;
	private int documentType;
	private String [] CompoundName= new String[50];
	private int [] CompoundValue= new int[50];
	private String[] measurementType = new String[50];


	private JFileChooser fc;
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
	
		if (location!=null) {
			int i=0;
	
			try(BufferedReader b = new BufferedReader(location))
			{
				String line;
				
				doctorID=Integer.parseInt(b.readLine());
				documentType=Integer.parseInt(b.readLine());
				Name=b.readLine();
				SSN=Integer.parseInt(b.readLine());
				while ((line=b.readLine())!=null)
				{
					String[] tokens= line.trim().split("\\t");
					CompoundName[i]=(tokens[0]);
					int Value= Integer.parseInt(tokens[1]);
					CompoundValue[i]=(Value);
					measurementType[i]=(tokens[2]);
					i++;
				}
				b.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public int getSSN() {
		return SSN;
	}


	public void setSSN(int sSN) {
		SSN = sSN;
	}


	public int getDoctorID() {
		return doctorID;
	}


	public void setDoctorID(int doctorID) {
		this.doctorID = doctorID;
	}


	public int getDocumentType() {
		return documentType;
	}


	public void setDocumentType(int documentType) {
		this.documentType = documentType;
	}


	public String[] getCompoundName() {
		return CompoundName;
	}


	public void setCompoundName(String[] compoundName) {
		CompoundName = compoundName;
	}


	public int[] getCompoundValue() {
		return CompoundValue;
	}


	public void setCompoundValue(int[] compoundValue) {
		CompoundValue = compoundValue;
	}
	
	public String[] getMeasurementType() {
		return measurementType;
	}


	public void setMeasurementType(String[] measurementType) {
		this.measurementType = measurementType;
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