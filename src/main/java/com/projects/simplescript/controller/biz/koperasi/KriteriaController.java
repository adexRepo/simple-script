package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.Storage;
import com.projects.simplescript.model.biz.Kriteria;
import com.projects.simplescript.model.biz.SubKriteria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/kriteria.fxml")
@RequiredArgsConstructor
public class KriteriaController implements Initializable {

  @FXML
    private Button btnAddKriteria;

    @FXML
    private Button btnAddSubKriteria;

    @FXML
    private Button btnDeleteKriteria;

    @FXML
    private Button btnDeleteSubKriteria;

    @FXML
    private Button btnEditKriteria;

    @FXML
    private Button btnEditSubKriteria;

    @FXML
    private TableView<Kriteria> tbKriteria;

    @FXML
    private TableColumn<Kriteria, String> idKriteria;

    @FXML
    private TableColumn<Kriteria, String> kriteriaName;

    @FXML
    private TableView<SubKriteria> tbSubKriteria;

    @FXML
    private TableColumn<SubKriteria, Integer> idSubKriteria;

    @FXML
    private TableColumn<SubKriteria, String> SubkriteriaName;

    @FXML
    private TableColumn<SubKriteria, Integer> nilai;

    @FXML
    private TableColumn<SubKriteria, Integer> kriteriaId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idKriteria.setCellValueFactory(new PropertyValueFactory<>("id"));
        kriteriaName.setCellValueFactory(new PropertyValueFactory<>("kriteriaName"));

        idSubKriteria.setCellValueFactory(new PropertyValueFactory<>("id"));
        SubkriteriaName.setCellValueFactory(new PropertyValueFactory<>("SubkriteriaName"));
        nilai.setCellValueFactory(new PropertyValueFactory<>("nilai"));
        kriteriaId.setCellValueFactory(new PropertyValueFactory<>("kriteriaId"));
        kriteriaId.setVisible(false);

        List<Kriteria> lstKriteria = Storage.getInstance().getKriteria();
        List<SubKriteria> lstSubKriteria = Storage.getInstance().getSubKriteria();
        tbKriteria.getItems().setAll(lstKriteria);
        tbSubKriteria.getItems().setAll(lstSubKriteria);
    }

    @FXML
    void onAddKriteria(ActionEvent event) {

    }

    @FXML
    void onAddSubKriteria(ActionEvent event) {

    }

    @FXML
    void onClickKriteriaTable(ActionEvent event) {

    }

    @FXML
    void onClickRowTable(MouseEvent event) {

    }

    @FXML
    void onClickSubKriteriaTable(ActionEvent event) {

    }

    @FXML
    void onDeleteKriteria(ActionEvent event) {

    }

    @FXML
    void onDeleteSubKriteria(ActionEvent event) {

    }

    @FXML
    void onEditKriteria(ActionEvent event) {

    }

    @FXML
    void onEditSubKriteria(ActionEvent event) {

    }

}
