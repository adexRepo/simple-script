package com.projects.simplescript.model.biz;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "kriteria")
@Data
public class KriteriaNew {

    @Id
    private Integer id;

    @Column(name = "kriteria_name")
    private String kriteriaName;

    @Column(name = "k1_val")
    private Double k1Val;

    @Column(name = "k2_val")
    private Double k2Val;

    @Column(name = "k3_val")
    private Double k3Val;

    @Column(name = "k4_val")
    private Double k4Val;

    @Column(name = "k5_val")
    private Double k5Val;

    @Column(name = "k6_val")
    private Double k6Val;

    // private String realDate = new String();;
}
