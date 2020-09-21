package sample.controller;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import sample.util.LeitorArquivo;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    public TextField fita_9E;
    @FXML
    public TextField fita_8E;
    @FXML
    public TextField fita_7E;
    @FXML
    public TextField fita_6E;
    @FXML
    public TextField fita_5E;
    @FXML
    public TextField fita_4E;
    @FXML
    public TextField fita_3E;
    @FXML
    public TextField fita_2E;
    @FXML
    public TextField fita_1E;

    @FXML
    public TextField fita_0;

    @FXML
    public TextField fita_1D;
    @FXML
    public TextField fita_2D;
    @FXML
    public TextField fita_3D;
    @FXML
    public TextField fita_4D;
    @FXML
    public TextField fita_5D;
    @FXML
    public TextField fita_6D;
    @FXML
    public TextField fita_7D;
    @FXML
    public TextField fita_8D;
    @FXML
    public TextField fita_9D;


    private String estadoInicial;
    private String estadoAceitacao;
    private final ArrayList<String> algoritmoRegras = new ArrayList<String>();

    private String cadeia;

    private String valorDeEntradaInputadaNaFita = "";
    private String valorEsquerdaDoCabecote = "";
    private String valorAtualDoCabecote = "0";
    private String valorDireitaDoCabecote = "";


    private boolean statusMachine = false;

    private ArrayList<String> algoritmo = new ArrayList<String>();

    @FXML
    private Label labelEstado;

    @FXML
    private TextArea inputAlgoritmo;

    @FXML
    private TextField textFiledInputTest;

    @FXML
    private Label cadeiaResultado;

    @FXML
    private Label cadeiaResultadoMensagem;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnPasso;

    @FXML
    private AnchorPane anchorPaneId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        fita_0.setText(valorAtualDoCabecote);
        cadeiaResultadoMensagem.setVisible(false);
    }

    @FXML
    public void onActionClean() {
        /*TODO: limpar os campos na lista*/
        cadeiaResultado.setText("");
        estadoInicial = "";
        estadoAceitacao = "";
        algoritmoRegras.clear();
        estadoInicial = "";
        cadeia = "";

        valorDeEntradaInputadaNaFita = "";
        valorEsquerdaDoCabecote = "";
        valorAtualDoCabecote = "0";
        valorDireitaDoCabecote = "";

        cadeiaResultadoMensagem.setVisible(false);


        fita_9E.setText("");
        fita_8E.setText("");
        fita_7E.setText("");
        fita_6E.setText("");
        fita_5E.setText("");
        fita_4E.setText("");
        fita_3E.setText("");
        fita_2E.setText("");
        fita_1E.setText("");

        fita_0.setText("");

        fita_1D.setText("");
        fita_2D.setText("");
        fita_3D.setText("");
        fita_4D.setText("");
        fita_5D.setText("");
        fita_6D.setText("");
        fita_7D.setText("");
        fita_8D.setText("");
        fita_9D.setText("");

    }

    @FXML
    public void onLoadMachine() {

        if (inputAlgoritmo.getText().length() == 0 || inputAlgoritmo.getText().isEmpty()) {
            //TODO: colocar um alert na interface aqui
            System.out.println("Entre com o algoritmo");
            efeitoFade(cadeiaResultado, 3000, 10, 0);
            cadeiaResultado.setTextFill(Color.web("#eb0202"));
            cadeiaResultado.setFont(Font.font(16));
            cadeiaResultado.setText("Máquina não esta carregada! Entre com o algoritmo");
            return;
        }

        if (textFiledInputTest.getText().length() == 0 || textFiledInputTest.getText().isEmpty()) {
            //TODO: colocar um alert na interface aqui
            System.out.println("Entre com o a string de teste");
            efeitoFade(cadeiaResultado, 3000, 10, 0);
            cadeiaResultado.setTextFill(Color.web("#eb0202"));
            cadeiaResultado.setFont(Font.font(16));
            cadeiaResultado.setText("Máquina não esta carregada! Entre com a sequencia de teste.");
            return;
        }

        LeitorArquivo arquivo = new LeitorArquivo();
        algoritmo = arquivo.LoadFile(inputAlgoritmo.getText());
        valorDeEntradaInputadaNaFita = textFiledInputTest.getText();

        // TODO:
        //  Colocar underscore no inicio da fita.


        System.out.println(algoritmo.toString());
        System.out.println(valorDeEntradaInputadaNaFita);

        // TODO:
        //  aqui somente carrega o algoritmo
        //  Colocar underscore no inicio da fita.

        /*Todo:
        *  seta os valores na fita */


        logicMachine(algoritmo, valorDeEntradaInputadaNaFita);

        if (!algoritmo.isEmpty() && !textFiledInputTest.getText().isEmpty()) {
            efeitoFade(cadeiaResultado, 6000, 10, 0);
            cadeiaResultado.setTextFill(Color.web("#02eb16"));
            cadeiaResultado.setFont(Font.font(22));
            cadeiaResultado.setText("Algoritmo carregado!");
        }

    }

    @FXML
    public void onInitMachine(ActionEvent actionEvent) {
        if (!algoritmo.isEmpty() || !valorDeEntradaInputadaNaFita.isEmpty()) {
            checarEstado();
        }
        else {
            efeitoFade(cadeiaResultado, 6000, 10, 0);
            cadeiaResultado.setTextFill(Color.web("#eb0202"));
            cadeiaResultado.setFont(Font.font(22));
            cadeiaResultado.setText("Máquina ou a cadeia não estão carregadas!");
        }

    }

    @FXML
    public void onInitPassoPasso(ActionEvent actionEvent) {
        System.out.println(algoritmo.toString());

        if (!algoritmo.isEmpty() || !valorDeEntradaInputadaNaFita.isEmpty()) {
            checarEstadoPasos();
        }
        else {
            efeitoFade(cadeiaResultado, 6000, 10, 0);
            cadeiaResultado.setTextFill(Color.web("#eb0202"));
            cadeiaResultado.setFont(Font.font(22));
            cadeiaResultado.setText("Máquina ou a cadeia não estão carregadas!");
        }


    }

    @FXML
    public void onActionEstado(ActionEvent actionEvent) {
        labelEstado.setText("");
    }

    private void logicMachine(ArrayList<String> regras, String sequenciaInput) {

        /*TODO: aqui so roda quando e feito o carregamento do algoritmo*/
        cadeia = sequenciaInput;
        estadoInicial = regras.get(0);  // q0
        estadoAceitacao = regras.get(1);  // qAccept
        for(int i = 2; i < regras.size(); i++) {
            // pego as regras e passo para o array algoritmo
            algoritmoRegras.add(regras.get(i));
        }
        // valor da posição inicial do cabeçote
        valorAtualDoCabecote = cadeia.substring(0,1);
        // valor da posição a esqueda do inicial cabeçote
        valorEsquerdaDoCabecote = "";
        // valor da posição a direita do inicial cabeçote
        valorDireitaDoCabecote = cadeia.substring(1);
    }

    private void checarEstado() {
        boolean aux = true;
        do
        {
            aux = checarEstadoPasos();
        } while (aux);

    }

    private boolean checarEstadoPasos() {

        if(estadoInicial.equals("FINAL")) {
            System.out.println("Cadeia aceita");
            cadeiaResultado.setTextFill(Color.web("#02eb16"));
            cadeiaResultado.setFont(Font.font(30));
            cadeiaResultado.setText(textFiledInputTest.getText());
            cadeiaResultadoMensagem.setVisible(true);
            cadeiaResultadoMensagem.setText("Cadeia Aceita");
            cadeiaResultadoMensagem.setStyle("-fx-background-color: #02eb16;");
            return false;

        } else {

            System.out.println(estadoInicial);
            for (String linha : algoritmoRegras) {
                if(checarRegra(linha)) {
                    executarPasso(linha);
                    atualizaFita();
                    return true;
                }
            }

            System.out.println("Cadeia rejeitada");

            cadeiaResultado.setTextFill(Color.web("#eb0202"));
            cadeiaResultado.setFont(Font.font(30));
            cadeiaResultado.setText(textFiledInputTest.getText());
            cadeiaResultadoMensagem.setVisible(true);
            cadeiaResultadoMensagem.setText("Cadeia Rejeitada");
            cadeiaResultadoMensagem.setStyle("-fx-background-color: #eb0202;");
            return false;
        }

    }

    private boolean checarRegra(String linha) {

        String array[] = new String[5];
        array = linha.split(",");
        String valorEstado = array[0];
        String valorCelula = array[1];

        return (valorCelula.equals(valorAtualDoCabecote) & valorEstado.equals(estadoInicial));
    }

    private void executarPasso(String linha) {
        String array[] = new String[5];
        array = linha.split(",");
        String linhaEstado = array[0]; // estou no estado -> q0
        String linhaValor = array[1]; //  leio -> 0
        String linhaNovoEstado = array[2]; // mudo o estado para -> q1
        String linhaNovoValor = array[3]; // gravo o valor de -> 0
        String linhaMovimento = array[4]; // ando para -> >

        System.out.println("Estou em " + linhaEstado +  " leio " + linhaValor + " vou para o estado " + linhaNovoEstado + " gravo " + linhaNovoValor + " ando para " + linhaMovimento);

        // Set the new value
        // o linhaNovoValor != "*"
        if (!linhaNovoValor.equals("_")) {
            valorAtualDoCabecote = linhaNovoValor;
        }

        // Move the tape or halt
        if (linhaMovimento.equals("<")) {
            moverFita("esquerda");
        } else if (linhaMovimento.equals(">")) {
            moverFita("direita");
        }

        // Set the new state
        if (linhaNovoEstado.equals(estadoAceitacao))
            estadoInicial = "FINAL";
        else
            estadoInicial = linhaNovoEstado;

    }

    private void moverFita(String movimento) {
        if(movimento.equals("esquerda")) {
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

    private void atualizaFita() {
        if (cadeia.length() == 0) {
            valorAtualDoCabecote = "_";
        }
        System.out.println("Valor a esquerda do Cabeçote ->> " + valorEsquerdaDoCabecote);
        System.out.println("Valor atual do Cabeçote ->> " + valorAtualDoCabecote);
        System.out.println("Valor a direita do Cabeçote ->> " + valorDireitaDoCabecote);

        fita_0.setText(valorAtualDoCabecote);
        char[] charsE = valorEsquerdaDoCabecote.toCharArray();
        char[] charsD = valorDireitaDoCabecote.toCharArray();

        switch (charsE.length) {
            case 0:
                fita_1E.setText("_");
                fita_2E.setText("_");
                fita_3E.setText("_");
                fita_4E.setText("_");
                fita_5E.setText("_");
                fita_6E.setText("_");
                fita_7E.setText("_");
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 1:
                fita_1E.setText(String.valueOf(charsE[0]));
                fita_2E.setText("_");
                fita_3E.setText("_");
                fita_4E.setText("_");
                fita_5E.setText("_");
                fita_6E.setText("_");
                fita_7E.setText("_");
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 2:
                fita_1E.setText(String.valueOf(charsE[1]));
                fita_2E.setText(String.valueOf(charsE[0]));
                fita_3E.setText("_");
                fita_4E.setText("_");
                fita_5E.setText("_");
                fita_6E.setText("_");
                fita_7E.setText("_");
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 3:
                fita_1E.setText(String.valueOf(charsE[2]));
                 fita_2E.setText(String.valueOf(charsE[1]));
                fita_3E.setText(String.valueOf(charsE[0]));
                fita_4E.setText("_");
                fita_5E.setText("_");
                fita_6E.setText("_");
                fita_7E.setText("_");
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 4:
                fita_1E.setText(String.valueOf(charsE[3]));
                fita_2E.setText(String.valueOf(charsE[2]));
                fita_3E.setText(String.valueOf(charsE[1]));
                fita_4E.setText(String.valueOf(charsE[0]));
                fita_5E.setText("_");
                fita_6E.setText("_");
                fita_7E.setText("_");
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 5:
                fita_1E.setText(String.valueOf(charsE[4]));
                fita_2E.setText(String.valueOf(charsE[3]));
                fita_3E.setText(String.valueOf(charsE[2]));
                fita_4E.setText(String.valueOf(charsE[1]));
                fita_5E.setText(String.valueOf(charsE[0]));
                fita_6E.setText("_");
                fita_7E.setText("_");
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 6:
                fita_1E.setText(String.valueOf(charsE[5]));
                fita_2E.setText(String.valueOf(charsE[4]));
                fita_3E.setText(String.valueOf(charsE[3]));
                fita_4E.setText(String.valueOf(charsE[2]));
                fita_5E.setText(String.valueOf(charsE[1]));
                fita_6E.setText(String.valueOf(charsE[0]));
                fita_7E.setText("_");
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 7:
                fita_1E.setText(String.valueOf(charsE[6]));
                fita_2E.setText(String.valueOf(charsE[5]));
                fita_3E.setText(String.valueOf(charsE[4]));
                fita_4E.setText(String.valueOf(charsE[3]));
                fita_5E.setText(String.valueOf(charsE[2]));
                fita_6E.setText(String.valueOf(charsE[1]));
                fita_7E.setText(String.valueOf(charsE[0]));
                fita_8E.setText("_");
                fita_9E.setText("_");
                break;

            case 8:
                fita_1E.setText(String.valueOf(charsE[7]));
                fita_2E.setText(String.valueOf(charsE[6]));
                fita_3E.setText(String.valueOf(charsE[5]));
                fita_4E.setText(String.valueOf(charsE[4]));
                fita_5E.setText(String.valueOf(charsE[3]));
                fita_6E.setText(String.valueOf(charsE[2]));
                fita_7E.setText(String.valueOf(charsE[1]));
                fita_8E.setText(String.valueOf(charsE[0]));
                fita_9E.setText("_");
                break;

            case 9:
                fita_1E.setText(String.valueOf(charsE[8]));
                fita_2E.setText(String.valueOf(charsE[7]));
                fita_3E.setText(String.valueOf(charsE[6]));
                fita_4E.setText(String.valueOf(charsE[5]));
                fita_5E.setText(String.valueOf(charsE[4]));
                fita_6E.setText(String.valueOf(charsE[3]));
                fita_7E.setText(String.valueOf(charsE[2]));
                fita_8E.setText(String.valueOf(charsE[1]));
                fita_9E.setText(String.valueOf(charsE[0]));
                break;

        }

        switch (charsD.length) {
            case 0:
                fita_1D.setText("_");
                fita_2D.setText("_");
                fita_3D.setText("_");
                fita_4D.setText("_");
                fita_5D.setText("_");
                fita_6D.setText("_");
                fita_7D.setText("_");
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 1:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText("_");
                fita_3D.setText("_");
                fita_4D.setText("_");
                fita_5D.setText("_");
                fita_6D.setText("_");
                fita_7D.setText("_");
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 2:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText("_");
                fita_4D.setText("_");
                fita_5D.setText("_");
                fita_6D.setText("_");
                fita_7D.setText("_");
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 3:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText(String.valueOf(charsD[2]));
                fita_4D.setText("_");
                fita_5D.setText("_");
                fita_6D.setText("_");
                fita_7D.setText("_");
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 4:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText(String.valueOf(charsD[2]));
                fita_4D.setText(String.valueOf(charsD[3]));
                fita_5D.setText("_");
                fita_6D.setText("_");
                fita_7D.setText("_");
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 5:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText(String.valueOf(charsD[2]));
                fita_4D.setText(String.valueOf(charsD[3]));
                fita_5D.setText(String.valueOf(charsD[4]));
                fita_6D.setText("_");
                fita_7D.setText("_");
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 6:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText(String.valueOf(charsD[2]));
                fita_4D.setText(String.valueOf(charsD[3]));
                fita_5D.setText(String.valueOf(charsD[4]));
                fita_6D.setText(String.valueOf(charsD[5]));
                fita_7D.setText("_");
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 7:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText(String.valueOf(charsD[2]));
                fita_4D.setText(String.valueOf(charsD[3]));
                fita_5D.setText(String.valueOf(charsD[4]));
                fita_6D.setText(String.valueOf(charsD[5]));
                fita_7D.setText(String.valueOf(charsD[6]));
                fita_8D.setText("_");
                fita_9D.setText("_");
                break;

            case 8:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText(String.valueOf(charsD[2]));
                fita_4D.setText(String.valueOf(charsD[3]));
                fita_5D.setText(String.valueOf(charsD[4]));
                fita_6D.setText(String.valueOf(charsD[5]));
                fita_7D.setText(String.valueOf(charsD[6]));
                fita_8D.setText(String.valueOf(charsD[7]));
                fita_9D.setText("_");
                break;

            case 9:
                fita_1D.setText(String.valueOf(charsD[0]));
                fita_2D.setText(String.valueOf(charsD[1]));
                fita_3D.setText(String.valueOf(charsD[2]));
                fita_4D.setText(String.valueOf(charsD[3]));
                fita_5D.setText(String.valueOf(charsD[4]));
                fita_6D.setText(String.valueOf(charsD[5]));
                fita_7D.setText(String.valueOf(charsD[6]));
                fita_8D.setText(String.valueOf(charsD[7]));
                fita_9D.setText(String.valueOf(charsD[8]));
                break;

        }

        labelEstado.setText("Estado: " + estadoInicial + ".");
    }

    private void efeitoFade(Node node, Integer duration, int from, int to) {
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(duration));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(from);
        fadeTransition.setToValue(to);

        fadeTransition.play();
    }


}
