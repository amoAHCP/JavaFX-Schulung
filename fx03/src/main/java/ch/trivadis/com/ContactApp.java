package ch.trivadis.com;

import ch.trivadis.com.model.Person;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Andy Moncsek on 20.07.16.
 */
public class ContactApp extends Application {

    /**
     * The data as an observable list of Persons.
     */
    private ObservableList<Person> personData = FXCollections.observableArrayList();

    public ContactApp() {
        // Add some sample data
        personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/PersonView.fxml"));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof PersonController) {
            PersonController cc = PersonController.class.cast(controller);
            cc.setData(personData);
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
