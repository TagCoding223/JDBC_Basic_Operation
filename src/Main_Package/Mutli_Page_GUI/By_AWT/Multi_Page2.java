package Main_Package.Mutli_Page_GUI.By_AWT;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Multi_Page2 extends Frame implements ActionListener {
    Multi_Page2(){
        setSize(400,400);
        setLocationRelativeTo(null);
        setTitle("Page 2");
        setLayout(new FlowLayout());
        setVisible(true);
        setBackground(Color.magenta);
        Label l=new Label("Page 2",Label.CENTER);
        add(l);
        System.out.println("Page 2");
        Button b=new Button("Next Page");
        add(b);
        b.addActionListener(this);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                System.exit(0);
            }
        });
    }

    public void actionPerformed(ActionEvent e){
        setVisible(false);
        new Multi_Page1().setVisible(true);
    }
//    public static void main(String[] args) {
//        new Multi_Page2();
//    }
}
