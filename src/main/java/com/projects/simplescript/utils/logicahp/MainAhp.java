package com.projects.simplescript.utils.logicahp;

public class MainAhp {
    public static void execute(String[] args) throws Exception {
        System.out.println("=====================================================");
        System.out.println("\tProgram Pendukung Keputusan Beasiswa");
        System.out.println("\t\tMenggunakan Metode AHP");
        System.out.println("=====================================================");

        Kriteria objKriteria  = new Kriteria("Biaya Pendidikan", 1, "K1");
        Kriteria objKriteria2 = new Kriteria("Pembelian Rumah", 2, "K2");
        Kriteria objKriteria3 = new Kriteria("Perbaikan Rumah", 3, "K3");
        Kriteria objKriteria4 = new Kriteria("Pembayaran Tepat Waktu", 4, "K4");
        Kriteria objKriteria5 = new Kriteria("Catatan Pinjaman", 5, "K5");
        Kriteria objKriteria6 = new Kriteria("Tanggungan anak", 6, "K6");
        int jumlahKriteria = 6;
        objKriteria.show();
        objKriteria2.show();
        objKriteria3.show();    
        objKriteria4.show();
        objKriteria5.show();
        objKriteria6.show();
        
        PB perbandinganMatriks = new PB();
        
        double[][] perbMatriksInput = new double[jumlahKriteria][jumlahKriteria];
        
        perbMatriksInput[0][0] = 1;
        perbMatriksInput[0][1] = 2;
        perbMatriksInput[0][2] = 3;
        perbMatriksInput[0][3] = 7;
        perbMatriksInput[0][4] = 2;
        perbMatriksInput[0][5] = 6;
        
        perbMatriksInput[1][0] = 0.5;
        perbMatriksInput[1][1] = 1;
        perbMatriksInput[1][2] = 4;
        perbMatriksInput[1][3] = 3;
        perbMatriksInput[1][4] = 3;
        perbMatriksInput[1][5] = 7;
        
        perbMatriksInput[2][0] = 0.33;
        perbMatriksInput[2][1] = 0.25;
        perbMatriksInput[2][2] = 1;
        perbMatriksInput[2][3] = 2;
        perbMatriksInput[2][4] = 3;
        perbMatriksInput[2][5] = 5;
        
        perbMatriksInput[3][0] = 0.14;
        perbMatriksInput[3][1] = 0.33;
        perbMatriksInput[3][2] = 0.5;
        perbMatriksInput[3][3] = 1;
        perbMatriksInput[3][4] = 1;
        perbMatriksInput[3][5] = 3;

        perbMatriksInput[4][0] = 0.5;
        perbMatriksInput[4][1] = 0.33;
        perbMatriksInput[4][2] = 0.33;
        perbMatriksInput[4][3] = 1;
        perbMatriksInput[4][4] = 1;
        perbMatriksInput[4][5] = 2;

        perbMatriksInput[5][0] = 0.17;
        perbMatriksInput[5][1] = 0.14;
        perbMatriksInput[5][2] = 0.2;
        perbMatriksInput[5][3] = 0.33;
        perbMatriksInput[5][4] = 0.5;
        perbMatriksInput[5][5] = 1;

        System.out.println("\nMatriks yang di-Input : ");
        perbandinganMatriks.display(perbMatriksInput);
    
        
        SubKriteriaAhp sub = new SubKriteriaAhp(perbMatriksInput);
        
        System.out.println("\nJumlah dari masing-masing Kolom : ");
        double[] jumlahkolom = sub.calcJumlahKolom();
        sub.display(jumlahkolom);

        
        System.out.println("\nSubKriteria Matriks : ");
        double[][] matriksnormalisasi = sub.normMatriks(jumlahkolom);
        perbandinganMatriks.display(matriksnormalisasi);
        
        
        System.out.println("\nJumlah dari masing-masing Baris : ");
        double[] jumlahbaris = sub.calcJumlahBaris(matriksnormalisasi);
        sub.display(jumlahbaris);
        
        
        System.out.println("\nPrioritasnya adalah : ");
        double[] prioritas = sub.calcPrioritas(jumlahbaris);
        sub.display(prioritas);
        
        
        System.out.println("\nMatriks Penjumlahan setiap Baris adalah : ");
        double[][] matrikspenjumlahanbaris = sub.calcMatriksPenjumlahanBaris(prioritas);
        perbandinganMatriks.display(matrikspenjumlahanbaris);
        
        
        System.out.println("\nJumlah Baris dari Matriks Penjumlahan setiap Baris adalah : ");
        double[] jumlahmatrikspnjmlhbaris = sub.calcJumlahBaris(matrikspenjumlahanbaris);
        sub.display(jumlahmatrikspnjmlhbaris);
        
        System.out.println("\nmax prioritas");
        double nilaiMaks = sub.nilaiMaxPrior(prioritas);
        
        System.out.println("Nilai Sub Kriteria ");
        double[] hitungSubKriteria = sub.subPrioritas(prioritas, nilaiMaks);
        sub.display(hitungSubKriteria);

        System.out.println("\nPerbandingan Rasio Konsistensi : ");
        double[] perrasiokonsistensi = sub.calcPerbandinganRasio(jumlahmatrikspnjmlhbaris,prioritas);
        sub.display(perrasiokonsistensi);

        System.out.println("\nKonsistensi Rasio adalah : ");
        sub.display();
        sub.Konsistensi();

        System.out.println("\n======================================");
        System.out.println("\tData Penerima Beasiswa");
        System.out.println("======================================");
        
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Nama    | "+objKriteria.namaKriteria+"        | "+objKriteria2.namaKriteria+"  | "+objKriteria3.namaKriteria+"    | "+objKriteria4.namaKriteria+" | "+objKriteria5.namaKriteria+" || "+objKriteria6.namaKriteria+"");
        System.out.println("----------------------------------------------------------------------------------------");
        System.out.println("Maria\t    4.00\t\t\t< 1.500.000\t\t 3\t\t\t6");
        System.out.println("Marni\t  3.00-3.50\t\t\t> 3.000.000\t\t<=2\t\t\t5");
        System.out.println("Agus\t  3.00-3.50\t\t\t< 1.500.000\t\t<=3\t\t\t4");

    }
    
}