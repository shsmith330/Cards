package csc180.shawn.smith.pro100projectbeta;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage PrimaryStage;
    @Override
    public void start(Stage stage) throws IOException {
        PrimaryStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Characters.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Game Screen");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return PrimaryStage;
    }
    public static void main(String[] args) {
        launch();
    }

    public static class DefenseCards extends Enemy.Card {
        public DefenseCards(JSONObject jsObject) throws IOException, ParseException {
            super(jsObject);
        }

        public String toString() {
            return "DefenseCards{}" + super.toString();
        }
    }
}