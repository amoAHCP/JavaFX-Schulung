package ch.trivadis.com;

import ch.trivadis.com.model.Person;
import javafx.application.Platform;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.*;

import java.util.stream.IntStream;


/**
 * Created by amo on 03.08.16.
 */
public class PersonController {

    @FXML
    private TextField filterField;
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

    @FXML
    private ProgressBar progress;

    @FXML
    private Label status;

    private ObservableList<Person> personData;
    private Person current;

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {

        final ProgressBar bar = new ProgressBar();
        new Thread(() -> IntStream.
                range(1,100000).
                forEach(val -> Platform.runLater(() -> bar.setProgress(val/1000000.0)))).
                start();
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
        if(old!=null){
            old.firstNameProperty().unbindBidirectional(firstName.textProperty());
            old.lastNameProperty().unbindBidirectional(lastName.textProperty());
            old.streetProperty().unbindBidirectional(street.textProperty());
            old.cityProperty().unbindBidirectional(city.textProperty());
            old.postalCodeProperty().unbindBidirectional(postalCode.textProperty());
        }

        if(person!=null) {
            firstName.textProperty().bindBidirectional(person.firstNameProperty());
            lastName.textProperty().bindBidirectional(person.lastNameProperty());
            street.textProperty().bindBidirectional(person.streetProperty());
            city.textProperty().bindBidirectional(person.cityProperty());
            postalCode.textProperty().bindBidirectional(person.postalCodeProperty());
        }
    }

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param personData
     */
    public void setData(ObservableList<Person> personData) {
        this.personData = personData;
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Person> filteredData = new FilteredList<>(personData, p -> true);

        // 2. Set the filter Predicate whenever the filter changes.
        filterField.textProperty().addListener((observable, oldValue, newValue) -> filteredData.setPredicate(person -> createFilterPredicate(newValue, person)));

        // 3. Wrap the FilteredList in a SortedList.
        SortedList<Person> sortedData = new SortedList<>(filteredData);

        // 4. Bind the SortedList comparator to the TableView comparator.
        sortedData.comparatorProperty().bind(personTable.comparatorProperty());

        // Add observable list data to the table
        personTable.setItems(sortedData);

    }

    private boolean createFilterPredicate(String newValue, Person person) {
        // If filter text is empty, display all persons.
        if (newValue == null || newValue.isEmpty()) {
            return true;
        }

        // Compare first name and last name of every person with filter text.
        String lowerCaseFilter = newValue.toLowerCase();

        if (person.firstNameProperty().getValue().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches first name.
        } else if (person.lastNameProperty().getValue().toLowerCase().contains(lowerCaseFilter)) {
            return true; // Filter matches last name.
        }
        return false; // Does not match.
    }

    @FXML
    public void addPerson() {
        personData.add(0,new Person("empty","empty"));
    }

    @FXML
    public void deletePerson() {
        if(current!=null)personData.remove(current);
    }

    @FXML
    public void savePerson() {
        if(current!=null){
            status.setText("Start");
            Task<Void> task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    int endExclusive = 100;
                    IntStream.range(0, endExclusive).forEach(element -> {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println("element: "+ element);
                        updateProgress(element, endExclusive);
                    });
                    return null;
                }
            };
            progress.progressProperty().unbind();
            progress.progressProperty().bind(task.progressProperty());
            task.setOnSucceeded(handler -> {
                status.setText("DONE");
            });
            new Thread(task).start();
        }
    }

}
