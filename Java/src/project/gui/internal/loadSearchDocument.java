package project.gui.internal;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import project.User;

public class LoadSearchDocument extends FrameType
{
	private SearchDocument sDoc;
	
	public LoadSearchDocument(User user, String name, int x, int y, int width,
			int height, SearchDocument sDoc) {
		super(user, name, x, y, width, height);
		this.sDoc = sDoc;
		createComponants();
	
	}

	int count = 1;
	
	@Override
	public void createComponants() 
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{10, 10};
		int [] rowHeights = new int[2+sDoc.getDocID().size()];
		double [] rowWeights = new double [2+sDoc.getDocID().size()];
		for (int i=0; i <rowHeights.length; i++)
		{
			rowHeights[i] =10;
			rowWeights[i] = 0.0;
		}
		rowWeights [2 + sDoc.getDocID().size() - 1]= Double.MIN_VALUE;
		gridBagLayout.rowHeights = rowHeights;
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = rowWeights;
		this.getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		this.getContentPane().add(scrollPane, gbc_scrollPane);
		
		JLabel lblSearchResultsFor = new JLabel("Search Results for Name");
		lblSearchResultsFor.setHorizontalAlignment(SwingConstants.CENTER);
		scrollPane.setColumnHeaderView(lblSearchResultsFor);
		
		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 10, 10, 10, 10};
		gbl_panel.rowHeights = new int[]{10, 10, 10};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		int i = 0;
		for (Integer documentID : sDoc.getDocID()) {
			newSearchResult(i++, panel, documentID);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		for (int i = 0; i < sDoc.getDocID().size(); i++)
		{ 
			if (arg0.getActionCommand().equals("btn" + i))
			{
				try {
					Statement st = null;
					ResultSet rs = null;
					String username = null;
					int documentType = 0, ssn = 0;
					String[] compoundNames = new String[50], compoundMeasurements = new String[50];
					int[] compoundValues = new int[50];
					
					st = user.getConnection().getConnection().createStatement();
					rs = st.executeQuery("exec loadDoc " + sDoc.getDocID().get(i));
					int j = 0;
					while (rs.next()) {
						username = rs.getString(1);
						ssn = rs.getInt(2);
						documentType = rs.getInt(3);
						compoundNames[j] = rs.getString(4);
						compoundValues[j] = rs.getInt(5);
						compoundMeasurements[j] = rs.getString(6);
						
						j++;
					}
					rs.close();
					st.close();
					sDoc.getMainGUI().createFrame(new Document(user, "Document #", 10, 10, 400, 500, sDoc.getDocID().get(i), documentType, ssn, username, compoundNames
							, compoundValues, compoundMeasurements));
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}//end if
		}
	}

	public void newSearchResult(int count, JPanel panel, Integer docID)
	{
		JLabel lblDocument = new JLabel("Document #" + docID);
		GridBagConstraints gbc_lblDocument = new GridBagConstraints();
		gbc_lblDocument.insets = new Insets(0, 0, 0, 5);
		gbc_lblDocument.gridx = 1;
		gbc_lblDocument.gridy = count;
		panel.add(lblDocument, gbc_lblDocument);
		
		JButton btnLoadDocument = sDoc.getButtonArray().get(count);
		GridBagConstraints gbc_btnLoadDocument = new GridBagConstraints();
		gbc_btnLoadDocument.gridx = 3;
		gbc_btnLoadDocument.gridy = count;
		panel.add(btnLoadDocument, gbc_btnLoadDocument);
		
		btnLoadDocument.setActionCommand("btn" + count);
		btnLoadDocument.addActionListener(this);
	}
	
}