package com.example.BootsBootique;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.EnumType;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;


@Entity
@Table(name="BOOTS")
public class Boot {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="TYPE")
    @Enumerated(EnumType.STRING)
    private BootType type;

    @Column(name="SIZE")
    private Float size;

    @Column(name="QUANTITY")
    private Integer quantity;

    @Column(name="MATERIAL")
    private String material;



    public Integer getId() {
        return this.id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public BootType getType() {
        return this.type;
    }
    public void setType(BootType type) {
        this.type = type;
    }
    public Float getSize() {
        return this.size;
    }
    public void setSize(Float size) {
        this.size = size;
    }
    public Integer getQuantity() {
        return this.quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public String getMaterial() {
        return this.material;
    }
    public void setMaterial(String material) {
        this.material = material;
    }
}