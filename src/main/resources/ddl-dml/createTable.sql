CREATE TABLE `kriteria` (
  `id` int(3) NOT NULL,
  `kriteria_name` varchar(100) DEFAULT NULL,
  `k1_val` double DEFAULT NULL,
  `k2_val` double DEFAULT NULL,
  `k3_val` double DEFAULT NULL,
  `k4_val` double DEFAULT NULL,
  `k5_val` double DEFAULT NULL,
  `k6_val` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

CREATE TABLE `alternatif` (
  `id` int(3) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `alamat` varchar(255) DEFAULT NULL,
  `k1_sub_id` int(3) DEFAULT NULL,
  `k2_sub_id` int(3) DEFAULT NULL,
  `k3_sub_id` int(3) DEFAULT NULL,
  `k4_sub_id` int(3) DEFAULT NULL,
  `k5_sub_id` int(3) DEFAULT NULL,
  `k6_sub_id` int(3) DEFAULT NULL,
  `score` double DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


CREATE TABLE `sub_kriteria` (
  `id` int(3) NOT NULL,
  `kriteria_id` int(3) NOT NULL,
  `sub_kriteria_name` varchar(100) DEFAULT NULL,
  `nilai` int(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;