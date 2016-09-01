package ch.trivadis.com;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by amo on 03.08.16.
 */
public class FXMLController {
    @FXML
    private Group group;
    private Line line;
    private Text text;

    public void initialize() {
        text = new Text("JavaFX 8");
        text.setFill(Color.RED);
        text.setFont(new Font(24));

        line = new Line(2, 8, 104, 8);
        line.setStroke(Color.BLUE);
        line.setStrokeWidth(4);
        group.getChildren().addAll(text, line);
    }

    public void changeLineColor() {
        line.setStroke(Color.RED);
    }

    @FXML
    protected void handleButtonAction(ActionEvent event) {
        text.setText("Update: JavaFX 8");
    }
}
