package jhenriquedsm.javafxjdbc;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import jhenriquedsm.javafxjdbc.db.DbException;
import jhenriquedsm.javafxjdbc.listeners.DataChangeListener;
import jhenriquedsm.javafxjdbc.model.entities.Seller;
import jhenriquedsm.javafxjdbc.model.exceptions.ValidationException;
import jhenriquedsm.javafxjdbc.model.services.SellerService;
import jhenriquedsm.javafxjdbc.util.Alerts;
import jhenriquedsm.javafxjdbc.util.Constraints;
import jhenriquedsm.javafxjdbc.util.Utils;

import java.net.URL;
import java.util.*;

public class SellerFormController implements Initializable {

    private Seller seller;

    private SellerService service;

    private List<DataChangeListener> dataChangeListeners = new ArrayList<>();

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

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void serSellerService(SellerService service) {
        this.service = service;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListeners.add(listener);
    }

    @FXML
    public void onBtSaveAction(ActionEvent actionEvent) {
        if (seller == null){
            throw new IllegalStateException("Entity was null");
        }
        if (service == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            seller = getFormData();
            service.saveOrUpdate(seller);
            notifyDataChangeListeners();
            Utils.currentStage(actionEvent).close();
        } catch (ValidationException e) {
            setErrorMessages(e.getErrors());
        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListeners) {
            listener.onDataChanged();
        }
    }

    private Seller getFormData() {
        Seller dep = new Seller();
        ValidationException exception = new ValidationException("Validation error");
        dep.setId(Utils.tryParseToInt(txtId.getText()));
        if (txtName.getText() == null || txtName.getText().trim().equals("")) {
            exception.addError("name", "Field can't be empty");
        }
        dep.setName(txtName.getText());

        if (!exception.getErrors().isEmpty()) {
            throw exception;
        }
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
        if (seller == null) {
            throw new IllegalStateException("Entity was null");
        }
        txtId.setText(String.valueOf(seller.getId()));
        txtName.setText(seller.getName());
    }

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();
        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }
}