import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


import javax.swing.*;

class DemoForm extends Frame{
	
	Label heading, firstName, lastName, gender, email, password, phoneNumber,city;
	TextField firstNameText, lastNameText, emailText, passwordText, phoneNumberText;
	Checkbox M,F;
	CheckboxGroup cb;
	Choice cityChoice;
	Button btn;
	
	
	DemoForm(){	
		
		// Layout 
		
		setLayout(null);		
		setBounds(450,150,400,450);
		setBackground(new Color(255, 135, 135));
		setResizable(false);
		setVisible(true);
		
		
		// Popup Layout
		
		JFrame frame = new JFrame();
	    frame.setSize(200, 200);
	    frame.setLocationRelativeTo(null);
	    frame.setVisible(false);
		
	    
	    // Font
	    
		setFont(new Font("Courier New", Font.BOLD,12));
		
		
		// Heading
		
		heading = new Label("Registration Form");
		heading.setBounds(150,50,110,50);
		add(heading);
		
		
		// First Name
		
		firstName = new Label("First Name : ");
		firstName.setBounds(90,120,110,20);
		add(firstName);
		
		firstNameText = new TextField();
		firstNameText.setBounds(200,120,110,20);
		add(firstNameText);
		
		
		// Last Name
		
		lastName = new Label("Last Name : ");
		lastName.setBounds(90,150,110,20);
		add(lastName);
		
		lastNameText = new TextField();
		lastNameText.setBounds(200,150,110,20);
		add(lastNameText);
		
		
		// Gender -  Radio Button
		
		gender = new Label("Gender : ");
		gender.setBounds(90,180,110,20);
		add(gender);
		
		
		cb = new CheckboxGroup();
		M = new Checkbox("Male",cb,true);
		F = new Checkbox("Female",cb,false);
		M.setBounds(200,180,40,22);
		F.setBounds(250,180,55,22);
		add(M);
		add(F);
		
		
		// Email
		
		email = new Label("Email : ");
		email.setBounds(90,210,110,20);
		add(email);
		
		emailText = new TextField();
		emailText.setBounds(200,210,110,20);
		add(emailText);
		
		
		// Password
		
		password = new Label("Password : ");
		password.setBounds(90,240,110,20);
		add(password);
		
		passwordText = new TextField();
		passwordText.setBounds(200,240,110,20);
		add(passwordText);
		passwordText.setEchoChar('✶');
		
		
		// Phone Number
		
		phoneNumber = new Label("Phone Number : ");
		phoneNumber.setBounds(90,270,110,20);
		add(phoneNumber);
		
		phoneNumberText = new TextField();
		phoneNumberText.setBounds(200,270,110,20);
		add(phoneNumberText);
		
		
		// City - Dropdown Menu
		
		city = new Label("City : ");
		city.setBounds(90,300,110,20);
		add(city);
		
		cityChoice = new Choice();
		add(cityChoice);
		cityChoice.setBounds(200,300,110,20);
		cityChoice.add("");
		cityChoice.add("Coimbatore");
		cityChoice.add("Chennai");
		cityChoice.add("Salem");
		cityChoice.add("Madurai");
		cityChoice.add("Trichy");
		
		
		//Submit button 
		
		btn = new Button("Submit");
		btn.setBounds(150,360,100,30);
		add(btn);
		
		
		// Validation of the fields
		
	
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				String firstName = firstNameText.getText();
				String lastName = lastNameText.getText();
				String email = emailText.getText();
				String password = passwordText.getText();
				String phoneNumber = phoneNumberText.getText();
				String gender = cb.getSelectedCheckbox().getLabel();
				String city = cityChoice.getSelectedItem().toString();
				
				int flag = 1;
				
				//First name validation 
				
				String firstNamePat ="^[a-zA-Z]{2,30}$";
				Pattern fnPat = Pattern.compile(firstNamePat);
				Matcher fnmat = fnPat.matcher(firstName);
				
				if(firstName.length()==0 && flag == 1 ) {
					JOptionPane.showMessageDialog(frame, "Please Enter First Name!");
					firstNameText.setBackground(Color.yellow);
					flag = 0;
				}
				else if(!fnmat.matches() && flag == 1) {
					JOptionPane.showMessageDialog(frame, "Invalid First Name!");
					firstNameText.setBackground(Color.yellow);
					flag = 0;
				}
				else if(flag == 1){
					firstNameText.setBackground(Color.white);
					flag = 1;
				}
				
				
				//Last name validation 
				
				String lastNamePat ="^[a-zA-Z]{1,30}$";
				Pattern lnPat = Pattern.compile(lastNamePat);
				Matcher lnmat = lnPat.matcher(lastName);
				
				if(lastName.length()==0 && flag == 1) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Please Enter Last Name!");
					firstNameText.setBackground(Color.yellow);
				}
				else if(!lnmat.matches() && flag==1) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Invalid Last Name!");
					lastNameText.setBackground(Color.yellow);
				}
				else if(flag == 1){
					flag = 1;
					lastNameText.setBackground(Color.white);
				}
				
				// Gender validation
				
				if(M.getState()==false && F.getState()==false && flag==1){
					flag = 0;
					JOptionPane.showMessageDialog(frame,"Please Select Gender");
				}
				else if(flag == 1){
					flag=1;
				}

				
				
				//email validation
				
				String emailPat = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
				Pattern empat = Pattern.compile(emailPat);
				Matcher emailmat = empat.matcher(email);
				
				if(email.length()==0 && flag == 1) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Please Enter Email!");
					emailText.setBackground(Color.yellow);
				}
				else if(!emailmat.matches() && flag == 1) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Invalid Email!");
					emailText.setBackground(Color.yellow);
				}
				else if(flag == 1){
					flag = 1;
					emailText.setBackground(Color.white);
				}
				
				
				// Password validation
				
				if(password.length()==0 && flag == 1) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Please Enter The Password!");
					passwordText.setBackground(Color.yellow);
				}
				else if(password.length()>0 && password.length()<=8) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Password's length must be greater than 8");
					passwordText.setBackground(Color.yellow);
				}
				else if(flag == 1){
					flag = 1;
					passwordText.setBackground(Color.white);
				}
				
				
				//Phone number validation
				
				String phonepat = "[9876][0-9]{9}$";
				Pattern phpat = Pattern.compile(phonepat);
				Matcher phmat = phpat.matcher(phoneNumber);
				
				if(phoneNumber.length()==0 && flag == 1) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Please Enter Phone Number!");
					phoneNumberText.setBackground(Color.yellow);
				}
				else if(!phmat.matches() && flag == 1) {
					flag = 0;
					JOptionPane.showMessageDialog(frame, "Invalid Phone Number!");
					phoneNumberText.setBackground(Color.yellow);
				}
				else if(flag == 1){
					flag = 1;
					phoneNumberText.setBackground(Color.white);
				}
				
				
				// City Validation
				
				if((cityChoice.getSelectedItem().equals(""))&& flag == 1){
					flag=0;
					JOptionPane.showMessageDialog(frame,"Please Select City");
				}
				else if(flag == 1){
					flag=1;
				}
				
				
				// Inserting the Values in the DataBase
				
				if(flag==1) {
						try {
							Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/registrationform","root","Vishwa@222");
							Statement st = con.createStatement();
							
							PreparedStatement p = con.prepareStatement("Select email from user where email=?");
							p.setString(1, email);
							ResultSet rs = p.executeQuery();
							
							
							// Checking whether the email is unique
							
							String tempEmail= "";
							boolean flag1 = false; 
							if(rs.next()) {
								tempEmail = rs.getString("email");
								if(tempEmail.equals(email)) {
									emailText.setText("");
									JOptionPane.showMessageDialog(frame,"Email Already Exists!");
									flag1=true;
								}
							}
							
							if(flag1==false) {
								PreparedStatement p1 = con.prepareStatement("insert into user values(?,?,?,?,?,?,?)");
								p1.setString(1, firstName);
								p1.setString(2, lastName);
								p1.setString(3, gender);
								p1.setString(4, email);
								p1.setString(5, password);
								p1.setString(6, phoneNumber);
								p1.setString(7, city);
								p1.executeUpdate();
								JOptionPane.showMessageDialog(frame,"Submitted Succesfully!");
							}
							else {
								flag1=false;
							}
						}
						
						catch (Exception e1) {
							System.out.println(e1);
						}
				}
			}
			
		});
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
		
	}
	
	public static void main(String[] args) throws SQLException {
		new DemoForm();
	
	}
}
