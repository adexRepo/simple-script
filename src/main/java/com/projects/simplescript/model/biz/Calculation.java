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

    public static List<Report1> getDataReport1(){
        List<Report1>  result = new ArrayList<>();
        List<String> anggotas = List.of("Maria", "Marni", "Agus");
        List<HasilPriority> dataHasil =  getDataHasilPerhitungan3();
        List<MatrixBobot> matrixBobot = getDataHasilPerhitungan2();

        for (int i = 0; i < anggotas.size(); i++) {
            Report1 report = new Report1();
            report.setAnggotaName(anggotas.get(i));
            report.setK1       (matrixBobot.get(i).getK2());
            report.setK2       (matrixBobot.get(i).getK3());
            report.setK3       (matrixBobot.get(i).getK4());
            report.setK4       (matrixBobot.get(i).getK5());
            report.setK5       (matrixBobot.get(i).getK6());
            report.setK6       (matrixBobot.get(i).getK7());
            report.setScore    (dataHasil.get(i).getHasil()); 
            result.add(report);
        }
        Collections.sort(result,  (a, b) -> a.getScore() < b.getScore() ? -1 : a.getScore() == b.getScore() ? 0 : 1);
        Integer counter = 1;
        for (Report1 item : result) {
            if(counter == 3){
                item.setKelayakan("Layak");
            }else{
                item.setKelayakan("Tidak Layak");
            }
            counter++;
        }

        return result;
    }

    public static List<HasilPriority> getDataHasilPerhitungan3(){
        double[][] matrix = Storage.getInstance().getMatrixAlternatif();
        List<String> anggotas = Storage.getInstance().getAlternatif();
       
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
        
        List<HasilPriority>  res = new ArrayList<>();
        for (int i = 0; i < priorities.length; i++) {
             HasilPriority map = new HasilPriority();
            map.setNamaAlternative(anggotas.get(i));
            map.setHasil(Math.round(priorities[i] * 100.0) / 100.0 );
            res.add(map);
        }

        return res;
    }

    public static double[] calculateEigenvalues(double[][] matrix) {
        RealMatrix realMatrix = MatrixUtils.createRealMatrix(matrix);

        EigenDecomposition eigenDecomposition = new EigenDecomposition(realMatrix);
        double[] eigenvalues = eigenDecomposition.getRealEigenvalues();

        return eigenvalues;
    }

    public static List<MatrixBobot> getDataHasilPerhitungan2() {
        double[][] matrix = Storage.getInstance().getMatrixAlternatif();
        List<MatrixBobot> result = new ArrayList<>();

        int numAlternatives = matrix.length;
        int numCriteria = matrix[0].length;

        List<String> anggotas = Storage.getInstance().getAlternatif();;

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
            map.setBobot(Math.round((jumlahBaris[counter] / 6) * 100.0) / 100.0);
            counter++;
        }


        return result;
    }

    public static List<Kodifikasi> getDataHasilPerhitungan1() {
        double[][] matrix = Storage.getInstance().getMatrixAlternatif();

        List<Kodifikasi> result = new ArrayList<>();

        List<String> anggotas = Storage.getInstance().getAlternatif();;

        for (int i = 0; i < matrix.length; i++) {
            Kodifikasi matrixInput = new Kodifikasi();
            matrixInput.setAnggota(anggotas.get(i));
            matrixInput.setK1((int)matrix[i][0]);
            matrixInput.setK2((int)matrix[i][1]);
            matrixInput.setK3((int)matrix[i][2]);
            matrixInput.setK4((int)matrix[i][3]);
            matrixInput.setK5((int)matrix[i][4]);
            matrixInput.setK6((int)matrix[i][5]);

            result.add(matrixInput);
        }

        return result;

    }

    public static List<MatrixInputAndNormalisasi> getDataFromObjectArrayMatrixAhp1() {

        List<MatrixInputAndNormalisasi> result = new ArrayList<>();
        double[][] perbMatriksInput = Storage.getInstance().getMatrixBasic();

        for (int i = 0; i < perbMatriksInput.length; i++) {
            MatrixInputAndNormalisasi matrixInput = new MatrixInputAndNormalisasi();
            matrixInput.setK1("K" + (i + 1));
            matrixInput.setK2(Math.round(perbMatriksInput[i][0] * 10.0) / 10.0);
            matrixInput.setK3(Math.round(perbMatriksInput[i][1] * 10.0) / 10.0);
            matrixInput.setK4(Math.round(perbMatriksInput[i][2] * 10.0) / 10.0);
            matrixInput.setK5(Math.round(perbMatriksInput[i][3] * 10.0) / 10.0);
            matrixInput.setK6(Math.round(perbMatriksInput[i][4] * 10.0) / 10.0);
            matrixInput.setK7(Math.round(perbMatriksInput[i][5] * 10.0) / 10.0);

            result.add(matrixInput);
        }

        SubKriteriaAhp sub = new SubKriteriaAhp(perbMatriksInput);

        System.out.println("\nJumlah dari masing-masing Kolom : ");
        double[] jumlahkolom = sub.calcJumlahKolom();
        MatrixInputAndNormalisasi total = new MatrixInputAndNormalisasi();
        total.setK1("Total");
        total.setK2(Math.round(jumlahkolom[0] * 100.0) / 100.0);
        total.setK3(Math.round(jumlahkolom[1] * 100.0) / 100.0);
        total.setK4(Math.round(jumlahkolom[2] * 100.0) / 100.0);
        total.setK5(Math.round(jumlahkolom[3] * 100.0) / 100.0);
        total.setK6(Math.round(jumlahkolom[4] * 100.0) / 100.0);
        total.setK7(Math.round(jumlahkolom[5] * 100.0) / 100.0);

        result.add(total);
        return result;
    }

    public static List<MatrixInputAndNormalisasi> getDataFromObjectArrayMatrixAhp2() {

        List<MatrixInputAndNormalisasi> result = new ArrayList<>();
        double[][] perbMatriksInput =  Storage.getInstance().getMatrixBasic();

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

        System.out.println("\nJumlah dari masing-masing Kolom : ");
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

    public static List<MatrixBobot> getDataFromObjectArrayMatrixAhp3() {
        List<MatrixBobot> result = new ArrayList<>();

        double[][] perbMatriksInput =  Storage.getInstance().getMatrixBasic();

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
            map.setBobot(Math.round((jumlahBaris[counter] / 6) * 100.0) / 100.0);
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
            result.get(i).setEigenVal(Math.round((val / 6) * 100.0) / 100.0);
            i++;
        }

        return result;
    }

    public static List<Kodifikasi> getDataKodifikasi() {
        List<Kodifikasi> result = new ArrayList<>();
        Kodifikasi data1 = new Kodifikasi();
        data1.setAnggota("Maria");
        data1.setK1(3);
        data1.setK2(1);
        data1.setK3(0);
        data1.setK4(1);
        data1.setK5(2);
        data1.setK6(2);

        Kodifikasi data2 = new Kodifikasi();
        data2.setAnggota("Marni");
        data2.setK1(2);
        data2.setK2(0);
        data2.setK3(1);
        data2.setK4(1);
        data2.setK5(3);
        data2.setK6(3);

        Kodifikasi data3 = new Kodifikasi();
        data3.setAnggota("Agus");
        data3.setK1(4);
        data3.setK2(1);
        data3.setK3(0);
        data3.setK4(1);
        data3.setK5(1);
        data3.setK6(1);

        result.add(data1);
        result.add(data2);
        result.add(data3);

        return result;
    }

}
