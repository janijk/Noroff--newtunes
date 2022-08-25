package com.assignment.newtunes.dataaccess;

import com.assignment.newtunes.models.Customer;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewTunesDAOTest {

    @Test
    void getAllCustomers_customerListSizeEqualsToTestDBSize_shouldReturnAllCustomers() {
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
}