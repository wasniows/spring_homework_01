package pl.coderslab.bean;

public interface CustomerRepository {

    void addCustomer(Customer customer);
    void delCustomer(Customer customer);
    void printCustomers();

}
