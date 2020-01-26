package by.javatr.bicrent.service.bic_sort;
import by.javatr.bicrent.entity.Bicycle;
import java.util.Comparator;
import java.util.List;

public class Sort {

    public static void sort(List<Bicycle> listBicycles, Comparator<Bicycle> c) {
        listBicycles.sort(c);
    }
}
