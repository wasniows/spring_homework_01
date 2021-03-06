package pl.coderslab.bean;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DdCustomerLogger implements CustomerLogger {
    private static final String CREATE_LOG_QUERY = "INSERT INTO log(log) VALUES (?)";
    private static final String URL = "jdbc:mysql://localhost:3306/logger?useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASS = "coderslab";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    @Override
    public void log(String operation) {

        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_LOG_QUERY);
            stmt.setString(1, LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) + " " + operation);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
