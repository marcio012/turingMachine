package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
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

    @FXML
    private TextField textFiledInputTest;

    @FXML
    private Label cadeiaResultado;

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

    @FXML
    public void onActionClean() {
        inputFita.setText("");
        cadeiaResultado.setText("");
    }

    @FXML
    public void onActionInit() {

        if (inputAlgoritmo.getText().length() == 0 || inputAlgoritmo.getText().isEmpty()) {
            //TODO: colocar um alert na interface aqui
            System.out.println("Entre com o algoritmo");
            return;
        }

        if (textFiledInputTest.getText().length() == 0 || textFiledInputTest.getText().isEmpty()) {
            //TODO: colocar um alert na interface aqui
            System.out.println("Entre com o a string de teste");
            return;
        }

        LeitorArquivo arquivo = new LeitorArquivo();
        algoritmo = arquivo.LoadFile(inputAlgoritmo.getText());
        System.out.println(algoritmo);

        String springTeste = textFiledInputTest.getText();
        inputFita.setText(springTeste);
        LogicMacine logigMachine = new LogicMacine(algoritmo, springTeste);
        boolean statusMachine = logigMachine.executar();

        if (statusMachine == false) {
            cadeiaResultado.setTextFill(Color.web("#eb0202"));
            cadeiaResultado.setText(inputFita.getText());

        } else {
            cadeiaResultado.setTextFill(Color.web("#02eb16"));
            cadeiaResultado.setText(inputFita.getText());
        }




    }

}
