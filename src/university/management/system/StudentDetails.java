package university.management.system;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.event.*;

public class StudentDetails extends JFrame implements ActionListener {

    Choice crollno;
    JTable table;
    JButton searchButton, printButton, updateButton, addButton, cancelButton;

    StudentDetails() {

        // Frame setup
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Heading
        JLabel heading = new JLabel("Search by Roll Number");
        heading.setBounds(20, 20, 150, 20);
        add(heading);

        // Roll number Choice
        crollno = new Choice();
        crollno.setBounds(180, 20, 150, 20);
        add(crollno);

        // Populate Choice with roll numbers from database
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            while (rs.next()) {
                crollno.add(rs.getString("rollno"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading roll numbers: " + e.getMessage());
        }

        // Table setup
        table = new JTable();
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("SELECT * FROM student");
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error loading student details: " + e.getMessage());
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0, 100, 900, 600);
        add(jsp);

        // Buttons
        searchButton = new JButton("Search");
        searchButton.setBounds(20, 70, 80, 20);
        searchButton.addActionListener(this);
        add(searchButton);

        printButton = new JButton("Print");
        printButton.setBounds(120, 70, 80, 20);
        printButton.addActionListener(this);
        add(printButton);

        addButton = new JButton("Add");
        addButton.setBounds(220, 70, 80, 20);
        addButton.addActionListener(this);
        add(addButton);

        updateButton = new JButton("Update");
        updateButton.setBounds(320, 70, 80, 20);
        updateButton.addActionListener(this);
        add(updateButton);

        cancelButton = new JButton("Cancel");
        cancelButton.setBounds(420, 70, 80, 20);
        cancelButton.addActionListener(this);
        add(cancelButton);

        // Frame properties
        setSize(900, 700);
        setLocation(300, 100);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == searchButton) {
            String query = "SELECT * FROM student WHERE rollno = '" + crollno.getSelectedItem() + "'";
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error during search: " + e.getMessage());
            }

        } else if (ae.getSource() == printButton) {
            try {
                table.print();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Error printing table: " + e.getMessage());
            }

        } else if (ae.getSource() == addButton) {
            setVisible(false);
            new AddStudent();

        } else if (ae.getSource() == updateButton) {
            setVisible(false);
            new UpdateStudent();

        } else if (ae.getSource() == cancelButton) {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new StudentDetails();
    }
}
