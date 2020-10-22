package interfaces;


import java.math.BigDecimal;
import java.sql.Timestamp;

public interface AdminInterface {
    //product
    public void addTypeOfProduct(String name, String maker, String productLine);

    public void addPromotion(String startTime, String endTime, int percent);

    public void addProduct(String name, int typeID, String specification, String url_image, int number,int yearCreate,String placeCreate, BigDecimal price, short installment, int discountID);

    public boolean verifyProductName(String productName);

    public void updateProduct(int id,String name, int typeID, String specification, String url_image, int number,int yearCreate,String placeCreate, BigDecimal price, short installment, int discountID);

    public void deleteProduct(int productID);

    public void addArticle(int providderID, String name, String url_image, String text);

    public boolean verifyArticle(String articleName);

    public void updateArticle(int providderID, String name, String url_image, String text);

    public void deleteArticle(int articleID);

    public void importCurrentProduct(int productID);

    public void showAllImportBills();

    public void updateImportBill(int productID, int number, BigDecimal price, String note);

    public void addCustomer(String name, String phonenumber, String note);

    public boolean verifyCustomerPhonenumber(String phonenumber);

}
