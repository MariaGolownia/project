package by.javatr.bicrent.controller;
import by.javatr.bicrent.action.validator.DataValidator;
import by.javatr.bicrent.action.validator.IncorrectDataException;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.entity.*;
import by.javatr.bicrent.entity.en_um.BicycleType;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.ServiceException;
import by.javatr.bicrent.service.bic_sort.BicycleComparator;
import by.javatr.bicrent.service.impl.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.sql.Blob;
import java.util.*;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import sun.applet.Main;

public class Runner {
    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {
        logger.info("Start runner");
//------------------------------------------------------------------------------------------------------------
// Тестирование ввода данных о user в БД
//        Dao dao = null;
//        User user1 = new User();
//        user1.setLogin("tera");
//        user1.setPassword("1111111111");
//        user1.setRole(Role.USER);
//        user1.setUserStatus(UserStatus.BLOCKED);
//        try {
//            dao = FactoryService.getInstance().get(DaoSql.UserDaoSql);
//            dao.create(user1);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения списка всех user из БД
//        try {
//           UserDao dao = (UserDao) FactoryService.getInstance().get(DaoSql.UserDaoSql);
//            List<User> userList = new ArrayList();
//            userList = dao.read();
//            for (User user:userList) {
//                System.out.print(user.toString());
//            }
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения  user по ID из БД
//        try {
//            Dao dao = FactoryService.getInstance().get(DaoSql.UserDaoSql);
//            User userTest = (User) dao.read(2);
//            System.out.print(userTest.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения user по login и password из БД
//        try {
//            Dao dao = FactoryService.getInstance().get(DaoSql.UserDaoSql);
//            UserDaoSql userDaoTest4 =
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }
//        UserDaoSql userDaoTest4 = new UserDaoSql();
//        try {
//            User userTest = userDaoTest4.read("swallow", "swallow111");
//            System.out.print(userTest.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
//        // Тестирование получения update user
//        UserDaoSql userDaoTest5 = new UserDaoSql();
//        try {
//            User userTestUpdate = new User();
//            userTestUpdate.setId(2);
//            userTestUpdate.setLogin("update");
//            userTestUpdate.setPassword("update");
//            userTestUpdate.setRole(Role.USER);
//            userTestUpdate.setUserStatus(UserStatus.ACTIVE);
//            userDaoTest5.update(userTestUpdate);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование удаления user
//        UserDaoSql userDaoTest5 = new UserDaoSql();
//        try {
//            userDaoTest5.delete(8);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получение Company by ID
//        CompanyDaoSql companyDao = new CompanyDaoSql();
//        try {
//            Company company = new Company();
//            company = companyDao.read(1);
//            System.out.print(company.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получение Company by AccountNumber
//        CompanyDaoSql companyDao = new CompanyDaoSql();
//        try {
//            Company company = new Company();
//            company = companyDao.readByAccountNumberOfPayer(192693197);
//            System.out.print(company.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }

//------------------------------------------------------------------------------------------------------------
        // Тестирование создания Company by AccountNumber
//        CompanyDaoSql companyDao = new CompanyDaoSql();
//        try {
//            Company company = new Company();
//            company.setName("Limited liability company Enjoyment");
//            company.setAccountNumberOfPayer(15978958);
//            Integer id = companyDao.create(company);
//            System.out.print(company.toString());
//            System.out.print(id.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование  удаления Company
//        CompanyDaoSql companyDao = new CompanyDaoSql();
//        try {
//            companyDao.delete(5);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения Location by ID
//        LocationDaoSql locationDao = new LocationDaoSql();
//        try {
//            Location location = new Location();
//            location = locationDao.read(1);
//            System.out.print(location.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения Location by Company_ID
//        LocationDaoSql locationDao = new LocationDaoSql();
//        List<Location> locationList = new ArrayList();
//        try {
//            locationList = locationDao.readByCompanyId(1);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//        for (Location location:locationList) {
//            System.out.print(location.toString());
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения Location by country
//        LocationDaoSql locationDao = new LocationDaoSql();
//        List<Location> locationList = new ArrayList();
//        try {
//            locationList = locationDao.readByCountry("Poland");
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//        for (Location location:locationList) {
//            System.out.print(location.toString());
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения Location by city
//        LocationDaoSql locationDao = new LocationDaoSql();
//        List<Location> locationList = new ArrayList();
//        try {
//            locationList = locationDao.readByCity("Poznan");
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//        for (Location location : locationList) {
//            System.out.print(location.toString());
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование создания Location
//        LocationDaoSql locationDao = new LocationDaoSql();
//        Location location = new Location();
//        location.setName("Basic");
//        Company company = new Company();
//        company.setName("Limited liability company Enjoyment");
//        company.setAccountNumberOfPayer(15978958);
//        location.setCompany(company);
//        location.setCountry(Country.BELARUS);
//        location.setCity(City.GRODNO);
//        location.setStreet("Yastnaja");
//        location.setHouse("2A");
//        location.setOffice("12");
//        location.setPhone(80297568900L);
//        location.setSecondPhone(80297568989L);
//        location.setPhoto("There will be a relative path to the photo!");
//        try {
//            locationDao.create(location);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование update Location
//        LocationDaoSql locationDao = new LocationDaoSql();
//        Location location = new Location();
//        location.setName("New");
//        location.setId(18);
//        Company company = new Company();
//        company.setName("Limited liability company Enjoyment");
//        company.setAccountNumberOfPayer(15978958);
//        location.setCompany(company);
//        location.setCountry(Country.BELARUS);
//        location.setCity(City.GRODNO);
//        location.setStreet("Yastnaja");
//        location.setHouse("2A");
//        location.setOffice("12");
//        location.setPhone(80297568900L);
//        location.setSecondPhone(80297568989L);
//        location.setPhoto("There will be a relative path to the photo!");
//        try {
//            locationDao.update(location);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование  удаления Location
//        LocationDaoSql locationDao = new LocationDaoSql();
//        try {
//            locationDao.delete(18);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения Price by ID
//        PriceDaoSql priceDao = new PriceDaoSql();
//        try {
//            Price price = new Price();
//            price = priceDao.read(1);
//            System.out.print(price.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения всех Price By Currency
//        PriceDaoSql priceDao = new PriceDaoSql();
//        try {
//            List<Price> priceList = new ArrayList<>();
//            Currency currencyTest = Currency.BYN;
//            priceList = priceDao.readByCurrency(currencyTest);
//            for (Price price:priceList) {
//            System.out.print(price.toString());
//        }
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование получения всех Price
//        PriceDaoSql priceDao = new PriceDaoSql();
//        try {
//            List<Price> priceList = new ArrayList<>();
//            priceList = priceDao.read();
//            for (Price price:priceList) {
//            System.out.print(price.toString());
//        }
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование создания Price
////        Price priceTest = new Price();
////        priceTest.setCurrency(Currency.BYN);
////        TimeUnit timeUnit = TimeUnit.HOUR;
////        priceTest.setUnitTime(timeUnit);
////        priceTest.setBookMaxTimeInMin(60);
////        priceTest.setRate(new BigDecimal(0.05));
////        priceTest.setBookRate(new BigDecimal(0));
////
////    PriceDaoSql priceDao = new PriceDaoSql();
////        try {
////            priceDao.create(priceTest);
////        } catch (PersistentException e) {
////            e.printStackTrace();
////        }
//
////------------------------------------------------------------------------------------------------------------
        // Тестирование update Price
//        Price priceUpdate = new Price();
//        priceUpdate.setId(1);
//        priceUpdate.setCurrency(Currency.BYN);
//        TimeUnit timeUnit = TimeUnit.HOUR;
//        priceUpdate.setUnitTime(timeUnit);
//        priceUpdate.setBookMaxTimeInMin(30);
//        priceUpdate.setRate(new BigDecimal(0.01));
//        priceUpdate.setBookRate(new BigDecimal(0));
//
//        PriceDaoSql priceDao = new PriceDaoSql();
//        try {
//            priceDao.update(priceUpdate);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
////------------------------------------------------------------------------------------------------------------
        // Тестирование delete Price
//        PriceDaoSql priceDaoImpl = new PriceDaoSql();
//        try {
//            priceDaoImpl.delete(5);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
////------------------------------------------------------------------------------------------------------------
        // Тестирование получения bicycle by ID
//        BicycleDaoSql bicycleDao = new BicycleDaoSql();
//        try {
//            Bicycle bicycle = new Bicycle();
//            bicycle = bicycleDao.read(1);
//            System.out.print(bicycle.toString());
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }}
//------------------------------------------------------------------------------------------------------------
        // Тестирование создания bicycle
