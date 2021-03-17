package pl.coderslab.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class FileCustomerLogger implements CustomerLogger{

    private String filename;

    public FileCustomerLogger(@Value("${file_name:log.txt}") String filename) {
        this.filename = filename;
    }


    @Override
    public void log(String operation) {
        LocalDateTime data = LocalDateTime.now();
        try (FileWriter fileWriter = new FileWriter(filename, true)){
            fileWriter.append(data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"))).append(" ").append(operation).append("\n");
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
