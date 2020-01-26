package test.by.javatr.bicrent.service.impl;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.Bicycle;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.entity.en_um.BicycleType;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.impl.BicycleServiceImpl;
import by.javatr.bicrent.service.impl.LocationServiceImpl;
import org.testng.Assert;
import java.sql.Blob;

public class BicycleServiceImplTest {
    private FactoryService factoryService = FactoryService.getInstance();
    private BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);

    @org.testng.annotations.Test
    public void save() {
        Bicycle bicycle = new Bicycle();
        bicycle.setModel("BY-1456");
        bicycle.setBicycleType(BicycleType.UNIVERSAL);
        Short year = 2019;
        bicycle.setProductionYear(year);
        bicycle.setProducer("Belarus");
        LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
        Location location = locationService.findById(1);
        bicycle.setCurrentLocation(location);
        bicycle.setPriceId(1);
        bicycle.setIfNotBooked(true);
        bicycle.setIfFree(true);
        Blob photo = bicycleService.findById(5).getPhoto();
        bicycle.setPhoto(photo);
        bicycleService.save(bicycle);
        //  last id
        Bicycle bicycleLast = bicycleService.readLastBicycle();
        Assert.assertEquals(bicycle, bicycleLast);
    }

    @org.testng.annotations.Test
    // Тестирование получения bicycle by ID
    public void testFindById() {
        Bicycle bicycleFromDB = new Bicycle();
        bicycleFromDB = bicycleService.findById(1);
        Bicycle bicycleRequired = new Bicycle();
        bicycleRequired.setModel("Specialized S-Works");
        bicycleRequired.setBicycleType(BicycleType.HIGHLAND);
        Short year = 2019;
        bicycleRequired.setProductionYear(year);
        bicycleRequired.setProducer("USA");
        Assert.assertEquals(bicycleRequired, bicycleFromDB);
    }

    @org.testng.annotations.Test
    public void testChangeFreeStatus() {
        Integer ID_BICYCLE_UPDATE = 21;
        Bicycle bicycle = new Bicycle();
        bicycle.setId(ID_BICYCLE_UPDATE);
        bicycle.setModel("BY-99888");
        bicycle.setBicycleType(BicycleType.UNIVERSAL);
        Short year = 2017;
        bicycle.setProductionYear(year);
        bicycle.setProducer("Belarus");
        LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
        Location location = locationService.findById(2);
        bicycle.setCurrentLocation(location);
        bicycle.setPriceId(1);
        bicycle.setIfNotBooked(true);
        bicycle.setIfFree(true);
        Blob photo = bicycleService.findById(2).getPhoto();
        bicycle.setPhoto(photo);
        bicycleService.update(bicycle);
        Bicycle bicycleFromDB = bicycleService.findById(ID_BICYCLE_UPDATE);
        Assert.assertEquals(bicycle, bicycleFromDB);

    }

    @org.testng.annotations.Test
    public void testSortBy() {
    }

    @org.testng.annotations.Test
    public void testFindByCurrentLocation() {

    }

    @org.testng.annotations.Test
    public void testFindByCurrentLocationWithPriceAndFreedom() {
    }

    @org.testng.annotations.Test
    public void testFindByFreeStatus() {
    }

}