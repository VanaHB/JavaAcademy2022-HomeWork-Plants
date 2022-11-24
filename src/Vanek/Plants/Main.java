package Vanek.Plants;

import java.io.FileNotFoundException;
import java.time.LocalDate;

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
            seznam.addPlant(new Plant("neznámá", LocalDate.now()));
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

        System.out.println("konec programu");
    }
}
