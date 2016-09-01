package ch.trivadis.com;

import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.util.stream.IntStream;

/**
 * Created by amo on 16.08.16.
 */
public class HighLevelBindingApp {

    public static void main(String[] args) {
        // Area = width * height
        IntegerProperty width = new SimpleIntegerProperty(10);
        IntegerProperty height = new SimpleIntegerProperty(1);
        NumberBinding area = width.multiply(height);
        System.out.println(area.getValue());

        IntStream.range(1, 100).forEach(num -> {
            height.set(num);
            System.out.println(area.getValue());
        });

        StringProperty myText = new SimpleStringProperty("hallo world");
        StringProperty target = new SimpleStringProperty("---");
        System.out.println(target.getValue());

        // unidirectional binding
        target.bindBidirectional(myText);

        System.out.println(target.getValue());

        IntStream.range(1, 100).forEach(num -> {
            String currentVal = myText.getValue();
            myText.setValue(currentVal + num);
            System.out.println(target.getValue());
        });
    }

}
