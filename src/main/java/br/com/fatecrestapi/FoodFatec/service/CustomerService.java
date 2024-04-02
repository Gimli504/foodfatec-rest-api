package br.com.fatecrestapi.FoodFatec.service;


import br.com.fatecrestapi.FoodFatec.entity.Customer;
import br.com.fatecrestapi.FoodFatec.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.math.BigDecimal;
import java.time.LocalDate;
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

        if (validateCustomer(customer)) {

                 encryptPassword(customer);
                return customerRepository.saveAndFlush(customer);


        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A renda mensal do cliente é " +
                            "obrigatória e deve ser maior ou igual a 0 (zero)!");
        }

    }

    public HashMap<String,Object> deleteCustomer(Long idCustomer){

        Optional<Customer> customer = Optional.ofNullable(customerRepository.findById(idCustomer).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente nao encontrado")));

        customerRepository.delete(customer.get());
        HashMap<String, Object> result = new HashMap<>();
        result.put("result","cliente:"+ customer.get().getFirstNameCustomer() + "" + customer.get().getLastNameCustomer()+ "excluido com sucesso");
        return result;

    }

    public Optional<Customer> findCustomerByid(Long idCustomer){
        return Optional.ofNullable(customerRepository.findById(idCustomer)
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Cliente não encontrado")));
    }

    public Customer updateCutomer(Customer customer){
        if(validateCustomer(customer)){

            if (findCustomerByid(customer.getIdCustomer()) != null) {
                encryptPassword(customer);
                return customerRepository.saveAndFlush(customer);
            }else{
                throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cliente não encontrado");
            }

        }else{
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"A renda salarial deve ser maior ou igual a 0");
            }
        }


        public Boolean validateCustomer(Customer customer){
            if(customer.getMonthlyIncomeCustomer() != null &&
                    customer.getMonthlyIncomeCustomer().compareTo(BigDecimal.valueOf(0)) >= 0 &&
                    !customer.getPasswordCustomer().equals("") &&
                    customer.getPasswordCustomer() != null) {
                return true;
            } else {
                return false;
            }
        }


        public Optional<Customer>  findByCpfCustomer(String cpf){
        return Optional.ofNullable(customerRepository.findByCpfCustomer(cpf).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"nenhum cliente com esse cpf foi encontrado")));
        }
       public Optional<Customer>  findByEmailCustomer(String email){
        return Optional.ofNullable(customerRepository.findByEmailCustomer(email).
                orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"nenhum cliente com esse email foi encontrado")));
        }

        public List<Customer> findByDateCreate(LocalDate dateCreatedCustomer){
        return customerRepository.findByDateCreate(dateCreatedCustomer);
        }


        public void encryptPassword(Customer customer){
          BCryptPasswordEncoder encrypt = new BCryptPasswordEncoder();
          String encryptPassword = null;

          if (customer.getIdCustomer() == null){
              encryptPassword = encrypt.encode(customer.getPasswordCustomer());
               customer.setPasswordCustomer(encryptPassword);
          }else{
              if(!customerRepository.findById(customer.getIdCustomer()).get().getPasswordCustomer().equals(customer.getPasswordCustomer())){
                  encryptPassword = encrypt.encode(customer.getPasswordCustomer());
                  customer.setPasswordCustomer(encryptPassword);
              }

          }
        }
}
