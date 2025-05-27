

import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class FurnitureAdminPanel extends JFrame {
    private JTextField nameField, categoryField, priceField, quantityField;
    private JTextArea descriptionArea;
    private JTable furnitureTable;
    private DefaultTableModel tableModel;
    private ArrayList<Item> itemList;

    public FurnitureAdminPanel() {
        itemList = new ArrayList<>();
        setTitle("Admin Dashboard - Furniture Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.BOLD, 14);


        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(6, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setFont(customFont);
        inputPanel.add(nameLabel);
        nameField = new JTextField();
        inputPanel.add(nameField);

        JLabel categoryLabel = new JLabel("Category:");
        categoryLabel.setFont(customFont);
        inputPanel.add(categoryLabel);
        categoryField = new JTextField();
        inputPanel.add(categoryField);

        JLabel priceLabel = new JLabel("Price:");
        inputPanel.add(priceLabel);
        priceField = new JTextField();
        inputPanel.add(priceField);

        JLabel quantityLabel = new JLabel("Quantity:");
        inputPanel.add(quantityLabel);
        quantityField = new JTextField();
        inputPanel.add(quantityField);

        JLabel descriptionLabel = new JLabel("Description:");
        inputPanel.add(descriptionLabel);
        descriptionArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(descriptionArea));

        JButton addButton = new JButton("Add Furniture");
        inputPanel.add(addButton);

        add(inputPanel, BorderLayout.NORTH);


        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Quantity", "Description"}, 0);
        furnitureTable = new JTable(tableModel);
        add(new JScrollPane(furnitureTable), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        JButton deleteButton = new JButton("Delete Selected");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(deleteButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);


        addButton.addActionListener(e -> addFurniture());
        deleteButton.addActionListener(e -> deleteFurniture());
        logoutButton.addActionListener(e -> logout());

        setVisible(true);
    }

    private void addFurniture() {
        try {
            int id = itemList.size() + 1;
            String name = nameField.getText().trim();
            String category = categoryField.getText().trim();
            double price = Double.parseDouble(priceField.getText().trim());
            int quantity = Integer.parseInt(quantityField.getText().trim());
            String description = descriptionArea.getText().trim();

            Furniture furniture = new Furniture(id, name, category, price, quantity, description);
            itemList.add(furniture);

            File file1 = new File("furnitures.txt");
            boolean fileExists = file1.exists();

            try (FileWriter writer1 = new FileWriter(file1, true);
                 BufferedWriter bufferedWriter = new BufferedWriter(writer1)) {

                if (fileExists) {
                    bufferedWriter.newLine();
                }
                bufferedWriter.write(id + "\t" + name + "\t" + category + "\t" + price + "\t" + quantity + "\t" + description);
            }

            tableModel.addRow(new Object[]{id, name, category, price, quantity, description});


            nameField.setText("");
            categoryField.setText("");
            priceField.setText("");
            quantityField.setText("");
            descriptionArea.setText("");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid input. Please check the values.");
        }
    }


    private void deleteFurniture() {
        int selectedRow = furnitureTable.getSelectedRow();
        if (selectedRow != -1) {
            itemList.remove(selectedRow);
            tableModel.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.");
        }
    }

    private void logout() {
        dispose();
        new Login();
    }

    public static void main(String[] args) {
        new FurnitureAdminPanel();
    }
}
