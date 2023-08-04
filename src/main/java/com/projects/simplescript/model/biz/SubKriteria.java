package com.projects.simplescript.model.biz;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SubKriteria {
    private Integer id;
    private Integer kriteriaId;
    private String SubkriteriaName;
    private Integer nilai;
}
