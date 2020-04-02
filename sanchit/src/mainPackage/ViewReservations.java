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

public class ViewReservations {

	JFrame f15;
	JTable t1;
	JScrollPane tableContainer;
	
	ViewReservations() {
		
		f15 = new JFrame("Reservations");
		f15.setLayout(new FlowLayout());
		//connect to database
		String columns[] = {"Transaction No.", "Flight ID", "Customer Name", "Customer ID", "Email ID"};
		
		ArrayList<String[]> ar = getData();
    	String data[][]=new String[ar.size()][5];
    	for(int i = 0; i<ar.size(); i++) {
    		data[i]= ar.get(i);
    		
    	}
		if(data[0]==null) {
			JOptionPane.showMessageDialog(null, "Unable to Extract Reservations Table",
			"ERROR", JOptionPane.ERROR_MESSAGE);	
		}
		else {
			try{
		    t1 = new JTable(data, columns);
		    t1.setPreferredScrollableViewportSize(new Dimension(800, 200));
		    t1.setFillsViewportHeight(true);
		    
		    tableContainer = new JScrollPane(t1);
			f15.getContentPane().add(tableContainer, BorderLayout.CENTER);
			
		    f15.setSize(600, 600);
		    f15.setLocation((1366-600)/2, (768-600)/2);
		    f15.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    f15.pack();
		    f15.setVisible(true);
			}catch(Exception e) {
				
			}
		}
	}

	private ArrayList<String[]> getData() {
		// TODO Auto-generated method stub
		ArrayList<String[]> ar = new ArrayList<String[]>();
		try{
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select * from reservation");
			String[] allData;
			while(rs.next()) {
				allData = new String[5];
				allData[0]=rs.getString("transaction_no");
				allData[1]=rs.getString("flightid");
				allData[2]=rs.getString("customer_name");
				allData[3]=rs.getString("customer_id");
				allData[4]=rs.getString("email");
				ar.add(allData);
			}
			return ar;
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
