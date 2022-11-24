package Vanek.Plants;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Plant {
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

    public void setFrequencyOfWatering(int frequencyOfWatering) {
        this.frequencyOfWatering = frequencyOfWatering;
    }

    //Tyto pomocné metody bych ale asi dával s modifikátorem private (nebo protected, pokud bych plánoval používat dědičnost).
    public boolean isWateringOk(int frequencyOfWatering) {
        //if (frequencyOfWatering > 0) { return true; }
        //return false;
        return frequencyOfWatering > 0; //simple version of if
    }

    public boolean isDateOk(LocalDate planted , LocalDate watering) {
        //if (planted.isBefore(watering) || planted.isEqual(watering)) { return true; }
        //return false;
        return planted.isBefore(watering) || planted.isEqual(watering); //simple version of if
    }

    public String getWateringInfo() {
        return getName()+" - poslední zálivka byla "+getWatering()+" a další by měla být "+getWatering().plusDays(getFrequencyOfWatering());
    }
}
