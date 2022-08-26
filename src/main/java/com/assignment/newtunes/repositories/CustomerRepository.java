package com.assignment.newtunes.repositories;

import com.assignment.newtunes.models.Customer;
import com.assignment.newtunes.models.CustomerCountry;
import com.assignment.newtunes.models.CustomerGenre;
import com.assignment.newtunes.models.CustomerSpender;

import java.util.List;

public interface CustomerRepository extends CRUDRepository <Customer,Integer> {
    /**
     * Fetch a page of customers from the db.
     *
     * @param amountOfRows How many customers to show
     * @param beginningAtRow From which row to start showing customers
     * @return List of customers
     */
    List<Customer> returnOnePage(int amountOfRows, int beginningAtRow);

    /**
     * Get a specific customer(s) from db identified by customers %first name% or %last name%
     *
     * @param name Name of customer to search
     * @return List of customers with given name
     */
    List<Customer> findByFirstOrLastName(String name);

    /**
     * Get the country with most customers associated with it
     *
     * @return CustomerCountry object
     */
    CustomerCountry returnCountryWithMostCustomers();

    /**
     * Get the customer that has spent the most money
     *
     * @return CustomerSpender object
     */
    CustomerSpender returnBiggestSpender();

    /**
     * Get a customers most popular genre. Most popular in this context
     * means the genre that corresponds to the most tracks from invoices associated to that customer.
     *
     * @param customerId Id of customer
     * @return CustomerGenre object
     */
    CustomerGenre returnCustomerMostPopularGenre(int customerId);


}
