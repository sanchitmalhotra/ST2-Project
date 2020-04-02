package mainPackage;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ViewFlights {

	JFrame f10;
	JTable t1;
	JScrollPane tableContainer;
	
	ViewFlights() {
		
		f10 = new JFrame("Flights Details");
		f10.setLayout(new FlowLayout());
		//connect to database
		String columns[] = {"Flight Type", "Flight ID", "Flight Name", 
				"FROM", "TO", "Seats", "Departure Time", "Arrival Time", "Available Seats"};
		
		ArrayList<String[]> ar = getData();
    	String data[][] = new String[ar.size()][9];
    	for(int i = 0; i<ar.size(); i++) {
    		data[i]= ar.get(i);
    		
    	}
		
    	if(data[0]==null) {
			JOptionPane.showMessageDialog(null, "Unable to Extract Employee Table",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else {
		    t1 = new JTable(data, columns);
		    t1.setPreferredScrollableViewportSize(new Dimension(900, 200));
		    t1.setFillsViewportHeight(true);
		    
		    tableContainer = new JScrollPane(t1);
			f10.getContentPane().add(tableContainer, BorderLayout.CENTER);
			
		    f10.setSize(600, 600);
		    f10.setLocation((1366-600)/2, (768-600)/2);
		    f10.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    //panel.add(tableContainer, BorderLayout.CENTER);
		    f10.pack();
		    f10.setVisible(true);
		}
	}

	private ArrayList<String[]> getData() {
		// TODO Auto-generated method stub
		ArrayList<String[]> ar = new ArrayList<String[]>();
		try{
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select * from flights");
			String[] allData;
			while(rs.next()) {
				allData = new String[9];
				allData[0]=rs.getString("flightType");
				allData[1]=rs.getString("flightID");
				allData[2]=rs.getString("flightName");
				allData[3]=rs.getString("source");
				allData[4]=rs.getString("destination");
				allData[5]=rs.getString("seats");
				allData[6]=rs.getString("departure");
				allData[7]=rs.getString("arrival");
				allData[8]=rs.getString("availseats");
				ar.add(allData);
			}
			return ar;
		}catch(Exception ex) {
			//ex.printStackTrace();
			return null;
		}
	}
}
