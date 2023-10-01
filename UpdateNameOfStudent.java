package Task_3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class UpdateStudent implements ActionListener {
    JDBC jdbc;
    ResultSet resultSet;
    int rowAffected;
    JButton deleteButton, updateButton, searchButton;
    Student std = new Student();
    Validation valid = new Validation();

    UpdateStudent() {

        deleteButton = new JButton("Remove");
        deleteButton.setBounds(400, 500, 200, 30);
        deleteButton.setBorder(BorderFactory.createEtchedBorder());
        deleteButton.setBorder(new LineBorder(new Color(102, 252, 241)));
        deleteButton.setBackground(new Color(11, 12, 16));
        deleteButton.setForeground(new Color(102, 252, 241));
        deleteButton.setFocusable(false);
        deleteButton.setVisible(true);
        deleteButton.addActionListener(this);

        updateButton = new JButton("Update");
        updateButton.setBounds(700, 500, 200, 30);
        updateButton.setBorder(BorderFactory.createEtchedBorder());
        updateButton.setBorder(new LineBorder(new Color(102, 252, 241)));
        updateButton.setBackground(new Color(11, 12, 16));
        updateButton.setForeground(new Color(102, 252, 241));
        updateButton.setFocusable(false);
        updateButton.setVisible(true);
        updateButton.addActionListener(this);
        std.contentPane.add(updateButton);
        std.contentPane.add(deleteButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        rowAffected = 0;
        DefaultTableModel tblmodel = (DefaultTableModel) std.studentTable.getModel();
        try {
            jdbc = new JDBC();
            jdbc.setConnection();
            System.out.println("connection success");

            PreparedStatement preparedStatement = jdbc.connection
                    .prepareStatement("SELECT Count(*) From students;");
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        ImageIcon icon1 = new ImageIcon("Task_3/Images/warning.png");
        System.out.println(std.studentTable.getSelectedRow());
        // to get multiple rows selected
        int[] selectedRows = std.studentTable.getSelectedRows();

        if (selectedRows.length == 0) {
            JOptionPane.showOptionDialog(null, "Select Some Row ", "Warning", JOptionPane.CLOSED_OPTION,
                    JOptionPane.WARNING_MESSAGE, icon1, null, 0);
        } else {
            // update query
            if (com.equals("Update")) {
                for (int i = 0; i < selectedRows.length; i++) {
                    String student_id_str = (String) tblmodel.getValueAt(selectedRows[i], 0);
                    int student_ids = Integer.parseInt(student_id_str);
                    String firstNm = (String) tblmodel.getValueAt(selectedRows[i], 1);
                    String lastNm = (String) tblmodel.getValueAt(selectedRows[i], 2);
                    String fatherName = (String) tblmodel.getValueAt(selectedRows[i], 3);
                    String doB = (String) tblmodel.getValueAt(selectedRows[i], 4);
                    String Email = (String) tblmodel.getValueAt(selectedRows[i], 5);
                    String cont = (String) tblmodel.getValueAt(selectedRows[i], 6);
                    String sub = (String) tblmodel.getValueAt(selectedRows[i], 7);
                    String yr = (String) tblmodel.getValueAt(selectedRows[i], 8);

                    // Validate fields
                    if (!valid.isValidName(firstNm)) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid First Name! Special character & number not allowed", "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!valid.isValidName(lastNm)) {
                        JOptionPane.showMessageDialog(null, "Invalid Last Name! Special character & number not allowed",
                                "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!valid.isValidName(fatherName)) {
                        JOptionPane.showMessageDialog(null,
                                "Invalid Father's Name! Special character & number not allowed", "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!valid.isValidDateOfBirth(doB)) {
                        JOptionPane.showMessageDialog(null, "Invalid Date of Birth! Formate must be 'xx-xx-xxxx'",
                                "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!valid.isValidEmail(Email)) {
                        JOptionPane.showMessageDialog(null, "Invalid Email Address! Must contain '@gmail.com'",
                                "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!valid.isValidContact(cont)) {
                        JOptionPane.showMessageDialog(null, "Invalid Contact Number! must be 10-digit only",
                                "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!valid.isValidSubject(sub)) {
                        JOptionPane.showMessageDialog(null, "Subject must not be empty", "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    if (!valid.isValidYear(yr)) {
                        JOptionPane.showMessageDialog(null, "Invalid Year! Must be '1st', '2nd' & '3rd' year only",
                                "Validation Error",
                                JOptionPane.ERROR_MESSAGE);
                        return;
                    } else {

                        try {
                            String UpdateQueryStudent = "UPDATE students " +
                                    "  SET  first_name = '" + firstNm + "'," +
                                    "    last_name = '" + lastNm + "'," +
                                    "    father_name = '" + fatherName + "'," +
                                    "    Dob = '" + doB + "'," +
                                    "    email = '" + Email + "'," +
                                    "    Contact = '" + cont + "'," +
                                    "    Subject = '" + sub + "'," +
                                    "    year = '" + yr + "'" +
                                    "WHERE student_id = " + student_ids + ";";
                            PreparedStatement pstmt = null;

                            try {
                                pstmt = jdbc.connection.prepareStatement(UpdateQueryStudent);
                                rowAffected += pstmt.executeUpdate();
                            } catch (SQLException sqlexc) {
                                sqlexc.printStackTrace();
                            }

                            JOptionPane.showOptionDialog(null, "updated successfully successfull ", "Warning",
                                    JOptionPane.CLOSED_OPTION,
                                    JOptionPane.WARNING_MESSAGE, icon1, null, 0);

                        } catch (Exception exc) {
                            exc.printStackTrace();
                        }
                    }
                    std.showTable();
                }
            } else {
                // Delete Query
                try {

                    for (int i = 0; i < selectedRows.length; ++i) {
                        String DeleteQuery = "DELETE FROM students WHERE student_id="
                                + tblmodel.getValueAt(selectedRows[i], 0) + ";";
                        PreparedStatement pstmt2 = null;
                        try {
                            pstmt2 = jdbc.connection.prepareStatement(DeleteQuery);
                            rowAffected += pstmt2.executeUpdate();

                        } catch (SQLException sqlexc) {
                            sqlexc.printStackTrace();
                        }

                        JOptionPane.showOptionDialog(null, "deleted successfully successfull ", "Warning",
                                JOptionPane.CLOSED_OPTION,
                                JOptionPane.WARNING_MESSAGE, icon1, null, 0);

                    }
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
                std.showTable();
            }

        }

    }

}
