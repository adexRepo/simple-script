package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.Anggota;
import com.projects.simplescript.model.biz.DummyData;

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
@FxmlView("/ui/biz/anggota.fxml")
@RequiredArgsConstructor
public class AnggotaController implements Initializable {

    @FXML
    private Button btnAddNewAnggota;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRead;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<Anggota> tbAnggota;

    @FXML
    private TableColumn<Anggota, Integer> idAnggota;

    @FXML
    private TableColumn<Anggota, String> namaAnggota;

    @FXML
    private TableColumn<Anggota, String> alamatAnggota;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idAnggota.setCellValueFactory(new PropertyValueFactory<>("idAnggota"));
        namaAnggota.setCellValueFactory(new PropertyValueFactory<>("namaAnggota"));
        alamatAnggota.setCellValueFactory(new PropertyValueFactory<>("alamatAnggota"));

        List<Anggota> lstAnggota = DummyData.getDummyAnggota();
        tbAnggota.getItems().setAll(lstAnggota);
    }

    @FXML
    void onDeleteAnggota(ActionEvent event) {

    }

    @FXML
    void onReadAnggota(ActionEvent event) {

    }

    @FXML
    void onUpdateAnggota(ActionEvent event) {

    }

}
