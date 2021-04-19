package bdd.dao;

import bdd.entity.Merchant;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.Date;
import java.util.List;

public class MerchantDao implements MerchantDaoInterface<Merchant, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    public MerchantDao() {
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
    public Merchant create(Merchant entity) {
        entity.setCreateDate(new Date());
        Transaction tx = getCurrentSession().beginTransaction();
        Integer merchantId = (Integer) getCurrentSession().save(entity);
        tx.commit();
        entity.setId(merchantId);
        return entity;
    }

    @Override
    public Merchant update(Merchant entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().update(entity);
        tx.commit();
        return (Merchant) getCurrentSession().get(Merchant.class, entity.getId());
    }

    @Override
    public Merchant findById(Integer id) {
        return (Merchant) getCurrentSession().get(Merchant.class, id);
    }

    @Override
    public void delete(Merchant entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().delete(entity);
        tx.commit();
    }

    @Override
    public List<Merchant> findAll() {
        List<Merchant> merchants = (List<Merchant>) getCurrentSession().createQuery("from " + Merchant.class.getSimpleName()).list();
        return merchants;
    }
}
