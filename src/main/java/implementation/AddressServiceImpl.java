package implementation;

import bdd.service.AddressDatabaseService;
import mapper.MapperUtils;
import model.Address;
import service.AddressService;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "service.AddressService")
public class AddressServiceImpl implements AddressService {

    @Override
    public Address createAddress(Address address) {
        AddressDatabaseService service = new AddressDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.Address.class, Address.class).
                map(service.create(
                        MapperUtils.getBasicMapper(Address.class, bdd.entity.Address.class).map(address, bdd.entity.Address.class)
                ), Address.class);
    }

    @Override
    public Address[] getListAddress() {
        AddressDatabaseService service = new AddressDatabaseService();
        List<bdd.entity.Address> addressDatabaseList = service.findAll();
        if(addressDatabaseList != null){
            List<Address> addressList = MapperUtils.getBasicMapper(bdd.entity.Address.class, Address.class).mapAsList(service.findAll(), Address.class);
            return addressList.toArray(new Address[0]);
        } else {
            return new Address[0];
        }
    }

    @Override
    public Address getAddress(Integer id) {
        AddressDatabaseService service = new AddressDatabaseService();
        bdd.entity.Address addressDatabase = service.getById(id);
        if(addressDatabase != null){
            return MapperUtils.getBasicMapper(bdd.entity.Address.class, Address.class).map(service.getById(id), Address.class);
        } else {
            return new Address();
        }
    }

    @Override
    public Address modifyAddress(Address address) {
        AddressDatabaseService service = new AddressDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.Address.class, Address.class).
                map(service.update(
                        MapperUtils.getBasicMapper(Address.class, bdd.entity.Address.class).map(address, bdd.entity.Address.class)
                ), Address.class);
    }

    @Override
    public void deleteAddress(Address address) {
        AddressDatabaseService service = new AddressDatabaseService();
        service.delete(MapperUtils.getBasicMapper(Address.class, bdd.entity.Address.class).map(address, bdd.entity.Address.class));

    }
}
