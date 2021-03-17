package pl.coderslab.app;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pl.coderslab.bean.*;

public class SpringDiApplication {

    public static void main(String[] args) {

        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("====== SimpleCustomerLogger ==========");
        SimpleCustomerLogger simpleCustomerLogger = context
                .getBean(SimpleCustomerLogger.class);
        simpleCustomerLogger.log("Operacja");

        System.out.println();
        System.out.println("====== ListCustomRepository ============");
        Customer customer = new Customer(1, "Jan", "Kowalski");
        Customer customer1 = new Customer(2, "Krzysztof", "Nowak");

        ListCustomerRepository listCustomerRepository = context
                .getBean(ListCustomerRepository.class);
        listCustomerRepository.addCustomer(customer);
        listCustomerRepository.printCustomers();
        listCustomerRepository.addCustomer(customer1);
        listCustomerRepository.printCustomers();
        listCustomerRepository.delCustomer(customer);
        listCustomerRepository.printCustomers();

        System.out.println();
        System.out.println("==========  DbCustomRepository ==========");
        DbCustomerRepository dbCustomerRepository = context
                .getBean(DbCustomerRepository.class);
        dbCustomerRepository.addCustomer(customer);
        dbCustomerRepository.addCustomer(customer1);
        dbCustomerRepository.printCustomers();
        dbCustomerRepository.delCustomer(customer);
        dbCustomerRepository.printCustomers();
    }
}
