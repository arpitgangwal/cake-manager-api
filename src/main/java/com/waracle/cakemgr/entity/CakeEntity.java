package com.waracle.cakemgr.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@DynamicUpdate
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "CAKE", uniqueConstraints = {@UniqueConstraint(columnNames = "name")})
public class CakeEntity implements Serializable {

    private static final long serialVersionUID = -2417760290457013668L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false)
    private Integer id;

    @NotNull
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "desc", nullable = false)
    private String description;

    @Column(name = "image", nullable = true)
    private String imageUrl;
}