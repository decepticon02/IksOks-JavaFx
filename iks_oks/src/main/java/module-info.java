module com.example.iks_oks {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.iks_oks to javafx.fxml;
    exports com.example.iks_oks;
}