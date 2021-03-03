package pl.coderslab.bean;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MemoryCustomerRepository implements CustomerRepository{

    private List<Customer> customerList  = new ArrayList<>();
    private SimpleCustomerLogger simpleCustomerLogger;
    private FileCustomerLogger fileCustomerLogger;
    private DBCustomerLogger dbCustomerLogger;

    @Autowired
    public MemoryCustomerRepository(DBCustomerLogger dbCustomerLogger) {
        this.dbCustomerLogger = dbCustomerLogger;
    }

    @Override
    public void addCustomer(Customer customer) {
       dbCustomerLogger.log("added to List");
        customerList.add(customer);

    }

    @Override
    public void delCustomer(Customer customer) {
        dbCustomerLogger.log("removed from List");
        customerList.remove(customer);
    }

    @Override
    public void allCustomers() {
        System.out.println(customerList);
        dbCustomerLogger.log("print from List");
    }
//    @Override
//    public void addCustomer(Customer customer) {
//        fileCustomerLogger.log("added");
//        customerList.add(customer);
//
//    }
//
//    @Override
//    public void delCustomer(Customer customer) {
//        fileCustomerLogger.log("removed");
//        customerList.remove(customer);
//    }
//
//    @Override
//    public void allCustomers() {
//        System.out.println(customerList);
//        fileCustomerLogger.log("printList");
//    }
}
