package ch.trivadis.com;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Created by amo on 24.08.16.
 */
public class BasicCSSWithStylesheet extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        StackPane root = new StackPane();

        String style = getClass().getResource("/style.css").toExternalForm();

        Button b = new Button("Hello");
        b.setId("my-button");

        root.getChildren().add(b);

        Scene scene = new Scene(root, 250, 200, Color.WHITESMOKE);

        stage.setTitle("Button / CSS app");
        scene.getStylesheets().add(style);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

