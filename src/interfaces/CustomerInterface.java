package interfaces;

import java.math.BigDecimal;

public interface CustomerInterface {
    //display function
    public void showAllProducts();

    public void showHotProducts();

    public void showBigsaleProducts();

    public void showProductsInConditions(BigDecimal fromPrice, BigDecimal toPrice, String maker, String productLine, String sort, int isInstallment);

    //show MARKETING_ARTICLE
    public void showAllArticles();

    public void showProductInDetail(int productID);

    public void showArticleInDetail(int articleID);

    public void showOrdersOfCustomer(String phonenumber);

    public void showProductsInOrder(int orderID);


    //order
    public void createOrderBill(String name, String phonenumber, String address);

    public void addProductsToOrder(int orderID, int productID, int number, String paymentMode, String option);

    public void updateNumberProductsOfOrder(int orderID, int productID, int number);

    public void deleteProductsInOrder(int orderID, int productID);


}
