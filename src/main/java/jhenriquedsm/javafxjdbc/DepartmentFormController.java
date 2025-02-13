package jhenriquedsm.javafxjdbc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jhenriquedsm.javafxjdbc.db.DbException;
import jhenriquedsm.javafxjdbc.model.entities.Department;
import jhenriquedsm.javafxjdbc.model.services.DepartmentService;
import jhenriquedsm.javafxjdbc.util.Alerts;
import jhenriquedsm.javafxjdbc.util.Constraints;
import jhenriquedsm.javafxjdbc.util.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

    private Department department;

    private DepartmentService service;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button btSave;

    @FXML
    private Button btCancel;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void serDepartmentService(DepartmentService service) {
        this.service = service;
    }

    @FXML
    public void onBtSaveAction(ActionEvent actionEvent) {
        if (department == null){
            throw new IllegalStateException("Entity was null");
        }
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            department = getFormData();
            service.saveOrUpdate(department);
            Utils.currentStage(actionEvent).close();
        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private Department getFormData() {
        Department dep = new Department();
        dep.setId(Utils.tryParseToInt(txtId.getText()));
        dep.setName(txtName.getText());
        return dep;
    }

    @FXML
    public void onBtCancelAction(ActionEvent actionEvent) {
        Utils.currentStage(actionEvent).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(txtId);
        Constraints.setTextFieldMaxLength(txtName, 30);
    }

    public void updateFormData() {
        if (department == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(department.getId()));
        txtName.setText(department.getName());
    }
}