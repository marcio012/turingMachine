package sample.util;

import java.util.ArrayList;

public class LeitorArquivo {

    private ArrayList<String> logica;

    public ArrayList<String> LoadFile(String inputAlgoritmo) {
        logica = new ArrayList<String>();
        String[] arrayAlgoritmo = inputAlgoritmo.split("\n");
        String estadoInicial = arrayAlgoritmo[0];
        String estadoFinal = arrayAlgoritmo[1];
        logica.add(estadoInicial);
        logica.add(estadoFinal);
        for (int i = 2; i < arrayAlgoritmo.length; i++) {
            logica.add(arrayAlgoritmo[i]);
        }
        return logica;
    }
}
