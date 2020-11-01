package service;

import interfaces.CustomerInterface;

import java.math.BigDecimal;

public class CustomerService implements CustomerInterface {
    @Override
    public void showAllProducts() {

    }

    @Override
    public void showHotProducts() {

    }

    @Override
    public void showBigsaleProducts() {

    }

    @Override
    public void showProductsInConditions(BigDecimal fromPrice, BigDecimal toPrice, String maker, String sort, boolean isInstallment) {

    }

    @Override
    public void showAllArticles() {

    }

    @Override
    public void showProductInDetail(int productID) {

    }

    @Override
    public void showArticleInDetail(int articleID) {

    }

    @Override
    public void showSameProducts(int mainProductID) {

    }

    @Override
    public void showOrdersOfCustomer(String phonenumber) {

    }

    @Override
    public void showProductsInOrder(int orderID) {

    }

    @Override
    public void showOrderInDetail(int orderID) {

    }

    @Override
    public void createOrderBill(String name, String phonenumber, String address) {

    }

    @Override
    public boolean isCustomerExist(String phonenumber) {
        return false;
    }

    @Override
    public void createCustomer(String name, String phonenumber) {

    }

    @Override
    public void addProductToOrder(int productID) {

    }

    @Override
    public void increaseNumberOfProduct(int productID) {

    }

    @Override
    public void decreaseNumberOfProduct(int productID) {

    }
}
