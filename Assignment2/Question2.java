package Assignment2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;


public class Question2 implements ActionListener {

	JLabel l1, l2, l3, l4, l5;
	JTextField t1, t2, t3, t4;
	JButton b1, b2, b3, b4, b5,b6;
	JRadioButton r1, r2;

	Question2() {
		// creating obj of java Frame
		JFrame fr = new JFrame("REGISTRATION FORM");
		fr.setVisible(true);
		fr.setSize(700, 500);
		fr.setLayout(null);

		// Assign Label(Entity)

		l1 = new JLabel("Id : ");
		l1.setBounds(100, 100, 120, 20);
		fr.add(l1);
		l2 = new JLabel("Name : ");
		l2.setBounds(100, 130, 120, 20);
		fr.add(l2);
		l3 = new JLabel("Gender : ");
		l3.setBounds(100, 160, 120, 20);
		fr.add(l3);
		l4 = new JLabel("Address : ");
		l4.setBounds(100, 190, 120, 20);
		fr.add(l4);
		l5 = new JLabel("Contact : ");
		l5.setBounds(100, 220, 120, 20);
		fr.add(l5);

		// assign text area
		t1 = new JTextField();
		t1.setBounds(180, 100, 120, 20);
		fr.add(t1);
		t2 = new JTextField();
		t2.setBounds(180, 130, 120, 20);
		fr.add(t2);
		t3 = new JTextField();
		t3.setBounds(180, 190, 120, 20);
		fr.add(t3);
		t4 = new JTextField();
		t4.setBounds(180, 220, 120, 20);
		fr.add(t4);

		r1 = new JRadioButton("Male");
		r1.setBounds(180, 160, 60, 20);

		r2 = new JRadioButton("Female");
		r2.setBounds(260, 160, 120, 20);
		ButtonGroup bg = new ButtonGroup();
		bg.add(r1);
		bg.add(r2);
		fr.add(r1);
		fr.add(r2);

		// assign Button name
		b1 = new JButton("Exit");
		b1.setBounds(100, 300, 100, 20);
		fr.add(b1);

		b2 = new JButton("Register");
		b2.setBounds(220, 300, 100, 20);
		fr.add(b2);
		b3 = new JButton("Delete");
		b3.setBounds(100, 330, 100, 20);
		fr.add(b3);
		b4 = new JButton("Update");
		b4.setBounds(220, 330, 100, 20);
		fr.add(b4);
		b5 = new JButton("Reset");
		b5.setBounds(100, 360, 100, 20);
		fr.add(b5);
		b6 = new JButton("Search");
		b6.setBounds(220, 360, 100, 20);
		fr.add(b6);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		
		
		
		
		
		
		
				

	   
	 
	
	
	
	}

	public static void main(String[] args) {
		new Question2();
	}

	// create Connection
	public static Connection createConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == b1) {
			System.out.println("Exit button clicked");
            System.exit(1);
            System.out.println("done");
		} else if (e.getSource() == b2) {
			System.out.println("Register button clicked");
			int id = Integer.parseInt(t1.getText());
			String name = t2.getText();
			String gender = "";
			if (r1.isSelected()) {
				gender = r1.getText();
			} else {
				gender = r2.getText();
			}
			String address = t3.getText();
			long contact = Long.parseLong(t4.getText());
			System.out.println("Id      : " + id);
			System.out.println("Name    : " + name);
			System.out.println("Gender  : " + gender);
			System.out.println("Contact : " + contact);
			System.out.println("Address : " + address);
			try {
                 Connection con=Question2.createConnection();
                 String sql="insert into student(id,name,gender,contact,address)values(?,?,?,?,?)";
                 PreparedStatement pst=con.prepareStatement(sql);
                 pst.setInt(1, id);
                 pst.setString(2, name);
                 pst.setString(3,gender);
                 pst.setLong(4, contact);
                 pst.setString(5, address);
                 pst.executeUpdate();
                 
                 t1.setText("");
 			 	 t2.setText("");
 			   	 t3.setText("");
 				 t4.setText("");
 				 r1.setSelected(false);
 				 r2.setSelected(false);
 			     System.out.println("Done");
                 
			} catch (Exception e2) {
				e2.printStackTrace();
			}

		} else if (e.getSource() == b3) {
		  System.out.println("Delete button clicked");
		  int id = Integer.parseInt(t1.getText());
			try {
				Connection con = Question2.createConnection();
				String sql = "delete from student where id=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				pst.executeUpdate();
				System.out.println("Data Deleted");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				r1.setSelected(false);
				r2.setSelected(false);

	     		} catch (Exception e2) {
	     		e2.printStackTrace();
			}
			} else if (e.getSource() == b4) {
			System.out.println("Update button cliked");
			int id = Integer.parseInt(t1.getText());
			String name = t2.getText();
			String gender = "";
			if (r1.isSelected()) {
				gender = r1.getText();
			} else {
				gender = r2.getText();
			}	
			String address = t3.getText();
	        long contact = Long.parseLong(t4.getText());
			try {
				Connection con = Question2.createConnection();
				String sql = "update student set name=?,gender=?.contact=?,address=? where id=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setString(1, name);
				pst.setString(2, gender);
				pst.setLong(3, contact);
				pst.setString(4, address);
			    pst.setInt(5, id);
				pst.executeUpdate();
				System.out.println("Data Updated");
				t1.setText("");
				t2.setText("");
				t3.setText("");
				t4.setText("");
				r1.setSelected(false);
                r2.setSelected(false);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
            

		} else if (e.getSource() == b5) {
	    System.out.println("Reset button clicked");
		t1.setText("");
		t2.setText("");
		t3.setText("");
		t4.setText("");
		r1.setSelected(false);
        r2.setSelected(false);
	    System.out.println("Reset done");
		}else if(e.getSource() == b6) {
			int id = Integer.parseInt(t1.getText());
			try {
				Connection con = Question2.createConnection();
				String sql = "select * from user where id=?";
				PreparedStatement pst = con.prepareStatement(sql);
				pst.setInt(1, id);
				ResultSet rs = pst.executeQuery();
				if (rs.next()) {
					t1.setText(String.valueOf(rs.getInt("id")));
		 			t2.setText(rs.getString("name"));
		            			
					t3.setText(String.valueOf(rs.getLong("contact")));
					t4.setText(rs.getString("address"));
					}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		
		}

	}
}
