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


    @Override                   //Add customer to db, returns 0 if failed, 1 if success
    public int insert(Customer object) {
        return newTunesDAO.addCustomer(object);
    }
    @Override
    public int update(Customer object) {
        return newTunesDAO.updateCustomer(object);
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
    @Override                   //Returns a single customer from the db whose id matches searched id
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
