package radiobutton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private Label msglbl;
    @FXML
    private ToggleGroup tgroup;
    @FXML
    private RadioButton morning;
    @FXML
    private RadioButton afternoon;
    @FXML
    private RadioButton evening;

    private boolean rbMorning = false, rbAfternoon = false, rbEvening = false;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (rbMorning) {
            msglbl.setText("Good Morning!");
        } else if (rbAfternoon) {
            msglbl.setText("Good Afternoon");
        } else if (rbEvening) {
            msglbl.setText("Good Evening");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tgroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            // instanceの直接比較
            if (newValue == morning) {
                rbMorning = true;
                rbAfternoon = false;
                rbEvening = false;
            } else if (newValue == afternoon) {
                rbAfternoon = true;
                rbMorning = false;
                rbEvening = false;
            } else if (newValue == evening) {
                rbEvening = true;
                rbMorning = false;
                rbAfternoon = false;
            }

            // userDataを使って比較する方法もある
        });
    }
}
