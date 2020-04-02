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

public class CancelReservation {

	JFrame f14;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9, l10, l11;
	JButton b1, b2;
	JComboBox<String> cb1;
	private int transNo;
	private long customerID;
	private int flightID;
	private String flightName;
	private String customerName;
	private String email;
	
	public CancelReservation() {
		f14 = new JFrame("Cancel Reservation");
		l1 = new JLabel("Select Transaction No. : ");
		l2 = new JLabel("Customer ID :");
		l3 = new JLabel("Flight ID : ");
		l4 = new JLabel("Flight Name : ");
		l5 = new JLabel("Customer Name : ");
		l6 = new JLabel("Email ID : ");
		l7 = new JLabel();		// for customer id
		l8 = new JLabel();		// for flight id
		l9 = new JLabel();		// for flight name
		l10 = new JLabel();		// for customer name
		l11 = new JLabel();		// for email id
		
		b1 = new JButton("OK");
		b2 = new JButton("Cancel");
		
		cb1 = new JComboBox<String>();
		getTransNo();
		l1.setBounds(30, 40, 150, 30);
		l2.setBounds(30, 90, 150, 30);
		l3.setBounds(30, 140, 150, 30);
		l4.setBounds(30, 190, 150, 30);
		l5.setBounds(30, 240, 150, 30);
		l6.setBounds(30, 290, 150, 30);
		cb1.setBounds(200, 40, 200, 30);
		l7.setBounds(200, 90, 200, 30);
		l8.setBounds(200, 140, 200, 30);
		l9.setBounds(200, 190, 200, 30);
		l10.setBounds(200, 240, 200, 30);
		l11.setBounds(200, 290, 200, 30);
		
		b1.setBounds(50, 350, 140, 30);
		b2.setBounds(210, 350, 150, 30);
		
		l7.setBackground(EmployeeHomePage.myblue);
		l8.setBackground(EmployeeHomePage.myblue);
		l9.setBackground(EmployeeHomePage.myblue);
		l10.setBackground(EmployeeHomePage.myblue);
		l11.setBackground(EmployeeHomePage.myblue);
		l7.setForeground(Color.WHITE);
		l8.setForeground(Color.WHITE);
		l9.setForeground(Color.WHITE);
		l10.setForeground(Color.WHITE);
		l11.setForeground(Color.WHITE);
		l7.setOpaque(true);
		l8.setOpaque(true);
		l9.setOpaque(true);
		l10.setOpaque(true);
		l11.setOpaque(true);
		
		f14.setLayout(null);
		f14.setSize(480, 450);
		f14.setLocation((1366-480)/2, (768-450)/2);
		f14.setResizable(false);
		f14.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		f14.add(l1);
		f14.add(l2);
		f14.add(l3);
		f14.add(l4);
		f14.add(l5);
		f14.add(l6);
		f14.add(l7);
		f14.add(l8);
		f14.add(l9);
		f14.add(l10);
		f14.add(l11);
		f14.add(cb1);
		f14.add(b1);
		f14.add(b2);
		
		f14.setVisible(true);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//connect to database
				if(cb1.getSelectedIndex()>0) {
					if(deleteRecord()) {
						NewReservation.updateSeats(availSeats()+1, flightID);
						JOptionPane.showMessageDialog(f14, "Reservation Cancelled");
						f14.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(f14, "",
						"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(f14, 
					"Please Select a Transaction No. ", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f14.setVisible(false);
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

	protected int availSeats() {
		// TODO Auto-generated method stub
		int a = 0;
		try {
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select availseats from flights where flightID = "+flightID);
			while(rs.next()) {
				a = rs.getInt("availseats");
			}
			return a;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return a;
		}
	}

	protected void setInfoLabels() {
		// TODO Auto-generated method stub
		clearLabels();
		l7.setText(""+customerID);
		l8.setText(""+flightID);
		l9.setText(flightName);
		l10.setText(customerName);
		l11.setText(email);
	}
	
	private void clearLabels() {
		l7.setText("");
		l8.setText("");
		l9.setText("");
		l10.setText("");
		l11.setText("");
	}
	
	protected void initializeDetails() {
		// TODO Auto-generated method stub
		transNo = Integer.parseInt((String) cb1.getSelectedItem());
		try{
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select flightID, customer_name, customer_id, email" +
			" from reservation where transaction_no="+transNo);
			while(rs.next()) {
				flightID = rs.getInt("flightid");
				customerName = rs.getString("customer_name");
				customerID = rs.getLong("customer_id");
				email = rs.getString("email");
			}
			Statement smt2 = Connect.conn.createStatement();
			ResultSet rs2 = smt2.executeQuery("select flightname from flights where flightid="+flightID);
			while(rs2.next()){
				flightName = rs2.getString("flightname");
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
			PreparedStatement psmt = Connect.conn.prepareStatement("delete from reservation where transaction_no= ?");
			psmt.setInt(1, transNo);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}//end of deleteRecord()
	
	public void getTransNo() {
		// TODO Auto-generated method stub
		cb1.removeAllItems();
		cb1.addItem("");
		try {
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select transaction_no from reservation");
			while(rs.next()) {
				cb1.addItem(rs.getString("transaction_no"));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
