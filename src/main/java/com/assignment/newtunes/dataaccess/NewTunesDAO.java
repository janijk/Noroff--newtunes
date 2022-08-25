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
}
