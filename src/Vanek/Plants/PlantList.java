package Vanek.Plants;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlantList {
    @SuppressWarnings("FieldMayBeFinal")
    private List<Plant> listOfPlants = new ArrayList<>();

    public void readFromFile(String file) throws DateTimeParseException , NumberFormatException , PlantException , FileNotFoundException {
        String [] pomocnePole;
        String separator = "\t";
        int radek = 0;
        List<String> seznamChyb = new ArrayList<>();
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
        //závorky hned za try znamenají "try with resources". Resource je objekt, který musí být uzavený, když program skončí.
            while (sc.hasNextLine()) {
                radek++;
                try {
                    pomocnePole = sc.nextLine().split(separator);
                    listOfPlants.add(new Plant(pomocnePole[0] , pomocnePole[1] , LocalDate.parse(pomocnePole[4]) , LocalDate.parse(pomocnePole[3]) , Integer.parseInt(pomocnePole[2])));
                } catch (DateTimeParseException e) {
                    seznamChyb.add("Řádek "+radek+" chyba zápisu v datumu: "+e.getLocalizedMessage());
                } catch (NumberFormatException e) {
                    seznamChyb.add("Řádek "+radek+" chyba zápisu ve frekvenci zavlažování: "+e.getLocalizedMessage());
                } catch (PlantException e) {
                    List<String> errors = e.getErrors();
                    for (String tmp : errors) {
                        seznamChyb.add("Řádek "+radek+" "+tmp);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            throw new FileNotFoundException("Problém se souborem: "+e.getLocalizedMessage());
        }
        if (seznamChyb.size() > 0) {
            throw new PlantException("Při načítání souboru vznikly chyby:", seznamChyb);
        }
    }

    public void writeToFile(String file) throws Exception{
        String lineOfFile;
        String separator = "\t";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Plant tmp : listOfPlants) {
                lineOfFile = tmp.getName()+separator+tmp.getNotes()+separator+tmp.getFrequencyOfWatering()+separator+tmp.getWatering()+separator+tmp.getPlanted()+"\n";
                writer.write(lineOfFile);
            }
        } catch (IOException e) {
            throw new Exception("Problém se souborem: "+e.getLocalizedMessage());
        }
    }

    public void addPlant(Plant plant) {
        listOfPlants.add(plant);
    }

    public Plant getPlantIndex(int index) {
        return listOfPlants.get(index);
    }

    public List<Plant> getPlantList() {
        //return this.listOfPlants;                 //vrací samotný list, který pak může někdo přímo editovat
        return new ArrayList<>(listOfPlants);       //vrací kopii listu
    }

    //.remove(int index) může vyhodit nehlídanou výjimku, obecně programátor by měl IndexOutOfBoundsException předejít
    //můžu jí zachytit hned tady anebo výš při volání metody, když jí nezachytím tak program spadne
    //public void removePlantIndex(int index) throws IndexOutOfBoundsException{ //nemusím definovat protože výjimka není hlídaná
    public void removePlantIndex(int index){
        listOfPlants.remove(index);
    }

    public int getListOfPlantsSize() {
        return listOfPlants.size();
    }

}
