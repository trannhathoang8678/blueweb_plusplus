package interfaces;

import java.math.BigDecimal;

public interface CustomerInterface {
    //display function
    public void showAllProducts();

    public void showHotProducts();

    public void showBigsaleProducts();

    public void showProductsInConditions(BigDecimal fromPrice, BigDecimal toPrice, String maker, String sort, boolean isInstallment);

    //show MARKETING_ARTICLE
    public void showAllArticles();

    public void showProductInDetail(int productID);

    public void showArticleInDetail(int articleID);

    public void showSameProducts(int mainProductID);

    public void showOrdersOfCustomer(String phonenumber);

    public void showProductsInOrder(int orderID);

    public void showOrderInDetail(int orderID);

    //order
    public void createOrderBill(String name, String phonenumber, String address);

    public boolean isCustomerExist(String phonenumber);

    public void createCustomer(String name, String phonenumber);

    public void addProductToOrder(int productID);

    public void increaseNumberOfProduct(int productID);

    public void decreaseNumberOfProduct(int productID);

}
