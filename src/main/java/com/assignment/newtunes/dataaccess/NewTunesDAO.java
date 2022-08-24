package com.assignment.newtunes.dataaccess;

import com.assignment.newtunes.models.Customer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@Component
public class NewTunesDAO {
    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;

    public NewTunesDAO() {
    }

    public NewTunesDAO(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    // Get all customers from db
    public List<Customer> getAllCustomers(){
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email FROM customer";
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ppsm =con.prepareStatement(sql);
            ResultSet rs = ppsm.executeQuery();
            while (rs.next()){
                Customer cust = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("country"),
                        rs.getString("postal_code"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                customers.add(cust);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customers;
    }

    // Get a specific customer from db identified by customer id
    public Customer getCustomerById(int customerId){
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "+
                "FROM customer WHERE customer_id=?";
        Customer customer = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ppsm = con.prepareStatement(sql);
            ppsm.setInt(1, customerId);
            ResultSet rs = ppsm.executeQuery();
            while (rs.next()){
                customer = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("country"),
                        rs.getString("postal_code"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customer;
    }

    // Get a specific customer(s) from db identified by customers %first name% or %last name%
    public List<Customer> getCustomerByName(String customerName){
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "+
                "FROM customer WHERE first_name LIKE ? OR last_name LIKE ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ppsm = con.prepareStatement(sql);
            ppsm.setString(1, "%"+customerName+"%");
            ppsm.setString(2, "%"+customerName+"%");
            ResultSet rs = ppsm.executeQuery();
            while (rs.next()){
                Customer cust = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("country"),
                        rs.getString("postal_code"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                customers.add(cust);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customers;
    }

    // Fetch a page of customers from the db takes in limit and offset parameters
    public List<Customer> getOnePageOfCustomers(int limit, int offset){
        String sql = "SELECT customer_id, first_name, last_name, country, postal_code, phone, email "+
                "FROM customer ORDER BY customer_id LIMIT ? OFFSET ?";
        List<Customer> customers = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ppsm = con.prepareStatement(sql);
            ppsm.setInt(1,limit);
            ppsm.setInt(2,offset);
            ResultSet rs = ppsm.executeQuery();
            while (rs.next()){
                Customer cust = new Customer(
                        rs.getInt("customer_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("country"),
                        rs.getString("postal_code"),
                        rs.getString("phone"),
                        rs.getString("email")
                );
                customers.add(cust);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return customers;
    }
}
