package com.projects.simplescript.model.biz;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.linear.EigenDecomposition;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import org.apache.commons.math3.linear.SingularValueDecomposition;

import com.projects.simplescript.utils.AddressGenerated;
import com.projects.simplescript.utils.logicahp.SubKriteriaAhp;

public class DummyData {

    public static double[][] dataMatrix() {
        double[][] perbMatriksInput = {
                { 1, 2, 3, 7, 2, 6 },
                { 0.5, 1, 4, 3, 3, 7 },
                { 0.33, 0.25, 1, 2, 3, 5 },
                { 0.14, 0.33, 0.5, 1, 1, 3 },
                { 0.5, 0.33, 0.33, 1, 1, 2 },
                { 0.17, 0.14, 0.2, 0.33, 0.5, 1 },
        };
        return perbMatriksInput;
    }

    public static double[][] dataMatrixAlternative() {
        double[][] matrix = {
                { 3, 1, 0, 1, 2, 2 },
                { 2, 0, 1, 1, 3, 3 },
                { 4, 1, 0, 1, 1, 1 }
        };
        return matrix;
    }

    public static List<HasilPriority> getDataHasilPerhitungan3(){
        double[][] matrix = dataMatrixAlternative();
        List<String> anggotas = List.of("Maria", "Marni", "Agus");
       
        int numAlternatives = matrix.length;
        int numCriteria = matrix[0].length;

        // Normalize matrix columns
        for (int col = 0; col < numCriteria; col++) {
            double colSum = 0;
            for (int row = 0; row < numAlternatives; row++) {
                colSum += matrix[row][col];
            }
            for (int row = 0; row < numAlternatives; row++) {
                matrix[row][col] /= colSum;
            }
        }

        // Calculate priorities using Singular Value Decomposition
        RealMatrix normalizedMatrix = MatrixUtils.createRealMatrix(matrix);
        SingularValueDecomposition svd = new SingularValueDecomposition(normalizedMatrix);
        RealMatrix rightSingularVectors = svd.getV();

        // Calculate priorities (eigenvectors) from right singular vectors
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
        double[][] matrix = dataMatrixAlternative();
        List<MatrixBobot> result = new ArrayList<>();

        int numAlternatives = matrix.length;
        int numCriteria = matrix[0].length;

        List<String> anggotas = List.of("Maria", "Marni", "Agus");

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
        double[][] matrix = dataMatrixAlternative();

        List<Kodifikasi> result = new ArrayList<>();

        List<String> anggotas = List.of("Maria", "Marni", "Agus");

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
        double[][] perbMatriksInput = dataMatrix();

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
        double[][] perbMatriksInput = dataMatrix();

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

        double[][] perbMatriksInput = dataMatrix();

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

    public static List<AlternativeList> getDataKuisioner() {

        List<AlternativeList> result = new ArrayList<>();

        AlternativeList data1 = new AlternativeList();
        data1.setNamaAnggota("Maria");
        data1.setBiayaPendidikan("50 jt - 75 jt");
        data1.setPembelianRumah("Ya");
        data1.setPerbaikanRumah("Tidak");
        data1.setBayarTepatWaktu("Selalu");
        data1.setCatatanPinjaman("Baik");
        data1.setTanggungan("Dua");

        AlternativeList data2 = new AlternativeList();
        data2.setNamaAnggota("Marni");
        data2.setBiayaPendidikan("25 jt - 50 jt");
        data2.setPembelianRumah("Tidak");
        data2.setPerbaikanRumah("Ya");
        data2.setBayarTepatWaktu("Selalu");
        data2.setCatatanPinjaman("Sangat baik");
        data2.setTanggungan("Tiga");

        AlternativeList data3 = new AlternativeList();
        data3.setNamaAnggota("Agus");
        data3.setBiayaPendidikan("75 jt - 100 jt");
        data3.setPembelianRumah("Ya");
        data3.setPerbaikanRumah("Tidak");
        data3.setBayarTepatWaktu("Selalu");
        data3.setCatatanPinjaman("Biasa");
        data3.setTanggungan("Satu");

        result.add(data1);
        result.add(data2);
        result.add(data3);

        return result;
    }

    public static List<Anggota> getDummyAnggota() {

        List<Anggota> result = new ArrayList<>();
        List<String> anggotas = List.of("Maria", "Agus", "Marni");
        List<String> alamats = AddressGenerated.generateDummyAddresses(3);
        for (int i = 0; i < anggotas.size(); i++) {
            Anggota dum = new Anggota();
            Integer id = i + 1;
            dum.setIdAnggota(id);
            dum.setNamaAnggota(anggotas.get(i));
            dum.setAlamatAnggota(alamats.get(i));

            result.add(dum);
        }

        return result;
    }

    public static List<SubKriteria> getDummySubKriteria() {
        List<SubKriteria> result = new ArrayList<>();
        List<Kriteria> lstKriteria = getDummyKriteria();

        for (Kriteria kriteria : lstKriteria) {
            if (kriteria.getId() == 1) {
                SubKriteria data1 = new SubKriteria(1, kriteria.getId(), "25 jt - 50 jt", 2);
                SubKriteria data2 = new SubKriteria(2, kriteria.getId(), "50 jt - 75 jt", 3);
                SubKriteria data3 = new SubKriteria(3, kriteria.getId(), "75 jt - 100 jt", 4);
                SubKriteria data4 = new SubKriteria(4, kriteria.getId(), "Lebih dari 100 jt", 5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
            } else if (kriteria.getId() == 2) {
                SubKriteria data1 = new SubKriteria(5, kriteria.getId(), "Tidak Pernah", 1);
                SubKriteria data2 = new SubKriteria(6, kriteria.getId(), "Jarang", 2);
                SubKriteria data3 = new SubKriteria(7, kriteria.getId(), "Kadang-kadang", 3);
                SubKriteria data4 = new SubKriteria(8, kriteria.getId(), "Sering", 4);
                SubKriteria data5 = new SubKriteria(9, kriteria.getId(), "Selalu", 5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);
            } else if (kriteria.getId() == 3) {
                SubKriteria data1 = new SubKriteria(10, kriteria.getId(), "Ya", 1);
                SubKriteria data2 = new SubKriteria(11, kriteria.getId(), "Tidak", 0);
                result.add(data1);
                result.add(data2);
            } else if (kriteria.getId() == 4) {
                SubKriteria data1 = new SubKriteria(12, kriteria.getId(), "Tidak Pernah", 1);
                SubKriteria data2 = new SubKriteria(13, kriteria.getId(), "Jarang", 2);
                SubKriteria data3 = new SubKriteria(14, kriteria.getId(), "Kadang-kadang", 3);
                SubKriteria data4 = new SubKriteria(15, kriteria.getId(), "Sering", 4);
                SubKriteria data5 = new SubKriteria(16, kriteria.getId(), "Selalu", 5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);
            } else if (kriteria.getId() == 5) {
                SubKriteria data1 = new SubKriteria(17, kriteria.getId(), "Sangat Buruk", 1);
                SubKriteria data2 = new SubKriteria(18, kriteria.getId(), "Buruk", 2);
                SubKriteria data3 = new SubKriteria(19, kriteria.getId(), "Biasa", 3);
                SubKriteria data4 = new SubKriteria(20, kriteria.getId(), "Baik", 4);
                SubKriteria data5 = new SubKriteria(21, kriteria.getId(), "Sangat Baik", 5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);
            } else if (kriteria.getId() == 6) {
                SubKriteria data1 = new SubKriteria(22, kriteria.getId(), "Satu", 1);
                SubKriteria data2 = new SubKriteria(23, kriteria.getId(), "Dua", 2);
                SubKriteria data3 = new SubKriteria(24, kriteria.getId(), "Tiga", 3);
                SubKriteria data4 = new SubKriteria(25, kriteria.getId(), "Lebih dari 3", 4);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
            }
        }

        return result;
    }

    public static List<Kriteria> getDummyKriteria() {
        List<Kriteria> arr = new ArrayList<>();
        Kriteria data1 = new Kriteria(1, "Biaya Pendidikan");
        Kriteria data2 = new Kriteria(2, "Pembelian Rumah");
        Kriteria data3 = new Kriteria(3, "Perbaikan Rumah");
        Kriteria data4 = new Kriteria(4, "Pembayaran Tepat Waktu");
        Kriteria data5 = new Kriteria(5, "Catatan Pinjaman");
        Kriteria data6 = new Kriteria(6, "Tanggungan anak");
        arr.add(data1);
        arr.add(data2);
        arr.add(data3);
        arr.add(data4);
        arr.add(data5);
        arr.add(data6);

        return arr;
    }

}
