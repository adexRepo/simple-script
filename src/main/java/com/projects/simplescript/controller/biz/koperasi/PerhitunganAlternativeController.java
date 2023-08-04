package com.projects.simplescript.controller.biz.koperasi;

import com.projects.simplescript.model.biz.PenilaianAlternative;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class PerhitunganAlternativeController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelNamaAnggota;

    @FXML
    private ComboBox<String> cbNamaAnggota;

    @FXML
    private Label labelPenilaian;

    @FXML
    private TableView<PenilaianAlternative> tableView;

    @FXML
    private TableColumn<PenilaianAlternative, String> tbPenilaian;

    @FXML
    private ComboBox<String> cbPenilaian;

    @FXML
    private Button btnTambah;

    @FXML
    private Button btnBatal;

    // Add any initialization logic here, if needed

    // Event handler for the "Tambah" button
    @FXML
    private void onTambahButtonClick() {
        // Add your logic here
    }

    // Event handler for the "Batal" button
    @FXML
    private void onBatalButtonClick() {
        // Add your logic here
    }
}
