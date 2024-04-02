package br.com.fatecrestapi.FoodFatec.entity;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Category {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_category")
    private Long idCategory;

    @Column(name = "name_category", nullable = false, length = 300,unique = true)
    private String nameCategory;

    @Column(name = "description_category", nullable = false, length = 1000)
    private String descriptionCategory;


}
