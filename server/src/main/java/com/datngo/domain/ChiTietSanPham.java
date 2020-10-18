package com.datngo.domain;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A ChiTietSanPham.
 */
@Entity
@Table(name = "chi_tiet_san_pham")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ChiTietSanPham implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "propeties_name")
    private String propetiesName;

    @OneToOne
    @JoinColumn(unique = true)
    private SanPham sanPham;

    @ManyToOne
    @JsonIgnoreProperties("chiTietSanPhams")
    private GioHang gioHang;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getSoLuong() {
        return soLuong;
    }

    public ChiTietSanPham soLuong(Integer soLuong) {
        this.soLuong = soLuong;
        return this;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }

    public String getPropetiesName() {
        return propetiesName;
    }

    public ChiTietSanPham propetiesName(String propetiesName) {
        this.propetiesName = propetiesName;
        return this;
    }

    public void setPropetiesName(String propetiesName) {
        this.propetiesName = propetiesName;
    }

    public SanPham getSanPham() {
        return sanPham;
    }

    public ChiTietSanPham sanPham(SanPham sanPham) {
        this.sanPham = sanPham;
        return this;
    }

    public void setSanPham(SanPham sanPham) {
        this.sanPham = sanPham;
    }

    public GioHang getGioHang() {
        return gioHang;
    }

    public ChiTietSanPham gioHang(GioHang gioHang) {
        this.gioHang = gioHang;
        return this;
    }

    public void setGioHang(GioHang gioHang) {
        this.gioHang = gioHang;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ChiTietSanPham)) {
            return false;
        }
        return id != null && id.equals(((ChiTietSanPham) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "ChiTietSanPham{" +
            "id=" + getId() +
            ", soLuong=" + getSoLuong() +
            ", propetiesName='" + getPropetiesName() + "'" +
            "}";
    }
}
