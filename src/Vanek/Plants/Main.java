package Vanek.Plants;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PlantList seznam = new PlantList();
        PlantList seznam2 = new PlantList();

        try {
            seznam.readFromFile(Settings.FILE1);
        } catch (PlantException e){
            ErrorReader.writeOnScreenPlantException(e.getLocalizedMessage() , e.getErrors());
        } catch (FileNotFoundException e) {
            ErrorReader.writeOnScreenException(e.getLocalizedMessage());
        }

        for (Plant tmp : seznam.getPlantList()) {
            System.out.println(tmp.getWateringInfo());
        }

        try {
            seznam.addPlant(new Plant("Kaktus", "zalývat by se ani moc nemusel", LocalDate.of(2022, 12, 24), LocalDate.of(2022, 12, 30), 365));
        }
        catch (PlantException e) {
            ErrorReader.writeOnScreenPlantException(e.getLocalizedMessage() , e.getErrors());
        }

        try {
            seznam.addPlant(new Plant("Neznámá", LocalDate.now()));
        }
        catch (PlantException e) {
            ErrorReader.writeOnScreenPlantException(e.getLocalizedMessage() , e.getErrors());
        }

        try {
            seznam.addPlant(new Plant("Neznámá 2", LocalDate.now()));
        }
        catch (PlantException e) {
            ErrorReader.writeOnScreenPlantException(e.getLocalizedMessage() , e.getErrors());
        }

        //ošetření nehlídané výjimky, nemuselo by se dělat
        try {
            seznam.removePlantIndex(seznam.getListOfPlantsSize()-1);
        } catch (IndexOutOfBoundsException e) {
            ErrorReader.writeOnScreenException(e.getLocalizedMessage());
        }

        try {
            seznam.writeToFile(Settings.FILE_NEW);
        } catch (Exception e) {
            ErrorReader.writeOnScreenException(e.getLocalizedMessage());
        }

        try {
            seznam2.readFromFile(Settings.FILE_NEW);
        } catch (PlantException e){
            ErrorReader.writeOnScreenPlantException(e.getLocalizedMessage() , e.getErrors());
        } catch (Exception e) {
            ErrorReader.writeOnScreenException(e.getLocalizedMessage());
        }

        System.out.println("-----------");
        for (Plant tmp : seznam2.getPlantList()) {
            System.out.println(tmp.getWateringInfo());
        }

//DOMÁCÍ ÚKOL ŠESTÉ LEKCE:
        /*
        System.out.println("--- výchozí pořadí:");
        seznam2.getPlantList().forEach(tmp -> System.out.println(tmp.getName()));
        System.out.println("--- seřazeno Comparable podle jména:");
        seznam2.sortByName();
        seznam2.getPlantList().forEach(tmp -> System.out.println(tmp.getName()+" "+tmp.getNotes()));
        System.out.println("--- seřazeno Comaprátorem podle datumu:");
        seznam2.sortByLastWatering();
        seznam2.getPlantList().forEach(tmp -> System.out.println(tmp.getName()+" "+tmp.getWatering()));
        System.out.println("--- kdy se sázely rostliny:");
        Set<Plant> posledniKdykoliv = seznam2.plantedWhenever();
        posledniKdykoliv.forEach(tmp -> System.out.println(tmp.getPlanted()+" "+tmp.getName()));
        System.out.println("--- kdy se sázely rostliny (jen poslední měsíc):");
        Set<Plant> posledniMesic = seznam2.plantedLastMonth();
        posledniMesic.forEach(tmp -> System.out.println(tmp.getPlanted()+" "+tmp.getName()));

*/

// test přidávání do množiny
        /*
        Set<String> mnozina = new HashSet<>();
        seznam2.getPlantList().forEach(Plant -> mnozina.add(Plant.getNotes())); //do mnozina se přidají ukazatele na prvky ze seznamu2
        //seznam2.removePlantIndex(2); //odsraněním prvku se seznamu v množitě zůstává
        seznam2.getPlantIndex(0).setNotes("nový popis");

        System.out.println("");

         */
    }
}
