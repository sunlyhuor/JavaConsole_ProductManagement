package co.istad.controller;

import co.istad.model.Product;
import co.istad.service.ProductService;
import co.istad.service.ProductServiceImpl;
import co.istad.util.Singleton;
import co.istad.view.ProductView;

import java.security.Signature;
import java.util.List;
import java.util.Scanner;

public class ProductController {

    private ProductService productService;
    private Scanner scanner;
    public ProductController() {
        productService = Singleton.getProductServiceImpl();
        scanner = Singleton.getScanner();
    }

    public void index() {
        List<Product> products = productService.select();
        ProductView.printProductList(products);
    }

    public void updateById(){
            System.out.print("Enter Product Id You want to update : ");
            Long id = Long.parseLong(scanner.nextLine());
            System.out.print("-> Enter New Product name : ");
            String name = (scanner.nextLine());
            System.out.print("-> Enter New Product price : ");
            Double price = Double.parseDouble(scanner.nextLine());
            System.out.print("Enter New Product qty : ");
            Integer qty = Integer.parseInt(scanner.nextLine());

            Product pro = new Product();
            pro.setId(id);
            pro.setName(name);
            pro.setPrice(price);
            pro.setQty( qty );

            System.out.println( productService.updateById( pro ) );
    }

    public void deleteById(){
        System.out.print("Enter Product Id You want to delete : ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.println( productService.deleteById(id) );
    }

    public void insert(){
        System.out.print("-> Enter Product name : ");
        String name = (scanner.nextLine());
        System.out.print("-> Enter Product price : ");
        Double price = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter Product qty : ");
        Integer qty = Integer.parseInt(scanner.nextLine());

        Product pro = new Product();
        pro.setName(name);
        pro.setPrice(price);
        pro.setQty( qty );

        System.out.println( productService.insert( pro ) );

    }


}
