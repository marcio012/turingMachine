package sample.util;

import java.util.ArrayList;

public class LeitorArquivo {

    private ArrayList<String> logica;

    public ArrayList<String> LoadFile(String inputAlgoritmo) {
        logica = new ArrayList<String>();
        String[] arrayAlgoritmo = inputAlgoritmo.split("\n");
        for (int i = 0; i < arrayAlgoritmo.length; i++) {
            logica.add(arrayAlgoritmo[i]);
        }
        return logica;
    }
}
