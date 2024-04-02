package br.com.fatecrestapi.FoodFatec.service;


import br.com.fatecrestapi.FoodFatec.entity.Product;
import br.com.fatecrestapi.FoodFatec.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;


@Service
public class ProductService
{

    @Autowired
    static ProductRepository productRepository;

    public List<Product> getInfoProduct(){
        return productRepository.findAll();
    }

    public static Product saveProduct(Product product) {

        if(validateProduct(product)){
            return productRepository.saveAndFlush(product);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Alguma coisa deu errado");
        }

    }

    public HashMap<String,Object> deleteProduct(Long idProduct){

        Optional<Product> product = Optional.ofNullable(productRepository.findById(idProduct).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado ")));

        productRepository.delete(product.get());
        HashMap<String, Object> result = new HashMap<>();
        result.put("result","Produto:"+ product.get().getNameProduct() +  "excluido com sucesso");
        return result;

    }

    public Optional<Product> findProductByid(Long idProduct){
        return Optional.ofNullable(productRepository.findById(idProduct)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Produto não encontrado")));
    }

    public Product updateProduct(Product product){
        if(validateProduct(product)){
            return productRepository.saveAndFlush(product);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"ALguma coisa deu errado");
        }
    }



    public static Boolean validateProduct(Product product){
        if(product.getNameProduct().equals("") && product.getDescriptionProduct().equals("")
                 && product.getCostProduct().compareTo(BigDecimal.valueOf(0) )  >=0 ) {
            return false;
        } else {
            return true;
        }
    }

}
