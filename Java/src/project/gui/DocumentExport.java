package project.gui;
import java.io.*;
public class DocumentExport {
 
        private  int DoctorID;
        private  int DocumentType;
        private  String Name;
        private  int SSN;
        private  String [] CompoundName;
        private  int CompoundValue;
        private  String [] MeasurementType;
       
        public DocumentExport(int DocID, int DocumentType, String Name, int Social, String [] CompoundName, int CompoundValue, String [] MeasurementType)
        {
                this.DoctorID=DocID;
                this.DocumentType=DocumentType;
                this.Name=Name;
                this.SSN=Social;
                this.CompoundName=CompoundName;
                this.CompoundValue=CompoundValue;
                this.MeasurementType=MeasurementType;
        }
       
        public  boolean exportData() throws IOException
        {
               
                File documentsDirectory= new File("user.home");
                boolean DirectoryExists=documentsDirectory.exists();
               
               
                        if (DirectoryExists!=true)
                        {
                        File newDirectory= (new File("user.home\\Medical_Documents"));
                        newDirectory.mkdirs();
                        DirectoryExists=true;
                        }
               
               
                if (DirectoryExists==true)
                {
                        System.out.println("This directory exists");
                        PrintWriter Exporter= new PrintWriter("user.home\\Medical_Documents\\" +DoctorID+ SSN + DocumentType +"med.mddara");
                        Object [] Data= new Object[1000];
                        for(int i=0; i<Data.length; i++)
                        {
                                if (Data[i]==null)
                                {
                                        break;
                                }
                                if(i%7==0)
                                {
                                        Data[i]=DoctorID;
                                }
                               
                                if(i%7==1)
                                {
                                        Data[i]=DocumentType;
                                }
                               
                                if(i%7==2)
                                {
                                        Data[i]=Name;
                                }
                               
                                if(i%7==3)
                                {
                                        Data[i]=SSN;
                                }
                               
                                if(i%7==4)
                                {
                                        Data[i]=CompoundName;
                                }
                               
                                if(i%7==5)
                                {
                                        Data[i]=CompoundValue;
                                }
                               
                                if(i%7==6)
                                {
                                        Data[i]=MeasurementType;
                                }
                       
                                for(int j=0; j<Data.length; j++)
                                {
                                        if (j%7==0)
                                        {
                                                Exporter.print((Integer)Data[j]);
                                        }
                                        if (j%7==1)
                                        {
                                                Exporter.print((Integer)Data[j]);
                                        }
                                        if (j%7==2)
                                        {
                                                Exporter.print((String)Data[j]);
                                        }
                                        if (j%7==3)
                                        {
                                                Exporter.print((Integer)Data[j]);
                                        }
                                        if (j%7==4)
                                        {
                                                Exporter.print((String)Data[j]);
                                        }
                                        if (j%7==5)
                                        {
                                                Exporter.print((Integer)Data[j]);
                                        }
                                        if (j%7==6)
                                        {
                                                Exporter.print((String)Data[j]);
                                        }
                                       
                                        if (Data[j]==null)
                                        {
                                                Exporter.close();
                                                break;
                                        }
                                }
                        }
                       
                       
                }
               
                return true;
        }
        }