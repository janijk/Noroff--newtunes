package com.assignment.newtunes.models;

public record Customer (int id,
                        String first_name,
                        String last_name,
                        String country,
                        String postal_code,
                        String phone_number,
                        String email){
}
