package mainPackage;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CreateEmployee {

	JFrame f5 ;
	JLabel l1, l2, l3, l4, l5, l6, l7;
	JTextField tf1, tf2, tf3, tf4, tf5;
	JPasswordField pf1;
	JTextArea ta1;
	JButton b1, b2;
	JScrollPane sp1;
	
	private int employeeID;
	private String employeeName;
	private String employeePassword;
	private String dateOfBirth;
	private String employeeAddress;
	private String email;
	private Long contact;
	final static Font LABELFONT = new Font("Bradley Hand ITC", Font.BOLD, 20);
	public CreateEmployee() {
	
		f5 = new JFrame("New Employee Form");
		l1 = new JLabel("Empolyee ID : ");
		l2 = new JLabel("Employee Name  : ");
		l3 = new JLabel("Password  : ");
		l4 = new JLabel("Date Of Birth  : ");
		l5 = new JLabel("Address: ");
		l6 = new JLabel("Email ID : ");
		l7 = new JLabel("Contact No. : ");
		tf1 = new JTextField();
		tf2 = new JTextField();
		tf3 = new JTextField();
		tf4 = new JTextField();
		tf5 = new JTextField();
		
		pf1 = new JPasswordField();
		ta1 = new JTextArea();
		b1 = new JButton("Create");
		b2 = new JButton("Back");
		sp1 = new JScrollPane(ta1);
		
		l1.setBounds(40, 50, 140, 30);
		l2.setBounds(40, 100, 140, 30);
		l3.setBounds(40, 150, 140, 30);
		l4.setBounds(40, 200, 140, 30);
		l5.setBounds(40, 250, 140, 30);
		l6.setBounds(40, 370, 140, 30);
		l7.setBounds(40, 420, 140, 30);
		
		tf1.setBounds(200, 50, 300, 30);
		tf2.setBounds(200, 100, 300, 30);
		pf1.setBounds(200, 150, 300, 30);
		tf3.setBounds(200, 200, 300, 30);
		ta1.setBounds(200, 250, 300, 100);
		tf4.setBounds(200, 370, 300, 30);
		tf5.setBounds(200, 420, 300, 30);
		
		b1.setBounds(80, 480, 150, 30);
		b2.setBounds(270, 480, 120, 30);
		//sp1.setBounds(250, 260, 300, 40);
		/*
		l1.setFont(LABELFONT);
		l2.setFont(LABELFONT);
		l3.setFont(LABELFONT);
		l4.setFont(LABELFONT);
		l5.setFont(LABELFONT);
		l6.setFont(LABELFONT);
		l7.setFont(LABELFONT);
		
		//BufferedImage myImage = null;
		//try {
		//	myImage = ImageIO.read(new File("C:/image12.jpg"));
		//} catch (IOException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		//f5.setContentPane(new ImagePanel(myImage));
		*/
		f5.setLayout(null);
		f5.setSize(540, 550);
		f5.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f5.setLocation((1366-540)/2, (768-550)/2);
		
		f5.setResizable(false);
		f5.add(l1);
		f5.add(l2);
		f5.add(l3);
		f5.add(l4);
		f5.add(l5);
		f5.add(l6);
		f5.add(l7);
		f5.add(tf1);
		f5.add(tf2);
		f5.add(tf3);
		f5.add(tf4);
		f5.add(ta1);
		f5.add(pf1);
		f5.add(tf5);
		f5.add(b1);
		f5.add(b2);
		f5.add(sp1);
		f5.setVisible(true);
		
		b1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent ae) {
				
				if(validateForm()) {
					initializeDetails();
					if(createRecord()) {
					JOptionPane.showMessageDialog(f5, "Employee Created");
					f5.setVisible(false);
					}
					else{
						JOptionPane.showMessageDialog(f5, "Record Not Created");
					}
				}
				else{
						JOptionPane.showMessageDialog(f5,  "Fill all the details", 
								"Error", JOptionPane.ERROR_MESSAGE);
					}
			}
		});
		
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f5.setVisible(false);
				//new AdminLogin();
			}
		});
	}// end of constructor
	@SuppressWarnings("deprecation")
	private boolean validateForm() {
		if(((tf1.getText().length())==0)||((tf2.getText().length())==0)||((tf3.getText().length())==0)
				||((pf1.getText().length())==0)||tf5.getText().length()==0)
			return false;
		else 
			return true;
	}
	
	@SuppressWarnings("deprecation")
	public void initializeDetails() {
		try {
		employeeID = Integer.parseInt(tf1.getText());
		employeeName = tf2.getText();
		employeePassword = pf1.getText();
		dateOfBirth = tf3.getText();
		employeeAddress = ta1.getText();
		email = tf4.getText();
		contact = Long.parseLong(tf5.getText());
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(f5, "Enter Valid Details", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public boolean createRecord() {
		
		try{
			if(employeeAddress.length()!=0) {
			PreparedStatement psmt = Connect.conn.prepareStatement("insert into employee values(?, ?, ?, ?, ?, ?, ?)");
			psmt.setInt(1, employeeID);
			psmt.setString(2, employeeName);
			psmt.setString(3, employeePassword);
			psmt.setString(4, dateOfBirth);
			psmt.setString(5, employeeAddress);
			psmt.setString(6, email);
			psmt.setLong(7, contact);
			psmt.executeUpdate();
			JOptionPane.showMessageDialog(f5, "Record Created Successfully!");
			return true;
			}
			else{
				PreparedStatement psmt = Connect.conn.prepareStatement("insert into employee" +
						"(employee_id, employee_name, password, date_of_birth, email)" +
						" values(?, ?, ?, ?, ?, ?)");
				psmt.setInt(1, employeeID);
				psmt.setString(2, employeeName);
				psmt.setString(3, employeePassword);
				psmt.setString(4, dateOfBirth);
				psmt.setString(5, email);
				psmt.setLong(6, contact);
				psmt.executeUpdate();
				JOptionPane.showMessageDialog(f5, "Record Created Successfully!");
				return true;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(f5, "Invalid Details", "ERROR", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}
}