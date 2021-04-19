package bdd.dao;

import bdd.entity.Merchant;
import bdd.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class ProductDao implements ProductDaoInterface<Product, Integer>{

    private Session currentSession;

    private Transaction currentTransaction;

    public ProductDao() {
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
    public Product create(Product entity) {
        entity.setCreateDate(new Date());
        Transaction tx = getCurrentSession().beginTransaction();
        Integer productId = (Integer) getCurrentSession().save(entity);
        tx.commit();
        entity.setId(productId);
        return  entity;
    }

    @Override
    public Product update(Product entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().update(entity);
        tx.commit();
        return (Product) getCurrentSession().get(Product.class, entity.getId());
    }

    @Override
    public Product findById(Integer id) {
        return (Product) getCurrentSession().get(Product.class, id);
    }

    @Override
    public void delete(Product entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().delete(entity);
        tx.commit();
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = (List<Product>) getCurrentSession().createQuery("from " + Product.class.getSimpleName()).list();
        return products;
    }
}
