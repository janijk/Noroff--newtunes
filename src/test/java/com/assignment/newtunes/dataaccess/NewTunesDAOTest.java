package com.assignment.newtunes.dataaccess;

import com.assignment.newtunes.models.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewTunesDAOTest {

    @Test
    void getAllCustomers_testCustomerListSizeEqualsToTestDBSize_shouldReturnAllCustomers() {
        //Prepare
        String url = "jdbc:postgresql://localhost:5432/chinook";
        String username = "postgres";
        String password = "jani";
        NewTunesDAO ntDAO = new NewTunesDAO(url,username,password);
        int expected = 59;

        //Act
        int actual = ntDAO.getAllCustomers().size();
        List<Customer> customers = ntDAO.getAllCustomers();

        //Assert
        assertEquals(expected,actual);
    }

    @Test
    void getCustomerById_getSingleCustomerByItsId_getSpecificCustomerReturnsCorrectCustomer(){
        //Prepare
        String url = "jdbc:postgresql://localhost:5432/chinook";
        String username = "postgres";
        String password = "jani";
        NewTunesDAO ntDAO = new NewTunesDAO(url,username,password);
        int customerId = 1;
        Customer expected = new Customer(
                1,
                "Luís",
                "Gonçalves",
                "Brazil",
                "12227-000",
                "+55 (12) 3923-5555",
                "luisg@embraer.com.br"
                );

        //Act
        Customer actual = ntDAO.getCustomerById(customerId);

        //Assert
        assertEquals(expected.toString(),actual.toString());
    }
}