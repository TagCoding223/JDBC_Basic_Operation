package Main_Package.Bank_Project_GUI;

import Main_Package.Bank_Project_Cmd.Accounts;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login extends JFrame implements ItemListener, ActionListener {
    Accounts accounts;
    Frame frame;
    Connection con;
    JLabel l_border,l_title,l_email,l_password;
    TextField tf_email,tf_password;
    Button b_login;
    Checkbox cb_show_hide;
    String email;
    Login(){
        con= new DB_Connection().getConnection();

        // Frame Properties
        setSize(500,250);
        setTitle("Banking System");
        setVisible(true);
//        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(500,280);
        setResizable(false);

        l_border=new JLabel();
        l_border.setBorder(BorderFactory.createLineBorder(Color.BLACK,1,true));
//        l_border.setBorder(BorderFactory.createEmptyBorder(2,5,2,5));
        l_border.setBounds(30,45,420,150);
        add(l_border);

        l_title=new JLabel("Login");
        l_title.setBounds(210,12,70,25);
        l_title.setFont(new Font("Arial",Font.BOLD,20));
        add(l_title);

        l_email=new JLabel("Email");
        l_email.setBounds(70,60,70,25);
        l_email.setFont(new Font("Arial",Font.BOLD,20));
        add(l_email);

        l_password=new JLabel("Password");
        l_password.setBounds(70,100,100,25);
        l_password.setFont(new Font("Arial",Font.BOLD,20));
        add(l_password);

        tf_email=new TextField();
        tf_email.setBounds(270,60,150,25);
        tf_email.setFont(new Font("Arial",Font.PLAIN,20));
        add(tf_email);

        tf_password=new TextField();
        tf_password.setBounds(270,100,150,25);
        tf_password.setFont(new Font("Arial",Font.PLAIN,20));
        tf_password.setEchoChar('*');
        add(tf_password);

        cb_show_hide=new Checkbox("Show/Hide Password",false);
        cb_show_hide.setBounds(280,130,140,25);
        cb_show_hide.setFont(new Font("Arial",Font.PLAIN,12));
        add(cb_show_hide);

        b_login=new Button("Login");
        b_login.setBounds(180,160,100,30);
        b_login.setFont(new Font("Arial",Font.BOLD,16));
        b_login.addActionListener(this);
        add(b_login);
    }

    public void itemStateChanged(ItemEvent e){
        if(e.getStateChange()==ItemEvent.SELECTED){
            tf_password.setEchoChar((char)0);
        }
        if(e.getStateChange()==ItemEvent.DESELECTED){
            tf_password.setEchoChar('*');
        }
    }
    int result,jresult;
    long account_number;
    public void actionPerformed(ActionEvent e){
        email=login();
        if(email!=null&&(!accounts.account_exist(email))){
            result=JOptionPane.showConfirmDialog(frame,"Do you want to open a new bank " +
                            "account ?",
                    "User Logged In",
                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            if(result==JOptionPane.YES_OPTION){
                account_number = accounts.open_account(email);
                jresult=JOptionPane.showConfirmDialog(frame,"Account Created Successfully !!!" +
                                ".\nYour Account number is : "+account_number,
                        "Account Details",
                        JOptionPane.OK_OPTION);
                System.out.println("\n Account Created Successfully !!!");
                System.out.println("Your Account number is : "+account_number);
            }
            if(result==JOptionPane.NO_OPTION){
                setVisible(false);
                new BankingApp_Home_GUI().setVisible(true);
            }
            if(jresult==JOptionPane.OK_OPTION){
                setVisible(false);
                new User_Platform().setVisible(true);
            }
        }
    }

    public String login(){
        String login_q="Select * from user where email=? and password=?;";
        try{
            PreparedStatement pstmt= con.prepareStatement(login_q);
            pstmt.setString(1,tf_email.getText());
            pstmt.setString(2,tf_password.getText());
            ResultSet rs=pstmt.executeQuery();
            if(rs.next()){
                return tf_email.getText();
            }else {
                return null;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    public static void main(String[] args) {
        new Login();
    }
}
