package implementation;

import bdd.service.MerchantProductDatabaseService;
import mapper.MapperUtils;
import model.MerchantProduct;
import service.MerchantProductService;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "service.MerchantProductService")
public class MerchantProductServiceImpl implements MerchantProductService {

    @Override
    public MerchantProduct createMerchantProduct(MerchantProduct merchantProduct) {
        MerchantProductDatabaseService service = new MerchantProductDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.MerchantProduct.class, MerchantProduct.class).
                map(service.create(
                        MapperUtils.getBasicMapper(MerchantProduct.class, bdd.entity.MerchantProduct.class).map(merchantProduct, bdd.entity.MerchantProduct.class)
                ), MerchantProduct.class);
    }

    @Override
    public MerchantProduct[] getListMerchantProduct() {
        MerchantProductDatabaseService service = new MerchantProductDatabaseService();
        List<bdd.entity.MerchantProduct> merchantProductDatabaseList = service.findAll();
        if (merchantProductDatabaseList != null) {
            List<MerchantProduct> merchantProductList = MapperUtils.getBasicMapper(bdd.entity.MerchantProduct.class, MerchantProduct.class).mapAsList(service.findAll(), MerchantProduct.class);
            return merchantProductList.toArray(new MerchantProduct[0]);
        } else {
            return new MerchantProduct[0];
        }
    }

    @Override
    public MerchantProduct getMerchantProduct(Integer id) {
        MerchantProductDatabaseService service = new MerchantProductDatabaseService();
        bdd.entity.MerchantProduct merchantProductDatabase = service.getById(id);
        if (merchantProductDatabase != null) {
            return MapperUtils.getBasicMapper(bdd.entity.MerchantProduct.class, MerchantProduct.class).map(service.getById(id), MerchantProduct.class);
        } else {
            return new MerchantProduct();
        }
    }

    @Override
    public MerchantProduct modifyMerchantProduct(MerchantProduct merchantProduct) {
        MerchantProductDatabaseService service = new MerchantProductDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.MerchantProduct.class, MerchantProduct.class).
                map(service.update(
                        MapperUtils.getBasicMapper(MerchantProduct.class, bdd.entity.MerchantProduct.class).map(merchantProduct, bdd.entity.MerchantProduct.class)
                ), MerchantProduct.class);
    }

    @Override
    public void deleteMerchantProduct(MerchantProduct merchantProduct) {
        MerchantProductDatabaseService service = new MerchantProductDatabaseService();
        service.delete(MapperUtils.getBasicMapper(MerchantProduct.class, bdd.entity.MerchantProduct.class).map(merchantProduct, bdd.entity.MerchantProduct.class));

    }
}
