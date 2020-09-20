package sample.core;

import java.util.ArrayList;

public class LogicMacine {

    private String estadoInicial;
    private String estadoAceitacao;
    private ArrayList<String> algoritmo = new ArrayList<String>();
    private String cadeia;

    private String estado;

    private String valorEsquerdaDoCabecote = "";
    private String valorAtualDoCabecote = "";
    private String valorDireitaDoCabecote = "";

    private String estadoFinalDaFita;


    public LogicMacine(ArrayList<String> regras, String cadeia) {
        this.cadeia = cadeia;
        estadoInicial = regras.get(0);  // q0
        estadoAceitacao = regras.get(1);  // qAccept
        for(int i = 2; i < regras.size(); i++) {
            // pego as regras e passo para o array algoritmo
            this.algoritmo.add(regras.get(i));
        }
        // valor da posição inicial do cabeçote
        valorAtualDoCabecote = this.cadeia.substring(0,1);
        // valor da posição a esqueda do inicial cabeçote
        valorEsquerdaDoCabecote = "";
        // valor da posição a direita do inicial cabeçote
        valorDireitaDoCabecote = this.cadeia.substring(1);
    }

//    public boolean executar() {
//        boolean status ;
//        do {
//            status = checarEstado();
//        }while (!this.estado.equals("Final"));
//        System.out.println("CADEIIIA ACEITA");
//        System.out.println(valorEsquerdaDoCabecote+valorAtualDoCabecote+valorDireitaDoCabecote);
//        return status;
//    }

    public boolean executar() {
        return checarEstado();
    }

    public boolean checarEstado() {
        boolean aux = false;
//        if(!this.estado.equals("Final")) {
            System.out.println("Cadeia aceita");
            for (String linha : algoritmo) {
                if(checarRegra(linha)) {
                    aux = true;
                    executarPasso(linha);
                    break;
                }
            }
//        }
        return aux;
    }

    public boolean checarRegra(String linha) {

        /*
            Todo:
            Lembrando que as linhas aqui estão entrando no formato de
            q0,0,q1,0,> por isso o split se faz necessário
        */

        String array[] = new String[5];
        array = linha.split(",");
        String valorEstado = array[0];
        String valorCelula = array[1];

        return (valorCelula.equals(valorAtualDoCabecote) & valorEstado.equals(estadoInicial));
    }

    public void executarPasso(String linha) {
        String array[] = new String[5];
        array = linha.split(",");
        //	String valorEstado = array[0];
        //	String valorAtualDoCabecote = array[1];
        String novoEstado = array[2];
        String novoValor = array[3];
        String movimento = array[4];
        System.out.println("New value " + novoValor +  "  New State " + novoEstado + " movimento: " +movimento);
        this.valorAtualDoCabecote = array[3];
        moverFita(movimento);
        this.estadoInicial = novoEstado;
    }

    public void moverFita(String movimento) {
        if(movimento.equals("<")) {
            if (valorEsquerdaDoCabecote.length() == 0) {
                valorEsquerdaDoCabecote = "_";
            }
            valorDireitaDoCabecote = valorAtualDoCabecote + valorDireitaDoCabecote;
            valorAtualDoCabecote = valorEsquerdaDoCabecote.substring(valorEsquerdaDoCabecote.length() - 1);
            valorEsquerdaDoCabecote = valorEsquerdaDoCabecote.substring(0, valorEsquerdaDoCabecote.length() - 1);
        }else {
            if (valorDireitaDoCabecote.length() == 0) {
                valorDireitaDoCabecote = "_";
            }
            valorEsquerdaDoCabecote = valorEsquerdaDoCabecote + valorAtualDoCabecote;
            valorAtualDoCabecote = valorDireitaDoCabecote.substring(0,1);
            valorDireitaDoCabecote = valorDireitaDoCabecote.substring(1);
        }
    }

    public String atualizaFita() {
        if (this.cadeia.length() == 0)
            valorAtualDoCabecote = "_";
//        lblTapeLeft.setText(TapeLeft);
//        lblTapeCurrent.setText(TapeCurrent);
//        lblTapeRight.setText(TapeRight);
//        lblState.setText("State: " + State + "   ");
        return "Estado: " + estadoInicial;
    }
}
