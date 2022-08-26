package com.assignment.newtunes.repositories;

import com.assignment.newtunes.models.Customer;
import com.assignment.newtunes.models.CustomerCountry;

import java.util.List;

public interface CustomerRepository extends CRUDRepository <Customer,Integer> {
    List<Customer> returnOnePage(int amountOfRows, int beginningAtRow);
    List<Customer> findByFirstOrLastName(String name);
    CustomerCountry returnCountryWithMostCustomers();


}
