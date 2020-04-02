package mainPackage;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

class WelcomeScreenAndLogin {
	
	JFrame f1, f2, f3;
	JButton b1, b2, b3, b4, b5, b6, b7;
	JLabel l1, l2, l3, l4, l5, l6;
	JTextField tf1, tf2;
	JPasswordField pf1, pf2;
	final static Font allButton = new Font("Bookman Old Style", Font.PLAIN, 16);
	final static Font allLabel = new Font("Berlin Sans FB", Font.PLAIN, 18);
	static BufferedImage myImage = null;
	
	@SuppressWarnings("static-access")
	WelcomeScreenAndLogin(){
		
		f1 = new JFrame("Welcome!!");
		f2 = new JFrame("Login As Admin");
		f3 = new JFrame("Login As Employee");
		f1.getContentPane().setBackground(Color.BLACK);
		
		Connect.getConnection();		// Static Connection To database
		
		l1 = new JLabel();
		l2 = new JLabel();
		l3 = new JLabel("Enter login id : ");
		l4 = new JLabel("Enter Password : ");
		l5 = new JLabel("Enter login id : ");
		l6 = new JLabel("Enter Password : ");

		b1 = new JButton("Administrator");
		b2 = new JButton("Employee");
		b3 = new JButton("Check Flights");
		b4 = new JButton("Submit");
		b5 = new JButton("Back");
		b6 = new JButton("Submit");
		b7 = new JButton("Back");
 
		b1.setFont(allButton);
		b2.setFont(allButton);
		b3.setFont(allButton);
		b4.setFont(allButton);
		b5.setFont(allButton);
		b6.setFont(allButton);
		b7.setFont(allButton);
		l1.setFont(allLabel);
		l2.setFont(allLabel);
		l3.setFont(allLabel);
		l4.setFont(allLabel);
		l5.setFont(allLabel);
		tf1 = new JTextField();
		tf2 = new JTextField();
		pf1 = new JPasswordField();
		pf2 = new JPasswordField();
		
		b1.setBounds(100, 400, 150, 30);
		b2.setBounds(300, 400, 150, 30);
		b3.setBounds(500, 400, 150, 30);
		b4.setBounds(70, 200, 130, 40);
		b5.setBounds(250, 200, 130, 40);
		b6.setBounds(70, 200, 130, 40);
		b7.setBounds(250, 200, 130, 40);
		l3.setBounds(40, 50, 140, 40);
		l4.setBounds(40, 120, 140, 40);
		l5.setBounds(40, 50, 140, 40);
		l6.setBounds(40, 120, 140, 40);
		tf1.setBounds(200, 50, 200, 30);
		tf2.setBounds(200, 50, 200, 30);
		pf1.setBounds(200, 120, 200, 30);
		pf2.setBounds(200, 120, 200, 30);

		try {
			myImage = ImageIO.read(new File("C:/image"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f1.setContentPane(new ImagePanel(myImage));
		f1.add(b1);
		f1.add(b2);
		f1.add(b3);
		f2.add(l3);
		f2.add(l4);
		f2.add(tf1);
		f2.add(pf1);
		f2.add(b4);
		f2.add(b5);
		f3.add(l5);
		f3.add(l6);
		f3.add(tf2);
		f3.add(pf2);
		f3.add(b6);
		f3.add(b7);
		
		f1.setDefaultCloseOperation(f1.EXIT_ON_CLOSE);
		f2.setDefaultCloseOperation(f1.DISPOSE_ON_CLOSE);
		f3.setDefaultCloseOperation(f1.DISPOSE_ON_CLOSE);
		f1.setSize(770, 495);
		f1.setLayout(null);
		f1.setLocation((1366-770)/2, (768-495)/2);
		f1.setVisible(true);
		f1.setResizable(false);
		
		f2.setSize(450, 320);
		f2.setLocation((1366-450)/2, (768-320)/2);
		f2.setLayout(null);
		f2.setResizable(false);
		f2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f3.setSize(450, 320);
		f3.setLocation((1366-450)/2, (768-320)/2);
		f3.setLayout(null);
		f3.setResizable(false);
		f3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//b1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
		
		//Login as Admin button
		b1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				tf1.setText("");
				pf1.setText("");
				f2.setVisible(true);
			}
		});
		
		// Login as Employee Button
		b2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				tf2.setText("");
				pf2.setText("");
				f3.setVisible(true);
			}
		});
		
		// check flights button
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f1.setVisible(false);
				new CheckFlights();
				//Privileges for user 
			}
		});
		
		// Admin login page's submit button
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				if(loginAdmin()) {
					f2.setVisible(false);
					f1.setVisible(false);
					new AdminHomePage(tf1.getText());
				}else {
					JOptionPane.showMessageDialog(f2,
					"Invalid Employee ID or Password. Please try again",
					"Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// back to welcome screen button
		b5.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				f2.setVisible(false);
				f1.setVisible(true);
			}
		});
		
		// employee submit button
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {	
				if(loginEmployee()) {
					f3.setVisible(false);
					f1.setVisible(false);
					new EmployeeHomePage(tf2.getText());
				}else {
					JOptionPane.showMessageDialog(f3,
					"Invalid Employee ID or Password. Please try again",
					"Login Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// back button
		b7.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				f3.setVisible(false);
				f1.setVisible(true);
			}
		});
	}
	
	@SuppressWarnings("deprecation")
	protected boolean loginEmployee() {
		// TODO Auto-generated method stub
		Statement smt = null;
		ResultSet rs = null;
		try {
			smt = Connect.conn.createStatement();
			rs = smt.executeQuery("select password from employee where employee_id="+tf2.getText());
			String pass = null;
			while(rs.next()) {
				pass = rs.getString("password");
			}
			if(pass.equals(pf2.getText().trim()))
				return true;
			else 
				return false;
		}
		catch(Exception e) {
			//e.printStackTrace();
			return false;
		}
	}

	@SuppressWarnings("deprecation")
	protected boolean loginAdmin() {
		// TODO Auto-generated method stub
		Statement smt = null;
		ResultSet rs = null;
		try {
			smt = Connect.conn.createStatement();
			rs = smt.executeQuery("select password from admin where admin='"+tf1.getText().trim()+"'");
			String pass = null;
			while(rs.next()) {
				pass = rs.getString("password");
			}
			if(pass.equals(pf1.getText()))
				return true;
			else 
				return false;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	public static void main(String args[]){
		new WelcomeScreenAndLogin();
	}
}