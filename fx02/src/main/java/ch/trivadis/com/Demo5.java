package ch.trivadis.com;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Andy Moncsek on 20.07.16.
 */
public class Demo5 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/fxml_example5.fxml"));
        Parent root = loader.load();
        Object controller = loader.getController();
        if (controller instanceof FXMLController) {
            FXMLController cc = FXMLController.class.cast(controller);
            cc.changeLineColor();
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
