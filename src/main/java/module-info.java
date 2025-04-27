module com.students.sri_lanka_marine_life_explorer {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens com.students.sri_lanka_marine_life_explorer to javafx.fxml;
    exports com.students.sri_lanka_marine_life_explorer;
}
