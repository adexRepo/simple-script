package com.projects.simplescript.utils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.stereotype.Component;

import javafx.scene.control.Alert.AlertType;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@Component
public class GenerateReport {

    public static String generate(String typeReport, List<?> data, String format) throws Exception {

        try {
            if (typeReport.equals("Karyawan")) {
                return export("Karyawan", data, "pdf", "Report-Karyawan");
            } else if (typeReport.equals("kompetensi")) {
                return export("kompetensi", data, "pdf", "Report-Kompetensi");
            } else if (typeReport.equals("reportPerhitunganAhp")) {
                return export("reportPerhitunganAhp", data, "pdf", "Report-Perhitungan-Ahp");
            } else if (typeReport.equals("reportAlternatif")) {
                return export("reportAlternatif", data, "pdf", "Report-Alternatif");
            } else if (typeReport.equals("reportKriteria")) {
                return export("reportKriteria", data, "pdf", "Report-Kriteria");
            } else if (typeReport.equals("reportKoperasi")) {
                return export("reportKoperasi", data, "pdf", "Report-Koperasi");
            } else {
                return "Gagal";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public static String export(String classPath, List<?> data, String format, String fileName)
            throws Exception {
        try {
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy", new Locale("id", "ID"));
            ComponentUi.showAlert(AlertType.INFORMATION, "Generating PDF", "Processing");
            String realDate = "Jakarta, " + currentDate.format(dateFormatter);
            String userHome = System.getProperty("user.home");
            String path = userHome + "\\Downloads"; // Set the path to the Downloads folder
            String nama_file = "src/main/resources/report/"+classPath+".jrxml";
            File report = new File(nama_file);
            JasperDesign jDesign = JRXmlLoader.load(report);
            JasperReport jReport = JasperCompileManager.compileReport(jDesign);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy", "System Auto");
            parameters.put("tgl", realDate); // Pass the realDate parameter to JasperReports
            System.out.println(parameters.toString());
            JasperPrint jasperPrint = JasperFillManager.fillReport(jReport, parameters, dataSource);
            // JasperViewer.viewReport(jasperPrint,false);
            if (format.equalsIgnoreCase("pdf")) {
                JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\" + fileName + ".pdf");
            }
            return "Report generated in path: " + path + "\\" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

}
