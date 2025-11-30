
package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {

    JTextField tfusername;
    JPasswordField tfpassword;
    JButton login, cancel;

    Login() {

        setLayout(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel lblusername = new JLabel("Username");
        lblusername.setBounds(40, 20, 100, 30);
        add(lblusername);

        tfusername = new JTextField();
        tfusername.setBounds(150, 20, 150, 30);
        add(tfusername);

        JLabel lblpassword = new JLabel("Password");
        lblpassword.setBounds(40, 70, 100, 30);
        add(lblpassword);

        tfpassword = new JPasswordField();
        tfpassword.setBounds(150, 70, 150, 30);
        add(tfpassword);

        login = new JButton("Login");
        login.setBounds(40, 140, 120, 30);
        login.addActionListener(this);
        add(login);

        cancel = new JButton("Cancel");
        cancel.setBounds(180, 140, 120, 30);
        cancel.addActionListener(this);
        add(cancel);

        // OPTIONAL IMAGE
        try {
            ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/first.jpg"));
            Image i2 = i1.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            JLabel img = new JLabel(new ImageIcon(i2));
            img.setBounds(350, 0, 200, 200);
            add(img);
        } catch (Exception e) {
            System.out.println("Image not found");
        }

        setSize(600, 300);
        setLocation(500, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == login) {

            String user = tfusername.getText();
            String pass = new String(tfpassword.getPassword());

            if (user.equals("admin") && pass.equals("1234")) {
                JOptionPane.showMessageDialog(null, "Login Success");
                setVisible(false);

                // 🚀 OPEN DASHBOARD
                new Project();

            } else {
                JOptionPane.showMessageDialog(null, "Invalid Login");
            }

        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new Login();
    }
}