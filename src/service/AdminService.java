package service;

import interfaces.AdminInterface;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.Timestamp;

public class AdminService implements AdminInterface {
    private Connection connection;

    public AdminService(Connection connection) {
        this.connection = connection;
    }

    public AdminService() {
    }

    @Override
    public void addTypeOfProduct(String name, String maker, String productLine) {
        String addProductType = "INSERT INTO TYPE (name,maker,product_line) VALUES ('" + name + "','" + maker + "','" + productLine + "');";
        // System.out.println(addProductType);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addProductType);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addPromotion(String startTime, String endTime, int percent) {
        String addDiscount = "INSERT INTO DISCOUNT (start_time,end_time,percent) VALUES ('" + startTime + "','" + endTime + "','" + percent + "');";
        System.out.println(addDiscount);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(String name, int typeID, String specification, String url_image, int number, BigDecimal price, boolean installment, int discountID) {

        String addProduct = "INSERT INTO PRODUCT (name,typeID,specification,url_image,number,price,installment,discountID) VALUES ('"
                + name + "','" + typeID + "','" + specification + "','" + url_image + "','"
                + number + "','" + price + "','" + installment + "','" + discountID ;
        System.out.println(addProduct);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyProductName(String productName) {
        return true;
    }

    @Override
    public void updateProduct(String name, int typeID, String specification, String url_image, int number, BigDecimal price, boolean installment, int discountID) {
    }

    @Override
    public void deleteProduct(int productID) {
    }

    @Override
    public void addArticle(int providderID, String name, String url_image, String text) {
    }

    @Override
    public boolean verifyArticle(String articleName) {
        return true;
    }

    @Override
    public void updateArticle(int providderID, String name, String url_image, String text) {
    }

    @Override
    public void deleteArticle(int articleID) {
    }

    @Override
    public void importCurrentProduct(int productID) {
    }

    @Override
    public void showAllImportBills() {
    }

    @Override
    public void updateImportBill(int productID, int number, BigDecimal price, String note) {
    }

    @Override
    public void addCustomer(String name, String phonenumber, String note) {
    }

    @Override
    public boolean verifyCustomerPhonenumber(String phonenumber) {
        return true;
    }
}
