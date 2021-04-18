package implementation;

import javax.jws.WebService;

import model.Product;
import service.ProductService;

@WebService(endpointInterface = "service.ProductService")
public class ProductServiceImpl implements ProductService {

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product[] getListProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product modifyProduct(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProduct(Integer productId) {
		// TODO Auto-generated method stub
		return false;
	}

}
