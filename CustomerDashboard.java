

import javax.swing.*;
import java.util.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Scanner;

public class CustomerDashboard extends JFrame {
    private JTable furnitureTable;
    private DefaultTableModel tableModel;
    private ArrayList<Furniture> itemList;

    public CustomerDashboard() {
        itemList = new ArrayList<>();
        setTitle("Customer Dashboard - Purchase Items");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        Font customFont = new Font("Arial", Font.BOLD, 14);


        tableModel = new DefaultTableModel(new String[]{"ID", "Name", "Category", "Price", "Quantity", "Description"}, 0);
        furnitureTable = new JTable(tableModel);
        add(new JScrollPane(furnitureTable), BorderLayout.CENTER);


        JPanel buttonPanel = new JPanel();
        JButton buyButton = new JButton("Buy");
        JButton cancelButton = new JButton("Cancel");
        JButton logoutButton = new JButton("Logout");

        buttonPanel.add(buyButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.SOUTH);


        buyButton.addActionListener(e -> buyItem());
        cancelButton.addActionListener(e -> cancelTransaction());
        logoutButton.addActionListener(e -> logout());

        loadItems(); // Load sample items
        setVisible(true);
    }

    private void loadItems() {
        File file2 = new File("furnitures.txt");
        if (!file2.exists()) {
            System.out.println("Error: furnitures.txt not found!");
            return;
        }
        try (Scanner scan2 = new Scanner(file2)) {
            scan2.useDelimiter("\\t|\\n");

            while (scan2.hasNextLine()) {
                String line = scan2.nextLine();
                System.out.println("Reading line: " + line);

                Scanner lineScanner = new Scanner(line);
                lineScanner.useDelimiter("\\t");

                try {
                    int itemId = lineScanner.nextInt();
                    String itemName = lineScanner.next();
                    String itemCategory = lineScanner.next();
                    double itemPrice = lineScanner.nextDouble();
                    int itemQuantity = lineScanner.nextInt();
                    String itemDetails = lineScanner.next();

                    itemList.add(new Furniture(itemId, itemName, itemCategory, itemPrice, itemQuantity, itemDetails));
                    tableModel.addRow(new Object[]{itemId, itemName, itemCategory, itemPrice, itemQuantity, itemDetails});

                } catch (Exception e) {
                    System.out.println("Skipping invalid line: " + line);
                    e.printStackTrace();
                }
                lineScanner.close();
            }
        } catch (Exception e) {
            System.out.println("Error loading items: " + e.getMessage());
        }
    }



    private void buyItem() {
        int selectedRow = furnitureTable.getSelectedRow();
        if (selectedRow != -1) {
            int quantity = (int) tableModel.getValueAt(selectedRow, 4);
            if (quantity > 0) {
                tableModel.setValueAt(quantity - 1, selectedRow, 4);
                JOptionPane.showMessageDialog(this, "Item purchased successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Sorry, item is out of stock!");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to buy.");
        }
    }

    private void cancelTransaction() {
        JOptionPane.showMessageDialog(this, "Transaction canceled.");
    }

    private void logout() {
        dispose();
        new Login();
    }

//    public static void main(String[] args) {
//        new CustomerDashboard();
//    }
}
