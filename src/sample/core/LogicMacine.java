package sample.core;

import java.util.ArrayList;

public class LogicMacine {

    private String estadoAtual;
    private String estadoAceitacao;
    private ArrayList<String> algoritmo;
    private String valorAtual = "";
    private String valorDireita = "";
    private String valorEsquerda = "";

    public LogicMacine(ArrayList<String> regras, String cadeia) {
        estadoAtual = regras.get(0);
        estadoAceitacao = regras.get(1);
        algoritmo = new ArrayList<String>();
        for(int i = 2; i < regras.size(); i++) {
            algoritmo.add(regras.get(i));
        }
        valorAtual = cadeia.substring(0,1);
        valorEsquerda = "";
        valorDireita = cadeia.substring(1);
    }

    public void executar() {
        do{
            checarEstado();

        }while(!estadoAtual.equals(estadoAceitacao));
        System.out.println("CADEIIIA ACEITA");
        System.out.println(valorEsquerda+valorAtual+valorDireita);
    }

    public void checarEstado() {
        boolean aux = false;
        if(estadoAtual.equals(estadoAceitacao)) {
            System.out.println("Cadeia aceita");
            //ACEITO, mostrar no console
        }else {
            for (String linha : algoritmo) {
                if(checarRegra(linha)) {
                    aux = true;
                    executarPasso(linha);
                    break;
                }
            }
            //pensar aquii!!
            if(!aux) {
                System.out.println("cadeiia não aceiita");
                System.out.println(valorEsquerda+valorAtual+valorDireita);
            }
        }
    }

    public boolean checarRegra(String linha) {
        String array[] = new String[5];
        array = linha.split(",");
        String valorEstado = array[0];
        String valorCelula = array[1];
        if(valorCelula.equals(valorAtual) && valorEstado.equals(estadoAtual)) {
            return true;
        }
        return false;
    }

    public void executarPasso(String linha) {
        String array[] = new String[5];
        array = linha.split(",");
        //	String valorEstado = array[0];
        //	String valorAtual = array[1];
        String novoEstado = array[2];
        String novoValor = array[3];
        String movimento = array[4];
        System.out.println("New value " + novoValor +  "  New State " + novoEstado + " movimento: " +movimento);
        this.valorAtual = array[3];
        moverFita(movimento);
        this.estadoAtual = novoEstado;
    }

    public void moverFita(String movimento) {
        if(movimento.equals("<")) {
            if (valorEsquerda.length() == 0) {
                valorEsquerda = "_";
            }
            valorDireita = valorAtual + valorDireita;
            valorAtual = valorEsquerda.substring(valorEsquerda.length() - 1);
            valorEsquerda = valorEsquerda.substring(0, valorEsquerda.length() - 1);
        }else {
            if (valorDireita.length() == 0) {
                valorDireita = "_";
            }
            valorEsquerda = valorEsquerda + valorAtual;
            valorAtual = valorDireita.substring(0,1);
            valorDireita = valorDireita.substring(1);
        }
    }
}
