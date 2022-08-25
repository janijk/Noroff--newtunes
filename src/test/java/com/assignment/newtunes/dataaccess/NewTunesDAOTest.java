package com.assignment.newtunes.dataaccess;

import com.assignment.newtunes.models.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NewTunesDAOTest {

    @Test
    void getAllCustomers_testCustomerListSizeEqualsToTestDBSize_returnAllCustomers() {
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
    void getCustomerById_getSingleCustomerByItsId_returnsCorrectCustomer(){
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
    @Test
    void getCustomerByName_searchForCustomerWithFirstAndLastNameUsingWildcards_returnsCorrectCustomerOrCustomersMatching(){
        //Prepare
        String url = "jdbc:postgresql://localhost:5432/chinook";
        String username = "postgres";
        String password = "jani";
        NewTunesDAO ntDAO = new NewTunesDAO(url,username,password);
        String customerName = "Jack";
        List<Customer> expected = new ArrayList<>();
        expected.add(new Customer(
                17,
                "Jack",
                "Smith",
                "USA",
                "98052-8300",
                "+1 (425) 882-8080",
                "jacksmith@microsoft.com"
        ));
        //Act
        List <Customer> actual = ntDAO.getCustomerByName(customerName);

        //Assert
        assertEquals(expected.toString(),actual.toString());
    }
    @Test
    void getOnePageOfCustomers_setLimitAndOffsetToFive_returnCustomersWithIdsBetweenSixAndTen(){
        //Prepare
        String url = "jdbc:postgresql://localhost:5432/chinook";
        String username = "postgres";
        String password = "jani";
        NewTunesDAO ntDAO = new NewTunesDAO(url,username,password);
        int limit = 5;
        int offset = 5;
        List<Integer> expected = new ArrayList(Arrays.asList(6,7,8,9,10));

        //Act
        List <Customer> customers = ntDAO.getOnePageOfCustomers(5,5);
        List<Integer> actual = new ArrayList();
        customers.forEach(c-> actual.add(c.id()));

        //Assert
        assertEquals(expected,actual);
    }
    @Test
    void addCustomer_insertProvidedCustomerIntoDbIdAutoIncrements_customerIsAddedToDB(){
        //Prepare
        String url = "jdbc:postgresql://localhost:5432/chinook";
        String username = "postgres";
        String password = "jani";
        NewTunesDAO ntDAO = new NewTunesDAO(url,username,password);
        Customer customer = new Customer(
                0,
                "Jane",
                "Doe",
                "UK",
                "11111-000",
                "+44 (12) 3923-5555",
                "jane.doe@anon.com"
        );
        int expected = 1;

        //Act
        int actual = ntDAO.addCustomer(customer);

        //Assert
        assertEquals(expected,actual);
    }
}