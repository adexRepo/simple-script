package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.KriteriaNew;
import com.projects.simplescript.model.biz.SubKriteriaNew;
import com.projects.simplescript.services.AhpService;

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

  private final AhpService service;

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
    private TableView<KriteriaNew> tbKriteria;

    @FXML
    private TableColumn<KriteriaNew, String> idKriteria;

    @FXML
    private TableColumn<KriteriaNew, String> kriteriaName;

    @FXML
    private TableView<SubKriteriaNew> tbSubKriteria;

    @FXML
    private TableColumn<SubKriteriaNew, Integer> idSubKriteria;

    @FXML
    private TableColumn<SubKriteriaNew, String> SubkriteriaName;

    @FXML
    private TableColumn<SubKriteriaNew, Integer> nilai;

    @FXML
    private TableColumn<SubKriteriaNew, Integer> kriteriaId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idKriteria.setCellValueFactory(new PropertyValueFactory<>("id"));
        kriteriaName.setCellValueFactory(new PropertyValueFactory<>("kriteriaName"));

        idSubKriteria.setCellValueFactory(new PropertyValueFactory<>("id"));
        SubkriteriaName.setCellValueFactory(new PropertyValueFactory<>("subKriteriaName"));
        nilai.setCellValueFactory(new PropertyValueFactory<>("nilai"));
        kriteriaId.setCellValueFactory(new PropertyValueFactory<>("kriteriaId"));
        kriteriaId.setVisible(false);

        List<KriteriaNew> lstKriteria =  service.getAllKriteria();
        tbKriteria.getItems().setAll(lstKriteria);
    }

    @FXML
    void onClickRowTable(MouseEvent event) {
      KriteriaNew item = tbKriteria.getSelectionModel().getSelectedItem();

      List<SubKriteriaNew>lstSub = service.getSubKriteriaByIdKriteria(item.getId());
      tbSubKriteria.getItems().setAll(lstSub);
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
