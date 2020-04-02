package mainPackage;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

class AdminHomePage {

	JFrame f4;
	JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11;
	JLabel l1, l2, l3, l4, l5, l6, l7, l8, l10;
	JPasswordField pf1, pf2, pf3;
	JPanel p1;
	JPanel p2, p4;
	protected Component tf1;
	protected Component tf2;
	String adminName;
	String Password;
	
	AdminHomePage(String adminName){
		this.adminName = adminName;
		f4 = new JFrame("Admin Homepage");
		f4.setLayout(null);
		f4.setSize(700, 495);
		f4.setLocation((1366-700)/2, (768-495)/2);
		
		b1 = new JButton("Employee");
		b2 = new JButton("Flights");
		b3 = new JButton("Change Password");
		b4 = new JButton("Logout");
		b5 = new JButton("Create Employee");
		b6 = new JButton("Delete Employee");
		b7 = new JButton("View Employees");
		b8 = new JButton("Add Flight");
		b9 = new JButton("Delete Flight");
		b10 = new JButton("View Flights");
		b11 = new JButton("Change Password");
		
		
		l1 = new JLabel("Enter Previous Password : ");
		l2 = new JLabel("Enter New Password : ");
		l3 = new JLabel("Confirm New Password : ");
		l4 = new JLabel();
		l5 = new JLabel();
		l6 = new JLabel();
		l7 = new JLabel();
		l8 = new JLabel();
		l10 = new JLabel();
		
		pf1 = new JPasswordField();
		pf2 = new JPasswordField();
		pf3 = new JPasswordField();
		p1 = new JPanel();
		p2 = new JPanel();
		p4 = new JPanel();
		

		l4.setBounds(40, 150, 150, 150);
		l5.setBounds(250, 150, 150, 150);
		l6.setBounds(460, 150, 150, 150);
		l7.setBounds(40, 150, 150, 150);
		l8.setBounds(250, 150, 150, 150);
		l10.setBounds(460, 150, 150, 150);
		String imageName1 = "C:/image3.jpg";
		String imageName2 = "C:/image4.jpg";
		String imageName3 = "C:/image5.jpg";
		String imageName4 = "C:/image6.jpg";
		String imageName5 = "C:/image8.jpg";
		String imageName7 = "C:/image7.jpg";
		try{
		l4.setIcon(new ImageIcon(ImageIO.read(new File(imageName1))));
		l5.setIcon(new ImageIcon(ImageIO.read(new File(imageName2))));
		l6.setIcon(new ImageIcon(ImageIO.read(new File(imageName3))));
		l7.setIcon(new ImageIcon(ImageIO.read(new File(imageName4))));
		l8.setIcon(new ImageIcon(ImageIO.read(new File(imageName5))));
		l10.setIcon(new ImageIcon(ImageIO.read(new File(imageName7))));
		}catch(IOException ex) {
			ex.printStackTrace();
		}
		p1.setLayout(null);
		p2.setLayout(null);
		p4.setLayout(null);
		b1.setBounds(0, 405, 175, 50);
		b2.setBounds(175, 405, 175, 50);
		b3.setBounds(350, 405, 175, 50);
		b4.setBounds(525, 405, 175, 50);
		b5.setBounds(40, 300, 150, 30);
		b6.setBounds(250, 300, 150, 30);
		b7.setBounds(460, 300, 150, 30);
		b8.setBounds(40, 300, 150, 30);
		b9.setBounds(250, 300, 150, 30);
		b10.setBounds(460, 300, 150, 30);
		p1.setBounds(0, 0, 700, 400);
		p2.setBounds(0, 0, 700, 400);
		p4.setBounds(0, 120, 700, 300);
		
		pf1.setBounds(260, 50, 250, 30);
		pf2.setBounds(260, 110, 250, 30);
		pf3.setBounds(260, 170, 250, 30);
		l1.setBounds(60, 50, 200, 30);
		l2.setBounds(60, 110, 200, 30);
		l3.setBounds(60, 170, 200, 30);
		b11.setBounds(120, 230, 200, 30);
		
		BufferedImage myImage = null;
		try {
			myImage = ImageIO.read(new File("C:/image2.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		f4.setContentPane(new ImagePanel(myImage));
		
		p1.setVisible(false);
		p2.setVisible(false);
		p4.setVisible(false);

		p1.setOpaque(false);
		p2.setOpaque(false);
		//p4.setOpaque(false);
		//p3.setBorder();
		p1.add(b5);
		p1.add(b6);
		p1.add(b7);
		p1.add(l4);
		p1.add(l5);
		p1.add(l6);
		p2.add(b8);
		p2.add(b9);
		p2.add(b10);
		p2.add(l7);
		p2.add(l8);
		p2.add(l10);
		p4.add(l1);
		p4.add(l2);
		p4.add(l3);
		p4.add(pf1);
		p4.add(pf2);
		p4.add(pf3);
		p4.add(b11);
		
		f4.add(b1);
		f4.add(b2);
		f4.add(b3);
		f4.add(b4);
		f4.add(p1);
		f4.add(p2);
		f4.add(p4);
		f4.setResizable(false);
		f4.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f4.setVisible(true);
		//Employee Button
		b1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent ae) {
				p2.setVisible(false);
				p4.setVisible(false);
				p1.setVisible(true);
			}
		});
		//Flights button
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				p1.setVisible(false);
				p4.setVisible(false);
				p2.setVisible(true);
				}
		});
		//Change Password button
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				p1.setVisible(false);
				p2.setVisible(false);
				p4.setVisible(true);
			}});
		//Logout Button
		b4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				f4.setVisible(false);
				new WelcomeScreenAndLogin();
			}
		});
		//Create employee button
		b5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//f4.setVisible(false);
				new CreateEmployee();
			}
		});
		//delete employee button
		b6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new DeleteEmployee();
			}
		});
		//viewEmployee
		b7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				//connect to database
				new ViewEmployees();
			}
		});
		// add flight button
		b8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new AddFlight();
			}
		});
		
		//delete flight button
		b9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new DeleteFlight();
			}
		});
		
		// change password button
		b11.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent ae) {
					if(pf2.getText().equals(pf3.getText())) {
						if(ChangeAdminPassword(pf1.getText(), pf2.getText())) {
						JOptionPane.showMessageDialog(p4, 
						"Password Updated Successfully", "Success!", JOptionPane.INFORMATION_MESSAGE);
						p4.setVisible(false);
						}
						else {
							JOptionPane.showMessageDialog(p4,
							"Old Password in incorrect", "Error", JOptionPane.ERROR_MESSAGE);
						}
					}
					else
						JOptionPane.showMessageDialog(f4, "New Passwords do not match",
								"Password Error", JOptionPane.ERROR_MESSAGE);
			}
		});
		
		//view flights button
		b10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				new ViewFlights();
			}
		});
		
	}// End of Constructor

	protected boolean ChangeAdminPassword(String oldPassword, String newPassword) {
		// TODO Auto-generated method stub
		if(validatePassword(oldPassword)) {
			try {
				PreparedStatement psmt = Connect.conn.prepareStatement("update admin " +
				"set password ='"+newPassword + "' where admin="+adminName);
				psmt.executeUpdate();
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}
			return true;
		}
		else {
			return false;
		}
	}
	
	private boolean validatePassword(String oldPassword) {
		// TODO Auto-generated method stub
		String pass=null;
		try {
			Statement smt = Connect.conn.createStatement();
			ResultSet rs = smt.executeQuery("select password from admin where admin ="+adminName);
			while(rs.next()) {
				pass = rs.getString("password");
			}
			if(pass.equals(oldPassword)) return true;
			else return false;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return false;
		}
	}

}
