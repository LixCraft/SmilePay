package bdd.service;

import bdd.dao.ProductDao;
import bdd.entity.Product;

import java.util.List;

public class ProductDatabaseService {

    private static ProductDao productDao;

    public ProductDatabaseService() {
        productDao = new ProductDao();
    }

    public Product create(Product entryProduct){
        productDao.openCurrentSession();
        Product merchant = productDao.create(entryProduct);
        productDao.closeCurrentSession();
        return merchant;
    }

    public Product update(Product entryProduct){
        productDao.openCurrentSession();
        Product merchant = productDao.update(entryProduct);
        productDao.closeCurrentSession();
        return merchant;
    }

    public Product getById(Integer id) {
        productDao.openCurrentSession();
        Product merchant = productDao.findById(id);
        productDao.closeCurrentSession();
        return merchant;
    }

    public void delete(Product entryProduct){
        productDao.openCurrentSession();
        productDao.delete(entryProduct);
        productDao.closeCurrentSession();
    }

    public List<Product> findAll() {
        productDao.openCurrentSession();
        List<Product> products = productDao.findAll();
        productDao.closeCurrentSession();
        return products;
    }
}
