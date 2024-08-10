package view;

import model.Product;
import service.ProductManager;

import javax.swing.*;
import java.awt.*;

public class ProductManagementFrame extends JFrame {
    private JTable productTable;
    private JButton btnNewProduct;
    private JButton btnBack;
    private ProductTableModel productTableModel;

    public ProductManagementFrame() {
        setTitle("Gestión de Productos");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //Modelo de la tabla y tabla.
        productTableModel = new ProductTableModel();
        productTable = new JTable(productTableModel);
        productTable.setFillsViewportHeight(true);
        productTable.setRowHeight(30);

        //Estilo de la tabla.
        productTable.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        productTable.getTableHeader().setBackground(new Color(0x2E86C1)); // Color de fondo del encabezado
        productTable.getTableHeader().setForeground(Color.WHITE); // Color del texto del encabezado
        productTable.setSelectionBackground(new Color(0xAED6F1)); // Color de fondo de la selección
        productTable.setSelectionForeground(Color.BLACK); // Color del texto de la selección
        productTable.setFont(new Font("SansSerif", Font.PLAIN, 14)); // Fuente del texto de la tabla

        //Inicializar botones.
        btnNewProduct = new JButton("Nuevo Producto");
        btnBack = new JButton("Volver");

        //Estilo de los botones.
        btnNewProduct.setPreferredSize(new Dimension(150, 40));
        btnBack.setPreferredSize(new Dimension(150, 40));

        //Diseno de colores y fondo de color en nuevo producto.
        btnNewProduct.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnNewProduct.setBackground(new Color(0x28B463)); 
        btnNewProduct.setForeground(Color.WHITE); 

        //Diseno de colores y fondo de color en Volver.
        btnBack.setFont(new Font("SansSerif", Font.PLAIN, 16));
        btnBack.setBackground(new Color(0xC0392B));
        btnBack.setForeground(Color.WHITE);

        //Panel de botones.
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelButtons.add(btnNewProduct);
        panelButtons.add(btnBack);

        //Añadir componentes al JFrame.
        add(new JScrollPane(productTable), BorderLayout.CENTER); // Añadir la tabla dentro de un JScrollPane
        add(panelButtons, BorderLayout.SOUTH); // Añadir el panel de botones en la parte inferior

        //Acción para el botón Nuevo Producto.
        btnNewProduct.addActionListener(e -> {
            //Abre un formulario para añadir un nuevo producto.
            ProductFormFrame productFormFrame = new ProductFormFrame(null, productTableModel);
            productFormFrame.setVisible(true);
        });

        //Acción para el botón Volver.
        btnBack.addActionListener(e -> {
            //Abre la ventana principal y cierra la ventana actual
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
            dispose();
        });

        //Acción al seleccionar un producto en la tabla.
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow >= 0) {
                    //Obtiene el producto seleccionado y abre el formulario para editarlo.
                    Product selectedProduct = productTableModel.getProductAt(selectedRow);
                    ProductFormFrame productFormFrame = new ProductFormFrame(selectedProduct, productTableModel);
                    productFormFrame.setVisible(true);
                }
            }
        });
    }
}
