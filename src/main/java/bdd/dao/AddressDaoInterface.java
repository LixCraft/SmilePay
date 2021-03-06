package bdd.dao;

import java.io.Serializable;
import java.util.List;

public interface AddressDaoInterface<T, Id extends Serializable> {

    T create(T entity);

    T update(T entity);

    T findById(Id id);

    void delete(T entity);

    List<T> findAll();
}
