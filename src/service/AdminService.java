package service;

import interfaces.AdminInterface;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
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
      //  System.out.println(addDiscount);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(String name, int typeID, String specification, String url_image,
                           int number,int yearCreate,String placeCreate, BigDecimal price, short installment, int discountID) {
        if(!verifyProductName(name))
        {
            System.out.println("This product name have existed");
            return;
        }
        String addProduct = "INSERT INTO PRODUCT (name,type_id,specification,url_image,number,year_create,place_create,price,instalment,discount_id) VALUES ('"
                + name + "','" + typeID + "','" + specification + "','" + url_image + "','"
                + number + "','" + yearCreate + "','" + placeCreate + "','"  + price + "','" + installment + "','" + discountID + "');";
     //   System.out.println(addProduct);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addProduct);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyProductName(String productName) {
        String getProductNameList = "SELECT name FROM PRODUCT";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getProductNameList);
            while(rs.next())
            {
                if(rs.getString(1).equals(productName))
                    return false;
            }
            return true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateProduct(int id,String name, int typeID, String specification, String url_image,
                              int number,int yearCreate,String placeCreate, BigDecimal price, short installment, int discountID) {
        // if you don't want to change information, write null ( for string) or -1 (for integer)
        String updateProductInfo = "UPDATE PRODUCT SET ";
        if(name != null)
        {
            updateProductInfo += "name='" + name + "',";
        }
        if(typeID != -1)
        {
            updateProductInfo += "type_id='" + typeID + "',";
        }
        if(specification != null)
        {
            updateProductInfo += "specification='" + specification + "',";
        }
        if(url_image != null)
        {
            updateProductInfo += "url_image='" + url_image + "',";
        }
        if(number != -1)
        {
            updateProductInfo += "number='" + number + "',";
        }
        if(yearCreate != -1)
        {
            updateProductInfo += "year_create='" + yearCreate + "',";
        }
        if(placeCreate != null)
        {
            updateProductInfo += "place_create='" + placeCreate + "',";
        }
        if(price != null)
        {
            updateProductInfo += "price='" + price + "',";
        }
        if(installment != -1)
        {
            updateProductInfo += "installment='" + installment + "',";
        }
        if(discountID != -1)
        {
            updateProductInfo += "discount_id='" + discountID + "',";
        }
        if(updateProductInfo.equals("UPDATE PRODUCT SET "))
        {
            System.out.println("No information change");
            return;
        }
        updateProductInfo = updateProductInfo.substring(0,updateProductInfo.length()-1);
        updateProductInfo += " WHERE product_id ='" + id + "';";
        System.out.println(updateProductInfo);
        try
        {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateProductInfo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
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
