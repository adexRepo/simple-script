package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.Storage;
import com.projects.simplescript.model.biz.Anggota;
import com.projects.simplescript.model.biz.Kodifikasi;
import com.projects.simplescript.model.biz.Kriteria;
import com.projects.simplescript.model.biz.SubKriteria;
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

    @FXML
    private ComboBox<String> cbNamaAnggota;

    @FXML
    private TableView<Kriteria> tbKriteria;

    @FXML
    private TableColumn<Kriteria, String> idKriteria;

    @FXML
    private TableColumn<Kriteria, String> kriteriaName;

    @FXML
    private ComboBox<String> cbSubKriteria;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnCancel;

    @FXML
    private void onTambahButtonClick() {
        String selectedItemSub = cbSubKriteria.getValue();
        String selectedItemAnggota = cbNamaAnggota.getValue();
        Kriteria selected = tbKriteria.getSelectionModel().getSelectedItem();

        if(selectedItemSub == null || selectedItemAnggota == null || selected == null){
            ComponentUi.showAlert(AlertType.WARNING, "Wajib Pilih", "Pilih terlebih dahulu nama dan sub kriteria");
        }

        Storage storage = Storage.getInstance();
        List<SubKriteria> subKriteria = new ArrayList<>(Storage.getInstance().getSubKriteria());
        SubKriteria subKriteriaDtl = subKriteria.stream()
                .filter(val -> val.getSubkriteriaName().equals(selectedItemSub))
                .findFirst()
                .orElse(null);

        List<Kodifikasi> kodifikasi = storage.getKodifikasi();
        for (Kodifikasi kod : kodifikasi) {
            if (kod.getAnggota().equals(selectedItemAnggota)) {
                if (subKriteriaDtl.getKriteriaId() == 1) {
                    kod.setK1(subKriteriaDtl.getId());
                } else if (subKriteriaDtl.getKriteriaId() == 2) {
                    kod.setK2(subKriteriaDtl.getId());
                } else if (subKriteriaDtl.getKriteriaId() == 3) {
                    kod.setK3(subKriteriaDtl.getId());
                } else if (subKriteriaDtl.getKriteriaId() == 4) {
                    kod.setK4(subKriteriaDtl.getId());
                } else if (subKriteriaDtl.getKriteriaId() == 5) {
                    kod.setK5(subKriteriaDtl.getId());
                } else if (subKriteriaDtl.getKriteriaId() == 6) {
                    kod.setK6(subKriteriaDtl.getId());
                }
            }
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
        Kriteria selected = tbKriteria.getSelectionModel().getSelectedItem();
        if (selected != null) {
            List<SubKriteria> subKriteria = new ArrayList<>(Storage.getInstance().getSubKriteria());
            subKriteria.removeIf(val -> !val.getKriteriaId().equals(selected.getId()));
            List<String> data = new ArrayList<>();
            for (SubKriteria item : subKriteria) {
                data.add(item.getSubkriteriaName());
            }
            cbSubKriteria.getItems().setAll(data);
        } else {
            cbSubKriteria.getItems().clear();
            ;
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Storage store = Storage.getInstance();

        idKriteria.setCellValueFactory(new PropertyValueFactory<>("id"));
        kriteriaName.setCellValueFactory(new PropertyValueFactory<>("kriteriaName"));
        idKriteria.setVisible(false);

        List<Kriteria> kriterias = store.getKriteria();
        tbKriteria.getItems().setAll(kriterias);

        List<Anggota> anggota = store.getAnggotas();
        List<String> dataCb = new ArrayList<>();
        for (Anggota data : anggota) {
            dataCb.add(data.getNamaAnggota());
        }
        cbNamaAnggota.getItems().setAll(dataCb);
    }

}
