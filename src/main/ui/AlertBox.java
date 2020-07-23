package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.*;

public class AlertBox {

    public static void display(String title, String message) {
        Stage window = new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250);
        window.setMinHeight(150);

        Label label = new Label();
        label.setText(message);
        label.setStyle("-fx-text-fill: #FFFFFF");

        Button closeButton = new Button("Close");
        closeButton.setStyle("-fx-background-color: #21af4b; -fx-text-fill: #FFFFFF; -fx-background-radius: 10");
        closeButton.setOnAction(e -> window.close());

        VBox layout = new VBox(10);
        layout.setStyle("-fx-background-color: #383838");
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
}
