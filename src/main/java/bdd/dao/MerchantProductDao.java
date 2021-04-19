package bdd.dao;

import bdd.entity.Merchant;
import bdd.entity.MerchantProduct;
import bdd.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class MerchantProductDao implements MerchantDaoInterface<MerchantProduct, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    public MerchantProductDao() {
    }

    public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }

    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }

    public void closeCurrentSession() {
        currentSession.close();
    }

    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }

    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }

    public Session getCurrentSession() {
        return currentSession;
    }

    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }

    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }

    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    @Override
    public MerchantProduct create(MerchantProduct entity) {
        entity.setAffiliationDate(new Date());
        entity.setAffiliationId(generateAffiliationId(entity));
        Transaction tx = getCurrentSession().beginTransaction();
        Integer merchantProductId = (Integer) getCurrentSession().save(entity);
        tx.commit();
        entity.setId(merchantProductId);
        return  entity;
    }

    @Override
    public MerchantProduct update(MerchantProduct entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        entity.setAffiliationId(generateAffiliationId(entity));
        getCurrentSession().update(entity);
        tx.commit();
        return (MerchantProduct) getCurrentSession().get(MerchantProduct.class, entity.getId());
    }

    @Override
    public MerchantProduct findById(Integer id) {
        return (MerchantProduct) getCurrentSession().get(MerchantProduct.class, id);
    }

    @Override
    public void delete(MerchantProduct entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().delete(entity);
        tx.commit();
    }

    @Override
    public List<MerchantProduct> findAll() {
        List<MerchantProduct> products = (List<MerchantProduct>) getCurrentSession().createQuery("from " + MerchantProduct.class.getSimpleName()).list();
        return products;
    }

    private String generateAffiliationId(MerchantProduct entity){

        if(entity.getMerchant() != null && entity.getMerchant().getId() != null
                && entity.getProduct() != null && entity.getProduct().getId() != null){
            return entity.getMerchant().getId() + "_" + entity.getProduct().getId();
        } else {
            return null;
        }
    }
}
