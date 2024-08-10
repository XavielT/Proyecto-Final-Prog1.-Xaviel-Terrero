package view;

import model.User;
import service.UserManager;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class UserTableModel extends AbstractTableModel {

    private static final long serialVersionUID = 1L;
    
    //Nombres de las columnas de la tabla.
    private static final String[] COLUMN_NAMES = {"Usuario", "Nombre", "Apellido", "Teléfono", "Correo"};
    
    //Lista que almacena los objetos User.
    private List<User> users;

    //Constructor que inicializa la lista de usuarios.
    public UserTableModel() {
        users = new ArrayList<>(UserManager.getInstance().getAllUsers());
    }

    @Override
    public int getRowCount() {
        return users.size(); //Número de filas en la tabla.
    }

    @Override
    public int getColumnCount() {
        return COLUMN_NAMES.length; //Número de columnas en la tabla.
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUMN_NAMES[columnIndex]; //Nombre de la columna en columnIndex.
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        User user = users.get(rowIndex); //Obtiene el usuario en la fila rowIndex.
        switch (columnIndex) {
            case 0: return user.getUsername(); //Nombre de usuario.
            case 1: return user.getFirstName(); //Primer nombre.
            case 2: return user.getLastName(); //Apellido.
            case 3: return user.getPhoneNumber(); //Teléfono.
            case 4: return user.getEmail(); //Correo electrónico.
            default: throw new IllegalArgumentException("Invalid column index");
        }
    }

    public User getUserAt(int rowIndex) {
        return users.get(rowIndex); //Devuelve el usuario en la fila rowIndex.
    }

    public void updateUser(User user) {
        int index = users.indexOf(user); //Encuentra el índice del usuario.
        if (index != -1) {
            fireTableRowsUpdated(index, index); //Notifica que se ha actualizado una fila.
        }
    }

    public void removeUser(User user) {
        int index = users.indexOf(user); //Encuentra el índice del usuario.
        if (index != -1) {
            users.remove(index); //Elimina el usuario de la lista.
            fireTableRowsDeleted(index, index); //Notifica que se ha eliminado una fila.
        }
    }

    public void refreshData() {
        users = new ArrayList<>(UserManager.getInstance().getAllUsers()); //Actualiza la lista de usuarios.
        fireTableDataChanged(); // Notifica que los datos de la tabla han cambiado.
    }
}
