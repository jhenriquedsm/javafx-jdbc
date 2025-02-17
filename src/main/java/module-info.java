module jhenriquedsm.javafxjdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jdk.jshell;

    opens jhenriquedsm.javafxjdbc.model.entities to javafx.graphics, javafx.fxml, javafx.base;
    opens jhenriquedsm.javafxjdbc to javafx.fxml;
    exports jhenriquedsm.javafxjdbc;
}