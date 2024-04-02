package br.com.fatecrestapi.FoodFatec.controller;

import br.com.fatecrestapi.FoodFatec.entity.Product;
import br.com.fatecrestapi.FoodFatec.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/product" )
@CrossOrigin(value = "*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value="/list")
    public ResponseEntity<Object> getInfoProduct(){
        List<Product> result = productService.getInfoProduct();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value="/create")
    public ResponseEntity<Object> saveProduct(@RequestBody Product product) {
        Product result = ProductService.saveProduct(product);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/delete/{idProduct}")
    public ResponseEntity<Object> deleteProduct(@PathVariable Long idProduct){
        HashMap<String,Object> result = productService.deleteProduct(idProduct);
        return ResponseEntity.ok().body(result);

    }

    @GetMapping (value = "/findProduct/{idProduct}")
    public ResponseEntity<Object> findCategory(@PathVariable Long idProduct){
        Optional<Product> result = productService.findProductByid(idProduct);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping (value="/update")
    public ResponseEntity<Object> updateProduct(@RequestBody Product product){
        Product result = productService.updateProduct(product);
        return ResponseEntity.ok().body(result);
    }


}
