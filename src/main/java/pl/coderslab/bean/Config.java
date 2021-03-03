package pl.coderslab.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

    @Bean
    public SimpleCustomerLogger  simpleCustomerLogger(){
        return new SimpleCustomerLogger();
    }

    @Bean
    public DBCustomerLogger dbCustomerLogger(){
        return new DBCustomerLogger();
    }

//    @Bean
//    public MemoryCustomerRepository memoryCustomerRepository(){
//        return new MemoryCustomerRepository(fileCustomerLogger());
//    }

    @Bean
    public MemoryCustomerRepository memoryCustomerRepository(){
        return new MemoryCustomerRepository(dbCustomerLogger());
    }

    @Bean
    public DBCustomerRepository dbCustomerRepository(){
        return new DBCustomerRepository(dbCustomerLogger());
    }


    @Bean
    public FileCustomerLogger fileCustomerLogger(){
        return new FileCustomerLogger("log.txt");
    }
}
