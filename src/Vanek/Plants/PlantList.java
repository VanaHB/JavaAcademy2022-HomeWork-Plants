package Vanek.Plants;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

public class PlantList{
    @SuppressWarnings("FieldMayBeFinal")
    private List<Plant> listOfPlants = new ArrayList<>();

    public void readFromFile(String file) throws DateTimeParseException , NumberFormatException , PlantException , FileNotFoundException {
        String [] pomocnePole;
        String separator = "\t";
        int radek = 0;
        List<String> seznamChyb = new ArrayList<>();
        try (Scanner sc = new Scanner(new BufferedReader(new FileReader(file)))) {
        //závorky hned za try znamenají "try with resources". Resource je objekt, který musí být uzavený, když program skončí. Try with resources to zajistí.
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
        /*
        PrintWriter přidává metody pro zápis jako println(), PrintReader neexistuje a možná nejbližší tomu by byl Scanner
        PrintWriter is buffered as BufferedWriter - PrintWriter creates BufferedWriter
        I was specifically talking about IOException, in that every other IO abstraction (Reader, Writer etc) declares that its methods throw IOException if something goes wrong - PrintWriter doesn't.
         */
        //try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) { //alternativa
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (Plant tmp : listOfPlants) {
                lineOfFile = tmp.getName()+separator+tmp.getNotes()+separator+tmp.getFrequencyOfWatering()+separator+tmp.getWatering()+separator+tmp.getPlanted();
                //writer.write(lineOfFile); //alternativa
                writer.println(lineOfFile);
            }
        } catch (IOException e) {
            throw new Exception("Problém se souborem: "+e.getLocalizedMessage());
        }
    }

    public Plant getPlantIndex(int index) {
        return listOfPlants.get(index);
    }

    public List<Plant> getPlantList() {
        //return this.listOfPlants;                 //vrací samotný list, který pak může někdo přímo editovat (třeba něco vyjmout
        return new ArrayList<>(listOfPlants);       //vrací kopii listu, její editací se list v této třídě nemění
    }

    public int getListOfPlantsSize() {
        return listOfPlants.size();
    }

    public void addPlant(Plant plant) {
        listOfPlants.add(plant);
    }

    //.remove(int index) může vyhodit nehlídanou výjimku, obecně programátor by měl IndexOutOfBoundsException předejít
    //public void removePlantIndex(int index) throws IndexOutOfBoundsException{ //nemusím definovat protože výjimka není hlídaná
    //případně můžu jí zachytit hned tady anebo výš při volání metody, když jí nezachytím tak program spadne
    public void removePlantIndex(int index){
        listOfPlants.remove(index);
    }

    public Set<Plant> plantedWhenever() {
        Set mnozina = new HashSet<>();
        listOfPlants.forEach(Plant -> mnozina.add(Plant));
        return mnozina;
    }

    public Set<Plant> plantedLastMonth() {
        Set mnozina = new HashSet<>();
        for (Plant tmp : listOfPlants) {
            if ( (tmp.getPlanted().getYear() == LocalDate.now().getYear()) ||  (tmp.getPlanted().getMonth() == LocalDate.now().getMonth()) ) {
                mnozina.add(tmp);
            }
        }
        return mnozina;
    }

    public void sortByName() { Collections.sort(listOfPlants); }

    //tady používám třídu PlantListSortByDate která implementuje Comparator
    public void sortByLastWatering() {
        Collections.sort(listOfPlants, new PlantListSortByDate());
    }
}
