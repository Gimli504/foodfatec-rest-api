package br.com.fatecrestapi.FoodFatec.service;

import br.com.fatecrestapi.FoodFatec.entity.Category;
import br.com.fatecrestapi.FoodFatec.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

public class CategoryService {



    @Autowired
    CategoryRepository categoryRepository;
    public List<Category> getInfoCategory(){
        return categoryRepository.findAll();
    }

    public Category saveCategory(Category category) {

        return categoryRepository.saveAndFlush(category);
    }

    public HashMap<String,Object> deleteCategory(Long idCategory){

        Optional<Category> category = Optional.ofNullable(categoryRepository.findById(idCategory).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria não encontada")));

        categoryRepository.delete(category.get());
        HashMap<String, Object> result = new HashMap<>();
        result.put("result","Categoria exluida com sucesso"+ category.get().getNameCategory());
        return result;

    }

    public Optional<Category> findCategoryByid(Long idCategory){
        return Optional.ofNullable(categoryRepository.findById(idCategory)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Categoria não encontrada")));
    }

    public Category updateCategory(Category category){


            return categoryRepository.saveAndFlush(category);


    }

}
