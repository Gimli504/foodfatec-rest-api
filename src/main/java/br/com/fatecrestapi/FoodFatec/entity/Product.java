package br.com.fatecrestapi.FoodFatec.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private Long idProduct;

    @Column(name = "name_product", nullable = false, length = 300)
    @NotBlank(message = "O campo nome é obrigatório")
    @Length(min = 2,max = 300,message = "O nome deve ter entre 2 e 300")
    private String nameProduct;

    @Column(name = "description_product",nullable = false, length = 3000)
    @NotBlank(message = "O campo descrição deve ser preenchido")
    @Length(min=2, max=3000,message = "O  descrição deve ter entre 2 e 300 caracteres")
    private String descriptionProduct;



    @Column(name = "sku_product",nullable = false, length = 10)
    @Length(min=2, max=10,message = "O SKU deve ter entre 2 e 10 caracteres")
    @NotBlank(message = "O campo SKU deve ser preenchido")
    private String skuProduct;

    @Column(name = "ean_product",nullable = false, length = 15)
    @Length(min=2, max=15,message = "O EAN deve ter entre 2 e 15 caracteres")
    @NotBlank(message = "O campo EAN deve ser preenchido")
    private String eanProduct;

    @Column(name = "cost_produto",nullable = false, precision = 10, scale = 2)
    @NotBlank(message = "O preço de custo é obrigatório")
    private BigDecimal costProduct;

    @Column(name = "amount_produto",nullable = false,precision = 10,scale = 2)
    @NotBlank(message = "O preço de venda é obrigatório")
    private BigDecimal amountProduct;

    @Column(name = "published_Product",nullable = false)
    private Boolean publishedProduct;

    @Column(name = "date_create_product", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreateProduct;

    @ManyToOne
    @JoinColumn(name = "id_category")
    private Category category;
    @PrePersist
    private void prePersist(){
        this.setPublishedProduct(true);
        this.setDateCreateProduct(LocalDate.now());
    }


}
