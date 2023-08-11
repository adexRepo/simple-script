package com.projects.simplescript.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Component
public class GenerateReport {

    public static String generate(String typeReport, List<?> data, String format) throws Exception {

        try {
            if (typeReport.equals("Karyawan")) {
                return export("Karyawan", data, "pdf", "Report-Karyawan");
            } else if (typeReport.equals("kompetensi")) {
                return export("kompetensi", data, "pdf", "Report-Kompetensi");
            } else if (typeReport.equals("hasilPenilaian")) {
                return export("hasilPenilaian", data, "pdf", "Report-Hasil-Penilaian");
            } else if (typeReport.equals("rataRata")) {
                return export("rataRata", data, "pdf", "Report-Rata-rata-Penilaian");
            } else if (typeReport.equals("reportKeseluruhan")) {
                return export("reportKeseluruhan", data, "pdf", "Report-Penilaian-Keseluruhan");
            } else if (typeReport.equals("reportKoperasi")) {
                return export("reportKoperasi", data, "pdf", "Report Koperasi");
            }else{
                return "Gagal";
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    public static String export(String classPath, List<?> data, String format, String fileName)
            throws FileNotFoundException, JRException {
        String path = "C:\\Users\\Public\\report";
        File file = ResourceUtils.getFile("classpath:report/" + classPath + ".jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(data);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "Adek Kristiyanto");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (format.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\" + fileName + ".html");
        }
        if (format.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\" + fileName + ".pdf");
        }
        return "report generated in path : " + path+"\\"+fileName;
    }
}
