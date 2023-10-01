package Task_3;

import java.sql.*;

public class JDBC {
    String url;
    String userName;
    String pass;
    Connection connection;

    PreparedStatement stmt;

    public void setConnection() {
        LoginDb log = new LoginDb();
        try{
        url = log.URL+"student_management";
        System.out.println(url);
        userName = log.userName;
        pass = log.password;
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(url, userName, pass);
        System.out.println("Connection Succesfull : ");
        }catch(Exception e){
            System.out.println("Error in Connection : ");
        }
    }
}

