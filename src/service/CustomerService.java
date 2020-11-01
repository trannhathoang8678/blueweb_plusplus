package service;

import interfaces.CustomerInterface;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class CustomerService implements CustomerInterface {
    private Connection connection;

    public CustomerService(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void showAllProducts() {
        String showProducts = "SELECT name,url_image,price FROM PRODUCT;";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showProducts);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + " url_image: " + rs.getString(2));
                System.out.println(rs.getBigDecimal(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProductInDetail(int productID) {
        String showProduct = "SELECT PRODUCT.*,TYPE.name,TYPE.maker,TYPE.product_line,DISCOUNT.percent,DISCOUNT.end_time " +
                "FROM PRODUCT JOIN TYPE ON TYPE.type_id = PRODUCT.type_id " +
                "JOIN DISCOUNT ON DISCOUNT.discount_id = PRODUCT.discount_id" +
                " WHERE product_id=" + productID + ";";
        // System.out.println(showProduct);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showProduct);
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1) + " Name: " + rs.getString(2) + " url_image: " + rs.getString(5));
                System.out.println("Price: " + rs.getBigDecimal(9));
                if (rs.getInt(3) != 0) {
                    System.out.println("Type name: " + rs.getString(12) + " made by " + rs.getString(13));
                    System.out.println("Product line: " + rs.getString(14));
                }
                System.out.println("Release in " + rs.getInt(7) + " in " + rs.getString(8));
                System.out.println(rs.getString(4));
                if (rs.getInt(10) == 1)
                    System.out.println("Installable");
                if (rs.getInt(11) != 0) {
                    System.out.println("Discount " + rs.getInt(15) + "% until " + rs.getTimestamp(16));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showHotProducts() {
        String showHotProducts = "SELECT p.name,p.url_image,p.price FROM HOT_PRODUCT hp " +
                " JOIN PRODUCT p ON p.product_id = hp.product_id  WHERE is_hot=1;";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showHotProducts);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + " url_image: " + rs.getString(2));
                System.out.println(rs.getBigDecimal(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showBigsaleProducts() {
        String showBigSaleProducts = "SELECT p.name,p.url_image,p.price FROM HOT_PRODUCT hp " +
                " JOIN PRODUCT p ON p.product_id = hp.product_id  WHERE is_bigsale=1;";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showBigSaleProducts);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + " url_image: " + rs.getString(2));
                System.out.println(rs.getBigDecimal(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProductsInConditions(BigDecimal fromPrice, BigDecimal toPrice, String maker, String productLine, String sort, int isInstallment) {
        String showProducts = "SELECT p.name,p.url_image,p.price FROM PRODUCT p " +
                "JOIN TYPE t ON p.type_id = t.type_id WHERE TRUE";
        if (fromPrice != null) {
            showProducts += " AND p.price >= " + fromPrice;
        }
        if (toPrice != null) {
            showProducts += " AND p.price <= " + toPrice;
        }
        if (maker != null) {
            showProducts += " AND t.maker ='" + maker + "'";
        }
        if (productLine != null) {
            showProducts += " AND t.product_line ='" + productLine + "'";
        }
        if (isInstallment != -1) {
            showProducts += " AND p.instalment ='" + isInstallment + "'";
        }

        if (sort != null) {
            showProducts += "ORDER BY price " + sort;
        }
        showProducts += ";";
        // System.out.println(showProducts);
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showProducts);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + " url_image: " + rs.getString(2));
                System.out.println(rs.getBigDecimal(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showAllArticles() {
        String showArticles = "SELECT name,url_image,created_time FROM MARKETING_ARTICLE;";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showArticles);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + " url_image: " + rs.getString(2));
                System.out.println("Created time: " + rs.getTimestamp(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showArticleInDetail(int articleID) {
        String showArticle = "SELECT name,url_image,content,created_time,update_time FROM MARKETING_ARTICLE WHERE article_id=" + articleID + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showArticle);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + " url_image: " + rs.getString(2));
                System.out.println("Created time: " + rs.getTimestamp(4));
                System.out.println("Update time: " + rs.getTimestamp(5));
                System.out.println(rs.getString(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void showOrdersOfCustomer(String phonenumber) {
        int customerID = verifyCustomerPhonenumber(phonenumber);
        if (customerID == 0) {
            System.out.println("There is no order of this phonenumber");
            return;
        }
        String showOrders = "SELECT order_id,address,created_time FROM `ORDER` WHERE customer_id=" + customerID + ";";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showOrders);
            while (rs.next()) {
                System.out.println("OrderID : " + rs.getInt(1) + " address: " + rs.getString(2));
                System.out.println("Created time: " + rs.getTimestamp(3));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showProductsInOrder(int orderID) {
        String showProducts = "SELECT p.name,p.url_image,p.price,por.number_product FROM PRODUCT_ORDER por JOIN PRODUCT p ON por.product_id = p.product_id WHERE por.order_id=" + orderID + ";";
        try {
           // System.out.println(showProducts);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showProducts);
            while (rs.next()) {
                System.out.println("Name: " + rs.getString(1) + " url_image: " + rs.getString(2));
                System.out.println("Price: " + rs.getBigDecimal(3) + " number: " + rs.getInt(4));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createOrderBill(String name, String phonenumber, String address) {
        int customerID = verifyCustomerPhonenumber(phonenumber);
        if (customerID == 0) {
            addCustomer(name, phonenumber, "new");
            customerID = verifyCustomerPhonenumber(phonenumber);
        }
        String createBill = "INSERT INTO `ORDER` (customer_id,address) VALUES ('" + customerID + "','" + address + "');";
        // System.out.println(createBill);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(createBill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int verifyCustomerPhonenumber(String phonenumber) {
        String getRelationshipList = "SELECT phone_number,customer_id FROM CUSTOMER";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getRelationshipList);
            while (rs.next()) {
                if (rs.getString(1).equals(phonenumber))
                    return rs.getInt(2);
            }
            return 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addCustomer(String name, String phone, String note) {
        if (verifyCustomerPhonenumber(phone) != 0) {
            System.out.println("This phonenumber has already existed");
            return;
        }
        String addCustomerInfo = "INSERT INTO CUSTOMER (name,phone_number,note) VALUES ('" + name + "','" + phone + "','" + note + "');";
        // System.out.println(addCustomerInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addCustomerInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProductsToOrder(int orderID, int productID, int number, String paymentMode, String option) {
        String addProduct = "INSERT INTO PRODUCT_ORDER  VALUES ('" + orderID + "','" + productID + "','" + number + "','" + paymentMode + "','" + option + "');";
        // System.out.println(addProduct);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateNumberProductsOfOrder(int orderID, int productID, int number) {
        String updateNumber = "UPDATE PRODUCT_ORDER SET number_product = " + number;
        updateNumber += " WHERE order_id =" + orderID + " AND product_id=" + productID + " ;";
       // System.out.println(updateNumber);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProductsInOrder(int orderID, int productID) {
        //turn off safe_mode before delete
        String deleteProducts = "DELETE FROM PRODUCT_ORDER WHERE order_id=" + orderID + " AND product_id=" + productID + ";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteProducts);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
