package com.datngo.domain;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * not an ignored comment
 */
@Entity
@Table(name = "quan_ly_giao_dich")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class QuanLyGiaoDich implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "so_tien_giao_dich")
    private Long soTienGiaoDich;

    @Column(name = "loai_giao_dich")
    private String loaiGiaoDich;

    @Column(name = "ngay_giao_dich")
    private LocalDate ngayGiaoDich;

    @Column(name = "trang_thai")
    private Integer trangThai;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSoTienGiaoDich() {
        return soTienGiaoDich;
    }

    public QuanLyGiaoDich soTienGiaoDich(Long soTienGiaoDich) {
        this.soTienGiaoDich = soTienGiaoDich;
        return this;
    }

    public void setSoTienGiaoDich(Long soTienGiaoDich) {
        this.soTienGiaoDich = soTienGiaoDich;
    }

    public String getLoaiGiaoDich() {
        return loaiGiaoDich;
    }

    public QuanLyGiaoDich loaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
        return this;
    }

    public void setLoaiGiaoDich(String loaiGiaoDich) {
        this.loaiGiaoDich = loaiGiaoDich;
    }

    public LocalDate getNgayGiaoDich() {
        return ngayGiaoDich;
    }

    public QuanLyGiaoDich ngayGiaoDich(LocalDate ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
        return this;
    }

    public void setNgayGiaoDich(LocalDate ngayGiaoDich) {
        this.ngayGiaoDich = ngayGiaoDich;
    }

    public Integer getTrangThai() {
        return trangThai;
    }

    public QuanLyGiaoDich trangThai(Integer trangThai) {
        this.trangThai = trangThai;
        return this;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof QuanLyGiaoDich)) {
            return false;
        }
        return id != null && id.equals(((QuanLyGiaoDich) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "QuanLyGiaoDich{" +
            "id=" + getId() +
            ", soTienGiaoDich=" + getSoTienGiaoDich() +
            ", loaiGiaoDich='" + getLoaiGiaoDich() + "'" +
            ", ngayGiaoDich='" + getNgayGiaoDich() + "'" +
            ", trangThai=" + getTrangThai() +
            "}";
    }
}
