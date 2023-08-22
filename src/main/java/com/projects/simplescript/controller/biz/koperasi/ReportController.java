package com.projects.simplescript.controller.biz.koperasi;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.stereotype.Component;

import com.projects.simplescript.model.biz.Calculation;
import com.projects.simplescript.model.biz.Kodifikasi;
import com.projects.simplescript.model.biz.KriteriaNew;
import com.projects.simplescript.model.biz.MatrixBobot;
import com.projects.simplescript.model.biz.Report1;
import com.projects.simplescript.model.biz.Report2;
import com.projects.simplescript.services.AhpService;
import com.projects.simplescript.utils.ComponentUi;
import com.projects.simplescript.utils.GenerateReport;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.RequiredArgsConstructor;
import net.rgielen.fxweaver.core.FxmlView;

@Component
@FxmlView("/ui/biz/report.fxml")
@RequiredArgsConstructor
public class ReportController implements Initializable {

    private final AhpService service;

    @FXML
    private Button btnDownload;

    @FXML
    private TableColumn<Report1, String> col1;

    @FXML
    private TableColumn<Report1, Double> col2;

    @FXML
    private TableColumn<Report1, Double> col3;

    @FXML
    private TableColumn<Report1, Double> col4;

    @FXML
    private TableColumn<Report1, Double> col5;

    @FXML
    private TableColumn<Report1, Double> col6;

    @FXML
    private TableColumn<Report1, Double> col7;

    @FXML
    private TableColumn<Report1, Double> col8;

    @FXML
    private TableColumn<Report1, String> col9;

    @FXML
    private TableView<Report1> tblReport1;

    @FXML
    void onDownloadPdf(ActionEvent event) throws Exception {
        List<Kodifikasi> kodifikasi = service.getAllAlternatifValueNilai();
        Integer countKriteria = service.getAllKriteria().size();
        List<Report2> report = Calculation.getReportForPdf(kodifikasi, countKriteria);
        // report.stream().forEach(val -> val.setRealDate(realDate));
        String result = GenerateReport.generate("reportKoperasi", report, "pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result);
    }

    @FXML
    void onDownloadPdf2(ActionEvent event) throws Exception {
        List<KriteriaNew> lstKriteria = service.getAllKriteria();
        // lstKriteria.stream().forEach(val -> val.setRealDate(realDate));
        String result = GenerateReport.generate("reportKriteria", lstKriteria, "pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result);
    }

    @FXML
    void onDownloadPdf3(ActionEvent event) throws Exception {
        List<Kodifikasi> kodifikasi = service.getAllAlternatifValueNilai();
        Integer countKriteria = service.getAllKriteria().size();
        List<MatrixBobot> data2 = Calculation.getDataHasilPerhitungan2(kodifikasi, countKriteria);
        // data2.stream().forEach(val -> val.setRealDate(realDate));
        String result = GenerateReport.generate("reportAlternatif", data2, "pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result);
    }

    @FXML
    void onDownloadPdf4(ActionEvent event) throws Exception {
        List<KriteriaNew> kriteria = service.getAllKriteria();
        List<MatrixBobot> data3 = Calculation.getDataFromObjectArrayMatrixAhp3(kriteria);
        // data3.stream().forEach(val -> val.setRealDate(realDate));
        String result = GenerateReport.generate("reportPerhitunganAhp", data3, "pdf");
        ComponentUi.showAlert(AlertType.INFORMATION, "Generate Report", result);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        col1.setCellValueFactory(new PropertyValueFactory<>("anggotaName"));
        col2.setCellValueFactory(new PropertyValueFactory<>("k1"));
        col3.setCellValueFactory(new PropertyValueFactory<>("k2"));
        col4.setCellValueFactory(new PropertyValueFactory<>("k3"));
        col5.setCellValueFactory(new PropertyValueFactory<>("k4"));
        col6.setCellValueFactory(new PropertyValueFactory<>("k5"));
        col7.setCellValueFactory(new PropertyValueFactory<>("k6"));
        col8.setCellValueFactory(new PropertyValueFactory<>("score"));
        col9.setCellValueFactory(new PropertyValueFactory<>("kelayakan"));

        List<Kodifikasi> kodifikasi = service.getAllAlternatifValueNilai();
        Integer countKriteria = service.getAllKriteria().size();
        List<Report1> data1 = Calculation.getDataReportDisplay(kodifikasi, countKriteria);
        tblReport1.getItems().setAll(data1);

    }

}
