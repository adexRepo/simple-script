package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.Anggota;
import com.projects.simplescript.model.biz.DummyData;
import com.projects.simplescript.model.biz.Kriteria;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/updateAlternatif.fxml")
@RequiredArgsConstructor
public class UpdateAlternatifController implements Initializable {

    @FXML
    private ComboBox<Anggota> cbNamaAnggota;

    @FXML
    private TableView<Kriteria> tbKriteria;

    @FXML
    private TableColumn<Kriteria, String> idKriteria;

    @FXML
    private TableColumn<Kriteria, String> kriteriaName;

    @FXML
    private ComboBox<String> cbPenilaian;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnBatal;

    @FXML
    private void onTambahButtonClick() {
        // Add your logic here
    }

    @FXML
    private void onBatalButtonClick() {
        // Add your logic here
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idKriteria.setCellValueFactory(new PropertyValueFactory<>("id"));
        kriteriaName.setCellValueFactory(new PropertyValueFactory<>("kriteriaName"));
        idKriteria.setVisible(false);

        List<Kriteria> lstKriteria = DummyData.getDummyKriteria();
        tbKriteria.getItems().setAll(lstKriteria);


        List<Anggota> anggota = DummyData.getDummyAnggota();
        cbNamaAnggota.getItems().setAll(anggota);
    }
}
