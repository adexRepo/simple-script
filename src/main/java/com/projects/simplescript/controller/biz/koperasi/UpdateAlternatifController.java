package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.KriteriaNew;
import com.projects.simplescript.model.biz.SubKriteriaNew;
import com.projects.simplescript.services.AhpService;
import com.projects.simplescript.utils.ComponentUi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/updateAlternatif.fxml")
@RequiredArgsConstructor
public class UpdateAlternatifController implements Initializable {

    private final AhpService service;

    @FXML
    private ComboBox<String> cbNamaAnggota;

    @FXML
    private TableView<KriteriaNew> tbKriteria;

    @FXML
    private TableColumn<KriteriaNew, String> idKriteria;

    @FXML
    private TableColumn<KriteriaNew, String> kriteriaName;

    @FXML
    private ComboBox<String> cbSubKriteria;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnCancel;

    @FXML
    private void onTambahButtonClick() {
        String subItem = cbSubKriteria.getValue();
        String nama = cbNamaAnggota.getValue();
        KriteriaNew kriteria = tbKriteria.getSelectionModel().getSelectedItem();

        if(subItem.equals("") || nama.equals("") || kriteria == null){
            ComponentUi.showAlert(AlertType.WARNING, "Wajib Pilih", "Pilih terlebih dahulu nama dan sub kriteria");
        }

        if(kriteria != null){
            service.updateSubIdInAlternatifTableByName(nama,kriteria.getId(),subItem);
        }

        if(kriteria != null){
            ComponentUi.showAlert(AlertType.INFORMATION, "Update Data " + nama + " Success",
                    kriteria.getKriteriaName() + " menjadi " + subItem);
        }
    }

    @FXML
    private void onBatalButtonClick(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onSelectTable() {
        KriteriaNew selected = tbKriteria.getSelectionModel().getSelectedItem();
        if (selected != null) {
            List<SubKriteriaNew> subKriteria = service.getSubKriteriaByIdKriteria(selected.getId());
            subKriteria.removeIf(val -> !val.getKriteriaId().equals(selected.getId()));
            List<String> data = subKriteria.stream().map(val-> val.getSubKriteriaName()).toList();
            cbSubKriteria.getItems().setAll(data);
        } else {
            cbSubKriteria.getItems().clear();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        idKriteria.setCellValueFactory(new PropertyValueFactory<>("id"));
        kriteriaName.setCellValueFactory(new PropertyValueFactory<>("kriteriaName"));
        idKriteria.setVisible(false);

        List<KriteriaNew> kriterias = service.getAllKriteria();
        tbKriteria.getItems().setAll(kriterias);

        List<String> dataCb = service.getAllAlternatif().stream().map(val-> val.getName()).toList();
        cbNamaAnggota.getItems().setAll(dataCb);
    }

}
