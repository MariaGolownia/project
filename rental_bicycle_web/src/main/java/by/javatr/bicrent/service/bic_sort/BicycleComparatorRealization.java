package by.javatr.bicrent.service.bic_sort;

import by.javatr.bicrent.entity.Bicycle;

import java.math.BigDecimal;
import java.util.Comparator;

public class BicycleComparatorRealization {

    public static class ByModel implements Comparator<Bicycle> {
        @Override
        public int compare(Bicycle b1, Bicycle b2) {
            String model1 = b1.getModel();
            String model2 = b2.getModel();
            int res = String.CASE_INSENSITIVE_ORDER.compare(model1, model2);
            return (res != 0) ? res : model1.compareTo(model2);
        }

    }

    public static class ByRate implements Comparator<Bicycle> {
        @Override
        public int compare(Bicycle b1, Bicycle b2) {
            BigDecimal cost1 = b1.getRate();
            BigDecimal cost2 = b2.getRate();
            int res = cost1.compareTo(cost2);
            return res;
        }
    }


    public static class ByYear implements Comparator<Bicycle> {
        @Override
        public int compare(Bicycle b1, Bicycle b2) {
            Short year1 = b1.getProductionYear();
            Short year2 = b2.getProductionYear();
            int res = year1.compareTo(year2);
            return res;
        }
    }

    public static class ByCountry implements Comparator<Bicycle> {
        @Override
        public int compare(Bicycle b1, Bicycle b2) {
            String country1 = b1.getProducer();
            String country2 = b2.getProducer();
            int res = String.CASE_INSENSITIVE_ORDER.compare(country1, country2);
            return res;
        }
    }

    public static class ById implements Comparator<Bicycle> {
        @Override
        public int compare(Bicycle b1, Bicycle b2) {
            Integer id1 = b1.getId();
            Integer id2 = b2.getId();
            int res = id1.compareTo(id2);
            return res;
        }
    }

}
