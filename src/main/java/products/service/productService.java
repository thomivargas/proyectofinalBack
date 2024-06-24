package products.service;

import products.model.product;
import products.repositories.productRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class productService {

    @Autowired
    private productRepository productRepository;

    // Método para obtener todos los productos
    public List<product> getProducts() {
        return productRepository.findAll();
    }

    // Método para obtener un producto por su ID
    public Optional<product> getProductById(Long productId) {
        return productRepository.findById(productId);
    }

    // Método para crear un producto
    public product addProduct(product product) {
        return productRepository.save(product);
    }

    // Método para borrar un producto por ID
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }
    // Método para modificar un producto
    public product updateProduct(Long productId, product newProduct) {
        Optional<product> existingProductOptional = productRepository.findById(productId);
        if (existingProductOptional.isPresent()) {
            product existingProduct = existingProductOptional.get();
            existingProduct.setSku(newProduct.getSku());
            existingProduct.setName(newProduct.getName());
            existingProduct.setDescripcion(newProduct.getDescripcion());
            existingProduct.setPrice(newProduct.getPrice());
            existingProduct.setStatus(newProduct.getStatus());

            return productRepository.save(existingProduct);
        } else {
            // Manejo de error
            throw new IllegalArgumentException("No se encontró el producto con el ID proporcionado");
        }
    }

}
