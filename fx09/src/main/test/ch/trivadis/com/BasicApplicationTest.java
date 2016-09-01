package ch.trivadis.com;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.service.query.NodeQuery;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by amo on 25.08.16.
 */
public class BasicApplicationTest extends ApplicationTest {
    @Override
    public void start(Stage stage) {
        ContactApp app = new ContactApp();
        try {
            app.start(stage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkTable() {
        NodeQuery table = lookup("#personTable");
        TableView tableView =table.queryFirst();
        assertNotNull(tableView);
        ObservableList items = tableView.getItems();
        assertFalse(items.isEmpty());
        assertTrue(items.size()==9); // wir legen 9 Elemente initial in die Tabelle
        System.out.println(table.queryFirst());
    }
    @Test
    public void addElementAndCheckTable() {
        NodeQuery table = lookup("#personTable");
        TableView tableView =table.queryFirst();
        assertNotNull(tableView);
        ObservableList items = tableView.getItems();
        assertFalse(items.isEmpty());
        assertTrue(items.size()==9); // wir legen 9 Elemente initial in die Tabelle

        clickOn("#add");

        assertTrue(items.size()==10); // nun haben wir ein Element mehr in der Liste
    }
}
