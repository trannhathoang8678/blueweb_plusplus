package service;

import interfaces.AdminInterface;

import java.math.BigDecimal;
import java.sql.*;

public class AdminService implements AdminInterface {
    private Connection connection;

    public AdminService(Connection connection) {
        this.connection = connection;
    }

    public AdminService() {
    }
    public void turnOffSafeMode() {
        String sql = "SET sql_safe_updates = 0";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void turnOnSafeMode() {
        String sql = "SET sql_safe_updates = 1";
        try {
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        //turn off safe_mode before delete
        String deleteProductEntity = "DELETE FROM PRODUCT WHERE product_id='" + productID +"';";
        try{
            Statement statement = connection.createStatement();
            statement.execute(deleteProductEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addArticle(int providderID, String name, String url_image, String content) {
        if(!verifyArticle(name))
        {
            System.out.println("This article name have already existed");
            return;
        }
        String addMarketingArticle = "INSERT INTO MARKETING_ARTICLE (provider_id,name,url_image,content) VALUES ('" + providderID + "','"
                + name + "','" + url_image + "','" + content + "');";
         System.out.println(addMarketingArticle);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addMarketingArticle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyArticle(String articleName) {
        String getArticleNameList = "SELECT name FROM MARKETING_ARTICLE";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getArticleNameList);
            while(rs.next())
            {
                if(rs.getString(1).equals(articleName))
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
    public void updateArticle(int articleID,int providerID, String name, String url_image, String content) {
        String updateArticleInfo = "UPDATE MARKETING_ARTICLE SET ";
        if(name != null)
        {
            updateArticleInfo += "name='" + name + "',";
        }
        if(providerID != -1)
        {
            updateArticleInfo += "provider_id" + providerID + ",";
        }
        if(url_image != null)
        {
            updateArticleInfo += "url_image='" + url_image + "',";
        }
        if(content != null)
        {
            updateArticleInfo += "content='" + content + "',";
        }
        if(updateArticleInfo.equals("UPDATE PRODUCT SET "))
        {
            System.out.println("No information change");
            return;
        }
        updateArticleInfo = updateArticleInfo.substring(0,updateArticleInfo.length()-1);
        updateArticleInfo += " WHERE article_id ='" + articleID + "';";
        //System.out.println(updateArticleInfo);
        try
        {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateArticleInfo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(int articleID) {
        String deleteArticleEntity = "DELETE FROM MARKETING_ARTICLE WHERE article_id='" + articleID +"';";
        try{
            Statement statement = connection.createStatement();
            statement.execute(deleteArticleEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void addArticleProductRelationship(int articleID, int productID) {
        if(!verifyArticleProductRelationship(articleID,productID))
        {
            System.out.println("This relationship has already existed");
            return ;
        }
        String addRelationship = "INSERT INTO ARTICLE_PRODUCT  VALUES ('" + articleID + "','" + productID + "');" ;
       //  System.out.println(addRelationship);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addRelationship);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean verifyArticleProductRelationship(int articleID, int productID) {
        String getRelationshipList = "SELECT * FROM ARTICLE_PRODUCT";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getRelationshipList);
            while(rs.next())
            {
              //  System.out.println(rs.getInt(1) + " " + rs.getInt(2));
                if(rs.getInt(1)==articleID&&rs.getInt(2)==productID)
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
    public void deleteArticleProductRelationship(int articleID, int productID) {
        String deleteRelationship = "DELETE FROM ARTICLE_PRODUCT WHERE article_id='" + articleID +"' AND product_id=" + productID +";";
        try{
            Statement statement = connection.createStatement();
            statement.execute(deleteRelationship);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void importCurrentProduct(int productID, int number, BigDecimal price, String note) {
        String getProductNumber = "SELECT number FROM PRODUCT WHERE product_id=" + productID + ";";
        String importProduct = "UPDATE PRODUCT SET number=";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs =statement.executeQuery(getProductNumber);
            while(rs.next())
            {
                importProduct += (rs.getInt(1) + number) + " WHERE product_id=" + productID + ";";
            }
            statement.execute(importProduct);
            createImportBill(productID,number,price,note);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void createImportBill(int productID, int number, BigDecimal price, String note) {

    }

    @Override
    public void showAllImportBills() {
    }

    @Override
    public void updateImportBill(int billID,int productID, int number, BigDecimal price, String note) {
    }

    @Override
    public void addCustomer(String name, String phonenumber, String note) {
    }

    @Override
    public boolean verifyCustomerPhonenumber(String phonenumber) {
        return true;
    }
}
