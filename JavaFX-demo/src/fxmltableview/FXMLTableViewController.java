package fxmltableview;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

/**
 *
 */
public class FXMLTableViewController implements Initializable {

    @FXML
    private TableView<Person> tableView;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField emailField;

    @FXML
    protected void addPerson(ActionEvent event) {
        ObservableList<Person> data = tableView.getItems();
        data.add(createPerson());
        clear();
    }

    private Person createPerson() {
        return new Person(firstNameField.getText(),
                          lastNameField.getText(),
                          emailField.getText());
    }

    private void clear() {
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Person> personData = tableView.getItems();
        personData.add(new Person("John", "Smith", "john@somewhere.com"));
        personData.add(new Person("Gladys", "Fisher", "gladys@aol.com"));
        personData.add(new Person("Lynda", "Forrester", "lynda@embarqmail.com"));
        personData.add(new Person("Tim", "Ross", "tim@msn.com"));
        personData.add(new Person("Jane", "Stratton", "jane@comcast.net"));
    }
}
