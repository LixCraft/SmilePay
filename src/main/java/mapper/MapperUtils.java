package mapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

public class MapperUtils {

    public static MapperFacade getBasicMapper(Class source, Class destination) {
        MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();

        mapperFactory.classMap(source.getClass(), destination.getClass());
        return mapperFactory.getMapperFacade();
    }
}
