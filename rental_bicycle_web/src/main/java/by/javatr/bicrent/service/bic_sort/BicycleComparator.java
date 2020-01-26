package by.javatr.bicrent.service.bic_sort;

import java.util.Comparator;

public class BicycleComparator {

    public static class SortBicycleByModel extends SortBicycleSpecification {
        @Override
        public Comparator comparatorSpecified() {
            return new BicycleComparatorRealization.ByModel();
        }
    }

    public static class SortBicycleByRate extends SortBicycleSpecification {
        @Override
        public Comparator comparatorSpecified() {
            return new BicycleComparatorRealization.ByRate();
        }
    }


    public static class SortBicycleByCountry extends SortBicycleSpecification {
        @Override
        public Comparator comparatorSpecified() {
            return new BicycleComparatorRealization.ByCountry();
        }
    }

    public static class SortBicycleByYear extends SortBicycleSpecification {
        @Override
        public Comparator comparatorSpecified() {
            return new BicycleComparatorRealization.ByYear();
        }
    }

    public static class SortBicycleByID extends SortBicycleSpecification {
        @Override
        public Comparator comparatorSpecified() {
            return new BicycleComparatorRealization.ById();
        }
    }
}
