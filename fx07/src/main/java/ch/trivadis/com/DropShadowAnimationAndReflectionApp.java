package ch.trivadis.com;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Created by amo on 24.08.16.
 */
public class DropShadowAnimationAndReflectionApp extends Application {

    @Override
    public void start(Stage stage) {

        initUI(stage);
    }

    private void initUI(Stage stage) {

        StackPane root = new StackPane();

        Rectangle rect = new Rectangle(0, 0, 100, 100);
        rect.setFill(Color.GREENYELLOW);

        DropShadow ds = new DropShadow(15, Color.DARKGREEN);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, // set start position at 0
                        new KeyValue(ds.offsetXProperty(), 0)
                ),
                new KeyFrame(new Duration(3000), // set end position at 3s
                        new KeyValue(ds.offsetXProperty(), 100)
                )
        );

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.setAutoReverse(true);

        Reflection ref = new Reflection();
        ref.setInput(ds);
        rect.setEffect(ref);

        root.getChildren().add(rect);

        Scene scene = new Scene(root, 250, 200, Color.WHITESMOKE);

        stage.setTitle("DropShadowApp");
        stage.setScene(scene);
        stage.show();
        timeline.play();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

