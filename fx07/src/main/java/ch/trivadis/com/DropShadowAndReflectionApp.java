package ch.trivadis.com;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by amo on 24.08.16.
 */
public class DropShadowAndReflectionApp extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        StackPane root = new StackPane();

        Rectangle rect = new Rectangle(0, 0, 100, 100);
        rect.setFill(Color.GREENYELLOW);

        DropShadow ds = new DropShadow(15, Color.DARKGREEN);

        Reflection ref = new Reflection();
        ref.setInput(ds);
        rect.setEffect(ref);

        root.getChildren().add(rect);

        Scene scene = new Scene(root, 250, 200, Color.WHITESMOKE);

        stage.setTitle("DropShadowApp");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

