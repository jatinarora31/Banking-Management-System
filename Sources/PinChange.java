package bank.managemant.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class PinChange extends JFrame implements ActionListener{
    
    JPasswordField pin,repin;
    JButton change,back;
    String pinnumber;
    
    PinChange(String pinnumber) {
        this.pinnumber = pinnumber;
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/atm.jpg"));
        Image i2 = i1.getImage().getScaledInstance(900,850,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(0,0,900,800);
        add(image);
        
        JLabel text = new JLabel("CHANGE YOUR PIN");
        text.setForeground(Color.WHITE);
        text.setFont(new Font("System",Font.BOLD,16));
        text.setBounds(250,260,500,35);
        image.add(text);
        
        JLabel pintext = new JLabel("New PIN:");
        pintext.setForeground(Color.WHITE);
        pintext.setFont(new Font("System",Font.BOLD,16));
        pintext.setBounds(165,310,180,25);
        image.add(pintext);
        
        pin = new JPasswordField();
        pin.setFont(new Font("Raleway",Font.BOLD,25));
        pin.setBounds(330,310,180,25);
        image.add(pin);
        
        JLabel repintext = new JLabel("Re-Enter New PIN:");
        repintext.setForeground(Color.WHITE);
        repintext.setFont(new Font("System",Font.BOLD,16));
        repintext.setBounds(165,345,180,25);
        image.add(repintext);
        
        repin = new JPasswordField();
        repin.setFont(new Font("Raleway",Font.BOLD,25));
        repin.setBounds(330,345,180,25);
        image.add(repin);
        
        change = new JButton("CHANGE");
        change.setBounds(355,450,150,30);
        change.addActionListener(this);
        image.add(change);
        
        back = new JButton("BACK");
        back.setBounds(355,485,150,30);
        back.addActionListener(this);
        image.add(back);
        
        setSize(900,900);
        setLocation(300,0);
        
        setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == change) {
        try {
            String npin = pin.getText();
            String rpin = repin.getText();
            if (!npin.equals(rpin)) {
                JOptionPane.showMessageDialog(null,"Entered PIN doesn't match");
                return;
            }
            
            pin.setText("");
            repin.setText("");
            
            if (npin.equals("")) {
                JOptionPane.showMessageDialog(null,"Please enter PIN");
                return;
            }
            if(rpin.equals("")) {
                JOptionPane.showMessageDialog(null,"Please re-enter new PIN");
                return;
            }
            if (npin.matches("[a-zA-Z]+") || rpin.matches("[a-zA-Z]+")) {
                JOptionPane.showMessageDialog(null,"Please enter a Numerical Value (Ex. 0 to 9)");
                return;
                }
            
            if(rpin.length()>4) {
                JOptionPane.showMessageDialog(null,"Please re-enter 4 digit PIN");
                return;
            }
            
            pin.setText("");
            repin.setText("");
            
            JOptionPane.showMessageDialog(null,"PIN changed successfully");
            
            
            Conn conn = new Conn();
            String query1 = "update bank set pin = '"+rpin+"' where pin = '"+pinnumber+"'";
            String query2 = "update login set pin = '"+rpin+"' where pin = '"+pinnumber+"'";
            String query3 = "update signupthree set pin = '"+rpin+"' where pin = '"+pinnumber+"'";
            
            conn.s.executeUpdate(query1);
            conn.s.executeUpdate(query2);
            conn.s.executeUpdate(query3);
            
            
            
            
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        
        } catch (Exception e) {
            System.out.println(e);
            }
        }else {
            setVisible(false);
            new Transactions(pinnumber).setVisible(true);
        }
    }
    
    public static void main (String args[]) {
        new PinChange("").setVisible(true);
    }
}