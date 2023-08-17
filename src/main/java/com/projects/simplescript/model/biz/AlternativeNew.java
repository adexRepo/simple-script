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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String alamat;

    @Column(name = "k1_sub_id")
    private int k1SubId;

    @Column(name = "k2_sub_id")
    private int k2SubId;

    @Column(name = "k3_sub_id")
    private int k3SubId;

    @Column(name = "k4_sub_id")
    private int k4SubId;

    @Column(name = "k5_sub_id")
    private int k5SubId;

    @Column(name = "k6_sub_id")
    private int k6SubId;

    private Double score;
    private String status;

}
