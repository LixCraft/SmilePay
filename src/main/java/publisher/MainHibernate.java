package publisher;

import bdd.service.MerchantDatabaseService;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class MainHibernate {


    MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

    public static void main(String[] args){


        MerchantDatabaseService merchantDatabaseService = new MerchantDatabaseService();
        merchantDatabaseService.findAll();
        System.out.println(merchantDatabaseService.findAll());
    }
}
