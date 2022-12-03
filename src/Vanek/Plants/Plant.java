package Vanek.Plants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private LocalDate planted;
    private LocalDate watering;
    private int frequencyOfWatering;

    public Plant(String name , String notes , LocalDate planted , LocalDate watering , int frequencyOfWatering) throws PlantException{
        List<String> seznamChyb = new ArrayList<>();
        if (!isDateOk(planted, watering)) seznamChyb.add("Rostlinu nelze zalévat před zasazením - problém s datumy.");
        if (!isWateringOk(frequencyOfWatering)) seznamChyb.add("Rostlina se musí zalévat - záporná nebo nulová zálivka není povolena.");
        if (seznamChyb.size() > 0) throw new PlantException("Chyba při tvorbě objektu rostliny:" , seznamChyb);

        this.name = name;
        this.notes = notes;
        this.planted = planted;
        this.watering = watering;
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public Plant(String name , LocalDate planted , int frequencyOfWatering) throws PlantException{
        this(name, "" , planted , LocalDate.now() , frequencyOfWatering);
    }

    public Plant(String name , LocalDate planted) throws PlantException{
        this(name, "" , planted , LocalDate.now() , 7);
    }

    public String getName() {
        return name;
    }

    public String getNotes() {
        return notes;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public void setWatering(LocalDate watering) { this.watering = watering; }

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //Tyto pomocné metody jsou private protože se s nimi pracuje jen tady. V případě dědičnosti je už nechci vidět.
    private boolean isWateringOk(int frequencyOfWatering) {
        //if (frequencyOfWatering > 0) { return true; }
        //return false;
        return frequencyOfWatering > 0; //simple version
    }

    private boolean isDateOk(LocalDate planted , LocalDate watering) {
        return (planted.isBefore(watering) || planted.isEqual(watering));
    }

    public String getWateringInfo() {
        return getName()+" - poslední zálivka byla "+getWatering()+" a další by měla být "+getWatering().plusDays(getFrequencyOfWatering());
    }

    //metoda compareTo override defaultní metodu v Comparable<Plant> používanou Collections.sort(listOfPlants);
    //Rozhraní Comparable může mít jen jednu metodu compareTo a pro její změnu je nutný tento zásah do třídy
    //Pokud chci řadit podle víc krytérií tak je lepší implementovat interface Comparator
    @Override
    public int compareTo(Plant o) {
        return this.name.compareTo(o.getName());
    }

    //equals a hashCode se používá pro přidávání do množiny
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Plant plant = (Plant) o;
        return Objects.equals(planted, plant.planted);
    }

    @Override
    public int hashCode() { //equals nestačí, musí tu být i hashCode
        return Objects.hash(planted);
    }
}
