package interfaces;


import java.math.BigDecimal;

public class AdminInterface {
    //display function
    public void showAllProducts();

    public void showHotProducts();

    public void showBigsaleProducts();

    public void showProductsInConditions(BigDecimal fromPrice, BigDecimal toPrice, String maker, String sort, boolean isInstallment);

    //show MARKETING_ARTICLE
    public void showAllArticles();

    public void showProductInDetail(int id);

    public void showArticleInDetail(int id);

    public void showSameProducts(int idMainProduct);

    public void showOrdersOfCustomer(String phonenumber);

    public void showProductsInOrder(int idOrder);

    public void showOrderInDetail(int idOrder);

    //order
    public void createOrderBill(String name, String phonenumber, String address);

    public boolean isCustomerExist(String phonenumber);

    public void createCustomer(String name, String phonenumber);

    public void addProductToOrder(int idProduct);

    public void increaseNumberOfProduct(int idProduct);

    public void decreaseNumberOfProduct(int idProduct);

}
