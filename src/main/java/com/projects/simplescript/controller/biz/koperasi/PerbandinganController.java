package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.Calculation;
import com.projects.simplescript.model.biz.KriteriaNew;
import com.projects.simplescript.model.biz.MatrixBobot;
import com.projects.simplescript.model.biz.MatrixInputAndNormalisasi;
import com.projects.simplescript.services.AhpService;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DoubleStringConverter;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;


@Component
@FxmlView("/ui/biz/perbandingan.fxml")
@RequiredArgsConstructor
public class PerbandinganController implements Initializable {
    
    private final AhpService service;

    @FXML
    private TableView<MatrixInputAndNormalisasi> tbKodifikasi;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, String> col1;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Double> col2;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Double> col3;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Double> col4;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Double> col5;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Double> col6;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Double> col7;
    
        
    @FXML
    private TableView<MatrixInputAndNormalisasi> tbNormalisasi;

    @FXML
    private TableColumn<MatrixInputAndNormalisasi, String>  col11;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col22;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col33;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col44;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col55;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col66;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col77;
    
    @FXML
    private TableView<MatrixBobot> tbMatrix;
    @FXML
    private TableColumn<MatrixBobot,String>   col111;
    @FXML
    private TableColumn<MatrixBobot, Integer> col222;
    @FXML
    private TableColumn<MatrixBobot, Integer> col333;
    @FXML
    private TableColumn<MatrixBobot, Integer> col444;
    @FXML
    private TableColumn<MatrixBobot, Integer> col555;
    @FXML
    private TableColumn<MatrixBobot, Integer> col666;
    @FXML
    private TableColumn<MatrixBobot, Integer> col777;
    @FXML
    private TableColumn<MatrixBobot, Integer> col888;
    @FXML
    private TableColumn<MatrixBobot, Integer> col999;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        col1.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col2.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col3.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col4.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col5.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col6.setCellValueFactory(new PropertyValueFactory<>("k6"));
        col7.setCellValueFactory(new PropertyValueFactory<>("k7"));
        configEditableTableView();
        
        col11.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col22.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col33.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col44.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col55.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col66.setCellValueFactory(new PropertyValueFactory<>("k6"));
        col77.setCellValueFactory(new PropertyValueFactory<>("k7"));

        col111.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col222.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col333.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col444.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col555.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col666.setCellValueFactory(new PropertyValueFactory<>("k6"));
        col777.setCellValueFactory(new PropertyValueFactory<>("k7"));
        col888.setCellValueFactory(new PropertyValueFactory<>("bobot"));
        col999.setCellValueFactory(new PropertyValueFactory<>("eigenVal"));

        refresh();

    }
    
    @FXML
    void onHitung(ActionEvent event) throws Exception {
        List<MatrixInputAndNormalisasi> items = tbKodifikasi.getItems();
        service.updateValueKriteria(items);
        refresh();
    }

    private void refresh() {
        List<KriteriaNew> kriteria = service.getAllKriteria();
        List<MatrixInputAndNormalisasi> data1 = new ArrayList<>();
        for (KriteriaNew item : kriteria) {
            MatrixInputAndNormalisasi inp = new MatrixInputAndNormalisasi();
            inp.setK1("K"+item.getId());
            inp.setK2(item.getK1Val());
            inp.setK3(item.getK2Val());
            inp.setK4(item.getK3Val());
            inp.setK5(item.getK4Val());
            inp.setK6(item.getK5Val());
            inp.setK7(item.getK6Val());
            data1.add(inp);
        }
        
        tbKodifikasi.getItems().setAll(data1);
        List<MatrixInputAndNormalisasi> data2 = Calculation.getDataFromObjectArrayMatrixAhp2(kriteria);
        tbNormalisasi.getItems().setAll(data2);
        List<MatrixBobot> data3 = Calculation.getDataFromObjectArrayMatrixAhp3(kriteria);
        tbMatrix.getItems().setAll(data3);
    }

    private void configEditableTableView(){
        tbKodifikasi.setEditable(true);
        col2.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col2.setOnEditCommit(
            new EventHandler<CellEditEvent<MatrixInputAndNormalisasi, Double>>() {
                @Override
                public void handle(CellEditEvent<MatrixInputAndNormalisasi, Double> t) {
                    MatrixInputAndNormalisasi item = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    item.setK2(t.getNewValue());
                }
            }
        );

        
        col3.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col3.setOnEditCommit(
            new EventHandler<CellEditEvent<MatrixInputAndNormalisasi, Double>>() {
                @Override
                public void handle(CellEditEvent<MatrixInputAndNormalisasi, Double> t) {
                    MatrixInputAndNormalisasi item = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    item.setK3(t.getNewValue());
                }
            }
        );
        
        col4.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col4.setOnEditCommit(
            new EventHandler<CellEditEvent<MatrixInputAndNormalisasi, Double>>() {
                @Override
                public void handle(CellEditEvent<MatrixInputAndNormalisasi, Double> t) {
                    MatrixInputAndNormalisasi item = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    item.setK4(t.getNewValue());
                }
            }
        );
        
        col5.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col5.setOnEditCommit(
            new EventHandler<CellEditEvent<MatrixInputAndNormalisasi, Double>>() {
                @Override
                public void handle(CellEditEvent<MatrixInputAndNormalisasi, Double> t) {
                    MatrixInputAndNormalisasi item = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    item.setK5(t.getNewValue());
                }
            }
        );
        
        col6.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col6.setOnEditCommit(
            new EventHandler<CellEditEvent<MatrixInputAndNormalisasi, Double>>() {
                @Override
                public void handle(CellEditEvent<MatrixInputAndNormalisasi, Double> t) {
                    MatrixInputAndNormalisasi item = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    item.setK6(t.getNewValue());
                }
            }
        );
        
        col7.setCellFactory(TextFieldTableCell.forTableColumn(new DoubleStringConverter()));
        col7.setOnEditCommit(
            new EventHandler<CellEditEvent<MatrixInputAndNormalisasi, Double>>() {
                @Override
                public void handle(CellEditEvent<MatrixInputAndNormalisasi, Double> t) {
                    MatrixInputAndNormalisasi item = t.getTableView().getItems().get(t.getTablePosition().getRow());
                    item.setK7(t.getNewValue());
                }
            }
        );
        
    }
}
