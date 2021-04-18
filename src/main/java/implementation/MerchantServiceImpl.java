package implementation;

import model.Merchant;
import service.MerchantService;

import javax.jws.WebService;
import java.util.ArrayList;
import java.util.Date;

@WebService(endpointInterface = "service.MerchantService")
public class MerchantServiceImpl implements MerchantService {

    @Override
    public String isRunning() {
        return "MerchantServiceImpl is running";
    }

    @Override
    public Merchant createMerchant(Merchant merchant) {
        return null;
    }

    @Override
    public Merchant[] getListMerchant() {
        Merchant merchant1 = new Merchant();
        merchant1.setId(1);
        merchant1.setBirthdate(new Date());
        merchant1.setCreateDate(new Date());
        merchant1.setLastName("LUC");
        merchant1.setName("FELIX");

        Merchant merchant2 = new Merchant();
        merchant2.setId(2);
        merchant2.setBirthdate(new Date());
        merchant2.setCreateDate(new Date());
        merchant2.setLastName("DATA");
        merchant2.setName("OREO");

        ArrayList<Merchant> merchantList = new ArrayList<>();
        merchantList.add(merchant1);
        merchantList.add(merchant2);
        return merchantList.toArray(new Merchant[0]);
    }

    @Override
    public Merchant modifyMerchant(Merchant merchant) {
        return null;
    }

    @Override
    public boolean deleteMerchant(Integer merchantId) {
        return false;
    }
}
