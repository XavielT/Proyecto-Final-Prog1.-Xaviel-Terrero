
package view;

import model.User;
import service.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EditUserDialog extends JDialog {

    private JTextField txtUsername;
    private JTextField txtFirstName;
    private JTextField txtLastName;
    private JTextField txtPhoneNumber;
    private JTextField txtEmail;
    private JPasswordField txtPassword;
    private JButton btnSave;
    private JButton btnCancel;
    private User user;
    private UserTableModel userTableModel;

    public EditUserDialog(Frame parent, User user, UserTableModel userTableModel) {
        super(parent, "Modificar Usuario", true);
        this.user = user;
        this.userTableModel = userTableModel;
        initUI();
    }

    private void initUI() {
        setLayout(new GridLayout(7, 2, 10, 10));
        setSize(400, 300);
        setLocationRelativeTo(getParent());

        JLabel lblUsername = new JLabel("Username:");
        JLabel lblFirstName = new JLabel("First Name:");
        JLabel lblLastName = new JLabel("Last Name:");
        JLabel lblPhoneNumber = new JLabel("Phone Number:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblPassword = new JLabel("Password:");

        txtUsername = new JTextField(user.getUsername());
        txtFirstName = new JTextField(user.getFirstName());
        txtLastName = new JTextField(user.getLastName());
        txtPhoneNumber = new JTextField(user.getPhoneNumber());
        txtEmail = new JTextField(user.getEmail());
        txtPassword = new JPasswordField(user.getPassword());

        btnSave = new JButton("Save");
        btnCancel = new JButton("Cancel");

        add(lblUsername);
        add(txtUsername);
        add(lblFirstName);
        add(txtFirstName);
        add(lblLastName);
        add(txtLastName);
        add(lblPhoneNumber);
        add(txtPhoneNumber);
        add(lblEmail);
        add(txtEmail);
        add(lblPassword);
        add(txtPassword);
        add(btnSave);
        add(btnCancel);

        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String firstName = txtFirstName.getText();
                String lastName = txtLastName.getText();
                String phoneNumber = txtPhoneNumber.getText();
                String email = txtEmail.getText();
                String password = new String(txtPassword.getPassword());

                user.setUsername(username);
                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setPhoneNumber(phoneNumber);
                user.setEmail(email);
                user.setPassword(password);

                UserManager.getInstance().updateUser(user);
                userTableModel.refreshData();
                dispose();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
