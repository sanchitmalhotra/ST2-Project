package mainPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class DeleteFlight {

	JFrame f9;
	JLabel l1, l2, l3, l4, l5, l6, l7;
	JComboBox<String> cb1;
	JButton b1, b2;
	private int flightID;
	private String flightName;
	private String from;
	private String to;
	
	public DeleteFlight() {
		f9 = new JFrame("Delete Flight");
		l1 = new JLabel("Select Flight ID : ");
		l2 = new JLabel("Flight Name : ");
		l3 = new JLabel("From : ");
		l4 = new JLabel("To : ");
		l5 = new JLabel();
		l6 = new JLabel();
		l7 = new JLabel();
		cb1 = new JComboBox<String>();
		b1 = new JButton("OK");
		b2 = new JButton("Cancel");
		
		l1.setBounds(30, 40, 150, 30);
		l2.setBounds(30, 90, 150, 30);
		l3.setBounds(30, 140, 150, 30);
		l4.setBounds(30, 190, 150, 30);
		cb1.setBounds(220, 40, 200, 30);
		l5.setBounds(220, 90, 200, 30);
		l6.setBounds(220, 140, 200, 30);
		l7.setBounds(220, 190, 200, 30);
		
		b1.setBounds(40, 250, 150, 30);
		b2.setBounds(220, 250, 150, 30);
		
		getFlights();
		f9.add(l1);
		f9.add(l2);
		f9.add(l3);
		f9.add(l4);
		f9.add(l5);
		f9.add(l6);
		f9.add(l7);
		f9.add(cb1);
		f9.add(b1);
		f9.add(b2);
		
		l5.setBackground(EmployeeHomePage.myblue);
		l6.setBackground(EmployeeHomePage.myblue);
		l7.setBackground(EmployeeHomePage.myblue);
		l5.setForeground(Color.WHITE);
		l6.setForeground(Color.WHITE);
		l7.setForeground(Color.WHITE);
		l5.setOpaque(true);
		l6.setOpaque(true);
		l7.setOpaque(true);
		
		f9.setLayout(null);
		f9.setSize(450, 350);
		f9.setLocation((1366-450)/2, (768-350)/2);
		f9.setVisible(true);
		f9.setResizable(false);
		f9.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(cb1.getSelectedIndex()>0) {
					if(deleteRecord()) {
						JOptionPane.showMessageDialog(f9, "Flight Deleted");
						f9.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(f9, "Error",
						"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(f9, 
					"Please Select a Flight ID ", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f9.setVisible(false);
				//new AdminLogin();
			}
		});
		
		cb1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.println(cb1.getSelectedIndex());
				if(cb1.getSelectedItem()!="") {
					initializeDetails();
				}
				else {
					clearLabels();
				}
			}
			
		});
	}//end of constructor

	private void clearLabels() {
		l6.setText("");
		l7.setText("");
		l5.setText("");
	}
	
	protected void setInfoLabels() {
		// TODO Auto-generated method stub
		clearLabels();
		l5.setText(flightName);
		l6.setText(from);
		l7.setText(to);
	}
	
	protected void initializeDetails() {
		// TODO Auto-generated method stub
		flightID = Integer.parseInt((String) cb1.getSelectedItem());
		try{
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select flightname, source, destination" +
			" from flights where flightid="+flightID);
			while(rs.next()) {
				flightName = rs.getString("flightname");
				from = rs.getString("source");
				to = rs.getString("destination");
			}
			setInfoLabels();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	protected boolean deleteRecord() {
		// TODO Auto-generated method stub
		try{
			PreparedStatement psmt = Connect.conn.prepareStatement("delete from flights where flightid= ?");
			psmt.setInt(1, flightID);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}//end of deleteRecord()
	
	public void getFlights() {
		// TODO Auto-generated method stub
		cb1.removeAllItems();
		cb1.addItem("");
		try {
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select flightID from flights");
			while(rs.next()) {
				cb1.addItem(rs.getString("flightID"));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}