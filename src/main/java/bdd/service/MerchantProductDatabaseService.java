package bdd.service;


import bdd.dao.MerchantProductDao;
import bdd.entity.MerchantProduct;

import java.util.List;

public class MerchantProductDatabaseService {

    private static MerchantProductDao merchantProductDao;

    public MerchantProductDatabaseService() {
        merchantProductDao = new MerchantProductDao();
    }

    public MerchantProduct create(MerchantProduct entryMerchantProduct) {
        merchantProductDao.openCurrentSession();
        MerchantProduct merchantProduct = merchantProductDao.create(entryMerchantProduct);
        merchantProductDao.closeCurrentSession();
        return merchantProduct;
    }

    public MerchantProduct update(MerchantProduct entryMerchantProduct) {
        merchantProductDao.openCurrentSession();
        MerchantProduct merchantProduct = merchantProductDao.update(entryMerchantProduct);
        merchantProductDao.closeCurrentSession();
        return merchantProduct;
    }

    public MerchantProduct getById(Integer id) {
        merchantProductDao.openCurrentSession();
        MerchantProduct merchantProduct = merchantProductDao.findById(id);
        merchantProductDao.closeCurrentSession();
        return merchantProduct;
    }

    public void delete(MerchantProduct entryMerchantProduct) {
        merchantProductDao.openCurrentSession();
        merchantProductDao.delete(entryMerchantProduct);
        merchantProductDao.closeCurrentSession();
    }

    public List<MerchantProduct> findAll() {
        merchantProductDao.openCurrentSession();
        List<MerchantProduct> merchantProducts = merchantProductDao.findAll();
        merchantProductDao.closeCurrentSession();
        return merchantProducts;
    }
}
