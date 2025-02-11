module jhenriquedsm.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;

    opens jhenriquedsm.javafxjdbc.model.entities to javafx.graphics, javafx.fxml, javafx.base;
    opens jhenriquedsm.javafxjdbc to javafx.fxml;
    exports jhenriquedsm.javafxjdbc;
}