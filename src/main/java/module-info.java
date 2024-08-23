module csc180.shawn.smith.cards {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens csc180.shawn.smith.pro100projectbeta to javafx.fxml;
    exports csc180.shawn.smith.pro100projectbeta;
    exports csc180.shawn.smith.pro100projectbeta.Model;
    opens csc180.shawn.smith.pro100projectbeta.Model to javafx.fxml;
}