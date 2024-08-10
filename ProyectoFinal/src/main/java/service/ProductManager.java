package service;

import model.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private static ProductManager instance;
    private List<Product> products;

    //Constructor privado para la lista de productos.
    private ProductManager() {
        products = new ArrayList<>();
        //Inicializa productos si es necesario.
    }

    //El Singleton para la instancia única de ProductManager.
    public static synchronized ProductManager getInstance() {
        if (instance == null) {
            instance = new ProductManager();
        }
        return instance;
    }

    //Obtener la lista de todos los productos.
    public List<Product> getAllProducts() {
        return products;
    }

    //Agregar un nuevo producto.
    public void addProduct(Product product) {
        products.add(product);
    }

    //Actualizar un producto.
    public void updateProduct(Product product) {
     
    }

    //Eliminar un producto.
    public void deleteProduct(Product product) {
        products.remove(product);
    }
}
