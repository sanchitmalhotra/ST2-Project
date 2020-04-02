package mainPackage;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class AddFlight {

	JFrame f8;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8;
	JTextField tf1, tf2, tf3, tf4, tf5;
	JComboBox<String> list1, list2, list3;
	JButton b1, b2;
	
	String ar[], ar1[], ar2;
	private String flightType;
	private int flightID;
	private String flightName;
	private String source;
	private String destination;
	private int seats;
	private String departure;
	private String arrival;
	
	AddFlight() {
		f8 = new JFrame("New Flight Form");
		l1 = new JLabel("Flight Type : ");
		l2 = new JLabel("Flight ID : ");
		l3 = new JLabel("Flight Name: ");
		l4 = new JLabel("From  : ");
		l5 = new JLabel("To : ");
		l6 = new JLabel("Seats : ");
		l7 = new JLabel("Departure Time : ");
		l8 = new JLabel("Arrival Time : ");
		
		String ar1[]={"", "Domestic", "International"};
		
		list1 = new JComboBox<String>(ar1);
		tf1 = new JTextField();
		tf2 = new JTextField();
		list2 = new JComboBox<String>();
		list3 = new JComboBox<String>();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();
		b1 = new JButton("OK");
		b2 = new JButton("Cancel");
		
		l1.setBounds(30, 30, 120, 25);
		l2.setBounds(30, 70, 120, 25);
		l3.setBounds(30, 110, 120, 25);
		l4.setBounds(30, 150, 120, 25);
		l5.setBounds(30, 190, 120, 25);
		l6.setBounds(30, 230, 120, 25);
		l7.setBounds(30, 270, 120, 25);
		l8.setBounds(30, 310, 120, 25);
		
		list1.setBounds(170, 30, 200, 25);
		tf1.setBounds(170, 70, 200, 25);
		tf2.setBounds(170, 110, 200, 25);
		list2.setBounds(170, 150, 200, 25);
		list3.setBounds(170, 190, 200, 25);
		tf3.setBounds(170, 230, 200, 25);
		tf4.setBounds(170, 270, 200, 25);
		tf5.setBounds(170, 310, 200, 25);
		
		list1.setBackground(Color.WHITE);
		list2.setBackground(Color.WHITE);
		list3.setBackground(Color.WHITE);
		
		b1.setBounds(50, 370, 120, 30);
		b2.setBounds(200, 370, 120, 30);
		
		f8.add(l1);
		f8.add(l2);
		f8.add(l3);
		f8.add(l4);
		f8.add(l5);
		f8.add(l6);
		f8.add(l7);
		f8.add(l8);
		f8.add(list1);
		f8.add(tf1);
		f8.add(tf2);
		f8.add(list2);
		f8.add(list3);
		f8.add(tf3);
		f8.add(tf4);
		f8.add(tf5);
		f8.add(b1);
		f8.add(b2);
		
		f8.setLayout(null);
		f8.setSize(420, 450);
		f8.setLocation((1366-420)/2, (768-450)/2);
		f8.setResizable(false);
		f8.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f8.setVisible(true);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(validateForm() && initializeDetails()) {
					createRecord();
					JOptionPane.showMessageDialog(f8, "Flight Added");
					f8.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(f8, "Fill All the Details in the Form",
							"ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f8.setVisible(false);
			}
		});
		
		list1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				// TODO Auto-generated method stub
				if(list1.getSelectedItem().equals("Domestic")) {
					final String[] ar = {"","New Delhi", "Mumbai", "Kolkata", "Chennai", 
							"Chandigarh", "Bangalore", "Hyderabad", "Guwahati",
							"Patna", "Surat", "Ahemdabad", "Pune", "Bhopal", 
							"Jammu", "Amritsar", "Kanpur", "Lucknow"};
					changeListItems(ar);
				}
				else if(list1.getSelectedItem().equals("International")) {
					String[] ar2 = {"", "New Delhi", "Mumbai", "Kolkata", "Chennai", 
							"New York", "Chicago", "Dubai", "London","Hong Kong", 
							"Muscat", "Singapore", "Shanghai", "Toronto", "Cape Town"};
					changeListItems(ar2);
				}
			}
			
		});
	}//end of constructor
	
	protected void changeListItems(String[] ar3) {
		// TODO Auto-generated method stub
			list2.removeAllItems();
			list3.removeAllItems();
		for(int i=0; i<ar3.length; i++) {
			list2.addItem(ar3[i]);
			list3.addItem(ar3[i]);
		}
	}

	private boolean createRecord() {
		//connect to database
		try{
			DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "youcantseeme");

			PreparedStatement psmt = conn.prepareStatement("insert into flights values(?, ?, ?, ?, ?, ?, ?, ?, ?)");
			psmt.setString(1,flightType);
			psmt.setInt(2, flightID);
			psmt.setString(3, flightName);
			psmt.setString(4, source);
			psmt.setString(5, destination);
			psmt.setInt(6, seats);
			psmt.setString(7, departure);
			psmt.setString(8, arrival);
			psmt.setInt(9, seats);
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(f8, "Record Created Successfully!");
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(f8, "Invalid Details", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
	
	private boolean initializeDetails() {
		flightType = (String) list1.getSelectedItem();
		flightName = tf2.getText();
		source = (String) list2.getSelectedItem();
		destination = (String) list3.getSelectedItem();
		try {
		seats = Integer.parseInt(tf3.getText());
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(f8, "Invalid Number of seats",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		departure = tf4.getText();
		arrival = tf5.getText();
		//flightType = tf6.getText();
		try{
			flightID = Integer.parseInt(tf1.getText());
			return true;
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(f8, "Flight ID must be numerical",
					"ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private boolean validateForm() {
		// TODO Auto-generated method stub
		if((tf1.getText().length())==0||(tf2.getText().length())==0||
				(tf3.getText().length())==0||(tf4.getText().length())==0||
				(tf5.getText().length())==0||((String) list1.getSelectedItem()).length()==0
				||((String) list1.getSelectedItem()).length()==0||
				((String) list1.getSelectedItem()).length()==0)
			return false;
		else
			return true;
	}
}
