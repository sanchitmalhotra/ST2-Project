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

public class ViewEmployees {

	JFrame f7;
	JTable t1;
	JScrollPane tableContainer;
	ArrayList<String[]> ar;
	ViewEmployees() {
		
		f7 = new JFrame("Employee Details");
		f7.setLayout(new FlowLayout());
		//connect to database
		String columns[] = {"Employee ID", "Name", "Password", "Date Of Birth", "Address", "Email ID"};
		getData();
		
		String data[][]=new String[ar.size()][6];
		for(int i = 0; i<ar.size(); i++) {
    		data[i]= ar.get(i);	
    	}
    	
		if(data[0]==null) {
			JOptionPane.showMessageDialog(null, "Unable to Extract Employee Table",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			
		}
		else {
			try{
		    t1 = new JTable(data, columns);
		    t1.setPreferredScrollableViewportSize(new Dimension(800, 200));
		    t1.setFillsViewportHeight(true);
		    
		    tableContainer = new JScrollPane(t1);
			f7.getContentPane().add(tableContainer, BorderLayout.CENTER);
			
		    f7.setSize(600, 600);
		    f7.setLocation((1366-600)/2, (768-600)/2);
		    f7.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		    //panel.add(tableContainer, BorderLayout.CENTER);
		    f7.pack();
		    f7.setVisible(true);
			}catch(Exception e) {
				
			}
		}
	}

	private void getData() {
		// TODO Auto-generated method stub
		ar = new ArrayList<String[]>();
		try{
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select * from employee");
			String[] allData;
			while(rs.next()) {
				allData = new String[6];
				allData[0]=rs.getString("employee_id");
				allData[1]=rs.getString("employee_name");
				allData[2]=rs.getString("password");
				allData[3]=rs.getString("DATE_OF_BIRTH");
				allData[4]=rs.getString("address");
				allData[5]=rs.getString("email");
				ar.add(allData);
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}