package co.istad.dao;

import co.istad.database.ProductDatabase;
import co.istad.exception.ProductIdNotFoundException;
import co.istad.model.Product;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class ProductDaoImpl implements ProductDao{
    private final ProductDatabase productDb;
    public ProductDaoImpl() {
        productDb = new ProductDatabase();
    }

    @Override
    public Product insert(Product product) {
        long lastId = (long) (productDb.getProducts().size() + 1);
        product.setId( lastId );
        product.setImportDate(LocalDate.now());
        productDb.getProducts().add(product);
        return product;
    }

    @Override
    public List<Product> select() {

        return productDb.getProducts();
    }

    @Override
    public Optional<Product> selectById(Long id) {
        return productDb.getProducts().stream()
                .filter(product -> product.getId().equals(id))
                .findFirst();
    }

    @Override
    public Product updateById(Product product) {
        Product result = productDb.getProducts().stream()
                .filter(pro -> pro.getId().equals(product.getId()) )
                .findFirst()
                .orElseThrow(()
                        -> new ProductIdNotFoundException(
                                String.format("Product ID : %s does not exist in DB", product.getId()))
                );
        product.setImportDate( result.getImportDate() );
        productDb.getProducts()
                .set( productDb.getProducts().indexOf(result), product );
        return result;
    }

    @Override
    public Product deleteById(Long id) {
        Product foundProduct = productDb.getProducts().stream()
                .filter(product1 -> product1.getId().equals(id))
                .findFirst()
                .orElseThrow(()
                        -> new ProductIdNotFoundException(
                        String.format("Product ID : %s does not exist in DB", id))
                );
        productDb.getProducts().remove(foundProduct);
        return foundProduct;
    }

    @Override
    public List<Product> selectByName(String name) {
        return productDb.getProducts().stream()
                .filter(product -> product.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
    }
}
