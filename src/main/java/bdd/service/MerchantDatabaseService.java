package bdd.service;

import bdd.dao.MerchantDao;
import bdd.entity.Merchant;

import java.util.List;

public class MerchantDatabaseService {

    private static MerchantDao merchantDao;

    public MerchantDatabaseService(){
        merchantDao = new MerchantDao();
    }

    public Merchant create(Merchant entryMerchant){
        merchantDao.openCurrentSession();
        Merchant merchant = merchantDao.create(entryMerchant);
        merchantDao.closeCurrentSession();
        return merchant;
    }

    public Merchant update(Merchant entryMerchant){
        merchantDao.openCurrentSession();
        Merchant merchant = merchantDao.update(entryMerchant);
        merchantDao.closeCurrentSession();
        return merchant;
    }

    public Merchant getById(Integer id) {
        merchantDao.openCurrentSession();
        Merchant merchant = merchantDao.findById(id);
        merchantDao.closeCurrentSession();
        return merchant;
    }

    public void delete(Merchant entryMerchant){
        merchantDao.openCurrentSession();
        merchantDao.delete(entryMerchant);
        merchantDao.closeCurrentSession();
    }

    public List<Merchant> findAll() {
        merchantDao.openCurrentSession();
        List<Merchant> merchants = merchantDao.findAll();
        merchantDao.closeCurrentSession();
        return merchants;
    }
}
