package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.Storage;
import com.projects.simplescript.model.biz.Calculation;
import com.projects.simplescript.model.biz.HasilPriority;
import com.projects.simplescript.model.biz.Kodifikasi;
import com.projects.simplescript.model.biz.MatrixBobot;
import com.projects.simplescript.services.AhpService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/hasilPerhitungan.fxml")
@RequiredArgsConstructor
public class HasilPerhitunganController implements Initializable {

    private final AhpService service;

    @FXML
    private TableView<HasilPriority> tblHasil;
    @FXML
    private TableColumn<HasilPriority, String> col111;
    @FXML
    private TableColumn<HasilPriority, Double> col222;

    @FXML
    private TableView<MatrixBobot> tblKodifikasiNormalisasi;
    @FXML
    private TableColumn<MatrixBobot, Double> col11;
    @FXML
    private TableColumn<MatrixBobot, Double> col22;
    @FXML
    private TableColumn<MatrixBobot, Double> col33;
    @FXML
    private TableColumn<MatrixBobot, Double> col44;
    @FXML
    private TableColumn<MatrixBobot, Double> col55;
    @FXML
    private TableColumn<MatrixBobot, Double> col66;
    @FXML
    private TableColumn<MatrixBobot, Double> col7;
    @FXML
    private TableColumn<MatrixBobot, Double> col77;
    @FXML
    private TableColumn<MatrixBobot, Double> col88;

    @FXML
    private TableView<Kodifikasi> tblKodifikasi;
    @FXML
    private TableColumn<Kodifikasi, String> col1;
    @FXML
    private TableColumn<Kodifikasi, Integer> col2;
    @FXML
    private TableColumn<Kodifikasi, Integer> col3;
    @FXML
    private TableColumn<Kodifikasi, Integer> col4;
    @FXML
    private TableColumn<Kodifikasi, Integer> col5;
    @FXML
    private TableColumn<Kodifikasi, Integer> col6;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col1.setCellValueFactory(new PropertyValueFactory<>("anggota"));
        col2.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col3.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col4.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col5.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col6.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col7.setCellValueFactory(new PropertyValueFactory<>("k6"));

        List<Kodifikasi> data1 = service.getAllAlternatifValueNilai();
        tblKodifikasi.getItems().setAll(data1);

        col11.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col22.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col33.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col44.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col55.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col66.setCellValueFactory(new PropertyValueFactory<>("k6"));
        col77.setCellValueFactory(new PropertyValueFactory<>("k7"));
        col88.setCellValueFactory(new PropertyValueFactory<>("bobot"));

        Integer countKriteria = service.getAllKriteria().size();
        List<MatrixBobot> data2 = Calculation.getDataHasilPerhitungan2(data1,countKriteria);
        tblKodifikasiNormalisasi.getItems().setAll(data2);

        col111.setCellValueFactory(new PropertyValueFactory<>("namaAlternative"));
        col222.setCellValueFactory(new PropertyValueFactory<>("hasil"));

        List<HasilPriority> data3 = Calculation.getDataHasilPerhitungan3("notAll",data1,countKriteria);
        tblHasil.getItems().setAll(data3);
                Storage.checkConfig();
    }

}
