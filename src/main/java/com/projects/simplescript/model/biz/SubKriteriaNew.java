package com.projects.simplescript.model.biz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "sub_kriteria")
@Data
public class SubKriteriaNew {

    @Id
    @Column(name = "id")
    private Integer id;

    @Column(name = "kriteria_id")
    private Integer kriteriaId;

    @Column(name = "sub_kriteria_name")
    private String subKriteriaName;

    private Integer nilai;

}
