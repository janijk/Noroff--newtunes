package com.assignment.newtunes.models;

import com.assignment.newtunes.repositories.CustomerRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

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
    @Override
    public List<Customer> findAll() {

        return null;
    }
    @Override
    public Customer findById(Integer id) {
        return null;
    }
}
