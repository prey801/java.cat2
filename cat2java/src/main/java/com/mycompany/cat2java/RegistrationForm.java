package com.mycompany.cat2java;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.Vector;

public class RegistrationForm extends JFrame {
    private JTextField txtID, txtName, txtAddress, txtContact;
    private JRadioButton rbMale, rbFemale;
    private JButton btnRegister, btnExit;
    private JTable tblData;
    private DefaultTableModel tableModel;
    private Connection conn;
    
    public RegistrationForm() {
        // Set up the frame
        setTitle("Registration Form");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        // Create form panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Add form components
        formPanel.add(new JLabel("ID:"));
        txtID = new JTextField();
        formPanel.add(txtID);
        
        formPanel.add(new JLabel("Name:"));
        txtName = new JTextField();
        formPanel.add(txtName);
        
        formPanel.add(new JLabel("Gender:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rbMale = new JRadioButton("Male");
        rbFemale = new JRadioButton("Female");
        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(rbMale);
        bgGender.add(rbFemale);
        genderPanel.add(rbMale);
        genderPanel.add(rbFemale);
        formPanel.add(genderPanel);
        
        formPanel.add(new JLabel("Address:"));
        txtAddress = new JTextField();
        formPanel.add(txtAddress);
        
        formPanel.add(new JLabel("Contact:"));
        txtContact = new JTextField();
        formPanel.add(txtContact);
        
        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        btnRegister = new JButton("Register");
        btnExit = new JButton("Exit");
        buttonPanel.add(btnRegister);
        buttonPanel.add(btnExit);
        formPanel.add(buttonPanel);
        
        // Create table
        String[] columns = {"ID", "Name", "Gender", "Address", "Contact"};
        tableModel = new DefaultTableModel(columns, 0);
        tblData = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tblData);
        
        // Add panels to frame
        add(formPanel, BorderLayout.WEST);
        add(scrollPane, BorderLayout.CENTER);
        
        // Add button listeners
        btnRegister.addActionListener(e -> registerUser());
        btnExit.addActionListener(e -> System.exit(0));
        
        // Initialize database connection
        connectToDatabase();
        loadTableData();
    }
    
    private void connectToDatabase() {
        try {
            String url = "jdbc:mysql://localhost:3306/javacat2";
            String user = "admin";
            String password = "root";
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Database connection failed: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void registerUser() {
        try {
            String sql = "INSERT INTO users (id, name, gender, address, contact) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, txtID.getText());
            pstmt.setString(2, txtName.getText());
            pstmt.setString(3, rbMale.isSelected() ? "Male" : "Female");
            pstmt.setString(4, txtAddress.getText());
            pstmt.setString(5, txtContact.getText());
            
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, "Registration successful!");
            
            clearForm();
            loadTableData();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Registration failed: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void loadTableData() {
        try {
            tableModel.setRowCount(0);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM users");
            
            while (rs.next()) {
                Vector<String> row = new Vector<>();
                row.add(rs.getString("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("gender"));
                row.add(rs.getString("address"));
                row.add(rs.getString("contact"));
                tableModel.addRow(row);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading data: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void clearForm() {
        txtID.setText("");
        txtName.setText("");
        txtAddress.setText("");
        txtContact.setText("");
        rbMale.setSelected(false);
        rbFemale.setSelected(false);
    }
}