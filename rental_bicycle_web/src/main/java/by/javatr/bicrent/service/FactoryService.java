package by.javatr.bicrent.service;
import by.javatr.bicrent.dao.mysql.*;
import by.javatr.bicrent.service.impl.*;
import org.apache.logging.log4j.LogManager;

final public class FactoryService {
    private static final org.apache.logging.log4j.Logger logger = LogManager.getLogger();

    private static FactoryService instance;

    private FactoryService() {
    }

    public static FactoryService getInstance(){
        if (instance == null) {
            synchronized (FactoryService.class) {
                instance  = new FactoryService();
            }
        }
        return instance;
    }

    public <Type extends Service> Type  get (DaoSql entityDao) {
        switch (entityDao) {
            case BicycleDao:
                return (Type) new BicycleServiceImpl();
            case CompanyDao:
            case LocationDao:
                    return (Type) new LocationServiceImpl();
            case PaymentDao:
                  //TODO
            case PriceDao:
                return (Type) new PriceServiceImpl();
            case OrderDao:
                return (Type) new OrderServiceImpl();
            case UserDao:
                return (Type) new UserServiceImpl();
            case UserInfoDao:
                return (Type) new UserInfoServiceImpl();
            case VirtualCardDao:
                return (Type) new VirtualCardServiceImpl();
            default:
                try {
                    throw new ServiceException("Check the existence of the desired entity! " + entityDao);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
        }
                return null;
    }
}
