package br.com.fatecrestapi.FoodFatec.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_customer")
    private Long idCustomer;

    @Column(name = "first_name_customer", nullable = false, length = 300)
    @NotBlank(message = "O campo nome é obrigatório")
    @Length(min = 2,max = 300,message = "O primeiro nome deve ter entre 2 e 300")
    private String firstNameCustomer;

    @Column(name = "last_name_customer", nullable = false, length = 300)
    @NotBlank(message = "O campo ultimo nome é obrigatório")
    @Length(min = 2,max = 300,message = "O ultimo nome deve ter entre 2 e 300")
    private String lastNameCustomer;

    @Column(name = "cpf_customer",unique = true, nullable = false, length = 11)
    @CPF(message = "CPF é inválido")
    private String cpfCustomer;

    @Column(name = "birthdate_customer", nullable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthdateCustomer;

    @Column(name = "date_create_customer", nullable = false, updatable = false)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateCreateCustomer;

    @Column(name = "monthly_income_customer", nullable = false, precision = 10,scale = 2)
    private BigDecimal monthlyIncomeCustomer;

    @Column(name = "status_customer", nullable = false)
    private Boolean statusCustomer;

    @Column(name = "email_customer",unique = true, nullable = false, length = 300)
    @Email(message = "O Email informado é inválido")
    private String emailCustomer;

    @Column(name = "password_customer", nullable = false, length = 3000)
    private String passwordCustomer;

    @PrePersist
    private void prePersist(){
        this.setStatusCustomer(true);
        this.setDateCreateCustomer(LocalDate.now());
    }
}
