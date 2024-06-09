package Main_Package.Bank_Project_GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BankingApp_Home_GUI extends JFrame implements  MouseListener {
    JLabel title,option1,option2,option3;
    BankingApp_Home_GUI(){
        // Frame Properties
        setSize(1000,700);
        setTitle("Banking System");
        setVisible(true);
//        setLayout(new BorderLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocation(250,80);
        setResizable(false);

        // Frame Background
        ImageIcon i=new ImageIcon(ClassLoader.getSystemResource("Main_Package/icon/i3.jpg"));
        JLabel background=new JLabel(i,JLabel.CENTER);
//        background.setLayout(new FlowLayout());

        setSize(999,699);
        setSize(1000,700);

        //Frame Objects

        //Page title
        title=new JLabel("Banking System");
        title.setFont(new Font("Arial",Font.BOLD,38));
        title.setBounds(345,40,450,50);
        background.add(title);

        //Option 1
        option1=new JLabel("Register",JLabel.CENTER);
        option1.setFont(new Font("Arial",Font.BOLD,38));
        option1.setForeground(Color.GREEN);
        option1.setOpaque(true);// without this background not possible
        option1.setBackground(Color.GRAY);
        option1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        option1.setBounds(160,200,200,100);
        background.add(option1);

        //Option 2
        option2=new JLabel("Login",JLabel.CENTER);
        option2.setFont(new Font("Arial",Font.BOLD,38));
        option2.setForeground(Color.GREEN);
        option2.setOpaque(true);// without this background not possible
        option2.setBackground(Color.GRAY);
        option2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        option2.setBounds(640,200,200,100);
        background.add(option2);

        //Option 3
        option3=new JLabel("Exit",JLabel.CENTER);
        option3.setFont(new Font("Arial",Font.BOLD,38));
        option3.setForeground(Color.GREEN);
        option3.setOpaque(true);// without this background not possible
        option3.setBackground(Color.GRAY);
        option3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        option3.setBounds(405,400,200,100);
        background.add(option3);

        add(background);
        option1.addMouseListener(this);
        option2.addMouseListener(this);
        option3.addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==option1){
            setVisible(false);
            new Register().setVisible(true);
        }
        if(e.getSource()==option2){
            setVisible(false);
//            new Login().setVisible(true);
        }
        if(e.getSource()==option3){
//            int result=JOptionPane.showConfirmDialog(frame,"You want to exit ?","Confirm Exit",
//                    JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
            new Exit();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JLabel refer= (JLabel) e.getSource();
        refer.setFont(new Font("Arial",Font.BOLD,42));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JLabel refer=(JLabel) e.getSource();
        refer.setFont(new Font("Arial",Font.BOLD,38));
    }

    public static void main(String[] args) {
        new BankingApp_Home_GUI();
    }
}