//        BicycleDaoSql bicycleDao = new BicycleDaoSql();
//        try {
//            Bicycle bicycle = new Bicycle();
//            bicycle.setModel("BY-1456");
//            bicycle.setBicycleType(BicycleType.UNIVERSAL);
//            Short year = 2019;
//            bicycle.setProductionYear(year);
//            bicycle.setProducer("Belarus");
//            LocationDaoSql locationDao = new LocationDaoSql();
//            Location location = new Location();
//            location = locationDao.read(2);
//            bicycle.setCurrentLocation(location);
//            PriceDaoSql priceDao = new PriceDaoSql();
//            Price price = new Price();
//            price = priceDao.read(1);
//            bicycle.setPrice(price);
//            bicycle.setIfNotBooked(true);
//            bicycle.setIfFree(true);
//            Bicycle bicycleD = new Bicycle();
//            bicycleD = bicycleDao.read(1);
//            bicycle.setPhoto(bicycleD.getPhoto());
//            bicycleDao.create(bicycle);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование поиска bicycle по Location
//        BicycleDaoSql bicycleDao = new BicycleDaoSql();
//        try {
//            BicycleDaoSql bicycleDao1 = new BicycleDaoSql();
//            LocationDaoSql locationDao = new LocationDaoSql();
//            Location location = new Location();
//            location = locationDao.read(3);
//            List<Bicycle> bicycleList = new ArrayList<>();
//            bicycleList = bicycleDao.readByCurrentLocation(location);
//            for (Bicycle bicycle: bicycleList) {
//            System.out.print(bicycle.toString());
//        }
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование  update bicycle
//        BicycleDaoSql bicycleDao = new BicycleDaoSql();
//        try {
//            Bicycle bicycle = new Bicycle();
//            bicycle.setId(21);
//            bicycle.setModel("BY-99999");
//            bicycle.setBicycleType(BicycleType.UNIVERSAL);
//            Short year = 2018;
//            bicycle.setProductionYear(year);
//            bicycle.setProducer("Belarus");
//            LocationDaoSql locationDao = new LocationDaoSql();
//            Location location = new Location();
//            location = locationDao.read(2);
//            bicycle.setCurrentLocation(location);
//            PriceDaoSql priceDao = new PriceDaoSql();
//            Price price = new Price();
//            price = priceDao.read(1);
//            bicycle.setPrice(price);
//            bicycle.setIfNotBooked(true);
//            bicycle.setIfFree(true);
//            Bicycle bicycleD = new Bicycle();
//            bicycleD = bicycleDao.read(1);
//            bicycle.setPhoto(bicycleD.getPhoto());
//            BicycleDaoSql bicycleDao1 = new BicycleDaoSql();
//            bicycleDao1.update(bicycle);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
//------------------------------------------------------------------------------------------------------------
        // Тестирование delete Bicycle
