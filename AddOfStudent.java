package Task_3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
// import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableModel;

public class Student extends JFrame implements ActionListener {
    JLabel imgLabel, headingLabel;
    JButton addButton, editButton;
    JPanel studentDataPanel;
    JPanel panel;
    JDBC jdbc;
    PreparedStatement preparedStatement;
    ResultSet resultSet;
    JTable studentTable;
    JScrollPane scrollPane;
    JButton refresh;
    JTextField searchField;
    int rowAffected;
    UpdateStudent upstd;
    Container contentPane;

    Student() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Student Management");

        // Get the content pane
        contentPane = getContentPane();
        contentPane.setBackground(new Color(11, 12, 16));

        this.setBounds(0, 0, 1525, 820);
        this.setLayout(null);

        panel = new JPanel();
        panel.setBackground(new Color(69, 162, 158));
        panel.setBounds(0, 0, 300, 820);
        panel.setLayout(new BorderLayout());
        // panel.add(showTable());

        imgLabel = new JLabel(new ImageIcon("Task_3/Images/users-alt.png"));
        imgLabel.setBounds(50, 50, 200, 200);

        headingLabel = new JLabel("<html><h1>Student Management System</h1></html>");
        headingLabel.setBounds(700, 30, 500, 60);
        headingLabel.setForeground(new Color(102, 252, 241));

        addButton = new JButton("Add Students");
        addButton.setBounds(50, 280, 200, 30);
        addButton.setBorder(BorderFactory.createEtchedBorder());
        addButton.setBorder(new LineBorder(new Color(102, 252, 241)));
        addButton.setBackground(new Color(11, 12, 16));
        addButton.setForeground(new Color(102, 252, 241));
        addButton.setFocusable(false);
        addButton.addActionListener(this);

        editButton = new JButton("Edit Students");
        editButton.setBounds(50, 350, 200, 30);
        editButton.setBorder(BorderFactory.createEtchedBorder());
        editButton.setBorder(new LineBorder(new Color(102, 252, 241)));
        editButton.setBackground(new Color(11, 12, 16));
        editButton.setForeground(new Color(102, 252, 241));
        editButton.setFocusable(false);
        editButton.addActionListener(this);

        studentDataPanel = new JPanel();
        studentDataPanel.setBackground(Color.white);
        studentDataPanel.setBounds(400, 150, 1000, 300);
        studentDataPanel.setLayout(new BorderLayout());

        refresh = new JButton();
        refresh.setIcon(new ImageIcon("Task_3/Images/refresh (2).png"));
        refresh.setToolTipText("Refresh");
        refresh.setBackground(Color.BLACK);
        refresh.setFocusable(false);
        refresh.setBorder(BorderFactory.createEtchedBorder());
        refresh.setBorder(new LineBorder(Color.BLACK));
        refresh.setBounds(1330, 90, 50, 50);
        refresh.addActionListener(this);

        JLabel imagLabel = new JLabel(new ImageIcon("Task_3/Images/student(2).png"));
        imagLabel.setIconTextGap(-10);
        imagLabel.setBounds(1100,400,500,500);
        initializeTable();
        showTable();
        contentPane.add(imagLabel);
        contentPane.add(refresh);
        contentPane.add(studentDataPanel);
        contentPane.add(editButton);
        contentPane.add(addButton);
        contentPane.add(headingLabel);
        contentPane.add(imgLabel);
        contentPane.add(panel);
        this.setVisible(true);
        
    }
    private void initializeTable() {
        String tableColumns[] = { "Student_ID", "First Name", "Last Name", "Father Name", "DOB", "Email", "Contact", "Subject", "Year" };
        String tableData[][] = new String[0][tableColumns.length];
        DefaultTableModel model = new DefaultTableModel(tableData, tableColumns);
        studentTable = new JTable(model); // Create the table instance
        studentTable.setRowHeight(35);
        studentTable.setFont(new Font("monospaced", Font.BOLD, 10));

        scrollPane = new JScrollPane(studentTable);
        scrollPane.setBounds(0, 0, 1000, 300);

        studentDataPanel.add(scrollPane);
    }
    public void showTable() {
        DefaultTableModel model = (DefaultTableModel) studentTable.getModel();
        model.setRowCount(0); // Clear existing data

        try {
            jdbc = new JDBC();
            jdbc.setConnection();
            System.out.println("Connection success");

            preparedStatement = jdbc.connection.prepareStatement("SELECT * from students;");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Object[] rowData = {
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
                };
                model.addRow(rowData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String com = e.getActionCommand();
        System.out.println(com);
        if (com.equals("Add Students")) {
            dispose();
            new AddStudent();
        } else if (com.equals("Edit Students")) {
            dispose();
            upstd = new UpdateStudent();
        } else {
            studentDataPanel.setBounds(400, 150, 1000, 300);
            showTable();
        }
    }
}
