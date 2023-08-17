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

}
