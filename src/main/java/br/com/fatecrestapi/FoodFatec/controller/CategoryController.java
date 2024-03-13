package br.com.fatecrestapi.FoodFatec.controller;


import br.com.fatecrestapi.FoodFatec.entity.Category;
import br.com.fatecrestapi.FoodFatec.entity.Customer;
import br.com.fatecrestapi.FoodFatec.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/category" )
@CrossOrigin(value = "*")
public class CategoryController {


    @Autowired
    private CategoryService categoryService;

    @GetMapping (value="/list")
    public ResponseEntity<Object> getInfoCategory(){
        List<Category> result = categoryService.getInfoCategory();
        return ResponseEntity.ok().body(result);
    }

    @PostMapping(value="/create")
    public ResponseEntity<Object> saveCategory(@RequestBody Category category) {
        Category result = categoryService.saveCategory(category);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping(value = "/delete/{idCategory}")
    public ResponseEntity<Object> deleteCategory(@PathVariable Long idCategory){
        HashMap<String,Object> result = categoryService.deleteCategory(idCategory);
        return ResponseEntity.ok().body(result);

    }

    @GetMapping (value = "/findCategory/{idCategory}")
    public ResponseEntity<Object> findCategory(@PathVariable Long idCategory){
        Optional<Category> result = categoryService.findCategoryByid(idCategory);
        return ResponseEntity.ok().body(result);
    }

    @PutMapping (value="/update")
    public ResponseEntity<Object> updateCategory(@RequestBody Category category){
        Category result = categoryService.updateCategory(category);
        return ResponseEntity.ok().body(result);
    }




}
