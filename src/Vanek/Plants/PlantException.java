package Vanek.Plants;

import java.util.ArrayList;
import java.util.List;

public class PlantException extends Exception{
    @SuppressWarnings("FieldMayBeFinal")
    private List<String> errors;
    //private List<String> errors = new ArrayList<>();
    //Varování: INITIALIZER IS REDUNDANT, v PlantList třídě to nepíše
    //v PlantException tvořím proměnnou typu List<String>, do které přiřadím hotový - inicializovaný objekt
    //v PlantList tvořím proměnnou, které rovnou přiřadím prázdný objekt, se kterým následně pracuji

    public PlantException(String message , List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return new ArrayList<>(this.errors);
    }
}