//        BicycleDaoSql bicycleDao = new BicycleDaoSql();
//        try {
//            bicycleDao.delete(21);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
////------------------------------------------------------------------------------------------------------------
        // Тестирование создания userInfo
        //Справочно:
        // java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        //LocalDate localDate = sqlDate.toLocalDate();
//        UserInfoDaoSql userInfo = new UserInfoDaoSql();
//        try {
//            UserInfo userInfoTest = new UserInfo();
//            userInfoTest.setSurname("Svet");
//            userInfoTest.setName("Marina");
//            userInfoTest.setSecondName("Vladislavovna");
//            LocalDate dateOfBirth = LocalDate.of( 1979, 1, 10);
//            userInfoTest.setBirthDate(dateOfBirth);
//            LocalDate dateOfPassport = LocalDate.of( 1980, 2, 11);
//            userInfoTest.setPassportIssueDate(dateOfPassport);
//            userInfoTest.setCountry(Country.BELARUS);
//            userInfoTest.setPassportIssuingAuthority("Centralnoje ROVD");
//            userInfoTest.setPassportIdentificationNumber("71555HH001PK3");
//            userInfoTest.setPassportSerialNumber("MP1234589");
//            userInfoTest.setPassportAddressRegistration("Minsk, Esenina, 127-3");
//            userInfoTest.setPassportAddressResidence("Minsk, Esenina, 127-3");
//            userInfoTest.setPhoneNumber(80297885858L);
//            userInfoTest.setSecondPhoneNumber(80177855858L);
//            userInfoTest.setEmail("leto@yandex.ru");
//            userInfo.create(userInfoTest);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
        ////------------------------------------------------------------------------------------------------------------
        // Тестирование update userInfo
        //Справочно:
        // java.sql.Date sqlDate = java.sql.Date.valueOf( todayLocalDate );
        //LocalDate localDate = sqlDate.toLocalDate();
