package fxmlobjectexample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

/**
 *
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    public ImageView imageView;
    @FXML
    public Button submitBtn;
    @FXML
    public Label outputLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private TextArea outputText;
    @FXML
    private TextField inputText;

    private final String[] messages;

    public FXMLDocumentController() {
        messages = new String[]{"Today is going to be a great day",
                "It might rain today", "You are awesome!",
                "Programming is fun, don't you agree?", "Have a great day!"};
    }

    @FXML
    private void printOutput(ActionEvent event) {
        Random rand = new Random();
        int num = rand.nextInt(5);
        outputText.setText(inputText.getText() + ", " + messages[num]);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
