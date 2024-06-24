package products.controller;

import products.service.productService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import products.model.product;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("/products")
//@RequiredArgsConstructor

public class productController {
    @Autowired
    private productService productService;

    //Metodos

    //METODO PARA TRAER TODOS LOS PRODUCTOS
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<product> getProducts() {
        return this.productService.getProducts();
    }
    // METODO PARA TRAER UN PRODUCTO POR ID
    @GetMapping("/{productId}")
    public product getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Producto no encontrado"));
    }
    //METODO PARA CREAR UN PRODUCT
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> addProduct(@Valid @RequestBody product product, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        } else {
            product createdProduct = productService.addProduct(product);
            return ResponseEntity.ok(createdProduct);
        }
    }

    //METODO PARA BORRAR UN PRODUCTO POR ID
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductById(@PathVariable Long productId) {
        this.productService.deleteProductById(productId);
    }
    //METODO PARA ACTUALIZAR UN PRODUCTO POR ID
    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable Long productId, @RequestBody product product) {
        this.productService.updateProduct(productId, product);
    }


}