//        UserInfoDaoSql userInfo = new UserInfoDaoSql();
//        try {
//            UserInfo userInfoTest = new UserInfo();
//            userInfoTest.setId(6);
//            userInfoTest.setSurname("Svet");
//            userInfoTest.setName("Marina");
//            userInfoTest.setSecondName("Vladislavovna");
//            LocalDate dateOfBirth = LocalDate.of( 1977, 1, 10);
//            userInfoTest.setBirthDate(dateOfBirth);
//            LocalDate dateOfPassport = LocalDate.of( 1980, 2, 11);
//            userInfoTest.setPassportIssueDate(dateOfPassport);
//            userInfoTest.setCountry(Country.BELARUS);
//            userInfoTest.setPassportIssuingAuthority("Centralnoje ROVD");
//            userInfoTest.setPassportIdentificationNumber("71555HH001PK3");
//            userInfoTest.setPassportSerialNumber("MP1234589");
//            userInfoTest.setPassportAddressRegistration("Minsk, Esenina, 127-3");
//            userInfoTest.setPassportAddressResidence("Minsk, Esenina, 127-3");
//            userInfoTest.setPhoneNumber(80297885858L);
//            userInfoTest.setSecondPhoneNumber(80177855858L);
//            userInfoTest.setEmail("leto@yandex.ru");
//            userInfo.update(userInfoTest);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
////------------------------------------------------------------------------------------------------------------
// Тестирование update userInfo
//        OrderDaoSql rentDao = new OrderDaoSql();
//        try {
//            Rent rent = new Rent();
//            rent.setStartTime(LocalDateTime.now());
//            User user = new User();
//            UserDaoSql userDao = new UserDaoSql();
//            user = userDao.read(2);
//            Bicycle bicycle = new Bicycle();
//            BicycleDaoSql bicycleDao = new BicycleDaoSql();
//            bicycle = bicycleDao.read(2);
//            rent.setUser(user);
//            rent.setBicycle(bicycle);
//            rentDao.create(rent);
//        } catch (PersistentException e) {
//            e.printStackTrace();
//        }
////------------------------------------------------------------------------------------------------------------
//// Тестирование rent: readByUserId
////        OrderDaoSql rentDao = new OrderDaoSql();
////        try {
////            List<Rent>rentList = new ArrayList<>();
////            rentList = rentDao.readByUserId(2);
////            for (Rent rent : rentList) {
////            System.out.print(rent.toString());
////        }
////        } catch (PersistentException e){
////                e.printStackTrace();
////
////       }
        ////------------------------------------------------------------------------------------------------------------
// Тестирование rent: readByBicycle
//        OrderDaoSql rentDao = new OrderDaoSql();
//        try {
//            Rent rent = new Rent();
//            Bicycle bicycle = new Bicycle();
//            BicycleDaoSql bicycleDao = new BicycleDaoSql();
//            bicycle = bicycleDao.read(4);
//            rent = rentDao.readByBicycle(bicycle);
//            System.out.print(rent.toString());
//        } catch (PersistentException e){
//                e.printStackTrace();
//
//       }

        //       FactoryService factoryService = FactoryService.getInstance();
