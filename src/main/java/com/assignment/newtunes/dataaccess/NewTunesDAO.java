package com.assignment.newtunes.dataaccess;

import com.assignment.newtunes.models.Customer;
import com.assignment.newtunes.models.CustomerCountry;
import com.assignment.newtunes.models.CustomerSpender;
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

    // Add a customer to db using takes Customer as parameter
    public int addCustomer(Customer customer){
        String sql = "INSERT INTO customer (first_name, last_name, country, postal_code, phone, email)"+
                "VALUES (?,?,?,?,?,?)";
        int result = 0;
        try (Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement ppsm = con.prepareStatement(sql);
            ppsm.setString(1,customer.first_name());
            ppsm.setString(2,customer.last_name());
            ppsm.setString(3,customer.country());
            ppsm.setString(4,customer.postal_code());
            ppsm.setString(5,customer.phone_number());
            ppsm.setString(6,customer.email());
            result = ppsm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // Update customer information, takes Customer as parameter
    public int updateCustomer(Customer customer) {
        String sql = "UPDATE customer SET first_name = ?, last_name = ?, country = ?, postal_code = ?, " +
                "phone = ?, email = ? WHERE customer_id = ?";
        int result = 0;
        try (Connection con = DriverManager.getConnection(url, username, password)){
            PreparedStatement ppsm = con.prepareStatement(sql);
            ppsm.setString(1,customer.first_name());
            ppsm.setString(2,customer.last_name());
            ppsm.setString(3,customer.country());
            ppsm.setString(4,customer.postal_code());
            ppsm.setString(5,customer.phone_number());
            ppsm.setString(6,customer.email());
            ppsm.setInt(7,customer.id());
            result = ppsm.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }

    public CustomerCountry getCountryByMostCustomers() {
        String sql = "SELECT country, count(*) as cnt FROM customer GROUP BY country ORDER BY cnt DESC LIMIT 1";
        CustomerCountry customerCountry = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ppsm = con.prepareStatement(sql);
            ResultSet rs = ppsm.executeQuery();
            while(rs.next()) {
                customerCountry = new CustomerCountry(rs.getString("country"), rs.getInt("cnt"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerCountry;
    }

    public CustomerSpender getBiggestSpender() {
        String sql = "SELECT customer_id, Sum(total) AS sum_total FROM invoice" +
                " GROUP BY customer_id ORDER BY sum_total LIMIT 1";
        CustomerSpender customerSpender = null;
        try (Connection con = DriverManager.getConnection(url, username, password)) {
            PreparedStatement ppsm = con.prepareStatement(sql);
            ResultSet rs = ppsm.executeQuery();
            while(rs.next()) {
                customerSpender = new CustomerSpender(rs.getInt("customer_id"), rs.getInt("sum_total"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customerSpender;
    }
}
