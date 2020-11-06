import interfaces.CustomerInterface;
import service.AdminService;
import service.CustomerService;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Application {
    public static void main(String[] args) {
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("MySQL Drive Driver Not Found");
                return;
            }
            System.out.println("MySQL JDBC Registered");
            Connection connection = null;
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Hoangnt1@");
                AdminService adminService = new AdminService(connection);
                CustomerService customerService = new CustomerService(connection);
                //adminService.addTypeOfProduct("xps","dell","laptop");
                // adminService.addProduct("P53s",1,"high performance","url_image",14,2019,"China",new BigDecimal(33190000.5),(short)1,1);
                //  adminService.addPromotion("2020-10-21 11:37:52","2020-10-21 11:37:52",40);
                //    adminService.updateProduct(2,null,-1,"light and durable",null,-1,2020,null,null,(short)-1,-1);
                //      adminService.addArticle(3,"Choose laptops","ttt","hello world");
                //       adminService.updateArticle(1,-1,"hello","2%3f","hello world");
                //       adminService.deleteArticle(2);
                //  adminService.deleteProduct(2);
                //      adminService.addArticleProductRelationship(1,1);
                //       adminService.deleteArticleProductRelationship(1,1);
                //     adminService.importCurrentProduct(1,1,2,new BigDecimal(32.53),"a");
                //     adminService.addProvider("Hoang","0329609321","28 Pham Hong Thai");
                //    adminService.updateProvider(1,"Vinh",null,null);
                //  adminService.deleteProvider(1);
                // adminService.updateTypeOfProduct(3,"nitro 5","Acer",null);
                // adminService.deleteTypeOfProduct(4);
                //  adminService.updatePromotion(2,null,"2020-10-29 11:37:52",-1);
                //  adminService.deletePromotion(3);
                //    adminService.importCurrentProduct(1,1,6,new BigDecimal(32.5),"");
                //   adminService.showAllImportBills();
                //   adminService.addCustomer("Hoang","0329609321","Thu Thiem Garden");
                //  adminService.updateCustomer(1,"Vinh",null,null);
                //  adminService.deleteCustomer(1);
                // adminService.updateHotProduct(1,-1,1,-1,-1);
                // adminService.deleteHotProduct(1);
                // customerService.showAllProducts();
                // customerService.showProductInDetail(1);
                //customerService.showHotProducts();
                // customerService.showBigsaleProducts();
                //customerService.showProductsInConditions(new BigDecimal(33190000.500),new BigDecimal(33190000.500),"Lenovo","ASC",1);
                //customerService.showAllArticles();
                //customerService.showArticleInDetail(4);
                //customerService.createOrderBill("Vinh","0329609322","Cash","PH");
               // customerService.showOrdersOfCustomer("0329609322");
                //customerService.addProductsToOrder(1,1,45,"grey",new BigDecimal(22.3));
                //customerService.updateNumberProductsOfOrder(2,2,7);
                //customerService.deleteProductsInOrder(2,2);
                //customerService.showProductsInOrder(1);
            } catch (SQLException e) {
                System.out.println("Connection Fail! Exception: " + e);
                return;
            }
        }
    }
}
