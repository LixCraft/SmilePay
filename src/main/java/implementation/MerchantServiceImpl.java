package implementation;

import bdd.service.MerchantDatabaseService;
import ma.glasnost.orika.Mapper;
import mapper.MapperUtils;
import model.Merchant;
import service.MerchantService;

import javax.jws.WebService;
import java.util.List;

@WebService(endpointInterface = "service.MerchantService")
public class MerchantServiceImpl implements MerchantService {

    @Override
    public Merchant createMerchant(Merchant merchant) {
        MerchantDatabaseService service = new MerchantDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.Merchant.class, Merchant.class).
                map(service.create(
                        MapperUtils.getBasicMapper(Merchant.class, bdd.entity.Merchant.class).map(merchant, bdd.entity.Merchant.class)
                ), Merchant.class);
    }

    @Override
    public Merchant[] getListMerchant() {
        MerchantDatabaseService service = new MerchantDatabaseService();
        List<bdd.entity.Merchant> merchantDatabaseList = service.findAll();
        if(merchantDatabaseList != null){
            List<Merchant> merchantList = MapperUtils.getBasicMapper(bdd.entity.Merchant.class, Merchant.class).mapAsList(service.findAll(), Merchant.class);
            return merchantList.toArray(new Merchant[0]);
        } else {
            return new Merchant[0];
        }
    }

    @Override
    public Merchant getMerchant(Integer id) {
        MerchantDatabaseService service = new MerchantDatabaseService();
        bdd.entity.Merchant merchantDatabase = service.getById(id);
        if(merchantDatabase != null){
            return MapperUtils.getBasicMapper(bdd.entity.Merchant.class, Merchant.class).map(service.getById(id), Merchant.class);
        } else {
            return new Merchant();
        }
    }

    @Override
    public Merchant modifyMerchant(Merchant merchant) {
        MerchantDatabaseService service = new MerchantDatabaseService();
        return MapperUtils.getBasicMapper(bdd.entity.Merchant.class, Merchant.class).
                map(service.update(
                        MapperUtils.getBasicMapper(Merchant.class, bdd.entity.Merchant.class).map(merchant, bdd.entity.Merchant.class)
                ), Merchant.class);
    }

    @Override
    public void deleteMerchant(Merchant merchant) {
        MerchantDatabaseService service = new MerchantDatabaseService();
        service.delete(MapperUtils.getBasicMapper(Merchant.class, bdd.entity.Merchant.class).map(merchant, bdd.entity.Merchant.class));
    }
}
