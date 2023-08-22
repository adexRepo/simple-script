package com.projects.simplescript.services.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.springframework.stereotype.Service;

import com.projects.simplescript.dao.AlternatifRepository;
import com.projects.simplescript.dao.CustomSqlRepository;
import com.projects.simplescript.dao.KriteriaRepository;
import com.projects.simplescript.dao.SubKriteriaRepository;
import com.projects.simplescript.model.biz.AlternativeList;
import com.projects.simplescript.model.biz.AlternativeNew;
import com.projects.simplescript.model.biz.Kodifikasi;
import com.projects.simplescript.model.biz.KriteriaNew;
import com.projects.simplescript.model.biz.MatrixInputAndNormalisasi;
import com.projects.simplescript.model.biz.SubKriteriaNew;
import com.projects.simplescript.services.AhpService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AhpServiceImpl implements AhpService {

    private final KriteriaRepository repoKriteria;
    private final SubKriteriaRepository repoSubKriteria;
    private final AlternatifRepository repoAlternatif;
    private final CustomSqlRepository repoCustomSql;

    @Override
    public double[] calculatePriorityVector(double[][] matrix) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);
        EigenDecomposition decomposition = new EigenDecomposition(realMatrix);
        double[] eigenvalues = decomposition.getRealEigenvalues();
        double maxEigenvalue = eigenvalues[eigenvalues.length - 1];

        double[] priorityVector = new double[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            priorityVector[i] = eigenvalues[i] / maxEigenvalue;
            System.out.format("%.3f ", priorityVector[i]);
        }

        return priorityVector;
    }

    @Override
    public double[][] normalizeMatrix(double[][] matrix) {
        int n = matrix.length;
        double[][] normalizedMatrix = new double[n][n];
        for (int i = 0; i < n; i++) {
            double sum = 0;
            for (int j = 0; j < n; j++) {
                sum += matrix[j][i];
            }
            for (int j = 0; j < n; j++) {
                normalizedMatrix[j][i] = matrix[j][i] / sum;
            }
        }
        return normalizedMatrix;
    }

    @Override
    public List<KriteriaNew> getAllKriteria() {
        List<KriteriaNew> data = repoKriteria.findAll();
        return data;
    }

    @Override
    public List<SubKriteriaNew> getSubKriteriaByIdKriteria(Integer idKriteria) {
        List<SubKriteriaNew> data = repoSubKriteria.findByKriteriaId(idKriteria);
        return data;
    }

    @Override
    public List<AlternativeNew> getAllAlternatif() {
        List<AlternativeNew> data = repoAlternatif.findAll();
        return data;
    }

    @Override
    public List<AlternativeList> getAllAlternatifValueName() {
        List<AlternativeList> data = repoCustomSql.getAllAlternatifValueNameUsingJdbc();
        return data;
    }

    @Override
    public List<Kodifikasi> getAllAlternatifValueNilai() {
        List<Kodifikasi> data = repoCustomSql.getAllAlternatifValueNilaiUsingJdbc();
        return data;
    }

    @Override
    public void updateSubIdInAlternatifTableByName(String nama, Integer idKriteria, String subKriteriaName) {
        SubKriteriaNew subKriteria = repoSubKriteria.findByKriteriaIdAndSubKriteriaName(idKriteria, subKriteriaName).get(0);
        repoCustomSql.updateAlternatifSubIdByName(nama, idKriteria, subKriteria.getId());
    }

    @Override
    public void updateValueKriteria(List<MatrixInputAndNormalisasi> matrixInputAndNormalisasi) {
        for (MatrixInputAndNormalisasi item : matrixInputAndNormalisasi) {
            Integer id = Integer.valueOf(item.getK1().replaceAll("[^\\d.]", ""));
            Optional<KriteriaNew> kriteria = repoKriteria.findById(id);
            if (kriteria.isPresent()) {
                KriteriaNew k = kriteria.get();
                if (!(item.getK2().equals(k.getK1Val()))) {
                    k.setK1Val(item.getK2());
                }

                if (!(item.getK3().equals(k.getK2Val()))) {
                    k.setK2Val(item.getK3());
                }

                if (!(item.getK4().equals(k.getK3Val()))) {
                    k.setK3Val(item.getK4());
                }

                if (!(item.getK5().equals(k.getK4Val()))) {
                    k.setK4Val(item.getK5());
                }

                if (!(item.getK6().equals(k.getK5Val()))) {
                    k.setK5Val(item.getK6());
                }

                if (!(item.getK7().equals(k.getK6Val()))) {
                    k.setK6Val(item.getK7());
                }

                repoKriteria.save(k);
            }
        }

    }

    @Override
    public List<AlternativeNew> deleteAlternatif(Integer id) {
        repoAlternatif.deleteById(id);

        List<AlternativeNew> data = repoAlternatif.findAll();
        return data;
    }

    @Override
    public List<AlternativeNew> updateDataAlternatif(Integer id, String nama, String alamat) {

        Optional<AlternativeNew> entry = repoAlternatif.findById(id);
        if (entry.isPresent()) {
            AlternativeNew data = entry.get();
            data.setAlamat(alamat);
            data.setName(nama);
            repoAlternatif.saveAndFlush(data);

        }
        List<AlternativeNew> ret = repoAlternatif.findAll();
        return ret;
    }

    @Override
    @Transactional
    public List<AlternativeNew> saveDataAnggota(String nama, String alamat, String data1, String data2, String data3,
            String data4, String data5,
            String data6) {
        Long count = repoAlternatif.count();
        AlternativeNew newData = new AlternativeNew();
        newData.setId(count.intValue());
        newData.setName(nama);
        newData.setAlamat(alamat);
        List<SubKriteriaNew> subsKriteria1 = getAllSubKriteria(1,data1);
        List<SubKriteriaNew> subsKriteria2 = getAllSubKriteria(2,data2);
        List<SubKriteriaNew> subsKriteria3 = getAllSubKriteria(3,data3);
        List<SubKriteriaNew> subsKriteria4 = getAllSubKriteria(4,data4);
        List<SubKriteriaNew> subsKriteria5 = getAllSubKriteria(5,data5);
        List<SubKriteriaNew> subsKriteria6 = getAllSubKriteria(6,data6);

        newData.setK1SubId(subsKriteria1.get(0).getId());
        newData.setK2SubId(subsKriteria2.get(0).getId());
        newData.setK3SubId(subsKriteria3.get(0).getId());
        newData.setK4SubId(subsKriteria4.get(0).getId());
        newData.setK5SubId(subsKriteria5.get(0).getId());
        newData.setK6SubId(subsKriteria6.get(0).getId());

        repoAlternatif.saveAndFlush(newData);
        List<AlternativeNew> data = repoAlternatif.findAll();
        return data;
    }

    private List<SubKriteriaNew> getAllSubKriteria(Integer id, String name){
        return repoSubKriteria.findByKriteriaIdAndSubKriteriaName(id,name);
    }
}
