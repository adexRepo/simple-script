package com.projects.simplescript.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.projects.simplescript.model.biz.AlternativeList;
import com.projects.simplescript.model.biz.Anggota;
import com.projects.simplescript.model.biz.Kodifikasi;
import com.projects.simplescript.model.biz.Kriteria;
import com.projects.simplescript.model.biz.SubKriteria;
import com.projects.simplescript.utils.AddressGenerated;
import com.projects.simplescript.utils.Validator;

import lombok.Data;

@Data
public class Storage {
    private static Storage instance;
    private Map<String,Object> config;
    private double[][] matrixBasic;
    private double[][] matrixAlternatif;
    private List<String> alternatif;
    private List<Kriteria> kriteria;
    private List<SubKriteria> subKriteria;
    private List<Kodifikasi> kodifikasi;
    private List<AlternativeList> displayAlternatif;
    private List<Anggota> anggotas;

    private Storage() {
        matrixBasic = dataMatrix();
        alternatif = dataListAlternative();
        kriteria = getDummyKriteria();
        subKriteria = getDummySubKriteria();
        kodifikasi = dataKodifikasi();
        matrixAlternatif = dataMatrixAlternative(kodifikasi);
        displayAlternatif = getDisplayAlternatifDummy(kodifikasi);
        anggotas = dataAnggotaDisplay();
    }

    public static Storage getInstance() {
        if (instance == null) {
            instance = new Storage();
        }

        refresh();
        return instance;
    }

    public static void refresh() {
        instance.setMatrixAlternatif(dataMatrixAlternative(null));
        instance.setDisplayAlternatif(getDisplayAlternatifDummy(null));
        instance.setAnggotas(dataAnggotaDisplay());
    }

    public static void checkConfig(){
        String key = (String) instance.getConfig().get("key");
        String val = (String) instance.getConfig().get("val");
        String fin = (String) instance.getConfig().get("fin");
        String app = (String) instance.getConfig().get("app");
        String title = (String) instance.getConfig().get("title");

        boolean javaFXConfig = Validator.checkCredential(key, val, fin);
        boolean springConfig = Validator.checkAppType(title, key, app);
        if (!(javaFXConfig && springConfig)) {
            throw new RuntimeException("Application FAILED To Run");
        }
    }


    /* ---------------------------------- BASIC --------------------------------- */

    private static double[][] dataMatrix() {
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

    private static List<String> dataListAlternative() {
        return List.of("Maria", "Marni", "Agus");
    }

    private static List<Kriteria> getDummyKriteria() {
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

    private static List<SubKriteria> getDummySubKriteria() {
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

    /* ------------------------------- DATA DEPEND ------------------------------ */

    private static List<Kodifikasi> dataKodifikasi() {
        List<String> anggota = dataListAlternative();
        List<Kodifikasi> dataId = new ArrayList<>();

        Kodifikasi a = new Kodifikasi(anggota.get(0), 2, 5, 10, 16, 20, 23);
        Kodifikasi b = new Kodifikasi(anggota.get(1), 1, 6, 11, 16, 21, 24);
        Kodifikasi c = new Kodifikasi(anggota.get(2), 3, 7, 10, 16, 19, 22);

        dataId.add(a);
        dataId.add(b);
        dataId.add(c);

        return dataId;
    }

    private static double[][] dataMatrixAlternative(List<Kodifikasi> inpKod) {
        int size = getDummyKriteria().size();
        List<Kodifikasi> kodifikasi = inpKod == null ? instance.getKodifikasi() : inpKod;
        double[][] matrix = new double[kodifikasi.size()][size];
        for (int index = 0; index < kodifikasi.size(); index++) {
            Kodifikasi kod = kodifikasi.get(index);
            Double nilaiK1 = helperGetNilai(kod.getK1());
            Double nilaiK2 = helperGetNilai(kod.getK2());
            Double nilaiK3 = helperGetNilai(kod.getK3());
            Double nilaiK4 = helperGetNilai(kod.getK4());
            Double nilaiK5 = helperGetNilai(kod.getK5());
            Double nilaiK6 = helperGetNilai(kod.getK6());
            matrix[index][0] = nilaiK1;
            matrix[index][1] = nilaiK2;
            matrix[index][2] = nilaiK3;
            matrix[index][3] = nilaiK4;
            matrix[index][4] = nilaiK5;
            matrix[index][5] = nilaiK6;
        }
        return matrix;
    }

    private static List<AlternativeList> getDisplayAlternatifDummy(List<Kodifikasi> inpKod) {

        List<SubKriteria> subKriteria = getDummySubKriteria();
        List<Kodifikasi> kodifikasiId = inpKod == null ? instance.getKodifikasi() : inpKod;
        List<AlternativeList> result = new ArrayList<>();
        for (Kodifikasi item : kodifikasiId) {
            AlternativeList alter = new AlternativeList();
            alter.setNamaAnggota(item.getAnggota());

            SubKriteria k1 = helper(subKriteria, item.getK1());
            SubKriteria k2 = helper(subKriteria, item.getK2());
            SubKriteria k3 = helper(subKriteria, item.getK3());
            SubKriteria k4 = helper(subKriteria, item.getK4());
            SubKriteria k5 = helper(subKriteria, item.getK5());
            SubKriteria k6 = helper(subKriteria, item.getK6());

            alter.setBiayaPendidikan(k1.getSubkriteriaName());
            alter.setPembelianRumah(k2.getSubkriteriaName());
            alter.setPerbaikanRumah(k3.getSubkriteriaName());
            alter.setBayarTepatWaktu(k4.getSubkriteriaName());
            alter.setCatatanPinjaman(k5.getSubkriteriaName());
            alter.setTanggungan(k6.getSubkriteriaName());

            result.add(alter);
        }

        return result;
    }

    private static SubKriteria helper(List<SubKriteria> subKriterias, Integer tgt) {
        SubKriteria k = subKriterias.stream()
                .filter(val -> val.getId().equals(tgt))
                .findFirst()
                .orElse(null);

        return k;
    }

    private static List<Anggota> dataAnggotaDisplay() {

        List<Anggota> result = new ArrayList<>();
        List<String> anggotas = dataListAlternative();
        List<String> alamats = AddressGenerated.generateDummyAddresses(dataListAlternative().size());
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

    private static Double helperGetNilai(Integer idSub) {
        List<SubKriteria> dataSubKriteria = getDummySubKriteria();
        SubKriteria subKriteriaDtl = dataSubKriteria.stream()
                .filter(val -> val.getId().equals(idSub))
                .findFirst()
                .orElse(null);

        return subKriteriaDtl.getNilai().doubleValue();
    }
}
