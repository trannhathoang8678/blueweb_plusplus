package service;

import interfaces.AdminInterface;

import java.sql.Connection;
import java.sql.Statement;

public class AdminService  {
    private Connection connection;

    public AdminService(Connection connection) {
        this.connection = connection;
    }

    public AdminService() {
    }

    //@Override
    public void addTypeOfProduct(String name, String maker, String productLine) {
        String addProductType = "INSERT INTO TYPE (name,maker,product_line) VALUES ('" + name + "','" + maker + "','" + productLine + "');";
       // System.out.println(addProductType);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addProductType);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
