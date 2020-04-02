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

public class DeleteEmployee {

	JFrame f6;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
	JComboBox<String> cb1;
	JButton b1, b2;
	private String employeeName;
	private int employeeID;
	private String address;
	private Long contact;
	private String email;
	
	public DeleteEmployee() {
		f6 = new JFrame("Delete Employee");
		l1 = new JLabel("Select Employee ID : ");
		l2 = new JLabel("Employee Name : ");
		l3 = new JLabel("Address : ");
		l4 = new JLabel("Contact : ");
		l5 = new JLabel("Email : ");
		l6 = new JLabel();
		l7 = new JLabel();
		l8 = new JLabel();
		l9 = new JLabel();
		
		cb1 = new JComboBox<String>();
		b1 = new JButton("OK");
		b2 = new JButton("Cancel");
		
		getEmployeeID();
		l1.setBounds(30, 40, 150, 30);
		l2.setBounds(30, 90, 150, 30);
		l3.setBounds(30, 140, 150, 30);
		l4.setBounds(30, 190, 150, 30);
		l5.setBounds(30, 240, 150, 30);
		
		cb1.setBounds(220, 40, 200, 30);
		l6.setBounds(220, 90, 200, 30);
		l7.setBounds(220, 140, 200, 30);
		l8.setBounds(220, 190, 200, 30);
		l9.setBounds(220, 240, 200, 30);
		b1.setBounds(40, 300, 150, 30);
		b2.setBounds(220, 300, 150, 30);
		
		f6.add(l1);
		f6.add(l2);
		f6.add(l3);
		f6.add(l4);
		f6.add(l5);
		f6.add(l6);
		f6.add(l7);
		f6.add(l8);
		f6.add(l9);
		f6.add(cb1);
		f6.add(b1);
		f6.add(b2);
		
		l7.setBackground(EmployeeHomePage.myblue);
		l8.setBackground(EmployeeHomePage.myblue);
		l9.setBackground(EmployeeHomePage.myblue);
		l6.setBackground(EmployeeHomePage.myblue);
		l7.setForeground(Color.WHITE);
		l8.setForeground(Color.WHITE);
		l9.setForeground(Color.WHITE);
		l6.setForeground(Color.WHITE);
		l7.setOpaque(true);
		l8.setOpaque(true);
		l9.setOpaque(true);
		l6.setOpaque(true);
		
		f6.setLayout(null);
		f6.setSize(450, 420);
		f6.setLocation((1366-450)/2, (768-420)/2);
		f6.setVisible(true);
		f6.setResizable(false);
		f6.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				
				if(cb1.getSelectedIndex()>0) {
					if(deleteRecord()) {
						JOptionPane.showMessageDialog(f6, "Employee Deleted");
						f6.setVisible(false);
					}
					else {
						JOptionPane.showMessageDialog(f6, "Error",
						"ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				else {
					JOptionPane.showMessageDialog(f6, 
					"Please Select a Employee ID ", "Invalid", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f6.setVisible(false);
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
		l8.setText("");
		l9.setText("");
	}
	
	protected void setInfoLabels() {
		// TODO Auto-generated method stub
		clearLabels();
		l6.setText(employeeName);
		l7.setText(address);
		l8.setText(""+contact);
		l9.setText(email);
	}
	
	protected void initializeDetails() {
		// TODO Auto-generated method stub
		employeeID = Integer.parseInt((String) cb1.getSelectedItem());
		try{
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select employee_name, address, email, contact" +
			" from employee where employee_id="+employeeID);
			while(rs.next()) {
				employeeName = rs.getString("employee_name");
				address = rs.getString("address");
				contact = rs.getLong("contact");
				email = rs.getString("email");
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
			PreparedStatement psmt = Connect.conn.prepareStatement("delete from employee where employee_id= ?");
			psmt.setInt(1, employeeID);
			psmt.executeUpdate();
			return true;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}//end of deleteRecord()
	
	public void getEmployeeID() {
		// TODO Auto-generated method stub
		cb1.removeAllItems();
		cb1.addItem("");
		try {
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select employee_id from employee");
			while(rs.next()) {
				cb1.addItem(rs.getString("employee_id"));
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
