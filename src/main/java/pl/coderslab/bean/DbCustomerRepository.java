package pl.coderslab.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Arrays;

@Component
public class DbCustomerRepository implements CustomerRepository {

    private static final String CREATE_CUSTOMER_QUERY = "INSERT INTO customers(id, firstname, lastname) VALUES (?,?,?)";
    private static final String DELETE_CUSTOMER_QUERY = "DELETE FROM customers WHERE id = ?";
    private static final String FIND_ALL_CUSTOMERS_QUERY = "SELECT * FROM customers";

    private static final String URL = "jdbc:mysql://localhost:3306/logger?useSSL=false&characterEncoding=utf8";
    private static final String USER = "root";
    private static final String PASS = "coderslab";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    private CustomerLogger customerLogger;

    public DbCustomerRepository(@Qualifier("dbCustomerLogger") CustomerLogger customerLogger) {
        this.customerLogger = customerLogger;
    }


    @Override
    public void addCustomer(Customer customer) {
        try (Connection connection = getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(CREATE_CUSTOMER_QUERY);
            stmt.setInt(1, customer.getId());
            stmt.setString(2, customer.getFirstName());
            stmt.setString(3, customer.getLastName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerLogger.log("added to DB");
    }

    @Override
    public void delCustomer(Customer customer) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER_QUERY);
            statement.setInt(1, customer.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        customerLogger.log("removed from DB");
    }

    @Override
    public void printCustomers() {
        try (Connection connection = getConnection()) {
            Customer[] customers = new Customer[0];
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_CUSTOMERS_QUERY);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Customer customer = new Customer();
                customer.setId((rs.getInt("id")));
                customer.setFirstName(rs.getString("firstname"));
                customer.setLastName(rs.getString("lastname"));
                customers = addToArray(customer, customers);
            }
            System.out.println(Arrays.toString(customers));

        } catch (SQLException e) {            e.printStackTrace();
        }
        customerLogger.log("print from DB");
    }

    private Customer[] addToArray(Customer c, Customer[] customers) {
        Customer[] tmpCustomers = Arrays.copyOf(customers, customers.length + 1);
        tmpCustomers[customers.length] = c;
        return tmpCustomers;
    }
}
