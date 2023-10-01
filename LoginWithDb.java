package Task_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.swing.JTextField;


public class LoginDb {
    //change according to your database 
    JTextField passField, usrNameField, urlField;
    String URL="jdbc:mysql://localhost:3306/";
    String userName="root";
    String password="28MySql#123";


    void Db_connection_Frame() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn;
            Statement stmt;
            
            conn = DriverManager.getConnection(URL, userName, password);
            // System.out.println("Connected to database successfully!");//checking
            stmt = conn.createStatement();
            // sql query to add student table
            String query = "CREATE DATABASE IF NOT EXISTS student_management";
            // executing the query using statement variable
            stmt.executeUpdate(query);
            
            System.out.println(query);
            // sql query to use database
            String query2 = "use student_management";
            stmt.executeUpdate(query2);
            // sql query to add faculty table
            String query3 = "CREATE TABLE IF NOT EXISTS Faculty ( "
                    + "admin_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "first_name VARCHAR(250),"
                    + "last_name VARCHAR(250),"
                    + "email VARCHAR(250),"
                    + "password VARCHAR(250),"
                    + "position VARCHAR(250)"
                    + " );";
            // executing the query using statement variable
            stmt.executeUpdate(query3);

            String query4 = "INSERT INTO faculty(first_name,last_name,email,password,position) values ('Admin','admin','admin@gmail.com','1203','Founder');";
            stmt.executeUpdate(query4);
             // sql query to add student table
            String query5 = "CREATE TABLE IF NOT EXISTS students ( "
                    + "student_id INT PRIMARY KEY AUTO_INCREMENT,"
                    + "first_name VARCHAR(250),"
                    + "last_name VARCHAR(250),"
                    + "Father_name VARCHAR(250),"
                    + "Dob VARCHAR(250),"
                    + "email VARCHAR(250),"
                    + "Contact VARCHAR(250),"
                    + "Subject  VARCHAR(250),"
                    + "year VARCHAR(250)"
                    + " );";
            // executing the query using statement variable
            stmt.executeUpdate(query5);
            System.out.println(query5);

        } catch (Exception ex) {
            System.err.print("\n" + ex);
        }
        
    }

    

}
