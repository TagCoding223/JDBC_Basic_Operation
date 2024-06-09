package Main_Package.Bank_Project_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Register extends JFrame implements ItemListener, ActionListener {
    JLabel l_first_name,l_last_name,l_email,l_password,title;

    TextField tf_first_name,tf_last_name,tf_email,tf_password;

    Checkbox cb_show_p;
    Button b_submit,b_clear_all,b_back;
    Connection con;
    Frame frame=new Frame();
    Register(){
        con=new DB_Connection().getConnection();
        setSize(1000,700);
        setTitle("Banking System");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(250,80);
        setResizable(false);

        // Frame Background
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Main_Package/icon/i5.jpg"));
//        Image i2=i1.getImage().getScaledInstance(1000,700,Image.);
//        ImageIcon i3=new ImageIcon(i2);
        JLabel background=new JLabel(i1,JLabel.CENTER);

        setSize(999,699);
        setSize(1000,700);

        //Frame Objects
        //Page title
        title=new JLabel("New User Registration");
        title.setForeground(Color.cyan);
        title.setFont(new Font("Arial",Font.BOLD,38));
        title.setBounds(310,60,450,50);
        background.add(title);


        //label First name
        l_first_name=new JLabel("First Name");
        l_first_name.setForeground(Color.WHITE);
        l_first_name.setFont(new Font("Arial",Font.BOLD,28));
        l_first_name.setBounds(200,190,200,50);
        background.add(l_first_name);
        //textField First name
        tf_first_name=new TextField();
        tf_first_name.setFont(new Font("Arial",Font.BOLD,28));
        tf_first_name.setBounds(630,190,200,40);
        background.add(tf_first_name);


        //label Last name
        l_last_name=new JLabel("Last Name");
        l_last_name.setForeground(Color.WHITE);
        l_last_name.setFont(new Font("Arial",Font.BOLD,28));
        l_last_name.setBounds(200,250,200,50);
        background.add(l_last_name);
        //textField last name
        tf_last_name=new TextField();
        tf_last_name.setFont(new Font("Arial",Font.BOLD,28));
        tf_last_name.setBounds(630,250,200,40);
        background.add(tf_last_name);


        //label Email
        l_email=new JLabel("Email");
        l_email.setForeground(Color.WHITE);
        l_email.setFont(new Font("Arial",Font.BOLD,28));
        l_email.setBounds(200,310,200,50);
        background.add(l_email);
        //textField email
        tf_email=new TextField();
        tf_email.setFont(new Font("Arial",Font.BOLD,28));
        tf_email.setBounds(630,310,200,40);
        background.add(tf_email);


        //label Password
        l_password=new JLabel("Password");
        l_password.setForeground(Color.WHITE);
//        l_password.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        l_password.setFont(new Font("Arial",Font.BOLD,28));
        l_password.setBounds(200,370,200,50);
        background.add(l_password);
        //textField password
        tf_password=new TextField();
        tf_password.setFont(new Font("Arial",Font.BOLD,28));
        tf_password.setBounds(630,370,200,40);
        tf_password.setEchoChar('*');
        background.add(tf_password,TextField.CENTER_ALIGNMENT);

        // Checkbox show password
        cb_show_p=new Checkbox("Show Password",false);
        cb_show_p.setBounds(650,440,130,30);
        cb_show_p.setFont(new Font("Monospace",Font.PLAIN,15));
//        cb_show_p.setBackground();
        background.add(cb_show_p);


        // Back button
        b_back=new Button("Back");
        b_back.setBounds(270,520,100,50);
        b_back.setFont(new Font("Monospace",Font.PLAIN,18));
        background.add(b_back);

        // Clear all button
        b_clear_all=new Button("Clear All");
        b_clear_all.setBounds(630,520,100,50);
        b_clear_all.setFont(new Font("Monospace",Font.PLAIN,18));
        background.add(b_clear_all);

        // Submit button
        b_submit=new Button("Submit");
        b_submit.setBounds(450,520,100,50);
        b_submit.setFont(new Font("Monospace",Font.PLAIN,18));
        background.add(b_submit);


//        cb_show_p.addMouseListener(this);

        add(background);
        cb_show_p.addItemListener(this);
//        b_submit.addMouseListener(this);
//        b_clear_all.addMouseListener(this);

        b_back.addActionListener(this);
        b_submit.addActionListener(this);
        b_clear_all.addActionListener(this);
    }


    public void itemStateChanged(ItemEvent e){
        if(e.getStateChange()== ItemEvent.SELECTED){
            tf_password.setEchoChar((char)0);//zero display origin words
//            System.out.println(tf_password.getText());
        }else{
            tf_password.setEchoChar('*');
        }
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b_clear_all){
            tf_password.setText("");
            tf_email.setText("");
            tf_last_name.setText("");
            tf_first_name.setText("");
        }
        if(e.getSource()==b_submit){
            String full_name,email,password;
            full_name=tf_first_name.getText()+" "+tf_last_name.getText();
            email=tf_email.getText();
            password=tf_password.getText();
            if(user_exist(email)){
                System.out.println("\nUser Already Exists for this Email Address!!!");
                JOptionPane.showMessageDialog(frame,"User Already Exists for this Email " +
                        "Address!!!","Error",JOptionPane.OK_CANCEL_OPTION);
            }
            String register_query="Insert into user(full_name,email,password) values (?,?,?);";
            try {
                PreparedStatement pstmt= con.prepareStatement(register_query);
                pstmt.setString(1,full_name);
                pstmt.setString(2,email);
                pstmt.setString(3,password);
                int affectedRows=pstmt.executeUpdate();
                if(affectedRows>0){
                    System.out.println("Registration Successfully !!!");
                    JOptionPane.showMessageDialog(frame,"Registration Successfully !!!",
                            "Message",JOptionPane.OK_CANCEL_OPTION);
                    setVisible(false);
                    new BankingApp_Home_GUI().setVisible(true);
                }else{
                    System.out.println("registration Failed !!!");
                    JOptionPane.showMessageDialog(frame,"Registration Failed !!!" ,
                            "Message",JOptionPane.OK_CANCEL_OPTION);
                }
            }catch (Exception es){
                System.out.println("Duplicate entry '"+email+"' .");
            }
        }
        if (e.getSource()==b_back){
            setVisible(false);
            new BankingApp_Home_GUI().setVisible(true);
        }
    }

    public boolean user_exist(String email){
        String check_q="Select * from user where email=?;";
        try{
            PreparedStatement pstmt = con.prepareStatement(check_q);
            pstmt.setString(1,email);
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }
}
