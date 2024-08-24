package csc180.shawn.smith.pro100projectbeta;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class ChangeScene {
    private static Scene scene;
    private static Stage stage;

    public static void changeScene(Event event, String strFXMLFileName) throws IOException {
        URL url = new File("src/main/java/csc180/shawn/smith/pro100projectbeta/"+
                strFXMLFileName).toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static <T> T changeSceneRC(Event event, String strFXMLFileName) throws IOException {
        URL url = new File("src/main/java/csc180/shawn/smith/pro100projectbeta/"+
                strFXMLFileName).toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return loader.getController();
    }

    public static <T> T changeSceneSimple(String strFXMLFileName) throws IOException, URISyntaxException {
        URL url = new File("src/main/java/csc180/shawn/smith/pro100projectbeta/"+
                strFXMLFileName).toURI().toURL();
        FXMLLoader loader = new FXMLLoader(url);
        Parent root = loader.load();
        Stage stage = HelloApplication.getPrimaryStage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

        return loader.getController();
    }

    public static class Player {
        private int hp;
        private String name;
        private int num;
        private String image;
        private boolean dead;
        private boolean turn = false;
        private int sheildCount = 0;

        public int getSheildCount() {
            return this.sheildCount;
        }

        public void setSheildCount(int sheildCount) {
            this.sheildCount = sheildCount;
        }

        public boolean isDead() {
            if (this.hp <= 0) {
                this.dead = true;
            }

            return this.dead;
        }

        public int getNum() {
            return this.num;
        }

        public String getName() {
            return this.name;
        }

        public void setHp(int hp) {
            this.hp = hp;
        }

        public int getHp() {
            return this.hp;
        }

        public String getImage() {
            return this.image;
        }

        public boolean isTurn() {
            return this.turn;
        }

        public void setTurn(boolean turn) {
            this.turn = turn;
        }

        public Player(int hp, String name, int num, String image, boolean dead) {
            this.hp = hp;
            this.name = name;
            this.num = num;
            this.image = image;
            this.dead = dead;
        }

        public Player(int hp, String name, int num, boolean dead) {
            this.hp = hp;
            this.name = name;
            this.num = num;
            this.dead = dead;
        }

        public String toString() {
            return "Player{hp=" + this.hp + ", name='" + this.name + "', num=" + this.num + ", image='" + this.image + "', dead=" + this.dead + "}";
        }

        public String toStringTemp() {
            return "Player{name='" + this.name + "', hp=" + this.hp + ", dead=" + this.dead + ", num=" + this.num + "}";
        }
    }
}