//        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//        User user = userService.findByLoginAndPassword("admin", "12345fhedfhe");
//        System.out.print(user.toString());

//        FactoryService factoryService = FactoryService.getInstance();
//        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//        User userByLogin = userService.findByLogin("admin");
//        if (userByLogin == null)
//            System.out.print("11111111111111111111111111111111");
//        else
//            System.out.print("22222222222222222222222222222222");
//        FactoryService factoryService = FactoryService.getInstance();
//        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//        User userNew = new User();
//        userNew.setLogin("Maria");
//        userNew.setPassword("kdvm;kwgvlwjg");
//        userNew.setUserStatus(UserStatus.ACTIVE);
//        userNew.setRole(Role.USER);
//        try {
//            userService.save(userNew);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//
//            String date = "11-12-2010";
//        LocalDate localDate = DateConverter.converterDateFromString(date);
//        System.out.print(localDate);
//        try {
//            FactoryService factoryService = FactoryService.getInstance();
//            UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//            User userNew = new User();
//            userNew.setLogin("Pol");
//            userNew.setPassword("123456kjbjhgjyg");
//            userNew.setUserStatus(UserStatus.ACTIVE);
//            userNew.setRole(Role.USER);
//
//            Integer id = userService.save(userNew);
//
//            UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
//            UserInfo userInfo = new UserInfo();
//            userInfo.setId(id);
//            userInfo.setSurname("Tio");
//            userInfo.setName("Pol");
//            userInfo.setSecondName("Pol");
//            userInfo.setCountry("Pol");
//            userInfo.setPassportIssueDate(DateConverter.converterDateFromString("25-02-2010"));
//            userInfo.setPassportSerialNumber("1");
//            userInfo.setPassportIdentificationNumber("2");
//            userInfo.setPassportIssuingAuthority("3");
//            userInfo.setPassportAddressRegistration("4");
//            userInfo.setPassportAddressResidence("5");
//            userInfo.setPhoneNumber(6L);
//            userInfo.setSecondPhoneNumber(7L);
//            userInfo.setEmail("8");
//
//            userInfoService.save(userInfo);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//---------------------------------------------
//        List<String> list = new ArrayList<>();
//        FactoryService factoryService = FactoryService.getInstance();
//            LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
//            list = locationService.findByCountryAndCity("Belarus", "Minsk");
//            for (String location : list)

//                System.out.println(location);
//        List<Location> list = new ArrayList<>();
//        FactoryService factoryService = FactoryService.getInstance();
//        LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
//        list = locationService.findByCountryAndCity(Country.valueOf("Belarus".toUpperCase()), City.valueOf("Minsk".toUpperCase()));

//        FactoryService factoryService = FactoryService.getInstance();
//        List<Bicycle> list = new ArrayList<>();
//            BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
//            list = bicycleService.findByFreeStatus(2, true);

//        FactoryService factoryService = FactoryService.getInstance();
//        Location location = new Location();
//        LocationServiceImpl locationService = factoryService.get(DaoSql.LocationDao);
//        location = locationService.findById(2);

//        FactoryService factoryService = FactoryService.getInstance();
//        UserInfoServiceImpl userInfoService = factoryService.get (DaoSql.UserInfoDao);
////        Integer number = 9;
////        UserInfo userInfo1 = userInfoService.findByIdentity(number);
////
//        //System.out.println(userInfo1.toString());
//        UserInfo userInfo2 = userInfoService.findByIdNumberPassport("7123456A001PB3" );
//        System.out.println(userInfo2.toString());

//        FactoryService factoryService = FactoryService.getInstance();
//        List<VirtualCard> virtualCards = new ArrayList<>();
//        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
//        virtualCards = virtualCardService.findByUserPassportId("71555LK001PL43");
//        for (VirtualCard card : virtualCards)
//            System.out.println(card);

//        FactoryService factoryService = FactoryService.getInstance();
//        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
//        virtualCardService.topUp(3, new BigDecimal(2.00));

