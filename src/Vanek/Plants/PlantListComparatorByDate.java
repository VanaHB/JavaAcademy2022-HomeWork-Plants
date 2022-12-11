package Vanek.Plants;

import java.util.Comparator;

public class PlantListComparatorByDate implements Comparator<Plant> {

    //Comparator uses compare method X Comparable uses compareTo method
    @Override
    public int compare(Plant o1, Plant o2) {
        return o1.getWatering().compareTo(o2.getWatering());
    }
}
