module jhenriquedsm.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;


    opens jhenriquedsm.javafxjdbc to javafx.fxml;
    exports jhenriquedsm.javafxjdbc;
}