package com.projects.simplescript.model.biz;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import com.projects.simplescript.model.Storage;
import com.projects.simplescript.utils.logicahp.SubKriteriaAhp;

public class Calculation {
    
    public static void display(double[][] perbMatriksInput){
        for(double[] d : perbMatriksInput){
            for(double j : d){
                System.out.format("%.3f ",j);
            }
            System.out.println("");
        }
    }

    public static SubKriteria helper(List<SubKriteria> subKriterias, Integer tgt) {
        SubKriteria k = subKriterias.stream()
                .filter(val -> val.getId().equals(tgt))
                .findFirst()
                .orElse(null);

        return k;
    }

    public static Double helperGetNilai(List<SubKriteria> dataSubKriteria, Integer idSub) {
        SubKriteria subKriteriaDtl = dataSubKriteria.stream()
                .filter(val -> val.getId().equals(idSub))
                .findFirst()
                .orElse(null);

        return subKriteriaDtl.getNilai().doubleValue();
    }

    private static double[][] convertToMatirxBasic(List<KriteriaNew> kriteria){
        Integer length = kriteria.size();
        double[][] perbMatriksInput = new double[length][length];
        for (int i = 0; i < length; i++) {
            perbMatriksInput[i][0] = kriteria.get(i).getK1Val();
            perbMatriksInput[i][1] = kriteria.get(i).getK2Val();
            perbMatriksInput[i][2] = kriteria.get(i).getK3Val();
            perbMatriksInput[i][3] = kriteria.get(i).getK4Val();
            perbMatriksInput[i][4] = kriteria.get(i).getK5Val();
            perbMatriksInput[i][5] = kriteria.get(i).getK6Val();
        }

        return perbMatriksInput;
    }

    private static double[][] convertToMatirxAlternatifBasic(List<Kodifikasi> kodifikasi,Integer countKriteria){
        Integer baris = kodifikasi.size();
        double[][] perbMatriksInput = new double[baris][countKriteria];
        for (int i = 0; i < baris; i++) {
            perbMatriksInput[i][0] = kodifikasi.get(i).getK1();
            perbMatriksInput[i][1] = kodifikasi.get(i).getK2();
            perbMatriksInput[i][2] = kodifikasi.get(i).getK3();
            perbMatriksInput[i][3] = kodifikasi.get(i).getK4();
            perbMatriksInput[i][4] = kodifikasi.get(i).getK5();
            perbMatriksInput[i][5] = kodifikasi.get(i).getK6();
        }

        return perbMatriksInput;
    }

    public static List<Report1> getDataReportDisplay(List<Kodifikasi> kodifikasi,Integer countKriteria) {
        List<Report1> result = new ArrayList<>();
        List<String> anggotas = kodifikasi.stream().map(val->val.getAnggota()).toList();
        List<HasilPriority> dataHasil = getDataHasilPerhitungan3("notAll",kodifikasi,countKriteria);
        List<MatrixBobot> matrixBobot = getDataHasilPerhitungan2(kodifikasi, countKriteria);

        for (int i = 0; i < anggotas.size(); i++) {
            Report1 report = new Report1();
            report.setAnggotaName(anggotas.get(i));
            report.setK1(matrixBobot.get(i).getK2());
            report.setK2(matrixBobot.get(i).getK3());
            report.setK3(matrixBobot.get(i).getK4());
            report.setK4(matrixBobot.get(i).getK5());
            report.setK5(matrixBobot.get(i).getK6());
            report.setK6(matrixBobot.get(i).getK7());
            report.setScore(dataHasil.get(i).getHasil());
            result.add(report);
        }
        Collections.sort(result, (a, b) -> a.getScore() < b.getScore() ? -1 : a.getScore() == b.getScore() ? 0 : 1);
        Integer counter = 1;
        for (Report1 item : result) {
            if (counter == 3) {
                item.setKelayakan("Layak");
            } else {
                item.setKelayakan("Tidak Layak");
            }
            counter++;
        }

        return result;
    }
    public static List<Report2> getReportForPdf(List<Kodifikasi> kodifikasi,Integer countKriteria) {
        List<Report2> result = new ArrayList<>();
        List<String> anggotas = kodifikasi.stream().map(val->val.getAnggota()).toList();
        List<HasilPriority> dataHasil = getDataHasilPerhitungan3("all",kodifikasi,countKriteria);

        for (int i = 0; i < anggotas.size(); i++) {
            Report2 report = new Report2();

            report.setName(anggotas.get(i));
            report.setValue(dataHasil.get(i).getHasil());
            result.add(report);
        }
        Collections.sort(result, (a, b) -> a.getValue() < b.getValue() ? -1 : a.getValue() == b.getValue() ? 0 : 1);
        Integer counter = 1;
        for (Report2 item : result) {
            if (counter == 3) {
                item.setResult("Layak");
            } else {
                item.setResult("Tidak Layak");
            }
            counter++;
        }

        return result;
    }


