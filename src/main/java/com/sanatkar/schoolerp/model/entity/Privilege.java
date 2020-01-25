package com.sanatkar.schoolerp.model.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Create by ashkan on 2019/12/18
 */
@Data
@NoArgsConstructor
@Entity
public class Privilege implements Serializable {

    private static final long serialVersionUID = 2511928163502258195L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String url;

    @ManyToMany(mappedBy = "privileges", fetch = FetchType.EAGER)
    private List<Role> roles = new ArrayList<>();
}
