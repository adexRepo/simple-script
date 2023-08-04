package com.projects.simplescript.model.biz;

import java.util.ArrayList;
import java.util.List;

import com.projects.simplescript.utils.AddressGenerated;

public class DummyData {

    public static List<Anggota> getDummyAnggota() {

        List<Anggota> result = new ArrayList<>();
        List<String> anggotas = List.of("Maria", "Agus", "Arni", "Marni", "Joni");
        List<String> alamats = AddressGenerated.generateDummyAddresses(5);
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
            if( kriteria.getId() == 1){
                SubKriteria data1 = new SubKriteria(1,kriteria.getId(),"25 jt - 50 jt",2);
                SubKriteria data2 = new SubKriteria(2,kriteria.getId(),"50 jt - 75 jt",3);
                SubKriteria data3 = new SubKriteria(3,kriteria.getId(),"75 jt - 100 jt",4);
                SubKriteria data4 = new SubKriteria(4,kriteria.getId(),"Lebih dari 100 jt",5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
            }else if(kriteria.getId() == 2){
                SubKriteria data1 = new SubKriteria(5,kriteria.getId(),"Tidak Pernah",1);
                SubKriteria data2 = new SubKriteria(6,kriteria.getId(),"Jarang",2);
                SubKriteria data3 = new SubKriteria(7,kriteria.getId(),"Kadang-kadang",3);
                SubKriteria data4 = new SubKriteria(8,kriteria.getId(),"Sering",4);
                SubKriteria data5 = new SubKriteria(9,kriteria.getId(),"Selalu",5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);
            }else if(kriteria.getId() == 3){
                SubKriteria data1 = new SubKriteria(10,kriteria.getId(),"Ya",1);
                SubKriteria data2 = new SubKriteria(11,kriteria.getId(),"Tidak",0);
                result.add(data1);
                result.add(data2);
            }else if(kriteria.getId() == 4){
                SubKriteria data1 = new SubKriteria(12,kriteria.getId(),"Tidak Pernah",1);
                SubKriteria data2 = new SubKriteria(13,kriteria.getId(),"Jarang",2);
                SubKriteria data3 = new SubKriteria(14,kriteria.getId(),"Kadang-kadang",3);
                SubKriteria data4 = new SubKriteria(15,kriteria.getId(),"Sering",4);
                SubKriteria data5 = new SubKriteria(16,kriteria.getId(),"Selalu",5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);
            }else if(kriteria.getId() == 5){
                SubKriteria data1 = new SubKriteria(17,kriteria.getId(),"Sangat Buruk",1);
                SubKriteria data2 = new SubKriteria(18,kriteria.getId(),"Buruk",2);
                SubKriteria data3 = new SubKriteria(19,kriteria.getId(),"Biasa",3);
                SubKriteria data4 = new SubKriteria(20,kriteria.getId(),"Baik",4);
                SubKriteria data5 = new SubKriteria(21,kriteria.getId(),"Sangat Baik",5);
                result.add(data1);
                result.add(data2);
                result.add(data3);
                result.add(data4);
                result.add(data5);

            }else if(kriteria.getId() == 6){
                SubKriteria data1 = new SubKriteria(22,kriteria.getId(),"Satu",1);
                SubKriteria data2 = new SubKriteria(23,kriteria.getId(),"Dua",2);
                SubKriteria data3 = new SubKriteria(24,kriteria.getId(),"Tiga",3);
                SubKriteria data4 = new SubKriteria(25,kriteria.getId(),"Lebih dari 3",4);
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
