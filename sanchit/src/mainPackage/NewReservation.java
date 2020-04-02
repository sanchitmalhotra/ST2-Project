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
import javax.swing.JTextField;

public class NewReservation {

	JFrame f13;
	JLabel l1, l2, l3, l4, l5, l7, l8, l9, l10, l11, l12, l13, l14, l15, l16;
	JTextField tf1, tf2, tf3;
	JButton b1, b2;
	JComboBox<String> cb1, cb2;
	int flightID;
	String flightName;
	String from;
	String to;
	long customerID;
	String customerName;
	String email;
	int totalSeats, availSeats;
	
	NewReservation() {
		f13 = new JFrame("New Resevation");
		l1 = new JLabel("Select Flight ID : ");
		//l2 = new JLabel("Select Member Type : ");
		l3 = new JLabel("Enter Customer ID : ");
		l4 = new JLabel("Customer Name : ");
		l5 = new JLabel("Customer Email : ");
		l7 = new JLabel("Flight Name : ");
		l8 = new JLabel("From : ");
		l9 = new JLabel("TO : ");
		l10 = new JLabel("Total Seats : ");
		l11 = new JLabel("Available Seats : ");
		l12 = new JLabel();		//for flight name
		l13 = new JLabel();		//for from
		l14 = new JLabel();		//for to
		l15 = new JLabel();		//for total seats 		
		l16 = new JLabel();		//for available seats
		
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		b1 = new JButton("Make Reservation");
		b2 = new JButton("Cancel");
		
		cb1 = new JComboBox<String>();
		//cb2 = new JComboBox<String>();
		//cb2.addItem("Guest");
		//cb2.addItem("Registered Member");
		cb1.setBackground(Color.WHITE);
		//cb2.setBackground(Color.WHITE);
		l1.setBounds(30, 40, 180, 30);
		//l2.setBounds(30, 90, 180, 30);
		l3.setBounds(30, 90, 180, 30);
		l4.setBounds(30, 140, 180, 30);
		l5.setBounds(30, 190, 180, 30);
		l7.setBounds(30, 240, 180, 30);
		l8.setBounds(30, 290, 180, 30);
		l9.setBounds(330, 290, 100, 30);
		l10.setBounds(30, 340, 180, 30);
		l11.setBounds(30, 390, 180, 30);
		cb1.setBounds(210, 40, 200, 30);
		//cb2.setBounds(210, 90, 200, 30);
		tf1.setBounds(210, 90, 200, 30);
		tf2.setBounds(210, 140, 200, 30);
		tf3.setBounds(210, 190, 200, 30);
		l12.setBounds(210, 240, 100, 30);
		l13.setBounds(210, 290, 100, 30);
		l14.setBounds(410, 290, 100, 30);
		l15.setBounds(210, 340, 100, 30);
		l16.setBounds(210, 390, 100, 30);
		
		l12.setBackground(EmployeeHomePage.myblue);
		l13.setBackground(EmployeeHomePage.myblue);
		l14.setBackground(EmployeeHomePage.myblue);
		l15.setBackground(EmployeeHomePage.myblue);
		l16.setBackground(EmployeeHomePage.myblue);
		l12.setForeground(Color.WHITE);
		l13.setForeground(Color.WHITE);
		l14.setForeground(Color.WHITE);
		l15.setForeground(Color.WHITE);
		l16.setForeground(Color.WHITE);
		l12.setOpaque(true);
		l13.setOpaque(true);
		l14.setOpaque(true);
		l15.setOpaque(true);
		l16.setOpaque(true);
		
		b1.setBounds(60, 450, 180, 30);
		b2.setBounds(260, 450, 180, 30);
		
		getFlights();
		f13.setSize(520, 520);
		f13.setLocation((1366-520)/2, (768-520)/2);
		f13.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f13.setResizable(false);
		f13.setLayout(null);
		f13.add(l1);
		//f13.add(l2);
		f13.add(l3);
		f13.add(l4);
		f13.add(l5);
		f13.add(l7);
		f13.add(l8);
		f13.add(l9);
		f13.add(l10);
		f13.add(l11);
		f13.add(l12);
		f13.add(l13);
		f13.add(l14);
		f13.add(l15);
		f13.add(l16);
		f13.add(tf1);
		f13.add(tf2);
		f13.add(tf3);
		f13.add(cb1);
		//f13.add(cb2);
		f13.add(b1);
		f13.add(b2);
		f13.setVisible(true);
		
		cb1.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				System.out.println(cb1.getSelectedIndex());
				if(cb1.getSelectedItem()!="") {
					flightID = Integer.parseInt((String) cb1.getSelectedItem());
					try{
						Statement smt = Connect.conn.createStatement();
						ResultSet rs = smt.executeQuery("select flightName, source, destination, seats, availseats" +
						" from flights where flightID="+flightID);
						while(rs.next()) {
							flightName = rs.getString("flightName");
							from = rs.getString("source");
							to = rs.getString("destination");
							totalSeats = rs.getInt("seats");
							availSeats = rs.getInt("availseats");
							setInfoLabels();
						}
					}catch(Exception ex) {
						ex.printStackTrace();
					}
				}
				else {
					clearLabels();
				}
			}
			
		});
		

		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
			
				if(cb1.getSelectedIndex()>0) {
					if(verifyDetails()) {
						if(makeReservation()) {
							updateSeats(availSeats-1, flightID);
							JOptionPane.showMessageDialog(f13, 
							"Flight "+flightName+" Reserved for "+customerName, "Success!",
							JOptionPane.INFORMATION_MESSAGE);
							f13.setVisible(false);
						}
						
					}
					else 
						JOptionPane.showMessageDialog(f13,
						"Please Enter All the Details", "Invalid Details", JOptionPane.ERROR_MESSAGE);
				}
				else {
					System.out.println("Please select a flight first");
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {	
			
			public void actionPerformed(ActionEvent arg0) {		
				f13.setVisible(false);
			}
		});
	}// end of constructor
	
	protected static void updateSeats(int newSeats, int flightID) {
		try {
			PreparedStatement psmt = Connect.conn.prepareStatement("update " +
			"flights set availseats="+newSeats+" where flightID="+ flightID);
			psmt.executeUpdate();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	protected boolean makeReservation() {
		
		try {
			PreparedStatement psmt = Connect.conn.prepareStatement("insert into " +
			"reservation values (seq_trans.nextval, ?, ?, ?, ?)");
			psmt.setInt(1, flightID);
			psmt.setString(2, customerName);
			psmt.setLong(3, customerID);
			psmt.setString(4, email);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

	protected boolean verifyDetails() {
		// TODO Auto-generated method stub
		if(tf1.getText().trim().length()==0||tf2.getText().trim().length()==0||tf3.getText().trim().length()==0){
			JOptionPane.showMessageDialog(f13, "Invalid Details", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		else {
			try {
				customerID = Long.parseLong(tf1.getText().trim());
				customerName = tf2.getText().trim();
				email = tf3.getText().trim();
				return true;
			}
			catch(Exception ex) {
				JOptionPane.showMessageDialog(f13, 
				"Invalid Customer ID", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
	}

	protected void setInfoLabels() {
		// TODO Auto-generated method stub
		clearLabels();
		l12.setText(flightName);
		l13.setText(from);
		l14.setText(to);
		l15.setText(""+totalSeats);
		l16.setText(""+availSeats);
	}
	private void clearLabels() {
		l15.setText("");
		l12.setText("");
		l13.setText("");
		l14.setText("");
		l16.setText("");
	}

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
