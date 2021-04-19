package implementation;

import bdd.service.ProductDatabaseService;
import mapper.MapperUtils;
import model.Product;
import service.ProductService;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "service.ProductService")
public class ProductServiceImpl implements ProductService {

    @Override
    public Product createProduct(Product product) {
        ProductDatabaseService service = new ProductDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.Product.class, Product.class).
                map(service.create(
                        MapperUtils.getBasicMapper(Product.class, bdd.entity.Product.class).map(product, bdd.entity.Product.class)
                ), Product.class);
    }

    @Override
    public Product[] getListProduct() {
        ProductDatabaseService service = new ProductDatabaseService();
        List<bdd.entity.Product> productDatabaseList = service.findAll();
        if (productDatabaseList != null) {
            List<Product> productList = MapperUtils.getBasicMapper(bdd.entity.Product.class, Product.class).mapAsList(service.findAll(), Product.class);
            return productList.toArray(new Product[0]);
        } else {
            return new Product[0];
        }
    }

    @Override
    public Product getProduct(Integer id) {
        ProductDatabaseService service = new ProductDatabaseService();
        bdd.entity.Product productDatabase = service.getById(id);
        if (productDatabase != null) {
            return MapperUtils.getBasicMapper(bdd.entity.Product.class, Product.class).map(service.getById(id), Product.class);
        } else {
            return new Product();
        }
    }

    @Override
    public Product modifyProduct(Product product) {
        ProductDatabaseService service = new ProductDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.Product.class, Product.class).
                map(service.update(
                        MapperUtils.getBasicMapper(Product.class, bdd.entity.Product.class).map(product, bdd.entity.Product.class)
                ), Product.class);
    }

    @Override
    public void deleteProduct(Product product) {
        ProductDatabaseService service = new ProductDatabaseService();
        service.delete(MapperUtils.getBasicMapper(Product.class, bdd.entity.Product.class).map(product, bdd.entity.Product.class));
    }

}