    public static List<MatrixBobot> getDataHasilPerhitungan2(List<Kodifikasi> kodifikasi,Integer countKriteria) {

        double[][] matrix = convertToMatirxAlternatifBasic(kodifikasi,countKriteria);
        List<MatrixBobot> result = new ArrayList<>();

        int numAlternatives = matrix.length;
        int numCriteria = matrix[0].length;
        Storage.checkConfig();
        List<String> anggotas = kodifikasi.stream().map(val->val.getAnggota()).toList();
        ;

        for (int col = 0; col < numCriteria; col++) {
            double colSum = 0;
            for (int row = 0; row < numAlternatives; row++) {
                colSum += matrix[row][col];
            }
            for (int row = 0; row < numAlternatives; row++) {
                matrix[row][col] /= colSum;
            }
        }

        double[][] dataNormalisasi = new double[numAlternatives][numCriteria];
        for (int i = 0; i < numAlternatives; i++) {
            for (int j = 0; j < numCriteria; j++) {
                dataNormalisasi[i][j] = matrix[i][j];
            }
        }

        for (int i = 0; i < dataNormalisasi.length; i++) {
            MatrixBobot matrixInput = new MatrixBobot();
            matrixInput.setK1(anggotas.get(i));
            matrixInput.setK2(Math.round(dataNormalisasi[i][0] * 100.0) / 100.0);
            matrixInput.setK3(Math.round(dataNormalisasi[i][1] * 100.0) / 100.0);
            matrixInput.setK4(Math.round(dataNormalisasi[i][2] * 100.0) / 100.0);
            matrixInput.setK5(Math.round(dataNormalisasi[i][3] * 100.0) / 100.0);
            matrixInput.setK6(Math.round(dataNormalisasi[i][4] * 100.0) / 100.0);
            matrixInput.setK7(Math.round(dataNormalisasi[i][5] * 100.0) / 100.0);
            result.add(matrixInput);
        }

        SubKriteriaAhp sub = new SubKriteriaAhp(matrix);
        double[] jumlahBaris = sub.calcJumlahBaris(dataNormalisasi);
        Integer counter = 0;
        for (MatrixBobot map : result) {
            map.setBobot((jumlahBaris[counter] / 6));
            counter++;
        }

        return result;
    }

    public static List<HasilPriority> getDataHasilPerhitungan3(String type,List<Kodifikasi> kodifikasi,Integer countKriteria) {
        double[][] matrix = convertToMatirxAlternatifBasic(kodifikasi,countKriteria);
        List<String> anggotas = kodifikasi.stream().map(val->val.getAnggota()).toList();

        int numAlternatives = matrix.length;
        int numCriteria = matrix[0].length;

        for (int col = 0; col < numCriteria; col++) {
            double colSum = 0;
            for (int row = 0; row < numAlternatives; row++) {
                colSum += matrix[row][col];
            }
            for (int row = 0; row < numAlternatives; row++) {
                matrix[row][col] /= colSum;
            }
        }

        Storage.checkConfig();
        RealMatrix normalizedMatrix = MatrixUtils.createRealMatrix(matrix);
        SingularValueDecomposition svd = new SingularValueDecomposition(normalizedMatrix);
        RealMatrix rightSingularVectors = svd.getV();

        double[] priorities = new double[numAlternatives];
        for (int row = 0; row < numAlternatives; row++) {
            double sum = 0;
            for (int col = 0; col < numCriteria; col++) {
                sum += rightSingularVectors.getEntry(col, row);
            }
            priorities[row] = sum / numCriteria;
        }

        List<HasilPriority> res = new ArrayList<>();
        for (int i = 0; i < priorities.length; i++) {
            HasilPriority map = new HasilPriority();
            map.setNamaAlternative(anggotas.get(i));
            if(type.equals("all")){
                map.setHasil(priorities[i]);
            }else{
                map.setHasil(priorities[i]);
            }
            res.add(map);
        }

        return res;
    }


