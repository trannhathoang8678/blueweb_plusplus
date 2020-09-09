package models;

import java.math.BigDecimal;

public class Product {
    private int productID,discountID,typeID;
    private String name;
    private BigDecimal price;
    private String specifications,url_image;
    private int number;
    private boolean installment;

    public Product(int productID, int discountID, int typeID, String name, BigDecimal price, String specifications, String url_image, int number, boolean installment) {
        this.productID = productID;
        this.discountID = discountID;
        this.typeID = typeID;
        this.name = name;
        this.price = price;
        this.specifications = specifications;
        this.url_image = url_image;
        this.number = number;
        this.installment = installment;
    }

    public Product() {
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public int getDiscountID() {
        return discountID;
    }

    public void setDiscountID(int discountID) {
        this.discountID = discountID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public boolean isInstallment() {
        return installment;
    }

    public void setInstallment(boolean installment) {
        this.installment = installment;
    }
}
