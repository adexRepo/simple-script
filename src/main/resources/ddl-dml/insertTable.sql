INSERT INTO kriteria (id,kriteria_name,k1_val,k2_val,k3_val,k4_val,k5_val,k6_val) VALUES
	 (1,'Biaya Pendidikan',1.0,2.0,3.0,7.0,2.0,6.0),
	 (2,'Pembelian Rumah',0.5,1.0,4.0,3.0,3.0,7.0),
	 (3,'Perbaikan Rumah',0.33,0.25,1.0,2.0,3.0,5.0),
	 (4,'Pembayaran Tepat Waktu',0.14,0.33,0.5,1.0,1.0,3.0),
	 (5,'Catatan Pinjaman',0.5,0.33,0.33,1.0,1.0,2.0),
	 (6,'Tanggungan anak',0.17,0.14,0.2,0.33,0.5,1.0);

INSERT INTO sub_kriteria (id,kriteria_id,sub_kriteria_name,nilai) VALUES
	 (1,1,'25 jt - 50 jt',2),
	 (2,1,'50 jt - 75 jt',3),
	 (3,1,'75 jt - 100 jt',4),
	 (4,1,'Lebih dari 100 jt',5),
	 (5,2,'Tidak Pernah',1),
	 (6,2,'Jarang',2),
	 (7,2,'Kadang-kadang',3),
	 (8,2,'Sering',4),
	 (9,2,'Selalu',5),
	 (10,3,'Ya',1);
INSERT INTO sub_kriteria (id,kriteria_id,sub_kriteria_name,nilai) VALUES
	 (11,3,'Tidak',0),
	 (12,4,'Tidak Pernah',1),
	 (13,4,'Jarang',2),
	 (14,4,'Kadang-kadang',3),
	 (15,4,'Sering',4),
	 (16,4,'Selalu',5),
	 (17,5,'Sangat Buruk',1),
	 (18,5,'Buruk',2),
	 (19,5,'Biasa',3),
	 (20,5,'Baik',4);
INSERT INTO sub_kriteria (id,kriteria_id,sub_kriteria_name,nilai) VALUES
	 (21,5,'Sangat Baik',5),
	 (22,6,'Satu',1),
	 (23,6,'Dua',2),
	 (24,6,'Tiga',3),
	 (25,6,'Lebih dari 3',4);


INSERT INTO alternatif (id,name,alamat,k1_sub_id,k2_sub_id,k3_sub_id,k4_sub_id,k5_sub_id,k6_sub_id,score,status) VALUES
	 (0,'Maria','Jalan Raya Cijantung No. 01 Cijantung, Ciracas, jakarta Timur.',4,5,10,16,20,23,NULL,NULL),
	 (1,'Marni','Jalan Raya PKP No. 24 Kelapa Dua Wetan, Ciracas, jakarta Timur.',1,6,11,16,21,24,NULL,NULL),
	 (2,'Agus','Jalan Raya Bogor No. 24 Gedong, Pasar Rebo, jakarta Timur.',3,7,10,16,19,22,NULL,NULL);
