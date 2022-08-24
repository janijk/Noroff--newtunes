package com.assignment.newtunes.models;

import com.assignment.newtunes.dataaccess.NewTunesDAO;
import com.assignment.newtunes.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {
    @Autowired
    NewTunesDAO newTunesDAO;
    private final String url;
    private final String username;
    private final String password;
    public CustomerRepositoryImpl(
            @Value("${spring.datasource.url}") String url,
            @Value("${spring.datasource.username}") String username,
            @Value("${spring.datasource.password}") String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }


    @Override
    public int insert(Customer object) {
        return 0;
    }
    @Override
    public int update(Customer object) {
        return 0;
    }
    @Override
    public int delete(Customer object) {
        return 0;
    }
    @Override
    public int deleteById(Integer id) {
        return 0;
    }
    @Override                    //Returns all customers from the db
    public List<Customer> findAll() {
        return newTunesDAO.getAllCustomers();
    }
    @Override                   //Returns a single customer from the db with matching id
    public Customer findById(Integer id) {
        return newTunesDAO.getCustomerById(id);
    }
    @Override                   //Returns customer(s) whose first or lastname matches search word
    public List<Customer> findByFirstOrLastName(String name) {
        return newTunesDAO.getCustomerByName(name);
    }
    @Override                   //Returns specified (starting from row, ending to row) amount of customers from the db
    public List<Customer> returnOnePage(int amountOfRows, int beginningAtRow) {
        return newTunesDAO.getOnePageOfCustomers(amountOfRows,beginningAtRow);
    }

}
