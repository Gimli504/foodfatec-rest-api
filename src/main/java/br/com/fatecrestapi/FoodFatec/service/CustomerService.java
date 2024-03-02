package br.com.fatecrestapi.FoodFatec.service;

import br.com.fatecrestapi.FoodFatec.entity.Customer;
import br.com.fatecrestapi.FoodFatec.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;
    public List<Customer> getInfoCustomer(){
        return customerRepository.findAll();
    }

    public Customer saveCustomer(Customer customer) {
        if (customer.getMonthlyIncomeCustomer() != null &&
                customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) >= 0 &&
                !customer.getPasswordCustomer().equals("") &&
                customer.getPasswordCustomer() != null) {
            return customerRepository.saveAndFlush(customer);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A renda mensal do cliente é " +
                            "obrigatória e deve ser maior ou igual a 0 (zero)!");
        }
    }

    public HashMap<String,Object> deleteCustomer(Long idCustomer){

        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(idCustomer)).
                orElseThrow(()-> new ResponseStatusException((HttpStatus.NOT_FOUND)));

        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new HashMap<>();
        result.put("result","cliente:"+ customer.get().getFirstNameCustomer() + "" + customer.get().getLastNameCustomer()+ "excluido com sucesso");
        return result;

    }

}
