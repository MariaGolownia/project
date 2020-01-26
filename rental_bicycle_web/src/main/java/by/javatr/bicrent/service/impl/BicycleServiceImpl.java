package by.javatr.bicrent.service.impl;

import by.javatr.bicrent.dao.BicycleDao;
import by.javatr.bicrent.dao.mysql.DaoException;
import by.javatr.bicrent.dao.mysql.DaoSql;
import by.javatr.bicrent.dao.mysql.FactoryDaoSql;
import by.javatr.bicrent.dao.mysql.LocationDaoSql;
import by.javatr.bicrent.entity.Bicycle;
import by.javatr.bicrent.entity.Location;
import by.javatr.bicrent.service.BicycleService;
import by.javatr.bicrent.service.FactoryService;
import by.javatr.bicrent.service.Service;
import by.javatr.bicrent.service.bic_sort.Sort;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BicycleServiceImpl extends Service implements BicycleService {
    @Override
    public List<Bicycle> findByCurrentLocation(Integer idLocation) {
        BicycleDao dao = null;
        List<Bicycle> bicycles = new ArrayList<>();
        try {
            try {
                dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
                bicycles = dao.readByCurrentLocation(idLocation);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return bicycles;
    }

    @Override
    public List<Bicycle> findByCurrentLocationWithPriceAndFreedom(Integer locationId, Boolean ifFree) {
        BicycleDao dao = null;
        List<Bicycle> bicycles = new ArrayList<>();
        try {
            try {
                dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
                bicycles = dao.readByCurrentLocationWithPriceAndFreedom(locationId, ifFree);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return bicycles;
    }

    @Override
    public List<Bicycle> findByFreeStatus(Integer idLocation, Boolean ifFree) {
        BicycleDao dao = null;
        List<Bicycle> bicycles = new ArrayList<>();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            bicycles = dao.readByCurrentLocationAndFreedom(idLocation, ifFree);
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bicycles;
    }

    @Override
    public Bicycle findById(Integer bicycleId) {
        BicycleDao dao = null;
        Bicycle bicycle = new Bicycle();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            try {
                bicycle = dao.read(bicycleId);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return bicycle;
    }

    @Override
    public List<Bicycle> findById(List<Integer> bicyclesId) {
        FactoryService factoryService = FactoryService.getInstance();
        List<Bicycle> bicycleList = new ArrayList<>();
        Bicycle bicycleItem;
        for (Integer bic : bicyclesId) {
            BicycleServiceImpl bicycleService = factoryService.get(DaoSql.BicycleDao);
            bicycleItem = bicycleService.findById(bic);
            bicycleList.add(bicycleItem);
        }
        return bicycleList;
    }

    @Override
    public List<Bicycle> findAll() {
        List<Bicycle> bicycleList = new ArrayList<>();
        try {
            BicycleDao dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            bicycleList = dao.readAll();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bicycleList;
    }

    @Override
    public Bicycle readLastBicycle() {
        Bicycle bicycle = new Bicycle();
        try {
            BicycleDao dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            bicycle = dao.readByLastId();
        } catch (DaoException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bicycle;
    }

    @Override
    public void changeFreeStatus(List<Integer> bicyclesId, Boolean freeStatus) {
        BicycleDao dao = null;
        Bicycle bicycle = new Bicycle();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            try {
                for (Integer id : bicyclesId) {
                    dao.changeFreeStatus(id, freeStatus);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Bicycle> sortBy(List<Bicycle> bicycles, Comparator<Bicycle> bicycleComparator) {
        Sort sort = new Sort();
        bicycles.sort(bicycleComparator);
        return bicycles;
    }

    @Override
    public Integer save(Bicycle bicycleNew) {
        Integer idBicycleNew = null;
        BicycleDao dao = null;
        Bicycle bicycle = new Bicycle();
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            try {
                idBicycleNew = dao.create(bicycleNew);

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
        return idBicycleNew;
    }

    @Override
    public void update(Bicycle bicycle) {
        BicycleDao dao = null;
        try {
            dao = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            try {
                 dao.update(bicycle);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void changeLocation(List<Bicycle> bicycleList, Integer locationId) {
        Location location = null;
        BicycleDao daoBic = null;
        try {
            daoBic = FactoryDaoSql.getInstance().get(DaoSql.BicycleDao);
            LocationDaoSql daoLoc =FactoryDaoSql.getInstance().get(DaoSql.LocationDao);
            location = daoLoc.read(locationId);
            if (location != null && daoBic!=null) {
                for (int i = 0; i < bicycleList.size(); i++) {
                    bicycleList.get(i).setCurrentLocation(location);
                    daoBic.update(bicycleList.get(i));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DaoException e) {
            e.printStackTrace();
        }


    }
}
