package pl.coderslab.bean;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


public class ListCustomerRepository implements CustomerRepository{

    private List<Customer> customerList  = new ArrayList<>();
    private CustomerLogger customerLogger;

    public ListCustomerRepository(CustomerLogger customerLogger) {
        this.customerLogger = customerLogger;
    }

    @Override
    public void addCustomer(Customer customer) {
       customerLogger.log("added to List");
        customerList.add(customer);

    }

    @Override
    public void delCustomer(Customer customer) {
        customerLogger.log("removed from List");
        customerList.remove(customer);
    }

    @Override
    public void allCustomers() {
        System.out.println(customerList);
        customerLogger.log("print from List");
    }
}