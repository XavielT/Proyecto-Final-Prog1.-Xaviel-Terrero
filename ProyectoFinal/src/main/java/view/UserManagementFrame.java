package view;

import model.User;
import service.UserManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UserManagementFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTable userTable;
    private JButton btnBack;
    private JButton btnEdit;
    private JButton btnDelete;
    private UserTableModel userTableModel;

    public UserManagementFrame() {
        setTitle("Gestión de Usuarios");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        // Modelo de tabla y la tabla.
        userTableModel = new UserTableModel();
        userTable = new JTable(userTableModel);

        // Botones.
        btnBack = createButton("Volver", Color.LIGHT_GRAY, Color.BLACK);
        btnEdit = createButton("Modificar", Color.BLUE, Color.WHITE);
        btnDelete = createButton("Eliminar", Color.RED, Color.WHITE);

        // Panel para los botones.
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.add(btnEdit);
        panelButtons.add(btnDelete);
        panelButtons.add(btnBack);

        // Panel con scroll para la tabla de usuarios.
        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Usuarios"));

        // Añadir componentes al JFrame.
        add(scrollPane, BorderLayout.CENTER);
        add(panelButtons, BorderLayout.SOUTH);

        // Configuración de los listeners.
        setActionListeners();
    }

    private JButton createButton(String text, Color background, Color foreground) {
        JButton button = new JButton(text);
        button.setBackground(background);
        button.setForeground(foreground);
        button.setFocusPainted(false);
        button.setPreferredSize(new Dimension(100, 30));
        return button;
    }

    private void setActionListeners() {
        // Acción para el botón "Volver".
        btnBack.addActionListener(e -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            dispose();
        });

        // Acción para el botón "Modificar".
        btnEdit.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                User selectedUser = userTableModel.getUserAt(selectedRow);
                new EditUserDialog(this, selectedUser, userTableModel).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para modificar.");
            }
        });

        // Acción para el botón "Eliminar".
        btnDelete.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow >= 0) {
                User selectedUser = userTableModel.getUserAt(selectedRow);
                int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este usuario?", "Confirmar Eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    UserManager.getInstance().deleteUser(selectedUser);
                    userTableModel.refreshData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor, seleccione un usuario para eliminar.");
            }
        });
    }
}