//        Integer number;
//        FactoryService factoryService = FactoryService.getInstance();
//        OrderServiceImpl orderService = factoryService.get(DaoSql.OrderDao);
//        number = orderService.getLastOrderId();
//        System.out.println(number);

//        List<Bicycle> bicycles = new ArrayList<>();
//        FactoryService factoryService = FactoryService.getInstance();
//        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
//        bicycles = bicycleService.findByFreeStatus(2, true);
//        System.out.println(Arrays.toString(bicycles.toArray()));

//        FactoryService factoryService = FactoryService.getInstance();
//        Order order = new Order();
//        OrderServiceImpl orderService = factoryService.get(DaoSql.OrderDao);
//        order = orderService.read(37);
//        List<Integer>bicyclesIdList = order.getBicyclesId();
//        List<Bicycle>bicycleList = new ArrayList<>();
//        Bicycle bicycleItem;
//        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
//        for (Integer bic : bicyclesIdList) {
//            bicycleItem = bicycleService.findById(bic);
//            bicycleList.add(bicycleItem);
//            System.out.println(bicycleItem);
//        }
//
//        UserInfoServiceImpl userService = factoryService.get(DaoSql.UserInfoDao);
//        UserInfo userInfo = userService.findByIdentity(order.getUserId());
//        System.out.println(userInfo.getId());

  //----------------------------------------------------------
//        FactoryService factoryService = FactoryService.getInstance();
////        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
////        List<Bicycle>listBicycle = new ArrayList<>();
////        listBicycle = bicycleService.findByCurrentLocationWithPriceAndFreedom(5, true);
////System.out.println(Arrays.toString(listBicycle.toArray()));
//
//        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//        String userPassword = userService.getHashCodePassword("1");
//        System.out.println(userPassword);


//        FactoryService factoryService = FactoryService.getInstance();
//        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
//        List<Bicycle>listBicycle = new ArrayList<>();
//        listBicycle = bicycleService.findByCurrentLocationWithPriceAndFreedom(5, true);
//        for (int i = 0; i < listBicycle.size(); i++)
//            System.out.println(i + " " + listBicycle.get(i).getId()+ " " + listBicycle.get(i).getModel()
//            + " " + listBicycle.get(i).getRate() + " " + listBicycle.get(i).getProductionYear() +
//                     " " + listBicycle.get(i).getProducer());
//
//        System.out.println("----------------------------------------------------------------------------------");
//        listBicycle = bicycleService.sortBy(listBicycle, new BicycleComparator.SortBicycleByModel().comparatorSpecified());
//        for (int i = 0; i < listBicycle.size(); i++)
//            System.out.println(i + " " + listBicycle.get(i).getId()+ " " + listBicycle.get(i).getModel()
//                    + " " + listBicycle.get(i).getRate() + " " + listBicycle.get(i).getProductionYear() +
//                    " " + listBicycle.get(i).getProducer());
//
//
//        System.out.println("----------------------------------------------------------------------------------");
//        bicycleService.sortBy(listBicycle, new BicycleComparator.SortBicycleByYear().comparatorSpecified());
//        Collections.reverse(listBicycle);
//        for (int i = 0; i < listBicycle.size(); i++)
//            System.out.println(i + " " + listBicycle.get(i).getId()+ " " + listBicycle.get(i).getModel()
//                    + " " + listBicycle.get(i).getRate() + " " + listBicycle.get(i).getProductionYear() +
//                    " " + listBicycle.get(i).getProducer());

//        List<Enum> enumValues = Arrays.asList(CommandName.values());
//        String commandName ="authorization_page";
//
//        Boolean res=enumValues.contains(CommandName.valueOf(commandName.toUpperCase()));




