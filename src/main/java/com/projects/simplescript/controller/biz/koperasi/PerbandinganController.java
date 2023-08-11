package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.Calculation;
import com.projects.simplescript.model.biz.MatrixBobot;
import com.projects.simplescript.model.biz.MatrixInputAndNormalisasi;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;


@Component
@FxmlView("/ui/biz/perbandingan.fxml")
@RequiredArgsConstructor
public class PerbandinganController implements Initializable {
    
    @FXML
    private TableView<MatrixInputAndNormalisasi> tbKodifikasi;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, String> col1;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col2;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col3;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col4;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col5;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col6;
    @FXML
    private TableColumn<MatrixInputAndNormalisasi, Integer> col7;
    
        
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

        List<MatrixInputAndNormalisasi> data1 = Calculation.getDataFromObjectArrayMatrixAhp1();
        tbKodifikasi.getItems().setAll(data1);
        
        col11.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col22.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col33.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col44.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col55.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col66.setCellValueFactory(new PropertyValueFactory<>("k6"));
        col77.setCellValueFactory(new PropertyValueFactory<>("k7"));
        List<MatrixInputAndNormalisasi> data2 = Calculation.getDataFromObjectArrayMatrixAhp2();
        tbNormalisasi.getItems().setAll(data2);
        
        col111.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col222.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col333.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col444.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col555.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col666.setCellValueFactory(new PropertyValueFactory<>("k6"));
        col777.setCellValueFactory(new PropertyValueFactory<>("k7"));
        col888.setCellValueFactory(new PropertyValueFactory<>("bobot"));
        col999.setCellValueFactory(new PropertyValueFactory<>("eigenVal"));
        List<MatrixBobot> data3 = Calculation.getDataFromObjectArrayMatrixAhp3();
        tbMatrix.getItems().setAll(data3);
    }
    
}
