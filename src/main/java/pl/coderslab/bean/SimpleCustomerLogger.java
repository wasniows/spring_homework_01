package pl.coderslab.bean;

import java.time.LocalDateTime;

public class SimpleCustomerLogger implements CustomerLogger {
    @Override
    public void log(String operation) {

        System.out.println(LocalDateTime.now()+" "+ operation);
    }
}
