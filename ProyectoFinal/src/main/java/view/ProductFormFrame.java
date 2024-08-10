package view;

import model.Product;
import service.ProductManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProductFormFrame extends JFrame {
    private JTextField txtName;
    private JTextField txtBrand;
    private JTextField txtCategory;
    private JTextField txtPrice;
    private JTextField txtQuantity;
    private JButton btnSave;
    private JButton btnDelete;
    private JButton btnBack;

    private Product product;
    private ProductTableModel productTableModel;

    public ProductFormFrame(Product product, ProductTableModel productTableModel) {
        this.product = product;
        this.productTableModel = productTableModel;

        //Configuración de la ventana.
        setTitle(product == null ? "Nuevo Producto" : "Editar Producto");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        //Panel para los campos del formulario y su diseno.
        JPanel panel = new JPanel(new GridLayout(6, 2));

        //Etiquetas y campos de texto para el formulario.
        JLabel lblName = new JLabel("Nombre:");
        txtName = new JTextField(product != null ? product.getName() : "");

        JLabel lblBrand = new JLabel("Marca:");
        txtBrand = new JTextField(product != null ? product.getBrand() : "");

        JLabel lblCategory = new JLabel("Categoría:");
        txtCategory = new JTextField(product != null ? product.getCategory() : "");

        JLabel lblPrice = new JLabel("Precio:");
        txtPrice = new JTextField(product != null ? String.valueOf(product.getPrice()) : "");

        JLabel lblQuantity = new JLabel("Cantidad Disponible:");
        txtQuantity = new JTextField(product != null ? String.valueOf(product.getQuantity()) : "");

        //Botones para guardar, eliminar y volver.
        btnSave = new JButton("Guardar");
        btnDelete = new JButton("Eliminar");
        btnBack = new JButton("Volver");

        //Añadir componentes al panel.
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblBrand);
        panel.add(txtBrand);
        panel.add(lblCategory);
        panel.add(txtCategory);
        panel.add(lblPrice);
        panel.add(txtPrice);
        panel.add(lblQuantity);
        panel.add(txtQuantity);
        panel.add(btnSave);
        panel.add(btnDelete);

        //Añadir el panel al contenedor principal.
        add(panel, BorderLayout.CENTER);
        add(btnBack, BorderLayout.SOUTH);

        //Acción para el botón Guardar.
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (product == null) {
                    //Crear un nuevo producto.
                    Product newProduct = new Product(txtName.getText(), txtBrand.getText(), txtCategory.getText(),
                            Double.parseDouble(txtPrice.getText()), Integer.parseInt(txtQuantity.getText()));
                    ProductManager.getInstance().addProduct(newProduct);
                    productTableModel.addProduct(newProduct);
                } else {
                    //Actualizar el producto existente.
                    product.setName(txtName.getText());
                    product.setBrand(txtBrand.getText());
                    product.setCategory(txtCategory.getText());
                    product.setPrice(Double.parseDouble(txtPrice.getText()));
                    product.setQuantity(Integer.parseInt(txtQuantity.getText()));
                    ProductManager.getInstance().updateProduct(product);
                    productTableModel.updateProduct(product);
                }
                dispose(); //Cerrar la ventana después de guardar o actualizar.
            }
        });

        //Acción del botón Eliminar.
        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (product != null) {
                    ProductManager.getInstance().deleteProduct(product);
                    productTableModel.removeProduct(product);
                }
                dispose(); //Cerrar la ventana después de eliminar.
            }
        });

        //Acción del botón Volver.
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); //Cerrar la ventana sin realizar cambios.
            }
        });
    }
}
