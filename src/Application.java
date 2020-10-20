import service.AdminService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL Drive Driver Not Found");
                return;
            }
            System.out.println("MySQL JDBC Registered");
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Hoangnt1@");
                AdminService adminService = new AdminService(connection);
                adminService.addTypeOfProduct("xps","dell","laptop");
            }
            catch (SQLException e)
            {
                System.out.println("Connection Fail! Exception: " + e);
                return;
            }
        }
    }
}
