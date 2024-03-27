package br.com.fatecrestapi.FoodFatec.repository;

import br.com.fatecrestapi.FoodFatec.entity.Customer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByCpfCustomer(String cpf);

    Optional<Customer> findByEmailCustomer(String email);

    @Query(value = "SELECT c.* FROM c WHERE c.date_created_customer >= ?;", nativeQuery =true)
    List<Customer> findByDateCreate(@Param("dateCreatedCustomer")LocalDate dateCreatedCustomer);



}
