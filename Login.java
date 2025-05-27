

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login implements ActionListener {
    private JFrame frame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private String userType;

    public Login() {
        // Setup the main login frame
        frame = new JFrame("Login System");
        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);


        JLabel titleLabel = new JLabel("Select Login Type", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 20, 300, 30);
        frame.add(titleLabel);


        JButton adminButton = new JButton("Admin Login");
        adminButton.setBounds(200, 100, 200, 40);
        adminButton.addActionListener(e -> openLoginFrame("Admin"));
        frame.add(adminButton);

        JButton customerButton = new JButton("Customer Login");
        customerButton.setBounds(200, 180, 200, 40);
        customerButton.addActionListener(e -> openLoginFrame("Customer"));
        frame.add(customerButton);


        frame.setVisible(true);
    }

    private void openLoginFrame(String userType) {
        this.userType = userType;
        frame.dispose();


        JFrame loginFrame = new JFrame(userType + " Login");
        loginFrame.setSize(600, 400);
        loginFrame.setLayout(null);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLocationRelativeTo(null);


        JLabel titleLabel = new JLabel(userType + " Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBounds(150, 20, 300, 30);
        loginFrame.add(titleLabel);


        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setBounds(100, 100, 100, 30);
        loginFrame.add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(200, 100, 200, 30);
        loginFrame.add(usernameField);


        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(100, 150, 100, 30);
        loginFrame.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(200, 150, 200, 30);
        loginFrame.add(passwordField);


        JButton loginButton = new JButton("Login");
        loginButton.setBounds(250, 220, 100, 40);
        loginButton.addActionListener(this);
        loginFrame.add(loginButton);


        loginFrame.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());


        if (userType.equals("Admin")) {
            if (username.equals("Admin") && password.equals("Admin123")) {
                JOptionPane.showMessageDialog(null, "Welcome Admin!");
                openAdminFrame();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Admin credentials!");
            }
        } else if (userType.equals("Customer")) {
            if (username.equals("Customer") && password.equals("Customer123")) {
                JOptionPane.showMessageDialog(null, "Welcome Customer!");
                openCustomerFrame();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Customer credentials!");
            }
        }
    }

    private void openAdminFrame() {
        new FurnitureAdminPanel();
    }

    private void openCustomerFrame() {
        new CustomerDashboard();
    }


}
