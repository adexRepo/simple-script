package com.projects.simplescript.services;

import java.util.List;

import com.projects.simplescript.model.biz.AlternativeList;
import com.projects.simplescript.model.biz.AlternativeNew;
import com.projects.simplescript.model.biz.Kodifikasi;
import com.projects.simplescript.model.biz.KriteriaNew;
import com.projects.simplescript.model.biz.MatrixInputAndNormalisasi;
import com.projects.simplescript.model.biz.SubKriteriaNew;

public interface AhpService {

    double[] calculatePriorityVector(double[][] matrix);

    double[][] normalizeMatrix(double[][] matrix);

    List<KriteriaNew> getAllKriteria();

    List<SubKriteriaNew> getSubKriteriaByIdKriteria(Integer idKriteria);

    List<AlternativeNew> getAllAlternatif();

    List<AlternativeList> getAllAlternatifValueName();

    List<Kodifikasi> getAllAlternatifValueNilai();

    void updateSubIdInAlternatifTableByName(String nama, Integer id, String subItem);

    void updateValueKriteria(List<MatrixInputAndNormalisasi> items);

    List<AlternativeNew> deleteAlternatif(Integer id);

    List<AlternativeNew> updateDataAlternatif(Integer id, String string, String string2);

    List<AlternativeNew> saveDataAnggota(String nama, String alamat, String data1, String data2, String data3, String data4, String data5,
            String data6);

}
