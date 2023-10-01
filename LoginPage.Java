package Task_3;
//2nd page

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
// import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class Login extends JFrame {
    JLabel label;
    JLabel imgLabel;
    JLabel emailLabel, passwordLabel;
    JTextField emailField, passField;
    JButton sign_up, close, addConnection;
    ButtonListener btnListener;
    boolean status = false;
    int count = 0;
    JDBC jdbc= new JDBC();

    // constructor
    Login() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Login Page");

        // Get the content pane
        Container contentPane = getContentPane();
        contentPane.setBackground(new Color(11, 12, 16));

        this.setBounds(300, 100, 900, 600);
        this.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(69, 162, 158));
        panel.setBounds(0, 0, 300, 600);
        panel.setLayout(new BorderLayout());// sets the layout manager for the JPanel named "panel" to use the
                                            // BorderLayout. In Java Swing, a layout manager is responsible for
                                            // determining how components are arranged within a container (such as a
                                            // JPanel or a JFrame).

        imgLabel = new JLabel(new ImageIcon("Task_3/Images/user (1).png"));
        imgLabel.setBounds(50, 90, 200, 200);

        label = new JLabel("<html><h1>Admin Sign-up</h1></html>");
        label.setForeground(new Color(11, 12, 16));
        label.setBounds(70, 300, 200, 30);

        emailLabel = new JLabel("<html><h2>Email : </h2></html>");
        emailLabel.setBounds(420, 80, 250, 30);
        emailLabel.setForeground(new Color(102, 252, 241));

        emailField = new JTextField(10);
        emailField.setBounds(420, 130, 300, 30);

        passwordLabel = new JLabel("<html><h2>Password : </h2></html>");
        passwordLabel.setBounds(420, 190, 250, 30);
        passwordLabel.setForeground(new Color(102, 252, 241));

        passField = new JTextField(10);
        passField.setBounds(420, 240, 300, 30);

        sign_up = new JButton("Sign up");
        sign_up.setBounds(420, 420, 100, 30);
        // let's set the location and size of buttons & color
        sign_up.setBorder(BorderFactory.createEtchedBorder());
        sign_up.setBorder(new LineBorder(new Color(102, 252, 241)));
        sign_up.setBackground(new Color(11, 12, 16));
        sign_up.setForeground(new Color(102, 252, 241));
        btnListener = new ButtonListener();
        sign_up.addActionListener(btnListener);
        sign_up.setFocusable(false);

        close = new JButton("Cancel");
        close.setBounds(620, 420, 100, 30);
        // let's set the location and size of buttons & color
        close.setBorder(BorderFactory.createEtchedBorder());
        close.setBorder(new LineBorder(new Color(102, 252, 241)));
        close.setBackground(new Color(11, 12, 16));
        close.setForeground(new Color(102, 252, 241));
        close.addActionListener(btnListener);
        close.setFocusable(false);

        addConnection = new JButton("Connection");// to create database connectivity (database, tables, insertion)
        addConnection.setBounds(730, 10, 150, 30);
        // let's set the location and size of buttons & color
        addConnection.setBorder(BorderFactory.createEtchedBorder());
        addConnection.setBorder(new LineBorder(new Color(102, 252, 241)));
        addConnection.setBackground(new Color(11, 12, 16));
        addConnection.setForeground(new Color(102, 252, 241));
        addConnection.addActionListener(btnListener);
        addConnection.setFocusable(false);

        contentPane.add(addConnection);
        contentPane.add(close);
        contentPane.add(sign_up);
        contentPane.add(passField);
        contentPane.add(passwordLabel);
        contentPane.add(emailField);
        contentPane.add(emailLabel);
        contentPane.add(imgLabel);
        contentPane.add(label);
        contentPane.add(panel);
        this.setVisible(true);
    }

    private class ButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String com = e.getActionCommand();// getting the action command of button in String
            System.out.println(com);// checking
            if (com.equals("Connection")) {
                new LoginDb().Db_connection_Frame();
            } else if (com.equals("Sign up")) {
                // checking email & password matches with database entry
                String emailLogin = emailField.getText();
                String passwordLogin = passField.getText();
                System.out.println(emailLogin);
                System.out.println(passwordLogin);
                try {
                    jdbc .setConnection();
                    System.out.println("Connected successfully!");
                    Statement stmt;
                    stmt = jdbc.connection.createStatement();
                    PreparedStatement ps = jdbc.connection
                            .prepareStatement("select * from faculty where email = ? and password  = ?");
                    ps.setString(1, emailLogin);
                    ps.setString(2, passwordLogin);
                    ResultSet rs1 = ps.executeQuery();
                    System.out.println(rs1);
                    status = rs1.next();
                    System.out.println(status);
                    ImageIcon icon = new ImageIcon("Task_3/Images/warning.png");
                    if (status == true) {
                        count = JOptionPane.showOptionDialog(null, "Successfully signed up", "Connection",
                                JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, icon, null, 0);
                        if (count == 0) {
                            new Student();
                            dispose();
                        }
                    } else {
                        JOptionPane.showOptionDialog(null, "Invalid Email & Password", "Connection",
                                JOptionPane.CLOSED_OPTION, JOptionPane.WARNING_MESSAGE, icon, null, 0);
                    }

                } catch (Exception exception) {
                    exception.getStackTrace();
                }
            } else {
                System.exit(0);
            }
        }

    }
}
