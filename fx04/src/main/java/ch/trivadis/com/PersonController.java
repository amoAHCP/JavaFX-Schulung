package ch.trivadis.com;

import ch.trivadis.com.model.Person;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static java.awt.SystemColor.text;

/**
 * Created by amo on 03.08.16.
 */
public class PersonController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField street;
    @FXML
    private TextField city;
    @FXML
    private TextField postalCode;

    private Person current;


    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // Listen for selection changes and show the person details when changed.
        personTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue,oldValue));


    }

    /**
     * Fills all text fields to show details about the person.
     * If the specified person is null, all text fields are cleared.
     *
     * @param person the person or null
     */
    private void showPersonDetails(Person person, Person old) {
        current = person;
        firstName.setText(person.firstNameProperty().getValue());
        lastName.setText(person.lastNameProperty().getValue());
        street.setText(person.streetProperty().getValue());
        city.setText(person.cityProperty().getValue());
        postalCode.setText(person.postalCodeProperty().getValue());
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param personData
     */
    public void setData(ObservableList<Person> personData) {

        // Add observable list data to the table
        personTable.setItems(personData);
        personData.addListener(new ListChangeListener<Person>() {
            @Override
            public void onChanged(Change<? extends Person> c) {
                System.out.println("change event: "+c.toString());
            }
        });

    }

    @FXML
    public void addPerson() {
        personTable.getItems().add(0,new Person("empty","empty"));
    }

    @FXML
    public void deletePerson() {
       if(current!=null)personTable.getItems().remove(current);
    }

}
