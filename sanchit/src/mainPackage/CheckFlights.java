package mainPackage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CheckFlights {

	JFrame f16;
	JLabel l1, l2;
	JButton b1, b2;
	JComboBox<String> cb1, cb2;
	JScrollPane sp1;
	JPanel p1;
	JTable tb1;

	CheckFlights() {

		f16 = new JFrame("Check Flights");
		l1 = new JLabel("From : ");
		l2 = new JLabel("To : ");
		b1 = new JButton("Check Flights");
		b2 = new JButton("Back");
		//sp1 = new JScrollPane();
		p1 = new JPanel();
		p1.setLayout(new FlowLayout());
		cb1 = new JComboBox<String>();
		cb2 = new JComboBox<String>();
		
		l1.setBounds(50, 100, 100, 30);
		l2.setBounds(50, 150, 100, 30);
		cb1.setBounds(170, 100, 200, 30);
		cb2.setBounds(170, 150, 200, 30);
		b1.setBounds(400, 100, 150, 30);
		b2.setBounds(400, 150, 150, 30);
		p1.setBounds(50, 200, 650, 250);
			
		p1.setLayout(new BorderLayout());
		p1.setOpaque(true);
		//p1.setVisible(false);
		setFromAndTo();
		f16.setLayout(null);
		f16.setSize(700, 500);
		f16.setLocation((1366-700)/2, (768-500)/2);
		f16.setResizable(false);
		f16.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f16.add(l1);
		f16.add(l2);
		f16.add(cb1);
		f16.add(cb2);
		f16.add(b1);
		f16.add(b2);
		f16.setVisible(true);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(((String) cb1.getSelectedItem()).length()!=0 || ((String) cb2.getSelectedItem()).length()!=0)
					extractTable((String)cb1.getSelectedItem(), (String)cb2.getSelectedItem());
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f16.setVisible(false);
				new WelcomeScreenAndLogin();
			}
		});
	}
	private void setFromAndTo() {
		try {
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select source, destination from flights");
			while(rs.next()) {
				cb1.addItem(rs.getString("source"));
				cb2.addItem(rs.getString("destination"));
				}
			}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	private void extractTable(String from, String to) {
		ArrayList<String[]> ar = new ArrayList<String[]>();
		try{
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select * from flights where source = '"+from +"' and destination = '"+to+"'");
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
			displayTable(ar);
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(f16,
			"No flights for the specified destination", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	private void displayTable(ArrayList<String[]> ar) {
		// TODO Auto-generated method stub
		String columns[] = {"Flight Type", "Flight ID", "Flight Name", 
		"FROM", "TO", "Seats", "Departure Time", "Arrival Time", "Available Seats"};
		
	   	String data[][] = new String[ar.size()][9];
    	for(int i = 0; i<ar.size(); i++)
    		data[i]= ar.get(i);
    	
    	if(data[0]==null) {
			JOptionPane.showMessageDialog(null, "Unable to Extract Employee Table",
					"ERROR", JOptionPane.ERROR_MESSAGE);
		}
		else {
		    
			tb1 = new JTable(data, columns);
		    sp1 = new JScrollPane(tb1);
			//tb1.setFillsViewportHeight(true);
	
		    p1.add(sp1, BorderLayout.CENTER);
		    f16.getContentPane().add(p1);
		    f16.pack();
		    //panel.add(tableContainer, BorderLayout.CENTER);
		    //p1.setVisible(true);

		}
	}
	
}
