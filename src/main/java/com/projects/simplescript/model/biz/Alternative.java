package com.projects.simplescript.model.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Alternative {
    private Integer colNo         ;
    private String  colNamaAnggota;
    private String  colAlamat     ;
    private String  colStatus     ;
}
