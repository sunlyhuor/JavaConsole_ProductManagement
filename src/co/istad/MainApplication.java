package co.istad;

import co.istad.controller.MenuController;
import co.istad.controller.ProductController;
import co.istad.exception.MenuOptionNotFoundException;
import co.istad.util.Singleton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

    private final Scanner scanner;
    private final MenuController menuController;
    private final ProductController productController;
    public MainApplication() {
        scanner = Singleton.getScanner();
        menuController = Singleton.menuController();
        productController = Singleton.productController();
    }

    private void run() {
        while(true){
            menuController.index();
            System.out.print("Enter options : ");
            int opts = Integer.parseInt(scanner.nextLine());
            switch (opts) {
                case 1 -> productController.index();
                case 2 -> productController.insert();
                case 3 -> productController.updateById();
                case 4 -> productController.deleteById();
                default -> throw new MenuOptionNotFoundException("Please chose a right one(1-4)!");
            }
        }

    }

    static <T> void test( T t ){
        System.out.printf(String.valueOf(t));
    }

    public static void main(String[] args) {
        new MainApplication().run();;

    }
}
