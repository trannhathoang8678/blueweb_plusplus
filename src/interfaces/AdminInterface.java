package interfaces;


import java.math.BigDecimal;
import java.sql.Timestamp;

public interface AdminInterface {
    //product
    public void addProvider(String name, String phone, String address);

    public void updateProvider(int id, String name, String phone, String address);

    public void deleteProvider(int id);

    public void addTypeOfProduct(String name, String maker, String productLine);

    public void updateTypeOfProduct(int id, String name, String make, String productLine);

    public void deleteTypeOfProduct(int id);

    public void addPromotion(String startTime, String endTime, int percent);

    public void updatePromotion(int id, String startTime, String endTime, int percent);

    public void deletePromotion(int id);

    public void addProduct(String name, int typeID, String specification, String url_image, int number, int yearCreate, String placeCreate, BigDecimal price, short installment, int discountID);

    public boolean verifyProductName(String productName);

    public void updateProduct(int id, String name, int typeID, String specification, String url_image, int number, int yearCreate, String placeCreate, BigDecimal price, short installment, int discountID);

    public void deleteProduct(int productID);

    public void addArticle(int providderID, String name, String url_image, String Content);

    public boolean verifyArticle(String articleName);

    public void updateArticle(int articleID, int providderID, String name, String url_image, String content);

    public void deleteArticle(int articleID);

    public void addArticleProductRelationship(int articleID, int productID);

    public boolean verifyArticleProductRelationship(int articleID, int productID);

    public void deleteArticleProductRelationship(int articleID, int productID);

    public void importCurrentProduct(int productID, int providerID, int number, BigDecimal price, String note);

    public void createImportBill(int productID, int providerID, int number, BigDecimal price, String note);

    public void showAllImportBills();


    public void addCustomer(String name, String phonenumber, String note);

    public void updateCustomer(int id, String name, String phonenumber, String note);

    public void deleteCustomer(int id);

    public int verifyCustomerPhonenumber(String phonenumber);

}
