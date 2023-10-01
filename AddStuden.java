package Task_3;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

public class AddStudent implements ActionListener {
    JDBC jdbc;
    ResultSet resultSet;
    Student std = new Student();
    JLabel Fname, Lname, FatherName, DateofBrth, emailLabel, contct, subj, year;
    JTextField fNameField, lNameField, fatherNameField, dobField, emailField, contactField, subjField, yearField;
    JButton add;
    Validation valid = new Validation();

    AddStudent() {
        Fname = new JLabel("First Name :");
        Fname.setFont(new Font("monospaced", Font.BOLD, 12));
        Fname.setBounds(400, 500, 100, 30);
        Fname.setForeground(new Color(102, 252, 241));

        fNameField = new JTextField(10);
        fNameField.setBounds(550, 500, 200, 30);
        fNameField.setBackground(new Color(197, 198, 199));
        fNameField.setBorder(BorderFactory.createEtchedBorder());
        fNameField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        Lname = new JLabel("Last Name :");
        Lname.setFont(new Font("monospaced", Font.BOLD, 12));
        Lname.setBounds(800, 500, 100, 30);
        Lname.setForeground(new Color(102, 252, 241));

        lNameField = new JTextField(10);
        lNameField.setBounds(900, 500, 200, 30);
        lNameField.setBackground(new Color(197, 198, 199));
        lNameField.setBorder(BorderFactory.createEtchedBorder());
        lNameField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        FatherName = new JLabel("Father Name :");
        FatherName.setFont(new Font("monospaced", Font.BOLD, 12));
        FatherName.setBounds(800, 550, 100, 30);
        FatherName.setForeground(new Color(102, 252, 241));

        fatherNameField = new JTextField(10);
        fatherNameField.setBounds(900, 550, 200, 30);
        fatherNameField.setBackground(new Color(197, 198, 199));
        fatherNameField.setBorder(BorderFactory.createEtchedBorder());
        fatherNameField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        DateofBrth = new JLabel("Date Of Birth :");
        DateofBrth.setFont(new Font("monospaced", Font.BOLD, 11));
        DateofBrth.setBounds(400, 650, 100, 30);
        DateofBrth.setForeground(new Color(102, 252, 241));

        dobField = new JTextField(10);
        dobField.setBounds(550, 650, 200, 30);
        dobField.setBackground(new Color(197, 198, 199));
        dobField.setBorder(BorderFactory.createEtchedBorder());
        dobField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        emailLabel = new JLabel("Email :");
        emailLabel.setFont(new Font("monospaced", Font.BOLD, 11));
        emailLabel.setBounds(400, 700, 100, 30);
        emailLabel.setForeground(new Color(102, 252, 241));

        emailField = new JTextField(10);
        emailField.setBounds(550, 700, 200, 30);
        emailField.setBackground(new Color(197, 198, 199));
        emailField.setBorder(BorderFactory.createEtchedBorder());
        emailField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        contct = new JLabel("Contact :");
        contct.setFont(new Font("monospaced", Font.BOLD, 11));
        contct.setBounds(400, 550, 100, 30);
        contct.setForeground(new Color(102, 252, 241));

        contactField = new JTextField(10);
        contactField.setBounds(550, 550, 200, 30);
        contactField.setBackground(new Color(197, 198, 199));
        contactField.setBorder(BorderFactory.createEtchedBorder());
        contactField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        subj = new JLabel("Subject :");
        subj.setFont(new Font("monospaced", Font.BOLD, 11));
        subj.setBounds(400, 600, 100, 30);
        subj.setForeground(new Color(102, 252, 241));

        subjField = new JTextField(10);
        subjField.setBounds(550, 600, 200, 30);
        subjField.setBackground(new Color(197, 198, 199));
        subjField.setBorder(BorderFactory.createEtchedBorder());
        subjField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        year = new JLabel("Year :");
        year.setFont(new Font("monospaced", Font.BOLD, 11));
        year.setBounds(800, 600, 100, 30);
        year.setForeground(new Color(102, 252, 241));

        yearField = new JTextField(10);
        yearField.setBounds(900, 600, 200, 30);
        yearField.setBackground(new Color(197, 198, 199));
        yearField.setBorder(BorderFactory.createEtchedBorder());
        yearField.setBorder(new LineBorder(new Color(69, 162, 158), 2));

        add = new JButton("Add");
        add.setBounds(840, 700, 200, 30);
        add.setBorder(BorderFactory.createEtchedBorder());
        add.setBorder(new LineBorder(new Color(102, 252, 241)));
        add.setBackground(new Color(11, 12, 16));
        add.setForeground(new Color(102, 252, 241));
        add.setFocusable(false);
        add.addActionListener(this);

        std.contentPane.add(add);
        std.contentPane.add(yearField);
        std.contentPane.add(year);
        std.contentPane.add(subjField);
        std.contentPane.add(subj);
        std.contentPane.add(contactField);
        std.contentPane.add(contct);
        std.contentPane.add(emailField);
        std.contentPane.add(emailLabel);
        std.contentPane.add(dobField);
        std.contentPane.add(DateofBrth);
        std.contentPane.add(fatherNameField);
        std.contentPane.add(FatherName);
        std.contentPane.add(lNameField);
        std.contentPane.add(Lname);
        std.contentPane.add(fNameField);
        std.contentPane.add(Fname);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String FNameSQl = fNameField.getText();
        String LNameSQl = lNameField.getText();
        String FatherNameSQl = fatherNameField.getText();
        String dobSQl = dobField.getText();
        String emailSQl = emailField.getText();
        String contactSQl = contactField.getText();
        String subjectSQl = subjField.getText();
        String yearSQl = yearField.getText();

        // Validate fields
        if (!valid.isValidName(FNameSQl)) {
            JOptionPane.showMessageDialog(null, "Invalid First Name! Special character & number not allowed", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!valid.isValidName(LNameSQl)) {
            JOptionPane.showMessageDialog(null, "Invalid Last Name! Special character & number not allowed", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!valid.isValidName(FatherNameSQl)) {
            JOptionPane.showMessageDialog(null, "Invalid Father's Name! Special character & number not allowed", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!valid.isValidDateOfBirth(dobSQl)) {
            JOptionPane.showMessageDialog(null, "Invalid Date of Birth! Formate must be 'xx-xx-xxxx'", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!valid.isValidEmail(emailSQl)) {
            JOptionPane.showMessageDialog(null, "Invalid Email Address! Must contain '@gmail.com'", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!valid.isValidContact(contactSQl)) {
            JOptionPane.showMessageDialog(null, "Invalid Contact Number! must be 10-digit only", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!valid.isValidSubject(subjectSQl)) {
            JOptionPane.showMessageDialog(null, "Subject must not be empty", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!valid.isValidYear(yearSQl)) {
            JOptionPane.showMessageDialog(null, "Invalid Year! Must be '1st', '2nd' & '3rd' year only", "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        } else {

            try {
                jdbc = new JDBC();
                jdbc.setConnection();
                System.out.println("connection success");

                String InsertQueryStudent = "insert Into students (first_name,last_name,Father_name,Dob,email,Contact,Subject,year)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement pstmt = jdbc.connection.prepareStatement(InsertQueryStudent);
                pstmt.setString(1, FNameSQl);
                pstmt.setString(2, LNameSQl);
                pstmt.setString(3, FatherNameSQl);
                pstmt.setString(4, dobSQl);
                pstmt.setString(5, emailSQl);
                pstmt.setString(6, contactSQl);
                pstmt.setString(7, subjectSQl);
                pstmt.setString(8, yearSQl);

                int rowsInserted = pstmt.executeUpdate();

                if (rowsInserted > 0) {
                    ImageIcon icon1 = new ImageIcon("Task_3/Images/warning.png");
                    JOptionPane.showOptionDialog(null, "Added successfully", "Success",
                            JOptionPane.CLOSED_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, icon1, null, 0);
                }

                pstmt.close();
                jdbc.connection.close();

            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }
}
