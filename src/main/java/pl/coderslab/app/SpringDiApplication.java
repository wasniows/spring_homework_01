package pl.coderslab.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.bean.*;

public class SpringDiApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(Config.class);

        SimpleCustomerLogger simpleCustomerLogger = context
                .getBean("simpleCustomerLogger", SimpleCustomerLogger.class);
        simpleCustomerLogger.log("Operacja");

        Customer customer = new Customer(1, "Jan", "Kowalski");
        Customer customer1 = new Customer(2, "Krzysztof", "Nowak");

        MemoryCustomerRepository memoryCustomerRepository = context
                .getBean("memoryCustomerRepository", MemoryCustomerRepository.class);
        memoryCustomerRepository.addCustomer(customer);
        memoryCustomerRepository.allCustomers();
        memoryCustomerRepository.addCustomer(customer1);
        memoryCustomerRepository.allCustomers();
        memoryCustomerRepository.delCustomer(customer);
        memoryCustomerRepository.allCustomers();

        DBCustomerRepository dbCustomerRepository = context
                .getBean("dbCustomerRepository", DBCustomerRepository.class);
        dbCustomerRepository.addCustomer(customer);
        dbCustomerRepository.addCustomer(customer1);
        dbCustomerRepository.allCustomers();
        dbCustomerRepository.delCustomer(customer);
        dbCustomerRepository.allCustomers();
    }
}
