package com.projects.simplescript.controller.biz.koperasi;

import com.projects.simplescript.model.biz.Alternative;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class AlternativeController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Label labelAlternatifList;

    @FXML
    private Button btnTambah;

    @FXML
    private TableView<Alternative> tbAlternatif;

    @FXML
    private TableColumn<Alternative, Integer> colNo;

    @FXML
    private TableColumn<Alternative, String> colNamaAnggota;

    @FXML
    private TableColumn<Alternative, String> colAlamat;

    @FXML
    private TableColumn<Alternative, String> colStatus;

    @FXML
    private Button btnEdit;

    @FXML
    private Button btnDelete;

    // YourDataType is the class representing your data for the TableView

    // Add any initialization logic here, if needed

    // Event handler for the "Tambah" button
    @FXML
    private void onTambahButtonClick() {
        // Add your logic here
    }

    // Event handler for the "Edit" button
    @FXML
    private void onEditButtonClick() {
        // Add your logic here
    }

    // Event handler for the "Delete" button
    @FXML
    private void onDeleteButtonClick() {
        // Add your logic here
    }
}
