package bdd.dao;

import bdd.entity.Address;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AddressDao implements AddressDaoInterface<Address, Integer> {

    private Session currentSession;

    private Transaction currentTransaction;

    public AddressDao() {
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
    public Address create(Address entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        Integer merchantId = (Integer) getCurrentSession().save(entity);
        tx.commit();
        entity.setId(merchantId);
        return entity;
    }

    @Override
    public Address update(Address entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().update(entity);
        tx.commit();
        return (Address) getCurrentSession().get(Address.class, entity.getId());
    }

    @Override
    public Address findById(Integer id) {
        return (Address) getCurrentSession().get(Address.class, id);
    }

    @Override
    public void delete(Address entity) {
        Transaction tx = getCurrentSession().beginTransaction();
        getCurrentSession().delete(entity);
        tx.commit();
    }

    @Override
    public List<Address> findAll() {
        List<Address> addresses = (List<Address>) getCurrentSession().createQuery("from " + Address.class.getSimpleName()).list();
        return addresses;
    }
}
