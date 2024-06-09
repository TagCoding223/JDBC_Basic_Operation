package Main_Package;

import javax.swing.*;
import java.awt.*;

public class Set_Image_Background extends JFrame {
    Set_Image_Background(){
        setSize(1000,800);
        setTitle("Image Background");
        setVisible(true);
        setLocation(275,30);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("Main_Package/icon/i1.jpg"));
//        Image i2= i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);
//        ImageIcon i3=new ImageIcon(i2);
//        JLabel l=new JLabel(i2,JLabel.CENTER);
        JLabel l=new JLabel(i1,JLabel.CENTER);
        l.setLayout(new FlowLayout());
        add(l);
        setSize(999,799);
        setSize(1000,800);
    }
    public static void main(String[] args) {
        new Set_Image_Background();
    }
}
