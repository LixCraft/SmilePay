package bdd.service;

import bdd.dao.AddressDao;
import bdd.entity.Address;

import java.util.List;

public class AddressDatabaseService {

    private static AddressDao addressDao;

    public AddressDatabaseService(){
        addressDao = new AddressDao();
    }

    public Address create(Address entryAddress){
        addressDao.openCurrentSession();
        Address address = addressDao.create(entryAddress);
        addressDao.closeCurrentSession();
        return address;
    }

    public Address update(Address entryAddress){
        addressDao.openCurrentSession();
        Address address = addressDao.update(entryAddress);
        addressDao.closeCurrentSession();
        return address;
    }

    public Address getById(Integer id) {
        addressDao.openCurrentSession();
        Address address = addressDao.findById(id);
        addressDao.closeCurrentSession();
        return address;
    }

    public void delete(Address entryAddress){
        addressDao.openCurrentSession();
        addressDao.delete(entryAddress);
        addressDao.closeCurrentSession();
    }

    public List<Address> findAll() {
        addressDao.openCurrentSession();
        List<Address> addresses = addressDao.findAll();
        addressDao.closeCurrentSession();
        return addresses;
    }
}
