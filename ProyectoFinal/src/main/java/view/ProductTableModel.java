package view;

import model.Product;
import service.ProductManager;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ProductTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Nombre", "Marca", "Categoría", "Precio", "Cantidad"};
    private List<Product> products;

    public ProductTableModel() {
        //Inicializa la lista de productos obtenidos del ProductManager.
        products = new ArrayList<>(ProductManager.getInstance().getAllProducts());
    }

    @Override
    public int getRowCount() {
        //Devuelve el número de filas en la tabla.
        return products.size();
    }

    @Override
    public int getColumnCount() {
        //Devuelve el número de columnas en la tabla.
        return columnNames.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        //Devuelve el nombre de la columna según el índice de columna.
        return columnNames[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //Devuelve el valor en la celda especificada por la fila y columna.
        Product product = products.get(rowIndex);
        switch (columnIndex) {
            case 0: return product.getName();
            case 1: return product.getBrand();
            case 2: return product.getCategory();
            case 3: return product.getPrice();
            case 4: return product.getQuantity();
            default: return null;
        }
    }

    public Product getProductAt(int rowIndex) {
        //Devuelve el producto en la fila especificada.
        return products.get(rowIndex);
    }

    public void addProduct(Product product) {
        //Añade un nuevo producto a la lista.
        //Notifica a la tabla que se han insertado nuevas filas.
        products.add(product);
        fireTableRowsInserted(products.size() - 1, products.size() - 1);
    }

    public void updateProduct(Product product) {
        //Actualiza el producto en la lista.
        //Notifica a la tabla que se han actualizado las filas.
        int index = products.indexOf(product);
        if (index != -1) {
            fireTableRowsUpdated(index, index);
        }
    }

    public void removeProduct(Product product) {
        //Elimina un producto de la lista.
        //Notifica a la tabla que se han eliminado filas.
        int index = products.indexOf(product);
        if (index != -1) {
            products.remove(index);
            fireTableRowsDeleted(index, index);
        }
    }

    public void refreshData() {
        //Refresca la lista de productos desde el ProductManager.
        //Notifica a la tabla que los datos han cambiado.
        products = new ArrayList<>(ProductManager.getInstance().getAllProducts());
        fireTableDataChanged();
    }
}