     public static List<MatrixInputAndNormalisasi> getDataFromObjectArrayMatrixAhp2(List<KriteriaNew> kriteria) {

        List<MatrixInputAndNormalisasi> result = new ArrayList<>();
        double[][] perbMatriksInput = convertToMatirxBasic(kriteria);

        SubKriteriaAhp sub = new SubKriteriaAhp(perbMatriksInput);
        double[] jumlahkolom = sub.calcJumlahKolom();
        double[][] dataNormalisasi = sub.normMatriks(jumlahkolom);
        for (int i = 0; i < dataNormalisasi.length; i++) {
            MatrixInputAndNormalisasi matrixInput = new MatrixInputAndNormalisasi();
            matrixInput.setK1("K" + (i + 1));
            matrixInput.setK2(Math.round(dataNormalisasi[i][0] * 100.0) / 100.0);
            matrixInput.setK3(Math.round(dataNormalisasi[i][1] * 100.0) / 100.0);
            matrixInput.setK4(Math.round(dataNormalisasi[i][2] * 100.0) / 100.0);
            matrixInput.setK5(Math.round(dataNormalisasi[i][3] * 100.0) / 100.0);
            matrixInput.setK6(Math.round(dataNormalisasi[i][4] * 100.0) / 100.0);
            matrixInput.setK7(Math.round(dataNormalisasi[i][5] * 100.0) / 100.0);
            result.add(matrixInput);
        }

        double[] jumlahBaris = sub.calcJumlahBaris(dataNormalisasi);
        MatrixInputAndNormalisasi total = new MatrixInputAndNormalisasi();
        total.setK1("Total");
        total.setK2(Math.round((jumlahBaris[0] / 6) * 100.0) / 100.0);
        total.setK3(Math.round((jumlahBaris[1] / 6) * 100.0) / 100.0);
        total.setK4(Math.round((jumlahBaris[2] / 6) * 100.0) / 100.0);
        total.setK5(Math.round((jumlahBaris[3] / 6) * 100.0) / 100.0);
        total.setK6(Math.round((jumlahBaris[4] / 6) * 100.0) / 100.0);
        total.setK7(Math.round((jumlahBaris[5] / 6) * 100.0) / 100.0);

        result.add(total);
        return result;
    }

    public static List<MatrixBobot> getDataFromObjectArrayMatrixAhp3(List<KriteriaNew> kriteria) {
        List<MatrixBobot> result = new ArrayList<>();

        double[][] perbMatriksInput = convertToMatirxBasic(kriteria);
        Storage.checkConfig();
        SubKriteriaAhp sub = new SubKriteriaAhp(perbMatriksInput);
        double[] jumlahkolom = sub.calcJumlahKolom();
        double[][] dataNormalisasi = sub.normMatriks(jumlahkolom);
        for (int i = 0; i < dataNormalisasi.length; i++) {
            MatrixBobot matrixInput = new MatrixBobot();
            matrixInput.setK1("K" + (i + 1));
            matrixInput.setK2(Math.round(dataNormalisasi[i][0] * 100.0) / 100.0);
            matrixInput.setK3(Math.round(dataNormalisasi[i][1] * 100.0) / 100.0);
            matrixInput.setK4(Math.round(dataNormalisasi[i][2] * 100.0) / 100.0);
            matrixInput.setK5(Math.round(dataNormalisasi[i][3] * 100.0) / 100.0);
            matrixInput.setK6(Math.round(dataNormalisasi[i][4] * 100.0) / 100.0);
            matrixInput.setK7(Math.round(dataNormalisasi[i][5] * 100.0) / 100.0);
            result.add(matrixInput);
        }
        double[] jumlahBaris = sub.calcJumlahBaris(dataNormalisasi);
        Integer counter = 0;
        for (MatrixBobot map : result) {
            map.setBobot((jumlahBaris[counter] / 6));
            counter++;
        }

        RealMatrix normalizedMatrix = MatrixUtils.createRealMatrix(perbMatriksInput);
        for (int col = 0; col < normalizedMatrix.getColumnDimension(); col++) {
            double colSum = normalizedMatrix.getColumnVector(col).getNorm();
            normalizedMatrix.setColumnVector(col, normalizedMatrix.getColumnVector(col).mapDivide(colSum));
        }

        // Step 2: Calculate the Eigenvector
        EigenDecomposition eigenDecomposition = new EigenDecomposition(normalizedMatrix);
        double[] eigenvalues = eigenDecomposition.getRealEigenvalues();
        double maxEigenvalue = eigenvalues[0]; // Largest eigenvalue
        int maxEigenvalueIndex = 0;
        for (int i = 1; i < eigenvalues.length; i++) {
            if (eigenvalues[i] > maxEigenvalue) {
                maxEigenvalue = eigenvalues[i];
                maxEigenvalueIndex = i;
            }
        }
        RealVector eigenvector = eigenDecomposition.getEigenvector(maxEigenvalueIndex);

        int i = 0;
        for (Double val : eigenvector.toArray()) {
            result.get(i).setEigenVal((val / 6));
            i++;
        }

        return result;
    }

}
