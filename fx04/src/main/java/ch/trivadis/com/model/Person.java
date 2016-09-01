package ch.trivadis.com.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by amo on 08.08.16.
 */
public class Person {
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final StringProperty postalCode;
    private final StringProperty city;


    public Person(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.street = new SimpleStringProperty("some street");
        this.postalCode = new SimpleStringProperty("1234");
        this.city = new SimpleStringProperty("some city");
    }


    public StringProperty firstNameProperty() {
        return firstName;
    }



    public StringProperty lastNameProperty() {
        return lastName;
    }


    public StringProperty streetProperty() {
        return street;
    }


    public StringProperty postalCodeProperty() {
        return postalCode;
    }



    public StringProperty cityProperty() {
        return city;
    }
}
