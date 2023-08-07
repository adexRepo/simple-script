package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.AlternativeList;
import com.projects.simplescript.model.biz.DummyData;
import com.projects.simplescript.model.biz.Kodifikasi;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/alternatifList.fxml")
@RequiredArgsConstructor
public class AlternativeController implements Initializable {

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<Kodifikasi, String> col11;

    @FXML
    private TableColumn<Kodifikasi, Integer> col22;

    @FXML
    private TableColumn<Kodifikasi, Integer> col33;

    @FXML
    private TableColumn<Kodifikasi, Integer> col44;

    @FXML
    private TableColumn<Kodifikasi, Integer> col55;

    @FXML
    private TableColumn<Kodifikasi, Integer> col66;

    @FXML
    private TableColumn<Kodifikasi, Integer> col77;

    @FXML
    private TableColumn<AlternativeList, String> coll1;

    @FXML
    private TableColumn<AlternativeList, String> coll2;

    @FXML
    private TableColumn<AlternativeList, String> coll3;

    @FXML
    private TableColumn<AlternativeList, String> coll4;

    @FXML
    private TableColumn<AlternativeList, String> coll5;

    @FXML
    private TableColumn<AlternativeList, String> coll6;

    @FXML
    private TableColumn<AlternativeList, String> coll7;

    @FXML
    private TableView<Kodifikasi> tblKodifikasi;

    @FXML
    private TableView<AlternativeList> tblKuisioner;

    @FXML
    void onUpdate(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // tb kuisioner
        coll1.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        coll2.setCellValueFactory(new PropertyValueFactory<>("biayaPendidikan"));
        coll3.setCellValueFactory(new PropertyValueFactory<>("pembelianRumah"));
        coll4.setCellValueFactory(new PropertyValueFactory<>("perbaikanRumah"));
        coll5.setCellValueFactory(new PropertyValueFactory<>("bayarTepatWaktu"));
        coll6.setCellValueFactory(new PropertyValueFactory<>("catatanPinjaman"));
        coll7.setCellValueFactory(new PropertyValueFactory<>("tanggungan"));

        List<AlternativeList> data1 = DummyData.getDataKuisioner();
        tblKuisioner.getItems().setAll(data1);

        col11.setCellValueFactory(new PropertyValueFactory<>("anggota"));
        col22.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col33.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col44.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col55.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col66.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col77.setCellValueFactory(new PropertyValueFactory<>("k6"));

        List<Kodifikasi> data2 = DummyData.getDataKodifikasi();
        tblKodifikasi.getItems().setAll(data2);

    }

}