//        String message_error = "";
//        String idPassportUser = "3041181A015PB4";
//        FactoryService factoryService = FactoryService.getInstance();
//        UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
//        UserInfo userInfoFromDB = new UserInfo();
//
//        try {
//            if (idPassportUser != null || !idPassportUser.equals("null") || !idPassportUser.trim().equals("")) {
//                userInfoFromDB = userInfoService.findByIdNumberPassport(idPassportUser);
//                System.out.println(userInfoFromDB.toString());
//                UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//                User userFromDB = userService.findByIdentity(userInfoFromDB.getId());
//                System.out.println(userFromDB.toString());
//
//            }
//            else {
//                message_error = "Select the user to edit!";
//
//            }
//        } catch (ServiceException e) {
//            message_error = e.getMessage();
//            e.printStackTrace();
//        }

//        try {
       FactoryService factoryService = FactoryService.getInstance();
//        UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
//        UserInfo userInfoFromDB = new UserInfo();
//
//
//
//        userInfoFromDB = userInfoService.findByIdNumberPassport("3041181A015PB4");
//
//        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//        Integer updateUserId = userInfoFromDB.getId();
//        User userFromDB = userService.findByIdentity(updateUserId);
//
//        User userNew = userFromDB;
//        UserInfo UserInfoNew = userInfoFromDB;
//        UserInfoNew.setSecondPhoneNumber(new Long("80297777777"));
//
//
//        userInfoService.update(UserInfoNew);
//        userService.update(userFromDB, userNew);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }


//        VirtualCard virtualCard = new VirtualCard();
//        virtualCard.setCurrency(Currency.valueOf("BYN"));
//        System.out.println(virtualCard.getCurrency().getName());

//        VirtualCard virtualCard1 = new VirtualCard();
//       virtualCard1.setCurrency(Currency.valueOf("BYN"));
//       System.out.println(virtualCard1.getCurrency().getName());
//
//        VirtualCard virtualCard2 = new VirtualCard();
//        virtualCard2.setCurrency(Currency.getCurrency("933"));
//        System.out.println(virtualCard2.getCurrency().getName());


//        System.out.println(Currency.getCodeOfCurrency("933"));
//        System.out.println(Currency.getCodeOfCurrency("BYN"));

//        UserInfoServiceImpl userInfoService = factoryService.get(DaoSql.UserInfoDao);
//        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//
//        try {
//            UserInfo userInfo = userInfoService.findByIdNumberPassport("3041181A015PB5");
//            System.out.println(userInfo.toString());
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        UserServiceImpl userService = factoryService.get(DaoSql.UserDao);
//        User user = userService.findByIdentity(12);
//        VirtualCardServiceImpl virtualCardService = factoryService.get(DaoSql.VirtualCardDao);
//        VirtualCard virtualCard = new VirtualCard();
//        virtualCard.setCurrency(Currency.EUR);
//        virtualCard.setBalance(BigDecimal.valueOf(5.00));
//        virtualCard.setName("night");
//        virtualCard.setUser(user);
//
//
//
//        try {
//            Integer id = virtualCardService.save(virtualCard);
//            System.out.println(id);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }

//        UserInfoServiceImpl userServiceInfo = factoryService.get(DaoSql.UserInfoDao);
//
//        try {
//            UserInfo userInfo = userServiceInfo.findByIdNumberPassport("3041181A015PB7");
//            //System.out.println(userInfo);
//        } catch (ServiceException e) {
//            e.printStackTrace();
//        }
//        BigDecimal bigDecimal1 = new BigDecimal(15.00);
//        System.out.println(bigDecimal1);


//        OrderServiceImpl userOrderService = factoryService.get(DaoSql.OrderDao);
//        List<Bicycle> bicycleList = new ArrayList<>();
//        Order order = userOrderService.read(33);
//        System.out.println(order.toString());
//
//        List<Integer> bicycleListId = userOrderService.getBicyclesIdByOrder(33);
//         System.out.println(Arrays.toString(bicycleListId.toArray()));
//
//        BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
//        for (int i = 0; i < bicycleListId.size(); i++) {
//            Bicycle bicycle = bicycleService.findById(bicycleListId.get(i));
//            bicycleList.add(bicycle);
//        }
//        System.out.println(Arrays.toString(bicycleList.toArray()));

        //Spring
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//                "applicationContext.xml"
//        );
//        VirtualCard virtualCard = context.getBean("virtualCardBean", VirtualCard.class);
//        System.out.println(virtualCard.toString());



    }
}


