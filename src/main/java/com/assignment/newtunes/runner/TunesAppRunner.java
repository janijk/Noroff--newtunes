package com.assignment.newtunes.runner;

import com.assignment.newtunes.dataaccess.NewTunesDAO;
import com.assignment.newtunes.models.CustomerRepositoryImpl;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class TunesAppRunner implements ApplicationRunner {
    private final CustomerRepositoryImpl custRepImpl;


    public TunesAppRunner(NewTunesDAO newTunesDAO, CustomerRepositoryImpl cRepImpl) {
        this.custRepImpl = cRepImpl;
    }

    /**
     * Display options menu
     *
     * @param options Array containing possible options
     */
    public static void menu(String[] options){
        for (String option : options){
            System.out.println(option);
        }
        System.out.print("Choose your option 1-" + options.length + ": ");
    }

    /**
     * Ask user for a customer id to search with, then call method to find customer by id.
     */
    public void searchWithId(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give customer id: ");
        boolean cont = true;
        while (cont){
            try {
                System.out.println(custRepImpl.findById(scanner.nextInt()));
                cont = false;
            }catch (InputMismatchException ex){
                System.out.print("Enter customer id as a number: ");
                scanner.next();
            }
        }
    }

    /**
     * Ask user for a customer name to search with, then call method to find customer by name.
     */
    public void searchWithName(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give customer name: ");
        boolean cont = true;
        while (cont){
            try {
                custRepImpl.findByFirstOrLastName(scanner.nextLine())
                        .stream().forEach(customer -> System.out.println(customer.toString()));
                cont = false;
            }catch (InputMismatchException ex){
                System.out.print("Enter customer name: ");
                scanner.next();
            }
        }
    }

    /**
     * Ask user for how many rows of customers to fetch and at which row to start,
     * then call method to get a page of customers.
     */
    public void getAPageOfCustomers(){
        Scanner scanner = new Scanner(System.in);
        boolean cont = true;
        int rowsBegin = 0;
        int rowsAmount = 0;
        while (cont){
            try {
                System.out.print("Give amount of rows: ");
                rowsAmount = scanner.nextInt();
                System.out.print("Beginning at row: ");
                rowsBegin = scanner.nextInt();
                custRepImpl.returnOnePage(rowsAmount,rowsBegin).stream()
                        .forEach(customer -> System.out.println(customer.toString()));
                cont = false;
            }catch (InputMismatchException ex){
                System.out.println("- Enter rows as numbers -");
                scanner.next();
            }
        }
    }

    /**
     * Ask user for a customer id to search with, then call method to find that users most popular genre.
     */
    public void getMostPopularGenre(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Give customer id: ");
        boolean cont = true;
        while (cont){
            try {
                System.out.println(custRepImpl.returnCustomerMostPopularGenre(scanner.nextInt()).genre());
                cont = false;
            }catch (InputMismatchException ex){
                System.out.print("Enter customer id as a number: ");
                scanner.next();
            }
        }
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String[] options = {
                "0. Exit",
                "1. Read all customers",
                "2. Search customer with id",
                "3. Search customer with name",
                "4. Read a page of customers",
                "5. Show country with most customers",
                "6. Show highest spender",
                "7. Show most popular genre for user"
        };
        Scanner scanner = new Scanner(System.in);
        int option=-1;
        while (option!=0){
            menu(options);
            try {
                option = scanner.nextInt();
                switch (option){
                    case 0:
                        option=0;
                        break;
                    case 1:             // Print out all customers from the db
                        custRepImpl.findAll().stream().forEach(customer -> System.out.println(customer.toString()));
                        break;
                    case 2:
                        searchWithId();
                        break;
                    case 3:
                        searchWithName();
                        break;
                    case 4:
                        getAPageOfCustomers();
                        break;
                    case 5:
                        System.out.println(custRepImpl.returnCountryWithMostCustomers().country() + " (" +
                                custRepImpl.returnCountryWithMostCustomers().numberOfCustomers() + " customers)");
                        break;
                    case 6:
                        System.out.println(
                                custRepImpl.findById(custRepImpl.returnBiggestSpender().customerId()).first_name() +
                                " " + custRepImpl.findById(custRepImpl.returnBiggestSpender().customerId()).last_name() +
                                " (total: " + custRepImpl.returnBiggestSpender().spending() + ")");
                        break;
                    case 7:
                        getMostPopularGenre();
                        break;
                }
            }catch (InputMismatchException ex){
                System.out.println("Choose option: 1-" + options.length);
                scanner.next();
            }
        }
    }
}