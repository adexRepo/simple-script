package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.Storage;
import com.projects.simplescript.model.biz.AlternativeNew;
import com.projects.simplescript.model.biz.SubKriteriaNew;
import com.projects.simplescript.services.AhpService;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/anggota.fxml")
@RequiredArgsConstructor
public class AnggotaController implements Initializable {

    private final AhpService service;

    @FXML
    private TableView<AlternativeNew> tbAnggota;

    @FXML
    private TableColumn<AlternativeNew, Integer> idAnggota;

    @FXML
    private TableColumn<AlternativeNew, String> namaAnggota;

    @FXML
    private TableColumn<AlternativeNew, String> alamatAnggota;

    @FXML
    private TextField alamat;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnUpdate;

    @FXML
    private Button btnSave;

    @FXML
    private ComboBox<String> cbK1;

    @FXML
    private ComboBox<String> cbK2;

    @FXML
    private ComboBox<String> cbK3;

    @FXML
    private ComboBox<String> cbK4;

    @FXML
    private ComboBox<String> cbK5;

    @FXML
    private ComboBox<String> cbK6;

    @FXML
    private TextField id;

    @FXML
    private TextField nama;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idAnggota.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaAnggota.setCellValueFactory(new PropertyValueFactory<>("name"));
        alamatAnggota.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        List<AlternativeNew> lstAnggota = service.getAllAlternatif();// Storage.getInstance().getAnggotas();
        tbAnggota.getItems().setAll(lstAnggota);
        Storage.checkConfig();

        cbK1.getItems().setAll(getListCb(service.getSubKriteriaByIdKriteria(1)));
        cbK2.getItems().setAll(getListCb(service.getSubKriteriaByIdKriteria(2)));
        cbK3.getItems().setAll(getListCb(service.getSubKriteriaByIdKriteria(3)));
        cbK4.getItems().setAll(getListCb(service.getSubKriteriaByIdKriteria(4)));
        cbK5.getItems().setAll(getListCb(service.getSubKriteriaByIdKriteria(5)));
        cbK6.getItems().setAll(getListCb(service.getSubKriteriaByIdKriteria(6)));

        cbK1.setDisable(true);
        cbK2.setDisable(true);
        cbK3.setDisable(true);
        cbK4.setDisable(true);
        cbK5.setDisable(true);
        cbK6.setDisable(true);
        id.setDisable(true);
        btnSave.setDisable(true);
    }

    private List<String> getListCb(List<SubKriteriaNew> subKriteria) {
        return subKriteria.stream().map(val -> val.getSubKriteriaName()).toList();
    }

    @FXML
    void onClickedTbl(MouseEvent event) {
        AlternativeNew alternatif = tbAnggota.getSelectionModel().getSelectedItem();
        if(alternatif != null){
            nama.setText(alternatif.getName());
            id.setText(alternatif.getId().toString());
            alamat.setText(alternatif.getAlamat());
        }
    }

    @FXML
    void onDelete(ActionEvent event) {
        if (!btnSave.isDisable()) {
            cbK1.setDisable(true);
            cbK2.setDisable(true);
            cbK3.setDisable(true);
            cbK4.setDisable(true);
            cbK5.setDisable(true);
            cbK6.setDisable(true);
            btnSave.setDisable(true);
        }

        AlternativeNew alternatif = tbAnggota.getSelectionModel().getSelectedItem();
        if (alternatif == null) {
            return;
        }

        List<AlternativeNew> newAnggota = service.deleteAlternatif(alternatif.getId());
        tbAnggota.getItems().setAll(newAnggota);
        reset();
    }

    @FXML
    void onTambah(ActionEvent event) {
        reset();
        cbK1.setDisable(false);
        cbK2.setDisable(false);
        cbK3.setDisable(false);
        cbK4.setDisable(false);
        cbK5.setDisable(false);
        cbK6.setDisable(false);
        btnSave.setDisable(false);
    }

    @FXML
    void onUpdate(ActionEvent event) {
        if (!btnSave.isDisable()) {
            reset();
        }

        AlternativeNew alternatif = tbAnggota.getSelectionModel().getSelectedItem();
        if (alternatif == null) {
            return;
        }
        List<AlternativeNew> newAnggota = service.updateDataAlternatif(alternatif.getId(), nama.getText(),
                alamat.getText());
        tbAnggota.getItems().setAll(newAnggota);
        reset();
    }

    private void reset() {
        cbK1.setDisable(true);
        cbK2.setDisable(true);
        cbK3.setDisable(true);
        cbK4.setDisable(true);
        cbK5.setDisable(true);
        cbK6.setDisable(true);
        id.setDisable(true);
        btnSave.setDisable(true);
        cbK1.setValue(null);
        cbK2.setValue(null);
        cbK3.setValue(null);
        cbK4.setValue(null);
        cbK5.setValue(null);
        cbK6.setValue(null);
        id.setText(null);
        alamat.setText(null);
        nama.setText(null);
    }

    @FXML
    void onSaveNew(ActionEvent event) {
        if (alamat== null || nama== null) {
            return;
        }

        String data1 = cbK1.getValue();
        String data2 = cbK2.getValue();
        String data3 = cbK3.getValue();
        String data4 = cbK4.getValue();
        String data5 = cbK5.getValue();
        String data6 = cbK6.getValue();
        if ((data1 == null || data2 == null || data3 == null || data4 == null || data5 == null
                || data6 == null)) {
            return;
        }

        List<AlternativeNew> newAnggota = service.saveDataAnggota(
                nama.getText(),
                alamat.getText(),
                data1,
                data2,
                data3,
                data4,
                data5,
                data6);
        tbAnggota.getItems().setAll(newAnggota);
        reset();
    }
}
