package com.projects.simplescript.dao;


import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projects.simplescript.model.biz.AlternativeList;
import com.projects.simplescript.model.biz.Kodifikasi;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class CustomSqlRepository {

    private final JdbcTemplate jdbcTemplate;

    public List<AlternativeList> getAllAlternatifValueNameUsingJdbc() {
        String query = "SELECT " +
                "a.name as namaAnggota, " +
                "(SELECT sub_kriteria_name FROM sub_kriteria sk WHERE sk.id = a.k1_sub_id) as biayaPendidikan, " +
                "(SELECT sub_kriteria_name FROM sub_kriteria sk WHERE sk.id = a.k2_sub_id) as pembelianRumah, " +
                "(SELECT sub_kriteria_name FROM sub_kriteria sk WHERE sk.id = a.k3_sub_id) as perbaikanRumah, " +
                "(SELECT sub_kriteria_name FROM sub_kriteria sk WHERE sk.id = a.k4_sub_id) as bayarTepatWaktu, " +
                "(SELECT sub_kriteria_name FROM sub_kriteria sk WHERE sk.id = a.k5_sub_id) as catatanPinjaman, " +
                "(SELECT sub_kriteria_name FROM sub_kriteria sk WHERE sk.id = a.k6_sub_id) as tanggungan " +
                "FROM alternatif a";
 
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(AlternativeList.class));
    }
    public List<Kodifikasi> getAllAlternatifValueNilaiUsingJdbc() {
        String query = "SELECT " +
                "a.name as anggota, " +
                "(SELECT nilai FROM sub_kriteria sk WHERE sk.id = a.k1_sub_id) as k1, " +
                "(SELECT nilai FROM sub_kriteria sk WHERE sk.id = a.k2_sub_id) as k2 , " +
                "(SELECT nilai FROM sub_kriteria sk WHERE sk.id = a.k3_sub_id) as k3, " +
                "(SELECT nilai FROM sub_kriteria sk WHERE sk.id = a.k4_sub_id) as k4, " +
                "(SELECT nilai FROM sub_kriteria sk WHERE sk.id = a.k5_sub_id) as k5, " +
                "(SELECT nilai FROM sub_kriteria sk WHERE sk.id = a.k6_sub_id) as k6      " +
                "FROM alternatif a";
 
        return jdbcTemplate.query(query, new BeanPropertyRowMapper<>(Kodifikasi.class));
    }

    public void updateAlternatifSubIdByName(String nama, Integer kriteriaId, Integer subId) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("UPDATE alternatif SET ");
        queryBuilder.append("k" + kriteriaId + "_sub_id = ? ");
        queryBuilder.append("WHERE name = ?");

        jdbcTemplate.update(queryBuilder.toString(), subId, nama);
    }

}
