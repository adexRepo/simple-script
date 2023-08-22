package com.projects.simplescript.model.biz;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "alternatif")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AlternativeNew {

    @Id
    private Integer id;

    private String name;
    private String alamat;

    @Column(name = "k1_sub_id")
    private Integer k1SubId;

    @Column(name = "k2_sub_id")
    private Integer k2SubId;

    @Column(name = "k3_sub_id")
    private Integer k3SubId;

    @Column(name = "k4_sub_id")
    private Integer k4SubId;

    @Column(name = "k5_sub_id")
    private Integer k5SubId;

    @Column(name = "k6_sub_id")
    private Integer k6SubId;

    private Double score;
    private String status;

}
