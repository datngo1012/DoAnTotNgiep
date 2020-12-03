package com.datngo.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Entity
@Table(name = "don_hang")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ten_sp")
    private String tenSanPham;

    @Column(name = "so_luong")
    private Long soLuong;

    @Column(name = "trang_thai")
    private String trangThai;

    @Column(name = "so_tien")
    private long soTien;

    @Column(name = "so_tien_dang_thieu")
    private Long soTienDangThieu;

    @Column(name = "nguoi_dung_id")
    private Long nguoiDungId;

    public Long getNguoiDungId() {
        return nguoiDungId;
    }

    public void setNguoiDungId(Long nguoiDungId) {
        this.nguoiDungId = nguoiDungId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Long getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Long soLuong) {
        this.soLuong = soLuong;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public long getSoTien() {
        return soTien;
    }

    public void setSoTien(long soTien) {
        this.soTien = soTien;
    }

    public Long getSoTienDangThieu() {
        return soTienDangThieu;
    }

    public void setSoTienDangThieu(Long soTienDangThieu) {
        this.soTienDangThieu = soTienDangThieu;
    }
}
