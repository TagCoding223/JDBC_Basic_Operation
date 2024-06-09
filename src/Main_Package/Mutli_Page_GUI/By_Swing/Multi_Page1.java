package Main_Package.Mutli_Page_GUI.By_Swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Multi_Page1 extends JFrame implements ActionListener {
    JButton b1,b2;
    Multi_Page1(){
        setLayout(new FlowLayout());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Page 1");
//        setLocationRelativeTo(null);
        setLocation(550,200);// In jframe this method use to set frame opening position
        getContentPane().setBackground(Color.RED);
        setSize(400,400);
        System.out.println("Page 1");
        JLabel l=new JLabel("Label 1");
        add(l);

        b1=new JButton("Next Page");
        add(b1);


        b2=new JButton("Error Message");
        add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==b1) {
            setVisible(false);
            new Multi_Page2().setVisible(true);
        }
        if(e.getSource()==b2){
            JOptionPane.showMessageDialog(null,"This is error Box");
        }
    }

    public static void main(String[] args) {
        new Multi_Page1().setVisible(true);
    }
}
