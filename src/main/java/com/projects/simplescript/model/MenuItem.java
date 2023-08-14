package com.projects.simplescript.model;

// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
// @Entity
// @Table(name = "menuitem")
public class MenuItem {
    // @Id
    private String id;
    private String parent;
    private String name;
    private String image;
    private Integer gridId;
}
