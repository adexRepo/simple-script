package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.Storage;
import com.projects.simplescript.model.biz.AlternativeNew;
import com.projects.simplescript.services.AhpService;

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

    private final AhpService service;

    @FXML
    private Button btnAddNewAnggota;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnRead;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableView<AlternativeNew> tbAnggota;

    @FXML
    private TableColumn<AlternativeNew, Integer> idAnggota;

    @FXML
    private TableColumn<AlternativeNew, String> namaAnggota;

    @FXML
    private TableColumn<AlternativeNew, String> alamatAnggota;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idAnggota.setCellValueFactory(new PropertyValueFactory<>("id"));
        namaAnggota.setCellValueFactory(new PropertyValueFactory<>("name"));
        alamatAnggota.setCellValueFactory(new PropertyValueFactory<>("alamat"));

        List<AlternativeNew> lstAnggota = service.getAllAlternatif();//Storage.getInstance().getAnggotas();
        tbAnggota.getItems().setAll(lstAnggota);
                        Storage.checkConfig();
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
