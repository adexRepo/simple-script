package com.projects.simplescript.model.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Anggota {
    private Integer idAnggota;
    private String namaAnggota;
    private String alamatAnggota;
}
