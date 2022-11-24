package Vanek.Plants;

import java.util.List;

public class ErrorReader {

    public static void writeOnScreenException(String message) {
        System.out.println(Settings.ANSI_RED +message+Settings.ANSI_RESET);
    }

    //metoda při výpisu do konzole červeně podbarvuje chybové zprávy
    //je to moje alternativa System.err , který v konzoly řadí chyby zvlášť a ztrácí se přehlednost
    public static void writeOnScreenPlantException(String message , List<String> data) {
        System.out.println(Settings.ANSI_RED +message+Settings.ANSI_RESET);
        for (String tmp : data) {
            System.out.println(Settings.ANSI_RED +tmp+Settings.ANSI_RESET);
        }
    }

    public static void writeToLogFile(String message , List<String> data) {
        //tady by se mohlo doprogramovat zapisování do logu
    }

}
