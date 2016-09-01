package ch.trivadis.com;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by Andy Moncsek on 20.07.16.
 */
public class Demo1 extends Application {

    @Override
    public void start(Stage primaryStage) {

        Text text = new Text("JavaFX 8");
        text.setFill(Color.RED);
        text.setFont(new Font(24));

        Line line = new Line(2, 8, 104, 8);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(4);

        Group group = new Group();
        group.setEffect(new DropShadow());
        group.getChildren().addAll(text, line);

        BorderPane root = new BorderPane();
        root.setTop(new Label("Introducing..."));
        root.setCenter(group);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
