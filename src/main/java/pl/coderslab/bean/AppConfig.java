package pl.coderslab.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AppConfig {

    @Bean
    public SimpleCustomerLogger simpleCustomerLogger() {
        return new SimpleCustomerLogger();
    }

//    @Bean
//    public FileCustomerLogger fileCustomerLogger() {
//        return new FileCustomerLogger("log.txt");
//    }
//
//    @Bean
//    public ListCustomerRepository memoryCustomerRepository() {
//        return new ListCustomerRepository(fileCustomerLogger());
//    }

//    @Bean
//    public DdCustomerLogger dbCustomerLogger() {
//        return new DdCustomerLogger();
//    }
//
//    @Bean
//    public DbCustomerRepository dbCustomerRepository() {
//        return new DbCustomerRepository(dbCustomerLogger());
//    }
}