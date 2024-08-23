module csc180.shawn.smith.cards {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens csc180.shawn.smith.cards to javafx.fxml;
    exports csc180.shawn.smith.cards;
}