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
    public void addProvider(String name, String phone, String address) {
        String addProviderInfo = "INSERT INTO PROVIDER (name,phone,address) VALUES ('" + name + "','" + phone + "','" + address + "');";
        // System.out.println(addProviderInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addProviderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateProvider(int id, String name, String phone, String address) {
        // if you don't want to change information, write null ( for string) or -1 (for integer)
        String updateProviderInfo = "UPDATE PROVIDER SET ";
        if (name != null) {
            updateProviderInfo += "name='" + name + "',";
        }
        if (phone != null) {
            updateProviderInfo += "phone='" + phone + "',";
        }
        if (address != null) {
            updateProviderInfo += "address='" + address + "',";
        }
        if (updateProviderInfo.equals("UPDATE PROVIDER SET ")) {
            System.out.println("No information change");
            return;
        }
        updateProviderInfo = updateProviderInfo.substring(0, updateProviderInfo.length() - 1);
        updateProviderInfo += " WHERE provider_id ='" + id + "';";
        // System.out.println(updateProviderInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateProviderInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProvider(int id) {
        //turn off safe_mode before delete
        String deleteProviderEntity = "DELETE FROM PROVIDER WHERE provider_id='" + id + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteProviderEntity);
        } catch (Exception e) {
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
    public void updateTypeOfProduct(int id, String name, String maker, String productLine) {
        // if you don't want to change information, write null ( for string) or -1 (for integer)
        String updateTypeInfo = "UPDATE TYPE SET ";
        if (name != null) {
            updateTypeInfo += "name='" + name + "',";
        }
        if (maker != null) {
            updateTypeInfo += "maker='" + maker + "',";
        }
        if (productLine != null) {
            updateTypeInfo += "product_line='" + productLine + "',";
        }
        if (updateTypeInfo.equals("UPDATE TYPE SET ")) {
            System.out.println("No information change");
            return;
        }
        updateTypeInfo = updateTypeInfo.substring(0, updateTypeInfo.length() - 1);
        updateTypeInfo += " WHERE type_id ='" + id + "';";
        // System.out.println(updateTypeInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateTypeInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void deleteTypeOfProduct(int id) {
        //turn off safe_mode before delete
        String deleteTypeEntity = "DELETE FROM TYPE WHERE type_id='" + id + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteTypeEntity);
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
    public void updatePromotion(int id, String startTime, String endTime, int percent) {
        // if you don't want to change information, write null ( for string) or -1 (for integer)
        String updatePromotionInfo = "UPDATE DISCOUNT SET ";
        if (startTime != null) {
            updatePromotionInfo += "start_time='" + startTime + "',";
        }
        if (endTime != null) {
            updatePromotionInfo += "end_time='" + endTime + "',";
        }
        if (percent != -1) {
            updatePromotionInfo += "percent='" + percent + "',";
        }
        if (updatePromotionInfo.equals("UPDATE DISCOUNT SET ")) {
            System.out.println("No information change");
            return;
        }
        updatePromotionInfo = updatePromotionInfo.substring(0, updatePromotionInfo.length() - 1);
        updatePromotionInfo += " WHERE discount_id ='" + id + "';";
        // System.out.println(updatePromotionInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updatePromotionInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deletePromotion(int id) {
        //turn off safe_mode before delete
        String deleteDiscount = "DELETE FROM DISCOUNT WHERE discount_id='" + id + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteDiscount);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addProduct(String name, int typeID, String specification, String url_image,
                           int number, int yearCreate, String placeCreate, BigDecimal price, short installment, int discountID) {
        if (!verifyProductName(name)) {
            System.out.println("This product name have existed");
            return;
        }
        String addProduct = "INSERT INTO PRODUCT (name,type_id,specification,url_image,number,year_create,place_create,price,instalment,discount_id) VALUES ('"
                + name + "','" + typeID + "','" + specification + "','" + url_image + "','"
                + number + "','" + yearCreate + "','" + placeCreate + "','" + price + "','" + installment + "','" + discountID + "');";
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
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getProductNameList);
            while (rs.next()) {
                if (rs.getString(1).equals(productName))
                    return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateProduct(int id, String name, int typeID, String specification, String url_image,
                              int number, int yearCreate, String placeCreate, BigDecimal price, short installment, int discountID) {
        // if you don't want to change information, write null ( for string) or -1 (for integer)
        String updateProductInfo = "UPDATE PRODUCT SET ";
        if (name != null) {
            updateProductInfo += "name='" + name + "',";
        }
        if (typeID != -1) {
            updateProductInfo += "type_id='" + typeID + "',";
        }
        if (specification != null) {
            updateProductInfo += "specification='" + specification + "',";
        }
        if (url_image != null) {
            updateProductInfo += "url_image='" + url_image + "',";
        }
        if (number != -1) {
            updateProductInfo += "number='" + number + "',";
        }
        if (yearCreate != -1) {
            updateProductInfo += "year_create='" + yearCreate + "',";
        }
        if (placeCreate != null) {
            updateProductInfo += "place_create='" + placeCreate + "',";
        }
        if (price != null) {
            updateProductInfo += "price='" + price + "',";
        }
        if (installment != -1) {
            updateProductInfo += "installment='" + installment + "',";
        }
        if (discountID != -1) {
            updateProductInfo += "discount_id='" + discountID + "',";
        }
        if (updateProductInfo.equals("UPDATE PRODUCT SET ")) {
            System.out.println("No information change");
            return;
        }
        updateProductInfo = updateProductInfo.substring(0, updateProductInfo.length() - 1);
        updateProductInfo += " WHERE product_id ='" + id + "';";
        //  System.out.println(updateProductInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateProductInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteProduct(int productID) {
        //turn off safe_mode before delete
        String deleteProductEntity = "DELETE FROM PRODUCT WHERE product_id='" + productID + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteProductEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addArticle(int providderID, String name, String url_image, String content) {
        if (!verifyArticle(name)) {
            System.out.println("This article name have already existed");
            return;
        }
        String addMarketingArticle = "INSERT INTO MARKETING_ARTICLE (provider_id,name,url_image,content) VALUES ('" + providderID + "','"
                + name + "','" + url_image + "','" + content + "');";
        //   System.out.println(addMarketingArticle);
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
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getArticleNameList);
            while (rs.next()) {
                if (rs.getString(1).equals(articleName))
                    return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void updateArticle(int articleID, int providerID, String name, String url_image, String content) {
        String updateArticleInfo = "UPDATE MARKETING_ARTICLE SET ";
        if (name != null) {
            updateArticleInfo += "name='" + name + "',";
        }
        if (providerID != -1) {
            updateArticleInfo += "provider_id" + providerID + ",";
        }
        if (url_image != null) {
            updateArticleInfo += "url_image='" + url_image + "',";
        }
        if (content != null) {
            updateArticleInfo += "content='" + content + "',";
        }
        if (updateArticleInfo.equals("UPDATE PRODUCT SET ")) {
            System.out.println("No information change");
            return;
        }
        updateArticleInfo = updateArticleInfo.substring(0, updateArticleInfo.length() - 1);
        updateArticleInfo += " WHERE article_id ='" + articleID + "';";
        //System.out.println(updateArticleInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateArticleInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteArticle(int articleID) {
        String deleteArticleEntity = "DELETE FROM MARKETING_ARTICLE WHERE article_id='" + articleID + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteArticleEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addArticleProductRelationship(int articleID, int productID) {
        if (!verifyArticleProductRelationship(articleID, productID)) {
            System.out.println("This relationship has already existed");
            return;
        }
        String addRelationship = "INSERT INTO ARTICLE_PRODUCT  VALUES ('" + articleID + "','" + productID + "');";
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
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getRelationshipList);
            while (rs.next()) {
                //  System.out.println(rs.getInt(1) + " " + rs.getInt(2));
                if (rs.getInt(1) == articleID && rs.getInt(2) == productID)
                    return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public void deleteArticleProductRelationship(int articleID, int productID) {
        String deleteRelationship = "DELETE FROM ARTICLE_PRODUCT WHERE article_id='" + articleID + "' AND product_id=" + productID + ";";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteRelationship);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importCurrentProduct(int productID, int provider_id, int number, BigDecimal price, String note) {
        String getProductNumber = "SELECT number FROM PRODUCT WHERE product_id=" + productID + ";";
        String importProduct = "UPDATE PRODUCT SET number=";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(getProductNumber);
            while (rs.next()) {
                importProduct += (rs.getInt(1) + number) + " WHERE product_id=" + productID + ";";
            }
            if (importProduct.equals("UPDATE PRODUCT SET number=")) {
                System.out.println("This product does not exist");
                return;
            }
            // System.out.println(importProduct);
            statement.execute(importProduct);
            createImportBill(productID, provider_id, number, price, note);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createImportBill(int productID, int providerID, int number, BigDecimal price, String note) {
        String addBill = "INSERT INTO IMPORT_BILL(product_id,provider_id,number,price,note)  VALUES ('" + productID + "','" + providerID + "','" + number + "','" + price + "','" + note + "');";
        //  System.out.println(addBill);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addBill);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showAllImportBills() {
        String showBills = "SELECT * FROM IMPORT_BILL";
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(showBills);
            while (rs.next()) {
                System.out.println("Bill id: " + rs.getInt(1) + " Product id: " + rs.getInt(2) + " Provider id : " + rs.getInt(3));
                System.out.println("Number: " + rs.getInt(4) + " Price: " + rs.getBigDecimal(5) + "Created time: " + rs.getTimestamp(7));
                System.out.println("Note: " + rs.getString(6));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addCustomer(String name, String phone, String note) {
        if (verifyCustomerPhonenumber(phone)!=0) {
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
    public void updateCustomer(int id, String name, String phone, String note) {
        // if you don't want to change information, write null ( for string) or -1 (for integer)
        String updateCustomerInfo = "UPDATE CUSTOMER SET ";
        if (name != null) {
            updateCustomerInfo += "name='" + name + "',";
        }
        if (phone != null) {
            updateCustomerInfo += "phone_number='" + phone + "',";
        }
        if (note != null) {
            updateCustomerInfo += "note='" + note + "',";
        }
        if (updateCustomerInfo.equals("UPDATE CUSTOMER SET ")) {
            System.out.println("No information change");
            return;
        }
        updateCustomerInfo = updateCustomerInfo.substring(0, updateCustomerInfo.length() - 1);
        updateCustomerInfo += " WHERE customer_id ='" + id + "';";
        // System.out.println(updateCustomerInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateCustomerInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int id) {
        //turn off safe_mode before delete
        String deleteCustomerEntity = "DELETE FROM CUSTOMER WHERE customer_id='" + id + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteCustomerEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
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

    public void addHotProduct(int productID, int isHot, int hotDisplayID, int isBigsale, int bigsaleID) {
        // write 0 for false and 1 for true for isHot and isBigsale conditions
        // write -1 if product is not on display
        String addHotProductDisplay = "INSERT INTO HOT_PRODUCT (product_id,is_hot,hot_display_id,is_bigsale,bigsale_id) VALUES ('"
                + productID + "','" + isHot + "','" + hotDisplayID + "','" + isBigsale + "','" + bigsaleID + "');";
        // System.out.println(addCustomerInfo);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(addHotProductDisplay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateHotProduct(int productID, int isHot, int hotDisplayID, int isBigsale, int bigsaleID) {
        // if you don't want to change information, write null ( for string) or -1 (for integer)
        String updateHotProductDisplay = "UPDATE HOT_PRODUCT SET ";

        if (isHot != -1) {
            updateHotProductDisplay += "is_hot='" + isHot + "',";
        }
        if (hotDisplayID != -1) {
            updateHotProductDisplay += "hot_display_id='" + hotDisplayID + "',";
        }
        if (isBigsale != -1) {
            updateHotProductDisplay += "is_bigsale='" + isBigsale + "',";
        }
        if (bigsaleID != -1) {
            updateHotProductDisplay += "bigsale_id='" + bigsaleID + "',";
        }
        if (updateHotProductDisplay.equals("UPDATE HOT_PRODUCT SET ")) {
            System.out.println("No information change");
            return;
        }
        updateHotProductDisplay = updateHotProductDisplay.substring(0, updateHotProductDisplay.length() - 1);
        updateHotProductDisplay += " WHERE product_id ='" + productID + "';";
        // System.out.println(updateHotProductDisplay);
        try {
            Statement statement = connection.createStatement();
            int rowAffected = statement.executeUpdate(updateHotProductDisplay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteHotProduct(int id) {
        //turn off safe_mode before delete
        String deleteHotProductDisplay = "DELETE FROM HOT_PRODUCT WHERE product_id='" + id + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(deleteHotProductDisplay);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
