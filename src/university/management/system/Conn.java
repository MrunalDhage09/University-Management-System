package university.management.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;

    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");  // MySQL JDBC driver  
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem","root","Project@10");  
            s = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}