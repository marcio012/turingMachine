package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.core.LogicMacine;
import sample.util.LeitorArquivo;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private ArrayList<String> algoritmo;

    @FXML
    private TextField inputFita;

    @FXML
    private TextArea inputAlgoritmo;

//    @FXML
//    private Button btnCarregarAlgoritmo;

    @FXML
    private TextField textFiledInputTest;

    @FXML
    private Button btnStringTest;

    @FXML
    private Button btnInicio;

    @FXML
    private Button btnInput;

    @FXML
    private AnchorPane anchorPaneId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

//    @FXML
//    public void onBtnCarregarAlgoritmo(){
//        String inputAlgoritmoText = inputAlgoritmo.getText();
//    }

//    @FXML
//    public void onInputAlfabetoAction() {
//        String alfabeto = inputAlgoritmo.getText();
//        System.out.println(alfabeto);
//    }

    @FXML
    public void onActionInit() {
        System.out.println("inciando");

        if (inputAlgoritmo.getText().length() == 0 || inputAlgoritmo.getText().isEmpty()) {
            System.out.println("Entre com o algoritmo");
        }

        if (textFiledInputTest.getText().length() == 0 || textFiledInputTest.getText().isEmpty()) {
            System.out.println("Entre com o a string de teste");
        }

        LeitorArquivo arquivo = new LeitorArquivo();
        algoritmo = arquivo.LoadFile(inputAlgoritmo.getText());
        System.out.println(algoritmo);

        String springTeste = textFiledInputTest.getText();
        LogicMacine logigMachine = new LogicMacine(algoritmo, springTeste);
        logigMachine.executar();

    }

    @FXML
    public void onBtnInputTest  () {}

}
